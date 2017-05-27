// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.stream;

import java.io.IOException;
import com.koushikdutta.async.ByteBufferList;
import java.io.InputStream;

public class ByteBufferListInputStream extends InputStream
{
    ByteBufferList bb;
    
    public ByteBufferListInputStream(final ByteBufferList bb) {
        this.bb = bb;
    }
    
    @Override
    public int read() throws IOException {
        int value;
        if (this.bb.remaining() <= 0) {
            value = -1;
        }
        else {
            value = this.bb.get();
        }
        return value;
    }
    
    @Override
    public int read(final byte[] array) throws IOException {
        return this.read(array, 0, array.length);
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        int min;
        if (this.bb.remaining() <= 0) {
            min = -1;
        }
        else {
            min = Math.min(n2, this.bb.remaining());
            this.bb.get(array, n, min);
        }
        return min;
    }
}
