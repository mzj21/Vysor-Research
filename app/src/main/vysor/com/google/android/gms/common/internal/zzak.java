// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzak implements Parcelable$Creator<ValidateAccountRequest>
{
    static void zza(final ValidateAccountRequest validateAccountRequest, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, validateAccountRequest.mVersionCode);
        zzb.zzc(parcel, 2, validateAccountRequest.zzavl());
        zzb.zza(parcel, 3, validateAccountRequest.AW, false);
        zzb.zza(parcel, 4, validateAccountRequest.zzavj(), n, false);
        zzb.zza(parcel, 5, validateAccountRequest.zzavm(), false);
        zzb.zza(parcel, 6, validateAccountRequest.getCallingPackage(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public ValidateAccountRequest zzco(final Parcel parcel) {
        int zzg = 0;
        String zzq = null;
        final int zzcq = zza.zzcq(parcel);
        Bundle zzs = null;
        Scope[] array = null;
        IBinder zzr = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzr = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 4: {
                    array = zza.zzb(parcel, zzcp, Scope.CREATOR);
                    continue;
                }
                case 5: {
                    zzs = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new ValidateAccountRequest(zzg2, zzg, zzr, array, zzs, zzq);
    }
    
    public ValidateAccountRequest[] zzgu(final int n) {
        return new ValidateAccountRequest[n];
    }
}
