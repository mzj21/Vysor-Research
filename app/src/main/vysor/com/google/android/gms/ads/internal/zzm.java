// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzm implements Parcelable$Creator<InterstitialAdParameterParcel>
{
    static void zza(final InterstitialAdParameterParcel interstitialAdParameterParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, interstitialAdParameterParcel.versionCode);
        zzb.zza(parcel, 2, interstitialAdParameterParcel.zzanx);
        zzb.zza(parcel, 3, interstitialAdParameterParcel.zzany);
        zzb.zza(parcel, 4, interstitialAdParameterParcel.zzanz, false);
        zzb.zza(parcel, 5, interstitialAdParameterParcel.zzaoa);
        zzb.zza(parcel, 6, interstitialAdParameterParcel.zzaob);
        zzb.zzc(parcel, 7, interstitialAdParameterParcel.zzaoc);
        zzb.zzaj(parcel, zzcr);
    }
    
    public InterstitialAdParameterParcel zzb(final Parcel parcel) {
        int zzg = 0;
        final int zzcq = zza.zzcq(parcel);
        String zzq = null;
        float zzl = 0.0f;
        boolean zzc = false;
        boolean zzc2 = false;
        boolean zzc3 = false;
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
                    zzc3 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzl = zza.zzl(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new InterstitialAdParameterParcel(zzg2, zzc3, zzc2, zzq, zzc, zzl, zzg);
    }
    
    public InterstitialAdParameterParcel[] zzi(final int n) {
        return new InterstitialAdParameterParcel[n];
    }
}
