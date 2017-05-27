// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

import java.util.Iterator;
import java.util.ArrayList;

public class MultiFuture<T> extends SimpleFuture<T>
{
    final FutureCallback<T> callback;
    ArrayList<FutureCallback<T>> callbacks;
    
    public MultiFuture() {
        this.callback = new FutureCallback<T>() {
            @Override
            public void onCompleted(final Exception ex, final T t) {
                final ArrayList<FutureCallback<T>> callbacks;
                synchronized (MultiFuture.this) {
                    callbacks = MultiFuture.this.callbacks;
                    MultiFuture.this.callbacks = null;
                    // monitorexit(this.this$0)
                    if (callbacks == null) {
                        return;
                    }
                }
                final Iterator<FutureCallback<T>> iterator = callbacks.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onCompleted(ex, t);
                }
            }
        };
    }
    
    @Override
    public MultiFuture<T> setCallback(final FutureCallback<T> futureCallback) {
        synchronized (this) {
            if (this.callbacks == null) {
                this.callbacks = new ArrayList<FutureCallback<T>>();
            }
            this.callbacks.add(futureCallback);
            // monitorexit(this)
            super.setCallback(this.callback);
            return this;
        }
    }
}
