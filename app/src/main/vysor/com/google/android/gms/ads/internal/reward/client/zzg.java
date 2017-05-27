// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zziy;

@zziy
public class zzg extends zzd.zza
{
    private final RewardedVideoAdListener zzgf;
    
    public zzg(final RewardedVideoAdListener zzgf) {
        this.zzgf = zzgf;
    }
    
    public void onRewardedVideoAdClosed() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdClosed();
        }
    }
    
    public void onRewardedVideoAdFailedToLoad(final int n) {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdFailedToLoad(n);
        }
    }
    
    public void onRewardedVideoAdLeftApplication() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdLeftApplication();
        }
    }
    
    public void onRewardedVideoAdLoaded() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdLoaded();
        }
    }
    
    public void onRewardedVideoAdOpened() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdOpened();
        }
    }
    
    public void onRewardedVideoStarted() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoStarted();
        }
    }
    
    public void zza(final zza zza) {
        if (this.zzgf != null) {
            this.zzgf.onRewarded(new zze(zza));
        }
    }
}
