// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import java.util.Collection;
import com.koushikdutta.async.parser.StringParser;
import com.koushikdutta.async.parser.JSONObjectParser;
import org.json.JSONObject;
import com.koushikdutta.async.parser.JSONArrayParser;
import org.json.JSONArray;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.koushikdutta.async.stream.OutputStreamDataCallback;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import com.koushikdutta.async.parser.ByteBufferListParser;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.parser.AsyncParser;
import com.koushikdutta.async.future.Future;
import android.annotation.SuppressLint;
import java.net.InetAddress;
import android.os.Build$VERSION;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.ProxySelector;
import java.net.URL;
import android.net.Uri;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.AsyncSSLException;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.future.Cancellable;
import java.util.Iterator;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.callback.ConnectCallback;
import java.util.concurrent.TimeoutException;
import android.text.TextUtils;
import com.koushikdutta.async.future.SimpleFuture;
import com.koushikdutta.async.http.callback.RequestCallback;
import com.koushikdutta.async.http.callback.HttpConnectCallback;
import java.util.concurrent.CopyOnWriteArrayList;
import com.koushikdutta.async.http.spdy.SpdyMiddleware;
import com.koushikdutta.async.AsyncServer;
import java.util.List;

public class AsyncHttpClient
{
    private static final String LOGTAG = "AsyncHttp";
    private static AsyncHttpClient mDefaultInstance;
    HttpTransportMiddleware httpTransportMiddleware;
    final List<AsyncHttpClientMiddleware> mMiddleware;
    AsyncServer mServer;
    AsyncSocketMiddleware socketMiddleware;
    SpdyMiddleware sslSocketMiddleware;
    
    public AsyncHttpClient(final AsyncServer mServer) {
        this.mMiddleware = new CopyOnWriteArrayList<AsyncHttpClientMiddleware>();
        this.mServer = mServer;
        this.insertMiddleware(this.socketMiddleware = new AsyncSocketMiddleware(this));
        this.insertMiddleware(this.sslSocketMiddleware = new SpdyMiddleware(this));
        this.insertMiddleware(this.httpTransportMiddleware = new HttpTransportMiddleware());
        this.sslSocketMiddleware.addEngineConfigurator(new SSLEngineSNIConfigurator());
    }
    
    private static void copyHeader(final AsyncHttpRequest asyncHttpRequest, final AsyncHttpRequest asyncHttpRequest2, final String s) {
        final String value = asyncHttpRequest.getHeaders().get(s);
        if (!TextUtils.isEmpty((CharSequence)value)) {
            asyncHttpRequest2.getHeaders().set(s, value);
        }
    }
    
    private void execute(final AsyncHttpRequest asyncHttpRequest, final int n, final FutureAsyncHttpResponse futureAsyncHttpResponse, final HttpConnectCallback httpConnectCallback) {
        if (this.mServer.isAffinityThread()) {
            this.executeAffinity(asyncHttpRequest, n, futureAsyncHttpResponse, httpConnectCallback);
        }
        else {
            this.mServer.post(new Runnable() {
                @Override
                public void run() {
                    AsyncHttpClient.this.executeAffinity(asyncHttpRequest, n, futureAsyncHttpResponse, httpConnectCallback);
                }
            });
        }
    }
    
