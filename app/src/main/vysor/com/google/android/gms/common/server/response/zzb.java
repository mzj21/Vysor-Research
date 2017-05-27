// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<FieldMappingDictionary.FieldMapPair>
{
    static void zza(final FieldMappingDictionary.FieldMapPair fieldMapPair, final Parcel parcel, final int n) {
        final int zzcr = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, fieldMapPair.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, fieldMapPair.zzcb, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable)fieldMapPair.DH, n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcr);
    }
    
    public FieldMappingDictionary.FieldMapPair zzcx(final Parcel parcel) {
        AbstractSafeParcelable abstractSafeParcelable = null;
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        String zzq = null;
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
                    abstractSafeParcelable = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<FastJsonResponse.Field<?, ?>>)FastJsonResponse.Field.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new FieldMappingDictionary.FieldMapPair(zzg, zzq, (FastJsonResponse.Field<?, ?>)abstractSafeParcelable);
    }
    
    public FieldMappingDictionary.FieldMapPair[] zzhc(final int n) {
        return new FieldMappingDictionary.FieldMapPair[n];
    }
}
