// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult>
{
    private final Executor aBG;
    private OnSuccessListener<? super TResult> aJD;
    private final Object zzakd;
    
    public zze(@NonNull final Executor abg, @NonNull final OnSuccessListener<? super TResult> ajd) {
        this.zzakd = new Object();
        this.aBG = abg;
        this.aJD = ajd;
    }
    
    @Override
    public void cancel() {
        synchronized (this.zzakd) {
            this.aJD = null;
        }
    }
    
    @Override
    public void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzakd) {
                if (this.aJD != null) {
                    // monitorexit(this.zzakd)
                    this.aBG.execute(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (zze.this.zzakd) {
                                if (zze.this.aJD != null) {
                                    zze.this.aJD.onSuccess(task.getResult());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
