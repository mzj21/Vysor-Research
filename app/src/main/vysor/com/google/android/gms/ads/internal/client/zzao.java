// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzao implements Parcelable$Creator<SearchAdRequestParcel>
{
    static void zza(final SearchAdRequestParcel searchAdRequestParcel, final Parcel parcel, final int n) {
        final int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, searchAdRequestParcel.versionCode);
        zzb.zzc(parcel, 2, searchAdRequestParcel.zzazp);
        zzb.zzc(parcel, 3, searchAdRequestParcel.backgroundColor);
        zzb.zzc(parcel, 4, searchAdRequestParcel.zzazq);
        zzb.zzc(parcel, 5, searchAdRequestParcel.zzazr);
        zzb.zzc(parcel, 6, searchAdRequestParcel.zzazs);
        zzb.zzc(parcel, 7, searchAdRequestParcel.zzazt);
        zzb.zzc(parcel, 8, searchAdRequestParcel.zzazu);
        zzb.zzc(parcel, 9, searchAdRequestParcel.zzazv);
        zzb.zza(parcel, 10, searchAdRequestParcel.zzazw, false);
        zzb.zzc(parcel, 11, searchAdRequestParcel.zzazx);
        zzb.zza(parcel, 12, searchAdRequestParcel.zzazy, false);
        zzb.zzc(parcel, 13, searchAdRequestParcel.zzazz);
        zzb.zzc(parcel, 14, searchAdRequestParcel.zzbaa);
        zzb.zza(parcel, 15, searchAdRequestParcel.zzbab, false);
        zzb.zzaj(parcel, zzcr);
    }
    
    public SearchAdRequestParcel zzg(final Parcel parcel) {
        final int zzcq = zza.zzcq(parcel);
        int zzg = 0;
        int zzg2 = 0;
        int zzg3 = 0;
        int zzg4 = 0;
        int zzg5 = 0;
        int zzg6 = 0;
        int zzg7 = 0;
        int zzg8 = 0;
        int zzg9 = 0;
        String zzq = null;
        int zzg10 = 0;
        String zzq2 = null;
        int zzg11 = 0;
        int zzg12 = 0;
        String zzq3 = null;
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
                    zzg2 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 3: {
                    zzg3 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 4: {
                    zzg4 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 5: {
                    zzg5 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 6: {
                    zzg6 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 7: {
                    zzg7 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 8: {
                    zzg8 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 9: {
                    zzg9 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 10: {
                    zzq = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 11: {
                    zzg10 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 12: {
                    zzq2 = zza.zzq(parcel, zzcp);
                    continue;
                }
                case 13: {
                    zzg11 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 14: {
                    zzg12 = zza.zzg(parcel, zzcp);
                    continue;
                }
                case 15: {
                    zzq3 = zza.zzq(parcel, zzcp);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        return new SearchAdRequestParcel(zzg, zzg2, zzg3, zzg4, zzg5, zzg6, zzg7, zzg8, zzg9, zzq, zzg10, zzq2, zzg11, zzg12, zzq3);
    }
    
    public SearchAdRequestParcel[] zzw(final int n) {
        return new SearchAdRequestParcel[n];
    }
}
