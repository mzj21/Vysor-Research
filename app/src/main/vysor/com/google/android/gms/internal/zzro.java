// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api;

public abstract class zzro<A extends Api.zzb, TResult>
{
    protected abstract void zzb(final A p0, final TaskCompletionSource<TResult> p1) throws RemoteException;
}
