// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.AdRequest;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationServerParameters;
import android.app.Activity;
import com.google.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.View;
import com.google.android.gms.common.annotation.KeepName;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.ads.mediation.MediationBannerAdapter;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters>
{
    private View zzgs;
    CustomEventBanner zzgt;
    CustomEventInterstitial zzgu;
    
    private void zza(final View zzgs) {
        this.zzgs = zzgs;
    }
    
    private static <T> T zzj(final String s) {
        try {
            final Object instance = Class.forName(s).newInstance();
            return (T)instance;
        }
        catch (Throwable t) {
            final String value = String.valueOf(t.getMessage());
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(new StringBuilder(46 + String.valueOf(s).length() + String.valueOf(value).length()).append("Could not instantiate custom event adapter: ").append(s).append(". ").append(value).toString());
            final Object instance = null;
            return (T)instance;
        }
    }
    
    @Override
    public void destroy() {
        if (this.zzgt != null) {
            this.zzgt.destroy();
        }
        if (this.zzgu != null) {
            this.zzgu.destroy();
        }
    }
    
    @Override
    public Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }
    
    @Override
    public View getBannerView() {
        return this.zzgs;
    }
    
    @Override
    public Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }
    
    @Override
    public void requestBannerAd(final MediationBannerListener mediationBannerListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final AdSize adSize, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.zzgt = zzj(customEventServerParameters.className);
        if (this.zzgt == null) {
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
        }
        else {
            Object extra;
            if (customEventExtras == null) {
                extra = null;
            }
            else {
                extra = customEventExtras.getExtra(customEventServerParameters.label);
            }
            this.zzgt.requestBannerAd(new zza(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, extra);
        }
    }
    
    @Override
    public void requestInterstitialAd(final MediationInterstitialListener mediationInterstitialListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.zzgu = zzj(customEventServerParameters.className);
        if (this.zzgu == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
        }
        else {
            Object extra;
            if (customEventExtras == null) {
                extra = null;
            }
            else {
                extra = customEventExtras.getExtra(customEventServerParameters.label);
            }
            this.zzgu.requestInterstitialAd(this.zza(mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, extra);
        }
    }
    
    @Override
    public void showInterstitial() {
        this.zzgu.showInterstitial();
    }
    
    zzb zza(final MediationInterstitialListener mediationInterstitialListener) {
        return new zzb(this, mediationInterstitialListener);
    }
    
    static final class zza implements CustomEventBannerListener
    {
        private final CustomEventAdapter zzgv;
        private final MediationBannerListener zzgw;
        
        public zza(final CustomEventAdapter zzgv, final MediationBannerListener zzgw) {
            this.zzgv = zzgv;
            this.zzgw = zzgw;
        }
        
        @Override
        public void onClick() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onFailedToReceiveAd.");
            this.zzgw.onClick(this.zzgv);
        }
        
        @Override
        public void onDismissScreen() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onFailedToReceiveAd.");
            this.zzgw.onDismissScreen(this.zzgv);
        }
        
        @Override
        public void onFailedToReceiveAd() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onFailedToReceiveAd.");
            this.zzgw.onFailedToReceiveAd(this.zzgv, AdRequest.ErrorCode.NO_FILL);
        }
        
        @Override
        public void onLeaveApplication() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onFailedToReceiveAd.");
            this.zzgw.onLeaveApplication(this.zzgv);
        }
        
        @Override
        public void onPresentScreen() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onFailedToReceiveAd.");
            this.zzgw.onPresentScreen(this.zzgv);
        }
        
        @Override
        public void onReceivedAd(final View view) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onReceivedAd.");
            this.zzgv.zza(view);
            this.zzgw.onReceivedAd(this.zzgv);
        }
    }
    
    class zzb implements CustomEventInterstitialListener
    {
        private final CustomEventAdapter zzgv;
        private final MediationInterstitialListener zzgx;
        
        public zzb(final CustomEventAdapter zzgv, final MediationInterstitialListener zzgx) {
            this.zzgv = zzgv;
            this.zzgx = zzgx;
        }
        
        @Override
        public void onDismissScreen() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onDismissScreen.");
            this.zzgx.onDismissScreen(this.zzgv);
        }
        
        @Override
        public void onFailedToReceiveAd() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onFailedToReceiveAd.");
            this.zzgx.onFailedToReceiveAd(this.zzgv, AdRequest.ErrorCode.NO_FILL);
        }
        
        @Override
        public void onLeaveApplication() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onLeaveApplication.");
            this.zzgx.onLeaveApplication(this.zzgv);
        }
        
        @Override
        public void onPresentScreen() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onPresentScreen.");
            this.zzgx.onPresentScreen(this.zzgv);
        }
        
        @Override
        public void onReceivedAd() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onReceivedAd.");
            this.zzgx.onReceivedAd(CustomEventAdapter.this);
        }
    }
}
