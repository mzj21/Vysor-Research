// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;

public interface AsyncHttpRequestBody<T>
{
    T get();
    
    String getContentType();
    
    int length();
    
    void parse(final DataEmitter p0, final CompletedCallback p1);
    
    boolean readFullyOnRequest();
    
    void write(final AsyncHttpRequest p0, final DataSink p1, final CompletedCallback p2);
}
