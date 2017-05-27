// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;

@zziy
public final class zzgy implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener
{
    private final zzgs zzbub;
    private NativeAdMapper zzbuc;
    
    public zzgy(final zzgs zzbub) {
        this.zzbub = zzbub;
    }
    
    @Override
    public void onAdClicked(final MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzhq("onAdClicked must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdClicked.");
        try {
            this.zzbub.onAdClicked();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClicked(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzhq("onAdClicked must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdClicked.");
        try {
            this.zzbub.onAdClicked();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClicked(final MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzhq("onAdClicked must be called on the main UI thread.");
        final NativeAdMapper zznq = this.zznq();
        if (zznq == null) {
            zzb.zzdf("Could not call onAdClicked since NativeAdMapper is null.");
        }
        else if (!zznq.getOverrideClickHandling()) {
            zzb.zzdd("Could not call onAdClicked since setOverrideClickHandling is not set to true");
        }
        else {
            zzb.zzdd("Adapter called onAdClicked.");
            try {
                this.zzbub.onAdClicked();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdClicked.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onAdClosed(final MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzhq("onAdClosed must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdClosed.");
        try {
            this.zzbub.onAdClosed();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClosed(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzhq("onAdClosed must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdClosed.");
        try {
            this.zzbub.onAdClosed();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClosed(final MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzhq("onAdClosed must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdClosed.");
        try {
            this.zzbub.onAdClosed();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdFailedToLoad(final MediationBannerAdapter mediationBannerAdapter, final int n) {
        zzac.zzhq("onAdFailedToLoad must be called on the main UI thread.");
        zzb.zzdd(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error. ").append(n).toString());
        try {
            this.zzbub.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdFailedToLoad(final MediationInterstitialAdapter mediationInterstitialAdapter, final int n) {
        zzac.zzhq("onAdFailedToLoad must be called on the main UI thread.");
        zzb.zzdd(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error ").append(n).append(".").toString());
        try {
            this.zzbub.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdFailedToLoad(final MediationNativeAdapter mediationNativeAdapter, final int n) {
        zzac.zzhq("onAdFailedToLoad must be called on the main UI thread.");
        zzb.zzdd(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error ").append(n).append(".").toString());
        try {
            this.zzbub.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdImpression(final MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzhq("onAdImpression must be called on the main UI thread.");
        final NativeAdMapper zznq = this.zznq();
        if (zznq == null) {
            zzb.zzdf("Could not call onAdImpression since NativeAdMapper is null. ");
        }
        else if (!zznq.getOverrideImpressionRecording()) {
            zzb.zzdd("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
        }
        else {
            zzb.zzdd("Adapter called onAdImpression.");
            try {
                this.zzbub.onAdImpression();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdImpression.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onAdLeftApplication(final MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzhq("onAdLeftApplication must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdLeftApplication.");
        try {
            this.zzbub.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLeftApplication(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzhq("onAdLeftApplication must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdLeftApplication.");
        try {
            this.zzbub.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLeftApplication(final MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzhq("onAdLeftApplication must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdLeftApplication.");
        try {
            this.zzbub.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLoaded(final MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzhq("onAdLoaded must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdLoaded.");
        try {
            this.zzbub.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLoaded(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzhq("onAdLoaded must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdLoaded.");
        try {
            this.zzbub.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLoaded(final MediationNativeAdapter mediationNativeAdapter, final NativeAdMapper zzbuc) {
        zzac.zzhq("onAdLoaded must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdLoaded.");
        this.zzbuc = zzbuc;
        try {
            this.zzbub.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdOpened(final MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzhq("onAdOpened must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdOpened.");
        try {
            this.zzbub.onAdOpened();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdOpened(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzhq("onAdOpened must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdOpened.");
        try {
            this.zzbub.onAdOpened();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdOpened(final MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzhq("onAdOpened must be called on the main UI thread.");
        zzb.zzdd("Adapter called onAdOpened.");
        try {
            this.zzbub.onAdOpened();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    public NativeAdMapper zznq() {
        return this.zzbuc;
    }
}
