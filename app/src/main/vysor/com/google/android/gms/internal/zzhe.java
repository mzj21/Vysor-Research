// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Set;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.zza;
import com.google.ads.AdSize;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.ads.AdRequest;

@zziy
public final class zzhe
{
    public static int zza(final AdRequest.ErrorCode errorCode) {
        int n = 0;
        switch (zzhe$1.zzbuk[errorCode.ordinal()]) {
            default: {
                n = 0;
                break;
            }
            case 2: {
                n = 1;
                break;
            }
            case 3: {
                n = 2;
                break;
            }
            case 4: {
                n = 3;
                break;
            }
        }
        return n;
    }
    
    public static AdRequest.Gender zzad(final int n) {
        AdRequest.Gender gender = null;
        switch (n) {
            default: {
                gender = AdRequest.Gender.UNKNOWN;
                break;
            }
            case 2: {
                gender = AdRequest.Gender.FEMALE;
                break;
            }
            case 1: {
                gender = AdRequest.Gender.MALE;
                break;
            }
        }
        return gender;
    }
    
    public static AdSize zzc(final AdSizeParcel adSizeParcel) {
        int i = 0;
        final AdSize[] array = { AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER };
        while (i < 6) {
            if (array[i].getWidth() == adSizeParcel.width && array[i].getHeight() == adSizeParcel.height) {
                return array[i];
            }
            ++i;
        }
        return new AdSize(zza.zza(adSizeParcel.width, adSizeParcel.height, adSizeParcel.zzaxi));
    }
    
    public static MediationAdRequest zzs(final AdRequestParcel adRequestParcel) {
        HashSet<String> set;
        if (adRequestParcel.zzawf != null) {
            set = new HashSet<String>(adRequestParcel.zzawf);
        }
        else {
            set = null;
        }
        return new MediationAdRequest(new Date(adRequestParcel.zzawd), zzad(adRequestParcel.zzawe), set, adRequestParcel.zzawg, adRequestParcel.zzawl);
    }
}
