// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.playlog.internal;

import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<PlayLoggerContext>
{
    static void zza(final PlayLoggerContext playLoggerContext, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, playLoggerContext.versionCode);
        zzb.zza(parcel, 2, playLoggerContext.packageName, false);
        zzb.zzc(parcel, 3, playLoggerContext.axu);
        zzb.zzc(parcel, 4, playLoggerContext.axv);
        zzb.zza(parcel, 5, playLoggerContext.axw, false);
        zzb.zza(parcel, 6, playLoggerContext.axx, false);
        zzb.zza(parcel, 7, playLoggerContext.axy);
        zzb.zza(parcel, 8, playLoggerContext.axz, false);
        zzb.zza(parcel, 9, playLoggerContext.axA);
        zzb.zzc(parcel, 10, playLoggerContext.axB);
        zzb.zzaj(parcel, zzcr);
    }
    
    public PlayLoggerContext zzrs(final Parcel parcel) {
        String zzq = null;
        int zzg = 0;
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        boolean zzc = true;
        boolean zzc2 = false;
        String zzq2 = null;
        String zzq3 = null;
        int zzg2 = 0;
        int zzg3 = 0;
        String zzq4 = null;
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
                    zzq4 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg3 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzq3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzq2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzq = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 9: {
                    zzc2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new PlayLoggerContext(zzg4, zzq4, zzg3, zzg2, zzq3, zzq2, zzc, zzq, zzc2, zzg);
    }
    
    public PlayLoggerContext[] zzzk(final int n) {
        return new PlayLoggerContext[n];
    }
}
