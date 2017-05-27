// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.zzac;

public final class BatchResult implements Result
{
    private final Status fp;
    private final PendingResult<?>[] vr;
    
    BatchResult(final Status fp, final PendingResult<?>[] vr) {
        this.fp = fp;
        this.vr = vr;
    }
    
    @Override
    public Status getStatus() {
        return this.fp;
    }
    
    public <R extends Result> R take(final BatchResultToken<R> batchResultToken) {
        zzac.zzb(batchResultToken.mId < this.vr.length, (Object)"The result token does not belong to this batch");
        return (R)this.vr[batchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}
