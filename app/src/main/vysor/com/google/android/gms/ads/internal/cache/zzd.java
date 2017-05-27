// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.cache;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzd implements Parcelable$Creator<CacheOffering>
{
    static void zza(final CacheOffering cacheOffering, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, cacheOffering.version);
        zzb.zza(parcel, 2, cacheOffering.url, false);
        zzb.zza(parcel, 3, cacheOffering.zzavv);
        zzb.zza(parcel, 4, cacheOffering.zzavw, false);
        zzb.zza(parcel, 5, cacheOffering.zzavx, false);
        zzb.zza(parcel, 6, cacheOffering.zzavy, false);
        zzb.zza(parcel, 7, cacheOffering.zzavz, false);
        zzb.zza(parcel, 8, cacheOffering.zzawa);
        zzb.zzaj(parcel, zzcr);
    }
    
    public CacheOffering zzd(final Parcel parcel) {
        boolean zzc = false;
        Bundle zzs = null;
        final int zzcq = zza.zzcq(parcel);
        long zzi = 0L;
        String zzq = null;
        String zzq2 = null;
        String zzq3 = null;
        String zzq4 = null;
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
                    zzq4 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzi = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzq3 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzs = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new CacheOffering(zzg, zzq4, zzi, zzq3, zzq2, zzq, zzs, zzc);
    }
    
    public CacheOffering[] zzs(final int n) {
        return new CacheOffering[n];
    }
}
