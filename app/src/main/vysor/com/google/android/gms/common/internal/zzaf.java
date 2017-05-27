// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzaf implements Parcelable$Creator<SignInButtonConfig>
{
    static void zza(final SignInButtonConfig signInButtonConfig, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, signInButtonConfig.mVersionCode);
        zzb.zzc(parcel, 2, signInButtonConfig.zzavh());
        zzb.zzc(parcel, 3, signInButtonConfig.zzavi());
        zzb.zza(parcel, 4, signInButtonConfig.zzavj(), n, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public SignInButtonConfig zzcn(final Parcel parcel) {
        int zzg = 0;
        final int zzcq = zza.zzcq(parcel);
        Scope[] array = null;
        int zzg2 = 0;
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
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 4: {
                    array = zza.zzb(parcel, zzcp, Scope.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new SignInButtonConfig(zzg3, zzg2, zzg, array);
    }
    
    public SignInButtonConfig[] zzgt(final int n) {
        return new SignInButtonConfig[n];
    }
}
