// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzf implements Parcelable$Creator<AdOverlayInfoParcel>
{
    static void zza(final AdOverlayInfoParcel adOverlayInfoParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, adOverlayInfoParcel.versionCode);
        zzb.zza(parcel, 2, (Parcelable)adOverlayInfoParcel.zzbye, n, false);
        zzb.zza(parcel, 3, adOverlayInfoParcel.zzpc(), false);
        zzb.zza(parcel, 4, adOverlayInfoParcel.zzpd(), false);
        zzb.zza(parcel, 5, adOverlayInfoParcel.zzpe(), false);
        zzb.zza(parcel, 6, adOverlayInfoParcel.zzpf(), false);
        zzb.zza(parcel, 7, adOverlayInfoParcel.zzbyj, false);
        zzb.zza(parcel, 8, adOverlayInfoParcel.zzbyk);
        zzb.zza(parcel, 9, adOverlayInfoParcel.zzbyl, false);
        zzb.zza(parcel, 10, adOverlayInfoParcel.zzph(), false);
        zzb.zzc(parcel, 11, adOverlayInfoParcel.orientation);
        zzb.zzc(parcel, 12, adOverlayInfoParcel.zzbyn);
        zzb.zza(parcel, 13, adOverlayInfoParcel.url, false);
        zzb.zza(parcel, 14, (Parcelable)adOverlayInfoParcel.zzaqv, n, false);
        zzb.zza(parcel, 15, adOverlayInfoParcel.zzpg(), false);
        zzb.zza(parcel, 16, adOverlayInfoParcel.zzbyp, false);
        zzb.zza(parcel, 17, (Parcelable)adOverlayInfoParcel.zzbyq, n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public AdOverlayInfoParcel[] zzai(final int n) {
        return new AdOverlayInfoParcel[n];
    }
    
    public AdOverlayInfoParcel zzk(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        AdLauncherIntentInfoParcel adLauncherIntentInfoParcel = null;
        IBinder zzr = null;
        IBinder zzr2 = null;
        IBinder zzr3 = null;
        IBinder zzr4 = null;
        String zzq = null;
        boolean zzc = false;
        String zzq2 = null;
        IBinder zzr5 = null;
        int zzg2 = 0;
        int zzg3 = 0;
        String zzq3 = null;
        VersionInfoParcel versionInfoParcel = null;
        IBinder zzr6 = null;
        String zzq4 = null;
        InterstitialAdParameterParcel interstitialAdParameterParcel = null;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    adLauncherIntentInfoParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<AdLauncherIntentInfoParcel>)AdLauncherIntentInfoParcel.CREATOR);
                    continue;
                }
                case 3: {
                    zzr = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzr2 = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzr3 = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzr4 = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 9: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzr5 = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 11: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 12: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 13: {
                    zzq3 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 14: {
                    versionInfoParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<VersionInfoParcel>)VersionInfoParcel.CREATOR);
                    continue;
                }
                case 15: {
                    zzr6 = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 16: {
                    zzq4 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 17: {
                    interstitialAdParameterParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<InterstitialAdParameterParcel>)InterstitialAdParameterParcel.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new AdOverlayInfoParcel(zzg, adLauncherIntentInfoParcel, zzr, zzr2, zzr3, zzr4, zzq, zzc, zzq2, zzr5, zzg2, zzg3, zzq3, versionInfoParcel, zzr6, zzq4, interstitialAdParameterParcel);
    }
}
