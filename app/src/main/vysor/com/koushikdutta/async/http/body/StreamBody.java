// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import java.io.InputStream;

public class StreamBody implements AsyncHttpRequestBody<InputStream>
{
    public static final String CONTENT_TYPE = "application/binary";
    String contentType;
    int length;
    InputStream stream;
    
    public StreamBody(final InputStream stream, final int length) {
        this.contentType = "application/binary";
        this.stream = stream;
        this.length = length;
    }
    
    @Override
    public InputStream get() {
        return this.stream;
    }
    
    @Override
    public String getContentType() {
        return this.contentType;
    }
    
    @Override
    public int length() {
        return this.length;
    }
    
    @Override
    public void parse(final DataEmitter dataEmitter, final CompletedCallback completedCallback) {
        throw new AssertionError((Object)"not implemented");
    }
    
    @Override
    public boolean readFullyOnRequest() {
        throw new AssertionError((Object)"not implemented");
    }
    
    public StreamBody setContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    @Override
    public void write(final AsyncHttpRequest asyncHttpRequest, final DataSink dataSink, final CompletedCallback completedCallback) {
        final InputStream stream = this.stream;
        long n;
        if (this.length < 0) {
            n = 2147483647L;
        }
        else {
            n = this.length;
        }
        Util.pump(stream, n, dataSink, completedCallback);
    }
}
