// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

import java.util.LinkedList;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.ContinuationCallback;

public class Continuation extends SimpleCancellable implements ContinuationCallback, Cancellable, Runnable
{
    CompletedCallback callback;
    Runnable cancelCallback;
    private boolean inNext;
    LinkedList<ContinuationCallback> mCallbacks;
    boolean started;
    private boolean waiting;
    
    public Continuation() {
        this(null);
    }
    
    public Continuation(final CompletedCallback completedCallback) {
        this(completedCallback, null);
    }
    
    public Continuation(final CompletedCallback callback, final Runnable cancelCallback) {
        this.mCallbacks = new LinkedList<ContinuationCallback>();
        this.cancelCallback = cancelCallback;
        this.callback = callback;
    }
    
    private ContinuationCallback hook(final ContinuationCallback continuationCallback) {
        if (continuationCallback instanceof DependentCancellable) {
            ((DependentCancellable)continuationCallback).setParent(this);
        }
        return continuationCallback;
    }
    
    private void next() {
        if (!this.inNext) {
            while (this.mCallbacks.size() > 0 && !this.waiting && !this.isDone() && !this.isCancelled()) {
                final ContinuationCallback continuationCallback = this.mCallbacks.remove();
                try {
                    this.inNext = true;
                    this.waiting = true;
                    continuationCallback.onContinue(this, this.wrap());
                    continue;
                }
                catch (Exception ex) {
                    this.reportCompleted(ex);
                    continue;
                }
                finally {
                    this.inNext = false;
                }
                break;
            }
            if (!this.waiting && !this.isDone() && !this.isCancelled()) {
                this.reportCompleted(null);
            }
        }
    }
    
    private CompletedCallback wrap() {
        return new CompletedCallback() {
            boolean mThisCompleted;
            
            @Override
            public void onCompleted(final Exception ex) {
                if (!this.mThisCompleted) {
                    this.mThisCompleted = true;
                    assert Continuation.this.waiting;
                    Continuation.this.waiting = false;
                    if (ex == null) {
                        Continuation.this.next();
                    }
                    else {
                        Continuation.this.reportCompleted(ex);
                    }
                }
            }
        };
    }
    
    public Continuation add(final ContinuationCallback continuationCallback) {
        this.mCallbacks.add(this.hook(continuationCallback));
        return this;
    }
    
    public Continuation add(final DependentFuture dependentFuture) {
        dependentFuture.setParent(this);
        this.add(new ContinuationCallback() {
            @Override
            public void onContinue(final Continuation continuation, final CompletedCallback completedCallback) throws Exception {
                dependentFuture.get();
                completedCallback.onCompleted(null);
            }
        });
        return this;
    }
    
    @Override
    public boolean cancel() {
        boolean b;
        if (!super.cancel()) {
            b = false;
        }
        else {
            if (this.cancelCallback != null) {
                this.cancelCallback.run();
            }
            b = true;
        }
        return b;
    }
    
    public CompletedCallback getCallback() {
        return this.callback;
    }
    
    public Runnable getCancelCallback() {
        return this.cancelCallback;
    }
    
    public Continuation insert(final ContinuationCallback continuationCallback) {
        this.mCallbacks.add(0, this.hook(continuationCallback));
        return this;
    }
    
    @Override
    public void onContinue(final Continuation continuation, final CompletedCallback callback) throws Exception {
        this.setCallback(callback);
        this.start();
    }
    
    void reportCompleted(final Exception ex) {
        if (this.setComplete() && this.callback != null) {
            this.callback.onCompleted(ex);
        }
    }
    
    @Override
    public void run() {
        this.start();
    }
    
    public void setCallback(final CompletedCallback callback) {
        this.callback = callback;
    }
    
    public void setCancelCallback(final Cancellable cancellable) {
        if (cancellable == null) {
            this.cancelCallback = null;
        }
        else {
            this.cancelCallback = new Runnable() {
                @Override
                public void run() {
                    cancellable.cancel();
                }
            };
        }
    }
    
    public void setCancelCallback(final Runnable cancelCallback) {
        this.cancelCallback = cancelCallback;
    }
    
    public Continuation start() {
        if (this.started) {
            throw new IllegalStateException("already started");
        }
        this.started = true;
        this.next();
        return this;
    }
}
