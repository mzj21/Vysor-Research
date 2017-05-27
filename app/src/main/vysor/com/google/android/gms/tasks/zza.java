// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult> implements zzf<TResult>
{
    private final Executor aBG;
    private final Continuation<TResult, TContinuationResult> aJu;
    private final zzh<TContinuationResult> aJv;
    
    public zza(@NonNull final Executor abg, @NonNull final Continuation<TResult, TContinuationResult> aJu, @NonNull final zzh<TContinuationResult> aJv) {
        this.aBG = abg;
        this.aJu = aJu;
        this.aJv = aJv;
    }
    
    @Override
    public void cancel() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void onComplete(@NonNull final Task<TResult> task) {
        this.aBG.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    zza.this.aJv.setResult(zza.this.aJu.then(task));
                }
                catch (RuntimeExecutionException exception) {
                    if (exception.getCause() instanceof Exception) {
                        zza.this.aJv.setException((Exception)exception.getCause());
                        return;
                    }
                    zza.this.aJv.setException(exception);
                }
                catch (Exception exception2) {
                    zza.this.aJv.setException(exception2);
                }
            }
        });
    }
}
