// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import java.util.List;
import com.google.android.gms.ads.internal.safebrowsing.SafeBrowsingConfigParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzh implements Parcelable$Creator<AdResponseParcel>
{
    static void zza(final AdResponseParcel adResponseParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, adResponseParcel.versionCode);
        zzb.zza(parcel, 2, adResponseParcel.zzbyj, false);
        zzb.zza(parcel, 3, adResponseParcel.body, false);
        zzb.zzb(parcel, 4, adResponseParcel.zzbsd, false);
        zzb.zzc(parcel, 5, adResponseParcel.errorCode);
        zzb.zzb(parcel, 6, adResponseParcel.zzbse, false);
        zzb.zza(parcel, 7, adResponseParcel.zzchb);
        zzb.zza(parcel, 8, adResponseParcel.zzchc);
        zzb.zza(parcel, 9, adResponseParcel.zzchd);
        zzb.zzb(parcel, 10, adResponseParcel.zzche, false);
        zzb.zza(parcel, 11, adResponseParcel.zzbsj);
        zzb.zzc(parcel, 12, adResponseParcel.orientation);
        zzb.zza(parcel, 13, adResponseParcel.zzchf, false);
        zzb.zza(parcel, 14, adResponseParcel.zzchg);
        zzb.zza(parcel, 15, adResponseParcel.zzchh, false);
        zzb.zza(parcel, 18, adResponseParcel.zzchi);
        zzb.zza(parcel, 19, adResponseParcel.zzchj, false);
        zzb.zza(parcel, 21, adResponseParcel.zzchk, false);
        zzb.zza(parcel, 22, adResponseParcel.zzchl);
        zzb.zza(parcel, 23, adResponseParcel.zzaxl);
        zzb.zza(parcel, 24, adResponseParcel.zzcgc);
        zzb.zza(parcel, 25, adResponseParcel.zzchm);
        zzb.zza(parcel, 26, adResponseParcel.zzchn);
        zzb.zza(parcel, 28, (Parcelable)adResponseParcel.zzcho, n, false);
        zzb.zza(parcel, 29, adResponseParcel.zzchp, false);
        zzb.zza(parcel, 30, adResponseParcel.zzchq, false);
        zzb.zza(parcel, 31, adResponseParcel.zzaxm);
        zzb.zza(parcel, 32, adResponseParcel.zzaxn);
        zzb.zza(parcel, 33, (Parcelable)adResponseParcel.zzchr, n, false);
        zzb.zzb(parcel, 34, adResponseParcel.zzchs, false);
        zzb.zzb(parcel, 35, adResponseParcel.zzcht, false);
        zzb.zza(parcel, 36, adResponseParcel.zzchu);
        zzb.zza(parcel, 37, (Parcelable)adResponseParcel.zzchv, n, false);
        zzb.zza(parcel, 38, adResponseParcel.zzcgt);
        zzb.zza(parcel, 39, adResponseParcel.zzcgu, false);
        zzb.zzb(parcel, 40, adResponseParcel.zzbsg, false);
        zzb.zza(parcel, 42, adResponseParcel.zzbsh);
        zzb.zza(parcel, 43, adResponseParcel.zzchw, false);
        zzb.zza(parcel, 44, (Parcelable)adResponseParcel.zzchx, n, false);
        zzb.zza(parcel, 45, adResponseParcel.zzchy, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public AdResponseParcel[] zzar(final int n) {
        return new AdResponseParcel[n];
    }
    
    public AdResponseParcel zzn(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        String zzq = null;
        String zzq2 = null;
        List<String> zzae = null;
        int zzg2 = 0;
        List<String> zzae2 = null;
        long zzi = 0L;
        boolean zzc = false;
        long zzi2 = 0L;
        List<String> zzae3 = null;
        long zzi3 = 0L;
        int zzg3 = 0;
        String zzq3 = null;
        long zzi4 = 0L;
        String zzq4 = null;
        boolean zzc2 = false;
        String zzq5 = null;
        String zzq6 = null;
        boolean zzc3 = false;
        boolean zzc4 = false;
        boolean zzc5 = false;
        boolean zzc6 = false;
        boolean zzc7 = false;
        LargeParcelTeleporter largeParcelTeleporter = null;
        String zzq7 = null;
        String zzq8 = null;
        boolean zzc8 = false;
        boolean zzc9 = false;
        RewardItemParcel rewardItemParcel = null;
        List<String> zzae4 = null;
        List<String> zzae5 = null;
        boolean zzc10 = false;
        AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel = null;
        boolean zzc11 = false;
        String zzq9 = null;
        List<String> zzae6 = null;
        boolean zzc12 = false;
        String zzq10 = null;
        SafeBrowsingConfigParcel safeBrowsingConfigParcel = null;
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
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzae = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzae2 = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzi = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 9: {
                    zzi2 = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzae3 = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 11: {
                    zzi3 = zza.zzi(parcel, zzcp);
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
                    zzi4 = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 15: {
                    zzq4 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 18: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 19: {
                    zzq5 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 21: {
                    zzq6 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 22: {
                    zzc3 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 23: {
                    zzc4 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 24: {
                    zzc5 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 25: {
                    zzc6 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 26: {
                    zzc7 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 28: {
                    largeParcelTeleporter = zza.zza(parcel, zzcp, LargeParcelTeleporter.CREATOR);
                    continue;
                }
                case 29: {
                    zzq7 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 30: {
                    zzq8 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 31: {
                    zzc8 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 32: {
                    zzc9 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 33: {
                    rewardItemParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<RewardItemParcel>)RewardItemParcel.CREATOR);
                    continue;
                }
                case 34: {
                    zzae4 = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 35: {
                    zzae5 = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 36: {
                    zzc10 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 37: {
                    autoClickProtectionConfigurationParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<AutoClickProtectionConfigurationParcel>)AutoClickProtectionConfigurationParcel.CREATOR);
                    continue;
                }
                case 38: {
                    zzc11 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 39: {
                    zzq9 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 40: {
                    zzae6 = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 42: {
                    zzc12 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 43: {
                    zzq10 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 44: {
                    safeBrowsingConfigParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<SafeBrowsingConfigParcel>)SafeBrowsingConfigParcel.CREATOR);
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
        return new AdResponseParcel(zzg, zzq, zzq2, zzae, zzg2, zzae2, zzi, zzc, zzi2, zzae3, zzi3, zzg3, zzq3, zzi4, zzq4, zzc2, zzq5, zzq6, zzc3, zzc4, zzc5, zzc6, zzc7, largeParcelTeleporter, zzq7, zzq8, zzc8, zzc9, rewardItemParcel, zzae4, zzae5, zzc10, autoClickProtectionConfigurationParcel, zzc11, zzq9, zzae6, zzc12, zzq10, safeBrowsingConfigParcel, zzq11);
    }
}
