// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.callback;

import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.callback.ResultCallback;

public interface RequestCallback<T> extends ResultCallback<AsyncHttpResponse, T>
{
    void onConnect(final AsyncHttpResponse p0);
    
    void onProgress(final AsyncHttpResponse p0, final long p1, final long p2);
}
