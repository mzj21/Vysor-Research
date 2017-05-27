// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.callback;

public interface ResultCallback<S, T>
{
    void onCompleted(final Exception p0, final S p1, final T p2);
}
