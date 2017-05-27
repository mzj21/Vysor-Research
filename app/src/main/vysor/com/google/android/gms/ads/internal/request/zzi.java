// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzi implements Parcelable$Creator<AutoClickProtectionConfigurationParcel>
{
    static void zza(final AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, autoClickProtectionConfigurationParcel.versionCode);
        zzb.zza(parcel, 2, autoClickProtectionConfigurationParcel.zzchz);
        zzb.zzb(parcel, 3, autoClickProtectionConfigurationParcel.zzcia, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public AutoClickProtectionConfigurationParcel[] zzas(final int n) {
        return new AutoClickProtectionConfigurationParcel[n];
    }
    
    public AutoClickProtectionConfigurationParcel zzo(final Parcel parcel) {
        boolean zzc = false;
        final int zzcq = zza.zzcq(parcel);
        List<String> zzae = null;
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
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzae = zza.zzae(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new AutoClickProtectionConfigurationParcel(zzg, zzc, zzae);
    }
}
