// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;

@zziy
public class zzdo
{
    private final long zzbil;
    @Nullable
    private final String zzbim;
    @Nullable
    private final zzdo zzbin;
    
    public zzdo(final long zzbil, @Nullable final String zzbim, @Nullable final zzdo zzbin) {
        this.zzbil = zzbil;
        this.zzbim = zzbim;
        this.zzbin = zzbin;
    }
    
    long getTime() {
        return this.zzbil;
    }
    
    String zzkx() {
        return this.zzbim;
    }
    
    @Nullable
    zzdo zzky() {
        return this.zzbin;
    }
}
