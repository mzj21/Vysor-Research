// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzdv;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzic;
import com.google.android.gms.dynamic.zzd;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.zziy;

@zziy
public class zzae
{
    private final zzh zzajr;
    private VideoOptions zzakr;
    private boolean zzami;
    private String zzang;
    private zza zzawb;
    private AdListener zzawc;
    private AppEventListener zzaxo;
    private AdSize[] zzaxp;
    private final zzgp zzays;
    private final AtomicBoolean zzayt;
    private final VideoController zzayu;
    final zzo zzayv;
    private Correlator zzayw;
    private zzu zzayx;
    private InAppPurchaseListener zzayy;
    private OnCustomRenderedAdLoadedListener zzayz;
    private PlayStorePurchaseListener zzaza;
    private String zzazb;
    private ViewGroup zzazc;
    private boolean zzazd;
    
    public zzae(final ViewGroup viewGroup) {
        this(viewGroup, null, false, zzh.zzjb(), false);
    }
    
    public zzae(final ViewGroup viewGroup, final AttributeSet set, final boolean b) {
        this(viewGroup, set, b, zzh.zzjb(), false);
    }
    
    zzae(final ViewGroup zzazc, final AttributeSet set, final boolean b, final zzh zzajr, final zzu zzayx, final boolean zzazd) {
        this.zzays = new zzgp();
        this.zzayu = new VideoController();
        this.zzayv = new zzo() {
            @Override
            public void onAdFailedToLoad(final int n) {
                zzae.this.zzayu.zza(zzae.this.zzdj());
                super.onAdFailedToLoad(n);
            }
            
            @Override
            public void onAdLoaded() {
                zzae.this.zzayu.zza(zzae.this.zzdj());
                super.onAdLoaded();
            }
        };
        this.zzazc = zzazc;
        this.zzajr = zzajr;
        this.zzayx = zzayx;
        this.zzayt = new AtomicBoolean(false);
        this.zzazd = zzazd;
        if (set == null) {
            return;
        }
        final Context context = zzazc.getContext();
        try {
            final zzk zzk = new zzk(context, set);
            this.zzaxp = zzk.zzm(b);
            this.zzang = zzk.getAdUnitId();
            if (zzazc.isInEditMode()) {
                zzm.zzjr().zza(zzazc, zza(context, this.zzaxp[0], this.zzazd), "Ads by Google");
            }
        }
        catch (IllegalArgumentException ex) {
            zzm.zzjr().zza(zzazc, new AdSizeParcel(context, AdSize.BANNER), ex.getMessage(), ex.getMessage());
        }
    }
    
    zzae(final ViewGroup viewGroup, final AttributeSet set, final boolean b, final zzh zzh, final boolean b2) {
        this(viewGroup, set, b, zzh, null, b2);
    }
    
    public zzae(final ViewGroup viewGroup, final AttributeSet set, final boolean b, final boolean b2) {
        this(viewGroup, set, b, zzh.zzjb(), b2);
    }
    
    public zzae(final ViewGroup viewGroup, final boolean b) {
        this(viewGroup, null, false, zzh.zzjb(), b);
    }
    
    private static AdSizeParcel zza(final Context context, final AdSize adSize, final boolean b) {
        final AdSizeParcel adSizeParcel = new AdSizeParcel(context, adSize);
        adSizeParcel.zzl(b);
        return adSizeParcel;
    }
    
    private static AdSizeParcel zza(final Context context, final AdSize[] array, final boolean b) {
        final AdSizeParcel adSizeParcel = new AdSizeParcel(context, array);
        adSizeParcel.zzl(b);
        return adSizeParcel;
    }
    
