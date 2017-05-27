// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.future;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import com.koushikdutta.async.AsyncSemaphore;

public class SimpleFuture<T> extends SimpleCancellable implements DependentFuture<T>
{
    FutureCallback<T> callback;
    Exception exception;
    T result;
    boolean silent;
    AsyncSemaphore waiter;
    
    public SimpleFuture() {
    }
    
    public SimpleFuture(final Exception complete) {
        this.setComplete(complete);
    }
    
    public SimpleFuture(final T complete) {
        this.setComplete(complete);
    }
    
    private boolean cancelInternal(final boolean silent) {
        boolean b;
        if (!super.cancel()) {
            b = false;
        }
        else {
            synchronized (this) {
                this.exception = new CancellationException();
                this.releaseWaiterLocked();
                final FutureCallback<T> handleCompleteLocked = this.handleCompleteLocked();
                this.silent = silent;
                // monitorexit(this)
                this.handleCallbackUnlocked(handleCompleteLocked);
                b = true;
            }
        }
        return b;
    }
    
    private T getResultOrThrow() throws ExecutionException {
        if (this.exception != null) {
            throw new ExecutionException(this.exception);
        }
        return this.result;
    }
    
    private void handleCallbackUnlocked(final FutureCallback<T> futureCallback) {
        if (futureCallback != null && !this.silent) {
            futureCallback.onCompleted(this.exception, this.result);
        }
    }
    
    private FutureCallback<T> handleCompleteLocked() {
        final FutureCallback<T> callback = this.callback;
        this.callback = null;
        return callback;
    }
    
    @Override
    public boolean cancel() {
        return this.cancelInternal(this.silent);
    }
    
    @Override
    public boolean cancel(final boolean b) {
        return this.cancel();
    }
    
    public boolean cancelSilently() {
        return this.cancelInternal(true);
    }
    
    AsyncSemaphore ensureWaiterLocked() {
        if (this.waiter == null) {
            this.waiter = new AsyncSemaphore();
        }
        return this.waiter;
    }
    
    @Override
    public T get() throws InterruptedException, ExecutionException {
        T t;
        synchronized (this) {
            if (this.isCancelled() || this.isDone()) {
                t = this.getResultOrThrow();
            }
            else {
                final AsyncSemaphore ensureWaiterLocked = this.ensureWaiterLocked();
                // monitorexit(this)
                ensureWaiterLocked.acquire();
                t = this.getResultOrThrow();
            }
        }
        return t;
    }
    
    @Override
    public T get(final long n, final TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        synchronized (this) {
            if (this.isCancelled() || this.isDone()) {
                return this.getResultOrThrow();
            }
            final AsyncSemaphore ensureWaiterLocked = this.ensureWaiterLocked();
            // monitorexit(this)
            if (!ensureWaiterLocked.tryAcquire(n, timeUnit)) {
                throw new TimeoutException();
            }
        }
        return this.getResultOrThrow();
    }
    
    public FutureCallback<T> getCallback() {
        return this.callback;
    }
    
    public FutureCallback<T> getCompletionCallback() {
        return new FutureCallback<T>() {
            @Override
            public void onCompleted(final Exception ex, final T t) {
                SimpleFuture.this.setComplete(ex, t);
            }
        };
    }
    
    void releaseWaiterLocked() {
        if (this.waiter != null) {
            this.waiter.release();
            this.waiter = null;
        }
    }
    
    @Override
    public SimpleFuture<T> reset() {
        super.reset();
        this.result = null;
        this.exception = null;
        this.waiter = null;
        this.callback = null;
        this.silent = false;
        return this;
    }
    
    @Override
    public SimpleFuture<T> setCallback(final FutureCallback<T> callback) {
        synchronized (this) {
            this.callback = callback;
            FutureCallback<T> handleCompleteLocked;
            if (this.isDone() || this.isCancelled()) {
                handleCompleteLocked = this.handleCompleteLocked();
            }
            else {
                handleCompleteLocked = null;
            }
            // monitorexit(this)
            this.handleCallbackUnlocked(handleCompleteLocked);
            return this;
        }
    }
    
    public SimpleFuture<T> setComplete(final Future<T> parent) {
        parent.setCallback(this.getCompletionCallback());
        this.setParent(parent);
        return this;
    }
    
    @Override
    public boolean setComplete() {
        return this.setComplete((T)null);
    }
    
    public boolean setComplete(final Exception ex) {
        return this.setComplete(ex, null);
    }
    
    public boolean setComplete(final Exception exception, final T result) {
        boolean b;
        synchronized (this) {
            if (!super.setComplete()) {
                // monitorexit(this)
                b = false;
            }
            else {
                this.result = result;
                this.exception = exception;
                this.releaseWaiterLocked();
                final FutureCallback<T> handleCompleteLocked = this.handleCompleteLocked();
                // monitorexit(this)
                this.handleCallbackUnlocked(handleCompleteLocked);
                b = true;
            }
        }
        return b;
    }
    
    public boolean setComplete(final T t) {
        return this.setComplete(null, t);
    }
    
    @Override
    public SimpleFuture<T> setParent(final Cancellable parent) {
        super.setParent(parent);
        return this;
    }
    
    @Override
    public final <C extends FutureCallback<T>> C then(final C callback) {
        if (callback instanceof DependentCancellable) {
            ((DependentCancellable)callback).setParent(this);
        }
        this.setCallback(callback);
        return callback;
    }
    
    @Override
    public T tryGet() {
        return this.result;
    }
    
    @Override
    public Exception tryGetException() {
        return this.exception;
    }
}
