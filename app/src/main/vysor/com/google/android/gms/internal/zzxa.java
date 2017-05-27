// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;

public final class zzxa implements Optional
{
    public static final zzxa aAa;
    private final boolean aAb;
    private final boolean aAc;
    private final Long aAd;
    private final Long aAe;
    private final boolean hh;
    private final boolean hj;
    private final String hk;
    private final String hl;
    
    static {
        aAa = new zza().zzcdf();
    }
    
    private zzxa(final boolean aAb, final boolean hh, final String hk, final boolean hj, final String hl, final boolean aAc, final Long aAd, final Long aAe) {
        this.aAb = aAb;
        this.hh = hh;
        this.hk = hk;
        this.hj = hj;
        this.aAc = aAc;
        this.hl = hl;
        this.aAd = aAd;
        this.aAe = aAe;
    }
    
    public boolean zzahk() {
        return this.hh;
    }
    
    public boolean zzahm() {
        return this.hj;
    }
    
    public String zzahn() {
        return this.hk;
    }
    
    @Nullable
    public String zzaho() {
        return this.hl;
    }
    
    public boolean zzcdb() {
        return this.aAb;
    }
    
    public boolean zzcdc() {
        return this.aAc;
    }
    
    @Nullable
    public Long zzcdd() {
        return this.aAd;
    }
    
    @Nullable
    public Long zzcde() {
        return this.aAe;
    }
    
    public static final class zza
    {
        public zzxa zzcdf() {
            return new zzxa(false, false, null, false, null, false, null, null, null);
        }
    }
}
