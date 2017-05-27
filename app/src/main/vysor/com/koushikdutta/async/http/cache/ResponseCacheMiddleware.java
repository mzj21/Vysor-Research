// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.cache;

import java.io.FileOutputStream;
import java.net.CacheResponse;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.koushikdutta.async.util.Charsets;
import java.security.cert.CertificateEncodingException;
import java.io.Writer;
import java.security.cert.CertificateException;
import java.io.ByteArrayInputStream;
import android.util.Base64;
import java.security.cert.CertificateFactory;
import com.koushikdutta.async.http.AsyncHttpRequest;
import java.security.cert.Certificate;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.callback.CompletedCallback;
import javax.net.ssl.SSLEngine;
import java.security.cert.X509Certificate;
import com.koushikdutta.async.AsyncSSLSocket;
import com.koushikdutta.async.util.Allocator;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.FilteredDataEmitter;
import android.net.Uri;
import com.koushikdutta.async.http.Headers;
import java.util.Locale;
import com.koushikdutta.async.Util;
import java.io.FileInputStream;
import com.koushikdutta.async.future.SimpleCancellable;
import com.koushikdutta.async.AsyncSocket;
import java.nio.ByteBuffer;
import java.io.Closeable;
import com.koushikdutta.async.util.StreamUtility;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import com.koushikdutta.async.future.Cancellable;
import java.util.Iterator;
import java.io.IOException;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import java.io.File;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.util.FileCache;
import com.koushikdutta.async.http.SimpleMiddleware;

public class ResponseCacheMiddleware extends SimpleMiddleware
{
    public static final String CACHE = "cache";
    public static final String CONDITIONAL_CACHE = "conditional-cache";
    public static final int ENTRY_BODY = 1;
    public static final int ENTRY_COUNT = 2;
    public static final int ENTRY_METADATA = 0;
    private static final String LOGTAG = "AsyncHttpCache";
    public static final String SERVED_FROM = "X-Served-From";
    private FileCache cache;
    private int cacheHitCount;
    private int cacheStoreCount;
    private boolean caching;
    private int conditionalCacheHitCount;
    private int networkCount;
    private AsyncServer server;
    private int writeAbortCount;
    private int writeSuccessCount;
    
    private ResponseCacheMiddleware() {
        this.caching = true;
    }
    
