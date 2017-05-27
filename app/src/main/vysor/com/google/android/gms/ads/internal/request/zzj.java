// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzj implements Parcelable$Creator<CapabilityParcel>
{
    static void zza(final CapabilityParcel capabilityParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, capabilityParcel.versionCode);
        zzb.zza(parcel, 2, capabilityParcel.zzcib);
        zzb.zza(parcel, 3, capabilityParcel.zzcic);
        zzb.zza(parcel, 4, capabilityParcel.zzcid);
        zzb.zzaj(parcel, zzcr);
    }
    
    public CapabilityParcel[] zzat(final int n) {
        return new CapabilityParcel[n];
    }
    
    public CapabilityParcel zzp(final Parcel parcel) {
        boolean zzc = false;
        final int zzcq = zza.zzcq(parcel);
        boolean zzc2 = false;
        boolean zzc3 = false;
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
                    zzc3 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new CapabilityParcel(zzg, zzc3, zzc2, zzc);
    }
}
