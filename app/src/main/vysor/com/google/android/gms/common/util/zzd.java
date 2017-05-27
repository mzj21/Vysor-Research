// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.internal.zzsi;
import android.content.Context;
import android.os.Bundle;
import android.content.pm.PackageInfo;

public class zzd
{
    public static int zza(final PackageInfo packageInfo) {
        int int1 = -1;
        if (packageInfo != null && packageInfo.applicationInfo != null) {
            final Bundle metaData = packageInfo.applicationInfo.metaData;
            if (metaData != null) {
                int1 = metaData.getInt("com.google.android.gms.version", int1);
            }
        }
        return int1;
    }
    
    public static boolean zzact() {
        return false;
    }
    
    public static int zzv(final Context context, final String s) {
        return zza(zzw(context, s));
    }
    
    @Nullable
    public static PackageInfo zzw(final Context context, final String s) {
        try {
            return zzsi.zzcr(context).getPackageInfo(s, 128);
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    @TargetApi(12)
    public static boolean zzx(final Context context, final String s) {
        final boolean zzaxl = zzs.zzaxl();
        boolean b = false;
        if (zzaxl) {
            if ("com.google.android.gms".equals(s)) {
                final boolean zzact = zzact();
                b = false;
                if (zzact) {
                    return b;
                }
            }
            try {
                final int n = zzsi.zzcr(context).getApplicationInfo(s, 0).flags & 0x200000;
                b = false;
                if (n != 0) {
                    b = true;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                b = false;
            }
        }
        return b;
    }
}
