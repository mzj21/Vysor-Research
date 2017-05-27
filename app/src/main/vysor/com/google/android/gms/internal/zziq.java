// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.formats.zzi;
import java.util.List;
import com.google.android.gms.ads.internal.zzq;
import android.content.Context;
import java.util.concurrent.Future;
import com.google.android.gms.ads.internal.request.AdResponseParcel;

@zziy
public class zziq extends zzkm
{
    private final Object zzakd;
    private final zzil.zza zzccj;
    private final zzke.zza zzcck;
    private final AdResponseParcel zzccl;
    private final zzis zzcdl;
    private Future<zzke> zzcdm;
    
    public zziq(final Context context, final zzq zzq, final zzke.zza zza, final zzau zzau, final zzil.zza zza2, final zzdq zzdq) {
        this(zza, zza2, new zzis(context, zzq, new zzky(context), zzau, zza, zzdq));
    }
    
    zziq(final zzke.zza zzcck, final zzil.zza zzccj, final zzis zzcdl) {
        this.zzakd = new Object();
        this.zzcck = zzcck;
        this.zzccl = zzcck.zzcop;
        this.zzccj = zzccj;
        this.zzcdl = zzcdl;
    }
    
    private zzke zzan(final int n) {
        return new zzke(this.zzcck.zzcix.zzcfu, null, null, n, null, null, this.zzccl.orientation, this.zzccl.zzbsj, this.zzcck.zzcix.zzcfx, false, null, null, null, null, null, this.zzccl.zzchd, this.zzcck.zzaqz, this.zzccl.zzchb, this.zzcck.zzcoj, this.zzccl.zzchg, this.zzccl.zzchh, this.zzcck.zzcod, null, null, null, null, this.zzcck.zzcop.zzchu, this.zzcck.zzcop.zzchv, null, null, this.zzccl.zzchy);
    }
    
    @Override
    public void onStop() {
        synchronized (this.zzakd) {
            if (this.zzcdm != null) {
                this.zzcdm.cancel(true);
            }
        }
    }
    
    @Override
    public void zzfc() {
    Label_0049_Outer:
        while (true) {
            while (true) {
                int n;
                while (true) {
                    try {
                        synchronized (this.zzakd) {
                            this.zzcdm = (Future<zzke>)zzkq.zza((Callable<Object>)this.zzcdl);
                            // monitorexit(this.zzakd)
                            final zzke zzan = this.zzcdm.get(60000L, TimeUnit.MILLISECONDS);
                            n = -2;
                            if (zzan != null) {
                                zzkr.zzcrf.post((Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        zziq.this.zzccj.zzb(zzan);
                                    }
                                });
                                return;
                            }
                        }
                    }
                    catch (TimeoutException ex) {
                        zzb.zzdf("Timed out waiting for native ad.");
                        this.zzcdm.cancel(true);
                        n = 2;
                        final zzke zzan = null;
                        continue Label_0049_Outer;
                    }
                    catch (ExecutionException ex2) {
                        final zzke zzan = null;
                        n = 0;
                        continue Label_0049_Outer;
                    }
                    catch (InterruptedException ex3) {
                        final zzke zzan = null;
                        n = 0;
                        continue Label_0049_Outer;
                    }
                    catch (CancellationException ex4) {
                        final zzke zzan = null;
                        n = 0;
                        continue Label_0049_Outer;
                    }
                    break;
                }
                final zzke zzan = this.zzan(n);
                continue;
            }
        }
    }
}
