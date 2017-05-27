// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.content.res.TypedArray;
import android.text.TextUtils;
import com.google.android.gms.R;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.zziy;

@zziy
public final class zzk
{
    private final String zzang;
    private final AdSize[] zzaxp;
    
    public zzk(final Context context, final AttributeSet set) {
        int n = 1;
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(set, R.styleable.AdsAttrs);
        final String string = obtainAttributes.getString(R.styleable.AdsAttrs_adSize);
        final String string2 = obtainAttributes.getString(R.styleable.AdsAttrs_adSizes);
        int n2;
        if (!TextUtils.isEmpty((CharSequence)string)) {
            n2 = n;
        }
        else {
            n2 = 0;
        }
        if (TextUtils.isEmpty((CharSequence)string2)) {
            n = 0;
        }
        if (n2 != 0 && n == 0) {
            this.zzaxp = zzah(string);
        }
        else if (n2 == 0 && n != 0) {
            this.zzaxp = zzah(string2);
        }
        else {
            if (n2 != 0) {
                throw new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
            }
            throw new IllegalArgumentException("Required XML attribute \"adSize\" was missing.");
        }
        this.zzang = obtainAttributes.getString(R.styleable.AdsAttrs_adUnitId);
        if (TextUtils.isEmpty((CharSequence)this.zzang)) {
            throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
        }
    }
    
    private static AdSize[] zzah(final String s) {
        final String[] split = s.split("\\s*,\\s*");
        final AdSize[] array = new AdSize[split.length];
        int i = 0;
    Label_0119_Outer:
        while (i < split.length) {
            final String trim = split[i].trim();
            while (true) {
                Label_0203: {
                    if (!trim.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$")) {
                        break Label_0203;
                    }
                    final String[] split2 = trim.split("[xX]");
                    split2[0] = split2[0].trim();
                    split2[1] = split2[1].trim();
                    try {
                        int int1;
                        if ("FULL_WIDTH".equals(split2[0])) {
                            int1 = -1;
                        }
                        else {
                            int1 = Integer.parseInt(split2[0]);
                        }
                        int int2;
                        if ("AUTO_HEIGHT".equals(split2[1])) {
                            int2 = -2;
                        }
                        else {
                            int2 = Integer.parseInt(split2[1]);
                        }
                        array[i] = new AdSize(int1, int2);
                        ++i;
                        continue Label_0119_Outer;
                    }
                    catch (NumberFormatException ex) {
                        final String value = String.valueOf(trim);
                        String concat;
                        if (value.length() != 0) {
                            concat = "Could not parse XML attribute \"adSize\": ".concat(value);
                        }
                        else {
                            concat = new String("Could not parse XML attribute \"adSize\": ");
                        }
                        throw new IllegalArgumentException(concat);
                    }
                }
                if ("BANNER".equals(trim)) {
                    array[i] = AdSize.BANNER;
                    continue;
                }
                if ("LARGE_BANNER".equals(trim)) {
                    array[i] = AdSize.LARGE_BANNER;
                    continue;
                }
                if ("FULL_BANNER".equals(trim)) {
                    array[i] = AdSize.FULL_BANNER;
                    continue;
                }
                if ("LEADERBOARD".equals(trim)) {
                    array[i] = AdSize.LEADERBOARD;
                    continue;
                }
                if ("MEDIUM_RECTANGLE".equals(trim)) {
                    array[i] = AdSize.MEDIUM_RECTANGLE;
                    continue;
                }
                if ("SMART_BANNER".equals(trim)) {
                    array[i] = AdSize.SMART_BANNER;
                    continue;
                }
                if ("WIDE_SKYSCRAPER".equals(trim)) {
                    array[i] = AdSize.WIDE_SKYSCRAPER;
                    continue;
                }
                if ("FLUID".equals(trim)) {
                    array[i] = AdSize.FLUID;
                    continue;
                }
                break;
            }
            final String value2 = String.valueOf(trim);
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Could not parse XML attribute \"adSize\": ".concat(value2);
            }
            else {
                concat2 = new String("Could not parse XML attribute \"adSize\": ");
            }
            throw new IllegalArgumentException(concat2);
        }
        if (array.length == 0) {
            final String value3 = String.valueOf(s);
            String concat3;
            if (value3.length() != 0) {
                concat3 = "Could not parse XML attribute \"adSize\": ".concat(value3);
            }
            else {
                concat3 = new String("Could not parse XML attribute \"adSize\": ");
            }
            throw new IllegalArgumentException(concat3);
        }
        return array;
    }
    
    public String getAdUnitId() {
        return this.zzang;
    }
    
    public AdSize[] zzm(final boolean b) {
        if (!b && this.zzaxp.length != 1) {
            throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
        }
        return this.zzaxp;
    }
}
