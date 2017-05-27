// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.ThinAdSizeParcel;
import com.google.android.gms.dynamic.zze;
import android.view.ViewParent;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.internal.zzkk;
import android.view.View;
import com.google.android.gms.internal.zzkf;
import java.util.HashSet;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.internal.zzcr;
import android.os.Bundle;
import com.google.android.gms.internal.zzcu;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import android.text.TextUtils;
import com.google.android.gms.internal.zzke;
import android.os.Debug;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdi;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.Timer;
import android.location.Location;
import com.google.android.gms.ads.internal.client.zzf;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzcm;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkj;
import com.google.android.gms.internal.zzil;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.client.zzu;

@zziy
public abstract class zza extends zzu.zza implements com.google.android.gms.ads.internal.client.zza, zzp, com.google.android.gms.ads.internal.request.zza.zza, zzer, zzil.zza, zzkj
{
    protected zzdq zzalg;
    protected zzdo zzalh;
    protected zzdo zzali;
    protected boolean zzalj;
    protected final zzr zzalk;
    protected final zzv zzall;
    @Nullable
    protected transient AdRequestParcel zzalm;
    protected final zzcm zzaln;
    protected final zzd zzalo;
    
    zza(final zzv zzall, @Nullable zzr zzalk, final zzd zzalo) {
        this.zzalj = false;
        this.zzall = zzall;
        if (zzalk == null) {
            zzalk = new zzr(this);
        }
        this.zzalk = zzalk;
        this.zzalo = zzalo;
        com.google.android.gms.ads.internal.zzu.zzfz().zzad(this.zzall.zzahn);
        com.google.android.gms.ads.internal.zzu.zzgd().zzb(this.zzall.zzahn, this.zzall.zzaqv);
        com.google.android.gms.ads.internal.zzu.zzge().initialize(this.zzall.zzahn);
        this.zzaln = com.google.android.gms.ads.internal.zzu.zzgd().zztx();
        com.google.android.gms.ads.internal.zzu.zzgc().initialize(this.zzall.zzahn);
        this.zzdq();
    }
    
    private AdRequestParcel zza(AdRequestParcel zzja) {
        if (zzi.zzcm(this.zzall.zzahn) && zzja.zzawl != null) {
            zzja = new zzf(zzja).zza(null).zzja();
        }
        return zzja;
    }
    
    private TimerTask zza(final Timer timer, final CountDownLatch countDownLatch) {
        return new TimerTask() {
            @Override
            public void run() {
                Label_0052: {
                    if (zzdi.zzbgi.get() == countDownLatch.getCount()) {
                        break Label_0052;
                    }
                    zzb.zzdd("Stopping method tracing");
                    Debug.stopMethodTracing();
                    if (countDownLatch.getCount() != 0L) {
                        break Label_0052;
                    }
                    timer.cancel();
                    return;
                }
                final String concat = String.valueOf(zza.this.zzall.zzahn.getPackageName()).concat("_adsTrace_");
                try {
                    zzb.zzdd("Starting method tracing");
                    countDownLatch.countDown();
                    Debug.startMethodTracing(new StringBuilder(20 + String.valueOf(concat).length()).append(concat).append(com.google.android.gms.ads.internal.zzu.zzgf().currentTimeMillis()).toString(), (int)zzdi.zzbgj.get());
                }
                catch (Exception ex) {
                    zzb.zzd("Exception occurred while starting method tracing.", ex);
                }
            }
        };
    }
    
