// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.support.annotation.Nullable;
import java.util.concurrent.TimeUnit;
import android.support.annotation.NonNull;

public abstract class PendingResult<R extends Result>
{
    @NonNull
    public abstract R await();
    
    @NonNull
    public abstract R await(final long p0, @NonNull final TimeUnit p1);
    
    public abstract void cancel();
    
    public abstract boolean isCanceled();
    
    public abstract void setResultCallback(@NonNull final ResultCallback<? super R> p0);
    
    public abstract void setResultCallback(@NonNull final ResultCallback<? super R> p0, final long p1, @NonNull final TimeUnit p2);
    
    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull final ResultTransform<? super R, ? extends S> resultTransform) {
        throw new UnsupportedOperationException();
    }
    
    public void zza(@NonNull final zza zza) {
        throw new UnsupportedOperationException();
    }
    
    @Nullable
    public Integer zzaqf() {
        throw new UnsupportedOperationException();
    }
    
    public interface zza
    {
        void zzv(final Status p0);
    }
}
