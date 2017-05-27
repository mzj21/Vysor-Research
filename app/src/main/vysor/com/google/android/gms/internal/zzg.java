// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.SystemClock;
import android.os.Process;
import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build$VERSION;
import java.util.concurrent.BlockingQueue;

public class zzg extends Thread
{
    private final zzb zzi;
    private final zzn zzj;
    private volatile boolean zzk;
    private final BlockingQueue<zzk<?>> zzx;
    private final zzf zzy;
    
    public zzg(final BlockingQueue<zzk<?>> zzx, final zzf zzy, final zzb zzi, final zzn zzj) {
        super("VolleyNetworkDispatcher");
        this.zzk = false;
        this.zzx = zzx;
        this.zzy = zzy;
        this.zzi = zzi;
        this.zzj = zzj;
    }
    
    @TargetApi(14)
    private void zzb(final zzk<?> zzk) {
        if (Build$VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(zzk.zzf());
        }
    }
    
    private void zzb(final zzk<?> zzk, final zzr zzr) {
        this.zzj.zza(zzk, zzk.zzb(zzr));
    }
    
    public void quit() {
        this.zzk = true;
        this.interrupt();
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            zzk<?> zzk = null;
            try {
                zzk = this.zzx.take();
                try {
                    zzk.zzc("network-queue-take");
                    if (!zzk.isCanceled()) {
                        goto Label_0080;
                    }
                    zzk.zzd("network-discard-cancelled");
                }
                catch (zzr zzr) {
                    zzr.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzb(zzk, zzr);
                }
                catch (Exception ex) {
                    zzs.zza(ex, "Unhandled exception %s", ex.toString());
                    final zzr zzr2 = new zzr(ex);
                    zzr2.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzj.zza(zzk, zzr2);
                }
                continue;
            }
            catch (InterruptedException ex2) {}
            final zzi zzi;
            final zzm<?> zza = zzk.zza(zzi);
            zzk.zzc("network-parse-complete");
            if (zzk.zzq() && zza.zzbf != null) {
                this.zzi.zza(zzk.zzg(), zza.zzbf);
                zzk.zzc("network-cache-written");
            }
            zzk.zzu();
            this.zzj.zza(zzk, zza);
        }
    }
}
