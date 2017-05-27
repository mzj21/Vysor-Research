// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import java.lang.reflect.Field;

public enum zzanz implements zzaoa
{
    bkD {
        @Override
        public String zzc(final Field field) {
            return field.getName();
        }
    }, 
    bkE {
        @Override
        public String zzc(final Field field) {
            return zzum(field.getName());
        }
    }, 
    bkF {
        @Override
        public String zzc(final Field field) {
            return zzum(zzbz(field.getName(), " "));
        }
    }, 
    bkG {
        @Override
        public String zzc(final Field field) {
            return zzbz(field.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    }, 
    bkH {
        @Override
        public String zzc(final Field field) {
            return zzbz(field.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    };
    
    private static String zza(final char c, final String s, final int n) {
        String s2;
        if (n < s.length()) {
            final String value = String.valueOf(s.substring(n));
            s2 = new StringBuilder(1 + String.valueOf(value).length()).append(c).append(value).toString();
        }
        else {
            s2 = String.valueOf(c);
        }
        return s2;
    }
    
    private static String zzbz(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (Character.isUpperCase(char1) && sb.length() != 0) {
                sb.append(s2);
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    private static String zzum(String s) {
        int n = 0;
        final StringBuilder sb = new StringBuilder();
        char c;
        for (c = s.charAt(0); n < -1 + s.length() && !Character.isLetter(c); ++n, c = s.charAt(n)) {
            sb.append(c);
        }
        if (n == s.length()) {
            s = sb.toString();
        }
        else if (!Character.isUpperCase(c)) {
            s = sb.append(zza(Character.toUpperCase(c), s, n + 1)).toString();
        }
        return s;
    }
}
