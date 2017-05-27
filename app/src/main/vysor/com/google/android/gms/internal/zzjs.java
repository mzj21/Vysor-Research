// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zze;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzu;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import android.content.Context;

@zziy
public class zzjs extends zzkm implements zzju, zzjx
{
    private final Context mContext;
    private final Object zzakd;
    private final String zzbst;
    private final zzke.zza zzcck;
    private int zzcdb;
    private final zzjz zzcna;
    private final zzjx zzcnb;
    private final String zzcnc;
    private final zzgg zzcnd;
    private final long zzcne;
    private int zzcnf;
    private zzjt zzcng;
    
    public zzjs(final Context mContext, final String zzbst, final String zzcnc, final zzgg zzcnd, final zzke.zza zzcck, final zzjz zzcna, final zzjx zzcnb, final long zzcne) {
        this.zzcnf = 0;
        this.zzcdb = 3;
        this.mContext = mContext;
        this.zzbst = zzbst;
        this.zzcnc = zzcnc;
        this.zzcnd = zzcnd;
        this.zzcck = zzcck;
        this.zzcna = zzcna;
        this.zzakd = new Object();
        this.zzcnb = zzcnb;
        this.zzcne = zzcne;
    }
    
    private void zza(final AdRequestParcel adRequestParcel, final zzgr zzgr) {
        this.zzcna.zzsw().zza((zzjx)this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbst)) {
                zzgr.zza(adRequestParcel, this.zzcnc, this.zzcnd.zzbrl);
            }
            else {
                zzgr.zzc(adRequestParcel, this.zzcnc);
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Fail to load ad from adapter.", (Throwable)ex);
            this.zza(this.zzbst, 0);
        }
    }
    
    private void zzk(final long n) {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzcnf != 0) {
                    final zzjt.zza zzl = new zzjt.zza().zzl(zzu.zzgf().elapsedRealtime() - n);
                    int zzcdb;
                    if (1 == this.zzcnf) {
                        zzcdb = 6;
                    }
                    else {
                        zzcdb = this.zzcdb;
                    }
                    this.zzcng = zzl.zzaz(zzcdb).zzcn(this.zzbst).zzco(this.zzcnd.zzbro).zzss();
                    break;
                }
                if (!this.zzf(n)) {
                    this.zzcng = new zzjt.zza().zzaz(this.zzcdb).zzl(zzu.zzgf().elapsedRealtime() - n).zzcn(this.zzbst).zzco(this.zzcnd.zzbro).zzss();
                    break;
                }
            }
        }
        // monitorexit(o)
    }
    
    @Override
    public void onStop() {
    }
    
    @Override
    public void zza(final String s, final int zzcdb) {
        synchronized (this.zzakd) {
            this.zzcnf = 2;
            this.zzcdb = zzcdb;
            this.zzakd.notify();
        }
    }
    
    @Override
    public void zzay(final int n) {
        this.zza(this.zzbst, 0);
    }
    
    @Override
    public void zzcm(final String s) {
        synchronized (this.zzakd) {
            this.zzcnf = 1;
            this.zzakd.notify();
        }
    }
    
    protected boolean zzf(final long n) {
        boolean b = false;
        final long n2 = this.zzcne - (zzu.zzgf().elapsedRealtime() - n);
        if (n2 <= 0L) {
            this.zzcdb = 4;
        }
        else {
            try {
                this.zzakd.wait(n2);
                b = true;
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                this.zzcdb = 5;
                b = false;
            }
        }
        return b;
    }
    
    @Override
    public void zzfc() {
        if (this.zzcna != null && this.zzcna.zzsw() != null && this.zzcna.zzsv() != null) {
            final zzjw zzsw = this.zzcna.zzsw();
            zzsw.zza((zzjx)null);
            zzsw.zza((zzju)this);
            final AdRequestParcel zzcfu = this.zzcck.zzcix.zzcfu;
            final zzgr zzsv = this.zzcna.zzsv();
        Label_0179:
            while (true) {
                try {
                    if (zzsv.isInitialized()) {
                        zza.zzctj.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                zzjs.this.zza(zzcfu, zzsv);
                            }
                        });
                    }
                    else {
                        zza.zzctj.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    zzsv.zza(zze.zzac(zzjs.this.mContext), zzcfu, null, zzsw, zzjs.this.zzcnc);
                                }
                                catch (RemoteException ex) {
                                    final String value = String.valueOf(zzjs.this.zzbst);
                                    String concat;
                                    if (value.length() != 0) {
                                        concat = "Fail to initialize adapter ".concat(value);
                                    }
                                    else {
                                        concat = new String("Fail to initialize adapter ");
                                    }
                                    zzb.zzd(concat, (Throwable)ex);
                                    zzjs.this.zza(zzjs.this.zzbst, 0);
                                }
                            }
                        });
                    }
                    this.zzk(zzu.zzgf().elapsedRealtime());
                    zzsw.zza((zzjx)null);
                    zzsw.zza((zzju)null);
                    if (this.zzcnf == 1) {
                        this.zzcnb.zzcm(this.zzbst);
                        return;
                    }
                    break Label_0179;
                }
                catch (RemoteException ex) {
                    zzb.zzd("Fail to check if adapter is initialized.", (Throwable)ex);
                    this.zza(this.zzbst, 0);
                    continue;
                }
                continue;
            }
            this.zzcnb.zza(this.zzbst, this.zzcdb);
        }
    }
    
    public zzjt zzsp() {
        synchronized (this.zzakd) {
            return this.zzcng;
        }
    }
    
    public zzgg zzsq() {
        return this.zzcnd;
    }
    
    @Override
    public void zzsr() {
        this.zza(this.zzcck.zzcix.zzcfu, this.zzcna.zzsv());
    }
}
