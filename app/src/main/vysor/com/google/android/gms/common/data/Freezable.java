// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

public interface Freezable<T>
{
    T freeze();
    
    boolean isDataValid();
}
