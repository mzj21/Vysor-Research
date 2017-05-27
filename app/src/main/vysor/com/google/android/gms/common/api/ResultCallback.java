// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public interface ResultCallback<R extends Result>
{
    void onResult(@NonNull final R p0);
}
