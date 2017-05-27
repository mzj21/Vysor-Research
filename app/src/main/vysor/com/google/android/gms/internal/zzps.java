// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzh;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zzl;

public class zzps extends zzl<zzpv>
{
    public zzps(final Context context, final Looper looper, final zzh zzh, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, zzh, connectionCallbacks, onConnectionFailedListener);
    }
    
    public void zza(final zzpu zzpu, final LogEventParcelable logEventParcelable) throws RemoteException {
        this.zzatx().zza(zzpu, logEventParcelable);
    }
    
    protected zzpv zzdm(final IBinder binder) {
        return zzpv.zza.zzdo(binder);
    }
    
    @Override
    protected String zzix() {
        return "com.google.android.gms.clearcut.service.START";
    }
    
    @Override
    protected String zziy() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}
