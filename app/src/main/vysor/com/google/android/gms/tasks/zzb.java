// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult>
{
    private final Executor aBG;
    private final Continuation<TResult, Task<TContinuationResult>> aJu;
    private final zzh<TContinuationResult> aJv;
    
    public zzb(@NonNull final Executor abg, @NonNull final Continuation<TResult, Task<TContinuationResult>> aJu, @NonNull final zzh<TContinuationResult> aJv) {
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
                while (true) {
                    Task task;
                    try {
                        task = zzb.this.aJu.then(task);
                        if (task == null) {
                            zzb.this.onFailure(new NullPointerException("Continuation returned null"));
                            return;
                        }
                    }
                    catch (RuntimeExecutionException exception) {
                        if (exception.getCause() instanceof Exception) {
                            zzb.this.aJv.setException((Exception)exception.getCause());
                            return;
                        }
                        zzb.this.aJv.setException(exception);
                        return;
                    }
                    catch (Exception exception2) {
                        zzb.this.aJv.setException(exception2);
                        return;
                    }
                    task.addOnSuccessListener(TaskExecutors.aJI, zzb.this);
                    task.addOnFailureListener(TaskExecutors.aJI, zzb.this);
                }
            }
        });
    }
    
    @Override
    public void onFailure(@NonNull final Exception exception) {
        this.aJv.setException(exception);
    }
    
    @Override
    public void onSuccess(final TContinuationResult result) {
        this.aJv.setResult(result);
    }
}
