// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhy;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.internal.zzdz;
import android.widget.FrameLayout;
import com.google.android.gms.internal.zzgq;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.os.IBinder;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zziy;

@zziy
public class zzl
{
    private final Object zzakd;
    private zzx zzaxq;
    private final zze zzaxr;
    private final zzd zzaxs;
    private final zzai zzaxt;
    private final zzel zzaxu;
    private final zzf zzaxv;
    private final zzid zzaxw;
    private final zzho zzaxx;
    
    public zzl(final zze zzaxr, final zzd zzaxs, final zzai zzaxt, final zzel zzaxu, final zzf zzaxv, final zzid zzaxw, final zzho zzaxx) {
        this.zzakd = new Object();
        this.zzaxr = zzaxr;
        this.zzaxs = zzaxs;
        this.zzaxt = zzaxt;
        this.zzaxu = zzaxu;
        this.zzaxv = zzaxv;
        this.zzaxw = zzaxw;
        this.zzaxx = zzaxx;
    }
    
    private static boolean zza(final Activity activity, final String s) {
        boolean booleanExtra = false;
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra(s)) {
            zzb.e("useClientJar flag not found in activity intent extras.");
        }
        else {
            booleanExtra = intent.getBooleanExtra(s, false);
        }
        return booleanExtra;
    }
    
    private void zzc(final Context context, final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", s);
        zzm.zzjr().zza(context, null, "gmob-apps", bundle, true);
    }
    
    @Nullable
    private static zzx zzje() {
        zzx interface1;
        try {
            final Object instance = zzl.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (!(instance instanceof IBinder)) {
                zzb.zzdf("ClientApi class is not an instance of IBinder");
                interface1 = null;
            }
            else {
                interface1 = zzx.zza.asInterface((IBinder)instance);
            }
        }
        catch (Exception ex) {
            zzb.zzd("Failed to instantiate ClientApi class.", ex);
            interface1 = null;
        }
        return interface1;
    }
    
    @Nullable
    private zzx zzjf() {
        synchronized (this.zzakd) {
            if (this.zzaxq == null) {
                this.zzaxq = zzje();
            }
            return this.zzaxq;
        }
    }
    
    public zzu zza(final Context context, final AdSizeParcel adSizeParcel, final String s) {
        return this.zza(context, false, (zza<zzu>)new zza<zzu>() {
            public zzu zza(final zzx zzx) throws RemoteException {
                return zzx.createSearchAdManager(com.google.android.gms.dynamic.zze.zzac(context), adSizeParcel, s, 9683000);
            }
            
            public zzu zzjg() {
                zzu zza = zzl.this.zzaxr.zza(context, adSizeParcel, s, null, 3);
                if (zza == null) {
                    zzl.this.zzc(context, "search");
                    zza = new zzak();
                }
                return zza;
            }
        });
    }
    
    public zzu zza(final Context context, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq) {
        return this.zza(context, false, (zza<zzu>)new zza<zzu>() {
            public zzu zza(final zzx zzx) throws RemoteException {
                return zzx.createBannerAdManager(com.google.android.gms.dynamic.zze.zzac(context), adSizeParcel, s, zzgq, 9683000);
            }
            
            public zzu zzjg() {
                zzu zza = zzl.this.zzaxr.zza(context, adSizeParcel, s, zzgq, 1);
                if (zza == null) {
                    zzl.this.zzc(context, "banner");
                    zza = new zzak();
                }
                return zza;
            }
        });
    }
    
    public com.google.android.gms.ads.internal.reward.client.zzb zza(final Context context, final zzgq zzgq) {
        return this.zza(context, false, (zza<com.google.android.gms.ads.internal.reward.client.zzb>)new zza<com.google.android.gms.ads.internal.reward.client.zzb>() {
            public com.google.android.gms.ads.internal.reward.client.zzb zzf(final zzx zzx) throws RemoteException {
                return zzx.createRewardedVideoAd(com.google.android.gms.dynamic.zze.zzac(context), zzgq, 9683000);
            }
            
            public com.google.android.gms.ads.internal.reward.client.zzb zzjl() {
                com.google.android.gms.ads.internal.reward.client.zzb zzb = zzl.this.zzaxv.zzb(context, zzgq);
                if (zzb == null) {
                    zzl.this.zzc(context, "rewarded_video");
                    zzb = new zzan();
                }
                return zzb;
            }
        });
    }
    
    public zzdz zza(final Context context, final FrameLayout frameLayout, final FrameLayout frameLayout2) {
        return this.zza(context, false, (zza<zzdz>)new zza<zzdz>() {
            public zzdz zze(final zzx zzx) throws RemoteException {
                return zzx.createNativeAdViewDelegate(com.google.android.gms.dynamic.zze.zzac(frameLayout), com.google.android.gms.dynamic.zze.zzac(frameLayout2));
            }
            
            public zzdz zzjk() {
                zzdz zzb = zzl.this.zzaxu.zzb(context, frameLayout, frameLayout2);
                if (zzb == null) {
                    zzl.this.zzc(context, "native_ad_view_delegate");
                    zzb = new zzam();
                }
                return zzb;
            }
        });
    }
    
    @VisibleForTesting
     <T> T zza(final Context context, boolean b, final zza<T> zza) {
        if (!b && !zzm.zzjr().zzas(context)) {
            zzb.zzdd("Google Play Services is not available");
            b = true;
        }
        T t;
        if (b) {
            t = zza.zzjo();
            if (t == null) {
                t = zza.zzjp();
            }
        }
        else {
            t = zza.zzjp();
            if (t == null) {
                t = zza.zzjo();
            }
        }
        return t;
    }
    
    public zzs zzb(final Context context, final String s, final zzgq zzgq) {
        return this.zza(context, false, (zza<zzs>)new zza<zzs>() {
            public zzs zzc(final zzx zzx) throws RemoteException {
                return zzx.createAdLoaderBuilder(com.google.android.gms.dynamic.zze.zzac(context), s, zzgq, 9683000);
            }
            
            public zzs zzji() {
                zzs zza = zzl.this.zzaxs.zza(context, s, zzgq);
                if (zza == null) {
                    zzl.this.zzc(context, "native_ad");
                    zza = new zzaj();
                }
                return zza;
            }
        });
    }
    
    public zzu zzb(final Context context, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq) {
        return this.zza(context, false, (zza<zzu>)new zza<zzu>() {
            public zzu zza(final zzx zzx) throws RemoteException {
                return zzx.createInterstitialAdManager(com.google.android.gms.dynamic.zze.zzac(context), adSizeParcel, s, zzgq, 9683000);
            }
            
            public zzu zzjg() {
                zzu zza = zzl.this.zzaxr.zza(context, adSizeParcel, s, zzgq, 2);
                if (zza == null) {
                    zzl.this.zzc(context, "interstitial");
                    zza = new zzak();
                }
                return zza;
            }
        });
    }
    
    @Nullable
    public zzhy zzb(final Activity activity) {
        return this.zza((Context)activity, zza(activity, "com.google.android.gms.ads.internal.purchase.useClientJar"), (zza<zzhy>)new zza<zzhy>() {
            public zzhy zzg(final zzx zzx) throws RemoteException {
                return zzx.createInAppPurchaseManager(com.google.android.gms.dynamic.zze.zzac(activity));
            }
            
            public zzhy zzjm() {
                zzhy zzg = zzl.this.zzaxw.zzg(activity);
                if (zzg == null) {
                    zzl.this.zzc((Context)activity, "iap");
                    zzg = null;
                }
                return zzg;
            }
        });
    }
    
    @Nullable
    public zzhp zzc(final Activity activity) {
        return this.zza((Context)activity, zza(activity, "com.google.android.gms.ads.internal.overlay.useClientJar"), (zza<zzhp>)new zza<zzhp>() {
            public zzhp zzh(final zzx zzx) throws RemoteException {
                return zzx.createAdOverlay(com.google.android.gms.dynamic.zze.zzac(activity));
            }
            
            public zzhp zzjn() {
                zzhp zzf = zzl.this.zzaxx.zzf(activity);
                if (zzf == null) {
                    zzl.this.zzc((Context)activity, "ad_overlay");
                    zzf = null;
                }
                return zzf;
            }
        });
    }
    
    public zzz zzl(final Context context) {
        return this.zza(context, false, (zza<zzz>)new zza<zzz>() {
            public zzz zzd(final zzx zzx) throws RemoteException {
                return zzx.getMobileAdsSettingsManagerWithClientJarVersion(com.google.android.gms.dynamic.zze.zzac(context), 9683000);
            }
            
            public zzz zzjj() {
                zzz zzm = zzl.this.zzaxt.zzm(context);
                if (zzm == null) {
                    zzl.this.zzc(context, "mobile_ads_settings");
                    zzm = new zzal();
                }
                return zzm;
            }
        });
    }
    
    @VisibleForTesting
    abstract class zza<T>
    {
        @Nullable
        protected abstract T zzb(final zzx p0) throws RemoteException;
        
        @Nullable
        protected abstract T zzjh() throws RemoteException;
        
        @Nullable
        protected final T zzjo() {
            T zzb = null;
            final zzx zza = zzl.this.zzjf();
            if (zza == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("ClientApi class cannot be loaded.");
            }
            else {
                try {
                    zzb = this.zzb(zza);
                }
                catch (RemoteException ex) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot invoke local loader using ClientApi class", (Throwable)ex);
                    zzb = null;
                }
            }
            return zzb;
        }
        
        @Nullable
        protected final T zzjp() {
            try {
                return this.zzjh();
            }
            catch (RemoteException ex) {
                zzb.zzd("Cannot invoke remote loader", (Throwable)ex);
                return null;
            }
        }
    }
}
