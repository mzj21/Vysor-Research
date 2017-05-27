// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public class zze implements zzn
{
    private final Executor zzr;
    
    public zze(final Handler handler) {
        this.zzr = new Executor() {
            @Override
            public void execute(final Runnable runnable) {
                handler.post(runnable);
            }
        };
    }
    
    @Override
    public void zza(final zzk<?> zzk, final zzm<?> zzm) {
        this.zza(zzk, zzm, null);
    }
    
    @Override
    public void zza(final zzk<?> zzk, final zzm<?> zzm, final Runnable runnable) {
        zzk.zzu();
        zzk.zzc("post-response");
        this.zzr.execute(new zza(zzk, zzm, runnable));
    }
    
    @Override
    public void zza(final zzk<?> zzk, final zzr zzr) {
        zzk.zzc("post-error");
        this.zzr.execute(new zza(zzk, zzm.zzd(zzr), null));
    }
    
    private class zza implements Runnable
    {
        private final zzk zzu;
        private final zzm zzv;
        private final Runnable zzw;
        
        public zza(final zzk zzu, final zzm zzv, final Runnable zzw) {
            this.zzu = zzu;
            this.zzv = zzv;
            this.zzw = zzw;
        }
        
        @Override
        public void run() {
            if (this.zzu.isCanceled()) {
                this.zzu.zzd("canceled-at-delivery");
            }
            else {
                if (this.zzv.isSuccess()) {
                    this.zzu.zza(this.zzv.result);
                }
                else {
                    this.zzu.zzc(this.zzv.zzbg);
                }
                if (this.zzv.zzbh) {
                    this.zzu.zzc("intermediate-response");
                }
                else {
                    this.zzu.zzd("done");
                }
                if (this.zzw != null) {
                    this.zzw.run();
                }
            }
        }
    }
}
