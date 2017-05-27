// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zze;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.ads.internal.reward.mediation.client.zza;

@zziy
public class zzjw extends zza.zza
{
    private volatile zzjx zzcnb;
    private volatile zzju zzcnn;
    private volatile zzjv zzcno;
    
    public zzjw(final zzjv zzcno) {
        this.zzcno = zzcno;
    }
    
    public void zza(final zzd zzd, final RewardItemParcel rewardItemParcel) {
        if (this.zzcno != null) {
            this.zzcno.zzc(rewardItemParcel);
        }
    }
    
    public void zza(final zzju zzcnn) {
        this.zzcnn = zzcnn;
    }
    
    public void zza(final zzjx zzcnb) {
        this.zzcnb = zzcnb;
    }
    
    public void zzb(final zzd zzd, final int n) {
        if (this.zzcnn != null) {
            this.zzcnn.zzay(n);
        }
    }
    
    public void zzc(final zzd zzd, final int n) {
        if (this.zzcnb != null) {
            this.zzcnb.zza(zze.zzae(zzd).getClass().getName(), n);
        }
    }
    
    public void zzq(final zzd zzd) {
        if (this.zzcnn != null) {
            this.zzcnn.zzsr();
        }
    }
    
    public void zzr(final zzd zzd) {
        if (this.zzcnb != null) {
            this.zzcnb.zzcm(zze.zzae(zzd).getClass().getName());
        }
    }
    
    public void zzs(final zzd zzd) {
        if (this.zzcno != null) {
            this.zzcno.onRewardedVideoAdOpened();
        }
    }
    
    public void zzt(final zzd zzd) {
        if (this.zzcno != null) {
            this.zzcno.onRewardedVideoStarted();
        }
    }
    
    public void zzu(final zzd zzd) {
        if (this.zzcno != null) {
            this.zzcno.onRewardedVideoAdClosed();
        }
    }
    
    public void zzv(final zzd zzd) {
        if (this.zzcno != null) {
            this.zzcno.zzso();
        }
    }
    
    public void zzw(final zzd zzd) {
        if (this.zzcno != null) {
            this.zzcno.onRewardedVideoAdLeftApplication();
        }
    }
}
