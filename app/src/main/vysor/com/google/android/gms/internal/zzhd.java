// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationInterstitialAdapter;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zziy
public final class zzhd<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener
{
    private final zzgs zzbub;
    
    public zzhd(final zzgs zzbub) {
        this.zzbub = zzbub;
    }
    
    @Override
    public void onClick(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onClick.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onClick must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdClicked();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdClicked.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdClicked();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdClicked.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onDismissScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onDismissScreen.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onDismissScreen must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdClosed();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdClosed.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdClosed();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdClosed.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onDismissScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzdd("Adapter called onDismissScreen.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onDismissScreen must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdClosed();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdClosed.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdClosed();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdClosed.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onFailedToReceiveAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter, final AdRequest.ErrorCode errorCode) {
        final String value = String.valueOf(errorCode);
        zzb.zzdd(new StringBuilder(47 + String.valueOf(value).length()).append("Adapter called onFailedToReceiveAd with error. ").append(value).toString());
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onFailedToReceiveAd must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdFailedToLoad(zzhe.zza(errorCode));
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdFailedToLoad.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdFailedToLoad(zzhe.zza(errorCode));
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdFailedToLoad.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onFailedToReceiveAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final AdRequest.ErrorCode errorCode) {
        final String value = String.valueOf(errorCode);
        zzb.zzdd(new StringBuilder(47 + String.valueOf(value).length()).append("Adapter called onFailedToReceiveAd with error ").append(value).append(".").toString());
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onFailedToReceiveAd must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdFailedToLoad(zzhe.zza(errorCode));
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdFailedToLoad.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdFailedToLoad(zzhe.zza(errorCode));
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdFailedToLoad.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onLeaveApplication(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onLeaveApplication.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onLeaveApplication must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdLeftApplication();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdLeftApplication.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdLeftApplication();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdLeftApplication.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onLeaveApplication(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzdd("Adapter called onLeaveApplication.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onLeaveApplication must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdLeftApplication();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdLeftApplication.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdLeftApplication();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdLeftApplication.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onPresentScreen(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onPresentScreen.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onPresentScreen must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdOpened();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdOpened.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdOpened();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdOpened.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onPresentScreen(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzdd("Adapter called onPresentScreen.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onPresentScreen must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdOpened();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdOpened.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdOpened();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdOpened.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onReceivedAd(final MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onReceivedAd.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onReceivedAd must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdLoaded();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdLoaded.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdLoaded();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdLoaded.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public void onReceivedAd(final MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzdd("Adapter called onReceivedAd.");
        if (!zzm.zzjr().zzvf()) {
            zzb.zzdf("onReceivedAd must be called on the main UI thread.");
            zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        zzhd.this.zzbub.onAdLoaded();
                    }
                    catch (RemoteException ex) {
                        zzb.zzd("Could not call onAdLoaded.", (Throwable)ex);
                    }
                }
            });
        }
        else {
            try {
                this.zzbub.onAdLoaded();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call onAdLoaded.", (Throwable)ex);
            }
        }
    }
}
