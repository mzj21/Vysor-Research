// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import android.content.Context;

public final class AdSize
{
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER;
    public static final AdSize FLUID;
    public static final AdSize FULL_BANNER;
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER;
    public static final AdSize LEADERBOARD;
    public static final AdSize MEDIUM_RECTANGLE;
    public static final AdSize SEARCH;
    public static final AdSize SMART_BANNER;
    public static final AdSize WIDE_SKYSCRAPER;
    private final int zzajw;
    private final int zzajx;
    private final String zzajy;
    
    static {
        BANNER = new AdSize(320, 50, "320x50_mb");
        FULL_BANNER = new AdSize(468, 60, "468x60_as");
        LARGE_BANNER = new AdSize(320, 100, "320x100_as");
        LEADERBOARD = new AdSize(728, 90, "728x90_as");
        MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
        WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
        SMART_BANNER = new AdSize(-1, -2, "smart_banner");
        FLUID = new AdSize(-3, -4, "fluid");
        SEARCH = new AdSize(-3, 0, "search_v2");
    }
    
    public AdSize(final int n, final int n2) {
        String value;
        if (n == -1) {
            value = "FULL";
        }
        else {
            value = String.valueOf(n);
        }
        String value2;
        if (n2 == -2) {
            value2 = "AUTO";
        }
        else {
            value2 = String.valueOf(n2);
        }
        final String value3 = String.valueOf("_as");
        this(n, n2, new StringBuilder(1 + String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(value3).length()).append(value).append("x").append(value2).append(value3).toString());
    }
    
    AdSize(final int zzajw, final int zzajx, final String zzajy) {
        if (zzajw < 0 && zzajw != -1 && zzajw != -3) {
            throw new IllegalArgumentException(new StringBuilder(37).append("Invalid mWidth for AdSize: ").append(zzajw).toString());
        }
        if (zzajx < 0 && zzajx != -2 && zzajx != -4) {
            throw new IllegalArgumentException(new StringBuilder(38).append("Invalid mHeight for AdSize: ").append(zzajx).toString());
        }
        this.zzajw = zzajw;
        this.zzajx = zzajx;
        this.zzajy = zzajy;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (o != this) {
            if (!(o instanceof AdSize)) {
                b = false;
            }
            else {
                final AdSize adSize = (AdSize)o;
                if (this.zzajw != adSize.zzajw || this.zzajx != adSize.zzajx || !this.zzajy.equals(adSize.zzajy)) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public int getHeight() {
        return this.zzajx;
    }
    
    public int getHeightInPixels(final Context context) {
        int n = 0;
        switch (this.zzajx) {
            default: {
                n = zzm.zzjr().zzb(context, this.zzajx);
                break;
            }
            case -2: {
                n = AdSizeParcel.zzb(context.getResources().getDisplayMetrics());
                break;
            }
            case -4:
            case -3: {
                n = -1;
                break;
            }
        }
        return n;
    }
    
    public int getWidth() {
        return this.zzajw;
    }
    
    public int getWidthInPixels(final Context context) {
        int n = 0;
        switch (this.zzajw) {
            default: {
                n = zzm.zzjr().zzb(context, this.zzajw);
                break;
            }
            case -1: {
                n = AdSizeParcel.zza(context.getResources().getDisplayMetrics());
                break;
            }
            case -4:
            case -3: {
                n = -1;
                break;
            }
        }
        return n;
    }
    
    @Override
    public int hashCode() {
        return this.zzajy.hashCode();
    }
    
    public boolean isAutoHeight() {
        return this.zzajx == -2;
    }
    
    public boolean isFluid() {
        return this.zzajw == -3 && this.zzajx == -4;
    }
    
    public boolean isFullWidth() {
        return this.zzajw == -1;
    }
    
    @Override
    public String toString() {
        return this.zzajy;
    }
}
