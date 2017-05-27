// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.RejectedExecutionException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.Future;
import com.google.android.gms.ads.internal.zzu;
import android.os.Process;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;

@zziy
public final class zzkq
{
    private static final ThreadPoolExecutor zzcqx;
    private static final ThreadPoolExecutor zzcqy;
    
    static {
        zzcqx = new ThreadPoolExecutor(10, 10, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), zzcu("Default"));
        zzcqy = new ThreadPoolExecutor(5, 5, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), zzcu("Loader"));
        zzkq.zzcqx.allowCoreThreadTimeOut(true);
        zzkq.zzcqy.allowCoreThreadTimeOut(true);
    }
    
    public static zzlj<Void> zza(final int n, final Runnable runnable) {
        zzlj<Void> zzlj;
        if (n == 1) {
            zzlj = zza(zzkq.zzcqy, (Callable<Void>)new Callable<Void>() {
                public Void zzdb() {
                    runnable.run();
                    return null;
                }
            });
        }
        else {
            zzlj = zza(zzkq.zzcqx, (Callable<Void>)new Callable<Void>() {
                public Void zzdb() {
                    runnable.run();
                    return null;
                }
            });
        }
        return zzlj;
    }
    
    public static zzlj<Void> zza(final Runnable runnable) {
        return zza(0, runnable);
    }
    
    public static <T> zzlj<T> zza(final Callable<T> callable) {
        return zza(zzkq.zzcqx, callable);
    }
    
    public static <T> zzlj<T> zza(final ExecutorService executorService, final Callable<T> callable) {
        final zzlg<T> zzlg = new zzlg<T>();
        try {
            zzlg.zzd(new Runnable() {
                final /* synthetic */ Future zzcrc = executorService.submit(new Runnable(zzlg, callable) {
                    final /* synthetic */ zzlg zzcra;
                    final /* synthetic */ Callable zzcrb;
                    
                    @Override
                    public void run() {
                        try {
                            Process.setThreadPriority(10);
                            this.zzcra.zzh(this.zzcrb.call());
                        }
                        catch (Exception ex) {
                            zzu.zzgd().zza(ex, "AdThreadPool.submit");
                            this.zzcra.zze(ex);
                        }
                    }
                });
                
                @Override
                public void run() {
                    if (zzlg.isCancelled()) {
                        this.zzcrc.cancel(true);
                    }
                }
            });
            return zzlg;
        }
        catch (RejectedExecutionException ex) {
            zzb.zzd("Thread execution is rejected.", ex);
            zzlg.cancel(true);
            return zzlg;
        }
    }
    
    private static ThreadFactory zzcu(final String s) {
        return new ThreadFactory() {
            private final AtomicInteger zzcrd = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                final String zzcre = s;
                return new Thread(runnable, new StringBuilder(23 + String.valueOf(zzcre).length()).append("AdWorker(").append(zzcre).append(") #").append(this.zzcrd.getAndIncrement()).toString());
            }
        };
    }
}
