// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.ArrayList;
import android.accounts.Account;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<GoogleSignInOptions>
{
    static void zza(final GoogleSignInOptions googleSignInOptions, final Parcel parcel, final int n) {
        final int zzcr = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, googleSignInOptions.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, googleSignInOptions.zzahj(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable)googleSignInOptions.getAccount(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, googleSignInOptions.zzahk());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, googleSignInOptions.zzahl());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, googleSignInOptions.zzahm());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, googleSignInOptions.zzahn(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, googleSignInOptions.zzaho(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcr);
    }
    
    public GoogleSignInOptions zzaw(final Parcel parcel) {
        String zzq = null;
        boolean zzc = false;
        final int zzcq = zza.zzcq(parcel);
        String zzq2 = null;
        boolean zzc2 = false;
        boolean zzc3 = false;
        Account account = null;
        ArrayList<Scope> zzc4 = null;
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
                    zzc4 = zza.zzc(parcel, zzcp, Scope.CREATOR);
                    continue;
                }
                case 3: {
                    account = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Account>)Account.CREATOR);
                    continue;
                }
                case 4: {
                    zzc3 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new GoogleSignInOptions(zzg, zzc4, account, zzc3, zzc2, zzc, zzq2, zzq);
    }
    
    public GoogleSignInOptions[] zzdh(final int n) {
        return new GoogleSignInOptions[n];
    }
}
