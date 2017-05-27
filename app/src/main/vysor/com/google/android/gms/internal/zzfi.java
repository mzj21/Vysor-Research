// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import com.google.android.gms.ads.internal.zzu;
import java.util.LinkedList;
import java.util.List;

@zziy
public class zzfi implements Iterable<zzfh>
{
    private final List<zzfh> zzbnv;
    
    public zzfi() {
        this.zzbnv = new LinkedList<zzfh>();
    }
    
    private zzfh zzg(final zzlt zzlt) {
        for (final zzfh zzfh : zzu.zzgw()) {
            if (zzfh.zzbkr == zzlt) {
                return zzfh;
            }
        }
        return null;
    }
    
    @Override
    public Iterator<zzfh> iterator() {
        return this.zzbnv.iterator();
    }
    
    public void zza(final zzfh zzfh) {
        this.zzbnv.add(zzfh);
    }
    
    public void zzb(final zzfh zzfh) {
        this.zzbnv.remove(zzfh);
    }
    
    public boolean zze(final zzlt zzlt) {
        final zzfh zzg = this.zzg(zzlt);
        boolean b;
        if (zzg != null) {
            zzg.zzbns.abort();
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public boolean zzf(final zzlt zzlt) {
        return this.zzg(zzlt) != null;
    }
    
    public int zzmi() {
        return this.zzbnv.size();
    }
}
