// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.wrapper.DataEmitterWrapper;
import com.koushikdutta.async.callback.DataCallback;

public class FilteredDataEmitter extends DataEmitterBase implements DataEmitter, DataTrackingEmitter, DataCallback, DataEmitterWrapper
{
    boolean closed;
    private DataEmitter mEmitter;
    private int totalRead;
    private DataTracker tracker;
    
    @Override
    public String charset() {
        String charset;
        if (this.mEmitter == null) {
            charset = null;
        }
        else {
            charset = this.mEmitter.charset();
        }
        return charset;
    }
    
    @Override
    public void close() {
        this.closed = true;
        if (this.mEmitter != null) {
            this.mEmitter.close();
        }
    }
    
    @Override
    public int getBytesRead() {
        return this.totalRead;
    }
    
    @Override
    public DataEmitter getDataEmitter() {
        return this.mEmitter;
    }
    
    @Override
    public DataTracker getDataTracker() {
        return this.tracker;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.mEmitter.getServer();
    }
    
    @Override
    public boolean isChunked() {
        return this.mEmitter.isChunked();
    }
    
    @Override
    public boolean isPaused() {
        return this.mEmitter.isPaused();
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        if (this.closed) {
            list.recycle();
        }
        else {
            if (list != null) {
                this.totalRead += list.remaining();
            }
            Util.emitAllData(this, list);
            if (list != null) {
                this.totalRead -= list.remaining();
            }
            if (this.tracker != null && list != null) {
                this.tracker.onData(this.totalRead);
            }
        }
    }
    
    @Override
    public void pause() {
        this.mEmitter.pause();
    }
    
    @Override
    public void resume() {
        this.mEmitter.resume();
    }
    
    @Override
    public void setDataEmitter(final DataEmitter mEmitter) {
        if (this.mEmitter != null) {
            this.mEmitter.setDataCallback(null);
        }
        (this.mEmitter = mEmitter).setDataCallback(this);
        this.mEmitter.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                FilteredDataEmitter.this.report(ex);
            }
        });
    }
    
    @Override
    public void setDataTracker(final DataTracker tracker) {
        this.tracker = tracker;
    }
}
