// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzk implements Parcelable$Creator<NativeAdOptionsParcel>
{
    static void zza(final NativeAdOptionsParcel nativeAdOptionsParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, nativeAdOptionsParcel.versionCode);
        zzb.zza(parcel, 2, nativeAdOptionsParcel.zzblb);
        zzb.zzc(parcel, 3, nativeAdOptionsParcel.zzblc);
        zzb.zza(parcel, 4, nativeAdOptionsParcel.zzbld);
        zzb.zzc(parcel, 5, nativeAdOptionsParcel.zzble);
        zzb.zza(parcel, 6, (Parcelable)nativeAdOptionsParcel.zzblf, n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public NativeAdOptionsParcel zzi(final Parcel parcel) {
        int zzg = 0;
        final int zzcq = zza.zzcq(parcel);
        VideoOptionsParcel videoOptionsParcel = null;
        boolean zzc = false;
        int zzg2 = 0;
        boolean zzc2 = false;
        int zzg3 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 2: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 6: {
                    videoOptionsParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<VideoOptionsParcel>)VideoOptionsParcel.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new NativeAdOptionsParcel(zzg3, zzc2, zzg2, zzc, zzg, videoOptionsParcel);
    }
    
    public NativeAdOptionsParcel[] zzy(final int n) {
        return new NativeAdOptionsParcel[n];
    }
}
