// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import android.support.annotation.NonNull;
import java.util.Queue;

class zzg<TResult>
{
    private Queue<zzf<TResult>> aJF;
    private boolean aJG;
    private final Object zzakd;
    
    zzg() {
        this.zzakd = new Object();
    }
    
    public void zza(@NonNull final Task<TResult> task) {
        while (true) {
            // monitorexit(o)
            while (true) {
                final zzf<TResult> zzf;
                synchronized (this.zzakd) {
                    if (this.aJF == null || this.aJG) {
                        break;
                    }
                    this.aJG = true;
                    // monitorexit(this.zzakd)
                    synchronized (this.zzakd) {
                        zzf = this.aJF.poll();
                        if (zzf == null) {
                            this.aJG = false;
                            break;
                        }
                    }
                }
                zzf.onComplete(task);
                continue;
            }
        }
    }
    
    public void zza(@NonNull final zzf<TResult> zzf) {
        synchronized (this.zzakd) {
            if (this.aJF == null) {
                this.aJF = new ArrayDeque<zzf<TResult>>();
            }
            this.aJF.add(zzf);
        }
    }
}
