// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import android.text.TextUtils;
import android.support.annotation.Nullable;

@zziy
public abstract class zzdn
{
    @zziy
    public static final zzdn zzbii;
    @zziy
    public static final zzdn zzbij;
    @zziy
    public static final zzdn zzbik;
    
    static {
        zzbii = new zzdn() {
            @Override
            public String zzg(@Nullable final String s, final String s2) {
                return s2;
            }
        };
        zzbij = new zzdn() {
            @Override
            public String zzg(@Nullable String s, final String s2) {
                if (s == null) {
                    s = s2;
                }
                return s;
            }
        };
        zzbik = new zzdn() {
            @Nullable
            private String zzau(@Nullable String substring) {
                if (!TextUtils.isEmpty((CharSequence)substring)) {
                    int n = 0;
                    int length = substring.length();
                    while (n < substring.length() && substring.charAt(n) == ',') {
                        ++n;
                    }
                    while (length > 0 && substring.charAt(length - 1) == ',') {
                        --length;
                    }
                    if (n != 0 || length != substring.length()) {
                        substring = substring.substring(n, length);
                    }
                }
                return substring;
            }
            
            @Override
            public String zzg(@Nullable final String s, final String s2) {
                final String zzau = this.zzau(s);
                String s3 = this.zzau(s2);
                if (!TextUtils.isEmpty((CharSequence)zzau)) {
                    if (TextUtils.isEmpty((CharSequence)s3)) {
                        s3 = zzau;
                    }
                    else {
                        s3 = new StringBuilder(1 + String.valueOf(zzau).length() + String.valueOf(s3).length()).append(zzau).append(",").append(s3).toString();
                    }
                }
                return s3;
            }
        };
    }
    
    public final void zza(final Map<String, String> map, final String s, final String s2) {
        map.put(s, this.zzg(map.get(s), s2));
    }
    
    public abstract String zzg(@Nullable final String p0, final String p1);
}
