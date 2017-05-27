// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzu;

@zziy
public abstract class zzde<T>
{
    private final int zzbae;
    private final String zzbaf;
    private final T zzbag;
    
    private zzde(final int zzbae, final String zzbaf, final T zzbag) {
        this.zzbae = zzbae;
        this.zzbaf = zzbaf;
        this.zzbag = zzbag;
        zzu.zzgk().zza(this);
    }
    
    public static zzde<String> zza(final int n, final String s) {
        final zzde<String> zza = zza(n, s, (String)null);
        zzu.zzgk().zzb(zza);
        return zza;
    }
    
    public static zzde<Integer> zza(final int n, final String s, final int n2) {
        return new zzde<Integer>(n, s, Integer.valueOf(n2)) {
            public Integer zzc(final SharedPreferences sharedPreferences) {
                return sharedPreferences.getInt(this.getKey(), (int)this.zzkq());
            }
        };
    }
    
    public static zzde<Long> zza(final int n, final String s, final long n2) {
        return new zzde<Long>(n, s, Long.valueOf(n2)) {
            public Long zzd(final SharedPreferences sharedPreferences) {
                return sharedPreferences.getLong(this.getKey(), (long)this.zzkq());
            }
        };
    }
    
    public static zzde<Boolean> zza(final int n, final String s, final Boolean b) {
        return new zzde<Boolean>(n, s, b) {
            public Boolean zzb(final SharedPreferences sharedPreferences) {
                return sharedPreferences.getBoolean(this.getKey(), (boolean)this.zzkq());
            }
        };
    }
    
    public static zzde<String> zza(final int n, final String s, final String s2) {
        return new zzde<String>(n, s, s2) {
            public String zze(final SharedPreferences sharedPreferences) {
                return sharedPreferences.getString(this.getKey(), (String)this.zzkq());
            }
        };
    }
    
    public static zzde<String> zzb(final int n, final String s) {
        final zzde<String> zza = zza(n, s, (String)null);
        zzu.zzgk().zzc(zza);
        return zza;
    }
    
    public T get() {
        return zzu.zzgl().zzd(this);
    }
    
    public String getKey() {
        return this.zzbaf;
    }
    
    protected abstract T zza(final SharedPreferences p0);
    
    public T zzkq() {
        return this.zzbag;
    }
}
