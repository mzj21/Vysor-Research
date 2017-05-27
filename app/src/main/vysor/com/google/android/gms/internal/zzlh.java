// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;

@zziy
public class zzlh<T> implements zzlj<T>
{
    private final T zzctv;
    private final zzlk zzcty;
    
    public zzlh(final T zzctv) {
        this.zzctv = zzctv;
        (this.zzcty = new zzlk()).zzvi();
    }
    
    @Override
    public boolean cancel(final boolean b) {
        return false;
    }
    
    @Override
    public T get() {
        return this.zzctv;
    }
    
    @Override
    public T get(final long n, final TimeUnit timeUnit) {
        return this.zzctv;
    }
    
    @Override
    public boolean isCancelled() {
        return false;
    }
    
    @Override
    public boolean isDone() {
        return true;
    }
    
    @Override
    public void zzc(final Runnable runnable) {
        this.zzcty.zzc(runnable);
    }
}
