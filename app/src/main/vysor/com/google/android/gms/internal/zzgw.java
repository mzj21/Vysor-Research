// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.location.Location;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdRequest;

@zziy
public final class zzgw implements MediationAdRequest
{
    private final int zzawu;
    private final boolean zzaxg;
    private final int zzbty;
    private final Date zzgn;
    private final Set<String> zzgp;
    private final boolean zzgq;
    private final Location zzgr;
    
    public zzgw(@Nullable final Date zzgn, final int zzawu, @Nullable final Set<String> zzgp, @Nullable final Location zzgr, final boolean zzgq, final int zzbty, final boolean zzaxg) {
        this.zzgn = zzgn;
        this.zzawu = zzawu;
        this.zzgp = zzgp;
        this.zzgr = zzgr;
        this.zzgq = zzgq;
        this.zzbty = zzbty;
        this.zzaxg = zzaxg;
    }
    
    @Override
    public Date getBirthday() {
        return this.zzgn;
    }
    
    @Override
    public int getGender() {
        return this.zzawu;
    }
    
    @Override
    public Set<String> getKeywords() {
        return this.zzgp;
    }
    
    @Override
    public Location getLocation() {
        return this.zzgr;
    }
    
    @Override
    public boolean isDesignedForFamilies() {
        return this.zzaxg;
    }
    
    @Override
    public boolean isTesting() {
        return this.zzgq;
    }
    
    @Override
    public int taggedForChildDirectedTreatment() {
        return this.zzbty;
    }
}
