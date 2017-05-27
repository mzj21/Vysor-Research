// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zziy
public class zzcs
{
    private final Object zzakd;
    private int zzaug;
    private List<zzcr> zzauh;
    
    public zzcs() {
        this.zzakd = new Object();
        this.zzauh = new LinkedList<zzcr>();
    }
    
    public boolean zza(final zzcr zzcr) {
        boolean b;
        synchronized (this.zzakd) {
            if (this.zzauh.contains(zzcr)) {
                b = true;
            }
            else {
                // monitorexit(this.zzakd)
                b = false;
            }
        }
        return b;
    }
    
    public boolean zzb(final zzcr zzcr) {
        boolean b;
        synchronized (this.zzakd) {
            final Iterator<zzcr> iterator = this.zzauh.iterator();
            while (iterator.hasNext()) {
                final zzcr zzcr2 = iterator.next();
                if (zzcr != zzcr2 && zzcr2.zzie().equals(zzcr.zzie())) {
                    iterator.remove();
                    b = true;
                    return b;
                }
            }
            // monitorexit(this.zzakd)
            b = false;
        }
        return b;
    }
    
    public void zzc(final zzcr zzcr) {
        synchronized (this.zzakd) {
            if (this.zzauh.size() >= 10) {
                zzb.zzdd(new StringBuilder(41).append("Queue is full, current size = ").append(this.zzauh.size()).toString());
                this.zzauh.remove(0);
            }
            zzcr.zzl(this.zzaug++);
            this.zzauh.add(zzcr);
        }
    }
    
    @Nullable
    public zzcr zzil() {
        zzcr zzcr;
        while (true) {
            zzcr = null;
        Label_0129:
            while (true) {
                zzcr zzcr3 = null;
                int n2 = 0;
                Label_0169: {
                    final int n;
                    Label_0157: {
                        synchronized (this.zzakd) {
                            if (this.zzauh.size() == 0) {
                                zzb.zzdd("Queue empty");
                                // monitorexit(this.zzakd)
                                zzcr = null;
                                break;
                            }
                            if (this.zzauh.size() >= 2) {
                                n = Integer.MIN_VALUE;
                                final Iterator<zzcr> iterator = this.zzauh.iterator();
                                if (!iterator.hasNext()) {
                                    this.zzauh.remove(zzcr);
                                    break;
                                }
                                final zzcr zzcr2 = iterator.next();
                                final int score = zzcr2.getScore();
                                if (score > n) {
                                    zzcr3 = zzcr2;
                                    n2 = score;
                                    break Label_0169;
                                }
                                break Label_0157;
                            }
                        }
                        break Label_0129;
                    }
                    n2 = n;
                    zzcr3 = zzcr;
                }
                int n = n2;
                zzcr = zzcr3;
                continue;
            }
            final zzcr zzcr4 = this.zzauh.get(0);
            zzcr4.zzig();
            // monitorexit(o)
            zzcr = zzcr4;
            break;
        }
        return zzcr;
    }
}
