// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.NoSuchElementException;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;

public class zzb<T> implements Iterator<T>
{
    protected final DataBuffer<T> zH;
    protected int zI;
    
    public zzb(final DataBuffer<T> dataBuffer) {
        this.zH = zzac.zzy(dataBuffer);
        this.zI = -1;
    }
    
    @Override
    public boolean hasNext() {
        return this.zI < -1 + this.zH.getCount();
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.zI).toString());
        }
        final DataBuffer<T> zh = this.zH;
        final int zi = 1 + this.zI;
        this.zI = zi;
        return zh.get(zi);
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
