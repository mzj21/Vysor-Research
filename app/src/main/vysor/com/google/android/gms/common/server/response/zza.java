// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.converter.ConverterWrapper;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<FastJsonResponse.Field>
{
    static void zza(final FastJsonResponse.Field field, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, field.getVersionCode());
        zzb.zzc(parcel, 2, field.zzavq());
        zzb.zza(parcel, 3, field.zzavv());
        zzb.zzc(parcel, 4, field.zzavr());
        zzb.zza(parcel, 5, field.zzavw());
        zzb.zza(parcel, 6, field.zzavx(), false);
        zzb.zzc(parcel, 7, field.zzavy());
        zzb.zza(parcel, 8, field.zzawa(), false);
        zzb.zza(parcel, 9, (Parcelable)field.zzawc(), n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public FastJsonResponse.Field zzcw(final Parcel parcel) {
        ConverterWrapper converterWrapper = null;
        int zzg = 0;
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        String zzq = null;
        String zzq2 = null;
        boolean zzc = false;
        int zzg2 = 0;
        boolean zzc2 = false;
        int zzg3 = 0;
        int zzg4 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg4 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzg3 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzc2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzq2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzq = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 9: {
                    converterWrapper = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<ConverterWrapper>)ConverterWrapper.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new FastJsonResponse.Field(zzg4, zzg3, zzc2, zzg2, zzc, zzq2, zzg, zzq, converterWrapper);
    }
    
    public FastJsonResponse.Field[] zzhb(final int n) {
        return new FastJsonResponse.Field[n];
    }
}
