// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@zziy
public class zzdf
{
    private final Collection<zzde> zzbah;
    private final Collection<zzde<String>> zzbai;
    private final Collection<zzde<String>> zzbaj;
    
    public zzdf() {
        this.zzbah = new ArrayList<zzde>();
        this.zzbai = new ArrayList<zzde<String>>();
        this.zzbaj = new ArrayList<zzde<String>>();
    }
    
    public void zza(final zzde zzde) {
        this.zzbah.add(zzde);
    }
    
    public void zzb(final zzde<String> zzde) {
        this.zzbai.add(zzde);
    }
    
    public void zzc(final zzde<String> zzde) {
        this.zzbaj.add(zzde);
    }
    
    public List<String> zzkr() {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<zzde<String>> iterator = this.zzbai.iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next().get();
            if (s != null) {
                list.add(s);
            }
        }
        return list;
    }
    
    public List<String> zzks() {
        final List<String> zzkr = this.zzkr();
        final Iterator<zzde<String>> iterator = this.zzbaj.iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next().get();
            if (s != null) {
                zzkr.add(s);
            }
        }
        return zzkr;
    }
}
