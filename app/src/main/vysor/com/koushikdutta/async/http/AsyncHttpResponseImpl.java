// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.AsyncServer;
import java.nio.charset.Charset;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.FilteredDataEmitter;

abstract class AsyncHttpResponseImpl extends FilteredDataEmitter implements AsyncSocket, ResponseHead, AsyncHttpResponse
{
    int code;
    boolean mCompleted;
    private boolean mFirstWrite;
    protected Headers mHeaders;
    private CompletedCallback mReporter;
    private AsyncHttpRequest mRequest;
    DataSink mSink;
    private AsyncSocket mSocket;
    String message;
    String protocol;
    
    public AsyncHttpResponseImpl(final AsyncHttpRequest mRequest) {
        this.mReporter = new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                if (ex != null && !AsyncHttpResponseImpl.this.mCompleted) {
                    AsyncHttpResponseImpl.this.report(new ConnectionClosedException("connection closed before response completed.", ex));
                }
                else {
                    AsyncHttpResponseImpl.this.report(ex);
                }
            }
        };
        this.mCompleted = false;
        this.mFirstWrite = true;
        this.mRequest = mRequest;
    }
    
    private void assertContent() {
        if (this.mFirstWrite) {
            this.mFirstWrite = false;
            assert this.mRequest.getHeaders().get("Content-Type") != null;
            assert HttpUtil.contentLength(this.mRequest.getHeaders()) != -1;
        }
    }
    
    private void terminate() {
        this.mSocket.setDataCallback(new NullDataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                super.onDataAvailable(dataEmitter, list);
                AsyncHttpResponseImpl.this.mSocket.close();
            }
        });
    }
    
    @Override
    public String charset() {
        final Multimap semicolonDelimited = Multimap.parseSemicolonDelimited(this.headers().get("Content-Type"));
        if (semicolonDelimited == null) {
            return null;
        }
        String string = semicolonDelimited.getString("charset");
        if (string == null || !Charset.isSupported(string)) {
            return null;
        }
        return string;
        string = null;
        return string;
    }
    
    @Override
    public void close() {
        super.close();
        this.terminate();
    }
    
    @Override
    public int code() {
        return this.code;
    }
    
    @Override
    public ResponseHead code(final int code) {
        this.code = code;
        return this;
    }
    
    @Override
    public DataEmitter emitter() {
        return this.getDataEmitter();
    }
    
    @Override
    public ResponseHead emitter(final DataEmitter dataEmitter) {
        this.setDataEmitter(dataEmitter);
        return this;
    }
    
    @Override
    public void end() {
        throw new AssertionError((Object)"end called?");
    }
    
    @Override
    public CompletedCallback getClosedCallback() {
        return this.mSink.getClosedCallback();
    }
    
    @Override
    public AsyncHttpRequest getRequest() {
        return this.mRequest;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.mSocket.getServer();
    }
    
    @Override
    public WritableCallback getWriteableCallback() {
        return this.mSink.getWriteableCallback();
    }
    
    @Override
    public ResponseHead headers(final Headers mHeaders) {
        this.mHeaders = mHeaders;
        return this;
    }
    
    @Override
    public Headers headers() {
        return this.mHeaders;
    }
    
    @Override
    public boolean isOpen() {
        return this.mSink.isOpen();
    }
    
    @Override
    public ResponseHead message(final String message) {
        this.message = message;
        return this;
    }
    
    @Override
    public String message() {
        return this.message;
    }
    
    protected void onHeadersReceived() {
    }
    
    protected void onHeadersSent() {
        final AsyncHttpRequestBody body = this.mRequest.getBody();
        if (body != null) {
            body.write(this.mRequest, this, new CompletedCallback() {
                @Override
                public void onCompleted(final Exception ex) {
                    AsyncHttpResponseImpl.this.onRequestCompleted(ex);
                }
            });
        }
        else {
            this.onRequestCompleted(null);
        }
    }
    
    protected void onRequestCompleted(final Exception ex) {
    }
    
    @Override
    public ResponseHead protocol(final String protocol) {
        this.protocol = protocol;
        return this;
    }
    
    @Override
    public String protocol() {
        return this.protocol;
    }
    
    @Override
    protected void report(final Exception ex) {
        super.report(ex);
        this.terminate();
        this.mSocket.setWriteableCallback(null);
        this.mSocket.setClosedCallback(null);
        this.mSocket.setEndCallback(null);
        this.mCompleted = true;
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback closedCallback) {
        this.mSink.setClosedCallback(closedCallback);
    }
    
    void setSocket(final AsyncSocket mSocket) {
        this.mSocket = mSocket;
        if (this.mSocket != null) {
            this.mSocket.setEndCallback(this.mReporter);
        }
    }
    
    @Override
    public void setWriteableCallback(final WritableCallback writeableCallback) {
        this.mSink.setWriteableCallback(writeableCallback);
    }
    
    @Override
    public DataSink sink() {
        return this.mSink;
    }
    
    @Override
    public ResponseHead sink(final DataSink mSink) {
        this.mSink = mSink;
        return this;
    }
    
    @Override
    public AsyncSocket socket() {
        return this.mSocket;
    }
    
    @Override
    public String toString() {
        String s;
        if (this.mHeaders == null) {
            s = super.toString();
        }
        else {
            s = this.mHeaders.toPrefixString(this.protocol + " " + this.code + " " + this.message);
        }
        return s;
    }
    
    @Override
    public void write(final ByteBufferList list) {
        this.assertContent();
        this.mSink.write(list);
    }
}
