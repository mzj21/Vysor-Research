// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbf extends zzbv
{
    private static final Object zzagd;
    private static volatile String zzaio;
    
    static {
        zzbf.zzaio = null;
        zzagd = new Object();
    }
    
    public zzbf(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzdp = "E";
        Label_0066: {
            if (zzbf.zzaio != null) {
                break Label_0066;
            }
            final Object zzagd = zzbf.zzagd;
            synchronized (zzagd) {
                if (zzbf.zzaio == null) {
                    zzbf.zzaio = (String)this.zzaiz.invoke(null, this.zzafz.getContext());
                }
                // monitorexit(zzagd)
                final zzae.zza zzair = this.zzair;
                // monitorenter(zzair)
                final zzbf zzbf = this;
                final zzae.zza zza = zzbf.zzair;
                final String s = com.google.android.gms.internal.zzbf.zzaio;
                final byte[] array = s.getBytes();
                final boolean b = true;
                final String s2 = zzak.zza(array, b);
                zza.zzdp = s2;
                return;
            }
        }
        try {
            final zzbf zzbf = this;
            final zzae.zza zza = zzbf.zzair;
            final String s = com.google.android.gms.internal.zzbf.zzaio;
            final byte[] array = s.getBytes();
            final boolean b = true;
            final String s2 = zzak.zza(array, b);
            zza.zzdp = s2;
        }
        finally {
        }
        // monitorexit(zzair)
    }
}