    private void zzkf() {
        try {
            final zzd zzds = this.zzayx.zzds();
            if (zzds != null) {
                this.zzazc.addView((View)zze.zzae(zzds));
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to get an ad frame.", (Throwable)ex);
        }
    }
    
    public void destroy() {
        try {
            if (this.zzayx != null) {
                this.zzayx.destroy();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to destroy AdView.", (Throwable)ex);
        }
    }
    
    public AdListener getAdListener() {
        return this.zzawc;
    }
    
    public AdSize getAdSize() {
        while (true) {
            try {
                if (this.zzayx != null) {
                    final AdSizeParcel zzdt = this.zzayx.zzdt();
                    if (zzdt != null) {
                        return zzdt.zzjd();
                    }
                }
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to get the current AdSize.", (Throwable)ex);
            }
            if (this.zzaxp != null) {
                return this.zzaxp[0];
            }
            return null;
        }
    }
    
    public AdSize[] getAdSizes() {
        return this.zzaxp;
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
                zzb.zzd("Failed to get the mediation adapter class name.", (Throwable)ex);
            }
            return null;
        }
    }
    
    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzayz;
    }
    
    public VideoController getVideoController() {
        return this.zzayu;
    }
    
    public VideoOptions getVideoOptions() {
        return this.zzakr;
    }
    
    public boolean isLoading() {
        while (true) {
            try {
                if (this.zzayx != null) {
                    return this.zzayx.isLoading();
                }
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to check if ad is loading.", (Throwable)ex);
            }
            return false;
        }
    }
    
    public void pause() {
        try {
            if (this.zzayx != null) {
                this.zzayx.pause();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to call pause.", (Throwable)ex);
        }
    }
    
    public void recordManualImpression() {
        if (!this.zzayt.getAndSet(true)) {
            try {
                if (this.zzayx != null) {
                    this.zzayx.zzdv();
                }
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to record impression.", (Throwable)ex);
            }
        }
    }
    
    public void resume() {
        try {
            if (this.zzayx != null) {
                this.zzayx.resume();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to call resume.", (Throwable)ex);
        }
    }
    
    public void setAdListener(final AdListener zzawc) {
        this.zzawc = zzawc;
        this.zzayv.zza(zzawc);
    }
    
    public void setAdSizes(final AdSize... array) {
        if (this.zzaxp != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        this.zza(array);
    }
    
    public void setAdUnitId(final String zzang) {
        if (this.zzang != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
            zzb.zzd("Failed to set the AppEventListener.", (Throwable)ex);
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
            zzb.zzd("Failed to set correlator.", (Throwable)ex);
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
            zzb.zzd("Failed to set the InAppPurchaseListener.", (Throwable)ex);
        }
    }
    
    public void setManualImpressionsEnabled(final boolean zzami) {
        this.zzami = zzami;
        try {
            if (this.zzayx != null) {
                this.zzayx.setManualImpressionsEnabled(this.zzami);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set manual impressions.", (Throwable)ex);
        }
    }
    
    public void setOnCustomRenderedAdLoadedListener(final OnCustomRenderedAdLoadedListener zzayz) {
        this.zzayz = zzayz;
        try {
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
            zzb.zzd("Failed to set the onCustomRenderedAdLoadedListener.", (Throwable)ex);
        }
    }
    
    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener zzaza, final String zzazb) {
        if (this.zzayy != null) {
            throw new IllegalStateException("InAppPurchaseListener has already been set.");
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
            zzb.zzd("Failed to set the play store purchase parameter.", (Throwable)ex);
        }
    }
    
    public void setVideoOptions(final VideoOptions zzakr) {
        this.zzakr = zzakr;
        try {
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                VideoOptionsParcel videoOptionsParcel;
                if (zzakr == null) {
                    videoOptionsParcel = null;
                }
                else {
                    videoOptionsParcel = new VideoOptionsParcel(zzakr);
                }
                zzayx.zza(videoOptionsParcel);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set video options.", (Throwable)ex);
        }
    }
    
    public void zza(final zza zzawb) {
        try {
            this.zzawb = zzawb;
            if (this.zzayx != null) {
                final zzu zzayx = this.zzayx;
                com.google.android.gms.ads.internal.client.zzb zzb;
                if (zzawb != null) {
                    zzb = new com.google.android.gms.ads.internal.client.zzb(zzawb);
                }
                else {
                    zzb = null;
                }
                zzayx.zza(zzb);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to set the AdClickListener.", (Throwable)ex);
        }
    }
    
    public void zza(final zzad zzad) {
        try {
            if (this.zzayx == null) {
                this.zzkg();
            }
            if (this.zzayx.zzb(this.zzajr.zza(this.zzazc.getContext(), zzad))) {
                this.zzays.zzh(zzad.zzkb());
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to load ad.", (Throwable)ex);
        }
    }
    
    public void zza(final AdSize... zzaxp) {
        this.zzaxp = zzaxp;
        while (true) {
            try {
                if (this.zzayx != null) {
                    this.zzayx.zza(zza(this.zzazc.getContext(), this.zzaxp, this.zzazd));
                }
                this.zzazc.requestLayout();
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to set the ad size.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public boolean zzb(final AdSizeParcel adSizeParcel) {
        return "search_v2".equals(adSizeParcel.zzaxi);
    }
    
    public zzab zzdj() {
        final zzu zzayx = this.zzayx;
        zzab zzdw = null;
        if (zzayx != null) {
            try {
                zzdw = this.zzayx.zzdw();
            }
            catch (RemoteException ex) {
                zzb.zzd("Failed to retrieve VideoController.", (Throwable)ex);
                zzdw = null;
            }
        }
        return zzdw;
    }
    
    void zzkg() throws RemoteException {
        if ((this.zzaxp == null || this.zzang == null) && this.zzayx == null) {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        (this.zzayx = this.zzkh()).zza(new zzc(this.zzayv));
        if (this.zzawb != null) {
            this.zzayx.zza(new com.google.android.gms.ads.internal.client.zzb(this.zzawb));
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
        if (this.zzakr != null) {
            this.zzayx.zza(new VideoOptionsParcel(this.zzakr));
        }
        this.zzayx.setManualImpressionsEnabled(this.zzami);
        this.zzkf();
    }
    
    protected zzu zzkh() throws RemoteException {
        final Context context = this.zzazc.getContext();
        final AdSizeParcel zza = zza(context, this.zzaxp, this.zzazd);
        zzu zzu;
        if (this.zzb(zza)) {
            zzu = zzm.zzjs().zza(context, zza, this.zzang);
        }
        else {
            zzu = zzm.zzjs().zza(context, zza, this.zzang, this.zzays);
        }
        return zzu;
    }
}
