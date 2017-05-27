// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.converter;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<StringToIntConverter.Entry>
{
    static void zza(final StringToIntConverter.Entry entry, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, entry.versionCode);
        zzb.zza(parcel, 2, entry.Dr, false);
        zzb.zzc(parcel, 3, entry.Ds);
        zzb.zzaj(parcel, zzcr);
    }
    
    public StringToIntConverter.Entry zzcv(final Parcel parcel) {
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
        return new StringToIntConverter.Entry(zzg2, zzq, zzg);
    }
    
    public StringToIntConverter.Entry[] zzha(final int n) {
        return new StringToIntConverter.Entry[n];
    }
}
