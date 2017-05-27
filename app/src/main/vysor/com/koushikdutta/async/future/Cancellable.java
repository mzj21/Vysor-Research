// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

public interface Cancellable
{
    boolean cancel();
    
    boolean isCancelled();
    
    boolean isDone();
}
