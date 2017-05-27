// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

public class zzab extends zzk<String>
{
    private final zzm.zzb<String> zzcg;
    
    public zzab(final int n, final String s, final zzm.zzb<String> zzcg, final zzm.zza zza) {
        super(n, s, zza);
        this.zzcg = zzcg;
    }
    
    @Override
    protected zzm<String> zza(final zzi zzi) {
        try {
            final String s = new String(zzi.data, zzx.zza(zzi.zzz));
            return zzm.zza(s, zzx.zzb(zzi));
        }
        catch (UnsupportedEncodingException ex) {
            final String s = new String(zzi.data);
            return zzm.zza(s, zzx.zzb(zzi));
        }
    }
    
    protected void zzi(final String s) {
        this.zzcg.zzb(s);
    }
}
