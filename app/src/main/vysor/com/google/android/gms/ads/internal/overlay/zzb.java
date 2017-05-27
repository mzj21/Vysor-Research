// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<AdLauncherIntentInfoParcel>
{
    static void zza(final AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, final Parcel parcel, final int n) {
        final int zzcr = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, adLauncherIntentInfoParcel.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, adLauncherIntentInfoParcel.zzbwe, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, adLauncherIntentInfoParcel.url, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, adLauncherIntentInfoParcel.mimeType, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, adLauncherIntentInfoParcel.packageName, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, adLauncherIntentInfoParcel.zzbwf, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, adLauncherIntentInfoParcel.zzbwg, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, adLauncherIntentInfoParcel.zzbwh, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 9, (Parcelable)adLauncherIntentInfoParcel.intent, n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcr);
    }
    
    public AdLauncherIntentInfoParcel[] zzae(final int n) {
        return new AdLauncherIntentInfoParcel[n];
    }
    
    public AdLauncherIntentInfoParcel zzj(final Parcel parcel) {
        Intent intent = null;
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        String zzq = null;
        String zzq2 = null;
        String zzq3 = null;
        String zzq4 = null;
        String zzq5 = null;
        String zzq6 = null;
        String zzq7 = null;
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
                    zzq7 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzq6 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzq5 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzq4 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzq3 = zza.zzq(parcel, zzcp);
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
                case 9: {
                    intent = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Intent>)Intent.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new AdLauncherIntentInfoParcel(zzg, zzq7, zzq6, zzq5, zzq4, zzq3, zzq2, zzq, intent);
    }
}
