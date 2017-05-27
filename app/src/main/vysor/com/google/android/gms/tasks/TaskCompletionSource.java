// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult>
{
    private final zzh<TResult> aJH;
    
    public TaskCompletionSource() {
        this.aJH = new zzh<TResult>();
    }
    
    @NonNull
    public Task<TResult> getTask() {
        return this.aJH;
    }
    
    public void setException(@NonNull final Exception exception) {
        this.aJH.setException(exception);
    }
    
    public void setResult(final TResult result) {
        this.aJH.setResult(result);
    }
}
