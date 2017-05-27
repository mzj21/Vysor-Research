// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;

public interface zzqq
{
    void begin();
    
    void connect();
    
    boolean disconnect();
    
    void onConnected(final Bundle p0);
    
    void onConnectionSuspended(final int p0);
    
    void zza(final ConnectionResult p0, final Api<?> p1, final int p2);
    
     <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(final T p0);
    
     <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(final T p0);
}
