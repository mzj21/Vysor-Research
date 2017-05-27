// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.cache;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzb implements Parcelable$Creator<CacheEntryParcel>
{
    static void zza(final CacheEntryParcel cacheEntryParcel, final Parcel parcel, final int n) {
        final int zzcr = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, cacheEntryParcel.version);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable)cacheEntryParcel.zziw(), n, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcr);
    }
    
    public CacheEntryParcel zzc(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
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
                    parcelFileDescriptor = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new CacheEntryParcel(zzg, parcelFileDescriptor);
    }
    
    public CacheEntryParcel[] zzr(final int n) {
        return new CacheEntryParcel[n];
    }
}
