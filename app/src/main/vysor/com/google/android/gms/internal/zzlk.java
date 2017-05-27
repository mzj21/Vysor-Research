// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.ArrayList;
import java.util.List;

@zziy
class zzlk
{
    private final Object zzcug;
    private final List<Runnable> zzcuh;
    private final List<Runnable> zzcui;
    private boolean zzcuj;
    
    public zzlk() {
        this.zzcug = new Object();
        this.zzcuh = new ArrayList<Runnable>();
        this.zzcui = new ArrayList<Runnable>();
        this.zzcuj = false;
    }
    
    private void zze(final Runnable runnable) {
        zzkq.zza(runnable);
    }
    
    private void zzf(final Runnable runnable) {
        zza.zzctj.post(runnable);
    }
    
    public void zzc(final Runnable runnable) {
        synchronized (this.zzcug) {
            if (this.zzcuj) {
                this.zze(runnable);
            }
            else {
                this.zzcuh.add(runnable);
            }
        }
    }
    
    public void zzd(final Runnable runnable) {
        synchronized (this.zzcug) {
            if (this.zzcuj) {
                this.zzf(runnable);
            }
            else {
                this.zzcui.add(runnable);
            }
        }
    }
    
    public void zzvi() {
        synchronized (this.zzcug) {
            if (this.zzcuj) {
                return;
            }
            final Iterator<Runnable> iterator = this.zzcuh.iterator();
            while (iterator.hasNext()) {
                this.zze(iterator.next());
            }
        }
        final Iterator<Runnable> iterator2 = this.zzcui.iterator();
        while (iterator2.hasNext()) {
            this.zzf(iterator2.next());
        }
        this.zzcuh.clear();
        this.zzcui.clear();
        this.zzcuj = true;
    }
    // monitorexit(o)
}
