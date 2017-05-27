// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import com.koushikdutta.async.future.SimpleFuture;
import java.util.HashMap;
import java.util.LinkedHashMap;
import com.koushikdutta.async.future.MultiFuture;
import javax.net.ssl.SSLContext;
import com.koushikdutta.async.future.SimpleCancellable;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.http.HttpUtil;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.future.TransformFuture;
import com.koushikdutta.async.DataSink;
import java.io.IOException;
import com.koushikdutta.async.AsyncSSLSocketWrapper;
import android.text.TextUtils;
import android.net.Uri;
import java.util.Iterator;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.http.AsyncHttpRequest;
import java.util.List;
import java.util.ArrayList;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.util.Charsets;
import java.nio.ByteBuffer;
import com.koushikdutta.async.http.Protocol;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.AsyncSSLSocket;
import com.koushikdutta.async.callback.ConnectCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import javax.net.ssl.SSLEngine;
import com.koushikdutta.async.http.AsyncSSLEngineConfigurator;
import com.koushikdutta.async.http.AsyncHttpClient;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.lang.reflect.Field;
import com.koushikdutta.async.http.AsyncSSLSocketMiddleware;

public class SpdyMiddleware extends AsyncSSLSocketMiddleware
{
    private static final NoSpdyException NO_SPDY;
    Field alpnProtocols;
    Hashtable<String, SpdyConnectionWaiter> connections;
    boolean initialized;
    Method nativeGetAlpnNegotiatedProtocol;
    Method nativeGetNpnNegotiatedProtocol;
    Field npnProtocols;
    Field peerHost;
    Field peerPort;
    boolean spdyEnabled;
    Field sslNativePointer;
    Field sslParameters;
    Field useSni;
    
    static {
        NO_SPDY = new NoSpdyException();
    }
    
    public SpdyMiddleware(final AsyncHttpClient asyncHttpClient) {
        super(asyncHttpClient);
        this.connections = new Hashtable<String, SpdyConnectionWaiter>();
        this.addEngineConfigurator(new AsyncSSLEngineConfigurator() {
            @Override
            public void configureEngine(final SSLEngine sslEngine, final GetSocketData getSocketData, final String s, final int n) {
                SpdyMiddleware.this.configure(sslEngine, getSocketData, s, n);
            }
        });
    }
    
    private boolean canSpdyRequest(final GetSocketData getSocketData) {
        return getSocketData.request.getBody() == null;
    }
    
    static byte[] concatLengthPrefixed(final Protocol... array) {
        final ByteBuffer allocate = ByteBuffer.allocate(8192);
        for (final Protocol protocol : array) {
            if (protocol != Protocol.HTTP_1_0) {
                allocate.put((byte)protocol.toString().length());
                allocate.put(protocol.toString().getBytes(Charsets.UTF_8));
            }
        }
        allocate.flip();
        return new ByteBufferList(new ByteBuffer[] { allocate }).getAllByteArray();
    }
    
