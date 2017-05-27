// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.parser.JSONArrayParser;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import org.json.JSONArray;

public class JSONArrayBody implements AsyncHttpRequestBody<JSONArray>
{
    public static final String CONTENT_TYPE = "application/json";
    JSONArray json;
    byte[] mBodyBytes;
    
    public JSONArrayBody() {
    }
    
    public JSONArrayBody(final JSONArray json) {
        this();
        this.json = json;
    }
    
    @Override
    public JSONArray get() {
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
        new JSONArrayParser().parse(dataEmitter).setCallback(new FutureCallback<JSONArray>() {
            @Override
            public void onCompleted(final Exception ex, final JSONArray json) {
                JSONArrayBody.this.json = json;
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
