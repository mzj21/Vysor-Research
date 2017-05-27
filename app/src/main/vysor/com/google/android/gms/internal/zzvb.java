// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import java.util.concurrent.Callable;

public class zzvb
{
    public static <T> T zzb(final Callable<T> callable) {
        final StrictMode$ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(StrictMode$ThreadPolicy.LAX);
            return callable.call();
        }
        catch (Throwable t) {
            StrictMode.setThreadPolicy(threadPolicy);
            return null;
        }
        finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
}
