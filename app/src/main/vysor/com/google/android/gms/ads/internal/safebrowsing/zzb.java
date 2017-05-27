// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.safebrowsing;

import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<SafeBrowsingConfigParcel>
{
    static void zza(final SafeBrowsingConfigParcel safeBrowsingConfigParcel, final Parcel parcel, final int n) {
        final int zzcr = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, safeBrowsingConfigParcel.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, safeBrowsingConfigParcel.zzcnz, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, safeBrowsingConfigParcel.zzcoa, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, safeBrowsingConfigParcel.zzcob);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, safeBrowsingConfigParcel.zzcoc);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcr);
    }
    
    public SafeBrowsingConfigParcel[] zzbb(final int n) {
        return new SafeBrowsingConfigParcel[n];
    }
    
    public SafeBrowsingConfigParcel zzu(final Parcel parcel) {
        String zzq = null;
        boolean zzc = false;
        final int zzcq = zza.zzcq(parcel);
        boolean zzc2 = false;
        String zzq2 = null;
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
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new SafeBrowsingConfigParcel(zzg, zzq2, zzq, zzc2, zzc);
    }
}
