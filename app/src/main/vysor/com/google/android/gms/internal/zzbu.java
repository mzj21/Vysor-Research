// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.List;
import java.lang.reflect.Method;

public class zzbu
{
    protected static final String TAG;
    private final String className;
    private final zzbb zzafz;
    private final String zzaix;
    private final int zzaiy;
    private volatile Method zzaiz;
    private List<Class> zzaja;
    private CountDownLatch zzajb;
    
    static {
        TAG = zzbu.class.getSimpleName();
    }
    
    public zzbu(final zzbb zzafz, final String className, final String zzaix, final List<Class> list) {
        this.zzaiy = 2;
        this.zzaiz = null;
        this.zzajb = new CountDownLatch(1);
        this.zzafz = zzafz;
        this.className = className;
        this.zzaix = zzaix;
        this.zzaja = new ArrayList<Class>(list);
        this.zzafz.zzch().submit(new Runnable() {
            @Override
            public void run() {
                zzbu.this.zzdc();
            }
        });
    }
    
    private String zzd(final byte[] array, final String s) throws zzaw.zza, UnsupportedEncodingException {
        return new String(this.zzafz.zzcj().zzc(array, s), "UTF-8");
    }
    
    private void zzdc() {
        try {
            final Class loadClass = this.zzafz.zzci().loadClass(this.zzd(this.zzafz.zzck(), this.className));
            if (loadClass != null) {
                this.zzaiz = loadClass.getMethod(this.zzd(this.zzafz.zzck(), this.zzaix), (Class[])this.zzaja.toArray(new Class[this.zzaja.size()]));
                if (this.zzaiz == null) {
                    this.zzajb.countDown();
                }
                else {
                    this.zzajb.countDown();
                }
            }
        }
        catch (zzaw.zza zza) {
            this.zzajb.countDown();
        }
        catch (UnsupportedEncodingException ex) {
            this.zzajb.countDown();
        }
        catch (ClassNotFoundException ex2) {
            this.zzajb.countDown();
        }
        catch (NoSuchMethodException ex3) {
            this.zzajb.countDown();
        }
        catch (NullPointerException ex4) {
            this.zzajb.countDown();
        }
        finally {
            this.zzajb.countDown();
        }
    }
    
    public Method zzdd() {
        Method method = null;
        if (this.zzaiz != null) {
            method = this.zzaiz;
        }
        else {
            try {
                final boolean await = this.zzajb.await(2L, TimeUnit.SECONDS);
                method = null;
                if (await) {
                    method = this.zzaiz;
                }
            }
            catch (InterruptedException ex) {}
        }
        return method;
    }
}
