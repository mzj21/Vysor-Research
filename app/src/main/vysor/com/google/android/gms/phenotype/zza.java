// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.phenotype;

import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<Configuration>
{
    static void zza(final Configuration configuration, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, configuration.mVersionCode);
        zzb.zzc(parcel, 2, configuration.axl);
        zzb.zza(parcel, 3, configuration.axm, n, false);
        zzb.zza(parcel, 4, configuration.axn, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public Configuration zzrq(final Parcel parcel) {
        String[] array = null;
        int n = 0;
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        Flag[] array2 = null;
        int n2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            String[] zzac = null;
            Flag[] array3 = null;
            int n3 = 0;
            int n4 = 0;
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp);
                    zzac = array;
                    array3 = array2;
                    n3 = n;
                    n4 = n2;
                    break;
                }
                case 1: {
                    final int zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    final String[] array4 = array;
                    array3 = array2;
                    n3 = n;
                    n4 = zzg;
                    zzac = array4;
                    break;
                }
                case 2: {
                    final int zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    n4 = n2;
                    final Flag[] array5 = array2;
                    n3 = zzg2;
                    zzac = array;
                    array3 = array5;
                    break;
                }
                case 3: {
                    final Flag[] array6 = com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp, Flag.CREATOR);
                    n3 = n;
                    n4 = n2;
                    final String[] array7 = array;
                    array3 = array6;
                    zzac = array7;
                    break;
                }
                case 4: {
                    zzac = com.google.android.gms.common.internal.safeparcel.zza.zzac(parcel, zzcp);
                    array3 = array2;
                    n3 = n;
                    n4 = n2;
                    break;
                }
            }
            n2 = n4;
            n = n3;
            array2 = array3;
            array = zzac;
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new Configuration(n2, n, array2, array);
    }
    
    public Configuration[] zzzi(final int n) {
        return new Configuration[n];
    }
}
