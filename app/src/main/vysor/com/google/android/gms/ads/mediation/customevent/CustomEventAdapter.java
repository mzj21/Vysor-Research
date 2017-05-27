// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.AdSize;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import android.content.Context;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.view.View;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter
{
    CustomEventBanner zzcxi;
    CustomEventInterstitial zzcxj;
    CustomEventNative zzcxk;
    private View zzgs;
    
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
    public View getBannerView() {
        return this.zzgs;
    }
    
    @Override
    public void onDestroy() {
        if (this.zzcxi != null) {
            this.zzcxi.onDestroy();
        }
        if (this.zzcxj != null) {
            this.zzcxj.onDestroy();
        }
        if (this.zzcxk != null) {
            this.zzcxk.onDestroy();
        }
    }
    
    @Override
    public void onPause() {
        if (this.zzcxi != null) {
            this.zzcxi.onPause();
        }
        if (this.zzcxj != null) {
            this.zzcxj.onPause();
        }
        if (this.zzcxk != null) {
            this.zzcxk.onPause();
        }
    }
    
    @Override
    public void onResume() {
        if (this.zzcxi != null) {
            this.zzcxi.onResume();
        }
        if (this.zzcxj != null) {
            this.zzcxj.onResume();
        }
        if (this.zzcxk != null) {
            this.zzcxk.onResume();
        }
    }
    
    @Override
    public void requestBannerAd(final Context context, final MediationBannerListener mediationBannerListener, final Bundle bundle, final AdSize adSize, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        this.zzcxi = zzj(bundle.getString("class_name"));
        if (this.zzcxi == null) {
            mediationBannerListener.onAdFailedToLoad(this, 0);
        }
        else {
            Bundle bundle3;
            if (bundle2 == null) {
                bundle3 = null;
            }
            else {
                bundle3 = bundle2.getBundle(bundle.getString("class_name"));
            }
            this.zzcxi.requestBannerAd(context, new zza(this, mediationBannerListener), bundle.getString("parameter"), adSize, mediationAdRequest, bundle3);
        }
    }
    
    @Override
    public void requestInterstitialAd(final Context context, final MediationInterstitialListener mediationInterstitialListener, final Bundle bundle, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        this.zzcxj = zzj(bundle.getString("class_name"));
        if (this.zzcxj == null) {
            mediationInterstitialListener.onAdFailedToLoad(this, 0);
        }
        else {
            Bundle bundle3;
            if (bundle2 == null) {
                bundle3 = null;
            }
            else {
                bundle3 = bundle2.getBundle(bundle.getString("class_name"));
            }
            this.zzcxj.requestInterstitialAd(context, this.zza(mediationInterstitialListener), bundle.getString("parameter"), mediationAdRequest, bundle3);
        }
    }
    
    @Override
    public void requestNativeAd(final Context context, final MediationNativeListener mediationNativeListener, final Bundle bundle, final NativeMediationAdRequest nativeMediationAdRequest, final Bundle bundle2) {
        this.zzcxk = zzj(bundle.getString("class_name"));
        if (this.zzcxk == null) {
            mediationNativeListener.onAdFailedToLoad(this, 0);
        }
        else {
            Bundle bundle3;
            if (bundle2 == null) {
                bundle3 = null;
            }
            else {
                bundle3 = bundle2.getBundle(bundle.getString("class_name"));
            }
            this.zzcxk.requestNativeAd(context, new zzc(this, mediationNativeListener), bundle.getString("parameter"), nativeMediationAdRequest, bundle3);
        }
    }
    
    @Override
    public void showInterstitial() {
        this.zzcxj.showInterstitial();
    }
    
    zzb zza(final MediationInterstitialListener mediationInterstitialListener) {
        return new zzb(this, mediationInterstitialListener);
    }
    
    static final class zza implements CustomEventBannerListener
    {
        private final CustomEventAdapter zzcxl;
        private final MediationBannerListener zzgk;
        
        public zza(final CustomEventAdapter zzcxl, final MediationBannerListener zzgk) {
            this.zzcxl = zzcxl;
            this.zzgk = zzgk;
        }
        
        @Override
        public void onAdClicked() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdClicked.");
            this.zzgk.onAdClicked(this.zzcxl);
        }
        
        @Override
        public void onAdClosed() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdClosed.");
            this.zzgk.onAdClosed(this.zzcxl);
        }
        
        @Override
        public void onAdFailedToLoad(final int n) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdFailedToLoad.");
            this.zzgk.onAdFailedToLoad(this.zzcxl, n);
        }
        
        @Override
        public void onAdLeftApplication() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdLeftApplication.");
            this.zzgk.onAdLeftApplication(this.zzcxl);
        }
        
        @Override
        public void onAdLoaded(final View view) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdLoaded.");
            this.zzcxl.zza(view);
            this.zzgk.onAdLoaded(this.zzcxl);
        }
        
        @Override
        public void onAdOpened() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdOpened.");
            this.zzgk.onAdOpened(this.zzcxl);
        }
    }
    
    class zzb implements CustomEventInterstitialListener
    {
        private final CustomEventAdapter zzcxl;
        private final MediationInterstitialListener zzgl;
        
        public zzb(final CustomEventAdapter zzcxl, final MediationInterstitialListener zzgl) {
            this.zzcxl = zzcxl;
            this.zzgl = zzgl;
        }
        
        @Override
        public void onAdClicked() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdClicked.");
            this.zzgl.onAdClicked(this.zzcxl);
        }
        
        @Override
        public void onAdClosed() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdClosed.");
            this.zzgl.onAdClosed(this.zzcxl);
        }
        
        @Override
        public void onAdFailedToLoad(final int n) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onFailedToReceiveAd.");
            this.zzgl.onAdFailedToLoad(this.zzcxl, n);
        }
        
        @Override
        public void onAdLeftApplication() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdLeftApplication.");
            this.zzgl.onAdLeftApplication(this.zzcxl);
        }
        
        @Override
        public void onAdLoaded() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onReceivedAd.");
            this.zzgl.onAdLoaded(CustomEventAdapter.this);
        }
        
        @Override
        public void onAdOpened() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdOpened.");
            this.zzgl.onAdOpened(this.zzcxl);
        }
    }
    
    static class zzc implements CustomEventNativeListener
    {
        private final CustomEventAdapter zzcxl;
        private final MediationNativeListener zzgm;
        
        public zzc(final CustomEventAdapter zzcxl, final MediationNativeListener zzgm) {
            this.zzcxl = zzcxl;
            this.zzgm = zzgm;
        }
        
        @Override
        public void onAdClicked() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdClicked.");
            this.zzgm.onAdClicked(this.zzcxl);
        }
        
        @Override
        public void onAdClosed() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdClosed.");
            this.zzgm.onAdClosed(this.zzcxl);
        }
        
        @Override
        public void onAdFailedToLoad(final int n) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdFailedToLoad.");
            this.zzgm.onAdFailedToLoad(this.zzcxl, n);
        }
        
        @Override
        public void onAdImpression() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdImpression.");
            this.zzgm.onAdImpression(this.zzcxl);
        }
        
        @Override
        public void onAdLeftApplication() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdLeftApplication.");
            this.zzgm.onAdLeftApplication(this.zzcxl);
        }
        
        @Override
        public void onAdLoaded(final NativeAdMapper nativeAdMapper) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdLoaded.");
            this.zzgm.onAdLoaded(this.zzcxl, nativeAdMapper);
        }
        
        @Override
        public void onAdOpened() {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Custom event adapter called onAdOpened.");
            this.zzgm.onAdOpened(this.zzcxl);
        }
    }
}
