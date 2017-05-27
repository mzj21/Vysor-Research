// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Looper;
import android.text.TextUtils;

public final class zzac
{
    public static int zza(final int n, final Object o) {
        if (n == 0) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
        return n;
    }
    
    public static long zza(final long n, final Object o) {
        if (n == 0L) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
        return n;
    }
    
    public static void zza(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalStateException(String.valueOf(o));
        }
    }
    
    public static void zza(final boolean b, final String s, final Object... array) {
        if (!b) {
            throw new IllegalStateException(String.format(s, array));
        }
    }
    
    public static void zzavb() {
        zzhr("Must not be called on the main application thread");
    }
    
    public static <T> T zzb(final T t, final Object o) {
        if (t == null) {
            throw new NullPointerException(String.valueOf(o));
        }
        return t;
    }
    
    public static void zzb(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
    }
    
    public static void zzb(final boolean b, final String s, final Object... array) {
        if (!b) {
            throw new IllegalArgumentException(String.format(s, array));
        }
    }
    
    public static void zzbr(final boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }
    
    public static void zzbs(final boolean b) {
        if (!b) {
            throw new IllegalArgumentException();
        }
    }
    
    public static int zzgq(final int n) {
        if (n == 0) {
            throw new IllegalArgumentException("Given Integer is zero");
        }
        return n;
    }
    
    public static String zzh(final String s, final Object o) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
        return s;
    }
    
    public static void zzhq(final String s) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException(s);
        }
    }
    
    public static void zzhr(final String s) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(s);
        }
    }
    
    public static String zzhz(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        return s;
    }
    
    public static <T> T zzy(final T t) {
        if (t == null) {
            throw new NullPointerException("null reference");
        }
        return t;
    }
}
