// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.server;

import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.WebSocketImpl;
import android.text.TextUtils;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.TrustManager;
import com.koushikdutta.async.AsyncSSLSocket;
import com.koushikdutta.async.AsyncSSLSocketWrapper;
import javax.net.ssl.SSLContext;
import java.net.InetAddress;
import com.koushikdutta.async.AsyncServer;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import com.koushikdutta.async.util.StreamUtility;
import java.io.Closeable;
import java.util.regex.Pattern;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import android.util.Pair;
import android.content.Context;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import java.util.regex.Matcher;
import java.util.Iterator;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.HttpUtil;
import com.koushikdutta.async.http.Protocol;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.AsyncServerSocket;
import com.koushikdutta.async.callback.ListenCallback;
import com.koushikdutta.async.callback.CompletedCallback;
import java.util.ArrayList;
import java.util.Hashtable;
import android.annotation.TargetApi;

@TargetApi(5)
public class AsyncHttpServer
{
    private static Hashtable<Integer, String> mCodes;
    static Hashtable<String, String> mContentTypes;
    final Hashtable<String, ArrayList<Pair>> mActions;
    CompletedCallback mCompletedCallback;
    ListenCallback mListenCallback;
    ArrayList<AsyncServerSocket> mListeners;
    
    static {
        AsyncHttpServer.mContentTypes = new Hashtable<String, String>();
        (AsyncHttpServer.mCodes = new Hashtable<Integer, String>()).put(200, "OK");
        AsyncHttpServer.mCodes.put(202, "Accepted");
        AsyncHttpServer.mCodes.put(206, "Partial Content");
        AsyncHttpServer.mCodes.put(101, "Switching Protocols");
        AsyncHttpServer.mCodes.put(301, "Moved Permanently");
        AsyncHttpServer.mCodes.put(302, "Found");
        AsyncHttpServer.mCodes.put(404, "Not Found");
    }
    
