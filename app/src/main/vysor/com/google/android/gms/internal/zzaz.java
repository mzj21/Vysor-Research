// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;

public class zzaz extends zzaj<Integer, Object>
{
    public Long zzahi;
    public Boolean zzahj;
    public Boolean zzahk;
    
    public zzaz() {
    }
    
    public zzaz(final String s) {
        this.zzk(s);
    }
    
    @Override
    protected HashMap<Integer, Object> zzar() {
        final HashMap<Integer, Long> hashMap = (HashMap<Integer, Long>)new HashMap<Integer, Boolean>();
        hashMap.put(0, (Boolean)(Object)this.zzahi);
        hashMap.put(1, this.zzahj);
        hashMap.put(2, this.zzahk);
        return (HashMap<Integer, Object>)hashMap;
    }
    
    @Override
    protected void zzk(final String s) {
        final HashMap<K, Long> zzl = zzaj.zzl(s);
        if (zzl != null) {
            this.zzahi = zzl.get(0);
            this.zzahj = (Boolean)(Object)zzl.get(1);
            this.zzahk = (Boolean)(Object)zzl.get(2);
        }
    }
}
