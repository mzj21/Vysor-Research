// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;

@zziy
public class zzlc
{
    private Object zzakd;
    private long zzcta;
    private long zzctb;
    
    public zzlc(final long zzcta) {
        this.zzctb = Long.MIN_VALUE;
        this.zzakd = new Object();
        this.zzcta = zzcta;
    }
    
    public boolean tryAcquire() {
        boolean b;
        synchronized (this.zzakd) {
            final long elapsedRealtime = zzu.zzgf().elapsedRealtime();
            if (this.zzctb + this.zzcta > elapsedRealtime) {
                // monitorexit(this.zzakd)
                b = false;
            }
            else {
                this.zzctb = elapsedRealtime;
                b = true;
            }
        }
        return b;
    }
}
