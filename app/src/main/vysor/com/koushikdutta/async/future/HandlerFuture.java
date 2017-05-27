// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

import android.os.Looper;
import android.os.Handler;

public class HandlerFuture<T> extends SimpleFuture<T>
{
    Handler handler;
    
    public HandlerFuture() {
        Looper looper = Looper.myLooper();
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.handler = new Handler(looper);
    }
    
    @Override
    public SimpleFuture<T> setCallback(final FutureCallback<T> futureCallback) {
        return super.setCallback(new FutureCallback<T>() {
            @Override
            public void onCompleted(final Exception ex, final T t) {
                if (Looper.myLooper() == HandlerFuture.this.handler.getLooper()) {
                    futureCallback.onCompleted(ex, t);
                }
                else {
                    HandlerFuture.this.handler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            FutureCallback.this.onCompleted(ex, t);
                        }
                    });
                }
            }
        });
    }
}
