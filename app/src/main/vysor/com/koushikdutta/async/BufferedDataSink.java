// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.WritableCallback;

public class BufferedDataSink implements DataSink
{
    boolean endPending;
    boolean forceBuffering;
    DataSink mDataSink;
    int mMaxBuffer;
    ByteBufferList mPendingWrites;
    WritableCallback mWritable;
    
    public BufferedDataSink(final DataSink dataSink) {
        this.mPendingWrites = new ByteBufferList();
        this.mMaxBuffer = Integer.MAX_VALUE;
        this.setDataSink(dataSink);
    }
    
    private void writePending() {
        if (!this.forceBuffering) {
            if (this.mPendingWrites.hasRemaining()) {
                this.mDataSink.write(this.mPendingWrites);
                if (this.mPendingWrites.remaining() == 0 && this.endPending) {
                    this.mDataSink.end();
                }
            }
            if (!this.mPendingWrites.hasRemaining() && this.mWritable != null) {
                this.mWritable.onWriteable();
            }
        }
    }
    
    @Override
    public void end() {
        if (this.getServer().getAffinity() != Thread.currentThread()) {
            this.getServer().run(new Runnable() {
                @Override
                public void run() {
                    BufferedDataSink.this.end();
                }
            });
        }
        else if (this.mPendingWrites.hasRemaining()) {
            this.endPending = true;
        }
        else {
            this.mDataSink.end();
        }
    }
    
    public void forceBuffering(final boolean forceBuffering) {
        if (!(this.forceBuffering = forceBuffering)) {
            this.writePending();
        }
    }
    
    @Override
    public CompletedCallback getClosedCallback() {
        return this.mDataSink.getClosedCallback();
    }
    
    public DataSink getDataSink() {
        return this.mDataSink;
    }
    
    public int getMaxBuffer() {
        return this.mMaxBuffer;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.mDataSink.getServer();
    }
    
    @Override
    public WritableCallback getWriteableCallback() {
        return this.mWritable;
    }
    
    public boolean isBuffering() {
        return this.mPendingWrites.hasRemaining() || this.forceBuffering;
    }
    
    @Override
    public boolean isOpen() {
        return this.mDataSink.isOpen();
    }
    
    public int remaining() {
        return this.mPendingWrites.remaining();
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback closedCallback) {
        this.mDataSink.setClosedCallback(closedCallback);
    }
    
    public void setDataSink(final DataSink mDataSink) {
        (this.mDataSink = mDataSink).setWriteableCallback(new WritableCallback() {
            @Override
            public void onWriteable() {
                BufferedDataSink.this.writePending();
            }
        });
    }
    
    public void setMaxBuffer(final int mMaxBuffer) {
        assert mMaxBuffer >= 0;
        this.mMaxBuffer = mMaxBuffer;
    }
    
    @Override
    public void setWriteableCallback(final WritableCallback mWritable) {
        this.mWritable = mWritable;
    }
    
    @Override
    public void write(final ByteBufferList list) {
        this.write(list, false);
    }
    
    protected void write(final ByteBufferList list, final boolean b) {
        if (this.getServer().getAffinity() != Thread.currentThread()) {
            this.getServer().run(new Runnable() {
                @Override
                public void run() {
                    BufferedDataSink.this.write(list, b);
                }
            });
        }
        else {
            if (!this.isBuffering()) {
                this.mDataSink.write(list);
            }
            if (list.remaining() > 0) {
                int n = Math.min(list.remaining(), this.mMaxBuffer);
                if (b) {
                    n = list.remaining();
                }
                if (n > 0) {
                    list.get(this.mPendingWrites, n);
                }
            }
        }
    }
}
