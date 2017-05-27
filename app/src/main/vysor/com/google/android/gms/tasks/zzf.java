// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

interface zzf<TResult>
{
    void cancel();
    
    void onComplete(@NonNull final Task<TResult> p0);
}
