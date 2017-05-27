// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbo extends zzbv
{
    private static final Object zzagd;
    private static volatile String zzct;
    
    static {
        zzbo.zzct = null;
        zzagd = new Object();
    }
    
    public zzbo(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzct = "E";
        Label_0047: {
            if (zzbo.zzct != null) {
                break Label_0047;
            }
            final Object zzagd = zzbo.zzagd;
            synchronized (zzagd) {
                if (zzbo.zzct == null) {
                    zzbo.zzct = (String)this.zzaiz.invoke(null, new Object[0]);
                }
                // monitorexit(zzagd)
                final zzae.zza zzair = this.zzair;
                // monitorenter(zzair)
                final zzbo zzbo = this;
                final zzae.zza zza = zzbo.zzair;
                final String s = com.google.android.gms.internal.zzbo.zzct;
                zza.zzct = s;
                return;
            }
        }
        try {
            final zzbo zzbo = this;
            final zzae.zza zza = zzbo.zzair;
            final String s = com.google.android.gms.internal.zzbo.zzct;
            zza.zzct = s;
        }
        finally {
        }
        // monitorexit(zzair)
    }
}
