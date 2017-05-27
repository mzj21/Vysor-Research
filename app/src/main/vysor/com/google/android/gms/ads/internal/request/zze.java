// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.DeadObjectException;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zze extends com.google.android.gms.common.internal.zze<com.google.android.gms.ads.internal.request.zzk>
{
    final int zzcfs;
    
    public zze(final Context context, final Looper looper, final zzb zzb, final zzc zzc, final int zzcfs) {
        super(context, looper, 8, zzb, zzc, null);
        this.zzcfs = zzcfs;
    }
    
    protected com.google.android.gms.ads.internal.request.zzk zzbd(final IBinder binder) {
        return com.google.android.gms.ads.internal.request.zzk.zza.zzbe(binder);
    }
    
    @Override
    protected String zzix() {
        return "com.google.android.gms.ads.service.START";
    }
    
    @Override
    protected String zziy() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }
    
    public com.google.android.gms.ads.internal.request.zzk zzry() throws DeadObjectException {
        return super.zzatx();
    }
}
