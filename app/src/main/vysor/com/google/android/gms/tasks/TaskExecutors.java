// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.os.Looper;
import android.os.Handler;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class TaskExecutors
{
    public static final Executor MAIN_THREAD;
    static final Executor aJI;
    
    static {
        MAIN_THREAD = new zza();
        aJI = new Executor() {
            @Override
            public void execute(@NonNull final Runnable runnable) {
                runnable.run();
            }
        };
    }
    
    private static final class zza implements Executor
    {
        private final Handler mHandler;
        
        public zza() {
            this.mHandler = new Handler(Looper.getMainLooper());
        }
        
        @Override
        public void execute(@NonNull final Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }
}
