// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzh;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api;

public final class zzrx
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final zzry Dh;
    public static final Api.zzf<zzsb> fa;
    private static final Api.zza<zzsb, Api.ApiOptions.NoOptions> fb;
    
    static {
        fa = new Api.zzf();
        fb = new Api.zza<zzsb, Api.ApiOptions.NoOptions>() {
            public zzsb zzf(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzh zzh, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new zzsb(context, looper, zzh, connectionCallbacks, onConnectionFailedListener);
            }
        };
        API = new Api<Api.ApiOptions.NoOptions>("Common.API", (Api.zza<C, Api.ApiOptions.NoOptions>)zzrx.fb, (Api.zzf<C>)zzrx.fa);
        Dh = new zzrz();
    }
}
