// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.server;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;

public class UnknownRequestBody implements AsyncHttpRequestBody<Void>
{
    DataEmitter emitter;
    int length;
    private String mContentType;
    
    public UnknownRequestBody(final DataEmitter emitter, final String mContentType, final int length) {
        this.length = -1;
        this.mContentType = mContentType;
        this.emitter = emitter;
        this.length = length;
    }
    
    public UnknownRequestBody(final String mContentType) {
        this.length = -1;
        this.mContentType = mContentType;
    }
    
    @Override
    public Void get() {
        return null;
    }
    
    @Override
    public String getContentType() {
        return this.mContentType;
    }
    
    public DataEmitter getEmitter() {
        return this.emitter;
    }
    
    @Override
    public int length() {
        return this.length;
    }
    
    @Override
    public void parse(final DataEmitter emitter, final CompletedCallback endCallback) {
        (this.emitter = emitter).setEndCallback(endCallback);
        emitter.setDataCallback(new DataCallback.NullDataCallback());
    }
    
    @Override
    public boolean readFullyOnRequest() {
        return false;
    }
    
    @Deprecated
    public void setCallbacks(final DataCallback dataCallback, final CompletedCallback endCallback) {
        this.emitter.setEndCallback(endCallback);
        this.emitter.setDataCallback(dataCallback);
    }
    
    @Override
    public void write(final AsyncHttpRequest asyncHttpRequest, final DataSink dataSink, final CompletedCallback completedCallback) {
        Util.pump(this.emitter, dataSink, completedCallback);
        if (this.emitter.isPaused()) {
            this.emitter.resume();
        }
    }
}
