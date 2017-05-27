// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzd implements Parcelable$Creator<VersionInfoParcel>
{
    static void zza(final VersionInfoParcel versionInfoParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, versionInfoParcel.versionCode);
        zzb.zza(parcel, 2, versionInfoParcel.zzcs, false);
        zzb.zzc(parcel, 3, versionInfoParcel.zzcts);
        zzb.zzc(parcel, 4, versionInfoParcel.zzctt);
        zzb.zza(parcel, 5, versionInfoParcel.zzctu);
        zzb.zzaj(parcel, zzcr);
    }
    
    public VersionInfoParcel[] zzbg(final int n) {
        return new VersionInfoParcel[n];
    }
    
    public VersionInfoParcel zzv(final Parcel parcel) {
        boolean zzc = false;
        final int zzcq = zza.zzcq(parcel);
        String zzq = null;
        int zzg = 0;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new VersionInfoParcel(zzg3, zzq, zzg2, zzg, zzc);
    }
}
