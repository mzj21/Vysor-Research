// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.flags.impl;

import com.google.android.gms.internal.zzvb;
import java.util.concurrent.Callable;
import android.content.SharedPreferences;

public abstract class zza<T>
{
    public static class zza extends com.google.android.gms.flags.impl.zza<Boolean>
    {
        public static Boolean zza(final SharedPreferences sharedPreferences, final String s, final Boolean b) {
            return zzvb.zzb((Callable<Boolean>)new Callable<Boolean>() {
                public Boolean zzuq() {
                    return sharedPreferences.getBoolean(s, (boolean)b);
                }
            });
        }
    }
    
    public static class zzb extends zza<Integer>
    {
        public static Integer zza(final SharedPreferences sharedPreferences, final String s, final Integer n) {
            return zzvb.zzb((Callable<Integer>)new Callable<Integer>() {
                public Integer zzbho() {
                    return sharedPreferences.getInt(s, (int)n);
                }
            });
        }
    }
    
    public static class zzc extends zza<Long>
    {
        public static Long zza(final SharedPreferences sharedPreferences, final String s, final Long n) {
            return zzvb.zzb((Callable<Long>)new Callable<Long>() {
                public Long zzbhp() {
                    return sharedPreferences.getLong(s, (long)n);
                }
            });
        }
    }
    
    public static class zzd extends zza<String>
    {
        public static String zza(final SharedPreferences sharedPreferences, final String s, final String s2) {
            return zzvb.zzb((Callable<String>)new Callable<String>() {
                public String zzacr() {
                    return sharedPreferences.getString(s, s2);
                }
            });
        }
    }
}
