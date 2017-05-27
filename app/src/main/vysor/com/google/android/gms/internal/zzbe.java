// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbe extends zzbv
{
    public zzbe(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzair) {
            this.zzair.zzcu = -1L;
            this.zzair.zzcu = (Long)this.zzaiz.invoke(null, this.zzafz.getContext());
        }
    }
}
