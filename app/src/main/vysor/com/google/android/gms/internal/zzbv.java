// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzbv implements Callable
{
    protected final String TAG;
    protected final String className;
    protected final zzbb zzafz;
    protected final zzae.zza zzair;
    protected final String zzaix;
    protected Method zzaiz;
    protected final int zzajd;
    protected final int zzaje;
    
    public zzbv(final zzbb zzafz, final String className, final String zzaix, final zzae.zza zzair, final int zzajd, final int zzaje) {
        this.TAG = this.getClass().getSimpleName();
        this.zzafz = zzafz;
        this.className = className;
        this.zzaix = zzaix;
        this.zzair = zzair;
        this.zzajd = zzajd;
        this.zzaje = zzaje;
    }
    
    protected abstract void zzcy() throws IllegalAccessException, InvocationTargetException;
    
    public Void zzdb() throws Exception {
        try {
            final long nanoTime = System.nanoTime();
            this.zzaiz = this.zzafz.zzc(this.className, this.zzaix);
            if (this.zzaiz != null) {
                this.zzcy();
                final zzao zzco = this.zzafz.zzco();
                if (zzco != null && this.zzajd != Integer.MIN_VALUE) {
                    zzco.zza(this.zzaje, this.zzajd, (System.nanoTime() - nanoTime) / 1000L);
                }
            }
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {}
        return null;
    }
}
