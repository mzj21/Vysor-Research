// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

public abstract class zzrj
{
    private static final ExecutorService ys;
    
    static {
        ys = new ThreadPoolExecutor(0, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new zzsf("GAC_Transform"));
    }
    
    public static ExecutorService zzarz() {
        return zzrj.ys;
    }
}
