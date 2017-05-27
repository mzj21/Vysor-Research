// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

public abstract class OptionalPendingResult<R extends Result> extends PendingResult<R>
{
    public abstract R get();
    
    public abstract boolean isDone();
}
