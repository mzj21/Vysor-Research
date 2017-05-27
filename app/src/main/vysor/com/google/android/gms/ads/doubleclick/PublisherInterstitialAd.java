// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.doubleclick;

import com.google.android.gms.ads.Correlator;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import android.content.Context;
import com.google.android.gms.ads.internal.client.zzaf;

public final class PublisherInterstitialAd
{
    private final zzaf zzakb;
    
    public PublisherInterstitialAd(final Context context) {
        this.zzakb = new zzaf(context, this);
    }
    
    public AdListener getAdListener() {
        return this.zzakb.getAdListener();
    }
    
    public String getAdUnitId() {
        return this.zzakb.getAdUnitId();
    }
    
    public AppEventListener getAppEventListener() {
        return this.zzakb.getAppEventListener();
    }
    
    public String getMediationAdapterClassName() {
        return this.zzakb.getMediationAdapterClassName();
    }
    
    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzakb.getOnCustomRenderedAdLoadedListener();
    }
    
    public boolean isLoaded() {
        return this.zzakb.isLoaded();
    }
    
    public boolean isLoading() {
        return this.zzakb.isLoading();
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.zzakb.zza(publisherAdRequest.zzdg());
    }
    
    public void setAdListener(final AdListener adListener) {
        this.zzakb.setAdListener(adListener);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.zzakb.setAdUnitId(adUnitId);
    }
    
    public void setAppEventListener(final AppEventListener appEventListener) {
        this.zzakb.setAppEventListener(appEventListener);
    }
    
    public void setCorrelator(final Correlator correlator) {
        this.zzakb.setCorrelator(correlator);
    }
    
    public void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzakb.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }
    
    public void show() {
        this.zzakb.show();
    }
}
