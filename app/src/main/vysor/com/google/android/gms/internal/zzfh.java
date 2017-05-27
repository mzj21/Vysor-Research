// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;

@zziy
public class zzfh extends zzkm
{
    final zzlt zzbkr;
    final zzfj zzbns;
    private final String zzbnt;
    
    zzfh(final zzlt zzbkr, final zzfj zzbns, final String zzbnt) {
        this.zzbkr = zzbkr;
        this.zzbns = zzbns;
        this.zzbnt = zzbnt;
        zzu.zzgw().zza(this);
    }
    
    @Override
    public void onStop() {
        this.zzbns.abort();
    }
    
    @Override
    public void zzfc() {
        try {
            this.zzbns.zzbc(this.zzbnt);
        }
        finally {
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    zzu.zzgw().zzb(zzfh.this);
                }
            });
        }
    }
}
