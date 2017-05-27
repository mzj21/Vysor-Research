// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.converter;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<StringToIntConverter>
{
    static void zza(final StringToIntConverter stringToIntConverter, final Parcel parcel, final int n) {
        final int zzcr = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, stringToIntConverter.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, stringToIntConverter.zzavp(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcr);
    }
    
    public StringToIntConverter zzcu(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        ArrayList<StringToIntConverter.Entry> zzc = null;
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
                    zzc = zza.zzc(parcel, zzcp, (android.os.Parcelable$Creator<StringToIntConverter.Entry>)StringToIntConverter.Entry.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new StringToIntConverter(zzg, zzc);
    }
    
    public StringToIntConverter[] zzgz(final int n) {
        return new StringToIntConverter[n];
    }
}
