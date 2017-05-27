// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

public class zzqf implements ConnectionCallbacks, OnConnectionFailedListener
{
    public final Api<?> tv;
    private final int wT;
    private zzqg wU;
    
    public zzqf(final Api<?> tv, final int wt) {
        this.tv = tv;
        this.wT = wt;
    }
    
    private void zzaqx() {
        zzac.zzb(this.wU, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }
    
    @Override
    public void onConnected(@Nullable final Bundle bundle) {
        this.zzaqx();
        ((GoogleApiClient.ConnectionCallbacks)this.wU).onConnected(bundle);
    }
    
    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zzaqx();
        this.wU.zza(connectionResult, this.tv, this.wT);
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzaqx();
        ((GoogleApiClient.ConnectionCallbacks)this.wU).onConnectionSuspended(n);
    }
    
    public void zza(final zzqg wu) {
        this.wU = wu;
    }
}
