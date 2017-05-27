// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.parser.JSONObjectParser;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import org.json.JSONObject;

public class JSONObjectBody implements AsyncHttpRequestBody<JSONObject>
{
    public static final String CONTENT_TYPE = "application/json";
    JSONObject json;
    byte[] mBodyBytes;
    
    public JSONObjectBody() {
    }
    
    public JSONObjectBody(final JSONObject json) {
        this();
        this.json = json;
    }
    
    @Override
    public JSONObject get() {
        return this.json;
    }
    
    @Override
    public String getContentType() {
        return "application/json";
    }
    
    @Override
    public int length() {
        this.mBodyBytes = this.json.toString().getBytes();
        return this.mBodyBytes.length;
    }
    
    @Override
    public void parse(final DataEmitter dataEmitter, final CompletedCallback completedCallback) {
        new JSONObjectParser().parse(dataEmitter).setCallback(new FutureCallback<JSONObject>() {
            @Override
            public void onCompleted(final Exception ex, final JSONObject json) {
                JSONObjectBody.this.json = json;
                completedCallback.onCompleted(ex);
            }
        });
    }
    
    @Override
    public boolean readFullyOnRequest() {
        return true;
    }
    
    @Override
    public void write(final AsyncHttpRequest asyncHttpRequest, final DataSink dataSink, final CompletedCallback completedCallback) {
        Util.writeAll(dataSink, this.mBodyBytes, completedCallback);
    }
}
