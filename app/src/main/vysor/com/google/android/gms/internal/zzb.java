// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

public interface zzb
{
    void initialize();
    
    zza zza(final String p0);
    
    void zza(final String p0, final zza p1);
    
    public static class zza
    {
        public byte[] data;
        public String zza;
        public long zzb;
        public long zzc;
        public long zzd;
        public long zze;
        public Map<String, String> zzf;
        
        public zza() {
            this.zzf = Collections.emptyMap();
        }
        
        public boolean zza() {
            return this.zzd < System.currentTimeMillis();
        }
        
        public boolean zzb() {
            return this.zze < System.currentTimeMillis();
        }
    }
}
