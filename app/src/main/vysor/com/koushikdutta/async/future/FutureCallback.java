// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

public interface FutureCallback<T>
{
    void onCompleted(final Exception p0, final T p1);
}
