// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzh implements Parcelable$Creator<RewardedVideoAdRequestParcel>
{
    static void zza(final RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, rewardedVideoAdRequestParcel.versionCode);
        zzb.zza(parcel, 2, (Parcelable)rewardedVideoAdRequestParcel.zzcfu, n, false);
        zzb.zza(parcel, 3, rewardedVideoAdRequestParcel.zzaqt, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public RewardedVideoAdRequestParcel[] zzax(final int n) {
        return new RewardedVideoAdRequestParcel[n];
    }
    
    public RewardedVideoAdRequestParcel zzs(final Parcel parcel) {
        String s = null;
        final int zzcq = zza.zzcq(parcel);
        int n = 0;
        AdRequestParcel adRequestParcel = null;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            String zzq = null;
            AdRequestParcel adRequestParcel2 = null;
            int n2 = 0;
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    zzq = s;
                    adRequestParcel2 = adRequestParcel;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzcp);
                    final String s2 = s;
                    adRequestParcel2 = adRequestParcel;
                    n2 = zzg;
                    zzq = s2;
                    break;
                }
                case 2: {
                    final AdRequestParcel adRequestParcel3 = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<AdRequestParcel>)AdRequestParcel.CREATOR);
                    n2 = n;
                    zzq = s;
                    adRequestParcel2 = adRequestParcel3;
                    break;
                }
                case 3: {
                    zzq = zza.zzq(parcel, zzcp);
                    adRequestParcel2 = adRequestParcel;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            adRequestParcel = adRequestParcel2;
            s = zzq;
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new RewardedVideoAdRequestParcel(n, adRequestParcel, s);
    }
}
