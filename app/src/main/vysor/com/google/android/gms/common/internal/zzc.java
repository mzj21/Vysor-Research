// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.util.Log;
import android.os.Looper;

public final class zzc
{
    public static void zza(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalStateException(String.valueOf(o));
        }
    }
    
    public static void zzbr(final boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }
    
    public static void zzhq(final String s) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            final String value = String.valueOf(Thread.currentThread());
            final String value2 = String.valueOf(Looper.getMainLooper().getThread());
            Log.e("Asserts", new StringBuilder(57 + String.valueOf(value).length() + String.valueOf(value2).length()).append("checkMainThread: current thread ").append(value).append(" IS NOT the main thread ").append(value2).append("!").toString());
            throw new IllegalStateException(s);
        }
    }
    
    public static void zzhr(final String s) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            final String value = String.valueOf(Thread.currentThread());
            final String value2 = String.valueOf(Looper.getMainLooper().getThread());
            Log.e("Asserts", new StringBuilder(56 + String.valueOf(value).length() + String.valueOf(value2).length()).append("checkNotMainThread: current thread ").append(value).append(" IS the main thread ").append(value2).append("!").toString());
            throw new IllegalStateException(s);
        }
    }
    
    public static void zzu(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("null reference");
        }
    }
}
