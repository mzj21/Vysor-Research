// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gass.internal;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzd implements Parcelable$Creator<GassResponseParcel>
{
    static void zza(final GassResponseParcel gassResponseParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, gassResponseParcel.versionCode);
        zzb.zza(parcel, 2, gassResponseParcel.zzbnv(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public GassResponseParcel zzmv(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        byte[] zzt = null;
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
                    zzt = zza.zzt(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new GassResponseParcel(zzg, zzt);
    }
    
    public GassResponseParcel[] zztj(final int n) {
        return new GassResponseParcel[n];
    }
}
