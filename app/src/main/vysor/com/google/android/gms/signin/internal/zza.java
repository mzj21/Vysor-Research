// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<AuthAccountResult>
{
    static void zza(final AuthAccountResult authAccountResult, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, authAccountResult.mVersionCode);
        zzb.zzc(parcel, 2, authAccountResult.zzcdg());
        zzb.zza(parcel, 3, (Parcelable)authAccountResult.zzcdh(), n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public AuthAccountResult[] zzaad(final int n) {
        return new AuthAccountResult[n];
    }
    
    public AuthAccountResult zzsj(final Parcel parcel) {
        int zzg = 0;
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        Intent intent = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 3: {
                    intent = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new AuthAccountResult(zzg2, zzg, intent);
    }
}
