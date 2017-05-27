// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzqj<L> implements zzc<L>
{
    private final DataHolder xi;
    
    protected zzqj(final DataHolder xi) {
        this.xi = xi;
    }
    
    protected abstract void zza(final L p0, final DataHolder p1);
    
    @Override
    public void zzarg() {
        if (this.xi != null) {
            this.xi.close();
        }
    }
    
    @Override
    public final void zzt(final L l) {
        this.zza(l, this.xi);
    }
}
