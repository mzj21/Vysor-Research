// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import java.util.Set;

public final class zzaok extends zzaoh
{
    private final zzapf<String, zzaoh> ble;
    
    public zzaok() {
        this.ble = new zzapf<String, zzaoh>();
    }
    
    private zzaoh zzcm(final Object o) {
        zzaoh bld;
        if (o == null) {
            bld = zzaoj.bld;
        }
        else {
            bld = new zzaon(o);
        }
        return bld;
    }
    
    public Set<Map.Entry<String, zzaoh>> entrySet() {
        return this.ble.entrySet();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof zzaok && ((zzaok)o).ble.equals(this.ble));
    }
    
    public boolean has(final String s) {
        return this.ble.containsKey(s);
    }
    
    @Override
    public int hashCode() {
        return this.ble.hashCode();
    }
    
    public void zza(final String s, zzaoh bld) {
        if (bld == null) {
            bld = zzaoj.bld;
        }
        this.ble.put(s, bld);
    }
    
    public void zzb(final String s, final Boolean b) {
        this.zza(s, this.zzcm(b));
    }
    
    public void zzcb(final String s, final String s2) {
        this.zza(s, this.zzcm(s2));
    }
    
    public zzaoh zzuo(final String s) {
        return this.ble.get(s);
    }
    
    public zzaoe zzup(final String s) {
        return this.ble.get(s);
    }
}
