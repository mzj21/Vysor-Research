// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.response;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<FieldMappingDictionary>
{
    static void zza(final FieldMappingDictionary fieldMappingDictionary, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, fieldMappingDictionary.getVersionCode());
        zzb.zzc(parcel, 2, fieldMappingDictionary.zzawf(), false);
        zzb.zza(parcel, 3, fieldMappingDictionary.zzawg(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public FieldMappingDictionary zzcy(final Parcel parcel) {
        String zzq = null;
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        ArrayList<FieldMappingDictionary.Entry> zzc = null;
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
                    zzc = zza.zzc(parcel, zzcp, (android.os.Parcelable$Creator<FieldMappingDictionary.Entry>)FieldMappingDictionary.Entry.CREATOR);
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
        return new FieldMappingDictionary(zzg, zzc, zzq);
    }
    
    public FieldMappingDictionary[] zzhd(final int n) {
        return new FieldMappingDictionary[n];
    }
}
