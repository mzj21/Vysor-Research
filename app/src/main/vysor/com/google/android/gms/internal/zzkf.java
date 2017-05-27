// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.AdRequestParcel;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.Bundle;
import com.google.android.gms.ads.internal.zzu;
import java.util.LinkedList;

@zziy
public class zzkf
{
    private final Object zzakd;
    private final zzkh zzapw;
    private boolean zzcko;
    private final LinkedList<zza> zzcoq;
    private final String zzcor;
    private final String zzcos;
    private long zzcot;
    private long zzcou;
    private long zzcov;
    private long zzcow;
    private long zzcox;
    private long zzcoy;
    
    public zzkf(final zzkh zzapw, final String zzcor, final String zzcos) {
        this.zzakd = new Object();
        this.zzcot = -1L;
        this.zzcou = -1L;
        this.zzcko = false;
        this.zzcov = -1L;
        this.zzcow = 0L;
        this.zzcox = -1L;
        this.zzcoy = -1L;
        this.zzapw = zzapw;
        this.zzcor = zzcor;
        this.zzcos = zzcos;
        this.zzcoq = new LinkedList<zza>();
    }
    
    public zzkf(final String s, final String s2) {
        this(zzu.zzgd(), s, s2);
    }
    
    public Bundle toBundle() {
        final Bundle bundle;
        final ArrayList<Bundle> list;
        synchronized (this.zzakd) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.zzcor);
            bundle.putString("slotid", this.zzcos);
            bundle.putBoolean("ismediation", this.zzcko);
            bundle.putLong("treq", this.zzcox);
            bundle.putLong("tresponse", this.zzcoy);
            bundle.putLong("timp", this.zzcou);
            bundle.putLong("tload", this.zzcov);
            bundle.putLong("pcc", this.zzcow);
            bundle.putLong("tfetch", this.zzcot);
            list = new ArrayList<Bundle>();
            final Iterator<zza> iterator = this.zzcoq.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().toBundle());
            }
        }
        bundle.putParcelableArrayList("tclick", (ArrayList)list);
        // monitorexit(o)
        return bundle;
    }
    
    public void zzad(final boolean b) {
        synchronized (this.zzakd) {
            if (this.zzcoy != -1L) {
                this.zzcov = SystemClock.elapsedRealtime();
                if (!b) {
                    this.zzcou = this.zzcov;
                    this.zzapw.zza(this);
                }
            }
        }
    }
    
    public void zzae(final boolean zzcko) {
        synchronized (this.zzakd) {
            if (this.zzcoy != -1L) {
                this.zzcko = zzcko;
                this.zzapw.zza(this);
            }
        }
    }
    
    public void zzm(final long zzcoy) {
        synchronized (this.zzakd) {
            this.zzcoy = zzcoy;
            if (this.zzcoy != -1L) {
                this.zzapw.zza(this);
            }
        }
    }
    
    public void zzn(final long zzcot) {
        synchronized (this.zzakd) {
            if (this.zzcoy != -1L) {
                this.zzcot = zzcot;
                this.zzapw.zza(this);
            }
        }
    }
    
    public void zzsz() {
        synchronized (this.zzakd) {
            if (this.zzcoy != -1L && this.zzcou == -1L) {
                this.zzcou = SystemClock.elapsedRealtime();
                this.zzapw.zza(this);
            }
            this.zzapw.zztl().zzsz();
        }
    }
    
    public void zzt(final AdRequestParcel adRequestParcel) {
        synchronized (this.zzakd) {
            this.zzcox = SystemClock.elapsedRealtime();
            this.zzapw.zztl().zzb(adRequestParcel, this.zzcox);
        }
    }
    
    public void zzta() {
        synchronized (this.zzakd) {
            if (this.zzcoy != -1L) {
                final zza zza = new zza();
                zza.zzte();
                this.zzcoq.add(zza);
                ++this.zzcow;
                this.zzapw.zztl().zzta();
                this.zzapw.zza(this);
            }
        }
    }
    
    public void zztb() {
        synchronized (this.zzakd) {
            if (this.zzcoy != -1L && !this.zzcoq.isEmpty()) {
                final zza zza = this.zzcoq.getLast();
                if (zza.zztc() == -1L) {
                    zza.zztd();
                    this.zzapw.zza(this);
                }
            }
        }
    }
    
    @zziy
    private static final class zza
    {
        private long zzcoz;
        private long zzcpa;
        
        public zza() {
            this.zzcoz = -1L;
            this.zzcpa = -1L;
        }
        
        public Bundle toBundle() {
            final Bundle bundle = new Bundle();
            bundle.putLong("topen", this.zzcoz);
            bundle.putLong("tclose", this.zzcpa);
            return bundle;
        }
        
        public long zztc() {
            return this.zzcpa;
        }
        
        public void zztd() {
            this.zzcpa = SystemClock.elapsedRealtime();
        }
        
        public void zzte() {
            this.zzcoz = SystemClock.elapsedRealtime();
        }
    }
}
