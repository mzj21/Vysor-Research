// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzh;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.zzg;
import com.google.android.gms.common.api.Api;

public final class zzwy
{
    public static final Api<zzxa> API;
    public static final Api<zza> Hp;
    public static final Api.zzf<zzg> azY;
    static final Api.zza<zzg, zza> azZ;
    public static final Api.zzf<zzg> fa;
    public static final Api.zza<zzg, zzxa> fb;
    public static final Scope hd;
    public static final Scope he;
    
    static {
        fa = new Api.zzf();
        azY = new Api.zzf();
        fb = new Api.zza<zzg, zzxa>() {
            public com.google.android.gms.signin.internal.zzg zza(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzh zzh, final zzxa zzxa, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                zzxa aAa;
                if (zzxa == null) {
                    aAa = zzxa.aAa;
                }
                else {
                    aAa = zzxa;
                }
                return new com.google.android.gms.signin.internal.zzg(context, looper, true, zzh, aAa, connectionCallbacks, onConnectionFailedListener);
            }
        };
        azZ = new Api.zza<zzg, zza>() {
            public com.google.android.gms.signin.internal.zzg zza(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzh zzh, final zzwy.zza zza, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new com.google.android.gms.signin.internal.zzg(context, looper, false, zzh, zza.zzccz(), connectionCallbacks, onConnectionFailedListener);
            }
        };
        hd = new Scope("profile");
        he = new Scope("email");
        API = new Api<zzxa>("SignIn.API", (Api.zza<C, zzxa>)zzwy.fb, (Api.zzf<C>)zzwy.fa);
        Hp = new Api<zza>("SignIn.INTERNAL_API", (Api.zza<C, zza>)zzwy.azZ, (Api.zzf<C>)zzwy.azY);
    }
    
    public static class zza implements HasOptions
    {
        public Bundle zzccz() {
            return null;
        }
    }
}
