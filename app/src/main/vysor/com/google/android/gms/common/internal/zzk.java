// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Bundle;
import android.accounts.Account;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzk implements Parcelable$Creator<GetServiceRequest>
{
    static void zza(final GetServiceRequest getServiceRequest, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.Ci);
        zzb.zzc(parcel, 3, getServiceRequest.Cj);
        zzb.zza(parcel, 4, getServiceRequest.Ck, false);
        zzb.zza(parcel, 5, getServiceRequest.Cl, false);
        zzb.zza(parcel, 6, getServiceRequest.Cm, n, false);
        zzb.zza(parcel, 7, getServiceRequest.Cn, false);
        zzb.zza(parcel, 8, (Parcelable)getServiceRequest.Co, n, false);
        zzb.zza(parcel, 9, getServiceRequest.Cp);
        zzb.zzaj(parcel, zzcr);
    }
    
    public GetServiceRequest zzck(final Parcel parcel) {
        int zzg = 0;
        Account account = null;
        final int zzcq = zza.zzcq(parcel);
        long zzi = 0L;
        Bundle zzs = null;
        Scope[] array = null;
        IBinder zzr = null;
        String zzq = null;
        int zzg2 = 0;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzr = zza.zzr(parcel, zzcp);
                    continue;
                }
                case 6: {
                    array = zza.zzb(parcel, zzcp, Scope.CREATOR);
                    continue;
                }
                case 7: {
                    zzs = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 8: {
                    account = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
                case 9: {
                    zzi = zza.zzi(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new GetServiceRequest(zzg3, zzg2, zzg, zzq, zzr, array, zzs, account, zzi);
    }
    
    public GetServiceRequest[] zzgn(final int n) {
        return new GetServiceRequest[n];
    }
}
