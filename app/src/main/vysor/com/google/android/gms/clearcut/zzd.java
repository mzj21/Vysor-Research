// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.clearcut;

import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzd implements Parcelable$Creator<LogEventParcelable>
{
    static void zza(final LogEventParcelable logEventParcelable, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, logEventParcelable.versionCode);
        zzb.zza(parcel, 2, (Parcelable)logEventParcelable.uc, n, false);
        zzb.zza(parcel, 3, logEventParcelable.ud, false);
        zzb.zza(parcel, 4, logEventParcelable.ue, false);
        zzb.zza(parcel, 5, logEventParcelable.uf, false);
        zzb.zza(parcel, 6, logEventParcelable.ug, false);
        zzb.zza(parcel, 7, logEventParcelable.uh, false);
        zzb.zza(parcel, 8, logEventParcelable.ui);
        zzb.zzaj(parcel, zzcr);
    }
    
    public LogEventParcelable zzcb(final Parcel parcel) {
        byte[][] zzu = null;
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        boolean zzc = true;
        int[] zzw = null;
        String[] zzac = null;
        int[] zzw2 = null;
        byte[] zzt = null;
        PlayLoggerContext playLoggerContext = null;
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
                    playLoggerContext = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<PlayLoggerContext>)PlayLoggerContext.CREATOR);
                    continue;
                }
                case 3: {
                    zzt = zza.zzt(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzw2 = zza.zzw(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzac = zza.zzac(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzw = zza.zzw(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzu = zza.zzu(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new LogEventParcelable(zzg, playLoggerContext, zzt, zzw2, zzac, zzw, zzu, zzc);
    }
    
    public LogEventParcelable[] zzfj(final int n) {
        return new LogEventParcelable[n];
    }
}
