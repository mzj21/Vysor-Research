// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzh implements Parcelable$Creator<Status>
{
    static void zza(final Status status, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, status.getStatusCode());
        zzb.zza(parcel, 2, status.getStatusMessage(), false);
        zzb.zza(parcel, 3, (Parcelable)status.zzaqh(), n, false);
        zzb.zzc(parcel, 1000, status.getVersionCode());
        zzb.zzaj(parcel, zzcr);
    }
    
    public Status zzce(final Parcel parcel) {
        PendingIntent pendingIntent = null;
        int zzg = 0;
        final int zzcq = zza.zzcq(parcel);
        String zzq = null;
        int zzg2 = 0;
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
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    pendingIntent = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new Status(zzg2, zzg, zzq, pendingIntent);
    }
    
    public Status[] zzfp(final int n) {
        return new Status[n];
    }
}
