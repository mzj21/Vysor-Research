// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import android.os.Build$VERSION;
import android.net.Uri$Builder;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import android.os.Looper;
import java.util.ArrayList;
import java.util.LinkedList;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;

@zziy
public class zzix implements UncaughtExceptionHandler
{
    private Context mContext;
    private VersionInfoParcel zzaop;
    @Nullable
    private UncaughtExceptionHandler zzcff;
    @Nullable
    private UncaughtExceptionHandler zzcfg;
    
    public zzix(final Context mContext, final VersionInfoParcel zzaop, @Nullable final UncaughtExceptionHandler zzcff, @Nullable final UncaughtExceptionHandler zzcfg) {
        this.zzcff = zzcff;
        this.zzcfg = zzcfg;
        this.mContext = mContext;
        this.zzaop = zzaop;
    }
    
    public static zzix zza(final Context context, final Thread thread, final VersionInfoParcel versionInfoParcel) {
        Object o;
        if (context == null || thread == null || versionInfoParcel == null) {
            o = null;
        }
        else if (!zzu(context)) {
            o = null;
        }
        else {
            final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
            final zzix uncaughtExceptionHandler2 = new zzix(context, versionInfoParcel, uncaughtExceptionHandler, Thread.getDefaultUncaughtExceptionHandler());
            if (uncaughtExceptionHandler != null) {
                if (uncaughtExceptionHandler instanceof zzix) {
                    o = uncaughtExceptionHandler;
                    return (zzix)o;
                }
            }
            try {
                thread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)uncaughtExceptionHandler2);
                o = uncaughtExceptionHandler2;
            }
            catch (SecurityException ex) {
                zzb.zzc("Fail to set UncaughtExceptionHandler.", ex);
                o = null;
            }
        }
        return (zzix)o;
    }
    
    private Throwable zzd(Throwable cause) {
        if (!zzdi.zzbau.get()) {
            final LinkedList<Throwable> list = new LinkedList<Throwable>();
            while (cause != null) {
                list.push((Throwable)cause);
                cause = ((Throwable)cause).getCause();
            }
            Throwable t = null;
            while (!list.isEmpty()) {
                final Throwable t2 = list.pop();
                final StackTraceElement[] stackTrace = t2.getStackTrace();
                final ArrayList<StackTraceElement> list2 = new ArrayList<StackTraceElement>();
                list2.add(new StackTraceElement(t2.getClass().getName(), "<filtered>", "<filtered>", 1));
                final int length = stackTrace.length;
                int i = 0;
                boolean b = false;
                while (i < length) {
                    final StackTraceElement stackTraceElement = stackTrace[i];
                    if (this.zzcg(stackTraceElement.getClassName())) {
                        list2.add(stackTraceElement);
                        b = true;
                    }
                    else if (this.zzch(stackTraceElement.getClassName())) {
                        list2.add(stackTraceElement);
                    }
                    else {
                        list2.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                    }
                    ++i;
                }
                Throwable t3;
                if (b) {
                    if (t == null) {
                        t3 = new Throwable(t2.getMessage());
                    }
                    else {
                        t3 = new Throwable(t2.getMessage(), t);
                    }
                    t3.setStackTrace(list2.toArray(new StackTraceElement[0]));
                }
                else {
                    t3 = t;
                }
                t = t3;
            }
            cause = t;
        }
        return (Throwable)cause;
    }
    
    private static boolean zzu(final Context context) {
        return zzdi.zzbat.get();
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        Label_0031: {
            if (!this.zzb(t)) {
                break Label_0031;
            }
            if (Looper.getMainLooper().getThread() == thread) {
                this.zzc(t);
                break Label_0031;
            }
            this.zza(t, "AdMobExceptionReporter.uncaughtException");
            return;
        }
        if (this.zzcff != null) {
            this.zzcff.uncaughtException(thread, t);
            return;
        }
        if (this.zzcfg != null) {
            this.zzcfg.uncaughtException(thread, t);
        }
    }
    
    String zza(final Class clazz, final Throwable t, final String s) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return new Uri$Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build$VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build$VERSION.SDK_INT)).appendQueryParameter("device", zzu.zzfz().zzuj()).appendQueryParameter("js", this.zzaop.zzcs).appendQueryParameter("appid", this.mContext.getApplicationContext().getPackageName()).appendQueryParameter("exceptiontype", clazz.getName()).appendQueryParameter("stacktrace", stringWriter.toString()).appendQueryParameter("eids", TextUtils.join((CharSequence)",", (Iterable)zzdi.zzkr())).appendQueryParameter("exceptionkey", s).appendQueryParameter("cl", "134102376").appendQueryParameter("rc", "dev").toString();
    }
    
    public void zza(final Throwable t, final String s) {
        if (zzu(this.mContext)) {
            final Throwable zzd = this.zzd(t);
            if (zzd != null) {
                final Class<? extends Throwable> class1 = t.getClass();
                final ArrayList<String> list = new ArrayList<String>();
                list.add(this.zza(class1, zzd, s));
                zzu.zzfz().zza(list, zzu.zzgd().zztp());
            }
        }
    }
    
    protected boolean zzb(Throwable cause) {
        boolean b = true;
        boolean b2 = false;
        if (cause != null) {
            boolean b3 = false;
            boolean b4 = false;
            while (cause != null) {
                for (final StackTraceElement stackTraceElement : cause.getStackTrace()) {
                    if (this.zzcg(stackTraceElement.getClassName())) {
                        b4 = b;
                    }
                    if (this.getClass().getName().equals(stackTraceElement.getClassName())) {
                        b3 = b;
                    }
                }
                cause = cause.getCause();
            }
            if (!b4 || b3) {
                b = false;
            }
            b2 = b;
        }
        return b2;
    }
    
    public void zzc(final Throwable t) {
        this.zza(t, "");
    }
    
    protected boolean zzcg(final String s) {
        boolean annotationPresent;
        if (TextUtils.isEmpty((CharSequence)s)) {
            annotationPresent = false;
        }
        else if (s.startsWith(zzdi.zzbav.get())) {
            annotationPresent = true;
        }
        else {
            try {
                annotationPresent = Class.forName(s).isAnnotationPresent(zziy.class);
            }
            catch (Exception ex) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Fail to check class type for class ".concat(value);
                }
                else {
                    concat = new String("Fail to check class type for class ");
                }
                zzb.zza(concat, ex);
                annotationPresent = false;
            }
        }
        return annotationPresent;
    }
    
    protected boolean zzch(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        boolean b = false;
        if (!empty) {
            if (!s.startsWith("android.")) {
                final boolean startsWith = s.startsWith("java.");
                b = false;
                if (!startsWith) {
                    return b;
                }
            }
            b = true;
        }
        return b;
    }
}
