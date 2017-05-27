// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

public class SimpleCancellable implements DependentCancellable
{
    public static final Cancellable COMPLETED;
    boolean cancelled;
    boolean complete;
    private Cancellable parent;
    
    static {
        COMPLETED = new SimpleCancellable() {
            {
                this.setComplete();
            }
        };
    }
    
    @Override
    public boolean cancel() {
        boolean b = true;
        synchronized (this) {
            if (this.complete) {
                // monitorexit(this)
                b = false;
                return b;
            }
            if (this.cancelled) {
                return b;
            }
        }
        this.cancelled = true;
        final Cancellable parent = this.parent;
        this.parent = null;
        // monitorexit(this)
        if (parent != null) {
            parent.cancel();
        }
        this.cancelCleanup();
        this.cleanup();
        return b;
    }
    
    protected void cancelCleanup() {
    }
    
    protected void cleanup() {
    }
    
    protected void completeCleanup() {
    }
    
    @Override
    public boolean isCancelled() {
        while (true) {
            synchronized (this) {
                if (this.cancelled) {
                    return true;
                }
                if (this.parent != null && this.parent.isCancelled()) {
                    return true;
                }
                return false;
            }
            return true;
            b = false;
            return b;
        }
    }
    
    @Override
    public boolean isDone() {
        return this.complete;
    }
    
    public Cancellable reset() {
        this.cancel();
        this.complete = false;
        this.cancelled = false;
        return this;
    }
    
    public boolean setComplete() {
        boolean b = true;
        // monitorexit(this)
        Label_0049: {
            synchronized (this) {
                if (this.cancelled) {
                    // monitorexit(this)
                    b = false;
                    return b;
                }
                if (!this.complete) {
                    break Label_0049;
                }
                assert false;
            }
            return b;
        }
        this.complete = true;
        this.parent = null;
        // monitorexit(this)
        this.completeCleanup();
        this.cleanup();
        return b;
    }
    
    @Override
    public SimpleCancellable setParent(final Cancellable parent) {
        synchronized (this) {
            if (!this.isDone()) {
                this.parent = parent;
            }
            return this;
        }
    }
}
