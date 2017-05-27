// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzi implements Parcelable$Creator<AdSizeParcel>
{
    static void zza(final AdSizeParcel adSizeParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, adSizeParcel.versionCode);
        zzb.zza(parcel, 2, adSizeParcel.zzaxi, false);
        zzb.zzc(parcel, 3, adSizeParcel.height);
        zzb.zzc(parcel, 4, adSizeParcel.heightPixels);
        zzb.zza(parcel, 5, adSizeParcel.zzaxj);
        zzb.zzc(parcel, 6, adSizeParcel.width);
        zzb.zzc(parcel, 7, adSizeParcel.widthPixels);
        zzb.zza(parcel, 8, adSizeParcel.zzaxk, n, false);
        zzb.zza(parcel, 9, adSizeParcel.zzaxl);
        zzb.zza(parcel, 10, adSizeParcel.zzaxm);
        zzb.zza(parcel, 11, adSizeParcel.zzaxn);
        zzb.zzaj(parcel, zzcr);
    }
    
    public AdSizeParcel zzf(final Parcel parcel) {
        AdSizeParcel[] array = null;
        boolean zzc = false;
        final int zzcq = zza.zzcq(parcel);
        boolean zzc2 = false;
        boolean zzc3 = false;
        int zzg = 0;
        int zzg2 = 0;
        boolean zzc4 = false;
        int zzg3 = 0;
        int zzg4 = 0;
        String zzq = null;
        int zzg5 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg5 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg4 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzc4 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 8: {
                    array = zza.zzb(parcel, zzcp, (android.os.Parcelable$Creator<AdSizeParcel>)AdSizeParcel.CREATOR);
                    continue;
                }
                case 9: {
                    zzc3 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 11: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new AdSizeParcel(zzg5, zzq, zzg4, zzg3, zzc4, zzg2, zzg, array, zzc3, zzc2, zzc);
    }
    
    public AdSizeParcel[] zzu(final int n) {
        return new AdSizeParcel[n];
    }
}
