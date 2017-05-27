// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzh;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zzl;

public class zzsb extends zzl<zzsd>
{
    public zzsb(final Context context, final Looper looper, final zzh zzh, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 39, zzh, connectionCallbacks, onConnectionFailedListener);
    }
    
    protected zzsd zzea(final IBinder binder) {
        return zzsd.zza.zzec(binder);
    }
    
    public String zzix() {
        return "com.google.android.gms.common.service.START";
    }
    
    @Override
    protected String zziy() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }
}
