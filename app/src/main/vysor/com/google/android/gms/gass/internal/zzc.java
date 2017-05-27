// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gass.internal;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<GassRequestParcel>
{
    static void zza(final GassRequestParcel gassRequestParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, gassRequestParcel.versionCode);
        zzb.zza(parcel, 2, gassRequestParcel.packageName, false);
        zzb.zza(parcel, 3, gassRequestParcel.aez, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public GassRequestParcel zzmu(final Parcel parcel) {
        String zzq = null;
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        String zzq2 = null;
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
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new GassRequestParcel(zzg, zzq2, zzq);
    }
    
    public GassRequestParcel[] zzti(final int n) {
        return new GassRequestParcel[n];
    }
}
