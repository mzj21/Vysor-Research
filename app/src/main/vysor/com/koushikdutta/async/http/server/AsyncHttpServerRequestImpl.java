// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.server;

import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.http.HttpUtil;
import com.koushikdutta.async.http.Protocol;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.http.Headers;
import java.util.regex.Matcher;
import com.koushikdutta.async.LineEmitter;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.FilteredDataEmitter;

public abstract class AsyncHttpServerRequestImpl extends FilteredDataEmitter implements CompletedCallback, AsyncHttpServerRequest
{
    AsyncHttpRequestBody mBody;
    LineEmitter.StringCallback mHeaderCallback;
    Matcher mMatcher;
    private Headers mRawHeaders;
    private CompletedCallback mReporter;
    AsyncSocket mSocket;
    String method;
    private String statusLine;
    
    public AsyncHttpServerRequestImpl() {
        this.mRawHeaders = new Headers();
        this.mReporter = new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                AsyncHttpServerRequestImpl.this.onCompleted(ex);
            }
        };
        this.mHeaderCallback = new LineEmitter.StringCallback() {
            @Override
            public void onStringAvailable(final String s) {
                try {
                    if (AsyncHttpServerRequestImpl.this.statusLine == null) {
                        AsyncHttpServerRequestImpl.this.statusLine = s;
                        if (!AsyncHttpServerRequestImpl.this.statusLine.contains("HTTP/")) {
                            AsyncHttpServerRequestImpl.this.onNotHttp();
                            AsyncHttpServerRequestImpl.this.mSocket.setDataCallback(null);
                        }
                        return;
                    }
                    else if (!"\r".equals(s)) {
                        AsyncHttpServerRequestImpl.this.mRawHeaders.addLine(s);
                        return;
                    }
                }
                catch (Exception ex) {
                    AsyncHttpServerRequestImpl.this.onCompleted(ex);
                    return;
                }
                final DataEmitter bodyDecoder = HttpUtil.getBodyDecoder(AsyncHttpServerRequestImpl.this.mSocket, Protocol.HTTP_1_1, AsyncHttpServerRequestImpl.this.mRawHeaders, true);
                AsyncHttpServerRequestImpl.this.mBody = HttpUtil.getBody(bodyDecoder, AsyncHttpServerRequestImpl.this.mReporter, AsyncHttpServerRequestImpl.this.mRawHeaders);
                if (AsyncHttpServerRequestImpl.this.mBody == null) {
                    AsyncHttpServerRequestImpl.this.mBody = AsyncHttpServerRequestImpl.this.onUnknownBody(AsyncHttpServerRequestImpl.this.mRawHeaders);
                    if (AsyncHttpServerRequestImpl.this.mBody == null) {
                        AsyncHttpServerRequestImpl.this.mBody = new UnknownRequestBody(AsyncHttpServerRequestImpl.this.mRawHeaders.get("Content-Type"));
                    }
                }
                AsyncHttpServerRequestImpl.this.mBody.parse(bodyDecoder, AsyncHttpServerRequestImpl.this.mReporter);
                AsyncHttpServerRequestImpl.this.onHeadersReceived();
            }
        };
    }
    
    @Override
    public AsyncHttpRequestBody getBody() {
        return this.mBody;
    }
    
    @Override
    public DataCallback getDataCallback() {
        return this.mSocket.getDataCallback();
    }
    
    @Override
    public Headers getHeaders() {
        return this.mRawHeaders;
    }
    
    @Override
    public Matcher getMatcher() {
        return this.mMatcher;
    }
    
    @Override
    public String getMethod() {
        return this.method;
    }
    
    @Override
    public AsyncSocket getSocket() {
        return this.mSocket;
    }
    
    public String getStatusLine() {
        return this.statusLine;
    }
    
    @Override
    public boolean isChunked() {
        return this.mSocket.isChunked();
    }
    
    @Override
    public boolean isPaused() {
        return this.mSocket.isPaused();
    }
    
    @Override
    public void onCompleted(final Exception ex) {
        this.report(ex);
    }
    
    protected abstract void onHeadersReceived();
    
    protected void onNotHttp() {
        System.out.println("not http!");
    }
    
    protected AsyncHttpRequestBody onUnknownBody(final Headers headers) {
        return null;
    }
    
    @Override
    public void pause() {
        this.mSocket.pause();
    }
    
    @Override
    public void resume() {
        this.mSocket.resume();
    }
    
    @Override
    public void setDataCallback(final DataCallback dataCallback) {
        this.mSocket.setDataCallback(dataCallback);
    }
    
    void setSocket(final AsyncSocket mSocket) {
        this.mSocket = mSocket;
        final LineEmitter dataCallback = new LineEmitter();
        this.mSocket.setDataCallback(dataCallback);
        dataCallback.setLineCallback(this.mHeaderCallback);
        this.mSocket.setEndCallback(new NullCompletedCallback());
    }
    
    @Override
    public String toString() {
        String s;
        if (this.mRawHeaders == null) {
            s = super.toString();
        }
        else {
            s = this.mRawHeaders.toPrefixString(this.statusLine);
        }
        return s;
    }
}