    private void executeAffinity(final AsyncHttpRequest request, final int n, final FutureAsyncHttpResponse futureAsyncHttpResponse, final HttpConnectCallback httpConnectCallback) {
        assert this.mServer.isAffinityThread();
        if (n > 15) {
            this.reportConnectedCompleted(futureAsyncHttpResponse, new RedirectLimitExceededException("too many redirects"), null, request, httpConnectCallback);
        }
        else {
            request.getUri();
            final AsyncHttpClientMiddleware.OnResponseCompleteDataOnRequestSentData onResponseCompleteDataOnRequestSentData = new AsyncHttpClientMiddleware.OnResponseCompleteDataOnRequestSentData();
            request.executionTime = System.currentTimeMillis();
            (onResponseCompleteDataOnRequestSentData.request = request).logd("Executing request.");
            final Iterator<AsyncHttpClientMiddleware> iterator = this.mMiddleware.iterator();
            while (iterator.hasNext()) {
                iterator.next().onRequest((AsyncHttpClientMiddleware.OnRequestData)onResponseCompleteDataOnRequestSentData);
            }
            if (request.getTimeout() > 0) {
                futureAsyncHttpResponse.timeoutRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (onResponseCompleteDataOnRequestSentData.socketCancellable != null) {
                            onResponseCompleteDataOnRequestSentData.socketCancellable.cancel();
                            if (onResponseCompleteDataOnRequestSentData.socket != null) {
                                onResponseCompleteDataOnRequestSentData.socket.close();
                            }
                        }
                        AsyncHttpClient.this.reportConnectedCompleted(futureAsyncHttpResponse, new TimeoutException(), null, request, httpConnectCallback);
                    }
                };
                futureAsyncHttpResponse.scheduled = this.mServer.postDelayed(futureAsyncHttpResponse.timeoutRunnable, getTimeoutRemaining(request));
            }
            onResponseCompleteDataOnRequestSentData.connectCallback = new ConnectCallback() {
                boolean reported;
                
                @Override
                public void onConnectCompleted(final Exception ex, final AsyncSocket asyncSocket) {
                    if (this.reported && asyncSocket != null) {
                        asyncSocket.setDataCallback(new DataCallback.NullDataCallback());
                        asyncSocket.setEndCallback(new CompletedCallback.NullCompletedCallback());
                        asyncSocket.close();
                        throw new AssertionError((Object)"double connect callback");
                    }
                    this.reported = true;
                    request.logv("socket connected");
                    if (futureAsyncHttpResponse.isCancelled()) {
                        if (asyncSocket != null) {
                            asyncSocket.close();
                        }
                    }
                    else {
                        if (futureAsyncHttpResponse.timeoutRunnable != null) {
                            AsyncHttpClient.this.mServer.removeAllCallbacks(futureAsyncHttpResponse.scheduled);
                        }
                        if (ex != null) {
                            AsyncHttpClient.this.reportConnectedCompleted(futureAsyncHttpResponse, ex, null, request, httpConnectCallback);
                        }
                        else {
                            onResponseCompleteDataOnRequestSentData.socket = asyncSocket;
                            futureAsyncHttpResponse.socket = asyncSocket;
                            AsyncHttpClient.this.executeSocket(request, n, futureAsyncHttpResponse, httpConnectCallback, onResponseCompleteDataOnRequestSentData);
                        }
                    }
                }
            };
            setupAndroidProxy(request);
            if (request.getBody() != null && request.getHeaders().get("Content-Type") == null) {
                request.getHeaders().set("Content-Type", request.getBody().getContentType());
            }
            final Iterator<AsyncHttpClientMiddleware> iterator2 = this.mMiddleware.iterator();
            while (iterator2.hasNext()) {
                final Cancellable socket = iterator2.next().getSocket((AsyncHttpClientMiddleware.GetSocketData)onResponseCompleteDataOnRequestSentData);
                if (socket != null) {
                    futureAsyncHttpResponse.setParent(onResponseCompleteDataOnRequestSentData.socketCancellable = socket);
                    return;
                }
            }
            this.reportConnectedCompleted(futureAsyncHttpResponse, new IllegalArgumentException("invalid uri=" + request.getUri() + " middlewares=" + this.mMiddleware), null, request, httpConnectCallback);
        }
    }
    
    private void executeSocket(final AsyncHttpRequest asyncHttpRequest, final int n, final FutureAsyncHttpResponse futureAsyncHttpResponse, final HttpConnectCallback httpConnectCallback, final AsyncHttpClientMiddleware.OnResponseCompleteDataOnRequestSentData onResponseCompleteDataOnRequestSentData) {
        final AsyncHttpResponseImpl response = new AsyncHttpResponseImpl(asyncHttpRequest) {
            @Override
            public AsyncSocket detachSocket() {
                asyncHttpRequest.logd("Detaching socket");
                AsyncSocket socket = this.socket();
                if (socket == null) {
                    socket = null;
                }
                else {
                    socket.setWriteableCallback(null);
                    socket.setClosedCallback(null);
                    socket.setEndCallback(null);
                    socket.setDataCallback(null);
                    this.setSocket(null);
                }
                return socket;
            }
            
            @Override
            protected void onHeadersReceived() {
                super.onHeadersReceived();
                if (!futureAsyncHttpResponse.isCancelled()) {
                    if (futureAsyncHttpResponse.timeoutRunnable != null) {
                        AsyncHttpClient.this.mServer.removeAllCallbacks(futureAsyncHttpResponse.scheduled);
                    }
                    asyncHttpRequest.logv("Received headers:\n" + this.toString());
                    final Iterator<AsyncHttpClientMiddleware> iterator = AsyncHttpClient.this.mMiddleware.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onHeadersReceived((AsyncHttpClientMiddleware.OnHeadersReceivedDataOnRequestSentData)onResponseCompleteDataOnRequestSentData);
                    }
                }
            }
            
            @Override
            protected void onRequestCompleted(final Exception ex) {
                if (ex != null) {
                    AsyncHttpClient.this.reportConnectedCompleted(futureAsyncHttpResponse, ex, null, asyncHttpRequest, httpConnectCallback);
                }
                else {
                    asyncHttpRequest.logv("request completed");
                    if (!futureAsyncHttpResponse.isCancelled()) {
                        if (futureAsyncHttpResponse.timeoutRunnable != null && this.mHeaders == null) {
                            AsyncHttpClient.this.mServer.removeAllCallbacks(futureAsyncHttpResponse.scheduled);
                            futureAsyncHttpResponse.scheduled = AsyncHttpClient.this.mServer.postDelayed(futureAsyncHttpResponse.timeoutRunnable, getTimeoutRemaining(asyncHttpRequest));
                        }
                        final Iterator<AsyncHttpClientMiddleware> iterator = AsyncHttpClient.this.mMiddleware.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().onRequestSent((AsyncHttpClientMiddleware.OnRequestSentData)onResponseCompleteDataOnRequestSentData);
                        }
                    }
                }
            }
            
            @Override
            protected void report(final Exception exception) {
                if (exception != null) {
                    asyncHttpRequest.loge("exception during response", exception);
                }
                if (!futureAsyncHttpResponse.isCancelled()) {
                    if (exception instanceof AsyncSSLException) {
                        asyncHttpRequest.loge("SSL Exception", exception);
                        final AsyncSSLException ex = (AsyncSSLException)exception;
                        asyncHttpRequest.onHandshakeException(ex);
                        if (ex.getIgnore()) {
                            return;
                        }
                    }
                    final AsyncSocket socket = this.socket();
                    if (socket != null) {
                        super.report(exception);
                        if ((!socket.isOpen() || exception != null) && this.headers() == null && exception != null) {
                            AsyncHttpClient.this.reportConnectedCompleted(futureAsyncHttpResponse, exception, null, asyncHttpRequest, httpConnectCallback);
                        }
                        onResponseCompleteDataOnRequestSentData.exception = exception;
                        final Iterator<AsyncHttpClientMiddleware> iterator = AsyncHttpClient.this.mMiddleware.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().onResponseComplete(onResponseCompleteDataOnRequestSentData);
                        }
                    }
                }
            }
            
            @Override
            public void setDataEmitter(final DataEmitter bodyEmitter) {
                onResponseCompleteDataOnRequestSentData.bodyEmitter = bodyEmitter;
                final Iterator<AsyncHttpClientMiddleware> iterator = AsyncHttpClient.this.mMiddleware.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onBodyDecoder((AsyncHttpClientMiddleware.OnBodyDataOnRequestSentData)onResponseCompleteDataOnRequestSentData);
                }
                super.setDataEmitter(onResponseCompleteDataOnRequestSentData.bodyEmitter);
                final Headers mHeaders = this.mHeaders;
                final int code = this.code();
                if ((code != 301 && code != 302 && code != 307) || !asyncHttpRequest.getFollowRedirect()) {
                    asyncHttpRequest.logv("Final (post cache response) headers:\n" + this.toString());
                    AsyncHttpClient.this.reportConnectedCompleted(futureAsyncHttpResponse, null, this, asyncHttpRequest, httpConnectCallback);
                    return;
                }
                while (true) {
                    final String value = mHeaders.get("Location");
                    while (true) {
                        try {
                            Uri uri = Uri.parse(value);
                            if (uri.getScheme() == null) {
                                uri = Uri.parse(new URL(new URL(asyncHttpRequest.getUri().toString()), value).toString());
                            }
                            if (asyncHttpRequest.getMethod().equals("HEAD")) {
                                final String s = "HEAD";
                                final AsyncHttpRequest asyncHttpRequest = new AsyncHttpRequest(uri, s);
                                asyncHttpRequest.executionTime = asyncHttpRequest.executionTime;
                                asyncHttpRequest.logLevel = asyncHttpRequest.logLevel;
                                asyncHttpRequest.LOGTAG = asyncHttpRequest.LOGTAG;
                                asyncHttpRequest.proxyHost = asyncHttpRequest.proxyHost;
                                asyncHttpRequest.proxyPort = asyncHttpRequest.proxyPort;
                                setupAndroidProxy(asyncHttpRequest);
                                copyHeader(asyncHttpRequest, asyncHttpRequest, "User-Agent");
                                copyHeader(asyncHttpRequest, asyncHttpRequest, "Range");
                                asyncHttpRequest.logi("Redirecting");
                                asyncHttpRequest.logi("Redirected");
                                AsyncHttpClient.this.execute(asyncHttpRequest, 1 + n, futureAsyncHttpResponse, httpConnectCallback);
                                this.setDataCallback(new DataCallback.NullDataCallback());
                                return;
                            }
                        }
                        catch (Exception ex) {
                            AsyncHttpClient.this.reportConnectedCompleted(futureAsyncHttpResponse, ex, this, asyncHttpRequest, httpConnectCallback);
                            return;
                        }
                        final String s = "GET";
                        continue;
                    }
                }
            }
        };
        onResponseCompleteDataOnRequestSentData.sendHeadersCallback = new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                if (ex != null) {
                    response.report(ex);
                }
                else {
                    response.onHeadersSent();
                }
            }
        };
        onResponseCompleteDataOnRequestSentData.receiveHeadersCallback = new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                if (ex != null) {
                    response.report(ex);
                }
                else {
                    response.onHeadersReceived();
                }
            }
        };
        ((AsyncHttpResponseImpl)(onResponseCompleteDataOnRequestSentData.response = response)).setSocket(onResponseCompleteDataOnRequestSentData.socket);
        final Iterator<AsyncHttpClientMiddleware> iterator = this.mMiddleware.iterator();
        while (iterator.hasNext() && !iterator.next().exchangeHeaders((AsyncHttpClientMiddleware.OnExchangeHeaderData)onResponseCompleteDataOnRequestSentData)) {}
    }
    
    public static AsyncHttpClient getDefaultInstance() {
        if (AsyncHttpClient.mDefaultInstance == null) {
            AsyncHttpClient.mDefaultInstance = new AsyncHttpClient(AsyncServer.getDefault());
        }
        return AsyncHttpClient.mDefaultInstance;
    }
    
    private static long getTimeoutRemaining(final AsyncHttpRequest asyncHttpRequest) {
        return asyncHttpRequest.getTimeout();
    }
    
    private <T> void invoke(final RequestCallback<T> requestCallback, final SimpleFuture<T> simpleFuture, final AsyncHttpResponse asyncHttpResponse, final Exception ex, final T t) {
        this.mServer.post(new Runnable() {
            @Override
            public void run() {
                AsyncHttpClient.this.invokeWithAffinity(requestCallback, simpleFuture, asyncHttpResponse, ex, t);
            }
        });
    }
    
    private void invokeConnect(final RequestCallback requestCallback, final AsyncHttpResponse asyncHttpResponse) {
        if (requestCallback != null) {
            requestCallback.onConnect(asyncHttpResponse);
        }
    }
    
    private void invokeProgress(final RequestCallback requestCallback, final AsyncHttpResponse asyncHttpResponse, final long n, final long n2) {
        if (requestCallback != null) {
            requestCallback.onProgress(asyncHttpResponse, n, n2);
        }
    }
    
    private <T> void invokeWithAffinity(final RequestCallback<T> requestCallback, final SimpleFuture<T> simpleFuture, final AsyncHttpResponse asyncHttpResponse, final Exception complete, final T complete2) {
        boolean b;
        if (complete != null) {
            b = simpleFuture.setComplete(complete);
        }
        else {
            b = simpleFuture.setComplete(complete2);
        }
        if (b && requestCallback != null) {
            requestCallback.onCompleted(complete, asyncHttpResponse, complete2);
        }
    }
    
    private void reportConnectedCompleted(final FutureAsyncHttpResponse futureAsyncHttpResponse, final Exception complete, final AsyncHttpResponseImpl complete2, final AsyncHttpRequest asyncHttpRequest, final HttpConnectCallback httpConnectCallback) {
        assert httpConnectCallback != null;
        this.mServer.removeAllCallbacks(futureAsyncHttpResponse.scheduled);
        boolean b;
        if (complete != null) {
            asyncHttpRequest.loge("Connection error", complete);
            b = futureAsyncHttpResponse.setComplete(complete);
        }
        else {
            asyncHttpRequest.logd("Connection successful");
            b = ((SimpleFuture<AsyncHttpResponseImpl>)futureAsyncHttpResponse).setComplete(complete2);
        }
        if (b) {
            httpConnectCallback.onConnectCompleted(complete, complete2);
            assert !(!complete2.isPaused());
        }
        else if (complete2 != null) {
            complete2.setDataCallback(new DataCallback.NullDataCallback());
            complete2.close();
        }
    }
    
    @SuppressLint({ "NewApi" })
    private static void setupAndroidProxy(final AsyncHttpRequest asyncHttpRequest) {
        if (asyncHttpRequest.proxyHost == null) {
            while (true) {
                while (true) {
                    InetSocketAddress inetSocketAddress = null;
                    Label_0107: {
                        try {
                            final List<Proxy> select = ProxySelector.getDefault().select(URI.create(asyncHttpRequest.getUri().toString()));
                            if (!select.isEmpty()) {
                                final Proxy proxy = select.get(0);
                                if (proxy.type() == Proxy.Type.HTTP && proxy.address() instanceof InetSocketAddress) {
                                    inetSocketAddress = (InetSocketAddress)proxy.address();
                                    if (Build$VERSION.SDK_INT < 14) {
                                        break Label_0107;
                                    }
                                    final String s = inetSocketAddress.getHostString();
                                    asyncHttpRequest.enableProxy(s, inetSocketAddress.getPort());
                                }
                            }
                        }
                        catch (Exception ex) {}
                        break;
                    }
                    final InetAddress address = inetSocketAddress.getAddress();
                    if (address != null) {
                        final String s = address.getHostAddress();
                        continue;
                    }
                    final String s = inetSocketAddress.getHostName();
                    continue;
                }
            }
        }
    }
    
    public Future<AsyncHttpResponse> execute(final AsyncHttpRequest asyncHttpRequest, final HttpConnectCallback httpConnectCallback) {
        final FutureAsyncHttpResponse futureAsyncHttpResponse = new FutureAsyncHttpResponse();
        this.execute(asyncHttpRequest, 0, futureAsyncHttpResponse, httpConnectCallback);
        return futureAsyncHttpResponse;
    }
    
    public Future<AsyncHttpResponse> execute(final String s, final HttpConnectCallback httpConnectCallback) {
        return this.execute(new AsyncHttpGet(s), httpConnectCallback);
    }
    
    public <T> SimpleFuture<T> execute(final AsyncHttpRequest asyncHttpRequest, final AsyncParser<T> asyncParser, final RequestCallback<T> requestCallback) {
        final FutureAsyncHttpResponse parent = new FutureAsyncHttpResponse();
        final SimpleFuture<T> simpleFuture = new SimpleFuture<T>();
        this.execute(asyncHttpRequest, 0, parent, new HttpConnectCallback() {
            @Override
            public void onConnectCompleted(final Exception ex, final AsyncHttpResponse asyncHttpResponse) {
                if (ex != null) {
                    AsyncHttpClient.this.invoke(requestCallback, simpleFuture, asyncHttpResponse, ex, null);
                }
                else {
                    AsyncHttpClient.this.invokeConnect(requestCallback, asyncHttpResponse);
                    simpleFuture.setParent(asyncParser.parse(asyncHttpResponse).setCallback(new FutureCallback<T>() {
                        @Override
                        public void onCompleted(final Exception ex, final T t) {
                            AsyncHttpClient.this.invoke(requestCallback, simpleFuture, asyncHttpResponse, ex, t);
                        }
                    }));
                }
            }
        });
        simpleFuture.setParent(parent);
        return simpleFuture;
    }
    
    public Future<ByteBufferList> executeByteBufferList(final AsyncHttpRequest asyncHttpRequest, final DownloadCallback downloadCallback) {
        return (Future<ByteBufferList>)this.execute(asyncHttpRequest, (AsyncParser<Object>)new ByteBufferListParser(), (RequestCallback<Object>)downloadCallback);
    }
    
    public Future<File> executeFile(final AsyncHttpRequest asyncHttpRequest, final String s, final FileCallback fileCallback) {
        final File file = new File(s);
        file.getParentFile().mkdirs();
        try {
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 8192);
            final FutureAsyncHttpResponse parent = new FutureAsyncHttpResponse();
            final SimpleFuture<File> simpleFuture = new SimpleFuture<File>() {
                public void cancelCleanup() {
                    while (true) {
                        try {
                            parent.get().setDataCallback(new DataCallback.NullDataCallback());
                            parent.get().close();
                            try {
                                bufferedOutputStream.close();
                                file.delete();
                            }
                            catch (Exception ex) {}
                        }
                        catch (Exception ex2) {
                            continue;
                        }
                        break;
                    }
                }
            };
            simpleFuture.setParent(parent);
            this.execute(asyncHttpRequest, 0, parent, new HttpConnectCallback() {
                long mDownloaded = 0L;
                
                @Override
                public void onConnectCompleted(final Exception ex, final AsyncHttpResponse asyncHttpResponse) {
                    Label_0038: {
                        if (ex == null) {
                            break Label_0038;
                        }
                        while (true) {
                            try {
                                bufferedOutputStream.close();
                                file.delete();
                                AsyncHttpClient.this.invoke(fileCallback, simpleFuture, asyncHttpResponse, ex, null);
                                return;
                                AsyncHttpClient.this.invokeConnect(fileCallback, asyncHttpResponse);
                                asyncHttpResponse.setDataCallback(new OutputStreamDataCallback(bufferedOutputStream) {
                                    final /* synthetic */ long val$contentLength = HttpUtil.contentLength(asyncHttpResponse.headers());
                                    
                                    @Override
                                    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                                        final HttpConnectCallback this$1 = HttpConnectCallback.this;
                                        this$1.mDownloaded += list.remaining();
                                        super.onDataAvailable(dataEmitter, list);
                                        AsyncHttpClient.this.invokeProgress(fileCallback, asyncHttpResponse, HttpConnectCallback.this.mDownloaded, this.val$contentLength);
                                    }
                                });
                                asyncHttpResponse.setEndCallback(new CompletedCallback() {
                                    @Override
                                    public void onCompleted(Exception ex) {
                                        while (true) {
                                            while (true) {
                                                try {
                                                    bufferedOutputStream.close();
                                                    if (ex != null) {
                                                        file.delete();
                                                        AsyncHttpClient.this.invoke(fileCallback, simpleFuture, asyncHttpResponse, ex, null);
                                                        return;
                                                    }
                                                }
                                                catch (IOException ex2) {
                                                    ex = ex2;
                                                    continue;
                                                }
                                                break;
                                            }
                                            AsyncHttpClient.this.invoke(fileCallback, simpleFuture, asyncHttpResponse, null, file);
                                        }
                                    }
                                });
                            }
                            catch (IOException ex2) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            });
            final SimpleFuture simpleFuture2 = simpleFuture;
            return (Future<File>)simpleFuture2;
        }
        catch (FileNotFoundException complete) {
            final SimpleFuture simpleFuture3 = new SimpleFuture();
            simpleFuture3.setComplete(complete);
            final SimpleFuture simpleFuture2 = simpleFuture3;
            return (Future<File>)simpleFuture2;
        }
    }
    
    public Future<JSONArray> executeJSONArray(final AsyncHttpRequest asyncHttpRequest, final JSONArrayCallback jsonArrayCallback) {
        return (Future<JSONArray>)this.execute(asyncHttpRequest, (AsyncParser<Object>)new JSONArrayParser(), (RequestCallback<Object>)jsonArrayCallback);
    }
    
    public Future<JSONObject> executeJSONObject(final AsyncHttpRequest asyncHttpRequest, final JSONObjectCallback jsonObjectCallback) {
        return (Future<JSONObject>)this.execute(asyncHttpRequest, (AsyncParser<Object>)new JSONObjectParser(), (RequestCallback<Object>)jsonObjectCallback);
    }
    
    public Future<String> executeString(final AsyncHttpRequest asyncHttpRequest, final StringCallback stringCallback) {
        return (Future<String>)this.execute(asyncHttpRequest, (AsyncParser<Object>)new StringParser(), (RequestCallback<Object>)stringCallback);
    }
    
    public Collection<AsyncHttpClientMiddleware> getMiddleware() {
        return this.mMiddleware;
    }
    
    public SpdyMiddleware getSSLSocketMiddleware() {
        return this.sslSocketMiddleware;
    }
    
    public AsyncServer getServer() {
        return this.mServer;
    }
    
    public AsyncSocketMiddleware getSocketMiddleware() {
        return this.socketMiddleware;
    }
    
    public void insertMiddleware(final AsyncHttpClientMiddleware asyncHttpClientMiddleware) {
        this.mMiddleware.add(0, asyncHttpClientMiddleware);
    }
    
    public Future<WebSocket> websocket(final AsyncHttpRequest asyncHttpRequest, final String s, final WebSocketConnectCallback webSocketConnectCallback) {
        WebSocketImpl.addWebSocketUpgradeHeaders(asyncHttpRequest, s);
        final SimpleFuture<WebSocket> simpleFuture = new SimpleFuture<WebSocket>();
        simpleFuture.setParent(this.execute(asyncHttpRequest, new HttpConnectCallback() {
            @Override
            public void onConnectCompleted(Exception ex, final AsyncHttpResponse asyncHttpResponse) {
                if (ex != null) {
                    if (simpleFuture.setComplete(ex) && webSocketConnectCallback != null) {
                        webSocketConnectCallback.onCompleted(ex, null);
                    }
                }
                else {
                    final WebSocket finishHandshake = WebSocketImpl.finishHandshake(asyncHttpRequest.getHeaders(), asyncHttpResponse);
                    if (finishHandshake == null) {
                        ex = new WebSocketHandshakeException("Unable to complete websocket handshake");
                        if (!simpleFuture.setComplete(ex)) {
                            return;
                        }
                    }
                    else if (!simpleFuture.setComplete(finishHandshake)) {
                        return;
                    }
                    if (webSocketConnectCallback != null) {
                        webSocketConnectCallback.onCompleted(ex, finishHandshake);
                    }
                }
            }
        }));
        return simpleFuture;
    }
    
    public Future<WebSocket> websocket(final String s, final String s2, final WebSocketConnectCallback webSocketConnectCallback) {
        return this.websocket(new AsyncHttpGet(s.replace("ws://", "http://").replace("wss://", "https://")), s2, webSocketConnectCallback);
    }
    
    public abstract static class DownloadCallback extends RequestCallbackBase<ByteBufferList>
    {
    }
    
    public abstract static class FileCallback extends RequestCallbackBase<File>
    {
    }
    
    private class FutureAsyncHttpResponse extends SimpleFuture<AsyncHttpResponse>
    {
        public Object scheduled;
        public AsyncSocket socket;
        public Runnable timeoutRunnable;
        
        @Override
        public boolean cancel() {
            boolean b;
            if (!super.cancel()) {
                b = false;
            }
            else {
                if (this.socket != null) {
                    this.socket.setDataCallback(new DataCallback.NullDataCallback());
                    this.socket.close();
                }
                if (this.scheduled != null) {
                    AsyncHttpClient.this.mServer.removeAllCallbacks(this.scheduled);
                }
                b = true;
            }
            return b;
        }
    }
    
    public abstract static class JSONArrayCallback extends RequestCallbackBase<JSONArray>
    {
    }
    
    public abstract static class JSONObjectCallback extends RequestCallbackBase<JSONObject>
    {
    }
    
    public abstract static class RequestCallbackBase<T> implements RequestCallback<T>
    {
        @Override
        public void onConnect(final AsyncHttpResponse asyncHttpResponse) {
        }
        
        @Override
        public void onProgress(final AsyncHttpResponse asyncHttpResponse, final long n, final long n2) {
        }
    }
    
    public abstract static class StringCallback extends RequestCallbackBase<String>
    {
    }
    
    public interface WebSocketConnectCallback
    {
        void onCompleted(final Exception p0, final WebSocket p1);
    }
}
