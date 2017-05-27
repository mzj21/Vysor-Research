// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public abstract class zzqs
{
    private static final ExecutorService ys;
    
    static {
        ys = Executors.newFixedThreadPool(2, new zzsf("GAC_Executor"));
    }
    
    public static ExecutorService zzarz() {
        return zzqs.ys;
    }
}
