// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.internal.client.zzah;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.internal.client.zzag;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import android.content.Context;

public class MobileAds
{
    public static RewardedVideoAd getRewardedVideoAdInstance(final Context context) {
        return zzag.zzki().getRewardedVideoAdInstance(context);
    }
    
    @Deprecated
    public static void initialize(final Context context) {
        initialize(context, null, null);
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(final Context context, final String s) {
        initialize(context, s, null);
    }
    
    @Deprecated
    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(final Context context, final String s, final Settings settings) {
        final zzag zzki = zzag.zzki();
        zzah zzdi;
        if (settings == null) {
            zzdi = null;
        }
        else {
            zzdi = settings.zzdi();
        }
        zzki.zza(context, s, zzdi);
    }
    
    public static void openDebugMenu(final Context context, final String s) {
        zzag.zzki().openDebugMenu(context, s);
    }
    
    public static void setAppMuted(final boolean appMuted) {
        zzag.zzki().setAppMuted(appMuted);
    }
    
    public static void setAppVolume(final float appVolume) {
        zzag.zzki().setAppVolume(appVolume);
    }
    
    public static final class Settings
    {
        private final zzah zzakc;
        
        public Settings() {
            this.zzakc = new zzah();
        }
        
        @Deprecated
        public String getTrackingId() {
            return this.zzakc.getTrackingId();
        }
        
        @Deprecated
        public boolean isGoogleAnalyticsEnabled() {
            return this.zzakc.isGoogleAnalyticsEnabled();
        }
        
        @Deprecated
        public Settings setGoogleAnalyticsEnabled(final boolean b) {
            this.zzakc.zzq(b);
            return this;
        }
        
        @Deprecated
        public Settings setTrackingId(final String s) {
            this.zzakc.zzar(s);
            return this;
        }
        
        zzah zzdi() {
            return this.zzakc;
        }
    }
}
