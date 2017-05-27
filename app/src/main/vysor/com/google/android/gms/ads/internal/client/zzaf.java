// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.reward.client.zzg;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzdv;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzic;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.AdListener;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zzaf
{
    private final Context mContext;
    private final zzh zzajr;
    private String zzang;
    private zza zzawb;
    private AdListener zzawc;
    private AppEventListener zzaxo;
    private final zzgp zzays;
    private Correlator zzayw;
    private zzu zzayx;
    private InAppPurchaseListener zzayy;
    private OnCustomRenderedAdLoadedListener zzayz;
    private PlayStorePurchaseListener zzaza;
    private String zzazb;
    private PublisherInterstitialAd zzazf;
    private boolean zzazg;
    private RewardedVideoAdListener zzgf;
    
    public zzaf(final Context context) {
        this(context, zzh.zzjb(), null);
    }
    
    public zzaf(final Context context, final PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzh.zzjb(), publisherInterstitialAd);
    }
    
    public zzaf(final Context mContext, final zzh zzajr, final PublisherInterstitialAd zzazf) {
        this.zzays = new zzgp();
        this.mContext = mContext;
        this.zzajr = zzajr;
        this.zzazf = zzazf;
    }
    
    private void zzap(final String s) throws RemoteException {
        if (this.zzang == null) {
            this.zzaq(s);
        }
        AdSizeParcel zzjc;
        if (this.zzazg) {
            zzjc = AdSizeParcel.zzjc();
        }
        else {
            zzjc = new AdSizeParcel();
        }
        this.zzayx = zzm.zzjs().zzb(this.mContext, zzjc, this.zzang, this.zzays);
        if (this.zzawc != null) {
            this.zzayx.zza(new zzc(this.zzawc));
        }
        if (this.zzawb != null) {
            this.zzayx.zza(new zzb(this.zzawb));
        }
        if (this.zzaxo != null) {
            this.zzayx.zza(new zzj(this.zzaxo));
        }
        if (this.zzayy != null) {
            this.zzayx.zza(new zzic(this.zzayy));
        }
        if (this.zzaza != null) {
            this.zzayx.zza(new zzig(this.zzaza), this.zzazb);
        }
        if (this.zzayz != null) {
            this.zzayx.zza(new zzdv(this.zzayz));
        }
        if (this.zzayw != null) {
            this.zzayx.zza(this.zzayw.zzdh());
        }
        if (this.zzgf != null) {
            this.zzayx.zza(new zzg(this.zzgf));
        }
    }
    
    private void zzaq(final String s) {
        if (this.zzayx == null) {
            throw new IllegalStateException(new StringBuilder(63 + String.valueOf(s).length()).append("The ad unit ID must be set on InterstitialAd before ").append(s).append(" is called.").toString());
        }
    }
    
    public AdListener getAdListener() {
        return this.zzawc;
    }
    
    public String getAdUnitId() {
        return this.zzang;
    }
    
    public AppEventListener getAppEventListener() {
        return this.zzaxo;
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zzayy;
    }
    
    public String getMediationAdapterClassName() {
        while (true) {
            try {
                if (this.zzayx != null) {
                    return this.zzayx.getMediationAdapterClassName();
                }
            }
            catch (RemoteException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get the mediation adapter class name.", (Throwable)ex);
            }
            return null;
        }
    }
    
    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzayz;
    }
    
    public boolean isLoaded() {
        boolean b = false;
        try {
            b = (this.zzayx != null && this.zzayx.isReady());
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is ready.", (Throwable)ex);
        }
        return b;
    }
    
    public boolean isLoading() {
        boolean b = false;
        try {
            b = (this.zzayx != null && this.zzayx.isLoading());
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is loading.", (Throwable)ex);
        }
        return b;
    }
    
    public void setAdListener(final AdListener zzawc) {
        try {
            this.zzawc = zzawc;
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                zzc zzc;
                if (zzawc != null) {
                    zzc = new zzc(zzawc);
                }
                else {
                    zzc = null;
                }
                zzayx.zza(zzc);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdListener.", (Throwable)ex);
        }
    }
    
    public void setAdUnitId(final String zzang) {
        if (this.zzang != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.zzang = zzang;
    }
    
    public void setAppEventListener(final AppEventListener zzaxo) {
        try {
            this.zzaxo = zzaxo;
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                zzj zzj;
                if (zzaxo != null) {
                    zzj = new zzj(zzaxo);
                }
                else {
                    zzj = null;
                }
                zzayx.zza(zzj);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void setCorrelator(final Correlator zzayw) {
        this.zzayw = zzayw;
        try {
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                zzy zzdh;
                if (this.zzayw == null) {
                    zzdh = null;
                }
                else {
                    zzdh = this.zzayw.zzdh();
                }
                zzayx.zza(zzdh);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set correlator.", (Throwable)ex);
        }
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener zzayy) {
        if (this.zzaza != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.zzayy = zzayy;
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                zzic zzic;
                if (zzayy != null) {
                    zzic = new zzic(zzayy);
                }
                else {
                    zzic = null;
                }
                zzayx.zza(zzic);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the InAppPurchaseListener.", (Throwable)ex);
        }
    }
    
    public void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener zzayz) {
        try {
            this.zzayz = zzayz;
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                zzdv zzdv;
                if (zzayz != null) {
                    zzdv = new zzdv(zzayz);
                }
                else {
                    zzdv = null;
                }
                zzayx.zza(zzdv);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the OnCustomRenderedAdLoadedListener.", (Throwable)ex);
        }
    }
    
    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener zzaza, final String zzazb) {
        if (this.zzayy != null) {
            throw new IllegalStateException("In app purchase parameter has already been set.");
        }
        try {
            this.zzaza = zzaza;
            this.zzazb = zzazb;
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                zzig zzig;
                if (zzaza != null) {
                    zzig = new zzig(zzaza);
                }
                else {
                    zzig = null;
                }
                zzayx.zza(zzig, zzazb);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the play store purchase parameter.", (Throwable)ex);
        }
    }
    
    public void setRewardedVideoAdListener(final RewardedVideoAdListener zzgf) {
        try {
            this.zzgf = zzgf;
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                zzg zzg;
                if (zzgf != null) {
                    zzg = new zzg(zzgf);
                }
                else {
                    zzg = null;
                }
                zzayx.zza(zzg);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdListener.", (Throwable)ex);
        }
    }
    
    public void show() {
        try {
            this.zzaq("show");
            this.zzayx.showInterstitial();
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to show interstitial.", (Throwable)ex);
        }
    }
    
    public void zza(final zza zzawb) {
        try {
            this.zzawb = zzawb;
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                zzb zzb;
                if (zzawb != null) {
                    zzb = new zzb(zzawb);
                }
                else {
                    zzb = null;
                }
                zzayx.zza(zzb);
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdClickListener.", (Throwable)ex);
        }
    }
    
    public void zza(final zzad zzad) {
        try {
            if (this.zzayx == null) {
                this.zzap("loadAd");
            }
            if (this.zzayx.zzb(this.zzajr.zza(this.mContext, zzad))) {
                this.zzays.zzh(zzad.zzkb());
            }
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public void zzd(final boolean zzazg) {
        this.zzazg = zzazg;
    }
}
