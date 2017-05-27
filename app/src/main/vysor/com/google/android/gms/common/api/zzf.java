// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.WeakHashMap;
import java.util.Map;

public abstract class zzf
{
    private static final Map<Object, zzf> vW;
    private static final Object zzaok;
    
    static {
        vW = new WeakHashMap<Object, zzf>();
        zzaok = new Object();
    }
    
    public abstract void remove(final int p0);
}
