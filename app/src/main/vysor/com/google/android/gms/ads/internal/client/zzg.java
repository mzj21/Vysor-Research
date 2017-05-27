// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import java.util.List;
import android.os.Bundle;
import android.location.Location;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzg implements Parcelable$Creator<AdRequestParcel>
{
    static void zza(final AdRequestParcel adRequestParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, adRequestParcel.versionCode);
        zzb.zza(parcel, 2, adRequestParcel.zzawd);
        zzb.zza(parcel, 3, adRequestParcel.extras, false);
        zzb.zzc(parcel, 4, adRequestParcel.zzawe);
        zzb.zzb(parcel, 5, adRequestParcel.zzawf, false);
        zzb.zza(parcel, 6, adRequestParcel.zzawg);
        zzb.zzc(parcel, 7, adRequestParcel.zzawh);
        zzb.zza(parcel, 8, adRequestParcel.zzawi);
        zzb.zza(parcel, 9, adRequestParcel.zzawj, false);
        zzb.zza(parcel, 10, (Parcelable)adRequestParcel.zzawk, n, false);
        zzb.zza(parcel, 11, (Parcelable)adRequestParcel.zzawl, n, false);
        zzb.zza(parcel, 12, adRequestParcel.zzawm, false);
        zzb.zza(parcel, 13, adRequestParcel.zzawn, false);
        zzb.zza(parcel, 14, adRequestParcel.zzawo, false);
        zzb.zzb(parcel, 15, adRequestParcel.zzawp, false);
        zzb.zza(parcel, 16, adRequestParcel.zzawq, false);
        zzb.zza(parcel, 17, adRequestParcel.zzawr, false);
        zzb.zza(parcel, 18, adRequestParcel.zzaws);
        zzb.zzaj(parcel, zzcr);
    }
    
    public AdRequestParcel zze(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        long zzi = 0L;
        Bundle zzs = null;
        int zzg2 = 0;
        List<String> zzae = null;
        boolean zzc = false;
        int zzg3 = 0;
        boolean zzc2 = false;
        String zzq = null;
        SearchAdRequestParcel searchAdRequestParcel = null;
        Location location = null;
        String zzq2 = null;
        Bundle zzs2 = null;
        Bundle zzs3 = null;
        List<String> zzae2 = null;
        String zzq3 = null;
        String zzq4 = null;
        boolean zzc3 = false;
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
                    zzi = zza.zzi(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzs = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzae = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzc = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzc2 = zza.zzc(parcel, zzcp);
                    continue;
                }
                case 9: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 10: {
                    searchAdRequestParcel = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<SearchAdRequestParcel>)SearchAdRequestParcel.CREATOR);
                    continue;
                }
                case 11: {
                    location = zza.zza(parcel, zzcp, (android.os.Parcelable$Creator<Location>)Location.CREATOR);
                    continue;
                }
                case 12: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 13: {
                    zzs2 = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 14: {
                    zzs3 = zza.zzs(parcel, zzcp);
                    continue;
                }
                case 15: {
                    zzae2 = zza.zzae(parcel, zzcp);
                    continue;
                }
                case 16: {
                    zzq3 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 17: {
                    zzq4 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 18: {
                    zzc3 = zza.zzc(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new AdRequestParcel(zzg, zzi, zzs, zzg2, zzae, zzc, zzg3, zzc2, zzq, searchAdRequestParcel, location, zzq2, zzs2, zzs3, zzae2, zzq3, zzq4, zzc3);
    }
    
    public AdRequestParcel[] zzt(final int n) {
        return new AdRequestParcel[n];
    }
}
