// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.stream;

import java.nio.ByteBuffer;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import java.io.IOException;
import java.io.OutputStream;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.CompletedCallback;

public class OutputStreamDataCallback implements CompletedCallback, DataCallback
{
    private OutputStream mOutput;
    
    public OutputStreamDataCallback(final OutputStream mOutput) {
        this.mOutput = mOutput;
    }
    
    public void close() {
        try {
            this.mOutput.close();
        }
        catch (IOException ex) {
            this.onCompleted(ex);
        }
    }
    
    public OutputStream getOutputStream() {
        return this.mOutput;
    }
    
    @Override
    public void onCompleted(final Exception ex) {
        ex.printStackTrace();
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        try {
            Label_0062: {
                try {
                    while (list.size() > 0) {
                        final ByteBuffer remove = list.remove();
                        this.mOutput.write(remove.array(), remove.arrayOffset() + remove.position(), remove.remaining());
                        ByteBufferList.reclaim(remove);
                    }
                    break Label_0062;
                }
                catch (Exception ex) {
                    this.onCompleted(ex);
                }
                return;
            }
            list.recycle();
        }
        finally {
            list.recycle();
        }
    }
}
