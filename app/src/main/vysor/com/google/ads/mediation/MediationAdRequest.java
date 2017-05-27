// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import java.util.Calendar;
import android.location.Location;
import java.util.Set;
import com.google.ads.AdRequest;
import java.util.Date;

@Deprecated
public class MediationAdRequest
{
    private final Date zzgn;
    private final AdRequest.Gender zzgo;
    private final Set<String> zzgp;
    private final boolean zzgq;
    private final Location zzgr;
    
    public MediationAdRequest(final Date zzgn, final AdRequest.Gender zzgo, final Set<String> zzgp, final boolean zzgq, final Location zzgr) {
        this.zzgn = zzgn;
        this.zzgo = zzgo;
        this.zzgp = zzgp;
        this.zzgq = zzgq;
        this.zzgr = zzgr;
    }
    
    public Integer getAgeInYears() {
        Integer n;
        if (this.zzgn != null) {
            final Calendar instance = Calendar.getInstance();
            final Calendar instance2 = Calendar.getInstance();
            instance.setTime(this.zzgn);
            n = instance2.get(1) - instance.get(1);
            if (instance2.get(2) < instance.get(2) || (instance2.get(2) == instance.get(2) && instance2.get(5) < instance.get(5))) {
                --n;
            }
        }
        else {
            n = null;
        }
        return n;
    }
    
    public Date getBirthday() {
        return this.zzgn;
    }
    
    public AdRequest.Gender getGender() {
        return this.zzgo;
    }
    
    public Set<String> getKeywords() {
        return this.zzgp;
    }
    
    public Location getLocation() {
        return this.zzgr;
    }
    
    public boolean isTesting() {
        return this.zzgq;
    }
}
