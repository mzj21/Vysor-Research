// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzz;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.ads.internal.reward.client.zzb;
import android.widget.FrameLayout;
import com.google.android.gms.internal.zzdz;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzhy;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzu;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.app.Activity;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.dynamic.zze;
import android.content.Context;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.common.util.DynamiteApi;
import android.support.annotation.Keep;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.internal.client.zzx;

@zziy
@Keep
@DynamiteApi
public class ClientApi extends zzx.zza
{
    public zzs createAdLoaderBuilder(final zzd zzd, final String s, final zzgq zzgq, final int n) {
        return new zzk(zze.zzae(zzd), s, zzgq, new VersionInfoParcel(9683000, n, true), zzd.zzeq());
    }
    
    public zzhp createAdOverlay(final zzd zzd) {
        return new com.google.android.gms.ads.internal.overlay.zzd(zze.zzae(zzd));
    }
    
    public zzu createBannerAdManager(final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final int n) throws RemoteException {
        return new zzf(zze.zzae(zzd), adSizeParcel, s, zzgq, new VersionInfoParcel(9683000, n, true), zzd.zzeq());
    }
    
    public zzhy createInAppPurchaseManager(final zzd zzd) {
        return new com.google.android.gms.ads.internal.purchase.zze(zze.zzae(zzd));
    }
    
    public zzu createInterstitialAdManager(final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final int n) throws RemoteException {
        final Context context = zze.zzae(zzd);
        zzdi.initialize(context);
        final VersionInfoParcel versionInfoParcel = new VersionInfoParcel(9683000, n, true);
        final boolean equals = "reward_mb".equals(adSizeParcel.zzaxi);
        int n2;
        if ((!equals && zzdi.zzbdi.get()) || (equals && zzdi.zzbdj.get())) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        zzu.zza zza;
        if (n2 != 0) {
            zza = new zzft(context, s, zzgq, versionInfoParcel, zzd.zzeq());
        }
        else {
            zza = new zzl(context, adSizeParcel, s, zzgq, versionInfoParcel, zzd.zzeq());
        }
        return zza;
    }
    
    public zzdz createNativeAdViewDelegate(final zzd zzd, final zzd zzd2) {
        return new com.google.android.gms.ads.internal.formats.zzl(zze.zzae(zzd), zze.zzae(zzd2));
    }
    
    public zzb createRewardedVideoAd(final zzd zzd, final zzgq zzgq, final int n) {
        return new zzjq(zze.zzae(zzd), zzd.zzeq(), zzgq, new VersionInfoParcel(9683000, n, true));
    }
    
    public zzu createSearchAdManager(final zzd zzd, final AdSizeParcel adSizeParcel, final String s, final int n) throws RemoteException {
        return new zzt(zze.zzae(zzd), adSizeParcel, s, new VersionInfoParcel(9683000, n, true));
    }
    
    @Nullable
    public zzz getMobileAdsSettingsManager(final zzd zzd) {
        return null;
    }
    
    public zzz getMobileAdsSettingsManagerWithClientJarVersion(final zzd zzd, final int n) {
        return zzo.zza(zze.zzae(zzd), new VersionInfoParcel(9683000, n, true));
    }
}
