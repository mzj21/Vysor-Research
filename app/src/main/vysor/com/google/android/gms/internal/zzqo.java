// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import java.util.Collections;

public class zzqo implements zzqq
{
    private final zzqr xk;
    
    public zzqo(final zzqr xk) {
        this.xk = xk;
    }
    
    @Override
    public void begin() {
        this.xk.zzary();
        this.xk.wV.xX = Collections.emptySet();
    }
    
    @Override
    public void connect() {
        this.xk.zzarw();
    }
    
    @Override
    public boolean disconnect() {
        return true;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(final T t) {
        this.xk.wV.xQ.add(t);
        return t;
    }
    
    @Override
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
