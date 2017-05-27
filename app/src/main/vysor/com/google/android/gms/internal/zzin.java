// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.formats.zzi;
import java.util.List;
import com.google.android.gms.ads.internal.request.AdResponseParcel;

@zziy
public class zzin extends zzkm
{
    private final zzil.zza zzccj;
    private final zzke.zza zzcck;
    private final AdResponseParcel zzccl;
    
    public zzin(final zzke.zza zzcck, final zzil.zza zzccj) {
        this.zzcck = zzcck;
        this.zzccl = this.zzcck.zzcop;
        this.zzccj = zzccj;
    }
    
    private zzke zzan(final int n) {
        return new zzke(this.zzcck.zzcix.zzcfu, null, null, n, null, null, this.zzccl.orientation, this.zzccl.zzbsj, this.zzcck.zzcix.zzcfx, false, null, null, null, null, null, this.zzccl.zzchd, this.zzcck.zzaqz, this.zzccl.zzchb, this.zzcck.zzcoj, this.zzccl.zzchg, this.zzccl.zzchh, this.zzcck.zzcod, null, null, null, null, this.zzcck.zzcop.zzchu, this.zzcck.zzcop.zzchv, null, null, null);
    }
    
    @Override
    public void onStop() {
    }
    
    @Override
    public void zzfc() {
        zzkr.zzcrf.post((Runnable)new Runnable() {
            final /* synthetic */ zzke zzcdc = zzin.this.zzan(0);
            
            @Override
            public void run() {
                zzin.this.zzccj.zzb(this.zzcdc);
            }
        });
    }
}
