// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server;

import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<FavaDiagnosticsEntity>
{
    static void zza(final FavaDiagnosticsEntity favaDiagnosticsEntity, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, favaDiagnosticsEntity.mVersionCode);
        zzb.zza(parcel, 2, favaDiagnosticsEntity.Dl, false);
        zzb.zzc(parcel, 3, favaDiagnosticsEntity.Dm);
        zzb.zzaj(parcel, zzcr);
    }
    
    public FavaDiagnosticsEntity zzcs(final Parcel parcel) {
        int zzg = 0;
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        String zzq = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzq = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new FavaDiagnosticsEntity(zzg2, zzq, zzg);
    }
    
    public FavaDiagnosticsEntity[] zzgx(final int n) {
        return new FavaDiagnosticsEntity[n];
    }
}
