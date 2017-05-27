// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import com.koushikdutta.async.callback.DataCallback;

public class LineEmitter implements DataCallback
{
    Charset charset;
    ByteBufferList data;
    StringCallback mLineCallback;
    
    public LineEmitter() {
        this(null);
    }
    
    public LineEmitter(final Charset charset) {
        this.data = new ByteBufferList();
        this.charset = charset;
    }
    
    public StringCallback getLineCallback() {
        return this.mLineCallback;
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        final ByteBuffer allocate = ByteBuffer.allocate(list.remaining());
        while (list.remaining() > 0) {
            final byte value = list.get();
            if (value == 10) {
                assert this.mLineCallback != null;
                allocate.flip();
                this.data.add(allocate);
                this.mLineCallback.onStringAvailable(this.data.readString(this.charset));
                this.data = new ByteBufferList();
                return;
            }
            else {
                allocate.put(value);
            }
        }
        allocate.flip();
        this.data.add(allocate);
    }
    
    public void setLineCallback(final StringCallback mLineCallback) {
        this.mLineCallback = mLineCallback;
    }
    
    public interface StringCallback
    {
        void onStringAvailable(final String p0);
    }
}
