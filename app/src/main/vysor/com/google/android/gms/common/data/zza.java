// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<BitmapTeleporter>
{
    static void zza(final BitmapTeleporter bitmapTeleporter, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, bitmapTeleporter.mVersionCode);
        zzb.zza(parcel, 2, (Parcelable)bitmapTeleporter.zzcie, n, false);
        zzb.zzc(parcel, 3, bitmapTeleporter.lN);
        zzb.zzaj(parcel, zzcr);
    }
    
    public BitmapTeleporter zzcf(final Parcel parcel) {
        int n = 0;
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        int n2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            int zzg = 0;
            ParcelFileDescriptor parcelFileDescriptor2 = null;
            int n3 = 0;
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp);
                    zzg = n;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    n3 = n2;
                    break;
                }
                case 1: {
                    final int zzg2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    final int n4 = n;
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    n3 = zzg2;
                    zzg = n4;
                    break;
                }
                case 2: {
                    final ParcelFileDescriptor parcelFileDescriptor3 = com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    n3 = n2;
                    zzg = n;
                    parcelFileDescriptor2 = parcelFileDescriptor3;
                    break;
                }
                case 3: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    parcelFileDescriptor2 = parcelFileDescriptor;
                    n3 = n2;
                    break;
                }
            }
            n2 = n3;
            parcelFileDescriptor = parcelFileDescriptor2;
            n = zzg;
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new BitmapTeleporter(n2, parcelFileDescriptor, n);
    }
    
    public BitmapTeleporter[] zzfy(final int n) {
        return new BitmapTeleporter[n];
    }
}
