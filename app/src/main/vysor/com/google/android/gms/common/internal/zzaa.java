// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.internal.zzsh;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import com.google.android.gms.internal.zzsi;
import android.content.Context;

public class zzaa
{
    private static String CS;
    private static int CT;
    private static Object zzaok;
    private static boolean zzcdp;
    
    static {
        zzaa.zzaok = new Object();
    }
    
    public static String zzcg(final Context context) {
        zzci(context);
        return zzaa.CS;
    }
    
    public static int zzch(final Context context) {
        zzci(context);
        return zzaa.CT;
    }
    
    private static void zzci(final Context context) {
        final Object zzaok = zzaa.zzaok;
        Label_0061: {
            final String packageName;
            final zzsh zzsh;
            synchronized (zzaok) {
                if (zzaa.zzcdp) {
                    return;
                }
                zzaa.zzcdp = true;
                packageName = context.getPackageName();
                final zzsh zzcr;
                zzsh = (zzcr = zzsi.zzcr(context));
                final String s = packageName;
                final int n = 128;
                final ApplicationInfo applicationInfo = zzcr.getApplicationInfo(s, n);
                final Bundle bundle = applicationInfo.metaData;
                final Bundle bundle3;
                final Bundle bundle2 = bundle3 = bundle;
                if (bundle3 == null) {
                    return;
                }
                break Label_0061;
            }
            while (true) {
                try {
                    final zzsh zzcr = zzsh;
                    final String s = packageName;
                    final int n = 128;
                    final ApplicationInfo applicationInfo = zzcr.getApplicationInfo(s, n);
                    final Bundle bundle = applicationInfo.metaData;
                    final Bundle bundle3;
                    final Bundle bundle2 = bundle3 = bundle;
                    if (bundle3 != null) {
                        zzaa.CS = bundle2.getString("com.google.app.id");
                        zzaa.CT = bundle2.getInt("com.google.android.gms.version");
                    }
                    // monitorexit(zzaok)
                }
                catch (PackageManager$NameNotFoundException ex) {
                    Log.wtf("MetadataValueReader", "This should never happen.", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
}
