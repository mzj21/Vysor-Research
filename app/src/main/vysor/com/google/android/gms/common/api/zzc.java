// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzpz;
import android.support.v4.util.ArrayMap;

public class zzc implements Result
{
    private final Status fp;
    private final ArrayMap<zzpz<?>, ConnectionResult> vn;
    
    public zzc(final Status fp, final ArrayMap<zzpz<?>, ConnectionResult> vn) {
        this.fp = fp;
        this.vn = vn;
    }
    
    @Override
    public Status getStatus() {
        return this.fp;
    }
}
