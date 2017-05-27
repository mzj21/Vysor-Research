// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public abstract class TransformedResult<R extends Result>
{
    public abstract void andFinally(@NonNull final ResultCallbacks<? super R> p0);
    
    @NonNull
    public abstract <S extends Result> TransformedResult<S> then(@NonNull final ResultTransform<? super R, ? extends S> p0);
}