    public AsyncHttpServer() {
        this.mListeners = new ArrayList<AsyncServerSocket>();
        this.mListenCallback = new ListenCallback() {
            @Override
            public void onAccepted(final AsyncSocket socket) {
                new AsyncHttpServerRequestImpl() {
                    String fullPath;
                    boolean hasContinued;
                    HttpServerRequestCallback match;
                    String path;
                    boolean requestComplete;
                    AsyncHttpServerResponseImpl res;
                    boolean responseComplete;
                    
                    private void handleOnCompleted() {
                        if (this.requestComplete && this.responseComplete) {
                            if (HttpUtil.isKeepAlive(Protocol.HTTP_1_1, this.getHeaders())) {
                                ListenCallback.this.onAccepted(socket);
                            }
                            else {
                                socket.close();
                            }
                        }
                    }
                    
                    @Override
                    public String getPath() {
                        return this.path;
                    }
                    
                    @Override
                    public Multimap getQuery() {
                        final String[] split = this.fullPath.split("\\?", 2);
                        Multimap query;
                        if (split.length < 2) {
                            query = new Multimap();
                        }
                        else {
                            query = Multimap.parseQuery(split[1]);
                        }
                        return query;
                    }
                    
                    @Override
                    public void onCompleted(final Exception ex) {
                        if (this.res.code() != 101) {
                            this.requestComplete = true;
                            super.onCompleted(ex);
                            this.mSocket.setDataCallback(new NullDataCallback() {
                                @Override
                                public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                                    super.onDataAvailable(dataEmitter, list);
                                    AsyncHttpServerRequestImpl.this.mSocket.close();
                                }
                            });
                            this.handleOnCompleted();
                            if (this.getBody().readFullyOnRequest()) {
                                AsyncHttpServer.this.onRequest(this.match, this, this.res);
                            }
                        }
                    }
                    
                    @Override
                    protected void onHeadersReceived() {
                        final Headers headers = this.getHeaders();
                        if (!this.hasContinued && "100-continue".equals(headers.get("Expect"))) {
                            this.pause();
                            Util.writeAll(this.mSocket, "HTTP/1.1 100 Continue\r\n\r\n".getBytes(), new CompletedCallback() {
                                @Override
                                public void onCompleted(final Exception ex) {
                                    AsyncHttpServerRequestImpl.this.resume();
                                    if (ex != null) {
                                        AsyncHttpServerRequestImpl.this.report(ex);
                                    }
                                    else {
                                        AsyncHttpServerRequestImpl.this.hasContinued = true;
                                        AsyncHttpServerRequestImpl.this.onHeadersReceived();
                                    }
                                }
                            });
                        }
                        else {
                            final String[] split = this.getStatusLine().split(" ");
                            this.fullPath = split[1];
                            this.path = this.fullPath.split("\\?")[0];
                            this.method = split[0];
                            synchronized (AsyncHttpServer.this.mActions) {
                                final ArrayList<Pair> list = AsyncHttpServer.this.mActions.get(this.method);
                                if (list != null) {
                                    for (final Pair pair : list) {
                                        final Matcher matcher = pair.regex.matcher(this.path);
                                        if (matcher.matches()) {
                                            this.mMatcher = matcher;
                                            this.match = pair.callback;
                                            break;
                                        }
                                    }
                                }
                                // monitorexit(this.this$1.this$0.mActions)
                                this.res = new AsyncHttpServerResponseImpl(socket, this) {
                                    @Override
                                    protected void onEnd() {
                                        super.onEnd();
                                        this.mSocket.setEndCallback(null);
                                        AsyncHttpServerRequestImpl.this.responseComplete = true;
                                        AsyncHttpServerRequestImpl.this.handleOnCompleted();
                                    }
                                    
                                    @Override
                                    protected void report(final Exception ex) {
                                        super.report(ex);
                                        if (ex != null) {
                                            socket.setDataCallback(new DataCallback.NullDataCallback());
                                            socket.setEndCallback(new CompletedCallback.NullCompletedCallback());
                                            socket.close();
                                        }
                                    }
                                };
                                final boolean onRequest = AsyncHttpServer.this.onRequest(this, this.res);
                                if (this.match == null && !onRequest) {
                                    this.res.code(404);
                                    this.res.end();
                                    return;
                                }
                            }
                            if (!this.getBody().readFullyOnRequest()) {
                                AsyncHttpServer.this.onRequest(this.match, this, this.res);
                            }
                            else if (this.requestComplete) {
                                AsyncHttpServer.this.onRequest(this.match, this, this.res);
                            }
                        }
                    }
                    
                    @Override
                    protected AsyncHttpRequestBody onUnknownBody(final Headers headers) {
                        return AsyncHttpServer.this.onUnknownBody(headers);
                    }
                }.setSocket(socket);
                socket.resume();
            }
            
            @Override
            public void onCompleted(final Exception ex) {
                AsyncHttpServer.this.report(ex);
            }
            
            @Override
            public void onListening(final AsyncServerSocket asyncServerSocket) {
                AsyncHttpServer.this.mListeners.add(asyncServerSocket);
            }
        };
        this.mActions = new Hashtable<String, ArrayList<Pair>>();
        AsyncHttpServer.mContentTypes.put("js", "application/javascript");
        AsyncHttpServer.mContentTypes.put("json", "application/json");
        AsyncHttpServer.mContentTypes.put("png", "image/png");
        AsyncHttpServer.mContentTypes.put("jpg", "image/jpeg");
        AsyncHttpServer.mContentTypes.put("html", "text/html");
        AsyncHttpServer.mContentTypes.put("css", "text/css");
        AsyncHttpServer.mContentTypes.put("mp4", "video/mp4");
        AsyncHttpServer.mContentTypes.put("mov", "video/quicktime");
        AsyncHttpServer.mContentTypes.put("wmv", "video/x-ms-wmv");
    }
    
    public static android.util.Pair<Integer, InputStream> getAssetStream(final Context context, final String s) {
        final AssetManager assets = context.getAssets();
        try {
            final InputStream open = assets.open(s);
            final android.util.Pair pair = new android.util.Pair((Object)open.available(), (Object)open);
            return (android.util.Pair<Integer, InputStream>)pair;
        }
        catch (IOException ex) {
            final android.util.Pair pair = null;
            return (android.util.Pair<Integer, InputStream>)pair;
        }
    }
    
    public static String getContentType(final String s) {
        String tryGetContentType = tryGetContentType(s);
        if (tryGetContentType == null) {
            tryGetContentType = "text/plain";
        }
        return tryGetContentType;
    }
    
    public static String getResponseCodeDescription(final int n) {
        String s = AsyncHttpServer.mCodes.get(n);
        if (s == null) {
            s = "Unknown";
        }
        return s;
    }
    
    private void report(final Exception ex) {
        if (this.mCompletedCallback != null) {
            this.mCompletedCallback.onCompleted(ex);
        }
    }
    
    public static String tryGetContentType(final String s) {
        final int lastIndex = s.lastIndexOf(".");
        if (lastIndex == -1) {
            return null;
        }
        String s2 = AsyncHttpServer.mContentTypes.get(s.substring(lastIndex + 1));
        if (s2 == null) {
            return null;
        }
        return s2;
        s2 = null;
        return s2;
    }
    
    public void addAction(final String s, final String s2, final HttpServerRequestCallback callback) {
        final Pair pair = new Pair();
        pair.regex = Pattern.compile("^" + s2);
        pair.callback = callback;
        synchronized (this.mActions) {
            ArrayList<Pair> list = this.mActions.get(s);
            if (list == null) {
                list = new ArrayList<Pair>();
                this.mActions.put(s, list);
            }
            list.add(pair);
        }
    }
    
    public void directory(final Context context, final String s, final String s2) {
        final Context applicationContext = context.getApplicationContext();
        this.addAction("GET", s, new HttpServerRequestCallback() {
            @Override
            public void onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
                final String replaceAll = asyncHttpServerRequest.getMatcher().replaceAll("");
                final android.util.Pair<Integer, InputStream> assetStream = AsyncHttpServer.getAssetStream(applicationContext, s2 + replaceAll);
                if (assetStream == null || assetStream.second == null) {
                    asyncHttpServerResponse.code(404);
                    asyncHttpServerResponse.end();
                }
                else {
                    final InputStream inputStream = (InputStream)assetStream.second;
                    asyncHttpServerResponse.getHeaders().set("Content-Length", String.valueOf(assetStream.first));
                    asyncHttpServerResponse.code(200);
                    asyncHttpServerResponse.getHeaders().add("Content-Type", AsyncHttpServer.getContentType(s2 + replaceAll));
                    Util.pump(inputStream, asyncHttpServerResponse, new CompletedCallback() {
                        @Override
                        public void onCompleted(final Exception ex) {
                            asyncHttpServerResponse.end();
                            StreamUtility.closeQuietly(inputStream);
                        }
                    });
                }
            }
        });
        this.addAction("HEAD", s, new HttpServerRequestCallback() {
            @Override
            public void onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
                final String replaceAll = asyncHttpServerRequest.getMatcher().replaceAll("");
                final android.util.Pair<Integer, InputStream> assetStream = AsyncHttpServer.getAssetStream(applicationContext, s2 + replaceAll);
                if (assetStream == null || assetStream.second == null) {
                    asyncHttpServerResponse.code(404);
                    asyncHttpServerResponse.end();
                }
                else {
                    StreamUtility.closeQuietly((InputStream)assetStream.second);
                    asyncHttpServerResponse.getHeaders().set("Content-Length", String.valueOf(assetStream.first));
                    asyncHttpServerResponse.code(200);
                    asyncHttpServerResponse.getHeaders().add("Content-Type", AsyncHttpServer.getContentType(s2 + replaceAll));
                    asyncHttpServerResponse.writeHead();
                    asyncHttpServerResponse.end();
                }
            }
        });
    }
    
    public void directory(final String s, final File file) {
        this.directory(s, file, false);
    }
    
    public void directory(final String s, final File file, final boolean b) {
        assert file.isDirectory();
        this.addAction("GET", s, new HttpServerRequestCallback() {
            @Override
            public void onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
                final File file = new File(file, asyncHttpServerRequest.getMatcher().replaceAll(""));
                if (file.isDirectory() && b) {
                    final ArrayList<Object> list = new ArrayList<Object>();
                    final ArrayList<Object> list2 = new ArrayList<Object>();
                    for (final File file2 : file.listFiles()) {
                        if (file2.isDirectory()) {
                            list.add(file2);
                        }
                        else {
                            list2.add(file2);
                        }
                    }
                    final Comparator<File> comparator = new Comparator<File>() {
                        @Override
                        public int compare(final File file, final File file2) {
                            return file.getName().compareTo(file2.getName());
                        }
                    };
                    Collections.sort(list, (Comparator<? super Object>)comparator);
                    Collections.sort(list2, (Comparator<? super Object>)comparator);
                    list2.addAll(0, list);
                }
                else if (!file.isFile()) {
                    asyncHttpServerResponse.code(404);
                    asyncHttpServerResponse.end();
                }
                else {
                    try {
                        final FileInputStream fileInputStream = new FileInputStream(file);
                        asyncHttpServerResponse.code(200);
                        Util.pump(fileInputStream, asyncHttpServerResponse, new CompletedCallback() {
                            @Override
                            public void onCompleted(final Exception ex) {
                                asyncHttpServerResponse.end();
                            }
                        });
                    }
                    catch (FileNotFoundException ex) {
                        asyncHttpServerResponse.code(404);
                        asyncHttpServerResponse.end();
                    }
                }
            }
        });
    }
    
    public void get(final String s, final HttpServerRequestCallback httpServerRequestCallback) {
        this.addAction("GET", s, httpServerRequestCallback);
    }
    
    public CompletedCallback getErrorCallback() {
        return this.mCompletedCallback;
    }
    
    public ListenCallback getListenCallback() {
        return this.mListenCallback;
    }
    
    public AsyncServerSocket listen(final int n) {
        return this.listen(AsyncServer.getDefault(), n);
    }
    
    public AsyncServerSocket listen(final AsyncServer asyncServer, final int n) {
        return asyncServer.listen(null, n, this.mListenCallback);
    }
    
    public void listenSecure(final int n, final SSLContext sslContext) {
        AsyncServer.getDefault().listen(null, n, new ListenCallback() {
            @Override
            public void onAccepted(final AsyncSocket asyncSocket) {
                AsyncSSLSocketWrapper.handshake(asyncSocket, null, n, sslContext.createSSLEngine(), null, null, false, (AsyncSSLSocketWrapper.HandshakeCallback)new AsyncSSLSocketWrapper.HandshakeCallback() {
                    @Override
                    public void onHandshakeCompleted(final Exception ex, final AsyncSSLSocket asyncSSLSocket) {
                        if (asyncSSLSocket != null) {
                            AsyncHttpServer.this.mListenCallback.onAccepted(asyncSSLSocket);
                        }
                    }
                });
            }
            
            @Override
            public void onCompleted(final Exception ex) {
                AsyncHttpServer.this.mListenCallback.onCompleted(ex);
            }
            
            @Override
            public void onListening(final AsyncServerSocket asyncServerSocket) {
                AsyncHttpServer.this.mListenCallback.onListening(asyncServerSocket);
            }
        });
    }
    
    protected void onRequest(final HttpServerRequestCallback httpServerRequestCallback, final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
        if (httpServerRequestCallback != null) {
            httpServerRequestCallback.onRequest(asyncHttpServerRequest, asyncHttpServerResponse);
        }
    }
    
    protected boolean onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
        return false;
    }
    
    protected AsyncHttpRequestBody onUnknownBody(final Headers headers) {
        return new UnknownRequestBody(headers.get("Content-Type"));
    }
    
    public void post(final String s, final HttpServerRequestCallback httpServerRequestCallback) {
        this.addAction("POST", s, httpServerRequestCallback);
    }
    
    public void removeAction(final String s, final String s2) {
    Label_0030_Outer:
        while (true) {
            while (true) {
                Label_0092: {
                    int n;
                    synchronized (this.mActions) {
                        final ArrayList<Pair> list = this.mActions.get(s);
                        if (list == null) {
                            break;
                        }
                        break Label_0092;
                        while (true) {
                            list.remove(n);
                            break;
                            continue Label_0030_Outer;
                        }
                    }
                    // iftrue(Label_0083:, !s2.equals((Object)(Pair)list.get(n).regex.toString()))
                    // iftrue(Label_0089:, n >= list.size())
                    Label_0083: {
                        ++n;
                    }
                    continue;
                }
                int n = 0;
                continue;
            }
            Label_0089: {
                break;
            }
        }
        // monitorexit(hashtable)
    }
    
    public void setErrorCallback(final CompletedCallback mCompletedCallback) {
        this.mCompletedCallback = mCompletedCallback;
    }
    
    public void stop() {
        if (this.mListeners != null) {
            final Iterator<AsyncServerSocket> iterator = this.mListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().stop();
            }
        }
    }
    
    public void websocket(final String s, final WebSocketRequestCallback webSocketRequestCallback) {
        this.websocket(s, null, webSocketRequestCallback);
    }
    
    public void websocket(final String s, final String s2, final WebSocketRequestCallback webSocketRequestCallback) {
        this.get(s, new HttpServerRequestCallback() {
            @Override
            public void onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
                final String value = asyncHttpServerRequest.getHeaders().get("Connection");
                boolean b = false;
                if (value != null) {
                    final String[] split = value.split(",");
                    final int length = split.length;
                    int n = 0;
                    while (true) {
                        b = false;
                        if (n >= length) {
                            break;
                        }
                        if ("Upgrade".equalsIgnoreCase(split[n].trim())) {
                            b = true;
                            break;
                        }
                        ++n;
                    }
                }
                if (!"websocket".equalsIgnoreCase(asyncHttpServerRequest.getHeaders().get("Upgrade")) || !b) {
                    asyncHttpServerResponse.code(404);
                    asyncHttpServerResponse.end();
                }
                else if (!TextUtils.equals((CharSequence)s2, (CharSequence)asyncHttpServerRequest.getHeaders().get("Sec-WebSocket-Protocol"))) {
                    asyncHttpServerResponse.code(404);
                    asyncHttpServerResponse.end();
                }
                else {
                    webSocketRequestCallback.onConnected(new WebSocketImpl(asyncHttpServerRequest, asyncHttpServerResponse), asyncHttpServerRequest);
                }
            }
        });
    }
    
    private static class Pair
    {
        HttpServerRequestCallback callback;
        Pattern regex;
    }
    
    public interface WebSocketRequestCallback
    {
        void onConnected(final WebSocket p0, final AsyncHttpServerRequest p1);
    }
}
