// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.client.zza;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.ads.internal.zzq;
import android.content.Context;

@zziy
public class zzir
{
    private static final Object zzaok;
    private static final long zzcdo;
    private static boolean zzcdp;
    private static zzfy zzcdq;
    private final Context mContext;
    private final zzq zzbkj;
    private final zzau zzbkp;
    private final zzke.zza zzcck;
    private zzfw zzcdr;
    private zzfy.zze zzcds;
    private zzfv zzcdt;
    private boolean zzcdu;
    
    static {
        zzcdo = TimeUnit.SECONDS.toMillis(60L);
        zzaok = new Object();
        zzir.zzcdp = false;
        zzir.zzcdq = null;
    }
    
    public zzir(final Context mContext, final zzke.zza zzcck, final zzq zzbkj, final zzau zzbkp) {
        this.zzcdu = false;
        this.mContext = mContext;
        this.zzcck = zzcck;
        this.zzbkj = zzbkj;
        this.zzbkp = zzbkp;
        this.zzcdu = zzdi.zzbfx.get();
    }
    
    public static String zza(final zzke.zza zza, final String s) {
        String s2;
        if (zza.zzcop.zzbyj.indexOf("https") == 0) {
            s2 = "https:";
        }
        else {
            s2 = "http:";
        }
        final String value = String.valueOf(s2);
        final String value2 = String.valueOf(s);
        String concat;
        if (value2.length() != 0) {
            concat = value.concat(value2);
        }
        else {
            concat = new String(value);
        }
        return concat;
    }
    
    private void zzrg() {
        synchronized (zzir.zzaok) {
            if (!zzir.zzcdp) {
                Context context;
                if (this.mContext.getApplicationContext() != null) {
                    context = this.mContext.getApplicationContext();
                }
                else {
                    context = this.mContext;
                }
                zzir.zzcdq = new zzfy(context, this.zzcck.zzcix.zzaqv, zza(this.zzcck, zzdi.zzbfv.get()), new zzkw<zzfv>() {
                    public void zza(final zzfv zzfv) {
                        zzfv.zza(zzir.this.zzbkj, zzir.this.zzbkj, zzir.this.zzbkj, zzir.this.zzbkj, false, null, null, null, null);
                    }
                }, new zzfy.zzb<zzfv>());
                zzir.zzcdp = true;
            }
        }
    }
    
    private void zzrh() {
        this.zzcds = new zzfy.zze(this.zzrm().zzc(this.zzbkp));
    }
    
    private void zzri() {
        this.zzcdr = new zzfw();
    }
    
    private void zzrj() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        (this.zzcdt = this.zzrk().zza(this.mContext, this.zzcck.zzcix.zzaqv, zza(this.zzcck, zzdi.zzbfv.get()), this.zzbkp, this.zzbkj.zzdp()).get(zzir.zzcdo, TimeUnit.MILLISECONDS)).zza(this.zzbkj, this.zzbkj, this.zzbkj, this.zzbkj, false, null, null, null, null);
    }
    
    public void zza(final zza zza) {
        if (this.zzcdu) {
            final zzfy.zze zzrn = this.zzrn();
            if (zzrn == null) {
                zzb.zzdf("SharedJavascriptEngine not initialized");
            }
            else {
                zzrn.zza(new zzlm.zzc<zzfz>() {
                    public void zzb(final zzfz zzfz) {
                        zza.zze(zzfz);
                    }
                }, new zzlm.zza() {
                    @Override
                    public void run() {
                        zza.zzro();
                    }
                });
            }
        }
        else {
            final zzfv zzrl = this.zzrl();
            if (zzrl == null) {
                zzb.zzdf("JavascriptEngine not initialized");
            }
            else {
                zza.zze(zzrl);
            }
        }
    }
    
    public void zzre() {
        if (this.zzcdu) {
            this.zzrg();
        }
        else {
            this.zzri();
        }
    }
    
    public void zzrf() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        if (this.zzcdu) {
            this.zzrh();
        }
        else {
            this.zzrj();
        }
    }
    
    protected zzfw zzrk() {
        return this.zzcdr;
    }
    
    protected zzfv zzrl() {
        return this.zzcdt;
    }
    
    protected zzfy zzrm() {
        return zzir.zzcdq;
    }
    
    protected zzfy.zze zzrn() {
        return this.zzcds;
    }
    
    public abstract static class zza
    {
        public abstract void zze(final zzfz p0);
        
        public void zzro() {
        }
    }
}
