// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.flags.impl;

import com.google.android.gms.internal.zzvb;
import java.util.concurrent.Callable;
import android.content.Context;
import android.content.SharedPreferences;

public class zzb
{
    private static SharedPreferences UF;
    
    static {
        zzb.UF = null;
    }
    
    public static SharedPreferences zzn(final Context context) {
        synchronized (SharedPreferences.class) {
            if (zzb.UF == null) {
                zzb.UF = zzvb.zzb((Callable<SharedPreferences>)new Callable<SharedPreferences>() {
                    public SharedPreferences zzbhq() {
                        return context.getSharedPreferences("google_sdk_flags", 1);
                    }
                });
            }
            return zzb.UF;
        }
    }
}
