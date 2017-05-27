// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;

public class zzbr extends zzbv
{
    public zzbr(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzair) {
            final zzba zzba = new zzba((String)this.zzaiz.invoke(null, new Object[0]));
            this.zzair.zzei = zzba.zzahl;
            this.zzair.zzej = zzba.zzahm;
        }
    }
}
