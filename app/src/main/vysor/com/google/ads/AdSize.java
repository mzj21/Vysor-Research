// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads;

import android.content.Context;

@Deprecated
public final class AdSize
{
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER;
    public static final int FULL_WIDTH = -1;
    public static final AdSize IAB_BANNER;
    public static final AdSize IAB_LEADERBOARD;
    public static final AdSize IAB_MRECT;
    public static final AdSize IAB_WIDE_SKYSCRAPER;
    public static final int LANDSCAPE_AD_HEIGHT = 32;
    public static final int LARGE_AD_HEIGHT = 90;
    public static final int PORTRAIT_AD_HEIGHT = 50;
    public static final AdSize SMART_BANNER;
    private final com.google.android.gms.ads.AdSize zzcj;
    
    static {
        SMART_BANNER = new AdSize(-1, -2, "mb");
        BANNER = new AdSize(320, 50, "mb");
        IAB_MRECT = new AdSize(300, 250, "as");
        IAB_BANNER = new AdSize(468, 60, "as");
        IAB_LEADERBOARD = new AdSize(728, 90, "as");
        IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
    }
    
    public AdSize(final int n, final int n2) {
        this(new com.google.android.gms.ads.AdSize(n, n2));
    }
    
    private AdSize(final int n, final int n2, final String s) {
        this(new com.google.android.gms.ads.AdSize(n, n2));
    }
    
    public AdSize(final com.google.android.gms.ads.AdSize zzcj) {
        this.zzcj = zzcj;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof AdSize && this.zzcj.equals(((AdSize)o).zzcj);
    }
    
    public AdSize findBestSize(final AdSize... array) {
        AdSize adSize = null;
        if (array != null) {
            float n = 0.0f;
            final int width = this.getWidth();
            final int height = this.getHeight();
            final int length = array.length;
            int i = 0;
        Label_0106_Outer:
            while (i < length) {
                final AdSize adSize2 = array[i];
                final int width2 = adSize2.getWidth();
                final int height2 = adSize2.getHeight();
                while (true) {
                    Label_0118: {
                        if (!this.isSizeAppropriate(width2, height2)) {
                            break Label_0118;
                        }
                        float n2 = width2 * height2 / (width * height);
                        if (n2 > 1.0f) {
                            n2 = 1.0f / n2;
                        }
                        if (n2 <= n) {
                            break Label_0118;
                        }
                        final AdSize adSize3 = adSize2;
                        ++i;
                        adSize = adSize3;
                        n = n2;
                        continue Label_0106_Outer;
                    }
                    float n2 = n;
                    final AdSize adSize3 = adSize;
                    continue;
                }
            }
        }
        return adSize;
    }
    
    public int getHeight() {
        return this.zzcj.getHeight();
    }
    
    public int getHeightInPixels(final Context context) {
        return this.zzcj.getHeightInPixels(context);
    }
    
    public int getWidth() {
        return this.zzcj.getWidth();
    }
    
    public int getWidthInPixels(final Context context) {
        return this.zzcj.getWidthInPixels(context);
    }
    
    @Override
    public int hashCode() {
        return this.zzcj.hashCode();
    }
    
    public boolean isAutoHeight() {
        return this.zzcj.isAutoHeight();
    }
    
    public boolean isCustomAdSize() {
        return false;
    }
    
    public boolean isFullWidth() {
        return this.zzcj.isFullWidth();
    }
    
    public boolean isSizeAppropriate(final int n, final int n2) {
        final int width = this.getWidth();
        final int height = this.getHeight();
        return n <= 1.25f * width && n >= 0.8f * width && n2 <= 1.25f * height && n2 >= 0.8f * height;
    }
    
    @Override
    public String toString() {
        return this.zzcj.toString();
    }
}
