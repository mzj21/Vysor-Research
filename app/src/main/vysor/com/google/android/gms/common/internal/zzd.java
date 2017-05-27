// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzd implements Parcelable$Creator<AuthAccountRequest>
{
    static void zza(final AuthAccountRequest authAccountRequest, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, authAccountRequest.AW, false);
        zzb.zza(parcel, 3, authAccountRequest.AX, n, false);
        zzb.zza(parcel, 4, authAccountRequest.AY, false);
        zzb.zza(parcel, 5, authAccountRequest.AZ, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public AuthAccountRequest zzci(final Parcel parcel) {
        Integer zzh = null;
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        Integer zzh2 = null;
        Scope[] array = null;
        IBinder zzr = null;
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
                    array = zza.zzb(parcel, zzcp, Scope.CREATOR);
                    continue;
                }
                case 4: {
                    zzh2 = zza.zzh(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzh = zza.zzh(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new AuthAccountRequest(zzg, zzr, array, zzh2, zzh);
    }
    
    public AuthAccountRequest[] zzgk(final int n) {
        return new AuthAccountRequest[n];
    }
}
