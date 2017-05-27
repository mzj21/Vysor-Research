// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;
import com.google.android.gms.common.api.Releasable;

public interface DataBuffer<T> extends Releasable, Iterable<T>
{
    @Deprecated
    void close();
    
    T get(final int p0);
    
    int getCount();
    
    @Deprecated
    boolean isClosed();
    
    Iterator<T> iterator();
    
    void release();
    
    Iterator<T> singleRefIterator();
    
    Bundle zzasz();
}
