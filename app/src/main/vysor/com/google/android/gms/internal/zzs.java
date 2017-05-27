// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.util.Log;

public class zzs
{
    public static boolean DEBUG;
    public static String TAG;
    
    static {
        zzs.TAG = "Volley";
        zzs.DEBUG = Log.isLoggable(zzs.TAG, 2);
    }
    
    public static void zza(final String s, final Object... array) {
        if (zzs.DEBUG) {
            Log.v(zzs.TAG, zzd(s, array));
        }
    }
    
    public static void zza(final Throwable t, final String s, final Object... array) {
        Log.e(zzs.TAG, zzd(s, array), t);
    }
    
    public static void zzb(final String s, final Object... array) {
        Log.d(zzs.TAG, zzd(s, array));
    }
    
    public static void zzc(final String s, final Object... array) {
        Log.e(zzs.TAG, zzd(s, array));
    }
    
    private static String zzd(String format, final Object... array) {
        if (array != null) {
            format = String.format(Locale.US, format, array);
        }
        final StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        for (int i = 2; i < stackTrace.length; ++i) {
            if (!stackTrace[i].getClass().equals(zzs.class)) {
                final String className = stackTrace[i].getClassName();
                final String substring = className.substring(1 + className.lastIndexOf(46));
                final String substring2 = substring.substring(1 + substring.lastIndexOf(36));
                final String value = String.valueOf(stackTrace[i].getMethodName());
                final String string = new StringBuilder(1 + String.valueOf(substring2).length() + String.valueOf(value).length()).append(substring2).append(".").append(value).toString();
                return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
            }
        }
        final String string = "<unknown>";
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
    }
    
    static class zza
    {
        public static final boolean zzbj;
        private final List<zzs.zza.zza> zzbk;
        private boolean zzbl;
        
        static {
            zzbj = zzs.DEBUG;
        }
        
        zza() {
            this.zzbk = new ArrayList<zzs.zza.zza>();
            this.zzbl = false;
        }
        
        private long getTotalDuration() {
            long n;
            if (this.zzbk.size() == 0) {
                n = 0L;
            }
            else {
                n = this.zzbk.get(-1 + this.zzbk.size()).time - this.zzbk.get(0).time;
            }
            return n;
        }
        
        @Override
        protected void finalize() throws Throwable {
            if (!this.zzbl) {
                this.zzd("Request on the loose");
                zzs.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }
        
        public void zza(final String s, final long n) {
            synchronized (this) {
                if (this.zzbl) {
                    throw new IllegalStateException("Marker added to finished log");
                }
            }
            this.zzbk.add(new zzs.zza.zza(s, n, SystemClock.elapsedRealtime()));
        }
        // monitorexit(this)
        
        public void zzd(final String s) {
            synchronized (this) {
                this.zzbl = true;
                final long totalDuration = this.getTotalDuration();
                if (totalDuration > 0L) {
                    final long time = this.zzbk.get(0).time;
                    zzs.zzb("(%-4d ms) %s", totalDuration, s);
                    final Iterator<zzs.zza.zza> iterator = this.zzbk.iterator();
                    long n = time;
                    while (iterator.hasNext()) {
                        final zzs.zza.zza zza = iterator.next();
                        final long time2 = zza.time;
                        zzs.zzb("(+%-4d) [%2d] %s", time2 - n, zza.zzbm, zza.name);
                        n = time2;
                    }
                }
            }
        }
        
        private static class zza
        {
            public final String name;
            public final long time;
            public final long zzbm;
            
            public zza(final String name, final long zzbm, final long time) {
                this.name = name;
                this.zzbm = zzbm;
                this.time = time;
            }
        }
    }
}
