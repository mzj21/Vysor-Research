// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

public abstract class TransformFuture<T, F> extends SimpleFuture<T> implements FutureCallback<F>
{
    protected void error(final Exception complete) {
        this.setComplete(complete);
    }
    
    @Override
    public void onCompleted(final Exception ex, final F n) {
        if (!this.isCancelled()) {
            if (ex != null) {
                this.error(ex);
            }
            else {
                try {
                    this.transform(n);
                }
                catch (Exception ex2) {
                    this.error(ex2);
                }
            }
        }
    }
    
    protected abstract void transform(final F p0) throws Exception;
}
