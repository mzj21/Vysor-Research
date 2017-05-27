// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbi extends zzbv
{
    private static final Object zzagd;
    private static volatile Long zzec;
    
    static {
        zzbi.zzec = null;
        zzagd = new Object();
    }
    
    public zzbi(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        Label_0038: {
            if (zzbi.zzec != null) {
                break Label_0038;
            }
            final Object zzagd = zzbi.zzagd;
            synchronized (zzagd) {
                if (zzbi.zzec == null) {
                    zzbi.zzec = (Long)this.zzaiz.invoke(null, new Object[0]);
                }
                // monitorexit(zzagd)
                final zzae.zza zzair = this.zzair;
                // monitorenter(zzair)
                final zzbi zzbi = this;
                final zzae.zza zza = zzbi.zzair;
                final Long n = com.google.android.gms.internal.zzbi.zzec;
                zza.zzec = n;
                return;
            }
        }
        try {
            final zzbi zzbi = this;
            final zzae.zza zza = zzbi.zzair;
            final Long n = com.google.android.gms.internal.zzbi.zzec;
            zza.zzec = n;
        }
        finally {
        }
        // monitorexit(zzair)
    }
}
