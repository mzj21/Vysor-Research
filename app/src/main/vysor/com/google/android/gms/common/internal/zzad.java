// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzad implements Parcelable$Creator<ResolveAccountRequest>
{
    static void zza(final ResolveAccountRequest resolveAccountRequest, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, resolveAccountRequest.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)resolveAccountRequest.getAccount(), n, false);
        zzb.zzc(parcel, 3, resolveAccountRequest.getSessionId());
        zzb.zza(parcel, 4, (Parcelable)resolveAccountRequest.zzavc(), n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public ResolveAccountRequest zzcl(final Parcel parcel) {
        GoogleSignInAccount googleSignInAccount = null;
        int n = 0;
        final int zzcq = zza.zzcq(parcel);
        Account account = null;
        int n2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            GoogleSignInAccount googleSignInAccount2 = null;
            int n3 = 0;
            Account account2 = null;
            int n4 = 0;
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    googleSignInAccount2 = googleSignInAccount;
                    n3 = n;
                    account2 = account;
                    n4 = n2;
                    break;
                }
                case 1: {
                    final int zzg = zza.zzg(parcel, zzcp);
                    final GoogleSignInAccount googleSignInAccount3 = googleSignInAccount;
                    n3 = n;
                    account2 = account;
                    n4 = zzg;
                    googleSignInAccount2 = googleSignInAccount3;
                    break;
                }
                case 2: {
                    final Account account3 = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    n4 = n2;
                    final int n5 = n;
                    account2 = account3;
                    googleSignInAccount2 = googleSignInAccount;
                    n3 = n5;
                    break;
                }
                case 3: {
                    final int zzg2 = zza.zzg(parcel, zzcp);
                    account2 = account;
                    n4 = n2;
                    final GoogleSignInAccount googleSignInAccount4 = googleSignInAccount;
                    n3 = zzg2;
                    googleSignInAccount2 = googleSignInAccount4;
                    break;
                }
                case 4: {
                    googleSignInAccount2 = zza.zza(parcel, zzcp, GoogleSignInAccount.CREATOR);
                    n3 = n;
                    account2 = account;
                    n4 = n2;
                    break;
                }
            }
            n2 = n4;
            account = account2;
            n = n3;
            googleSignInAccount = googleSignInAccount2;
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new ResolveAccountRequest(n2, account, n, googleSignInAccount);
    }
    
    public ResolveAccountRequest[] zzgr(final int n) {
        return new ResolveAccountRequest[n];
    }
}
