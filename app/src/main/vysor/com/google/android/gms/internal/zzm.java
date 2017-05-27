// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzm<T>
{
    public final T result;
    public final com.google.android.gms.internal.zzb.zza zzbf;
    public final zzr zzbg;
    public boolean zzbh;
    
    private zzm(final zzr zzbg) {
        this.zzbh = false;
        this.result = null;
        this.zzbf = null;
        this.zzbg = zzbg;
    }
    
    private zzm(final T result, final com.google.android.gms.internal.zzb.zza zzbf) {
        this.zzbh = false;
        this.result = result;
        this.zzbf = zzbf;
        this.zzbg = null;
    }
    
    public static <T> zzm<T> zza(final T t, final com.google.android.gms.internal.zzb.zza zza) {
        return new zzm<T>(t, zza);
    }
    
    public static <T> zzm<T> zzd(final zzr zzr) {
        return new zzm<T>(zzr);
    }
    
    public boolean isSuccess() {
        return this.zzbg == null;
    }
    
    public interface zza
    {
        void zze(final zzr p0);
    }
    
    public interface zzb<T>
    {
        void zzb(final T p0);
    }
}
