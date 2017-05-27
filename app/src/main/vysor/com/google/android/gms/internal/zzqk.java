// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public abstract class zzqk implements Releasable, Result
{
    protected final Status fp;
    protected final DataHolder xi;
    
    protected zzqk(final DataHolder xi, final Status fp) {
        this.fp = fp;
        this.xi = xi;
    }
    
    @Override
    public Status getStatus() {
        return this.fp;
    }
    
    @Override
    public void release() {
        if (this.xi != null) {
            this.xi.close();
        }
    }
}
