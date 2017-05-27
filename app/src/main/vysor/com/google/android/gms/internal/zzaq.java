// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import android.view.View;
import android.content.Context;
import android.util.DisplayMetrics;
import java.util.LinkedList;
import android.view.MotionEvent;

public abstract class zzaq implements zzap
{
    protected MotionEvent zzage;
    protected LinkedList<MotionEvent> zzagf;
    protected long zzagg;
    protected long zzagh;
    protected long zzagi;
    protected long zzagj;
    protected long zzagk;
    protected long zzagl;
    protected long zzagm;
    private boolean zzagn;
    protected boolean zzago;
    protected DisplayMetrics zzagp;
    
    protected zzaq(final Context context) {
        this.zzagf = new LinkedList<MotionEvent>();
        this.zzagg = 0L;
        this.zzagh = 0L;
        this.zzagi = 0L;
        this.zzagj = 0L;
        this.zzagk = 0L;
        this.zzagl = 0L;
        this.zzagm = 0L;
        this.zzagn = false;
        this.zzago = false;
        try {
            zzam.zzas();
            this.zzagp = context.getResources().getDisplayMetrics();
        }
        catch (Throwable t) {}
    }
    
    private String zza(final Context context, final String s, final boolean b, final View view) {
        boolean b2 = true;
        while (true) {
            if (b) {
                String s2;
                try {
                    zzae.zza zza = this.zza(context, view);
                    this.zzagn = true;
                    while (true) {
                        if (zza == null || zza.db() == 0) {
                            s2 = Integer.toString(5);
                            return s2;
                        }
                        if (zzb(b)) {
                            b2 = false;
                        }
                        s2 = zzam.zza(zza, s, b2);
                        return s2;
                        zza = this.zzc(context);
                        continue;
                    }
                }
                catch (NoSuchAlgorithmException ex) {
                    s2 = Integer.toString(7);
                }
                catch (UnsupportedEncodingException ex2) {
                    s2 = Integer.toString(7);
                }
                catch (Throwable t) {
                    s2 = Integer.toString(3);
                }
                return s2;
            }
            continue;
        }
    }
    
    private static boolean zza(final zzbc zzbc) {
        return zzbc != null && zzbc.zzfb != null && zzbc.zzaik != null;
    }
    
    private boolean zzb(final zzbc zzbc) {
        return this.zzagp != null && zzbc != null && zzbc.zzez != null && zzbc.zzail != null;
    }
    
    private static boolean zzb(final boolean b) {
        return !zzdi.zzbep.get() || (zzdi.zzbfh.get() && b);
    }
    
    protected abstract long zza(final StackTraceElement[] p0) throws zzay;
    
    protected abstract zzae.zza zza(final Context p0, final View p1);
    
    @Override
    public String zza(final Context context, final String s, final View view) {
        return this.zza(context, s, true, view);
    }
    
    @Override
    public void zza(final int n, final int n2, final int n3) {
        if (this.zzage != null) {
            this.zzage.recycle();
        }
        if (this.zzagp != null) {
            this.zzage = MotionEvent.obtain(0L, (long)n3, 1, n * this.zzagp.density, n2 * this.zzagp.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        }
        else {
            this.zzage = null;
        }
        this.zzago = false;
    }
    
    @Override
    public void zza(final MotionEvent motionEvent) {
        if (this.zzagn) {
            this.zzagj = 0L;
            this.zzagi = 0L;
            this.zzagh = 0L;
            this.zzagg = 0L;
            this.zzagk = 0L;
            this.zzagm = 0L;
            this.zzagl = 0L;
            final Iterator<MotionEvent> iterator = this.zzagf.iterator();
            while (iterator.hasNext()) {
                iterator.next().recycle();
            }
            this.zzagf.clear();
            this.zzage = null;
            this.zzagn = false;
        }
        switch (motionEvent.getAction()) {
            case 1: {
                this.zzage = MotionEvent.obtain(motionEvent);
                this.zzagf.add(this.zzage);
                if (this.zzagf.size() > 6) {
                    this.zzagf.remove().recycle();
                }
                ++this.zzagi;
                try {
                    this.zzagk = this.zza(new Throwable().getStackTrace());
                }
                catch (zzay zzay) {}
                break;
            }
            case 0: {
                ++this.zzagg;
                break;
            }
            case 3: {
                ++this.zzagj;
                break;
            }
            case 2: {
                this.zzagh += 1 + motionEvent.getHistorySize();
                if (!zzdi.zzbex.get()) {
                    if (!zzdi.zzbes.get()) {
                        break;
                    }
                }
                try {
                    final zzbc zzb = this.zzb(motionEvent);
                    if (zza(zzb)) {
                        this.zzagl += zzb.zzfb + zzb.zzaik;
                    }
                    if (this.zzb(zzb)) {
                        this.zzagm += zzb.zzez + zzb.zzail;
                    }
                }
                catch (zzay zzay2) {}
                break;
            }
        }
        this.zzago = true;
    }
    
    protected abstract zzbc zzb(final MotionEvent p0) throws zzay;
    
    @Override
    public String zzb(final Context context) {
        return this.zza(context, null, false, null);
    }
    
    public String zzb(final Context context, final String s) {
        return this.zza(context, s, null);
    }
    
    protected abstract zzae.zza zzc(final Context p0);
}
