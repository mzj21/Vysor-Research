// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzi implements Parcelable$Creator<SignInResponse>
{
    static void zza(final SignInResponse signInResponse, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, signInResponse.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)signInResponse.zzave(), n, false);
        zzb.zza(parcel, 3, (Parcelable)signInResponse.zzcdl(), n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public SignInResponse[] zzaai(final int n) {
        return new SignInResponse[n];
    }
    
    public SignInResponse zzsn(final Parcel parcel) {
        ResolveAccountResponse resolveAccountResponse = null;
        final int zzcq = zza.zzcq(parcel);
        int n = 0;
        ConnectionResult connectionResult = null;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            ResolveAccountResponse resolveAccountResponse2 = null;
            ConnectionResult connectionResult2 = null;
            int n2 = 0;
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    resolveAccountResponse2 = resolveAccountResponse;
                    connectionResult2 = connectionResult;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzcp);
                    final ResolveAccountResponse resolveAccountResponse3 = resolveAccountResponse;
                    connectionResult2 = connectionResult;
                    n2 = zzg;
                    resolveAccountResponse2 = resolveAccountResponse3;
                    break;
                }
                case 2: {
                    final ConnectionResult connectionResult3 = zza.zza(parcel, zzcp, ConnectionResult.CREATOR);
                    n2 = n;
                    resolveAccountResponse2 = resolveAccountResponse;
                    connectionResult2 = connectionResult3;
                    break;
                }
                case 3: {
                    resolveAccountResponse2 = zza.zza(parcel, zzcp, ResolveAccountResponse.CREATOR);
                    connectionResult2 = connectionResult;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            connectionResult = connectionResult2;
            resolveAccountResponse = resolveAccountResponse2;
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new SignInResponse(n, connectionResult, resolveAccountResponse);
    }
}
