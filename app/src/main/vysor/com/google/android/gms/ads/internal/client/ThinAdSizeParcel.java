// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import com.google.android.gms.internal.zziy;

@zziy
public class ThinAdSizeParcel extends AdSizeParcel
{
    public ThinAdSizeParcel(final AdSizeParcel adSizeParcel) {
        super(adSizeParcel.versionCode, adSizeParcel.zzaxi, adSizeParcel.height, adSizeParcel.heightPixels, adSizeParcel.zzaxj, adSizeParcel.width, adSizeParcel.widthPixels, adSizeParcel.zzaxk, adSizeParcel.zzaxl, adSizeParcel.zzaxm, adSizeParcel.zzaxn);
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, this.versionCode);
        zzb.zza(parcel, 2, this.zzaxi, false);
        zzb.zzc(parcel, 3, this.height);
        zzb.zzc(parcel, 6, this.width);
        zzb.zzaj(parcel, zzcr);
    }
}
