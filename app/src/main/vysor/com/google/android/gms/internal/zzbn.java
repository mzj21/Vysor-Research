// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbn extends zzbv
{
    private long zzait;
    
    public zzbn(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
        this.zzait = -1L;
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzdd = -1L;
        if (this.zzait == -1L) {
            this.zzait = (int)this.zzaiz.invoke(null, this.zzafz.getContext());
        }
        synchronized (this.zzair) {
            this.zzair.zzdd = this.zzait;
        }
    }
}
