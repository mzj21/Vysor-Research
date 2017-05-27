// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Process;

class zzsg implements Runnable
{
    private final int mPriority;
    private final Runnable zzw;
    
    public zzsg(final Runnable zzw, final int mPriority) {
        this.zzw = zzw;
        this.mPriority = mPriority;
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(this.mPriority);
        this.zzw.run();
    }
}
