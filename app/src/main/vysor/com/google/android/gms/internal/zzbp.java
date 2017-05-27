// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class zzbp extends zzbv
{
    private List<Long> zzaiu;
    
    public zzbp(final zzbb zzbb, final String s, final String s2, final zzae.zza zza, final int n, final int n2) {
        super(zzbb, s, s2, zza, n, n2);
        this.zzaiu = null;
    }
    
    @Override
    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzdq = -1L;
        this.zzair.zzdr = -1L;
        if (this.zzaiu == null) {
            this.zzaiu = (List<Long>)this.zzaiz.invoke(null, this.zzafz.getContext());
        }
        if (this.zzaiu != null && this.zzaiu.size() == 2) {
            synchronized (this.zzair) {
                this.zzair.zzdq = this.zzaiu.get(0);
                this.zzair.zzdr = this.zzaiu.get(1);
            }
        }
    }
}
