// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.internal.zzpz;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;

public class zzb extends zzc
{
    private final ConnectionResult vm;
    
    public zzb(final Status status, final ArrayMap<zzpz<?>, ConnectionResult> arrayMap) {
        super(status, arrayMap);
        this.vm = arrayMap.get(arrayMap.keyAt(0));
    }
}