    private void zzd(final zzke zzke) {
        if (com.google.android.gms.ads.internal.zzu.zzgh().zzuu() && !zzke.zzcoo && !TextUtils.isEmpty((CharSequence)zzke.zzchy)) {
            zzb.zzdd("Sending troubleshooting signals to the server.");
            com.google.android.gms.ads.internal.zzu.zzgh().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, zzke.zzchy, this.zzall.zzaqt);
            zzke.zzcoo = true;
        }
    }
    
    private void zzdq() {
        if (zzdi.zzbgg.get()) {
            final Timer timer = new Timer();
            timer.schedule(this.zza(timer, new CountDownLatch(zzdi.zzbgi.get())), 0L, zzdi.zzbgh.get());
        }
    }
    
    public void destroy() {
        zzac.zzhq("destroy must be called on the main UI thread.");
        this.zzalk.cancel();
        this.zzaln.zzk(this.zzall.zzara);
        this.zzall.destroy();
    }
    
    public boolean isLoading() {
        return this.zzalj;
    }
    
    public boolean isReady() {
        zzac.zzhq("isLoaded must be called on the main UI thread.");
        return this.zzall.zzaqx == null && this.zzall.zzaqy == null && this.zzall.zzara != null;
    }
    
    @Override
    public void onAdClicked() {
        if (this.zzall.zzara == null) {
            zzb.zzdf("Ad state was null when trying to ping click URLs.");
        }
        else {
            zzb.zzdd("Pinging click URLs.");
            if (this.zzall.zzarc != null) {
                this.zzall.zzarc.zzta();
            }
            if (this.zzall.zzara.zzbsd != null) {
                com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara.zzbsd);
            }
            if (this.zzall.zzard != null) {
                try {
                    this.zzall.zzard.onAdClicked();
                }
                catch (RemoteException ex) {
                    zzb.zzd("Could not notify onAdClicked event.", (Throwable)ex);
                }
            }
        }
    }
    
    @Override
    public void onAppEvent(final String s, @Nullable final String s2) {
        if (this.zzall.zzarf == null) {
            return;
        }
        try {
            this.zzall.zzarf.onAppEvent(s, s2);
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not call the AppEventListener.", (Throwable)ex);
        }
    }
    
    public void pause() {
        zzac.zzhq("pause must be called on the main UI thread.");
    }
    
    public void resume() {
        zzac.zzhq("resume must be called on the main UI thread.");
    }
    
    public void setManualImpressionsEnabled(final boolean b) {
        throw new UnsupportedOperationException("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }
    
    public void setUserId(final String s) {
        zzb.zzdf("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }
    
    public void stopLoading() {
        zzac.zzhq("stopLoading must be called on the main UI thread.");
        this.zzalj = false;
        this.zzall.zzi(true);
    }
    
    Bundle zza(@Nullable final zzcu zzcu) {
        Bundle bundle;
        if (zzcu == null) {
            bundle = null;
        }
        else {
            if (zzcu.zziq()) {
                zzcu.wakeup();
            }
            final zzcr zzio = zzcu.zzio();
            String s;
            String zzif;
            if (zzio != null) {
                s = zzio.zzie();
                zzif = zzio.zzif();
                final String value = String.valueOf(zzio.toString());
                String concat;
                if (value.length() != 0) {
                    concat = "In AdManager: loadAd, ".concat(value);
                }
                else {
                    concat = new String("In AdManager: loadAd, ");
                }
                zzb.zzdd(concat);
                if (s != null) {
                    com.google.android.gms.ads.internal.zzu.zzgd().zzct(s);
                }
            }
            else {
                s = com.google.android.gms.ads.internal.zzu.zzgd().zztq();
                zzif = null;
            }
            if (s != null) {
                bundle = new Bundle(1);
                bundle.putString("fingerprint", s);
                if (!s.equals(zzif)) {
                    bundle.putString("v_fp", zzif);
                }
            }
            else {
                bundle = null;
            }
        }
        return bundle;
    }
    
    public void zza(final AdSizeParcel zzaqz) {
        zzac.zzhq("setAdSize must be called on the main UI thread.");
        this.zzall.zzaqz = zzaqz;
        if (this.zzall.zzara != null && this.zzall.zzara.zzbyh != null && this.zzall.zzarv == 0) {
            this.zzall.zzara.zzbyh.zza(zzaqz);
        }
        if (this.zzall.zzaqw != null) {
            if (this.zzall.zzaqw.getChildCount() > 1) {
                this.zzall.zzaqw.removeView(this.zzall.zzaqw.getNextView());
            }
            this.zzall.zzaqw.setMinimumWidth(zzaqz.widthPixels);
            this.zzall.zzaqw.setMinimumHeight(zzaqz.heightPixels);
            this.zzall.zzaqw.requestLayout();
        }
    }
    
    public void zza(@Nullable final VideoOptionsParcel zzaro) {
        zzac.zzhq("setVideoOptions must be called on the main UI thread.");
        this.zzall.zzaro = zzaro;
    }
    
    public void zza(final com.google.android.gms.ads.internal.client.zzp zzard) {
        zzac.zzhq("setAdListener must be called on the main UI thread.");
        this.zzall.zzard = zzard;
    }
    
    public void zza(final zzq zzare) {
        zzac.zzhq("setAdListener must be called on the main UI thread.");
        this.zzall.zzare = zzare;
    }
    
    public void zza(final zzw zzarf) {
        zzac.zzhq("setAppEventListener must be called on the main UI thread.");
        this.zzall.zzarf = zzarf;
    }
    
    public void zza(final zzy zzarg) {
        zzac.zzhq("setCorrelationIdProvider must be called on the main UI thread");
        this.zzall.zzarg = zzarg;
    }
    
    public void zza(final com.google.android.gms.ads.internal.reward.client.zzd zzarq) {
        zzac.zzhq("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzall.zzarq = zzarq;
    }
    
    protected void zza(@Nullable final RewardItemParcel rewardItemParcel) {
        if (this.zzall.zzarq != null) {
            try {
                String type = "";
                int zzcny = 0;
                if (rewardItemParcel != null) {
                    type = rewardItemParcel.type;
                    zzcny = rewardItemParcel.zzcny;
                }
                this.zzall.zzarq.zza(new zzjp(type, zzcny));
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewarded().", (Throwable)ex);
            }
        }
    }
    
    public void zza(final zzdu zzdu) {
        throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
    }
    
    public void zza(final zzhx zzhx) {
        throw new IllegalStateException("setInAppPurchaseListener is not supported for current ad type");
    }
    
    public void zza(final zzib zzib, final String s) {
        throw new IllegalStateException("setPlayStorePurchaseParams is not supported for current ad type");
    }
    
    @Override
    public void zza(final zzke.zza zzarb) {
        if (zzarb.zzcop.zzchg != -1L && !TextUtils.isEmpty((CharSequence)zzarb.zzcop.zzchp)) {
            final long zzu = this.zzu(zzarb.zzcop.zzchp);
            if (zzu != -1L) {
                this.zzalg.zza(this.zzalg.zzc(zzu + zzarb.zzcop.zzchg), "stc");
            }
        }
        this.zzalg.zzav(zzarb.zzcop.zzchp);
        this.zzalg.zza(this.zzalh, "arf");
        this.zzali = this.zzalg.zzla();
        this.zzalg.zzh("gqi", zzarb.zzcop.zzchq);
        this.zzall.zzaqx = null;
        this.zza(this.zzall.zzarb = zzarb, this.zzalg);
    }
    
    protected abstract void zza(final zzke.zza p0, final zzdq p1);
    
    @Override
    public void zza(final HashSet<zzkf> set) {
        this.zzall.zza(set);
    }
    
    protected abstract boolean zza(final AdRequestParcel p0, final zzdq p1);
    
    boolean zza(final zzke zzke) {
        return false;
    }
    
    protected abstract boolean zza(@Nullable final zzke p0, final zzke p1);
    
    protected void zzb(final View view) {
        final zzv.zza zzaqw = this.zzall.zzaqw;
        if (zzaqw != null) {
            zzaqw.addView(view, com.google.android.gms.ads.internal.zzu.zzgb().zzup());
        }
    }
    
    @Override
    public void zzb(final zzke zzara) {
        this.zzalg.zza(this.zzali, "awr");
        this.zzall.zzaqy = null;
        if (zzara.errorCode != -2 && zzara.errorCode != 3) {
            com.google.android.gms.ads.internal.zzu.zzgd().zzb(this.zzall.zzgy());
        }
        if (zzara.errorCode == -1) {
            this.zzalj = false;
        }
        else {
            if (this.zza(zzara)) {
                zzb.zzdd("Ad refresh scheduled.");
            }
            if (zzara.errorCode != -2) {
                this.zzh(zzara.errorCode);
            }
            else {
                if (this.zzall.zzart == null) {
                    this.zzall.zzart = new zzkk(this.zzall.zzaqt);
                }
                this.zzaln.zzj(this.zzall.zzara);
                if (this.zza(this.zzall.zzara, zzara)) {
                    this.zzall.zzara = zzara;
                    this.zzall.zzhh();
                    final zzdq zzalg = this.zzalg;
                    String s;
                    if (this.zzall.zzara.zzib()) {
                        s = "1";
                    }
                    else {
                        s = "0";
                    }
                    zzalg.zzh("is_mraid", s);
                    final zzdq zzalg2 = this.zzalg;
                    String s2;
                    if (this.zzall.zzara.zzchc) {
                        s2 = "1";
                    }
                    else {
                        s2 = "0";
                    }
                    zzalg2.zzh("is_mediation", s2);
                    if (this.zzall.zzara.zzbyh != null && this.zzall.zzara.zzbyh.zzvr() != null) {
                        final zzdq zzalg3 = this.zzalg;
                        String s3;
                        if (this.zzall.zzara.zzbyh.zzvr().zzwm()) {
                            s3 = "1";
                        }
                        else {
                            s3 = "0";
                        }
                        zzalg3.zzh("is_delay_pl", s3);
                    }
                    this.zzalg.zza(this.zzalh, "ttc");
                    if (com.google.android.gms.ads.internal.zzu.zzgd().zztm() != null) {
                        com.google.android.gms.ads.internal.zzu.zzgd().zztm().zza(this.zzalg);
                    }
                    if (this.zzall.zzhc()) {
                        this.zzea();
                    }
                }
                if (zzara.zzbsg != null) {
                    com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn, zzara.zzbsg);
                }
            }
        }
    }
    
    public boolean zzb(final AdRequestParcel adRequestParcel) {
        zzac.zzhq("loadAd must be called on the main UI thread.");
        com.google.android.gms.ads.internal.zzu.zzge().zzit();
        if (zzdi.zzbdg.get()) {
            AdRequestParcel.zzj(adRequestParcel);
        }
        final AdRequestParcel zza = this.zza(adRequestParcel);
        boolean zzalj;
        if (this.zzall.zzaqx != null || this.zzall.zzaqy != null) {
            if (this.zzalm != null) {
                zzb.zzdf("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
            }
            else {
                zzb.zzdf("Loading already in progress, saving this object for future refreshes.");
            }
            this.zzalm = zza;
            zzalj = false;
        }
        else {
            zzb.zzde("Starting ad request.");
            this.zzdr();
            this.zzalh = this.zzalg.zzla();
            if (!zza.zzawg) {
                final String value = String.valueOf(zzm.zzjr().zzar(this.zzall.zzahn));
                zzb.zzde(new StringBuilder(71 + String.valueOf(value).length()).append("Use AdRequest.Builder.addTestDevice(\"").append(value).append("\") to get test ads on this device.").toString());
            }
            this.zzalk.zzg(zza);
            this.zzalj = this.zza(zza, this.zzalg);
            zzalj = this.zzalj;
        }
        return zzalj;
    }
    
    protected void zzc(@Nullable final zzke zzke) {
        if (zzke == null) {
            zzb.zzdf("Ad state was null when trying to ping impression URLs.");
        }
        else {
            zzb.zzdd("Pinging Impression URLs.");
            if (this.zzall.zzarc != null) {
                this.zzall.zzarc.zzsz();
            }
            if (zzke.zzbse != null && !zzke.zzcom) {
                com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, zzke.zzbse);
                zzke.zzcom = true;
                this.zzd(zzke);
            }
        }
    }
    
    protected boolean zzc(final AdRequestParcel adRequestParcel) {
        boolean zza;
        if (this.zzall.zzaqw == null) {
            zza = false;
        }
        else {
            final ViewParent parent = this.zzall.zzaqw.getParent();
            if (!(parent instanceof View)) {
                zza = false;
            }
            else {
                final View view = (View)parent;
                zza = com.google.android.gms.ads.internal.zzu.zzfz().zza(view, view.getContext());
            }
        }
        return zza;
    }
    
    public void zzd(final AdRequestParcel adRequestParcel) {
        if (this.zzc(adRequestParcel)) {
            this.zzb(adRequestParcel);
        }
        else {
            zzb.zzde("Ad is not visible. Not refreshing ad.");
            this.zzalk.zzh(adRequestParcel);
        }
    }
    
    public zzd zzdp() {
        return this.zzalo;
    }
    
    public void zzdr() {
        this.zzalg = new zzdq(zzdi.zzbca.get(), "load_ad", this.zzall.zzaqz.zzaxi);
        this.zzalh = new zzdo(-1L, null, null);
        this.zzali = new zzdo(-1L, null, null);
    }
    
    public com.google.android.gms.dynamic.zzd zzds() {
        zzac.zzhq("getAdFrame must be called on the main UI thread.");
        return zze.zzac(this.zzall.zzaqw);
    }
    
    @Nullable
    public AdSizeParcel zzdt() {
        zzac.zzhq("getAdSize must be called on the main UI thread.");
        AdSizeParcel adSizeParcel;
        if (this.zzall.zzaqz == null) {
            adSizeParcel = null;
        }
        else {
            adSizeParcel = new ThinAdSizeParcel(this.zzall.zzaqz);
        }
        return adSizeParcel;
    }
    
    @Override
    public void zzdu() {
        this.zzdy();
    }
    
    public void zzdv() {
        zzac.zzhq("recordManualImpression must be called on the main UI thread.");
        if (this.zzall.zzara == null) {
            zzb.zzdf("Ad state was null when trying to ping manual tracking URLs.");
        }
        else {
            zzb.zzdd("Pinging manual tracking URLs.");
            if (this.zzall.zzara.zzche != null && !this.zzall.zzara.zzcon) {
                com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara.zzche);
                this.zzall.zzara.zzcon = true;
                this.zzd(this.zzall.zzara);
            }
        }
    }
    
    public zzab zzdw() {
        return null;
    }
    
    protected void zzdx() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc_w           "Ad closing."
        //     3: invokestatic    com/google/android/gms/internal/zzkn.zzde:(Ljava/lang/String;)V
        //     6: aload_0        
        //     7: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    10: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    13: ifnull          28
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    20: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    23: invokeinterface com/google/android/gms/ads/internal/client/zzq.onAdClosed:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    32: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    35: ifnull          50
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    42: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    45: invokeinterface com/google/android/gms/ads/internal/reward/client/zzd.onRewardedVideoAdClosed:()V
        //    50: return         
        //    51: astore_2       
        //    52: ldc_w           "Could not call AdListener.onAdClosed()."
        //    55: aload_2        
        //    56: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    59: goto            28
        //    62: astore_1       
        //    63: ldc_w           "Could not call RewardedVideoAdListener.onRewardedVideoAdClosed()."
        //    66: aload_1        
        //    67: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    70: goto            50
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  16     28     51     62     Landroid/os/RemoteException;
        //  38     50     62     73     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void zzdy() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc_w           "Ad leaving application."
        //     3: invokestatic    com/google/android/gms/internal/zzkn.zzde:(Ljava/lang/String;)V
        //     6: aload_0        
        //     7: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    10: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    13: ifnull          28
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    20: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    23: invokeinterface com/google/android/gms/ads/internal/client/zzq.onAdLeftApplication:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    32: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    35: ifnull          50
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    42: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    45: invokeinterface com/google/android/gms/ads/internal/reward/client/zzd.onRewardedVideoAdLeftApplication:()V
        //    50: return         
        //    51: astore_2       
        //    52: ldc_w           "Could not call AdListener.onAdLeftApplication()."
        //    55: aload_2        
        //    56: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    59: goto            28
        //    62: astore_1       
        //    63: ldc_w           "Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication()."
        //    66: aload_1        
        //    67: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    70: goto            50
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  16     28     51     62     Landroid/os/RemoteException;
        //  38     50     62     73     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void zzdz() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc_w           "Ad opening."
        //     3: invokestatic    com/google/android/gms/internal/zzkn.zzde:(Ljava/lang/String;)V
        //     6: aload_0        
        //     7: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    10: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    13: ifnull          28
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    20: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    23: invokeinterface com/google/android/gms/ads/internal/client/zzq.onAdOpened:()V
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    32: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    35: ifnull          50
        //    38: aload_0        
        //    39: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    42: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    45: invokeinterface com/google/android/gms/ads/internal/reward/client/zzd.onRewardedVideoAdOpened:()V
        //    50: return         
        //    51: astore_2       
        //    52: ldc_w           "Could not call AdListener.onAdOpened()."
        //    55: aload_2        
        //    56: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    59: goto            28
        //    62: astore_1       
        //    63: ldc_w           "Could not call RewardedVideoAdListener.onRewardedVideoAdOpened()."
        //    66: aload_1        
        //    67: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    70: goto            50
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  16     28     51     62     Landroid/os/RemoteException;
        //  38     50     62     73     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void zzea() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc_w           "Ad finished loading."
        //     3: invokestatic    com/google/android/gms/internal/zzkn.zzde:(Ljava/lang/String;)V
        //     6: aload_0        
        //     7: iconst_0       
        //     8: putfield        com/google/android/gms/ads/internal/zza.zzalj:Z
        //    11: aload_0        
        //    12: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    15: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    18: ifnull          33
        //    21: aload_0        
        //    22: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    25: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    28: invokeinterface com/google/android/gms/ads/internal/client/zzq.onAdLoaded:()V
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    37: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    40: ifnull          55
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    47: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    50: invokeinterface com/google/android/gms/ads/internal/reward/client/zzd.onRewardedVideoAdLoaded:()V
        //    55: return         
        //    56: astore_2       
        //    57: ldc_w           "Could not call AdListener.onAdLoaded()."
        //    60: aload_2        
        //    61: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    64: goto            33
        //    67: astore_1       
        //    68: ldc_w           "Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded()."
        //    71: aload_1        
        //    72: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    75: goto            55
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  21     33     56     67     Landroid/os/RemoteException;
        //  43     55     67     78     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void zzeb() {
        if (this.zzall.zzarq != null) {
            try {
                this.zzall.zzarq.onRewardedVideoStarted();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not call RewardedVideoAdListener.onVideoStarted().", (Throwable)ex);
            }
        }
    }
    
    protected void zzh(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: bipush          30
        //     6: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //     9: ldc_w           "Failed to load ad: "
        //    12: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    15: iload_1        
        //    16: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    19: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    22: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //    25: aload_0        
        //    26: iconst_0       
        //    27: putfield        com/google/android/gms/ads/internal/zza.zzalj:Z
        //    30: aload_0        
        //    31: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    34: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    37: ifnull          53
        //    40: aload_0        
        //    41: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    44: getfield        com/google/android/gms/ads/internal/zzv.zzare:Lcom/google/android/gms/ads/internal/client/zzq;
        //    47: iload_1        
        //    48: invokeinterface com/google/android/gms/ads/internal/client/zzq.onAdFailedToLoad:(I)V
        //    53: aload_0        
        //    54: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    57: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    60: ifnull          76
        //    63: aload_0        
        //    64: getfield        com/google/android/gms/ads/internal/zza.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    67: getfield        com/google/android/gms/ads/internal/zzv.zzarq:Lcom/google/android/gms/ads/internal/reward/client/zzd;
        //    70: iload_1        
        //    71: invokeinterface com/google/android/gms/ads/internal/reward/client/zzd.onRewardedVideoAdFailedToLoad:(I)V
        //    76: return         
        //    77: astore_3       
        //    78: ldc_w           "Could not call AdListener.onAdFailedToLoad()."
        //    81: aload_3        
        //    82: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    85: goto            53
        //    88: astore_2       
        //    89: ldc_w           "Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad()."
        //    92: aload_2        
        //    93: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    96: goto            76
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  40     53     77     88     Landroid/os/RemoteException;
        //  63     76     88     99     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    long zzu(final String s) {
        final int index = s.indexOf("ufe");
        int n = s.indexOf(44, index);
        if (n == -1) {
            n = s.length();
        }
        final int n2 = index + 4;
        try {
            return Long.parseLong(s.substring(n2, n));
        }
        catch (IndexOutOfBoundsException ex) {
            zzb.zzdf("Invalid index for Url fetch time in CSI latency info.");
        }
        catch (NumberFormatException ex2) {
            zzb.zzdf("Cannot find valid format of Url fetch time in CSI latency info.");
            goto Label_0058;
        }
    }
}
