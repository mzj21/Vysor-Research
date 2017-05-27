// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<GInAppPurchaseManagerInfoParcel>
{
    static void zza(final GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, gInAppPurchaseManagerInfoParcel.versionCode);
        zzb.zza(parcel, 3, gInAppPurchaseManagerInfoParcel.zzql(), false);
        zzb.zza(parcel, 4, gInAppPurchaseManagerInfoParcel.zzqm(), false);
        zzb.zza(parcel, 5, gInAppPurchaseManagerInfoParcel.zzqn(), false);
        zzb.zza(parcel, 6, gInAppPurchaseManagerInfoParcel.zzqk(), false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public GInAppPurchaseManagerInfoParcel[] zzaj(final int n) {
        return new GInAppPurchaseManagerInfoParcel[n];
    }
    
    public GInAppPurchaseManagerInfoParcel zzl(final Parcel parcel) {
        IBinder zzr = null;
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        int zzg = 0;
        IBinder zzr2 = null;
        IBinder zzr3 = null;
        IBinder zzr4 = null;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzr4 = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzr3 = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzr2 = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzr = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new GInAppPurchaseManagerInfoParcel(zzg, zzr4, zzr3, zzr2, zzr);
    }
}
