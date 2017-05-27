// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<ConnectionEvent>
{
    static void zza(final ConnectionEvent connectionEvent, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, connectionEvent.mVersionCode);
        zzb.zza(parcel, 2, connectionEvent.getTimeMillis());
        zzb.zza(parcel, 4, connectionEvent.zzawk(), false);
        zzb.zza(parcel, 5, connectionEvent.zzawl(), false);
        zzb.zza(parcel, 6, connectionEvent.zzawm(), false);
        zzb.zza(parcel, 7, connectionEvent.zzawn(), false);
        zzb.zza(parcel, 8, connectionEvent.zzawo(), false);
        zzb.zza(parcel, 10, connectionEvent.zzaws());
        zzb.zza(parcel, 11, connectionEvent.zzawr());
        zzb.zzc(parcel, 12, connectionEvent.getEventType());
        zzb.zza(parcel, 13, connectionEvent.zzawp(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public ConnectionEvent zzdb(final Parcel parcel) {
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        int zzg = 0;
        long zzi = 0L;
        int zzg2 = 0;
        String zzq = null;
        String zzq2 = null;
        String zzq3 = null;
        String zzq4 = null;
        String zzq5 = null;
        String zzq6 = null;
        long zzi2 = 0L;
        long zzi3 = 0L;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzq = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzq2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzq3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzq4 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzq5 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzi2 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzcp);
                    continue;
                }
                case 11: {
                    zzi3 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzcp);
                    continue;
                }
                case 12: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 13: {
                    zzq6 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new ConnectionEvent(zzg, zzi, zzg2, zzq, zzq2, zzq3, zzq4, zzq5, zzq6, zzi2, zzi3);
    }
    
    public ConnectionEvent[] zzhg(final int n) {
        return new ConnectionEvent[n];
    }
}