    public static ResponseCacheMiddleware addCache(final AsyncHttpClient asyncHttpClient, final File file, final long n) throws IOException {
        final Iterator<AsyncHttpClientMiddleware> iterator = asyncHttpClient.getMiddleware().iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof ResponseCacheMiddleware) {
                throw new IOException("Response cache already added to http client");
            }
        }
        final ResponseCacheMiddleware responseCacheMiddleware = new ResponseCacheMiddleware();
        responseCacheMiddleware.server = asyncHttpClient.getServer();
        responseCacheMiddleware.cache = new FileCache(file, n, false);
        asyncHttpClient.insertMiddleware(responseCacheMiddleware);
        return responseCacheMiddleware;
    }
    
    public void clear() {
        if (this.cache != null) {
            this.cache.clear();
        }
    }
    
    public int getCacheHitCount() {
        return this.cacheHitCount;
    }
    
    public int getCacheStoreCount() {
        return this.cacheStoreCount;
    }
    
    public boolean getCaching() {
        return this.caching;
    }
    
    public int getConditionalCacheHitCount() {
        return this.conditionalCacheHitCount;
    }
    
    public FileCache getFileCache() {
        return this.cache;
    }
    
    public int getNetworkCount() {
        return this.networkCount;
    }
    
    @Override
    public Cancellable getSocket(final GetSocketData getSocketData) {
        final RequestHeaders requestHeaders = new RequestHeaders(getSocketData.request.getUri(), RawHeaders.fromMultimap(getSocketData.request.getHeaders().getMultiMap()));
        getSocketData.state.put("request-headers", requestHeaders);
        Cancellable cancellable;
        if (this.cache == null || !this.caching || requestHeaders.isNoCache()) {
            ++this.networkCount;
            cancellable = null;
        }
        else {
            final String keyString = FileCache.toKeyString(getSocketData.request.getUri());
            FileInputStream[] value = null;
            long contentLength = 0L;
            Entry entry = null;
            Label_0229: {
                try {
                    value = this.cache.get(keyString, 2);
                    if (value == null) {
                        ++this.networkCount;
                        cancellable = null;
                    }
                    else {
                        contentLength = value[1].available();
                        entry = new Entry(value[0]);
                        if (entry.matches(getSocketData.request.getUri(), getSocketData.request.getMethod(), getSocketData.request.getHeaders().getMultiMap())) {
                            break Label_0229;
                        }
                        ++this.networkCount;
                        StreamUtility.closeQuietly((Closeable[])value);
                        cancellable = null;
                    }
                }
                catch (IOException ex) {
                    ++this.networkCount;
                    StreamUtility.closeQuietly((Closeable[])value);
                    cancellable = null;
                }
                return cancellable;
            }
            final EntryCacheResponse candidate = new EntryCacheResponse(entry, value[1]);
            Map<String, List<String>> headers = null;
            Label_0310: {
                try {
                    headers = candidate.getHeaders();
                    final FileInputStream body = candidate.getBody();
                    if (headers != null && body != null) {
                        break Label_0310;
                    }
                    ++this.networkCount;
                    StreamUtility.closeQuietly((Closeable[])value);
                    cancellable = null;
                }
                catch (Exception ex2) {
                    ++this.networkCount;
                    StreamUtility.closeQuietly((Closeable[])value);
                    cancellable = null;
                }
                return cancellable;
            }
            final RawHeaders fromMultimap = RawHeaders.fromMultimap(headers);
            final ResponseHeaders cachedResponseHeaders = new ResponseHeaders(getSocketData.request.getUri(), fromMultimap);
            fromMultimap.set("Content-Length", String.valueOf(contentLength));
            fromMultimap.removeAll("Content-Encoding");
            fromMultimap.removeAll("Transfer-Encoding");
            cachedResponseHeaders.setLocalTimestamps(System.currentTimeMillis(), System.currentTimeMillis());
            final ResponseSource chooseResponseSource = cachedResponseHeaders.chooseResponseSource(System.currentTimeMillis(), requestHeaders);
            if (chooseResponseSource == ResponseSource.CACHE) {
                getSocketData.request.logi("Response retrieved from cache");
                AsyncSocket asyncSocket;
                if (entry.isHttps()) {
                    asyncSocket = new CachedSSLSocket(candidate, contentLength);
                }
                else {
                    asyncSocket = new CachedSocket(candidate, contentLength);
                }
                ((CachedSocket)asyncSocket).pending.add(ByteBuffer.wrap(fromMultimap.toHeaderString().getBytes()));
                this.server.post(new Runnable() {
                    @Override
                    public void run() {
                        getSocketData.connectCallback.onConnectCompleted(null, asyncSocket);
                        ((CachedBodyEmitter)asyncSocket).sendCachedDataOnNetworkThread();
                    }
                });
                ++this.cacheHitCount;
                getSocketData.state.put("socket-owner", this);
                cancellable = new SimpleCancellable();
                ((SimpleCancellable)cancellable).setComplete();
            }
            else if (chooseResponseSource == ResponseSource.CONDITIONAL_CACHE) {
                getSocketData.request.logi("Response may be served from conditional cache");
                final CacheData cacheData = new CacheData();
                cacheData.snapshot = value;
                cacheData.contentLength = contentLength;
                cacheData.cachedResponseHeaders = cachedResponseHeaders;
                cacheData.candidate = candidate;
                getSocketData.state.put("cache-data", cacheData);
                cancellable = null;
            }
            else {
                getSocketData.request.logd("Response can not be served from cache");
                ++this.networkCount;
                StreamUtility.closeQuietly((Closeable[])value);
                cancellable = null;
            }
        }
        return cancellable;
    }
    
    @Override
    public void onBodyDecoder(final OnBodyDataOnRequestSentData onBodyDataOnRequestSentData) {
        if (Util.getWrappedSocket(onBodyDataOnRequestSentData.socket, CachedSocket.class) != null) {
            onBodyDataOnRequestSentData.response.headers().set("X-Served-From", "cache");
        }
        else {
            final CacheData cacheData = onBodyDataOnRequestSentData.state.get("cache-data");
            final RawHeaders fromMultimap = RawHeaders.fromMultimap(onBodyDataOnRequestSentData.response.headers().getMultiMap());
            fromMultimap.removeAll("Content-Length");
            fromMultimap.setStatusLine(String.format(Locale.ENGLISH, "%s %s %s", onBodyDataOnRequestSentData.response.protocol(), onBodyDataOnRequestSentData.response.code(), onBodyDataOnRequestSentData.response.message()));
            final ResponseHeaders responseHeaders = new ResponseHeaders(onBodyDataOnRequestSentData.request.getUri(), fromMultimap);
            onBodyDataOnRequestSentData.state.put("response-headers", responseHeaders);
            if (cacheData != null) {
                if (cacheData.cachedResponseHeaders.validate(responseHeaders)) {
                    onBodyDataOnRequestSentData.request.logi("Serving response from conditional cache");
                    final ResponseHeaders combine = cacheData.cachedResponseHeaders.combine(responseHeaders);
                    onBodyDataOnRequestSentData.response.headers(new Headers(combine.getHeaders().toMultimap()));
                    onBodyDataOnRequestSentData.response.code(combine.getHeaders().getResponseCode());
                    onBodyDataOnRequestSentData.response.message(combine.getHeaders().getResponseMessage());
                    onBodyDataOnRequestSentData.response.headers().set("X-Served-From", "conditional-cache");
                    ++this.conditionalCacheHitCount;
                    final CachedBodyEmitter bodyEmitter = new CachedBodyEmitter(cacheData.candidate, cacheData.contentLength);
                    bodyEmitter.setDataEmitter(onBodyDataOnRequestSentData.bodyEmitter);
                    ((CachedBodyEmitter)(onBodyDataOnRequestSentData.bodyEmitter = bodyEmitter)).sendCachedData();
                    return;
                }
                onBodyDataOnRequestSentData.state.remove("cache-data");
                StreamUtility.closeQuietly((Closeable[])cacheData.snapshot);
            }
            if (this.caching) {
                final RequestHeaders requestHeaders = onBodyDataOnRequestSentData.state.get("request-headers");
                if (requestHeaders == null || !responseHeaders.isCacheable(requestHeaders) || !onBodyDataOnRequestSentData.request.getMethod().equals("GET")) {
                    ++this.networkCount;
                    onBodyDataOnRequestSentData.request.logd("Response is not cacheable");
                }
                else {
                    final String keyString = FileCache.toKeyString(onBodyDataOnRequestSentData.request.getUri());
                    final Entry entry = new Entry(onBodyDataOnRequestSentData.request.getUri(), requestHeaders.getHeaders().getAll(responseHeaders.getVaryFields()), onBodyDataOnRequestSentData.request, responseHeaders.getHeaders());
                    final BodyCacher bodyEmitter2 = new BodyCacher();
                    final EntryEditor editor = new EntryEditor(keyString);
                    try {
                        entry.writeTo(editor);
                        editor.newOutputStream(1);
                        bodyEmitter2.editor = editor;
                        bodyEmitter2.setDataEmitter(onBodyDataOnRequestSentData.bodyEmitter);
                        onBodyDataOnRequestSentData.bodyEmitter = bodyEmitter2;
                        onBodyDataOnRequestSentData.state.put("body-cacher", bodyEmitter2);
                        onBodyDataOnRequestSentData.request.logd("Caching response");
                        ++this.cacheStoreCount;
                    }
                    catch (Exception ex) {
                        editor.abort();
                        ++this.networkCount;
                    }
                }
            }
        }
    }
    
    @Override
    public void onResponseComplete(final OnResponseCompleteDataOnRequestSentData onResponseCompleteDataOnRequestSentData) {
        final CacheData cacheData = onResponseCompleteDataOnRequestSentData.state.get("cache-data");
        if (cacheData != null && cacheData.snapshot != null) {
            StreamUtility.closeQuietly((Closeable[])cacheData.snapshot);
        }
        final CachedSocket cachedSocket = Util.getWrappedSocket(onResponseCompleteDataOnRequestSentData.socket, CachedSocket.class);
        if (cachedSocket != null) {
            StreamUtility.closeQuietly(cachedSocket.cacheResponse.getBody());
        }
        final BodyCacher bodyCacher = onResponseCompleteDataOnRequestSentData.state.get("body-cacher");
        if (bodyCacher != null) {
            if (onResponseCompleteDataOnRequestSentData.exception != null) {
                bodyCacher.abort();
            }
            else {
                bodyCacher.commit();
            }
        }
    }
    
    public void removeFromCache(final Uri uri) {
        this.getFileCache().remove(FileCache.toKeyString(uri));
    }
    
    public void setCaching(final boolean caching) {
        this.caching = caching;
    }
    
    private static class BodyCacher extends FilteredDataEmitter
    {
        ByteBufferList cached;
        EntryEditor editor;
        
        public void abort() {
            if (this.editor != null) {
                this.editor.abort();
                this.editor = null;
            }
        }
        
        @Override
        public void close() {
            this.abort();
            super.close();
        }
        
        public void commit() {
            if (this.editor != null) {
                this.editor.commit();
                this.editor = null;
            }
        }
        
        @Override
        public void onDataAvailable(final DataEmitter p0, final ByteBufferList p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.cached:Lcom/koushikdutta/async/ByteBufferList;
            //     4: ifnull          32
            //     7: aload_0        
            //     8: aload_1        
            //     9: aload_0        
            //    10: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.cached:Lcom/koushikdutta/async/ByteBufferList;
            //    13: invokespecial   com/koushikdutta/async/FilteredDataEmitter.onDataAvailable:(Lcom/koushikdutta/async/DataEmitter;Lcom/koushikdutta/async/ByteBufferList;)V
            //    16: aload_0        
            //    17: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.cached:Lcom/koushikdutta/async/ByteBufferList;
            //    20: invokevirtual   com/koushikdutta/async/ByteBufferList.remaining:()I
            //    23: ifle            27
            //    26: return         
            //    27: aload_0        
            //    28: aconst_null    
            //    29: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.cached:Lcom/koushikdutta/async/ByteBufferList;
            //    32: new             Lcom/koushikdutta/async/ByteBufferList;
            //    35: dup            
            //    36: invokespecial   com/koushikdutta/async/ByteBufferList.<init>:()V
            //    39: astore_3       
            //    40: aload_0        
            //    41: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.editor:Lcom/koushikdutta/async/http/cache/ResponseCacheMiddleware$EntryEditor;
            //    44: ifnull          181
            //    47: aload_0        
            //    48: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.editor:Lcom/koushikdutta/async/http/cache/ResponseCacheMiddleware$EntryEditor;
            //    51: iconst_1       
            //    52: invokevirtual   com/koushikdutta/async/http/cache/ResponseCacheMiddleware$EntryEditor.newOutputStream:(I)Ljava/io/FileOutputStream;
            //    55: astore          6
            //    57: aload           6
            //    59: ifnull          177
            //    62: aload_2        
            //    63: invokevirtual   com/koushikdutta/async/ByteBufferList.isEmpty:()Z
            //    66: ifne            181
            //    69: aload_2        
            //    70: invokevirtual   com/koushikdutta/async/ByteBufferList.remove:()Ljava/nio/ByteBuffer;
            //    73: astore          7
            //    75: aload           6
            //    77: aload           7
            //    79: invokestatic    com/koushikdutta/async/ByteBufferList.writeOutputStream:(Ljava/io/OutputStream;Ljava/nio/ByteBuffer;)V
            //    82: aload_3        
            //    83: aload           7
            //    85: invokevirtual   com/koushikdutta/async/ByteBufferList.add:(Ljava/nio/ByteBuffer;)Lcom/koushikdutta/async/ByteBufferList;
            //    88: pop            
            //    89: goto            62
            //    92: astore          5
            //    94: aload_0        
            //    95: invokevirtual   com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.abort:()V
            //    98: aload_2        
            //    99: aload_3        
            //   100: invokevirtual   com/koushikdutta/async/ByteBufferList.get:(Lcom/koushikdutta/async/ByteBufferList;)V
            //   103: aload_3        
            //   104: aload_2        
            //   105: invokevirtual   com/koushikdutta/async/ByteBufferList.get:(Lcom/koushikdutta/async/ByteBufferList;)V
            //   108: aload_0        
            //   109: aload_1        
            //   110: aload_2        
            //   111: invokespecial   com/koushikdutta/async/FilteredDataEmitter.onDataAvailable:(Lcom/koushikdutta/async/DataEmitter;Lcom/koushikdutta/async/ByteBufferList;)V
            //   114: aload_0        
            //   115: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.editor:Lcom/koushikdutta/async/http/cache/ResponseCacheMiddleware$EntryEditor;
            //   118: ifnull          26
            //   121: aload_2        
            //   122: invokevirtual   com/koushikdutta/async/ByteBufferList.remaining:()I
            //   125: ifle            26
            //   128: aload_0        
            //   129: new             Lcom/koushikdutta/async/ByteBufferList;
            //   132: dup            
            //   133: invokespecial   com/koushikdutta/async/ByteBufferList.<init>:()V
            //   136: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.cached:Lcom/koushikdutta/async/ByteBufferList;
            //   139: aload_2        
            //   140: aload_0        
            //   141: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.cached:Lcom/koushikdutta/async/ByteBufferList;
            //   144: invokevirtual   com/koushikdutta/async/ByteBufferList.get:(Lcom/koushikdutta/async/ByteBufferList;)V
            //   147: goto            26
            //   150: astore          8
            //   152: aload_3        
            //   153: aload           7
            //   155: invokevirtual   com/koushikdutta/async/ByteBufferList.add:(Ljava/nio/ByteBuffer;)Lcom/koushikdutta/async/ByteBufferList;
            //   158: pop            
            //   159: aload           8
            //   161: athrow         
            //   162: astore          4
            //   164: aload_2        
            //   165: aload_3        
            //   166: invokevirtual   com/koushikdutta/async/ByteBufferList.get:(Lcom/koushikdutta/async/ByteBufferList;)V
            //   169: aload_3        
            //   170: aload_2        
            //   171: invokevirtual   com/koushikdutta/async/ByteBufferList.get:(Lcom/koushikdutta/async/ByteBufferList;)V
            //   174: aload           4
            //   176: athrow         
            //   177: aload_0        
            //   178: invokevirtual   com/koushikdutta/async/http/cache/ResponseCacheMiddleware$BodyCacher.abort:()V
            //   181: aload_2        
            //   182: aload_3        
            //   183: invokevirtual   com/koushikdutta/async/ByteBufferList.get:(Lcom/koushikdutta/async/ByteBufferList;)V
            //   186: aload_3        
            //   187: aload_2        
            //   188: invokevirtual   com/koushikdutta/async/ByteBufferList.get:(Lcom/koushikdutta/async/ByteBufferList;)V
            //   191: goto            108
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  40     75     92     108    Ljava/lang/Exception;
            //  40     75     162    177    Any
            //  75     82     150    162    Any
            //  82     89     92     108    Ljava/lang/Exception;
            //  82     89     162    177    Any
            //  94     98     162    177    Any
            //  152    162    92     108    Ljava/lang/Exception;
            //  152    162    162    177    Any
            //  177    181    92     108    Ljava/lang/Exception;
            //  177    181    162    177    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        @Override
        protected void report(final Exception ex) {
            super.report(ex);
            if (ex != null) {
                this.abort();
            }
        }
    }
    
    public static class CacheData
    {
        ResponseHeaders cachedResponseHeaders;
        EntryCacheResponse candidate;
        long contentLength;
        FileInputStream[] snapshot;
    }
    
    private static class CachedBodyEmitter extends FilteredDataEmitter
    {
        private Allocator allocator;
        boolean allowEnd;
        EntryCacheResponse cacheResponse;
        private boolean paused;
        ByteBufferList pending;
        Runnable sendCachedDataRunnable;
        
        public CachedBodyEmitter(final EntryCacheResponse cacheResponse, final long n) {
            this.pending = new ByteBufferList();
            this.allocator = new Allocator();
            this.sendCachedDataRunnable = new Runnable() {
                @Override
                public void run() {
                    CachedBodyEmitter.this.sendCachedDataOnNetworkThread();
                }
            };
            this.cacheResponse = cacheResponse;
            this.allocator.setCurrentAlloc((int)n);
        }
        
        @Override
        public void close() {
            if (this.getServer().getAffinity() != Thread.currentThread()) {
                this.getServer().post(new Runnable() {
                    @Override
                    public void run() {
                        CachedBodyEmitter.this.close();
                    }
                });
            }
            else {
                this.pending.recycle();
                StreamUtility.closeQuietly(this.cacheResponse.getBody());
                super.close();
            }
        }
        
        @Override
        public boolean isPaused() {
            return this.paused;
        }
        
        @Override
        protected void report(final Exception ex) {
            if (this.allowEnd) {
                StreamUtility.closeQuietly(this.cacheResponse.getBody());
                super.report(ex);
            }
        }
        
        @Override
        public void resume() {
            this.paused = false;
            this.sendCachedData();
        }
        
        void sendCachedData() {
            this.getServer().post(this.sendCachedDataRunnable);
        }
        
        void sendCachedDataOnNetworkThread() {
            ByteBuffer allocate = null;
            Label_0030: {
                if (this.pending.remaining() <= 0) {
                    break Label_0030;
                }
                super.onDataAvailable(this, this.pending);
                if (this.pending.remaining() <= 0) {
                    break Label_0030;
                }
                return;
                try {
                    allocate = this.allocator.allocate();
                    assert allocate.position() == 0;
                }
                catch (IOException ex) {
                    this.allowEnd = true;
                    this.report(ex);
                    return;
                }
            }
            final int read = this.cacheResponse.getBody().read(allocate.array(), allocate.arrayOffset(), allocate.capacity());
            if (read == -1) {
                ByteBufferList.reclaim(allocate);
                this.allowEnd = true;
                this.report(null);
                return;
            }
            this.allocator.track(read);
            allocate.limit(read);
            this.pending.add(allocate);
            super.onDataAvailable(this, this.pending);
            if (this.pending.remaining() <= 0) {
                this.getServer().postDelayed(this.sendCachedDataRunnable, 10L);
            }
        }
    }
    
    private class CachedSSLSocket extends CachedSocket implements AsyncSSLSocket
    {
        public CachedSSLSocket(final EntryCacheResponse entryCacheResponse, final long n) {
            super(entryCacheResponse, n);
        }
        
        @Override
        public X509Certificate[] getPeerCertificates() {
            return null;
        }
        
        @Override
        public SSLEngine getSSLEngine() {
            return null;
        }
    }
    
    private class CachedSocket extends CachedBodyEmitter implements AsyncSocket
    {
        boolean closed;
        CompletedCallback closedCallback;
        boolean open;
        
        public CachedSocket(final EntryCacheResponse entryCacheResponse, final long n) {
            super(entryCacheResponse, n);
            this.allowEnd = true;
        }
        
        @Override
        public void close() {
            this.open = false;
        }
        
        @Override
        public void end() {
        }
        
        @Override
        public CompletedCallback getClosedCallback() {
            return this.closedCallback;
        }
        
        @Override
        public AsyncServer getServer() {
            return ResponseCacheMiddleware.this.server;
        }
        
        @Override
        public WritableCallback getWriteableCallback() {
            return null;
        }
        
        @Override
        public boolean isOpen() {
            return this.open;
        }
        
        @Override
        protected void report(final Exception ex) {
            super.report(ex);
            if (!this.closed) {
                this.closed = true;
                if (this.closedCallback != null) {
                    this.closedCallback.onCompleted(ex);
                }
            }
        }
        
        @Override
        public void setClosedCallback(final CompletedCallback closedCallback) {
            this.closedCallback = closedCallback;
        }
        
        @Override
        public void setWriteableCallback(final WritableCallback writableCallback) {
        }
        
        @Override
        public void write(final ByteBufferList list) {
            list.recycle();
        }
    }
    
    private static final class Entry
    {
        private final String cipherSuite;
        private final Certificate[] localCertificates;
        private final Certificate[] peerCertificates;
        private final String requestMethod;
        private final RawHeaders responseHeaders;
        private final String uri;
        private final RawHeaders varyHeaders;
        
        public Entry(final Uri uri, final RawHeaders varyHeaders, final AsyncHttpRequest asyncHttpRequest, final RawHeaders responseHeaders) {
            this.uri = uri.toString();
            this.varyHeaders = varyHeaders;
            this.requestMethod = asyncHttpRequest.getMethod();
            this.responseHeaders = responseHeaders;
            this.cipherSuite = null;
            this.peerCertificates = null;
            this.localCertificates = null;
        }
        
        public Entry(final InputStream p0) throws IOException {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: invokespecial   java/lang/Object.<init>:()V
            //     4: aconst_null    
            //     5: astore_2       
            //     6: new             Lcom/koushikdutta/async/http/cache/StrictLineReader;
            //     9: dup            
            //    10: aload_1        
            //    11: getstatic       com/koushikdutta/async/util/Charsets.US_ASCII:Ljava/nio/charset/Charset;
            //    14: invokespecial   com/koushikdutta/async/http/cache/StrictLineReader.<init>:(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
            //    17: astore_3       
            //    18: aload_0        
            //    19: aload_3        
            //    20: invokevirtual   com/koushikdutta/async/http/cache/StrictLineReader.readLine:()Ljava/lang/String;
            //    23: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.uri:Ljava/lang/String;
            //    26: aload_0        
            //    27: aload_3        
            //    28: invokevirtual   com/koushikdutta/async/http/cache/StrictLineReader.readLine:()Ljava/lang/String;
            //    31: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.requestMethod:Ljava/lang/String;
            //    34: aload_0        
            //    35: new             Lcom/koushikdutta/async/http/cache/RawHeaders;
            //    38: dup            
            //    39: invokespecial   com/koushikdutta/async/http/cache/RawHeaders.<init>:()V
            //    42: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.varyHeaders:Lcom/koushikdutta/async/http/cache/RawHeaders;
            //    45: aload_3        
            //    46: invokevirtual   com/koushikdutta/async/http/cache/StrictLineReader.readInt:()I
            //    49: istore          5
            //    51: iconst_0       
            //    52: istore          6
            //    54: iload           6
            //    56: iload           5
            //    58: if_icmpge       78
            //    61: aload_0        
            //    62: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.varyHeaders:Lcom/koushikdutta/async/http/cache/RawHeaders;
            //    65: aload_3        
            //    66: invokevirtual   com/koushikdutta/async/http/cache/StrictLineReader.readLine:()Ljava/lang/String;
            //    69: invokevirtual   com/koushikdutta/async/http/cache/RawHeaders.addLine:(Ljava/lang/String;)V
            //    72: iinc            6, 1
            //    75: goto            54
            //    78: aload_0        
            //    79: new             Lcom/koushikdutta/async/http/cache/RawHeaders;
            //    82: dup            
            //    83: invokespecial   com/koushikdutta/async/http/cache/RawHeaders.<init>:()V
            //    86: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.responseHeaders:Lcom/koushikdutta/async/http/cache/RawHeaders;
            //    89: aload_0        
            //    90: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.responseHeaders:Lcom/koushikdutta/async/http/cache/RawHeaders;
            //    93: aload_3        
            //    94: invokevirtual   com/koushikdutta/async/http/cache/StrictLineReader.readLine:()Ljava/lang/String;
            //    97: invokevirtual   com/koushikdutta/async/http/cache/RawHeaders.setStatusLine:(Ljava/lang/String;)V
            //   100: aload_3        
            //   101: invokevirtual   com/koushikdutta/async/http/cache/StrictLineReader.readInt:()I
            //   104: istore          7
            //   106: iconst_0       
            //   107: istore          8
            //   109: iload           8
            //   111: iload           7
            //   113: if_icmpge       133
            //   116: aload_0        
            //   117: getfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.responseHeaders:Lcom/koushikdutta/async/http/cache/RawHeaders;
            //   120: aload_3        
            //   121: invokevirtual   com/koushikdutta/async/http/cache/StrictLineReader.readLine:()Ljava/lang/String;
            //   124: invokevirtual   com/koushikdutta/async/http/cache/RawHeaders.addLine:(Ljava/lang/String;)V
            //   127: iinc            8, 1
            //   130: goto            109
            //   133: aload_0        
            //   134: aconst_null    
            //   135: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.cipherSuite:Ljava/lang/String;
            //   138: aload_0        
            //   139: aconst_null    
            //   140: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.peerCertificates:[Ljava/security/cert/Certificate;
            //   143: aload_0        
            //   144: aconst_null    
            //   145: putfield        com/koushikdutta/async/http/cache/ResponseCacheMiddleware$Entry.localCertificates:[Ljava/security/cert/Certificate;
            //   148: iconst_2       
            //   149: anewarray       Ljava/io/Closeable;
            //   152: dup            
            //   153: iconst_0       
            //   154: aload_3        
            //   155: aastore        
            //   156: dup            
            //   157: iconst_1       
            //   158: aload_1        
            //   159: aastore        
            //   160: invokestatic    com/koushikdutta/async/util/StreamUtility.closeQuietly:([Ljava/io/Closeable;)V
            //   163: return         
            //   164: astore          4
            //   166: iconst_2       
            //   167: anewarray       Ljava/io/Closeable;
            //   170: dup            
            //   171: iconst_0       
            //   172: aload_2        
            //   173: aastore        
            //   174: dup            
            //   175: iconst_1       
            //   176: aload_1        
            //   177: aastore        
            //   178: invokestatic    com/koushikdutta/async/util/StreamUtility.closeQuietly:([Ljava/io/Closeable;)V
            //   181: aload           4
            //   183: athrow         
            //   184: astore          4
            //   186: aload_3        
            //   187: astore_2       
            //   188: goto            166
            //    Exceptions:
            //  throws java.io.IOException
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  6      18     164    166    Any
            //  18     148    184    191    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        private boolean isHttps() {
            return this.uri.startsWith("https://");
        }
        
        private Certificate[] readCertArray(final StrictLineReader strictLineReader) throws IOException {
            final int int1 = strictLineReader.readInt();
            Certificate[] array;
            if (int1 == -1) {
                array = null;
            }
            else {
                try {
                    final CertificateFactory instance = CertificateFactory.getInstance("X.509");
                    array = new Certificate[int1];
                    for (int i = 0; i < array.length; ++i) {
                        array[i] = instance.generateCertificate(new ByteArrayInputStream(Base64.decode(strictLineReader.readLine(), 0)));
                    }
                }
                catch (CertificateException ex) {
                    throw new IOException(ex.getMessage());
                }
            }
            return array;
        }
        
        private void writeCertArray(final Writer writer, final Certificate[] array) throws IOException {
            int i = 0;
            if (array == null) {
                writer.write("-1\n");
            }
            else {
                try {
                    writer.write(Integer.toString(array.length) + '\n');
                    while (i < array.length) {
                        writer.write(Base64.encodeToString(array[i].getEncoded(), 0) + '\n');
                        ++i;
                    }
                }
                catch (CertificateEncodingException ex) {
                    throw new IOException(ex.getMessage());
                }
            }
        }
        
        public boolean matches(final Uri uri, final String s, final Map<String, List<String>> map) {
            return this.uri.equals(uri.toString()) && this.requestMethod.equals(s) && new ResponseHeaders(uri, this.responseHeaders).varyMatches(this.varyHeaders.toMultimap(), map);
        }
        
        public void writeTo(final EntryEditor entryEditor) throws IOException {
            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(entryEditor.newOutputStream(0), Charsets.UTF_8));
            bufferedWriter.write(this.uri + '\n');
            bufferedWriter.write(this.requestMethod + '\n');
            bufferedWriter.write(Integer.toString(this.varyHeaders.length()) + '\n');
            for (int i = 0; i < this.varyHeaders.length(); ++i) {
                bufferedWriter.write(this.varyHeaders.getFieldName(i) + ": " + this.varyHeaders.getValue(i) + '\n');
            }
            bufferedWriter.write(this.responseHeaders.getStatusLine() + '\n');
            bufferedWriter.write(Integer.toString(this.responseHeaders.length()) + '\n');
            for (int j = 0; j < this.responseHeaders.length(); ++j) {
                bufferedWriter.write(this.responseHeaders.getFieldName(j) + ": " + this.responseHeaders.getValue(j) + '\n');
            }
            if (this.isHttps()) {
                bufferedWriter.write(10);
                bufferedWriter.write(this.cipherSuite + '\n');
                this.writeCertArray(bufferedWriter, this.peerCertificates);
                this.writeCertArray(bufferedWriter, this.localCertificates);
            }
            bufferedWriter.close();
        }
    }
    
    static class EntryCacheResponse extends CacheResponse
    {
        private final Entry entry;
        private final FileInputStream snapshot;
        
        public EntryCacheResponse(final Entry entry, final FileInputStream snapshot) {
            this.entry = entry;
            this.snapshot = snapshot;
        }
        
        @Override
        public FileInputStream getBody() {
            return this.snapshot;
        }
        
        @Override
        public Map<String, List<String>> getHeaders() {
            return this.entry.responseHeaders.toMultimap();
        }
    }
    
    class EntryEditor
    {
        boolean done;
        String key;
        FileOutputStream[] outs;
        File[] temps;
        
        public EntryEditor(final String key) {
            this.key = key;
            this.temps = ResponseCacheMiddleware.this.cache.getTempFiles(2);
            this.outs = new FileOutputStream[2];
        }
        
        void abort() {
            StreamUtility.closeQuietly((Closeable[])this.outs);
            FileCache.removeFiles(this.temps);
            if (!this.done) {
                ResponseCacheMiddleware.this.writeAbortCount++;
                this.done = true;
            }
        }
        
        void commit() {
            StreamUtility.closeQuietly((Closeable[])this.outs);
            if (!this.done) {
                ResponseCacheMiddleware.this.cache.commitTempFiles(this.key, this.temps);
                ResponseCacheMiddleware.this.writeSuccessCount++;
                this.done = true;
            }
        }
        
        FileOutputStream newOutputStream(final int n) throws IOException {
            if (this.outs[n] == null) {
                this.outs[n] = new FileOutputStream(this.temps[n]);
            }
            return this.outs[n];
        }
    }
}
