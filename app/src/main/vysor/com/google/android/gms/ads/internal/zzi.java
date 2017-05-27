// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzat;
import android.view.View;
import com.google.android.gms.internal.zzdi;
import android.content.Context;
import java.util.Iterator;
import android.view.MotionEvent;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.ads.internal.client.zzm;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzap;

@zziy
class zzi implements zzap, Runnable
{
    private zzv zzall;
    private final List<Object[]> zzamv;
    private final AtomicReference<zzap> zzamw;
    CountDownLatch zzamx;
    
    public zzi(final zzv zzall) {
        this.zzamv = new Vector<Object[]>();
        this.zzamw = new AtomicReference<zzap>();
        this.zzamx = new CountDownLatch(1);
        this.zzall = zzall;
        if (zzm.zzjr().zzvf()) {
            zzkq.zza(this);
        }
        else {
            this.run();
        }
    }
    
    private void zzev() {
        if (!this.zzamv.isEmpty()) {
            for (final Object[] array : this.zzamv) {
                if (array.length == 1) {
                    this.zzamw.get().zza((MotionEvent)array[0]);
                }
                else {
                    if (array.length != 3) {
                        continue;
                    }
                    this.zzamw.get().zza((int)array[0], (int)array[1], (int)array[2]);
                }
            }
            this.zzamv.clear();
        }
    }
    
    private Context zzh(Context context) {
        if (zzdi.zzbba.get()) {
            final Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
        }
        return context;
    }
    
    @Override
    public void run() {
        while (true) {
            while (true) {
                try {
                    if (zzdi.zzbbs.get()) {
                        if (!this.zzall.zzaqv.zzctu) {
                            final boolean b = false;
                            this.zza(this.zzd(this.zzall.zzaqv.zzcs, this.zzh(this.zzall.zzahn), b));
                            return;
                        }
                    }
                }
                finally {
                    this.zzamx.countDown();
                    this.zzall = null;
                }
                final boolean b = true;
                continue;
            }
        }
    }
    
    @Override
    public String zza(final Context context, final String s, final View view) {
        if (!this.zzeu()) {
            return "";
        }
        final zzap zzap = this.zzamw.get();
        if (zzap == null) {
            return "";
        }
        this.zzev();
        return zzap.zza(this.zzh(context), s, view);
        zza = "";
        return zza;
    }
    
    @Override
    public void zza(final int n, final int n2, final int n3) {
        final zzap zzap = this.zzamw.get();
        if (zzap != null) {
            this.zzev();
            zzap.zza(n, n2, n3);
        }
        else {
            this.zzamv.add(new Object[] { n, n2, n3 });
        }
    }
    
    @Override
    public void zza(final MotionEvent motionEvent) {
        final zzap zzap = this.zzamw.get();
        if (zzap != null) {
            this.zzev();
            zzap.zza(motionEvent);
        }
        else {
            this.zzamv.add(new Object[] { motionEvent });
        }
    }
    
    protected void zza(final zzap zzap) {
        this.zzamw.set(zzap);
    }
    
    @Override
    public String zzb(final Context context) {
        if (!this.zzeu()) {
            return "";
        }
        final zzap zzap = this.zzamw.get();
        if (zzap == null) {
            return "";
        }
        this.zzev();
        return zzap.zzb(this.zzh(context));
        zzb = "";
        return zzb;
    }
    
    protected zzap zzd(final String s, final Context context, final boolean b) {
        return zzat.zza(s, context, b);
    }
    
    protected boolean zzeu() {
        try {
            this.zzamx.await();
            return true;
        }
        catch (InterruptedException ex) {
            zzb.zzd("Interrupted during GADSignals creation.", ex);
            return false;
        }
    }
}
