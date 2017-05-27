// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T>
{
    protected final DataHolder xi;
    
    protected AbstractDataBuffer(final DataHolder xi) {
        this.xi = xi;
        if (this.xi != null) {}
    }
    
    @Deprecated
    @Override
    public final void close() {
        this.release();
    }
    
    @Override
    public abstract T get(final int p0);
    
    @Override
    public int getCount() {
        int count;
        if (this.xi == null) {
            count = 0;
        }
        else {
            count = this.xi.getCount();
        }
        return count;
    }
    
    @Deprecated
    @Override
    public boolean isClosed() {
        return this.xi == null || this.xi.isClosed();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new zzb<T>(this);
    }
    
    @Override
    public void release() {
        if (this.xi != null) {
            this.xi.close();
        }
    }
    
    @Override
    public Iterator<T> singleRefIterator() {
        return new zzg<T>(this);
    }
    
    @Override
    public Bundle zzasz() {
        return this.xi.zzasz();
    }
}
