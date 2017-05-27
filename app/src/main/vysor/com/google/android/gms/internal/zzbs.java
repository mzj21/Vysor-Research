// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbs extends zzbv
{
    private static final Object zzagd;
    private static volatile Long zzaiw;
    
    static {
        zzbs.zzaiw = null;
        zzagd = new Object();
    }
    
    public zzbs(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        Label_0038: {
            if (zzbs.zzaiw != null) {
                break Label_0038;
            }
            final Object zzagd = zzbs.zzagd;
            synchronized (zzagd) {
                if (zzbs.zzaiw == null) {
                    zzbs.zzaiw = (Long)this.zzaiz.invoke(null, new Object[0]);
                }
                // monitorexit(zzagd)
                final zzae.zza zzair = this.zzair;
                // monitorenter(zzair)
                final zzbs zzbs = this;
                final zzae.zza zza = zzbs.zzair;
                final Long n = com.google.android.gms.internal.zzbs.zzaiw;
                zza.zzds = n;
                return;
            }
        }
        try {
            final zzbs zzbs = this;
            final zzae.zza zza = zzbs.zzair;
            final Long n = com.google.android.gms.internal.zzbs.zzaiw;
            zza.zzds = n;
        }
        finally {
        }
        // monitorexit(zzair)
    }
}
