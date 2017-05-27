// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import android.os.Bundle;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zzh
{
    public static final zzh zzaxh;
    
    static {
        zzaxh = new zzh();
    }
    
    public static zzh zzjb() {
        return zzh.zzaxh;
    }
    
    public AdRequestParcel zza(final Context context, final zzad zzad) {
        final Date birthday = zzad.getBirthday();
        long time;
        if (birthday != null) {
            time = birthday.getTime();
        }
        else {
            time = -1L;
        }
        final String contentUrl = zzad.getContentUrl();
        final int gender = zzad.getGender();
        final Set<String> keywords = zzad.getKeywords();
        Object unmodifiableList;
        if (!keywords.isEmpty()) {
            unmodifiableList = Collections.unmodifiableList((List<?>)new ArrayList<Object>(keywords));
        }
        else {
            unmodifiableList = null;
        }
        final boolean testDevice = zzad.isTestDevice(context);
        final int zzkd = zzad.zzkd();
        final Location location = zzad.getLocation();
        final Bundle networkExtrasBundle = zzad.getNetworkExtrasBundle(AdMobAdapter.class);
        final boolean manualImpressionsEnabled = zzad.getManualImpressionsEnabled();
        final String publisherProvidedId = zzad.getPublisherProvidedId();
        final SearchAdRequest zzka = zzad.zzka();
        SearchAdRequestParcel searchAdRequestParcel;
        if (zzka != null) {
            searchAdRequestParcel = new SearchAdRequestParcel(zzka);
        }
        else {
            searchAdRequestParcel = null;
        }
        final Context applicationContext = context.getApplicationContext();
        String zza = null;
        if (applicationContext != null) {
            zza = zzm.zzjr().zza(Thread.currentThread().getStackTrace(), applicationContext.getPackageName());
        }
        return new AdRequestParcel(7, time, networkExtrasBundle, gender, (List<String>)unmodifiableList, testDevice, zzkd, manualImpressionsEnabled, publisherProvidedId, searchAdRequestParcel, location, contentUrl, zzad.zzkc(), zzad.getCustomTargeting(), Collections.unmodifiableList((List<? extends String>)new ArrayList<String>(zzad.zzke())), zzad.zzjz(), zza, zzad.isDesignedForFamilies());
    }
    
    public RewardedVideoAdRequestParcel zza(final Context context, final zzad zzad, final String s) {
        return new RewardedVideoAdRequestParcel(this.zza(context, zzad), s);
    }
}
