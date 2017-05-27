// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.internal.client.zza;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.content.Context;
import com.google.android.gms.ads.internal.client.zzaf;

public final class InterstitialAd
{
    private final zzaf zzakb;
    
    public InterstitialAd(final Context context) {
        this.zzakb = new zzaf(context);
    }
    
    public AdListener getAdListener() {
        return this.zzakb.getAdListener();
    }
    
    public String getAdUnitId() {
        return this.zzakb.getAdUnitId();
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zzakb.getInAppPurchaseListener();
    }
    
    public String getMediationAdapterClassName() {
        return this.zzakb.getMediationAdapterClassName();
    }
    
    public boolean isLoaded() {
        return this.zzakb.isLoaded();
    }
    
    public boolean isLoading() {
        return this.zzakb.isLoading();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final AdRequest adRequest) {
        this.zzakb.zza(adRequest.zzdg());
    }
    
    public void setAdListener(final AdListener adListener) {
        this.zzakb.setAdListener(adListener);
        if (adListener != null && adListener instanceof zza) {
            this.zzakb.zza((zza)adListener);
        }
        else if (adListener == null) {
            this.zzakb.zza((zza)null);
        }
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.zzakb.setAdUnitId(adUnitId);
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener inAppPurchaseListener) {
        this.zzakb.setInAppPurchaseListener(inAppPurchaseListener);
    }
    
    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener playStorePurchaseListener, final String s) {
        this.zzakb.setPlayStorePurchaseParams(playStorePurchaseListener, s);
    }
    
    public void setRewardedVideoAdListener(final RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzakb.setRewardedVideoAdListener(rewardedVideoAdListener);
    }
    
    public void show() {
        this.zzakb.show();
    }
    
    public void zzd(final boolean b) {
        this.zzakb.zzd(b);
    }
}
