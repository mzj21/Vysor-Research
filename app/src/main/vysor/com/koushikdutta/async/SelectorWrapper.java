// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.util.concurrent.TimeUnit;
import java.nio.channels.SelectionKey;
import java.util.Set;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.nio.channels.Selector;

public class SelectorWrapper
{
    boolean isWaking;
    private Selector selector;
    Semaphore semaphore;
    
    public SelectorWrapper(final Selector selector) {
        this.semaphore = new Semaphore(0);
        this.selector = selector;
    }
    
    public void close() throws IOException {
        this.selector.close();
    }
    
    public Selector getSelector() {
        return this.selector;
    }
    
    public boolean isOpen() {
        return this.selector.isOpen();
    }
    
    public Set<SelectionKey> keys() {
        return this.selector.keys();
    }
    
    public void select() throws IOException {
        this.select(0L);
    }
    
    public void select(final long n) throws IOException {
        try {
            this.semaphore.drainPermits();
            this.selector.select(n);
        }
        finally {
            this.semaphore.release(Integer.MAX_VALUE);
        }
    }
    
    public int selectNow() throws IOException {
        return this.selector.selectNow();
    }
    
    public Set<SelectionKey> selectedKeys() {
        return this.selector.selectedKeys();
    }
    
    public void wakeupOnce() {
        int n = 1;
        if (this.semaphore.tryAcquire()) {
            n = 0;
        }
        this.selector.wakeup();
        if (n == 0) {
            synchronized (this) {
                if (this.isWaking) {
                    return;
                }
            }
            this.isWaking = true;
            // monitorexit(this)
            int n2 = 0;
            while (true) {
                Label_0121: {
                    if (n2 >= 100) {
                        break Label_0121;
                    }
                    try {
                        try {
                            if (this.semaphore.tryAcquire(10L, TimeUnit.MILLISECONDS)) {
                                synchronized (this) {
                                    this.isWaking = false;
                                }
                            }
                        }
                        catch (InterruptedException ex) {}
                        this.selector.wakeup();
                        ++n2;
                        continue;
                        synchronized (this) {
                            this.isWaking = false;
                        }
                    }
                    finally {
                        synchronized (this) {
                            this.isWaking = false;
                        }
                    }
                }
            }
        }
    }
}
