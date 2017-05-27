// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.os.SystemClock;

public final class zzh implements zze
{
    private static zzh EK;
    
    public static zze zzaxj() {
        synchronized (zzh.class) {
            if (zzh.EK == null) {
                zzh.EK = new zzh();
            }
            return zzh.EK;
        }
    }
    
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    @Override
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
    
    @Override
    public long nanoTime() {
        return System.nanoTime();
    }
}
