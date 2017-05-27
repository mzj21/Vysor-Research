// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import org.json.JSONObject;
import java.util.AbstractMap;
import java.util.HashSet;

@zziy
public class zzgb implements zzga
{
    private final zzfz zzbrj;
    private final HashSet<AbstractMap.SimpleEntry<String, zzev>> zzbrk;
    
    public zzgb(final zzfz zzbrj) {
        this.zzbrj = zzbrj;
        this.zzbrk = new HashSet<AbstractMap.SimpleEntry<String, zzev>>();
    }
    
    @Override
    public void zza(final String s, final zzev zzev) {
        this.zzbrj.zza(s, zzev);
        this.zzbrk.add((AbstractMap.SimpleEntry<String, zzev>)new AbstractMap.SimpleEntry(s, zzev));
    }
    
    @Override
    public void zza(final String s, final JSONObject jsonObject) {
        this.zzbrj.zza(s, jsonObject);
    }
    
    @Override
    public void zzb(final String s, final zzev zzev) {
        this.zzbrj.zzb(s, zzev);
        this.zzbrk.remove(new AbstractMap.SimpleEntry(s, zzev));
    }
    
    @Override
    public void zzb(final String s, final JSONObject jsonObject) {
        this.zzbrj.zzb(s, jsonObject);
    }
    
    @Override
    public void zzj(final String s, final String s2) {
        this.zzbrj.zzj(s, s2);
    }
    
    @Override
    public void zznd() {
        for (final AbstractMap.SimpleEntry<String, zzev> simpleEntry : this.zzbrk) {
            final String value = String.valueOf(simpleEntry.getValue().toString());
            String concat;
            if (value.length() != 0) {
                concat = "Unregistering eventhandler: ".concat(value);
            }
            else {
                concat = new String("Unregistering eventhandler: ");
            }
            zzkn.v(concat);
            this.zzbrj.zzb(simpleEntry.getKey(), simpleEntry.getValue());
        }
        this.zzbrk.clear();
    }
}
