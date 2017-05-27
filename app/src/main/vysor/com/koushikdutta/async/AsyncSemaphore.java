// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;

public class AsyncSemaphore
{
    Semaphore semaphore;
    
    public AsyncSemaphore() {
        this.semaphore = new Semaphore(0);
    }
    
    public void acquire() throws InterruptedException {
        final ThreadQueue orCreateThreadQueue = ThreadQueue.getOrCreateThreadQueue(Thread.currentThread());
        final AsyncSemaphore waiter = orCreateThreadQueue.waiter;
        orCreateThreadQueue.waiter = this;
        final Semaphore queueSemaphore = orCreateThreadQueue.queueSemaphore;
        try {
            if (!this.semaphore.tryAcquire()) {
                while (true) {
                    final Runnable remove = orCreateThreadQueue.remove();
                    if (remove == null) {
                        queueSemaphore.acquire(Math.max(1, queueSemaphore.availablePermits()));
                        if (this.semaphore.tryAcquire()) {
                            break;
                        }
                        continue;
                    }
                    else {
                        remove.run();
                    }
                }
                orCreateThreadQueue.waiter = waiter;
            }
        }
        finally {
            orCreateThreadQueue.waiter = waiter;
        }
    }
    
    public void release() {
        this.semaphore.release();
        ThreadQueue.release(this);
    }
    
    public boolean tryAcquire(final long n, final TimeUnit timeUnit) throws InterruptedException {
        while (true) {
            final long convert = TimeUnit.MILLISECONDS.convert(n, timeUnit);
            final ThreadQueue orCreateThreadQueue = ThreadQueue.getOrCreateThreadQueue(Thread.currentThread());
            final AsyncSemaphore waiter = orCreateThreadQueue.waiter;
            orCreateThreadQueue.waiter = this;
            final Semaphore queueSemaphore = orCreateThreadQueue.queueSemaphore;
            while (true) {
                long currentTimeMillis = 0L;
                Label_0143: {
                    try {
                        boolean b;
                        if (this.semaphore.tryAcquire()) {
                            b = true;
                        }
                        else {
                            currentTimeMillis = System.currentTimeMillis();
                            while (true) {
                                final Runnable remove = orCreateThreadQueue.remove();
                                if (remove == null) {
                                    break;
                                }
                                remove.run();
                            }
                            if (queueSemaphore.tryAcquire(Math.max(1, queueSemaphore.availablePermits()), convert, TimeUnit.MILLISECONDS)) {
                                break Label_0143;
                            }
                            orCreateThreadQueue.waiter = waiter;
                            b = false;
                        }
                        return b;
                    }
                    finally {
                        orCreateThreadQueue.waiter = waiter;
                    }
                }
                if (this.semaphore.tryAcquire()) {
                    final boolean b = true;
                    orCreateThreadQueue.waiter = waiter;
                    return b;
                }
                if (System.currentTimeMillis() - currentTimeMillis >= convert) {
                    orCreateThreadQueue.waiter = waiter;
                    return false;
                }
                continue;
            }
        }
    }
}
