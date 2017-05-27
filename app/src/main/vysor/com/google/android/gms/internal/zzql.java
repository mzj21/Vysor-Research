// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.api.Api;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class zzql extends GoogleApiClient
{
    private final UnsupportedOperationException xj;
    
    public zzql(final String s) {
        this.xj = new UnsupportedOperationException(s);
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        throw this.xj;
    }
    
    @Override
    public ConnectionResult blockingConnect(final long n, @NonNull final TimeUnit timeUnit) {
        throw this.xj;
    }
    
    @Override
    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        throw this.xj;
    }
    
    @Override
    public void connect() {
        throw this.xj;
    }
    
    @Override
    public void disconnect() {
        throw this.xj;
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        throw this.xj;
    }
    
    @NonNull
    @Override
    public ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        throw this.xj;
    }
    
    @Override
    public boolean hasConnectedApi(@NonNull final Api<?> api) {
        throw this.xj;
    }
    
    @Override
    public boolean isConnected() {
        throw this.xj;
    }
    
    @Override
    public boolean isConnecting() {
        throw this.xj;
    }
    
    @Override
    public boolean isConnectionCallbacksRegistered(@NonNull final ConnectionCallbacks connectionCallbacks) {
        throw this.xj;
    }
    
    @Override
    public boolean isConnectionFailedListenerRegistered(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        throw this.xj;
    }
    
    @Override
    public void reconnect() {
        throw this.xj;
    }
    
    @Override
    public void registerConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        throw this.xj;
    }
    
    @Override
    public void registerConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        throw this.xj;
    }
    
    @Override
    public void stopAutoManage(@NonNull final FragmentActivity fragmentActivity) {
        throw this.xj;
    }
    
    @Override
    public void unregisterConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        throw this.xj;
    }
    
    @Override
    public void unregisterConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        throw this.xj;
    }
}
