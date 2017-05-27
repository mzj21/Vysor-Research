// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.internal.zzsi;
import android.content.pm.PackageManager;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.Set;
import com.google.android.gms.common.internal.zzt;
import android.util.Log;
import android.content.pm.PackageInfo;
import android.content.Context;

public class zzf
{
    private static zzf vf;
    private final Context mContext;
    
    private zzf(final Context context) {
        this.mContext = context.getApplicationContext();
    }
    
    private boolean zzb(final PackageInfo packageInfo, final boolean b) {
        boolean b2;
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            b2 = false;
        }
        else {
            final zzd.zzb zzb = new zzd.zzb(packageInfo.signatures[0].toByteArray());
            Set<zzt> set;
            if (b) {
                set = zzd.zzapf();
            }
            else {
                set = zzd.zzapg();
            }
            final Iterator<zzt> iterator = set.iterator();
            while (iterator.hasNext()) {
                if (((zzd.zza)zzb).equals(iterator.next())) {
                    b2 = true;
                    return b2;
                }
            }
            b2 = false;
        }
        return b2;
    }
    
    public static zzf zzbz(final Context context) {
        zzac.zzy(context);
        synchronized (zzf.class) {
            if (zzf.vf == null) {
                zzd.zzbr(context);
                zzf.vf = new zzf(context);
            }
            return zzf.vf;
        }
    }
    
    zzd.zza zza(final PackageInfo packageInfo, final zzd.zza... array) {
        int i = 0;
        zzd.zza zza;
        if (packageInfo.signatures == null) {
            zza = null;
        }
        else if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            zza = null;
        }
        else {
            final zzd.zzb zzb = new zzd.zzb(packageInfo.signatures[0].toByteArray());
            while (i < array.length) {
                if (array[i].equals(zzb)) {
                    zza = array[i];
                    return zza;
                }
                ++i;
            }
            zza = null;
        }
        return zza;
    }
    
    public boolean zza(final PackageInfo packageInfo, final boolean b) {
        int n = 1;
        if (packageInfo == null || packageInfo.signatures == null) {
            return false;
        }
        zzd.zza zza;
        if (b) {
            zza = this.zza(packageInfo, zzd.zzd.uW);
        }
        else {
            final zzd.zza[] array = new zzd.zza[n];
            array[0] = zzd.zzd.uW[0];
            zza = this.zza(packageInfo, array);
        }
        if (zza == null) {
            return false;
        }
        return n != 0;
        n = 0;
        return n != 0;
    }
    
    public boolean zza(final PackageManager packageManager, final PackageInfo packageInfo) {
        boolean b = false;
        if (packageInfo != null) {
            if (zze.zzbv(this.mContext)) {
                b = this.zzb(packageInfo, true);
            }
            else {
                b = this.zzb(packageInfo, false);
                if (!b && this.zzb(packageInfo, true)) {
                    Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
                }
            }
        }
        return b;
    }
    
    public boolean zzb(final PackageManager packageManager, final PackageInfo packageInfo) {
        boolean b = false;
        if (packageInfo != null) {
            if (this.zza(packageInfo, false)) {
                b = true;
            }
            else {
                final boolean zza = this.zza(packageInfo, true);
                b = false;
                if (zza) {
                    if (zze.zzbv(this.mContext)) {
                        b = true;
                    }
                    else {
                        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
                        b = false;
                    }
                }
            }
        }
        return b;
    }
    
    public boolean zzb(final PackageManager packageManager, final String s) {
        try {
            return this.zza(packageManager, zzsi.zzcr(this.mContext).getPackageInfo(s, 64));
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
}
