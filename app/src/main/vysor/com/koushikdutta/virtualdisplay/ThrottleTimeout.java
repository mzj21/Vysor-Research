// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.virtualdisplay;

import java.util.Collection;
import com.koushikdutta.async.AsyncServer;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import com.koushikdutta.async.callback.ValueCallback;

public class ThrottleTimeout<T>
{
    ValueCallback<List<T>> callback;
    Object cancellable;
    long delay;
    Handlerish handlerish;
    ArrayList<T> values;
    
    public ThrottleTimeout(final Handler handler, final long delay, final ValueCallback<List<T>> callback) {
        this.values = new ArrayList<T>();
        this.delay = delay;
        this.callback = callback;
        this.handlerish = (Handlerish)new Handlerish() {
            @Override
            public void post(final Runnable runnable) {
                handler.post(runnable);
            }
            
            @Override
            public Object postDelayed(final Runnable runnable, final long n) {
                handler.postDelayed(runnable, n);
                return runnable;
            }
            
            @Override
            public void removeAllCallbacks(final Object o) {
                handler.removeCallbacks((Runnable)o);
            }
        };
    }
    
    public ThrottleTimeout(final AsyncServer asyncServer, final long delay, final ValueCallback<List<T>> callback) {
        this.values = new ArrayList<T>();
        this.delay = delay;
        this.callback = callback;
        this.handlerish = (Handlerish)new Handlerish() {
            @Override
            public void post(final Runnable runnable) {
                asyncServer.post(runnable);
            }
            
            @Override
            public Object postDelayed(final Runnable runnable, final long n) {
                return asyncServer.postDelayed(runnable, n);
            }
            
            @Override
            public void removeAllCallbacks(final Object o) {
                asyncServer.removeAllCallbacks(o);
            }
        };
    }
    
    public void postThrottled(final T t) {
        synchronized (this) {
            this.handlerish.post(new Runnable() {
                @Override
                public void run() {
                    ThrottleTimeout.this.values.add(t);
                    ThrottleTimeout.this.handlerish.removeAllCallbacks(ThrottleTimeout.this.cancellable);
                    ThrottleTimeout.this.cancellable = ThrottleTimeout.this.handlerish.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final ArrayList<T> list = new ArrayList<T>((Collection<? extends T>)ThrottleTimeout.this.values);
                            ThrottleTimeout.this.values.clear();
                            ThrottleTimeout.this.callback.onResult(list);
                        }
                    }, ThrottleTimeout.this.delay);
                }
            });
        }
    }
    
    private interface Handlerish
    {
        void post(final Runnable p0);
        
        Object postDelayed(final Runnable p0, final long p1);
        
        void removeAllCallbacks(final Object p0);
    }
}