    private void configure(final SSLEngine sslEngine, final GetSocketData getSocketData, final String s, final int n) {
    Label_0318:
        while (true) {
            if (this.initialized || !this.spdyEnabled) {
                break Label_0318;
            }
            while (true) {
                this.initialized = true;
                try {
                    this.peerHost = sslEngine.getClass().getSuperclass().getDeclaredField("peerHost");
                    this.peerPort = sslEngine.getClass().getSuperclass().getDeclaredField("peerPort");
                    this.sslParameters = sslEngine.getClass().getDeclaredField("sslParameters");
                    this.npnProtocols = this.sslParameters.getType().getDeclaredField("npnProtocols");
                    this.alpnProtocols = this.sslParameters.getType().getDeclaredField("alpnProtocols");
                    this.useSni = this.sslParameters.getType().getDeclaredField("useSni");
                    this.sslNativePointer = sslEngine.getClass().getDeclaredField("sslNativePointer");
                    final String string = this.sslParameters.getType().getPackage().getName() + ".NativeCrypto";
                    this.nativeGetNpnNegotiatedProtocol = Class.forName(string, true, this.sslParameters.getType().getClassLoader()).getDeclaredMethod("SSL_get_npn_negotiated_protocol", Long.TYPE);
                    this.nativeGetAlpnNegotiatedProtocol = Class.forName(string, true, this.sslParameters.getType().getClassLoader()).getDeclaredMethod("SSL_get0_alpn_selected", Long.TYPE);
                    this.peerHost.setAccessible(true);
                    this.peerPort.setAccessible(true);
                    this.sslParameters.setAccessible(true);
                    this.npnProtocols.setAccessible(true);
                    this.alpnProtocols.setAccessible(true);
                    this.useSni.setAccessible(true);
                    this.sslNativePointer.setAccessible(true);
                    this.nativeGetNpnNegotiatedProtocol.setAccessible(true);
                    this.nativeGetAlpnNegotiatedProtocol.setAccessible(true);
                    if (!this.canSpdyRequest(getSocketData)) {
                        return;
                    }
                }
                catch (Exception ex2) {
                    this.sslParameters = null;
                    this.npnProtocols = null;
                    this.alpnProtocols = null;
                    this.useSni = null;
                    this.sslNativePointer = null;
                    this.nativeGetNpnNegotiatedProtocol = null;
                    this.nativeGetAlpnNegotiatedProtocol = null;
                    continue Label_0318;
                }
                if (this.sslParameters != null) {
                    try {
                        final byte[] concatLengthPrefixed = concatLengthPrefixed(Protocol.SPDY_3);
                        this.peerHost.set(sslEngine, s);
                        this.peerPort.set(sslEngine, n);
                        final Object value = this.sslParameters.get(sslEngine);
                        this.alpnProtocols.set(value, concatLengthPrefixed);
                        this.useSni.set(value, true);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            break;
        }
    }
    
    private void invokeConnect(final String s, final ConnectCallback connectCallback, final Exception ex, final AsyncSSLSocket asyncSSLSocket) {
        final SpdyConnectionWaiter spdyConnectionWaiter = this.connections.get(s);
        if (spdyConnectionWaiter == null || spdyConnectionWaiter.originalCancellable.setComplete()) {
            connectCallback.onConnectCompleted(ex, asyncSSLSocket);
        }
    }
    
    private void newSocket(final GetSocketData getSocketData, final AsyncSpdyConnection asyncSpdyConnection, final ConnectCallback connectCallback) {
        final AsyncHttpRequest request = getSocketData.request;
        getSocketData.protocol = asyncSpdyConnection.protocol.toString();
        final AsyncHttpRequestBody body = getSocketData.request.getBody();
        final ArrayList<Header> list = new ArrayList<Header>();
        list.add(new Header(Header.TARGET_METHOD, request.getMethod()));
        list.add(new Header(Header.TARGET_PATH, requestPath(request.getUri())));
        final String value = request.getHeaders().get("Host");
        if (Protocol.SPDY_3 == asyncSpdyConnection.protocol) {
            list.add(new Header(Header.VERSION, "HTTP/1.1"));
            list.add(new Header(Header.TARGET_HOST, value));
        }
        else {
            if (Protocol.HTTP_2 != asyncSpdyConnection.protocol) {
                throw new AssertionError();
            }
            list.add(new Header(Header.TARGET_AUTHORITY, value));
        }
        list.add(new Header(Header.TARGET_SCHEME, request.getUri().getScheme()));
        final Multimap multiMap = request.getHeaders().getMultiMap();
        for (final String s : ((HashMap<String, Object>)multiMap).keySet()) {
            if (!SpdyTransport.isProhibitedHeader(asyncSpdyConnection.protocol, s)) {
                final Iterator<String> iterator2 = ((LinkedHashMap<Object, List<String>>)multiMap).get(s).iterator();
                while (iterator2.hasNext()) {
                    list.add(new Header(s.toLowerCase(), iterator2.next()));
                }
            }
        }
        request.logv("\n" + request);
        connectCallback.onConnectCompleted(null, asyncSpdyConnection.newStream(list, body != null, true));
    }
    
    private void noSpdy(final String s) {
        final SpdyConnectionWaiter spdyConnectionWaiter = this.connections.remove(s);
        if (spdyConnectionWaiter != null) {
            spdyConnectionWaiter.setComplete(SpdyMiddleware.NO_SPDY);
        }
    }
    
    private static String requestPath(final Uri uri) {
        String s = uri.getEncodedPath();
        if (s == null) {
            s = "/";
        }
        else if (!s.startsWith("/")) {
            s = "/" + s;
        }
        if (!TextUtils.isEmpty((CharSequence)uri.getEncodedQuery())) {
            s = s + "?" + uri.getEncodedQuery();
        }
        return s;
    }
    
    @Override
    protected AsyncSSLSocketWrapper.HandshakeCallback createHandshakeCallback(final GetSocketData getSocketData, final ConnectCallback connectCallback) {
        final String s = getSocketData.state.get("spdykey");
        AsyncSSLSocketWrapper.HandshakeCallback handshakeCallback;
        if (s == null) {
            handshakeCallback = super.createHandshakeCallback(getSocketData, connectCallback);
        }
        else {
            handshakeCallback = new AsyncSSLSocketWrapper.HandshakeCallback() {
                @Override
                public void onHandshakeCompleted(final Exception ex, final AsyncSSLSocket asyncSSLSocket) {
                    getSocketData.request.logv("checking spdy handshake");
                    if (ex != null || SpdyMiddleware.this.nativeGetAlpnNegotiatedProtocol == null) {
                        SpdyMiddleware.this.invokeConnect(s, connectCallback, ex, asyncSSLSocket);
                        SpdyMiddleware.this.noSpdy(s);
                    }
                    else {
                        byte[] array;
                        try {
                            array = (byte[])SpdyMiddleware.this.nativeGetAlpnNegotiatedProtocol.invoke(null, (long)SpdyMiddleware.this.sslNativePointer.get(asyncSSLSocket.getSSLEngine()));
                            if (array == null) {
                                SpdyMiddleware.this.invokeConnect(s, connectCallback, null, asyncSSLSocket);
                                SpdyMiddleware.this.noSpdy(s);
                                return;
                            }
                        }
                        catch (Exception ex2) {
                            throw new AssertionError((Object)ex2);
                        }
                        final String s = new String(array);
                        final Protocol value = Protocol.get(s);
                        if (value == null || !value.needsSpdyConnection()) {
                            SpdyMiddleware.this.invokeConnect(s, connectCallback, null, asyncSSLSocket);
                            SpdyMiddleware.this.noSpdy(s);
                        }
                        else {
                            final AsyncSpdyConnection asyncSpdyConnection = new AsyncSpdyConnection(asyncSSLSocket, Protocol.get(s)) {
                                boolean hasReceivedSettings;
                                
                                @Override
                                public void settings(final boolean b, final Settings settings) {
                                    super.settings(b, settings);
                                    if (!this.hasReceivedSettings) {
                                        this.hasReceivedSettings = true;
                                        final SpdyConnectionWaiter spdyConnectionWaiter = SpdyMiddleware.this.connections.get(s);
                                        if (spdyConnectionWaiter.originalCancellable.setComplete()) {
                                            getSocketData.request.logv("using new spdy connection for host: " + getSocketData.request.getUri().getHost());
                                            SpdyMiddleware.this.newSocket(getSocketData, this, connectCallback);
                                        }
                                        ((SimpleFuture<SpdyMiddleware$2$1>)spdyConnectionWaiter).setComplete(this);
                                    }
                                }
                            };
                            try {
                                asyncSpdyConnection.sendConnectionPreface();
                            }
                            catch (IOException ex3) {
                                ex3.printStackTrace();
                            }
                        }
                    }
                }
            };
        }
        return handshakeCallback;
    }
    
    @Override
    public boolean exchangeHeaders(final OnExchangeHeaderData onExchangeHeaderData) {
        boolean exchangeHeaders;
        if (!(onExchangeHeaderData.socket instanceof AsyncSpdyConnection.SpdySocket)) {
            exchangeHeaders = super.exchangeHeaders(onExchangeHeaderData);
        }
        else {
            if (onExchangeHeaderData.request.getBody() != null) {
                onExchangeHeaderData.response.sink(onExchangeHeaderData.socket);
            }
            onExchangeHeaderData.sendHeadersCallback.onCompleted(null);
            final AsyncSpdyConnection.SpdySocket spdySocket = (AsyncSpdyConnection.SpdySocket)onExchangeHeaderData.socket;
            spdySocket.headers().then(new TransformFuture<Headers, List<Header>>() {
                @Override
                protected void transform(final List<Header> list) throws Exception {
                    final Headers complete = new Headers();
                    for (final Header header : list) {
                        complete.add(header.name.utf8(), header.value.utf8());
                    }
                    final String[] split = complete.remove(Header.RESPONSE_STATUS.utf8()).split(" ", 2);
                    onExchangeHeaderData.response.code(Integer.parseInt(split[0]));
                    if (split.length == 2) {
                        onExchangeHeaderData.response.message(split[1]);
                    }
                    onExchangeHeaderData.response.protocol(complete.remove(Header.VERSION.utf8()));
                    onExchangeHeaderData.response.headers(complete);
                    this.setComplete((T)complete);
                }
            }).setCallback((FutureCallback<T>)new FutureCallback<Headers>() {
                @Override
                public void onCompleted(final Exception ex, final Headers headers) {
                    onExchangeHeaderData.receiveHeadersCallback.onCompleted(ex);
                    onExchangeHeaderData.response.emitter(HttpUtil.getBodyDecoder(spdySocket, spdySocket.getConnection().protocol, headers, false));
                }
            });
            exchangeHeaders = true;
        }
        return exchangeHeaders;
    }
    
    @Override
    public Cancellable getSocket(final GetSocketData getSocketData) {
        final Uri uri = getSocketData.request.getUri();
        final int schemePort = this.getSchemePort(getSocketData.request.getUri());
        Cancellable cancellable;
        if (schemePort == -1) {
            cancellable = null;
        }
        else if (!this.spdyEnabled) {
            cancellable = super.getSocket(getSocketData);
        }
        else if (!this.canSpdyRequest(getSocketData)) {
            cancellable = super.getSocket(getSocketData);
        }
        else {
            final String string = uri.getHost() + schemePort;
            SpdyConnectionWaiter spdyConnectionWaiter = this.connections.get(string);
            if (spdyConnectionWaiter != null) {
                if (spdyConnectionWaiter.tryGetException() instanceof NoSpdyException) {
                    cancellable = super.getSocket(getSocketData);
                    return cancellable;
                }
                if (spdyConnectionWaiter.tryGet() != null && !spdyConnectionWaiter.tryGet().socket.isOpen()) {
                    this.connections.remove(string);
                    spdyConnectionWaiter = null;
                }
            }
            if (spdyConnectionWaiter == null) {
                getSocketData.state.put("spdykey", string);
                cancellable = super.getSocket(getSocketData);
                if (!cancellable.isDone() && !cancellable.isCancelled()) {
                    final SpdyConnectionWaiter spdyConnectionWaiter2 = new SpdyConnectionWaiter();
                    this.connections.put(string, spdyConnectionWaiter2);
                    cancellable = spdyConnectionWaiter2.originalCancellable;
                }
            }
            else {
                getSocketData.request.logv("waiting for potential spdy connection for host: " + getSocketData.request.getUri().getHost());
                cancellable = new SimpleCancellable();
                spdyConnectionWaiter.setCallback(new FutureCallback<AsyncSpdyConnection>() {
                    @Override
                    public void onCompleted(final Exception ex, final AsyncSpdyConnection asyncSpdyConnection) {
                        if (ex instanceof NoSpdyException) {
                            getSocketData.request.logv("spdy not available");
                            ((SimpleCancellable)cancellable).setParent(SpdyMiddleware.this.getSocket(getSocketData));
                        }
                        else if (ex != null) {
                            if (((SimpleCancellable)cancellable).setComplete()) {
                                getSocketData.connectCallback.onConnectCompleted(ex, null);
                            }
                        }
                        else {
                            getSocketData.request.logv("using existing spdy connection for host: " + getSocketData.request.getUri().getHost());
                            if (((SimpleCancellable)cancellable).setComplete()) {
                                SpdyMiddleware.this.newSocket(getSocketData, asyncSpdyConnection, getSocketData.connectCallback);
                            }
                        }
                    }
                });
            }
        }
        return cancellable;
    }
    
    public boolean getSpdyEnabled() {
        return this.spdyEnabled;
    }
    
    @Override
    public void onRequestSent(final OnRequestSentData onRequestSentData) {
        if (onRequestSentData.socket instanceof AsyncSpdyConnection.SpdySocket && onRequestSentData.request.getBody() != null) {
            onRequestSentData.response.sink().end();
        }
    }
    
    @Override
    public void setSSLContext(final SSLContext sslContext) {
        super.setSSLContext(sslContext);
        this.initialized = false;
    }
    
    public void setSpdyEnabled(final boolean spdyEnabled) {
        this.spdyEnabled = spdyEnabled;
    }
    
    @Override
    protected ConnectCallback wrapCallback(final GetSocketData getSocketData, final Uri uri, final int n, final boolean b, final ConnectCallback connectCallback) {
        ConnectCallback wrapCallback = super.wrapCallback(getSocketData, uri, n, b, connectCallback);
        final String s = getSocketData.state.get("spdykey");
        if (s != null) {
            wrapCallback = new ConnectCallback() {
                @Override
                public void onConnectCompleted(final Exception complete, final AsyncSocket asyncSocket) {
                    if (complete != null) {
                        final SpdyConnectionWaiter spdyConnectionWaiter = SpdyMiddleware.this.connections.remove(s);
                        if (spdyConnectionWaiter != null) {
                            spdyConnectionWaiter.setComplete(complete);
                        }
                    }
                    wrapCallback.onConnectCompleted(complete, asyncSocket);
                }
            };
        }
        return wrapCallback;
    }
    
    private static class NoSpdyException extends Exception
    {
    }
    
    private static class SpdyConnectionWaiter extends MultiFuture<AsyncSpdyConnection>
    {
        SimpleCancellable originalCancellable;
        
        private SpdyConnectionWaiter() {
            this.originalCancellable = new SimpleCancellable();
        }
    }
}
