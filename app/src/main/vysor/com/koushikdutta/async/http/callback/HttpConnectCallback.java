// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.callback;

import com.koushikdutta.async.http.AsyncHttpResponse;

public interface HttpConnectCallback
{
    void onConnectCompleted(final Exception p0, final AsyncHttpResponse p1);
}
