// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbt extends zzbv
{
    public zzbt(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzeg = 2;
        final boolean booleanValue = (boolean)this.zzaiz.invoke(null, this.zzafz.getApplicationContext());
        final zzae.zza zzair = this.zzair;
        // monitorenter(zzair)
        Label_0073: {
            if (!booleanValue) {
                break Label_0073;
            }
            try {
                this.zzair.zzeg = 1;
                return;
                this.zzair.zzeg = 0;
            }
            finally {
            }
            // monitorexit(zzair)
        }
    }
}
