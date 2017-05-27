// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.places;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzi implements Parcelable$Creator<PlaceReport>
{
    static void zza(final PlaceReport placeReport, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, placeReport.mVersionCode);
        zzb.zza(parcel, 2, placeReport.getPlaceId(), false);
        zzb.zza(parcel, 3, placeReport.getTag(), false);
        zzb.zza(parcel, 4, placeReport.getSource(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public PlaceReport zzny(final Parcel parcel) {
        String zzq = null;
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        String zzq2 = null;
        String zzq3 = null;
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
                    zzq3 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new PlaceReport(zzg, zzq3, zzq2, zzq);
    }
    
    public PlaceReport[] zzvb(final int n) {
        return new PlaceReport[n];
    }
}
