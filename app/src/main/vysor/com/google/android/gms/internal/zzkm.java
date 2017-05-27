// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zziy
public abstract class zzkm implements zzkt<Future>
{
    private volatile Thread zzcql;
    private boolean zzcqm;
    private final Runnable zzw;
    
    public zzkm() {
        this.zzw = new Runnable() {
            @Override
            public final void run() {
                zzkm.this.zzcql = Thread.currentThread();
                zzkm.this.zzfc();
            }
        };
        this.zzcqm = false;
    }
    
    public zzkm(final boolean zzcqm) {
        this.zzw = new Runnable() {
            @Override
            public final void run() {
                zzkm.this.zzcql = Thread.currentThread();
                zzkm.this.zzfc();
            }
        };
        this.zzcqm = zzcqm;
    }
    
    @Override
    public final void cancel() {
        this.onStop();
        if (this.zzcql != null) {
            this.zzcql.interrupt();
        }
    }
    
    public abstract void onStop();
    
    public abstract void zzfc();
    
    public final Future zzuc() {
        zzlj<Void> zzlj;
        if (this.zzcqm) {
            zzlj = zzkq.zza(1, this.zzw);
        }
        else {
            zzlj = zzkq.zza(this.zzw);
        }
        return zzlj;
    }
}
