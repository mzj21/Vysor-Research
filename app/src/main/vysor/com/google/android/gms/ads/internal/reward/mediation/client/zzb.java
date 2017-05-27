// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.mediation.client;

import com.google.android.gms.ads.reward.RewardItem;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;

@zziy
public class zzb implements MediationRewardedVideoAdListener
{
    private final zza zzcnx;
    
    public zzb(final zza zzcnx) {
        this.zzcnx = zzcnx;
    }
    
    @Override
    public void onAdClicked(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzhq("onAdClicked must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onAdClicked.");
        try {
            this.zzcnx.zzv(zze.zzac(mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdClicked.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdClosed(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzhq("onAdClosed must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onAdClosed.");
        try {
            this.zzcnx.zzu(zze.zzac(mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdClosed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdFailedToLoad(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, final int n) {
        zzac.zzhq("onAdFailedToLoad must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onAdFailedToLoad.");
        try {
            this.zzcnx.zzc(zze.zzac(mediationRewardedVideoAdAdapter), n);
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdFailedToLoad.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLeftApplication(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzhq("onAdLeftApplication must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onAdLeftApplication.");
        try {
            this.zzcnx.zzw(zze.zzac(mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdLeftApplication.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdLoaded(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzhq("onAdLoaded must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onAdLoaded.");
        try {
            this.zzcnx.zzr(zze.zzac(mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdLoaded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onAdOpened(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzhq("onAdOpened must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onAdOpened.");
        try {
            this.zzcnx.zzs(zze.zzac(mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onAdOpened.", (Throwable)ex);
        }
    }
    
    @Override
    public void onInitializationFailed(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, final int n) {
        zzac.zzhq("onInitializationFailed must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onInitializationFailed.");
        try {
            this.zzcnx.zzb(zze.zzac(mediationRewardedVideoAdAdapter), n);
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onInitializationFailed.", (Throwable)ex);
        }
    }
    
    @Override
    public void onInitializationSucceeded(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzhq("onInitializationSucceeded must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onInitializationSucceeded.");
        try {
            this.zzcnx.zzq(zze.zzac(mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onInitializationSucceeded.", (Throwable)ex);
        }
    }
    
    @Override
    public void onRewarded(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, final RewardItem rewardItem) {
        zzac.zzhq("onRewarded must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onRewarded.");
        while (true) {
            if (rewardItem != null) {
                try {
                    this.zzcnx.zza(zze.zzac(mediationRewardedVideoAdAdapter), new RewardItemParcel(rewardItem));
                    return;
                    this.zzcnx.zza(zze.zzac(mediationRewardedVideoAdAdapter), new RewardItemParcel(mediationRewardedVideoAdAdapter.getClass().getName(), 1));
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onRewarded.", (Throwable)ex);
                }
                return;
            }
            continue;
        }
    }
    
    @Override
    public void onVideoStarted(final MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzhq("onVideoStarted must be called on the main UI thread.");
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Adapter called onVideoStarted.");
        try {
            this.zzcnx.zzt(zze.zzac(mediationRewardedVideoAdAdapter));
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call onVideoStarted.", (Throwable)ex);
        }
    }
}
