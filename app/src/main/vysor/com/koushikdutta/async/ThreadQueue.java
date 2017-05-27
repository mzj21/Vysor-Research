// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.util.Iterator;
import java.util.concurrent.Semaphore;
import java.util.WeakHashMap;
import java.util.LinkedList;

public class ThreadQueue extends LinkedList<Runnable>
{
    private static final WeakHashMap<Thread, ThreadQueue> mThreadQueues;
    Semaphore queueSemaphore;
    AsyncSemaphore waiter;
    
    static {
        mThreadQueues = new WeakHashMap<Thread, ThreadQueue>();
    }
    
    public ThreadQueue() {
        this.queueSemaphore = new Semaphore(0);
    }
    
    static ThreadQueue getOrCreateThreadQueue(final Thread thread) {
        synchronized (ThreadQueue.mThreadQueues) {
            ThreadQueue threadQueue = ThreadQueue.mThreadQueues.get(thread);
            if (threadQueue == null) {
                threadQueue = new ThreadQueue();
                ThreadQueue.mThreadQueues.put(thread, threadQueue);
            }
            return threadQueue;
        }
    }
    
    static void release(final AsyncSemaphore asyncSemaphore) {
        synchronized (ThreadQueue.mThreadQueues) {
            for (final ThreadQueue threadQueue : ThreadQueue.mThreadQueues.values()) {
                if (threadQueue.waiter == asyncSemaphore) {
                    threadQueue.queueSemaphore.release();
                }
            }
        }
    }
    // monitorexit(weakHashMap)
    
    @Override
    public boolean add(final Runnable runnable) {
        synchronized (this) {
            return super.add(runnable);
        }
    }
    
    @Override
    public Runnable remove() {
        Runnable runnable;
        synchronized (this) {
            if (this.isEmpty()) {
                // monitorexit(this)
                runnable = null;
            }
            else {
                runnable = super.remove();
            }
        }
        return runnable;
    }
    
    @Override
    public boolean remove(final Object o) {
        synchronized (this) {
            return super.remove(o);
        }
    }
}
