// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import android.util.Log;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import android.text.TextUtils;
import android.view.View;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Calendar;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.content.Context;

public abstract class zzas extends zzaq
{
    private static final String TAG;
    private static long startTime;
    protected static volatile zzbb zzafz;
    static boolean zzagt;
    protected static final Object zzagw;
    protected boolean zzagr;
    protected String zzags;
    protected boolean zzagu;
    protected boolean zzagv;
    
    static {
        TAG = zzas.class.getSimpleName();
        zzas.startTime = 0L;
        zzas.zzagt = false;
        zzas.zzafz = null;
        zzagw = new Object();
    }
    
    protected zzas(final Context context, final String zzags) {
        super(context);
        this.zzagr = false;
        this.zzagu = false;
        this.zzagv = false;
        this.zzags = zzags;
        this.zzagr = false;
    }
    
    protected zzas(final Context context, final String zzags, final boolean zzagr) {
        super(context);
        this.zzagr = false;
        this.zzagu = false;
        this.zzagv = false;
        this.zzags = zzags;
        this.zzagr = zzagr;
    }
    
    static zzbc zza(final zzbb zzbb, final MotionEvent motionEvent, final DisplayMetrics displayMetrics) throws zzay {
        final Method zzc = zzbb.zzc(zzax.zzcd(), zzax.zzce());
        if (zzc == null || motionEvent == null) {
            throw new zzay();
        }
        try {
            return new zzbc((String)zzc.invoke(null, motionEvent, displayMetrics));
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {
            goto Label_0061;
        }
    }
    
    protected static void zza(final Context context, final boolean b) {
        synchronized (zzas.class) {
            if (!zzas.zzagt) {
                zzas.startTime = Calendar.getInstance().getTime().getTime() / 1000L;
                zzas.zzafz = zzb(context, b);
                zzas.zzagt = true;
            }
        }
    }
    
    private static void zza(final zzbb zzbb) {
        final List<Class<Context>> singletonList = Collections.singletonList(Context.class);
        zzbb.zza(zzax.zzbj(), zzax.zzbk(), (List<Class>)singletonList);
        zzbb.zza(zzax.zzbt(), zzax.zzbu(), (List<Class>)singletonList);
        zzbb.zza(zzax.zzbr(), zzax.zzbs(), (List<Class>)singletonList);
        zzbb.zza(zzax.zzbd(), zzax.zzbe(), (List<Class>)singletonList);
        zzbb.zza(zzax.zzbn(), zzax.zzbo(), (List<Class>)singletonList);
        zzbb.zza(zzax.zzaz(), zzax.zzba(), (List<Class>)singletonList);
        zzbb.zza(zzax.zzcf(), zzax.zzcg(), (List<Class>)singletonList);
        final List<Class> list = Arrays.asList(MotionEvent.class, DisplayMetrics.class);
        zzbb.zza(zzax.zzcd(), zzax.zzce(), (List<Class>)list);
        zzbb.zza(zzax.zzcb(), zzax.zzcc(), (List<Class>)list);
        zzbb.zza(zzax.zzbh(), zzax.zzbi(), (List<Class>)Collections.emptyList());
        zzbb.zza(zzax.zzbz(), zzax.zzca(), (List<Class>)Collections.emptyList());
        zzbb.zza(zzax.zzbp(), zzax.zzbq(), (List<Class>)Collections.emptyList());
        zzbb.zza(zzax.zzbf(), zzax.zzbg(), (List<Class>)Collections.emptyList());
        zzbb.zza(zzax.zzbl(), zzax.zzbm(), (List<Class>)Collections.emptyList());
        zzbb.zza(zzax.zzbx(), zzax.zzby(), (List<Class>)Collections.emptyList());
        zzbb.zza(zzax.zzbb(), zzax.zzbc(), (List<Class>)Arrays.asList(Context.class, Boolean.TYPE));
        zzbb.zza(zzax.zzbv(), zzax.zzbw(), (List<Class>)Arrays.asList(StackTraceElement[].class));
    }
    
    protected static zzbb zzb(final Context context, final boolean b) {
        Label_0043: {
            if (zzas.zzafz != null) {
                break Label_0043;
            }
            synchronized (zzas.zzagw) {
                if (zzas.zzafz == null) {
                    final zzbb zza = zzbb.zza(context, zzax.getKey(), zzax.zzay(), b);
                    zza(zza);
                    zzas.zzafz = zza;
                }
                return zzas.zzafz;
            }
        }
    }
    
    private void zzd(final zzbb zzbb, final zzae.zza zza) {
        int i = 0;
        while (true) {
            try {
                final zzbc zza2 = zza(zzbb, this.zzage, this.zzagp);
                zza.zzdf = zza2.zzaig;
                zza.zzdg = zza2.zzaih;
                zza.zzdh = zza2.zzaii;
                if (this.zzago) {
                    zza.zzdv = zza2.zzfb;
                    zza.zzdw = zza2.zzez;
                }
                if (!zzdi.zzbex.get() && !zzdi.zzbes.get()) {
                    break Label_0337;
                }
                final zzae.zza.zza zzeo = new zzae.zza.zza();
                final zzbc zzb = this.zzb(this.zzage);
                zzeo.zzdf = zzb.zzaig;
                zzeo.zzdg = zzb.zzaih;
                zzeo.zzfe = zzb.zzaii;
            Label_0235_Outer:
                while (true) {
                Label_0300_Outer:
                    while (true) {
                        Label_0524: {
                            while (true) {
                                Label_0331: {
                                    if (!this.zzago) {
                                        break Label_0331;
                                    }
                                    zzeo.zzez = zzb.zzez;
                                    zzeo.zzfb = zzb.zzfb;
                                    if (zzb.zzaij == 0L) {
                                        break Label_0235_Outer;
                                    }
                                    final int n = 1;
                                    zzeo.zzfd = n;
                                    Label_0264: {
                                        if (this.zzagh <= 0L) {
                                            break Label_0264;
                                        }
                                        if (this.zzagp == null) {
                                            break Label_0300_Outer;
                                        }
                                        final Long value = Math.round(this.zzagm / this.zzagh);
                                        zzeo.zzfa = value;
                                        zzeo.zzfc = Math.round(this.zzagl / this.zzagh);
                                    }
                                    zzeo.zzfg = zzb.zzfg;
                                    zzeo.zzff = zzb.zzff;
                                    if (zzb.zzaim == 0L) {
                                        break Label_0524;
                                    }
                                    final int n2 = 1;
                                    zzeo.zzfh = n2;
                                    if (this.zzagk > 0L) {
                                        zzeo.zzfi = this.zzagk;
                                    }
                                }
                                zza.zzeo = zzeo;
                                if (this.zzagg > 0L) {
                                    zza.zzea = this.zzagg;
                                }
                                if (this.zzagh > 0L) {
                                    zza.zzdz = this.zzagh;
                                }
                                if (this.zzagi > 0L) {
                                    zza.zzdy = this.zzagi;
                                }
                                if (this.zzagj > 0L) {
                                    zza.zzeb = this.zzagj;
                                }
                                try {
                                    final int n3 = -1 + this.zzagf.size();
                                    if (n3 > 0) {
                                        zza.zzep = new zzae.zza.zza[n3];
                                        while (i < n3) {
                                            final zzbc zza3 = zza(zzbb, this.zzagf.get(i), this.zzagp);
                                            final zzae.zza.zza zza4 = new zzae.zza.zza();
                                            zza4.zzdf = zza3.zzaig;
                                            zza4.zzdg = zza3.zzaih;
                                            zza.zzep[i] = zza4;
                                            ++i;
                                        }
                                        return;
                                    }
                                    return;
                                    final int n2 = 0;
                                    continue;
                                    final int n = 0;
                                    continue Label_0235_Outer;
                                    final Long value = null;
                                    continue Label_0300_Outer;
                                }
                                catch (zzay zzay) {
                                    zza.zzep = null;
                                }
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
            }
            catch (zzay zzay2) {
                continue;
            }
            break;
        }
    }
    
    @Override
    protected long zza(final StackTraceElement[] array) throws zzay {
        final Method zzc = zzas.zzafz.zzc(zzax.zzbv(), zzax.zzbw());
        if (zzc == null || array == null) {
            throw new zzay();
        }
        try {
            return new zzaz((String)zzc.invoke(null, array)).zzahi;
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {
            goto Label_0064;
        }
    }
    
    @Override
    protected zzae.zza zza(final Context context, final View view) {
        final zzae.zza zza = new zzae.zza();
        if (!TextUtils.isEmpty((CharSequence)this.zzags)) {
            zza.zzcs = this.zzags;
        }
        final zzbb zzb = zzb(context, this.zzagr);
        zzb.zzcw();
        this.zza(zzb, zza, view);
        zzb.zzcx();
        return zza;
    }
    
    protected void zza(final zzbb zzbb, final zzae.zza zza) {
        if (zzbb.zzch() != null) {
            this.zza(this.zzb(zzbb, zza));
        }
    }
    
    protected void zza(final zzbb zzbb, final zzae.zza zza, final View view) {
        this.zzd(zzbb, zza);
        this.zza(this.zzc(zzbb, zza));
    }
    
    protected void zza(final List<Callable<Void>> list) {
        if (zzas.zzafz != null) {
            final ExecutorService zzch = zzas.zzafz.zzch();
            if (zzch != null && !list.isEmpty()) {
                try {
                    zzch.invokeAll((Collection<? extends Callable<Object>>)list, (long)zzdi.zzben.get(), TimeUnit.MILLISECONDS);
                }
                catch (InterruptedException ex) {
                    Log.d(zzas.TAG, String.format("class methods got exception: %s", zzbd.zza(ex)));
                }
            }
        }
    }
    
    @Override
    protected zzbc zzb(final MotionEvent motionEvent) throws zzay {
        final Method zzc = zzas.zzafz.zzc(zzax.zzcb(), zzax.zzcc());
        if (zzc == null || motionEvent == null) {
            throw new zzay();
        }
        try {
            return new zzbc((String)zzc.invoke(null, motionEvent, this.zzagp));
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {
            goto Label_0071;
        }
    }
    
    protected List<Callable<Void>> zzb(final zzbb zzbb, final zzae.zza zza) {
        final int zzau = zzbb.zzau();
        final ArrayList<zzbg> list = (ArrayList<zzbg>)new ArrayList<Callable<Void>>();
        list.add((Callable<Void>)new zzbg(zzbb, zzax.zzbb(), zzax.zzbc(), zza, zzau, 27, zzdi.zzber.get() || zzdi.zzbes.get()));
        list.add((Callable<Void>)new zzbj(zzbb, zzax.zzbh(), zzax.zzbi(), zza, zzas.startTime, zzau, 25));
        list.add((Callable<Void>)new zzbo(zzbb, zzax.zzbp(), zzax.zzbq(), zza, zzau, 1));
        list.add((Callable<Void>)new zzbp(zzbb, zzax.zzbr(), zzax.zzbs(), zza, zzau, 31));
        list.add((Callable<Void>)new zzbs(zzbb, zzax.zzbz(), zzax.zzca(), zza, zzau, 33));
        list.add((Callable<Void>)new zzbf(zzbb, zzax.zzbt(), zzax.zzbu(), zza, zzau, 29));
        list.add((Callable<Void>)new zzbh(zzbb, zzax.zzbd(), zzax.zzbe(), zza, zzau, 5));
        list.add((Callable<Void>)new zzbn(zzbb, zzax.zzbn(), zzax.zzbo(), zza, zzau, 12));
        list.add((Callable<Void>)new zzbe(zzbb, zzax.zzaz(), zzax.zzba(), zza, zzau, 3));
        list.add((Callable<Void>)new zzbi(zzbb, zzax.zzbf(), zzax.zzbg(), zza, zzau, 44));
        list.add((Callable<Void>)new zzbm(zzbb, zzax.zzbl(), zzax.zzbm(), zza, zzau, 22));
        if (zzdi.zzbeu.get() || zzdi.zzbes.get()) {
            list.add((Callable<Void>)new zzbt(zzbb, zzax.zzcf(), zzax.zzcg(), zza, zzau, 48));
        }
        if (zzdi.zzbez.get() || zzdi.zzbes.get()) {
            list.add((Callable<Void>)new zzbr(zzbb, zzax.zzbx(), zzax.zzby(), zza, zzau, 51));
        }
        return (List<Callable<Void>>)list;
    }
    
    @Override
    protected zzae.zza zzc(final Context context) {
        final zzae.zza zza = new zzae.zza();
        if (!TextUtils.isEmpty((CharSequence)this.zzags)) {
            zza.zzcs = this.zzags;
        }
        final zzbb zzb = zzb(context, this.zzagr);
        zzb.zzcw();
        this.zza(zzb, zza);
        zzb.zzcx();
        return zza;
    }
    
    protected List<Callable<Void>> zzc(final zzbb zzbb, final zzae.zza zza) {
        final ArrayList<zzbl> list = new ArrayList<zzbl>();
        ArrayList<Callable<Void>> list2;
        if (zzbb.zzch() == null) {
            list2 = (ArrayList<Callable<Void>>)list;
        }
        else {
            final int zzau = zzbb.zzau();
            list.add(new zzbl(zzbb, zza));
            list.add((zzbl)new zzbo(zzbb, zzax.zzbp(), zzax.zzbq(), zza, zzau, 1));
            list.add((zzbl)new zzbj(zzbb, zzax.zzbh(), zzax.zzbi(), zza, zzas.startTime, zzau, 25));
            list.add((zzbl)new zzbi(zzbb, zzax.zzbf(), zzax.zzbg(), zza, zzau, 44));
            list.add((zzbl)new zzbe(zzbb, zzax.zzaz(), zzax.zzba(), zza, zzau, 3));
            list.add((zzbl)new zzbm(zzbb, zzax.zzbl(), zzax.zzbm(), zza, zzau, 22));
            if (zzdi.zzbfd.get() || zzdi.zzbes.get()) {
                list.add((zzbl)new zzbh(zzbb, zzax.zzbd(), zzax.zzbe(), zza, zzau, 5));
            }
            if (zzdi.zzbew.get() || zzdi.zzbes.get()) {
                list.add((zzbl)new zzbt(zzbb, zzax.zzcf(), zzax.zzcg(), zza, zzau, 48));
            }
            if (zzdi.zzbfb.get() || zzdi.zzbes.get()) {
                list.add((zzbl)new zzbr(zzbb, zzax.zzbx(), zzax.zzby(), zza, zzau, 51));
            }
            if (zzdi.zzbfg.get() || zzdi.zzbes.get()) {
                list.add((zzbl)new zzbq(zzbb, zzax.zzbv(), zzax.zzbw(), zza, zzau, 45, new Throwable().getStackTrace()));
            }
            list2 = (ArrayList<Callable<Void>>)list;
        }
        return list2;
    }
}
