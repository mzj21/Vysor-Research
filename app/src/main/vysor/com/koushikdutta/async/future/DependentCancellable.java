// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

public interface DependentCancellable extends Cancellable
{
    DependentCancellable setParent(final Cancellable p0);
}
