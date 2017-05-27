// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.api.Api;

public final class zzpz<O extends Api.ApiOptions>
{
    private final Api<O> tv;
    private final O vw;
    private final boolean wo;
    private final int wp;
    
    private zzpz(final Api<O> tv, final O vw) {
        this.wo = false;
        this.tv = tv;
        this.vw = vw;
        this.wp = zzab.hashCode(this.tv, this.vw);
    }
    
    public static <O extends Api.ApiOptions> zzpz<O> zza(final Api<O> api, final O o) {
        return new zzpz<O>(api, o);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (o != this) {
            if (!(o instanceof zzpz)) {
                b = false;
            }
            else {
                final zzpz zzpz = (zzpz)o;
                if (!zzab.equal(this.tv, zzpz.tv) || !zzab.equal(this.vw, zzpz.vw)) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        return this.wp;
    }
    
    public String zzaqj() {
        return this.tv.getName();
    }
}
