// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.zzf;
import android.annotation.TargetApi;
import com.google.android.gms.internal.zzsi;
import android.content.Context;

public final class zzy
{
    @TargetApi(19)
    public static boolean zzb(final Context context, final int n, final String s) {
        return zzsi.zzcr(context).zzg(n, s);
    }
    
    public static boolean zzf(final Context context, final int n) {
        final boolean zzb = zzb(context, n, "com.google.android.gms");
        boolean zzb2 = false;
        if (zzb) {
            final PackageManager packageManager = context.getPackageManager();
            try {
                zzb2 = zzf.zzbz(context).zzb(context.getPackageManager(), packageManager.getPackageInfo("com.google.android.gms", 64));
            }
            catch (PackageManager$NameNotFoundException ex) {
                final boolean loggable = Log.isLoggable("UidVerifier", 3);
                zzb2 = false;
                if (loggable) {
                    Log.d("UidVerifier", "Package manager can't find google play services package, defaulting to false");
                    zzb2 = false;
                }
            }
        }
        return zzb2;
    }
}
