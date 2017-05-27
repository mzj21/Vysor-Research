// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.parser.StringParser;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;

public class StringBody implements AsyncHttpRequestBody<String>
{
    public static final String CONTENT_TYPE = "text/plain";
    byte[] mBodyBytes;
    String string;
    
    public StringBody() {
    }
    
    public StringBody(final String string) {
        this();
        this.string = string;
    }
    
    @Override
    public String get() {
        return this.toString();
    }
    
    @Override
    public String getContentType() {
        return "text/plain";
    }
    
    @Override
    public int length() {
        if (this.mBodyBytes == null) {
            this.mBodyBytes = this.string.getBytes();
        }
        return this.mBodyBytes.length;
    }
    
    @Override
    public void parse(final DataEmitter dataEmitter, final CompletedCallback completedCallback) {
        new StringParser().parse(dataEmitter).setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(final Exception ex, final String string) {
                StringBody.this.string = string;
                completedCallback.onCompleted(ex);
            }
        });
    }
    
    @Override
    public boolean readFullyOnRequest() {
        return true;
    }
    
    @Override
    public String toString() {
        return this.string;
    }
    
    @Override
    public void write(final AsyncHttpRequest asyncHttpRequest, final DataSink dataSink, final CompletedCallback completedCallback) {
        if (this.mBodyBytes == null) {
            this.mBodyBytes = this.string.getBytes();
        }
        Util.writeAll(dataSink, this.mBodyBytes, completedCallback);
    }
}
