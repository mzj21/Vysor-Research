// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.zze;
import android.content.Context;
import android.support.annotation.Nullable;
import android.content.SharedPreferences;

@zziy
public class zzdh
{
    private final Object zzakd;
    private boolean zzaom;
    @Nullable
    private SharedPreferences zzbak;
    
    public zzdh() {
        this.zzakd = new Object();
        this.zzaom = false;
        this.zzbak = null;
    }
    
    public void initialize(final Context context) {
        final Context remoteContext;
        synchronized (this.zzakd) {
            if (this.zzaom) {
                return;
            }
            remoteContext = zze.getRemoteContext(context);
            if (remoteContext == null) {
                return;
            }
        }
        this.zzbak = zzu.zzgj().zzn(remoteContext);
        this.zzaom = true;
    }
    // monitorexit(o)
    
    public <T> T zzd(final zzde<T> zzde) {
        T t;
        synchronized (this.zzakd) {
            if (!this.zzaom) {
                t = zzde.zzkq();
            }
            else {
                // monitorexit(this.zzakd)
                t = zzle.zzb((Callable<T>)new Callable<T>() {
                    @Override
                    public T call() {
                        return zzde.zza(zzdh.this.zzbak);
                    }
                });
            }
        }
        return t;
    }
}
