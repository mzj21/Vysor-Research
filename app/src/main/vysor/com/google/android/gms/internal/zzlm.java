// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public interface zzlm<T>
{
    void zza(final zzc<T> p0, final zza p1);
    
    void zzg(final T p0);
    
    public interface zza
    {
        void run();
    }
    
    public static class zzb implements zza
    {
        @Override
        public void run() {
        }
    }
    
    public interface zzc<T>
    {
        void zzd(final T p0);
    }
}
