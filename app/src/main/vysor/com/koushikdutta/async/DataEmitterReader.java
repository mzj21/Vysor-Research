// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.callback.DataCallback;

public class DataEmitterReader implements DataCallback
{
    ByteBufferList mPendingData;
    DataCallback mPendingRead;
    int mPendingReadLength;
    
    public DataEmitterReader() {
        this.mPendingData = new ByteBufferList();
    }
    
    private boolean handlePendingData(final DataEmitter dataEmitter) {
        boolean b;
        if (this.mPendingReadLength > this.mPendingData.remaining()) {
            b = false;
        }
        else {
            final DataCallback mPendingRead = this.mPendingRead;
            this.mPendingRead = null;
            mPendingRead.onDataAvailable(dataEmitter, this.mPendingData);
            assert !this.mPendingData.hasRemaining();
            b = true;
        }
        return b;
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        assert this.mPendingRead != null;
        do {
            list.get(this.mPendingData, Math.min(list.remaining(), this.mPendingReadLength - this.mPendingData.remaining()));
            list.remaining();
        } while (this.handlePendingData(dataEmitter) && this.mPendingRead != null);
        list.remaining();
    }
    
    public void read(final int mPendingReadLength, final DataCallback mPendingRead) {
        assert this.mPendingRead == null;
        this.mPendingReadLength = mPendingReadLength;
        this.mPendingRead = mPendingRead;
        assert !this.mPendingData.hasRemaining();
        this.mPendingData.recycle();
    }
}
