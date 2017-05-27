// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import com.google.android.gms.ads.internal.zzu;
import java.math.BigInteger;
import android.text.TextUtils;
import android.content.Context;

@zziy
public final class zzkl
{
    private static final Object zzaok;
    private static String zzcqk;
    
    static {
        zzaok = new Object();
    }
    
    public static String zza(final Context context, final String s, final String s2) {
        synchronized (zzkl.zzaok) {
            if (zzkl.zzcqk == null && !TextUtils.isEmpty((CharSequence)s)) {
                zzb(context, s, s2);
            }
            return zzkl.zzcqk;
        }
    }
    
    private static void zzb(final Context context, final String s, final String s2) {
        BigInteger setBit = null;
        Label_0096: {
            try {
                final ClassLoader classLoader = context.createPackageContext(s2, 3).getClassLoader();
                final Class<?> forName = Class.forName("com.google.ads.mediation.MediationAdapter", false, classLoader);
                final BigInteger bigInteger = new BigInteger(new byte[1]);
                final String[] split = s.split(",");
                setBit = bigInteger;
                for (int i = 0; i < split.length; ++i) {
                    if (zzu.zzfz().zza(classLoader, forName, split[i])) {
                        setBit = setBit.setBit(i);
                    }
                }
                break Label_0096;
            }
            catch (Throwable t) {
                zzkl.zzcqk = "err";
            }
            return;
        }
        zzkl.zzcqk = String.format(Locale.US, "%X", setBit);
    }
    
    public static String zzub() {
        synchronized (zzkl.zzaok) {
            return zzkl.zzcqk;
        }
    }
}
