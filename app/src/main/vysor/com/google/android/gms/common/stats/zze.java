// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.util.Log;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;

public class zze
{
    private final long Ev;
    private final int Ew;
    private final SimpleArrayMap<String, Long> Ex;
    
    public zze() {
        this.Ev = 60000L;
        this.Ew = 10;
        this.Ex = new SimpleArrayMap<String, Long>(10);
    }
    
    public zze(final int ew, final long ev) {
        this.Ev = ev;
        this.Ew = ew;
        this.Ex = new SimpleArrayMap<String, Long>();
    }
    
    private void zze(final long n, final long n2) {
        for (int i = -1 + this.Ex.size(); i >= 0; --i) {
            if (n2 - this.Ex.valueAt(i) > n) {
                this.Ex.removeAt(i);
            }
        }
    }
    
    public Long zzif(final String s) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        long ev = this.Ev;
        synchronized (this) {
            while (this.Ex.size() >= this.Ew) {
                this.zze(ev, elapsedRealtime);
                ev /= 2L;
                Log.w("ConnectionTracker", new StringBuilder(94).append("The max capacity ").append(this.Ew).append(" is not enough. Current durationThreshold is: ").append(ev).toString());
            }
        }
        // monitorexit(this)
        return this.Ex.put(s, elapsedRealtime);
    }
    
    public boolean zzig(final String s) {
        while (true) {
            synchronized (this) {
                if (this.Ex.remove(s) != null) {
                    return true;
                }
            }
            return false;
        }
    }
}
