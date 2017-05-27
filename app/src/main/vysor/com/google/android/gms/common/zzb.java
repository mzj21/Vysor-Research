// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<ConnectionResult>
{
    static void zza(final ConnectionResult connectionResult, final Parcel parcel, final int n) {
        final int zzcr = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, connectionResult.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, connectionResult.getErrorCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable)connectionResult.getResolution(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, connectionResult.getErrorMessage(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcr);
    }
    
    public ConnectionResult zzcc(final Parcel parcel) {
        String s = null;
        int n = 0;
        final int zzcq = zza.zzcq(parcel);
        PendingIntent pendingIntent = null;
        int n2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            String zzq = null;
            PendingIntent pendingIntent2 = null;
            int n3 = 0;
            int n4 = 0;
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    zzq = s;
                    pendingIntent2 = pendingIntent;
                    n3 = n;
                    n4 = n2;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzcp);
                    final String s2 = s;
                    pendingIntent2 = pendingIntent;
                    n3 = n;
                    n4 = zzg;
                    zzq = s2;
                    break;
                }
                case 2: {
                    final int zzg2 = zza.zzg(parcel, zzcp);
                    n4 = n2;
                    final PendingIntent pendingIntent3 = pendingIntent;
                    n3 = zzg2;
                    zzq = s;
                    pendingIntent2 = pendingIntent3;
                    break;
                }
                case 3: {
                    final PendingIntent pendingIntent4 = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR);
                    n3 = n;
                    n4 = n2;
                    final String s3 = s;
                    pendingIntent2 = pendingIntent4;
                    zzq = s3;
                    break;
                }
                case 4: {
                    zzq = zza.zzq(parcel, zzcp);
                    pendingIntent2 = pendingIntent;
                    n3 = n;
                    n4 = n2;
                    break;
                }
            }
            n2 = n4;
            n = n3;
            pendingIntent = pendingIntent2;
            s = zzq;
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new ConnectionResult(n2, n, pendingIntent, s);
    }
    
    public ConnectionResult[] zzfk(final int n) {
        return new ConnectionResult[n];
    }
}
