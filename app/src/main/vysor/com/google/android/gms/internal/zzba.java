// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;

public class zzba extends zzaj<Integer, Long>
{
    public Long zzahl;
    public Long zzahm;
    
    public zzba() {
    }
    
    public zzba(final String s) {
        this.zzk(s);
    }
    
    @Override
    protected HashMap<Integer, Long> zzar() {
        final HashMap<Integer, Long> hashMap = new HashMap<Integer, Long>();
        hashMap.put(0, this.zzahl);
        hashMap.put(1, this.zzahm);
        return hashMap;
    }
    
    @Override
    protected void zzk(final String s) {
        final HashMap<K, Long> zzl = zzaj.zzl(s);
        if (zzl != null) {
            this.zzahl = zzl.get(0);
            this.zzahm = zzl.get(1);
        }
    }
}
