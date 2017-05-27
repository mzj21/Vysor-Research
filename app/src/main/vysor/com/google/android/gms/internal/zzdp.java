// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;
import android.support.annotation.Nullable;

@zziy
public class zzdp
{
    @Nullable
    private final zzdq zzalg;
    private final Map<String, zzdo> zzbio;
    
    public zzdp(@Nullable final zzdq zzalg) {
        this.zzalg = zzalg;
        this.zzbio = new HashMap<String, zzdo>();
    }
    
    public void zza(final String s, final zzdo zzdo) {
        this.zzbio.put(s, zzdo);
    }
    
    public void zza(final String s, final String s2, final long n) {
        zzdm.zza(this.zzalg, this.zzbio.get(s2), n, s);
        this.zzbio.put(s, zzdm.zza(this.zzalg, n));
    }
    
    @Nullable
    public zzdq zzkz() {
        return this.zzalg;
    }
}
