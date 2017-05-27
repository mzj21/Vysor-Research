// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zze;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;
import com.google.android.gms.ads.internal.reward.client.zzb;

@zziy
public class zzjq extends zzb.zza
{
    private final Context mContext;
    private final Object zzakd;
    private final VersionInfoParcel zzanh;
    private final zzjr zzcmt;
    
    zzjq(final Context mContext, final VersionInfoParcel zzanh, final zzjr zzcmt) {
        this.zzakd = new Object();
        this.mContext = mContext;
        this.zzanh = zzanh;
        this.zzcmt = zzcmt;
    }
    
    public zzjq(final Context context, final zzd zzd, final zzgq zzgq, final VersionInfoParcel versionInfoParcel) {
        this(context, versionInfoParcel, new zzjr(context, zzd, AdSizeParcel.zzjc(), zzgq, versionInfoParcel));
    }
    
    public void destroy() {
        this.zzh(null);
    }
    
    public boolean isLoaded() {
        synchronized (this.zzakd) {
            return this.zzcmt.isLoaded();
        }
    }
    
    public void pause() {
        this.zzf(null);
    }
    
    public void resume() {
        this.zzg(null);
    }
    
    public void setUserId(final String s) {
        com.google.android.gms.ads.internal.util.client.zzb.zzdf("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }
    
    public void show() {
        synchronized (this.zzakd) {
            this.zzcmt.zzsn();
        }
    }
    
    public void zza(final RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) {
        synchronized (this.zzakd) {
            this.zzcmt.zza(rewardedVideoAdRequestParcel);
        }
    }
    
    public void zza(final com.google.android.gms.ads.internal.reward.client.zzd zzd) {
        synchronized (this.zzakd) {
            this.zzcmt.zza(zzd);
        }
    }
    
    public void zzf(final com.google.android.gms.dynamic.zzd zzd) {
        synchronized (this.zzakd) {
            this.zzcmt.pause();
        }
    }
    
    public void zzg(final com.google.android.gms.dynamic.zzd zzd) {
        final Object zzakd = this.zzakd;
        // monitorenter(zzakd)
        Label_0038: {
            if (zzd != null) {
                break Label_0038;
            }
            Context context = null;
        Label_0028_Outer:
            while (true) {
                while (true) {
                    if (context == null) {
                        break Label_0028;
                    }
                    try {
                        this.zzcmt.onContextChanged(context);
                        this.zzcmt.resume();
                        return;
                        context = zze.zzae(zzd);
                        continue Label_0028_Outer;
                    }
                    catch (Exception ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to extract updated context.", ex);
                        continue;
                    }
                    finally {
                    }
                    // monitorexit(zzakd)
                    break;
                }
                break;
            }
        }
    }
    
    public void zzh(final com.google.android.gms.dynamic.zzd zzd) {
        synchronized (this.zzakd) {
            this.zzcmt.destroy();
        }
    }
}
