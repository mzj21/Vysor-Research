// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbh extends zzbv
{
    public zzbh(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzcw = -1L;
        this.zzair.zzcx = -1L;
        final int[] array = (int[])this.zzaiz.invoke(null, this.zzafz.getContext());
        synchronized (this.zzair) {
            this.zzair.zzcw = (long)array[0];
            this.zzair.zzcx = (long)array[1];
        }
    }
}
