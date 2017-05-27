// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zzd;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import android.view.MotionEvent;
import android.content.Context;

public final class zzcc
{
    private final zzcg zzakh;
    
    public zzcc(final String s, final Context context, final boolean b) {
        this.zzakh = zzcf.zzb(s, context, b);
    }
    
    public void zza(final MotionEvent motionEvent) throws RemoteException {
        this.zzakh.zzd(zze.zzac(motionEvent));
    }
    
    public Uri zzc(final Uri uri, final Context context) throws zzcd, RemoteException {
        final zzd zza = this.zzakh.zza(zze.zzac(uri), zze.zzac(context));
        if (zza == null) {
            throw new zzcd();
        }
        return (Uri)zze.zzae(zza);
    }
    
    public Uri zzd(final Uri uri, final Context context) throws zzcd, RemoteException {
        final zzd zzb = this.zzakh.zzb(zze.zzac(uri), zze.zzac(context));
        if (zzb == null) {
            throw new zzcd();
        }
        return (Uri)zze.zzae(zzb);
    }
}
