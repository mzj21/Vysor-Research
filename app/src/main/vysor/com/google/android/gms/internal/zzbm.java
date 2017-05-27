// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbm extends zzbv
{
    private static final Object zzagd;
    private static volatile Long zzais;
    
    static {
        zzbm.zzais = null;
        zzagd = new Object();
    }
    
    public zzbm(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        Label_0038: {
            if (zzbm.zzais != null) {
                break Label_0038;
            }
            final Object zzagd = zzbm.zzagd;
            synchronized (zzagd) {
                if (zzbm.zzais == null) {
                    zzbm.zzais = (Long)this.zzaiz.invoke(null, new Object[0]);
                }
                // monitorexit(zzagd)
                final zzae.zza zzair = this.zzair;
                // monitorenter(zzair)
                final zzbm zzbm = this;
                final zzae.zza zza = zzbm.zzair;
                final Long n = com.google.android.gms.internal.zzbm.zzais;
                zza.zzdm = n;
                return;
            }
        }
        try {
            final zzbm zzbm = this;
            final zzae.zza zza = zzbm.zzair;
            final Long n = com.google.android.gms.internal.zzbm.zzais;
            zza.zzdm = n;
        }
        finally {
        }
        // monitorexit(zzair)
    }
}
