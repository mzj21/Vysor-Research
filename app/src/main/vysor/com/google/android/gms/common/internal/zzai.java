// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import android.os.IInterface;

public class zzai<T extends IInterface> extends zzl<T>
{
    private final Api.zzg<T> Db;
    
    public zzai(final Context context, final Looper looper, final int n, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final zzh zzh, final Api.zzg<T> db) {
        super(context, looper, n, zzh, connectionCallbacks, onConnectionFailedListener);
        this.Db = db;
    }
    
    public Api.zzg<T> zzavk() {
        return this.Db;
    }
    
    protected void zzc(final int n, final T t) {
        this.Db.zza(n, t);
    }
    
    @Override
    protected T zzh(final IBinder binder) {
        return this.Db.zzh(binder);
    }
    
    @Override
    protected String zzix() {
        return this.Db.zzix();
    }
    
    @Override
    protected String zziy() {
        return this.Db.zziy();
    }
}
