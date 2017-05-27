// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.callback.ConnectCallback;
import com.koushikdutta.async.callback.ContinuationCallback;
import java.util.Locale;
import com.koushikdutta.async.future.Continuation;
import java.net.InetAddress;
import com.koushikdutta.async.future.TransformFuture;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.ArrayDeque;
import android.net.Uri;
import com.koushikdutta.async.future.SimpleCancellable;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.AsyncSocket;
import java.net.InetSocketAddress;
import java.util.Hashtable;

public class AsyncSocketMiddleware extends SimpleMiddleware
{
    boolean connectAllAddresses;
    Hashtable<String, ConnectionInfo> connectionInfo;
    int idleTimeoutMs;
    protected AsyncHttpClient mClient;
    int maxConnectionCount;
    int port;
    InetSocketAddress proxyAddress;
    String proxyHost;
    int proxyPort;
    String scheme;
    
    public AsyncSocketMiddleware(final AsyncHttpClient asyncHttpClient) {
        this(asyncHttpClient, "http", 80);
    }
    
    public AsyncSocketMiddleware(final AsyncHttpClient mClient, final String scheme, final int port) {
        this.idleTimeoutMs = 300000;
        this.connectionInfo = new Hashtable<String, ConnectionInfo>();
        this.maxConnectionCount = Integer.MAX_VALUE;
        this.mClient = mClient;
        this.scheme = scheme;
        this.port = port;
    }
    
    private ConnectionInfo getOrCreateConnectionInfo(final String s) {
        ConnectionInfo connectionInfo = this.connectionInfo.get(s);
        if (connectionInfo == null) {
            connectionInfo = new ConnectionInfo();
            this.connectionInfo.put(s, connectionInfo);
        }
        return connectionInfo;
    }
    
