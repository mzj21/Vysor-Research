// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzh implements Parcelable$Creator<SignInRequest>
{
    static void zza(final SignInRequest signInRequest, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, signInRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)signInRequest.zzcdk(), n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public SignInRequest[] zzaah(final int n) {
        return new SignInRequest[n];
    }
    
    public SignInRequest zzsm(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        ResolveAccountRequest resolveAccountRequest = null;
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
                    resolveAccountRequest = zza.zza(parcel, zzcp, ResolveAccountRequest.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new SignInRequest(zzg, resolveAccountRequest);
    }
}
