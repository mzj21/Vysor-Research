// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult> implements zzf<TResult>
{
    private final Executor aBG;
    private OnFailureListener aJB;
    private final Object zzakd;
    
    public zzd(@NonNull final Executor abg, @NonNull final OnFailureListener ajb) {
        this.zzakd = new Object();
        this.aBG = abg;
        this.aJB = ajb;
    }
    
    @Override
    public void cancel() {
        synchronized (this.zzakd) {
            this.aJB = null;
        }
    }
    
    @Override
    public void onComplete(@NonNull final Task<TResult> task) {
        if (!task.isSuccessful()) {
            synchronized (this.zzakd) {
                if (this.aJB != null) {
                    // monitorexit(this.zzakd)
                    this.aBG.execute(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (zzd.this.zzakd) {
                                if (zzd.this.aJB != null) {
                                    zzd.this.aJB.onFailure(task.getException());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
