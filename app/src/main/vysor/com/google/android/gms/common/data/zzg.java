// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T> extends zzb<T>
{
    private T Ad;
    
    public zzg(final DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.zI).toString());
        }
        ++this.zI;
        if (this.zI == 0) {
            this.Ad = this.zH.get(0);
            if (!(this.Ad instanceof zzc)) {
                final String value = String.valueOf(this.Ad.getClass());
                throw new IllegalStateException(new StringBuilder(44 + String.valueOf(value).length()).append("DataBuffer reference of type ").append(value).append(" is not movable").toString());
            }
        }
        else {
            ((zzc)this.Ad).zzfz(this.zI);
        }
        return this.Ad;
    }
}
