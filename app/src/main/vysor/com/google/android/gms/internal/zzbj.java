// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbj extends zzbv
{
    private long startTime;
    
    public zzbj(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final long startTime, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
        this.startTime = startTime;
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        final long longValue = (long)this.zzaiz.invoke(null, new Object[0]);
        synchronized (this.zzair) {
            this.zzair.zzew = longValue;
            if (this.startTime != 0L) {
                this.zzair.zzdi = longValue - this.startTime;
                this.zzair.zzdn = this.startTime;
            }
        }
    }
}
