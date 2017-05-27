// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

@zziy
public class zzln<T> implements zzlm<T>
{
    private final Object zzakd;
    protected int zzbqm;
    protected final BlockingQueue<zza> zzcuk;
    protected T zzcul;
    
    public zzln() {
        this.zzakd = new Object();
        this.zzbqm = 0;
        this.zzcuk = new LinkedBlockingQueue<zza>();
    }
    
    public int getStatus() {
        return this.zzbqm;
    }
    
    public void reject() {
        synchronized (this.zzakd) {
            if (this.zzbqm != 0) {
                throw new UnsupportedOperationException();
            }
        }
        this.zzbqm = -1;
        final Iterator<zza> iterator = this.zzcuk.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzcun.run();
        }
        this.zzcuk.clear();
    }
    // monitorexit(o)
    
    @Override
    public void zza(final zzc<T> zzc, final zzlm.zza zza) {
        while (true) {
            Label_0052: {
                synchronized (this.zzakd) {
                    if (this.zzbqm == 1) {
                        zzc.zzd(this.zzcul);
                    }
                    else {
                        if (this.zzbqm != -1) {
                            break Label_0052;
                        }
                        zza.run();
                    }
                    return;
                }
            }
            if (this.zzbqm == 0) {
                this.zzcuk.add(new zza(zzc, zza));
            }
        }
    }
    
    @Override
    public void zzg(final T zzcul) {
        synchronized (this.zzakd) {
            if (this.zzbqm != 0) {
                throw new UnsupportedOperationException();
            }
        }
        this.zzcul = zzcul;
        this.zzbqm = 1;
        final Iterator<zza> iterator = this.zzcuk.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzcum.zzd(zzcul);
        }
        this.zzcuk.clear();
    }
    // monitorexit(o)
    
    class zza
    {
        public final zzc<T> zzcum;
        public final zzlm.zza zzcun;
        
        public zza(final zzc<T> zzcum, final zzlm.zza zzcun) {
            this.zzcum = zzcum;
            this.zzcun = zzcun;
        }
    }
}
