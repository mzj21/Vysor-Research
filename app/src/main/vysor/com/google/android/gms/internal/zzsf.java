// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

public class zzsf implements ThreadFactory
{
    private final String Ff;
    private final AtomicInteger Fg;
    private final ThreadFactory Fh;
    private final int mPriority;
    
    public zzsf(final String s) {
        this(s, 0);
    }
    
    public zzsf(final String s, final int mPriority) {
        this.Fg = new AtomicInteger();
        this.Fh = Executors.defaultThreadFactory();
        this.Ff = zzac.zzb(s, "Name must not be null");
        this.mPriority = mPriority;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = this.Fh.newThread(new zzsg(runnable, this.mPriority));
        final String ff = this.Ff;
        thread.setName(new StringBuilder(13 + String.valueOf(ff).length()).append(ff).append("[").append(this.Fg.getAndIncrement()).append("]").toString());
        return thread;
    }
}
