// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import java.util.List;
import android.os.Bundle;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import android.os.Messenger;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzf implements Parcelable$Creator<AdRequestInfoParcel>
{
    static void zza(final AdRequestInfoParcel adRequestInfoParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, adRequestInfoParcel.versionCode);
        zzb.zza(parcel, 2, adRequestInfoParcel.zzcft, false);
        zzb.zza(parcel, 3, (Parcelable)adRequestInfoParcel.zzcfu, n, false);
        zzb.zza(parcel, 4, (Parcelable)adRequestInfoParcel.zzaqz, n, false);
        zzb.zza(parcel, 5, adRequestInfoParcel.zzaqt, false);
        zzb.zza(parcel, 6, (Parcelable)adRequestInfoParcel.applicationInfo, n, false);
        zzb.zza(parcel, 7, (Parcelable)adRequestInfoParcel.zzcfv, n, false);
        zzb.zza(parcel, 8, adRequestInfoParcel.zzcfw, false);
        zzb.zza(parcel, 9, adRequestInfoParcel.zzcfx, false);
        zzb.zza(parcel, 10, adRequestInfoParcel.zzcfy, false);
        zzb.zza(parcel, 11, (Parcelable)adRequestInfoParcel.zzaqv, n, false);
        zzb.zza(parcel, 12, adRequestInfoParcel.zzcfz, false);
        zzb.zzc(parcel, 13, adRequestInfoParcel.zzcga);
        zzb.zzb(parcel, 14, adRequestInfoParcel.zzarr, false);
        zzb.zza(parcel, 15, adRequestInfoParcel.zzcgb, false);
        zzb.zza(parcel, 16, adRequestInfoParcel.zzcgc);
        zzb.zza(parcel, 17, (Parcelable)adRequestInfoParcel.zzcgd, n, false);
        zzb.zzc(parcel, 18, adRequestInfoParcel.zzcge);
        zzb.zzc(parcel, 19, adRequestInfoParcel.zzcgf);
        zzb.zza(parcel, 20, adRequestInfoParcel.zzcgg);
        zzb.zza(parcel, 21, adRequestInfoParcel.zzcgh, false);
        zzb.zza(parcel, 25, adRequestInfoParcel.zzcgi);
        zzb.zza(parcel, 26, adRequestInfoParcel.zzcgj, false);
        zzb.zzb(parcel, 27, adRequestInfoParcel.zzcgk, false);
        zzb.zza(parcel, 28, adRequestInfoParcel.zzaqs, false);
        zzb.zza(parcel, 29, (Parcelable)adRequestInfoParcel.zzarn, n, false);
        zzb.zzb(parcel, 30, adRequestInfoParcel.zzcgl, false);
        zzb.zza(parcel, 31, adRequestInfoParcel.zzcgm);
        zzb.zza(parcel, 32, (Parcelable)adRequestInfoParcel.zzcgn, n, false);
        zzb.zza(parcel, 33, adRequestInfoParcel.zzcgo, false);
        zzb.zza(parcel, 34, adRequestInfoParcel.zzcgp);
        zzb.zzc(parcel, 35, adRequestInfoParcel.zzcgq);
        zzb.zzc(parcel, 36, adRequestInfoParcel.zzcgr);
        zzb.zza(parcel, 37, adRequestInfoParcel.zzcgs);
        zzb.zza(parcel, 38, adRequestInfoParcel.zzcgt);
        zzb.zza(parcel, 39, adRequestInfoParcel.zzcgu, false);
        zzb.zza(parcel, 40, adRequestInfoParcel.zzcgv);
        zzb.zza(parcel, 41, adRequestInfoParcel.zzcgw, false);
        zzb.zza(parcel, 42, adRequestInfoParcel.zzbsh);
        zzb.zzc(parcel, 43, adRequestInfoParcel.zzcgx);
        zzb.zza(parcel, 44, adRequestInfoParcel.zzcgy, false);
        zzb.zza(parcel, 45, adRequestInfoParcel.zzcgz, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public AdRequestInfoParcel[] zzaq(final int n) {
        return new AdRequestInfoParcel[n];
    }
    
    public AdRequestInfoParcel zzm(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        Bundle zzs = null;
        AdRequestParcel adRequestParcel = null;
        AdSizeParcel adSizeParcel = null;
        String zzq = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String zzq2 = null;
        String zzq3 = null;
        String zzq4 = null;
        VersionInfoParcel versionInfoParcel = null;
        Bundle zzs2 = null;
        int zzg2 = 0;
        List<String> zzae = null;
        Bundle zzs3 = null;
        boolean zzc = false;
        Messenger messenger = null;
        int zzg3 = 0;
        int zzg4 = 0;
        float zzl = 0.0f;
        String zzq5 = null;
        long zzi = 0L;
        String zzq6 = null;
        List<String> zzae2 = null;
        String zzq7 = null;
        NativeAdOptionsParcel nativeAdOptionsParcel = null;
        List<String> zzae3 = null;
        long zzi2 = 0L;
        CapabilityParcel capabilityParcel = null;
        String zzq8 = null;
        float zzl2 = 0.0f;
        boolean zzc2 = false;
        int zzg5 = 0;
        int zzg6 = 0;
        boolean zzc3 = false;
        boolean zzc4 = false;
        String zzq9 = null;
        String zzq10 = null;
        boolean zzc5 = false;
        int zzg7 = 0;
        Bundle zzs4 = null;
        String zzq11 = null;
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
                    zzs = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 3: {
                    adRequestParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<AdRequestParcel>)AdRequestParcel.CREATOR);
                    continue;
                }
                case 4: {
                    adSizeParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<AdSizeParcel>)AdSizeParcel.CREATOR);
                    continue;
                }
                case 5: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 6: {
                    applicationInfo = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<ApplicationInfo>)ApplicationInfo.CREATOR);
                    continue;
                }
                case 7: {
                    packageInfo = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<PackageInfo>)PackageInfo.CREATOR);
                    continue;
                }
                case 8: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 9: {
                    zzq3 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzq4 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 11: {
                    versionInfoParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<VersionInfoParcel>)VersionInfoParcel.CREATOR);
                    continue;
                }
                case 12: {
                    zzs2 = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 13: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 14: {
                    zzae = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 15: {
                    zzs3 = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 16: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 17: {
                    messenger = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Messenger>)Messenger.CREATOR);
                    continue;
                }
                case 18: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 19: {
                    zzg4 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 20: {
                    zzl = zza.zzl(parcel, zzcp);
                    continue;
                }
                case 21: {
                    zzq5 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 25: {
                    zzi = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 26: {
                    zzq6 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 27: {
                    zzae2 = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 28: {
                    zzq7 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 29: {
                    nativeAdOptionsParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<NativeAdOptionsParcel>)NativeAdOptionsParcel.CREATOR);
                    continue;
                }
                case 30: {
                    zzae3 = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 31: {
                    zzi2 = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 32: {
                    capabilityParcel = zza.zza(parcel, zzcp, CapabilityParcel.CREATOR);
                    continue;
                }
                case 33: {
                    zzq8 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 34: {
                    zzl2 = zza.zzl(parcel, zzcp);
                    continue;
                }
                case 35: {
                    zzg5 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 36: {
                    zzg6 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 37: {
                    zzc3 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 38: {
                    zzc4 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 39: {
                    zzq9 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 40: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 41: {
                    zzq10 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 42: {
                    zzc5 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 43: {
                    zzg7 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 44: {
                    zzs4 = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 45: {
                    zzq11 = zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new AdRequestInfoParcel(zzg, zzs, adRequestParcel, adSizeParcel, zzq, applicationInfo, packageInfo, zzq2, zzq3, zzq4, versionInfoParcel, zzs2, zzg2, zzae, zzs3, zzc, messenger, zzg3, zzg4, zzl, zzq5, zzi, zzq6, zzae2, zzq7, nativeAdOptionsParcel, zzae3, zzi2, capabilityParcel, zzq8, zzl2, zzc2, zzg5, zzg6, zzc3, zzc4, zzq9, zzq10, zzc5, zzg7, zzs4, zzq11);
    }
}
