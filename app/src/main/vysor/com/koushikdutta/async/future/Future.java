// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

public interface Future<T> extends Cancellable, java.util.concurrent.Future<T>
{
    Future<T> setCallback(final FutureCallback<T> p0);
    
     <C extends FutureCallback<T>> C then(final C p0);
    
    T tryGet();
    
    Exception tryGetException();
}
