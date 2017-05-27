// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzm implements Parcelable$Creator<LargeParcelTeleporter>
{
    static void zza(final LargeParcelTeleporter largeParcelTeleporter, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, largeParcelTeleporter.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)largeParcelTeleporter.zzcie, n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public LargeParcelTeleporter[] zzau(final int n) {
        return new LargeParcelTeleporter[n];
    }
    
    public LargeParcelTeleporter zzq(final Parcel parcel) {
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
        return new LargeParcelTeleporter(zzg, parcelFileDescriptor);
    }
}
