// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzae implements Parcelable$Creator<ResolveAccountResponse>
{
    static void zza(final ResolveAccountResponse resolveAccountResponse, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountResponse.AW, false);
        zzb.zza(parcel, 3, (Parcelable)resolveAccountResponse.zzave(), n, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zzavf());
        zzb.zza(parcel, 5, resolveAccountResponse.zzavg());
        zzb.zzaj(parcel, zzcr);
    }
    
    public ResolveAccountResponse zzcm(final Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean zzc = false;
        final int zzcq = zza.zzcq(parcel);
        boolean zzc2 = false;
        IBinder zzr = null;
        int zzg = 0;
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
                    zzr = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 3: {
                    connectionResult = zza.zza(parcel, zzcp, ConnectionResult.CREATOR);
                    continue;
                }
                case 4: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new ResolveAccountResponse(zzg, zzr, connectionResult, zzc2, zzc);
    }
    
    public ResolveAccountResponse[] zzgs(final int n) {
        return new ResolveAccountResponse[n];
    }
}
