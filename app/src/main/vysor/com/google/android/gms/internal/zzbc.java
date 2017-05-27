// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;

public class zzbc extends zzaj<Integer, Long>
{
    public Long zzaig;
    public Long zzaih;
    public Long zzaii;
    public Long zzaij;
    public Long zzaik;
    public Long zzail;
    public Long zzaim;
    public Long zzez;
    public Long zzfb;
    public Long zzff;
    public Long zzfg;
    
    public zzbc() {
    }
    
    public zzbc(final String s) {
        this.zzk(s);
    }
    
    @Override
    protected HashMap<Integer, Long> zzar() {
        final HashMap<Integer, Long> hashMap = new HashMap<Integer, Long>();
        hashMap.put(0, this.zzaig);
        hashMap.put(1, this.zzaih);
        hashMap.put(2, this.zzaii);
        hashMap.put(3, this.zzfb);
        hashMap.put(4, this.zzez);
        hashMap.put(5, this.zzaij);
        hashMap.put(6, this.zzaik);
        hashMap.put(7, this.zzail);
        hashMap.put(8, this.zzfg);
        hashMap.put(9, this.zzff);
        hashMap.put(10, this.zzaim);
        return hashMap;
    }
    
    @Override
    protected void zzk(final String s) {
        final HashMap<K, Long> zzl = zzaj.zzl(s);
        if (zzl != null) {
            this.zzaig = zzl.get(0);
            this.zzaih = zzl.get(1);
            this.zzaii = zzl.get(2);
            this.zzfb = zzl.get(3);
            this.zzez = zzl.get(4);
            this.zzaij = zzl.get(5);
            this.zzaik = zzl.get(6);
            this.zzail = zzl.get(7);
            this.zzfg = zzl.get(8);
            this.zzff = zzl.get(9);
            this.zzaim = zzl.get(10);
        }
    }
}
