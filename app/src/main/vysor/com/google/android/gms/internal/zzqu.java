// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import android.support.annotation.NonNull;
import android.os.Looper;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.common.api.Api;

public class zzqu<O extends Api.ApiOptions> extends zzql
{
    private final zzd<O> yN;
    
    public zzqu(final zzd<O> yn) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.yN = yn;
    }
    
    @Override
    public Looper getLooper() {
        return this.yN.getLooper();
    }
    
    @Override
    public void zza(final zzrp zzrp) {
        this.yN.zzapu();
    }
    
    @Override
    public void zzb(final zzrp zzrp) {
        this.yN.zzapv();
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull final T t) {
        return this.yN.zza(t);
    }
    
    @Override
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull final T t) {
        return this.yN.zzb(t);
    }
}
