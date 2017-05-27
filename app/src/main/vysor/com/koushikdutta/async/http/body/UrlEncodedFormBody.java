// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.koushikdutta.async.http.NameValuePair;
import java.util.List;
import com.koushikdutta.async.http.Multimap;

public class UrlEncodedFormBody implements AsyncHttpRequestBody<Multimap>
{
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private byte[] mBodyBytes;
    private Multimap mParameters;
    
    public UrlEncodedFormBody() {
    }
    
    public UrlEncodedFormBody(final Multimap mParameters) {
        this.mParameters = mParameters;
    }
    
    public UrlEncodedFormBody(final List<NameValuePair> list) {
        this.mParameters = new Multimap(list);
    }
    
    private void buildData() {
        int n = 1;
        final StringBuilder sb = new StringBuilder();
        try {
            for (final NameValuePair nameValuePair : this.mParameters) {
                if (nameValuePair.getValue() != null) {
                    if (n == 0) {
                        sb.append('&');
                    }
                    sb.append(URLEncoder.encode(nameValuePair.getName(), "UTF-8"));
                    sb.append('=');
                    sb.append(URLEncoder.encode(nameValuePair.getValue(), "UTF-8"));
                    n = 0;
                }
            }
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
        this.mBodyBytes = sb.toString().getBytes("UTF-8");
    }
    
    @Override
    public Multimap get() {
        return this.mParameters;
    }
    
    @Override
    public String getContentType() {
        return "application/x-www-form-urlencoded; charset=utf-8";
    }
    
    @Override
    public int length() {
        if (this.mBodyBytes == null) {
            this.buildData();
        }
        return this.mBodyBytes.length;
    }
    
    @Override
    public void parse(final DataEmitter dataEmitter, final CompletedCallback completedCallback) {
        final ByteBufferList list = new ByteBufferList();
        dataEmitter.setDataCallback(new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                list.get(list);
            }
        });
        dataEmitter.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                if (ex != null) {
                    completedCallback.onCompleted(ex);
                }
                else {
                    try {
                        UrlEncodedFormBody.this.mParameters = Multimap.parseUrlEncoded(list.readString());
                        completedCallback.onCompleted(null);
                    }
                    catch (Exception ex2) {
                        completedCallback.onCompleted(ex2);
                    }
                }
            }
        });
    }
    
    @Override
    public boolean readFullyOnRequest() {
        return true;
    }
    
    @Override
    public void write(final AsyncHttpRequest asyncHttpRequest, final DataSink dataSink, final CompletedCallback completedCallback) {
        if (this.mBodyBytes == null) {
            this.buildData();
        }
        Util.writeAll(dataSink, this.mBodyBytes, completedCallback);
    }
}
