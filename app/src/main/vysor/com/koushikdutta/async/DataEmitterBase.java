// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.CompletedCallback;

public abstract class DataEmitterBase implements DataEmitter
{
    CompletedCallback endCallback;
    private boolean ended;
    DataCallback mDataCallback;
    
    @Override
    public String charset() {
        return null;
    }
    
    @Override
    public DataCallback getDataCallback() {
        return this.mDataCallback;
    }
    
    @Override
    public final CompletedCallback getEndCallback() {
        return this.endCallback;
    }
    
    protected void report(final Exception ex) {
        if (!this.ended) {
            this.ended = true;
            if (this.getEndCallback() != null) {
                this.getEndCallback().onCompleted(ex);
            }
        }
    }
    
    @Override
    public void setDataCallback(final DataCallback mDataCallback) {
        this.mDataCallback = mDataCallback;
    }
    
    @Override
    public final void setEndCallback(final CompletedCallback endCallback) {
        this.endCallback = endCallback;
    }
}
