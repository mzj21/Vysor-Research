// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;
import com.google.android.gms.common.api.Api;
import android.os.IInterface;

public abstract class zzl<T extends IInterface> extends zze<T> implements Api.zze, zzm.zza
{
    private final Account ec;
    private final Set<Scope> hm;
    private final zzh xB;
    
    protected zzl(final Context context, final Looper looper, final int n, final zzh zzh, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzn.zzcf(context), GoogleApiAvailability.getInstance(), n, zzh, zzac.zzy(connectionCallbacks), zzac.zzy(onConnectionFailedListener));
    }
    
    protected zzl(final Context context, final Looper looper, final zzn zzn, final GoogleApiAvailability googleApiAvailability, final int n, final zzh xb, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, zzn, googleApiAvailability, n, zza(connectionCallbacks), zza(onConnectionFailedListener), xb.zzauk());
        this.xB = xb;
        this.ec = xb.getAccount();
        this.hm = this.zzb(xb.zzauh());
    }
    
    @Nullable
    private static zzb zza(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzb zzb;
        if (connectionCallbacks == null) {
            zzb = null;
        }
        else {
            zzb = new zzb() {
                @Override
                public void onConnected(@Nullable final Bundle bundle) {
                    connectionCallbacks.onConnected(bundle);
                }
                
                @Override
                public void onConnectionSuspended(final int n) {
                    connectionCallbacks.onConnectionSuspended(n);
                }
            };
        }
        return zzb;
    }
    
    @Nullable
    private static zzc zza(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzc zzc;
        if (onConnectionFailedListener == null) {
            zzc = null;
        }
        else {
            zzc = new zzc() {
                @Override
                public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
                    onConnectionFailedListener.onConnectionFailed(connectionResult);
                }
            };
        }
        return zzc;
    }
    
    private Set<Scope> zzb(@NonNull final Set<Scope> set) {
        final Set<Scope> zzc = this.zzc(set);
        final Iterator<Scope> iterator = zzc.iterator();
        while (iterator.hasNext()) {
            if (!set.contains(iterator.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return zzc;
    }
    
    @Override
    public final Account getAccount() {
        return this.ec;
    }
    
    @Override
    protected final Set<Scope> zzatz() {
        return this.hm;
    }
    
    protected final zzh zzaus() {
        return this.xB;
    }
    
    @NonNull
    protected Set<Scope> zzc(@NonNull final Set<Scope> set) {
        return set;
    }
}
