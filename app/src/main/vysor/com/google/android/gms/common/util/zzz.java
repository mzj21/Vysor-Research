// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.zzsi;
import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.util.Log;
import android.os.WorkSource;
import java.lang.reflect.Method;

public class zzz
{
    private static final Method Fa;
    private static final Method Fb;
    private static final Method Fc;
    private static final Method Fd;
    private static final Method Fe;
    
    static {
        Fa = zzaya();
        Fb = zzayb();
        Fc = zzayc();
        Fd = zzayd();
        Fe = zzaye();
    }
    
    public static int zza(final WorkSource workSource) {
        if (zzz.Fc == null) {
            return 0;
        }
        try {
            return (int)zzz.Fc.invoke(workSource, new Object[0]);
        }
        catch (Exception ex) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
        }
        return 0;
    }
    
    public static String zza(final WorkSource workSource, final int n) {
        if (zzz.Fe == null) {
            return null;
        }
        try {
            return (String)zzz.Fe.invoke(workSource, n);
        }
        catch (Exception ex) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
        }
        return null;
    }
    
    public static void zza(final WorkSource workSource, final int n, String s) {
        Label_0062: {
            if (zzz.Fb == null) {
                break Label_0062;
            }
            if (s == null) {
                s = "";
            }
            try {
                zzz.Fb.invoke(workSource, n, s);
                return;
            }
            catch (Exception ex) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex);
                return;
            }
        }
        if (zzz.Fa != null) {
            try {
                zzz.Fa.invoke(workSource, n);
            }
            catch (Exception ex2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", (Throwable)ex2);
            }
        }
    }
    
    private static Method zzaya() {
        try {
            return WorkSource.class.getMethod("add", Integer.TYPE);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzayb() {
        final boolean zzaxq = zzs.zzaxq();
        Method method = null;
        if (!zzaxq) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("add", Integer.TYPE, String.class);
            return method;
        }
        catch (Exception ex) {
            method = null;
            return method;
        }
    }
    
    private static Method zzayc() {
        try {
            return WorkSource.class.getMethod("size", (Class<?>[])new Class[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzayd() {
        try {
            return WorkSource.class.getMethod("get", Integer.TYPE);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static Method zzaye() {
        final boolean zzaxq = zzs.zzaxq();
        Method method = null;
        if (!zzaxq) {
            return method;
        }
        try {
            method = WorkSource.class.getMethod("getName", Integer.TYPE);
            return method;
        }
        catch (Exception ex) {
            method = null;
            return method;
        }
    }
    
    public static List<String> zzb(final WorkSource workSource) {
        int i = 0;
        int zza;
        if (workSource == null) {
            zza = 0;
        }
        else {
            zza = zza(workSource);
        }
        List<String> empty_LIST;
        if (zza == 0) {
            empty_LIST = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            empty_LIST = new ArrayList<String>();
            while (i < zza) {
                final String zza2 = zza(workSource, i);
                if (!zzw.zzij(zza2)) {
                    empty_LIST.add(zza2);
                }
                ++i;
            }
        }
        return empty_LIST;
    }
    
    public static boolean zzcp(final Context context) {
        boolean b = false;
        if (context != null) {
            final PackageManager packageManager = context.getPackageManager();
            b = false;
            if (packageManager != null) {
                final int checkPermission = zzsi.zzcr(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName());
                b = false;
                if (checkPermission == 0) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    public static WorkSource zzf(final int n, final String s) {
        final WorkSource workSource = new WorkSource();
        zza(workSource, n, s);
        return workSource;
    }
    
    public static WorkSource zzy(final Context context, final String s) {
        WorkSource zzf;
        if (context == null || context.getPackageManager() == null) {
            zzf = null;
        }
        else {
            ApplicationInfo applicationInfo;
            while (true) {
                while (true) {
                    try {
                        applicationInfo = zzsi.zzcr(context).getApplicationInfo(s, 0);
                        if (applicationInfo != null) {
                            break;
                        }
                        final String value = String.valueOf(s);
                        if (value.length() != 0) {
                            final String concat = "Could not get applicationInfo from package: ".concat(value);
                            Log.e("WorkSourceUtil", concat);
                            zzf = null;
                            return zzf;
                        }
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        final String value2 = String.valueOf(s);
                        String concat2;
                        if (value2.length() != 0) {
                            concat2 = "Could not find package: ".concat(value2);
                        }
                        else {
                            concat2 = new String("Could not find package: ");
                        }
                        Log.e("WorkSourceUtil", concat2);
                        zzf = null;
                        return zzf;
                    }
                    final String concat = new String("Could not get applicationInfo from package: ");
                    continue;
                }
            }
            zzf = zzf(applicationInfo.uid, s);
        }
        return zzf;
    }
}
