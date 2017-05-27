// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class zzc extends Thread
{
    private static final boolean DEBUG;
    private final BlockingQueue<zzk<?>> zzg;
    private final BlockingQueue<zzk<?>> zzh;
    private final zzb zzi;
    private final zzn zzj;
    private volatile boolean zzk;
    
    static {
        DEBUG = zzs.DEBUG;
    }
    
    public zzc(final BlockingQueue<zzk<?>> zzg, final BlockingQueue<zzk<?>> zzh, final zzb zzi, final zzn zzj) {
        super("VolleyCacheDispatcher");
        this.zzk = false;
        this.zzg = zzg;
        this.zzh = zzh;
        this.zzi = zzi;
        this.zzj = zzj;
    }
    
    public void quit() {
        this.zzk = true;
        this.interrupt();
    }
    
    @Override
    public void run() {
        if (zzc.DEBUG) {
            zzs.zza("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzi.initialize();
    Label_0029_Outer:
        while (true) {
            while (true) {
                zzk<?> zzk;
                try {
                    while (true) {
                        zzk = this.zzg.take();
                        zzk.zzc("cache-queue-take");
                        if (!zzk.isCanceled()) {
                            break;
                        }
                        zzk.zzd("cache-discard-canceled");
                    }
                }
                catch (InterruptedException ex) {
                    if (this.zzk) {
                        return;
                    }
                    continue Label_0029_Outer;
                }
                final zzb.zza zza = this.zzi.zza(zzk.zzg());
                if (zza == null) {
                    zzk.zzc("cache-miss");
                    this.zzh.put(zzk);
                    continue;
                }
                if (zza.zza()) {
                    zzk.zzc("cache-hit-expired");
                    zzk.zza(zza);
                    this.zzh.put(zzk);
                    continue;
                }
                zzk.zzc("cache-hit");
                final zzm<?> zza2 = zzk.zza(new zzi(zza.data, zza.zzf));
                zzk.zzc("cache-hit-parsed");
                if (!zza.zzb()) {
                    this.zzj.zza(zzk, zza2);
                    continue;
                }
                zzk.zzc("cache-hit-refresh-needed");
                zzk.zza(zza);
                zza2.zzbh = true;
                this.zzj.zza(zzk, zza2, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            zzc.this.zzh.put(zzk);
                        }
                        catch (InterruptedException ex) {}
                    }
                });
                continue;
            }
        }
    }
}
