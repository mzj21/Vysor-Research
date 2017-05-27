// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.DeadObjectException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api;

public abstract class zzrr<A extends Api.zzb>
{
    public zzrd.zzb<?> zzasr() {
        return null;
    }
    
    protected abstract void zzc(final A p0, final TaskCompletionSource<Void> p1) throws DeadObjectException;
}
