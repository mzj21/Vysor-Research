// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Scope;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzf implements Parcelable$Creator<RecordConsentRequest>
{
    static void zza(final RecordConsentRequest recordConsentRequest, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, recordConsentRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)recordConsentRequest.getAccount(), n, false);
        zzb.zza(parcel, 3, recordConsentRequest.zzcdi(), n, false);
        zzb.zza(parcel, 4, recordConsentRequest.zzahn(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public RecordConsentRequest[] zzaag(final int n) {
        return new RecordConsentRequest[n];
    }
    
    public RecordConsentRequest zzsl(final Parcel parcel) {
        String s = null;
        final int zzcq = zza.zzcq(parcel);
        int n = 0;
        Scope[] array = null;
        Account account = null;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            String zzq = null;
            Scope[] array2 = null;
            Account account2 = null;
            int n2 = 0;
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    zzq = s;
                    array2 = array;
                    account2 = account;
                    n2 = n;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzcp);
                    final String s2 = s;
                    array2 = array;
                    account2 = account;
                    n2 = zzg;
                    zzq = s2;
                    break;
                }
                case 2: {
                    final Account account3 = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    n2 = n;
                    final Scope[] array3 = array;
                    account2 = account3;
                    zzq = s;
                    array2 = array3;
                    break;
                }
                case 3: {
                    final Scope[] array4 = zza.zzb(parcel, zzcp, Scope.CREATOR);
                    account2 = account;
                    n2 = n;
                    final String s3 = s;
                    array2 = array4;
                    zzq = s3;
                    break;
                }
                case 4: {
                    zzq = zza.zzq(parcel, zzcp);
                    array2 = array;
                    account2 = account;
                    n2 = n;
                    break;
                }
            }
            n = n2;
            account = account2;
            array = array2;
            s = zzq;
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new RecordConsentRequest(n, account, array, s);
    }
}
