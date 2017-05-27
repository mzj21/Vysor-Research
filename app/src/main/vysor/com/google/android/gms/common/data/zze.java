// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Bundle;
import android.database.CursorWindow;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zze implements Parcelable$Creator<DataHolder>
{
    static void zza(final DataHolder dataHolder, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zza(parcel, 1, dataHolder.zzatf(), false);
        zzb.zza(parcel, 2, dataHolder.zzatg(), n, false);
        zzb.zzc(parcel, 3, dataHolder.getStatusCode());
        zzb.zza(parcel, 4, dataHolder.zzasz(), false);
        zzb.zzc(parcel, 1000, dataHolder.getVersionCode());
        zzb.zzaj(parcel, zzcr);
    }
    
    public DataHolder zzcg(final Parcel parcel) {
        int zzg = 0;
        Bundle zzs = null;
        final int zzcq = zza.zzcq(parcel);
        CursorWindow[] array = null;
        String[] zzac = null;
        int zzg2 = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                default: {
                    zza.zzb(parcel, zzcp);
                    continue;
                }
                case 1: {
                    zzac = zza.zzac(parcel, zzcp);
                    continue;
                }
                case 2: {
                    array = zza.zzb(parcel, zzcp, (android.os.Parcelable$Creator<CursorWindow>)CursorWindow.CREATOR);
                    continue;
                }
                case 3: {
                    zzg = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzs = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 1000: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        final DataHolder dataHolder = new DataHolder(zzg2, zzac, array, zzg, zzs);
        dataHolder.zzate();
        return dataHolder;
    }
    
    public DataHolder[] zzge(final int n) {
        return new DataHolder[n];
    }
}
