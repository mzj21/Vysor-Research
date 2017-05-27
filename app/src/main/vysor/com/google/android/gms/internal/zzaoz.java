// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzaoz
{
    public static void zzbs(final boolean b) {
        if (!b) {
            throw new IllegalArgumentException();
        }
    }
    
    public static <T> T zzy(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
}
