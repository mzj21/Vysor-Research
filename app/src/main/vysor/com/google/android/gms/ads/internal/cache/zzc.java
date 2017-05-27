// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.cache;

import android.os.DeadObjectException;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.zze;

@zziy
public class zzc extends zze<com.google.android.gms.ads.internal.cache.zzf>
{
    zzc(final Context context, final Looper looper, final zzb zzb, final com.google.android.gms.common.internal.zze.zzc zzc) {
        super(context, looper, 123, zzb, zzc, null);
    }
    
    protected com.google.android.gms.ads.internal.cache.zzf zzg(final IBinder binder) {
        return com.google.android.gms.ads.internal.cache.zzf.zza.zzi(binder);
    }
    
    @Override
    protected String zzix() {
        return "com.google.android.gms.ads.service.CACHE";
    }
    
    @Override
    protected String zziy() {
        return "com.google.android.gms.ads.internal.cache.ICacheService";
    }
    
    public com.google.android.gms.ads.internal.cache.zzf zziz() throws DeadObjectException {
        return super.zzatx();
    }
}
