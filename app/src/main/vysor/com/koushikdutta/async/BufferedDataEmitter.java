// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;

public class BufferedDataEmitter implements DataEmitter
{
    ByteBufferList mBuffers;
    DataCallback mDataCallback;
    DataEmitter mEmitter;
    CompletedCallback mEndCallback;
    Exception mEndException;
    boolean mEnded;
    
    public BufferedDataEmitter(final DataEmitter mEmitter) {
        this.mEnded = false;
        this.mBuffers = new ByteBufferList();
        (this.mEmitter = mEmitter).setDataCallback(new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                list.get(BufferedDataEmitter.this.mBuffers);
                BufferedDataEmitter.this.onDataAvailable();
            }
        });
        this.mEmitter.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(final Exception mEndException) {
                BufferedDataEmitter.this.mEnded = true;
                BufferedDataEmitter.this.mEndException = mEndException;
                if (BufferedDataEmitter.this.mBuffers.remaining() == 0 && BufferedDataEmitter.this.mEndCallback != null) {
                    BufferedDataEmitter.this.mEndCallback.onCompleted(mEndException);
                }
            }
        });
    }
    
    @Override
    public String charset() {
        return this.mEmitter.charset();
    }
    
    @Override
    public void close() {
        this.mEmitter.close();
    }
    
    @Override
    public DataCallback getDataCallback() {
        return this.mDataCallback;
    }
    
    @Override
    public CompletedCallback getEndCallback() {
        return this.mEndCallback;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.mEmitter.getServer();
    }
    
    @Override
    public boolean isChunked() {
        return false;
    }
    
    @Override
    public boolean isPaused() {
        return this.mEmitter.isPaused();
    }
    
    public void onDataAvailable() {
        if (this.mDataCallback != null && !this.isPaused() && this.mBuffers.remaining() > 0) {
            this.mDataCallback.onDataAvailable(this, this.mBuffers);
        }
        if (this.mEnded && !this.mBuffers.hasRemaining() && this.mEndCallback != null) {
            this.mEndCallback.onCompleted(this.mEndException);
        }
    }
    
    @Override
    public void pause() {
        this.mEmitter.pause();
    }
    
    @Override
    public void resume() {
        this.mEmitter.resume();
        this.onDataAvailable();
    }
    
    @Override
    public void setDataCallback(final DataCallback mDataCallback) {
        if (this.mDataCallback != null) {
            throw new RuntimeException("Buffered Data Emitter callback may only be set once");
        }
        this.mDataCallback = mDataCallback;
    }
    
    @Override
    public void setEndCallback(final CompletedCallback mEndCallback) {
        this.mEndCallback = mEndCallback;
    }
}
