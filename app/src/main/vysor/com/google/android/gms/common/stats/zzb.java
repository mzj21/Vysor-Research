// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import java.util.Iterator;
import android.content.pm.ResolveInfo;
import android.content.ComponentName;
import android.os.Process;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import com.google.android.gms.common.util.zzt;
import android.util.Log;
import android.os.Parcelable;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Debug;
import android.content.Context;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class zzb
{
    private static final Object Cz;
    private static zzb DX;
    private static Integer Ed;
    private final List<String> DY;
    private final List<String> DZ;
    private final List<String> Ea;
    private final List<String> Eb;
    private zze Ec;
    private zze Ee;
    
    static {
        Cz = new Object();
    }
    
    private zzb() {
        if (getLogLevel() == zzd.LOG_LEVEL_OFF) {
            this.DY = (List<String>)Collections.EMPTY_LIST;
            this.DZ = (List<String>)Collections.EMPTY_LIST;
            this.Ea = (List<String>)Collections.EMPTY_LIST;
            this.Eb = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            final String s = zzc.zza.Ei.get();
            List<String> dy;
            if (s == null) {
                dy = (List<String>)Collections.EMPTY_LIST;
            }
            else {
                dy = Arrays.asList(s.split(","));
            }
            this.DY = dy;
            final String s2 = zzc.zza.Ej.get();
            List<String> dz;
            if (s2 == null) {
                dz = (List<String>)Collections.EMPTY_LIST;
            }
            else {
                dz = Arrays.asList(s2.split(","));
            }
            this.DZ = dz;
            final String s3 = zzc.zza.Ek.get();
            List<String> ea;
            if (s3 == null) {
                ea = (List<String>)Collections.EMPTY_LIST;
            }
            else {
                ea = Arrays.asList(s3.split(","));
            }
            this.Ea = ea;
            final String s4 = zzc.zza.El.get();
            List<String> eb;
            if (s4 == null) {
                eb = (List<String>)Collections.EMPTY_LIST;
            }
            else {
                eb = Arrays.asList(s4.split(","));
            }
            this.Eb = eb;
            this.Ec = new zze(1024, zzc.zza.Em.get());
            this.Ee = new zze(1024, zzc.zza.Em.get());
        }
    }
    
    private static int getLogLevel() {
        Label_0032: {
            if (zzb.Ed != null) {
                break Label_0032;
            }
            try {
                int n;
                if (com.google.android.gms.common.util.zzd.zzact()) {
                    n = zzc.zza.Eh.get();
                }
                else {
                    n = zzd.LOG_LEVEL_OFF;
                }
                zzb.Ed = n;
                return zzb.Ed;
            }
            catch (SecurityException ex) {
                zzb.Ed = zzd.LOG_LEVEL_OFF;
                return zzb.Ed;
            }
        }
    }
    
    private static String zza(final StackTraceElement[] array, final int n) {
        String string;
        if (n + 4 >= array.length) {
            string = "<bottom of call stack>";
        }
        else {
            final StackTraceElement stackTraceElement = array[n + 4];
            final String value = String.valueOf(stackTraceElement.getClassName());
            final String value2 = String.valueOf(stackTraceElement.getMethodName());
            string = new StringBuilder(13 + String.valueOf(value).length() + String.valueOf(value2).length()).append(value).append(".").append(value2).append(":").append(stackTraceElement.getLineNumber()).toString();
        }
        return string;
    }
    
    private void zza(final Context context, final String s, final int n, final String s2, final String s3, final String s4, final String s5) {
        final long currentTimeMillis = System.currentTimeMillis();
        final int n2 = getLogLevel() & zzd.Er;
        String zzm = null;
        if (n2 != 0) {
            zzm = null;
            if (n != 13) {
                zzm = zzm(3, 5);
            }
        }
        long nativeHeapAllocatedSize = 0L;
        if ((getLogLevel() & zzd.Et) != 0x0) {
            nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
        }
        ConnectionEvent connectionEvent;
        if (n == 1 || n == 4 || n == 14) {
            connectionEvent = new ConnectionEvent(currentTimeMillis, n, null, null, null, null, zzm, s, SystemClock.elapsedRealtime(), nativeHeapAllocatedSize);
        }
        else {
            connectionEvent = new ConnectionEvent(currentTimeMillis, n, s2, s3, s4, s5, zzm, s, SystemClock.elapsedRealtime(), nativeHeapAllocatedSize);
        }
        context.startService(new Intent().setComponent(zzd.En).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)connectionEvent));
    }
    
    private void zza(final Context context, final String s, final String s2, final Intent intent, final int n) {
        String zzaxx = null;
        if (this.zzawv() && this.Ec != null) {
            String name;
            String processName;
            if (n == 4 || n == 1) {
                if (!this.Ec.zzig(s)) {
                    return;
                }
                name = null;
                processName = null;
            }
            else {
                final ServiceInfo zzd = zzd(context, intent);
                if (zzd == null) {
                    Log.w("ConnectionTracker", String.format("Client %s made an invalid request %s", s2, intent.toUri(0)));
                    return;
                }
                processName = zzd.processName;
                name = zzd.name;
                zzaxx = zzt.zzaxx();
                if (!this.zzb(zzaxx, s2, processName, name)) {
                    return;
                }
                this.Ec.zzif(s);
            }
            this.zza(context, s, n, zzaxx, s2, processName, name);
        }
    }
    
    public static zzb zzawu() {
        synchronized (zzb.Cz) {
            if (zzb.DX == null) {
                zzb.DX = new zzb();
            }
            return zzb.DX;
        }
    }
    
    private boolean zzawv() {
        return false;
    }
    
    private String zzb(final ServiceConnection serviceConnection) {
        return String.valueOf(Process.myPid() << 32 | System.identityHashCode(serviceConnection));
    }
    
    private boolean zzb(final String s, final String s2, final String s3, final String s4) {
        final int logLevel = getLogLevel();
        return !this.DY.contains(s) && !this.DZ.contains(s2) && !this.Ea.contains(s3) && !this.Eb.contains(s4) && (!s3.equals(s) || (logLevel & zzd.Es) == 0x0);
    }
    
    private boolean zzc(final Context context, final Intent intent) {
        final ComponentName component = intent.getComponent();
        return component != null && com.google.android.gms.common.util.zzd.zzx(context, component.getPackageName());
    }
    
    private static ServiceInfo zzd(final Context context, final Intent intent) {
        final List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        ServiceInfo serviceInfo;
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            Log.w("ConnectionTracker", String.format("There are no handler of this intent: %s\n Stack trace: %s", intent.toUri(0), zzm(3, 20)));
            serviceInfo = null;
        }
        else if (queryIntentServices.size() > 1) {
            Log.w("ConnectionTracker", String.format("Multiple handlers found for this intent: %s\n Stack trace: %s", intent.toUri(0), zzm(3, 20)));
            final Iterator<ResolveInfo> iterator = queryIntentServices.iterator();
            while (iterator.hasNext()) {
                Log.w("ConnectionTracker", iterator.next().serviceInfo.name);
            }
            serviceInfo = null;
        }
        else {
            serviceInfo = queryIntentServices.get(0).serviceInfo;
        }
        return serviceInfo;
    }
    
    private static String zzm(int i, final int n) {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        final StringBuffer sb = new StringBuffer();
        while (i < n + i) {
            sb.append(zza(stackTrace, i)).append(" ");
            ++i;
        }
        return sb.toString();
    }
    
    @SuppressLint({ "UntrackedBindService" })
    public void zza(final Context context, final ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
        this.zza(context, this.zzb(serviceConnection), null, (Intent)null, 1);
    }
    
    public void zza(final Context context, final ServiceConnection serviceConnection, final String s, final Intent intent) {
        this.zza(context, this.zzb(serviceConnection), s, intent, 3);
    }
    
    public boolean zza(final Context context, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        return this.zza(context, context.getClass().getName(), intent, serviceConnection, n);
    }
    
    @SuppressLint({ "UntrackedBindService" })
    public boolean zza(final Context context, final String s, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        boolean b;
        if (this.zzc(context, intent)) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            b = false;
        }
        else {
            final boolean bindService = context.bindService(intent, serviceConnection, n);
            if (bindService) {
                this.zza(context, this.zzb(serviceConnection), s, intent, 2);
            }
            b = bindService;
        }
        return b;
    }
    
    public void zzb(final Context context, final ServiceConnection serviceConnection) {
        this.zza(context, this.zzb(serviceConnection), null, (Intent)null, 4);
    }
}
