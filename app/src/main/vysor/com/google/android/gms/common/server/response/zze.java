// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zze implements Parcelable$Creator<SafeParcelResponse>
{
    static void zza(final SafeParcelResponse safeParcelResponse, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, safeParcelResponse.getVersionCode());
        zzb.zza(parcel, 2, safeParcelResponse.zzawi(), false);
        zzb.zza(parcel, 3, (Parcelable)safeParcelResponse.zzawj(), n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public SafeParcelResponse zzda(final Parcel parcel) {
        FieldMappingDictionary fieldMappingDictionary = null;
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        Parcel zzaf = null;
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
                    zzaf = zza.zzaf(parcel, zzcp);
                    continue;
                }
                case 3: {
                    fieldMappingDictionary = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<FieldMappingDictionary>)FieldMappingDictionary.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new SafeParcelResponse(zzg, zzaf, fieldMappingDictionary);
    }
    
    public SafeParcelResponse[] zzhf(final int n) {
        return new SafeParcelResponse[n];
    }
}
