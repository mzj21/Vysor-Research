// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Callable;

public class zzbl implements Callable
{
    private final zzbb zzafz;
    private final zzae.zza zzair;
    
    public zzbl(final zzbb zzafz, final zzae.zza zzair) {
        this.zzafz = zzafz;
        this.zzair = zzair;
    }
    
    public Void zzdb() throws Exception {
        if (this.zzafz.zzcq() != null) {
            this.zzafz.zzcq().get();
        }
        final zzae.zza zzcp = this.zzafz.zzcp();
        if (zzcp != null) {
            try {
                synchronized (this.zzair) {
                    zzark.zza(this.zzair, zzark.zzf(zzcp));
                }
            }
            catch (zzarj zzarj) {}
        }
        return null;
    }
}
