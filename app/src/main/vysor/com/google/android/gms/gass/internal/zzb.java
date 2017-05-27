// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gass.internal;

import android.os.IInterface;
import android.os.IBinder;
import android.os.DeadObjectException;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zze;

public class zzb extends zze<zze>
{
    public zzb(final Context context, final Looper looper, final com.google.android.gms.common.internal.zze.zzb zzb, final zzc zzc) {
        super(context, looper, 116, zzb, zzc, null);
    }
    
    public zze zzbnu() throws DeadObjectException {
        return super.zzatx();
    }
    
    protected zze zzgq(final IBinder binder) {
        return com.google.android.gms.gass.internal.zze.zza.zzgr(binder);
    }
    
    @Override
    protected String zzix() {
        return "com.google.android.gms.gass.START";
    }
    
    @Override
    protected String zziy() {
        return "com.google.android.gms.gass.internal.IGassService";
    }
}
