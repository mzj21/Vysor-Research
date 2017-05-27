// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.StrictMode$ThreadPolicy;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.StrictMode;
import java.util.concurrent.Callable;

@zziy
public class zzle
{
    public static <T> T zzb(final Callable<T> callable) {
        final StrictMode$ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            return callable.call();
        }
        catch (Throwable t) {
            zzb.zzb("Unexpected exception.", t);
            zzu.zzgd().zza(t, "StrictModeUtil.runWithLaxStrictMode");
            StrictMode.setThreadPolicy(threadPolicy);
            return null;
        }
        finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
}
