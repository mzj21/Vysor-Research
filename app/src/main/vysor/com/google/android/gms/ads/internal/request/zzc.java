// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.DeadObjectException;
import android.os.Binder;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzdb;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public final class zzc
{
    public static zzkt zza(final Context context, final VersionInfoParcel versionInfoParcel, final zzlm<AdRequestInfoParcel> zzlm, final zza zza) {
        return zza(context, versionInfoParcel, zzlm, zza, (zzb)new zzb() {
            @Override
            public boolean zza(final VersionInfoParcel versionInfoParcel) {
                return versionInfoParcel.zzctu || (zzi.zzcm(context) && !zzdi.zzbbv.get());
            }
        });
    }
    
    static zzkt zza(final Context context, final VersionInfoParcel versionInfoParcel, final zzlm<AdRequestInfoParcel> zzlm, final zza zza, final zzb zzb) {
        zzkt zzkt;
        if (zzb.zza(versionInfoParcel)) {
            zzkt = zza(context, zzlm, zza);
        }
        else {
            zzkt = zzb(context, versionInfoParcel, zzlm, zza);
        }
        return zzkt;
    }
    
    private static zzkt zza(final Context context, final zzlm<AdRequestInfoParcel> zzlm, final zza zza) {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Fetching ad response from local ad request service.");
        final zzd.zza zza2 = new zzd.zza(context, zzlm, zza);
        final Void void1 = (Void)zza2.zzqw();
        return zza2;
    }
    
    private static zzkt zzb(final Context context, final VersionInfoParcel versionInfoParcel, final zzlm<AdRequestInfoParcel> zzlm, final zza zza) {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Fetching ad response from remote ad request service.");
        zzkt zzkt;
        if (!zzm.zzjr().zzas(context)) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Failed to connect to remote ad request service.");
            zzkt = null;
        }
        else {
            zzkt = new zzd.zzb(context, versionInfoParcel, zzlm, zza);
        }
        return zzkt;
    }
    
    public interface zza
    {
        void zzb(final AdResponseParcel p0);
    }
    
    interface zzb
    {
        boolean zza(final VersionInfoParcel p0);
    }
}
