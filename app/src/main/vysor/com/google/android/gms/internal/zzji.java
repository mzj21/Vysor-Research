// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import android.content.Context;
import java.util.WeakHashMap;

@zziy
public final class zzji
{
    private WeakHashMap<Context, zza> zzcmo;
    
    public zzji() {
        this.zzcmo = new WeakHashMap<Context, zza>();
    }
    
    public zzjh zzy(final Context context) {
        final zza zza = this.zzcmo.get(context);
        zzjh zzjh;
        if (zza != null && !zza.hasExpired() && zzdi.zzbdw.get()) {
            zzjh = new zzjh.zza(context, zza.zzcmq).zzsk();
        }
        else {
            zzjh = new zzjh.zza(context).zzsk();
        }
        this.zzcmo.put(context, new zza(zzjh));
        return zzjh;
    }
    
    private class zza
    {
        public final long zzcmp;
        public final zzjh zzcmq;
        
        public zza(final zzjh zzcmq) {
            this.zzcmp = zzu.zzgf().currentTimeMillis();
            this.zzcmq = zzcmq;
        }
        
        public boolean hasExpired() {
            return this.zzcmp + zzdi.zzbdx.get() < zzu.zzgf().currentTimeMillis();
        }
    }
}
