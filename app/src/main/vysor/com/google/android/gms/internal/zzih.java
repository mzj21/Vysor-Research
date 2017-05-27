// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.formats.zzi;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import android.content.Context;

@zziy
public abstract class zzih implements zzkt<Void>, zza
{
    protected final Context mContext;
    protected final zzlt zzbkr;
    protected final zzil.zza zzccj;
    protected final zzke.zza zzcck;
    protected AdResponseParcel zzccl;
    private Runnable zzccm;
    protected final Object zzccn;
    private AtomicBoolean zzcco;
    
    protected zzih(final Context mContext, final zzke.zza zzcck, final zzlt zzbkr, final zzil.zza zzccj) {
        this.zzccn = new Object();
        this.zzcco = new AtomicBoolean(true);
        this.mContext = mContext;
        this.zzcck = zzcck;
        this.zzccl = this.zzcck.zzcop;
        this.zzbkr = zzbkr;
        this.zzccj = zzccj;
    }
    
    private zzke zzam(final int n) {
        final AdRequestInfoParcel zzcix = this.zzcck.zzcix;
        return new zzke(zzcix.zzcfu, this.zzbkr, this.zzccl.zzbsd, n, this.zzccl.zzbse, this.zzccl.zzche, this.zzccl.orientation, this.zzccl.zzbsj, zzcix.zzcfx, this.zzccl.zzchc, null, null, null, null, null, this.zzccl.zzchd, this.zzcck.zzaqz, this.zzccl.zzchb, this.zzcck.zzcoj, this.zzccl.zzchg, this.zzccl.zzchh, this.zzcck.zzcod, null, this.zzccl.zzchr, this.zzccl.zzchs, this.zzccl.zzcht, this.zzccl.zzchu, this.zzccl.zzchv, null, this.zzccl.zzbsg, this.zzccl.zzchy);
    }
    
    @Override
    public void cancel() {
        if (this.zzcco.getAndSet(false)) {
            this.zzbkr.stopLoading();
            zzu.zzgb().zzl(this.zzbkr);
            this.zzal(-1);
            zzkr.zzcrf.removeCallbacks(this.zzccm);
        }
    }
    
    @Override
    public void zza(final zzlt zzlt, final boolean b) {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("WebView finished loading.");
        if (this.zzcco.getAndSet(false)) {
            int zzqv = 0;
            if (b) {
                zzqv = this.zzqv();
            }
            this.zzal(zzqv);
            zzkr.zzcrf.removeCallbacks(this.zzccm);
        }
    }
    
    protected void zzal(final int n) {
        if (n != -2) {
            this.zzccl = new AdResponseParcel(n, this.zzccl.zzbsj);
        }
        this.zzbkr.zzvm();
        this.zzccj.zzb(this.zzam(n));
    }
    
    public final Void zzqt() {
        zzac.zzhq("Webview render task needs to be called on UI thread.");
        this.zzccm = new Runnable() {
            @Override
            public void run() {
                if (zzih.this.zzcco.get()) {
                    com.google.android.gms.ads.internal.util.client.zzb.e("Timed out waiting for WebView to finish loading.");
                    zzih.this.cancel();
                }
            }
        };
        zzkr.zzcrf.postDelayed(this.zzccm, (long)zzdi.zzbel.get());
        this.zzqu();
        return null;
    }
    
    protected abstract void zzqu();
    
    protected int zzqv() {
        return -2;
    }
}
