// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.mediation.client;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<RewardItemParcel>
{
    static void zza(final RewardItemParcel rewardItemParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, rewardItemParcel.versionCode);
        zzb.zza(parcel, 2, rewardItemParcel.type, false);
        zzb.zzc(parcel, 3, rewardItemParcel.zzcny);
        zzb.zzaj(parcel, zzcr);
    }
    
    public RewardItemParcel[] zzba(final int n) {
        return new RewardItemParcel[n];
    }
    
    public RewardItemParcel zzt(final Parcel parcel) {
        int zzg = 0;
        final int zzcq = zza.zzcq(parcel);
        String zzq = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new RewardItemParcel(zzg2, zzq, zzg);
    }
}
