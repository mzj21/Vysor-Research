// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.ads.internal.util.client.zzb;

@zziy
public final class zzkn extends zzb
{
    public static void v(final String s) {
        if (zzue()) {
            Log.v("Ads", s);
        }
    }
    
    public static boolean zzud() {
        return zzdi.zzbdt.get();
    }
    
    private static boolean zzue() {
        return zzb.zzbf(2) && zzud();
    }
}
