// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import android.util.Log;
import com.google.android.gms.internal.zziy;

@zziy
public class zzb
{
    public static void e(final String s) {
        if (zzbf(6)) {
            Log.e("Ads", s);
        }
    }
    
    public static void zza(final String s, final Throwable t) {
        if (zzbf(3)) {
            Log.d("Ads", s, t);
        }
    }
    
    public static void zzb(final String s, final Throwable t) {
        if (zzbf(6)) {
            Log.e("Ads", s, t);
        }
    }
    
    public static boolean zzbf(final int n) {
        return n >= 5 || Log.isLoggable("Ads", n);
    }
    
    public static void zzc(final String s, final Throwable t) {
        if (zzbf(4)) {
            Log.i("Ads", s, t);
        }
    }
    
    public static void zzd(final String s, final Throwable t) {
        if (zzbf(5)) {
            Log.w("Ads", s, t);
        }
    }
    
    public static void zzdd(final String s) {
        if (zzbf(3)) {
            Log.d("Ads", s);
        }
    }
    
    public static void zzde(final String s) {
        if (zzbf(4)) {
            Log.i("Ads", s);
        }
    }
    
    public static void zzdf(final String s) {
        if (zzbf(5)) {
            Log.w("Ads", s);
        }
    }
}
