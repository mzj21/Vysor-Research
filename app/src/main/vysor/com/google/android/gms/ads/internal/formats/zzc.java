// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzd;
import android.os.RemoteException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzdx;

@zziy
public class zzc extends zzdx.zza
{
    private final Uri mUri;
    private final Drawable zzbjo;
    private final double zzbjp;
    
    public zzc(final Drawable zzbjo, final Uri mUri, final double zzbjp) {
        this.zzbjo = zzbjo;
        this.mUri = mUri;
        this.zzbjp = zzbjp;
    }
    
    public double getScale() {
        return this.zzbjp;
    }
    
    public Uri getUri() throws RemoteException {
        return this.mUri;
    }
    
    public zzd zzln() throws RemoteException {
        return zze.zzac(this.zzbjo);
    }
}