    private void idleSocket(final AsyncSocket asyncSocket) {
        asyncSocket.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                asyncSocket.setClosedCallback(null);
                asyncSocket.close();
            }
        });
        asyncSocket.setWriteableCallback(null);
        asyncSocket.setDataCallback(new DataCallback.NullDataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                super.onDataAvailable(dataEmitter, list);
                list.recycle();
                asyncSocket.setClosedCallback(null);
                asyncSocket.close();
            }
        });
    }
    
    private void maybeCleanupConnectionInfo(final String s) {
        final ConnectionInfo connectionInfo = this.connectionInfo.get(s);
        if (connectionInfo != null) {
            while (!connectionInfo.sockets.isEmpty()) {
                final IdleSocketHolder idleSocketHolder = connectionInfo.sockets.peekLast();
                final AsyncSocket socket = idleSocketHolder.socket;
                if (idleSocketHolder.idleTime + this.idleTimeoutMs > System.currentTimeMillis()) {
                    break;
                }
                connectionInfo.sockets.pop();
                socket.setClosedCallback(null);
                socket.close();
            }
            if (connectionInfo.openCount == 0 && connectionInfo.queue.isEmpty() && connectionInfo.sockets.isEmpty()) {
                this.connectionInfo.remove(s);
            }
        }
    }
    
    private void nextConnection(final AsyncHttpRequest asyncHttpRequest) {
        final Uri uri = asyncHttpRequest.getUri();
        final String computeLookup = this.computeLookup(uri, this.getSchemePort(uri), asyncHttpRequest.getProxyHost(), asyncHttpRequest.getProxyPort());
        synchronized (this) {
            final ConnectionInfo connectionInfo = this.connectionInfo.get(computeLookup);
            if (connectionInfo == null) {
                return;
            }
            --connectionInfo.openCount;
            while (connectionInfo.openCount < this.maxConnectionCount && connectionInfo.queue.size() > 0) {
                final GetSocketData getSocketData = connectionInfo.queue.remove();
                final SimpleCancellable simpleCancellable = (SimpleCancellable)getSocketData.socketCancellable;
                if (!simpleCancellable.isCancelled()) {
                    simpleCancellable.setParent(this.getSocket(getSocketData));
                }
            }
        }
        this.maybeCleanupConnectionInfo(computeLookup);
    }
    // monitorexit(this)
    
    private void recycleSocket(final AsyncSocket asyncSocket, final AsyncHttpRequest asyncHttpRequest) {
        if (asyncSocket != null) {
            final Uri uri = asyncHttpRequest.getUri();
            final String computeLookup = this.computeLookup(uri, this.getSchemePort(uri), asyncHttpRequest.getProxyHost(), asyncHttpRequest.getProxyPort());
            final IdleSocketHolder idleSocketHolder = new IdleSocketHolder(asyncSocket);
            synchronized (this) {
                final ArrayDeque<IdleSocketHolder> sockets = this.getOrCreateConnectionInfo(computeLookup).sockets;
                sockets.push(idleSocketHolder);
                // monitorexit(this)
                asyncSocket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(final Exception ex) {
                        synchronized (AsyncSocketMiddleware.this) {
                            sockets.remove(idleSocketHolder);
                            AsyncSocketMiddleware.this.maybeCleanupConnectionInfo(computeLookup);
                        }
                    }
                });
            }
        }
    }
    
    String computeLookup(final Uri uri, final int n, final String s, final int n2) {
        String s2;
        if (s != null) {
            s2 = s + ":" + n2;
        }
        else {
            s2 = "";
        }
        if (s != null) {
            s2 = s + ":" + n2;
        }
        return uri.getScheme() + "//" + uri.getHost() + ":" + n + "?proxy=" + s2;
    }
    
    public void disableProxy() {
        this.proxyPort = -1;
        this.proxyHost = null;
        this.proxyAddress = null;
    }
    
    public void enableProxy(final String proxyHost, final int proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyAddress = null;
    }
    
    public boolean getConnectAllAddresses() {
        return this.connectAllAddresses;
    }
    
    public int getMaxConnectionCount() {
        return this.maxConnectionCount;
    }
    
    public int getSchemePort(final Uri uri) {
        int n = -1;
        if (uri.getScheme() != null && uri.getScheme().equals(this.scheme)) {
            if (uri.getPort() == n) {
                n = this.port;
            }
            else {
                n = uri.getPort();
            }
        }
        return n;
    }
    
    @Override
    public Cancellable getSocket(final GetSocketData getSocketData) {
        final Uri uri = getSocketData.request.getUri();
        final int schemePort = this.getSchemePort(getSocketData.request.getUri());
        Cancellable connectSocket;
        if (schemePort == -1) {
            connectSocket = null;
        }
        else {
            getSocketData.state.put("socket-owner", this);
            final ConnectionInfo orCreateConnectionInfo = this.getOrCreateConnectionInfo(this.computeLookup(uri, schemePort, getSocketData.request.getProxyHost(), getSocketData.request.getProxyPort()));
            synchronized (this) {
                if (orCreateConnectionInfo.openCount >= this.maxConnectionCount) {
                    connectSocket = new SimpleCancellable();
                    orCreateConnectionInfo.queue.add(getSocketData);
                    return connectSocket;
                }
            }
            ++orCreateConnectionInfo.openCount;
            while (!orCreateConnectionInfo.sockets.isEmpty()) {
                final IdleSocketHolder idleSocketHolder = orCreateConnectionInfo.sockets.pop();
                final AsyncSocket socket = idleSocketHolder.socket;
                if (idleSocketHolder.idleTime + this.idleTimeoutMs < System.currentTimeMillis()) {
                    socket.setClosedCallback(null);
                    socket.close();
                }
                else {
                    if (socket.isOpen()) {
                        getSocketData.request.logd("Reusing keep-alive socket");
                        getSocketData.connectCallback.onConnectCompleted(null, socket);
                        final SimpleCancellable simpleCancellable = new SimpleCancellable();
                        simpleCancellable.setComplete();
                        // monitorexit(this)
                        connectSocket = simpleCancellable;
                        return connectSocket;
                    }
                    continue;
                }
            }
            // monitorexit(this)
            if (!this.connectAllAddresses || this.proxyHost != null || getSocketData.request.getProxyHost() != null) {
                getSocketData.request.logd("Connecting socket");
                if (getSocketData.request.getProxyHost() == null && this.proxyHost != null) {
                    getSocketData.request.enableProxy(this.proxyHost, this.proxyPort);
                }
                String s;
                int proxyPort;
                boolean b;
                if (getSocketData.request.getProxyHost() != null) {
                    s = getSocketData.request.getProxyHost();
                    proxyPort = getSocketData.request.getProxyPort();
                    b = true;
                }
                else {
                    s = uri.getHost();
                    proxyPort = schemePort;
                    b = false;
                }
                if (b) {
                    getSocketData.request.logv("Using proxy: " + s + ":" + proxyPort);
                }
                connectSocket = this.mClient.getServer().connectSocket(s, proxyPort, this.wrapCallback(getSocketData, uri, schemePort, b, getSocketData.connectCallback));
            }
            else {
                getSocketData.request.logv("Resolving domain and connecting to all available addresses");
                connectSocket = this.mClient.getServer().getAllByName(uri.getHost()).then(new TransformFuture<AsyncSocket, InetAddress[]>() {
                    Exception lastException;
                    
                    @Override
                    protected void error(final Exception ex) {
                        super.error(ex);
                        AsyncSocketMiddleware.this.wrapCallback(getSocketData, uri, schemePort, false, getSocketData.connectCallback).onConnectCompleted(ex, null);
                    }
                    
                    @Override
                    protected void transform(final InetAddress[] array) throws Exception {
                        final Continuation continuation = new Continuation(new CompletedCallback() {
                            @Override
                            public void onCompleted(final Exception ex) {
                                if (TransformFuture.this.lastException == null) {
                                    TransformFuture.this.lastException = new ConnectionFailedException("Unable to connect to remote address");
                                }
                                if (TransformFuture.this.setComplete(TransformFuture.this.lastException)) {
                                    AsyncSocketMiddleware.this.wrapCallback(getSocketData, uri, schemePort, false, getSocketData.connectCallback).onConnectCompleted(TransformFuture.this.lastException, null);
                                }
                            }
                        });
                        for (final InetAddress inetAddress : array) {
                            continuation.add(new ContinuationCallback() {
                                final /* synthetic */ String val$inetSockAddress = String.format(Locale.ENGLISH, "%s:%s", inetAddress, schemePort);
                                
                                @Override
                                public void onContinue(final Continuation continuation, final CompletedCallback completedCallback) throws Exception {
                                    getSocketData.request.logv("attempting connection to " + this.val$inetSockAddress);
                                    AsyncSocketMiddleware.this.mClient.getServer().connectSocket(new InetSocketAddress(inetAddress, schemePort), AsyncSocketMiddleware.this.wrapCallback(getSocketData, uri, schemePort, false, new ConnectCallback() {
                                        @Override
                                        public void onConnectCompleted(final Exception lastException, final AsyncSocket asyncSocket) {
                                            if (TransformFuture.this.isDone()) {
                                                TransformFuture.this.lastException = new Exception("internal error during connect to " + ContinuationCallback.this.val$inetSockAddress);
                                                completedCallback.onCompleted(null);
                                            }
                                            else if (lastException != null) {
                                                TransformFuture.this.lastException = lastException;
                                                completedCallback.onCompleted(null);
                                            }
                                            else if (TransformFuture.this.isDone() || TransformFuture.this.isCancelled()) {
                                                getSocketData.request.logd("Recycling extra socket leftover from cancelled operation");
                                                AsyncSocketMiddleware.this.idleSocket(asyncSocket);
                                                AsyncSocketMiddleware.this.recycleSocket(asyncSocket, getSocketData.request);
                                            }
                                            else if (TransformFuture.this.setComplete(null, (T)asyncSocket)) {
                                                getSocketData.connectCallback.onConnectCompleted(null, asyncSocket);
                                            }
                                        }
                                    }));
                                }
                            });
                        }
                        continuation.start();
                    }
                });
            }
        }
        return connectSocket;
    }
    
    @Override
    public void onResponseComplete(final OnResponseCompleteDataOnRequestSentData onResponseCompleteDataOnRequestSentData) {
        if (onResponseCompleteDataOnRequestSentData.state.get("socket-owner") == this) {
            try {
                this.idleSocket(onResponseCompleteDataOnRequestSentData.socket);
                if (onResponseCompleteDataOnRequestSentData.exception != null || !onResponseCompleteDataOnRequestSentData.socket.isOpen()) {
                    onResponseCompleteDataOnRequestSentData.request.logv("closing out socket (exception)");
                    onResponseCompleteDataOnRequestSentData.socket.setClosedCallback(null);
                    onResponseCompleteDataOnRequestSentData.socket.close();
                }
                else if (!HttpUtil.isKeepAlive(onResponseCompleteDataOnRequestSentData.response.protocol(), onResponseCompleteDataOnRequestSentData.response.headers()) || !HttpUtil.isKeepAlive(Protocol.HTTP_1_1, onResponseCompleteDataOnRequestSentData.request.getHeaders())) {
                    onResponseCompleteDataOnRequestSentData.request.logv("closing out socket (not keep alive)");
                    onResponseCompleteDataOnRequestSentData.socket.setClosedCallback(null);
                    onResponseCompleteDataOnRequestSentData.socket.close();
                }
                else {
                    onResponseCompleteDataOnRequestSentData.request.logd("Recycling keep-alive socket");
                    this.recycleSocket(onResponseCompleteDataOnRequestSentData.socket, onResponseCompleteDataOnRequestSentData.request);
                }
            }
            finally {
                this.nextConnection(onResponseCompleteDataOnRequestSentData.request);
            }
        }
    }
    
    public void setConnectAllAddresses(final boolean connectAllAddresses) {
        this.connectAllAddresses = connectAllAddresses;
    }
    
    public void setIdleTimeoutMs(final int idleTimeoutMs) {
        this.idleTimeoutMs = idleTimeoutMs;
    }
    
    public void setMaxConnectionCount(final int maxConnectionCount) {
        this.maxConnectionCount = maxConnectionCount;
    }
    
    protected ConnectCallback wrapCallback(final GetSocketData getSocketData, final Uri uri, final int n, final boolean b, final ConnectCallback connectCallback) {
        return connectCallback;
    }
    
    static class ConnectionInfo
    {
        int openCount;
        ArrayDeque<GetSocketData> queue;
        ArrayDeque<IdleSocketHolder> sockets;
        
        ConnectionInfo() {
            this.queue = new ArrayDeque<GetSocketData>();
            this.sockets = new ArrayDeque<IdleSocketHolder>();
        }
    }
    
    class IdleSocketHolder
    {
        long idleTime;
        AsyncSocket socket;
        
        public IdleSocketHolder(final AsyncSocket socket) {
            this.idleTime = System.currentTimeMillis();
            this.socket = socket;
        }
    }
}
