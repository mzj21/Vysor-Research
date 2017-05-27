// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin;

import java.util.List;
import com.google.android.gms.common.api.Scope;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<GoogleSignInAccount>
{
    static void zza(final GoogleSignInAccount googleSignInAccount, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, googleSignInAccount.versionCode);
        zzb.zza(parcel, 2, googleSignInAccount.getId(), false);
        zzb.zza(parcel, 3, googleSignInAccount.getIdToken(), false);
        zzb.zza(parcel, 4, googleSignInAccount.getEmail(), false);
        zzb.zza(parcel, 5, googleSignInAccount.getDisplayName(), false);
        zzb.zza(parcel, 6, (Parcelable)googleSignInAccount.getPhotoUrl(), n, false);
        zzb.zza(parcel, 7, googleSignInAccount.getServerAuthCode(), false);
        zzb.zza(parcel, 8, googleSignInAccount.zzahe());
        zzb.zza(parcel, 9, googleSignInAccount.zzahf(), false);
        zzb.zzc(parcel, 10, googleSignInAccount.fK, false);
        zzb.zza(parcel, 11, googleSignInAccount.getGivenName(), false);
        zzb.zza(parcel, 12, googleSignInAccount.getFamilyName(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public GoogleSignInAccount zzav(final Parcel parcel) {
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        int zzg = 0;
        String zzq = null;
        String zzq2 = null;
        String zzq3 = null;
        String zzq4 = null;
        Uri uri = null;
        String zzq5 = null;
        long zzi = 0L;
        String zzq6 = null;
        List<Scope> zzc = null;
        String zzq7 = null;
        String zzq8 = null;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzq = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzq2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzq3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzq4 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 6: {
                    uri = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 7: {
                    zzq5 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzi = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzcp);
                    continue;
                }
                case 9: {
                    zzq6 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzc = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcp, Scope.CREATOR);
                    continue;
                }
                case 11: {
                    zzq7 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
                case 12: {
                    zzq8 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new GoogleSignInAccount(zzg, zzq, zzq2, zzq3, zzq4, uri, zzq5, zzi, zzq6, zzc, zzq7, zzq8);
    }
    
    public GoogleSignInAccount[] zzdg(final int n) {
        return new GoogleSignInAccount[n];
    }
}
