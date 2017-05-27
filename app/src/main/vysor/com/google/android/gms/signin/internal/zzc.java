// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import java.util.List;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<CheckServerAuthResult>
{
    static void zza(final CheckServerAuthResult checkServerAuthResult, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.mVersionCode);
        zzb.zza(parcel, 2, checkServerAuthResult.aAh);
        zzb.zzc(parcel, 3, checkServerAuthResult.aAi, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public CheckServerAuthResult[] zzaae(final int n) {
        return new CheckServerAuthResult[n];
    }
    
    public CheckServerAuthResult zzsk(final Parcel parcel) {
        boolean zzc = false;
        final int zzcq = zza.zzcq(parcel);
        List<Scope> zzc2 = null;
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
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzc2 = zza.zzc(parcel, zzcp, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new CheckServerAuthResult(zzg, zzc, zzc2);
    }
}
