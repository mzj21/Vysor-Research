// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.phenotype;

import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<Flag>
{
    static void zza(final Flag flag, final Parcel parcel, final int n) {
        final int zzcr = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, flag.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, flag.name, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, flag.axp);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, flag.afy);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, flag.afA);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, flag.Dr, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, flag.axq, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 8, flag.axr);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 9, flag.axs);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcr);
    }
    
    public Flag zzrr(final Parcel parcel) {
        byte[] zzt = null;
        int zzg = 0;
        final int zzcq = zza.zzcq(parcel);
        long zzi = 0L;
        double zzn = 0.0;
        int zzg2 = 0;
        String zzq = null;
        boolean zzc = false;
        String zzq2 = null;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzi = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzn = zza.zzn(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzt = zza.zzt(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 9: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new Flag(zzg3, zzq2, zzi, zzc, zzn, zzq, zzt, zzg2, zzg);
    }
    
    public Flag[] zzzj(final int n) {
        return new Flag[n];
    }
}
