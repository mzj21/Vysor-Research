// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

import java.util.concurrent.ExecutorService;

public class FutureThread<T> extends SimpleFuture<T>
{
    public FutureThread(final FutureRunnable<T> futureRunnable) {
        this(futureRunnable, "FutureThread");
    }
    
    public FutureThread(final FutureRunnable<T> futureRunnable, final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FutureThread.this.setComplete(futureRunnable.run());
                }
                catch (Exception complete) {
                    FutureThread.this.setComplete(complete);
                }
            }
        }, s).start();
    }
    
    public FutureThread(final ExecutorService executorService, final FutureRunnable<T> futureRunnable) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    FutureThread.this.setComplete(futureRunnable.run());
                }
                catch (Exception complete) {
                    FutureThread.this.setComplete(complete);
                }
            }
        });
    }
}
