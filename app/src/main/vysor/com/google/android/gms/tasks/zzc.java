// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult> implements zzf<TResult>
{
    private final Executor aBG;
    private OnCompleteListener<TResult> aJz;
    private final Object zzakd;
    
    public zzc(@NonNull final Executor abg, @NonNull final OnCompleteListener<TResult> aJz) {
        this.zzakd = new Object();
        this.aBG = abg;
        this.aJz = aJz;
    }
    
    @Override
    public void cancel() {
        synchronized (this.zzakd) {
            this.aJz = null;
        }
    }
    
    @Override
    public void onComplete(@NonNull final Task<TResult> task) {
        synchronized (this.zzakd) {
            if (this.aJz != null) {
                // monitorexit(this.zzakd)
                this.aBG.execute(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (zzc.this.zzakd) {
                            if (zzc.this.aJz != null) {
                                zzc.this.aJz.onComplete(task);
                            }
                        }
                    }
                });
            }
        }
    }
}
