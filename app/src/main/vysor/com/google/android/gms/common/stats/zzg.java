// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzg implements Parcelable$Creator<WakeLockEvent>
{
    static void zza(final WakeLockEvent wakeLockEvent, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, wakeLockEvent.mVersionCode);
        zzb.zza(parcel, 2, wakeLockEvent.getTimeMillis());
        zzb.zza(parcel, 4, wakeLockEvent.zzaww(), false);
        zzb.zzc(parcel, 5, wakeLockEvent.zzawz());
        zzb.zzb(parcel, 6, wakeLockEvent.zzaxa(), false);
        zzb.zza(parcel, 8, wakeLockEvent.zzaws());
        zzb.zza(parcel, 10, wakeLockEvent.zzawx(), false);
        zzb.zzc(parcel, 11, wakeLockEvent.getEventType());
        zzb.zza(parcel, 12, wakeLockEvent.zzawp(), false);
        zzb.zza(parcel, 13, wakeLockEvent.zzaxc(), false);
        zzb.zzc(parcel, 14, wakeLockEvent.zzaxb());
        zzb.zza(parcel, 15, wakeLockEvent.zzaxd());
        zzb.zza(parcel, 16, wakeLockEvent.zzaxe());
        zzb.zza(parcel, 17, wakeLockEvent.zzawy(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public WakeLockEvent zzdc(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        long zzi = 0L;
        int zzg2 = 0;
        String zzq = null;
        int zzg3 = 0;
        List<String> zzae = null;
        String zzq2 = null;
        long zzi2 = 0L;
        int zzg4 = 0;
        String zzq3 = null;
        String zzq4 = null;
        float zzl = 0.0f;
        long zzi3 = 0L;
        String zzq5 = null;
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
                    zzi = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzae = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzi2 = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzq3 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 11: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 12: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 13: {
                    zzq4 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 14: {
                    zzg4 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 15: {
                    zzl = zza.zzl(parcel, zzcp);
                    continue;
                }
                case 16: {
                    zzi3 = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 17: {
                    zzq5 = zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new WakeLockEvent(zzg, zzi, zzg2, zzq, zzg3, zzae, zzq2, zzi2, zzg4, zzq3, zzq4, zzl, zzi3, zzq5);
    }
    
    public WakeLockEvent[] zzhh(final int n) {
        return new WakeLockEvent[n];
    }
}
