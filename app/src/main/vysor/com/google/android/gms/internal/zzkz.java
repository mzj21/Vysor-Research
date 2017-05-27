// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import android.os.Looper;
import android.os.HandlerThread;
import android.os.Handler;

@zziy
public class zzkz
{
    private Handler mHandler;
    private final Object zzakd;
    private HandlerThread zzcsu;
    private int zzcsv;
    
    public zzkz() {
        this.zzcsu = null;
        this.mHandler = null;
        this.zzcsv = 0;
        this.zzakd = new Object();
    }
    
    public Looper zzuy() {
        while (true) {
            while (true) {
                synchronized (this.zzakd) {
                    if (this.zzcsv == 0) {
                        if (this.zzcsu == null) {
                            zzkn.v("Starting the looper thread.");
                            (this.zzcsu = new HandlerThread("LooperProvider")).start();
                            this.mHandler = new Handler(this.zzcsu.getLooper());
                            zzkn.v("Looper thread started.");
                        }
                        else {
                            zzkn.v("Resuming the looper thread");
                            this.zzakd.notifyAll();
                        }
                        ++this.zzcsv;
                        return this.zzcsu.getLooper();
                    }
                }
                zzac.zzb(this.zzcsu, "Invalid state: mHandlerThread should already been initialized.");
                continue;
            }
        }
    }
    
    public void zzuz() {
        while (true) {
            while (true) {
                synchronized (this.zzakd) {
                    if (this.zzcsv > 0) {
                        final boolean b = true;
                        zzac.zzb(b, (Object)"Invalid state: release() called more times than expected.");
                        final int zzcsv = -1 + this.zzcsv;
                        this.zzcsv = zzcsv;
                        if (zzcsv == 0) {
                            this.mHandler.post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    synchronized (zzkz.this.zzakd) {
                                        zzkn.v("Suspending the looper thread");
                                        while (zzkz.this.zzcsv == 0) {
                                            try {
                                                zzkz.this.zzakd.wait();
                                                zzkn.v("Looper thread resumed");
                                            }
                                            catch (InterruptedException ex) {
                                                zzkn.v("Looper thread interrupted.");
                                            }
                                        }
                                    }
                                }
                                // monitorexit(o)
                            });
                        }
                        return;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
}
