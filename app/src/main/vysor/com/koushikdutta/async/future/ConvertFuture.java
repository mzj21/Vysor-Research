// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

public abstract class ConvertFuture<T, F> extends TransformFuture<T, F>
{
    protected abstract Future<T> convert(final F p0) throws Exception;
    
    @Override
    protected final void transform(final F n) throws Exception {
        this.setComplete(this.convert(n));
    }
}
