// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

public interface DataBufferObserver
{
    void onDataChanged();
    
    void onDataRangeChanged(final int p0, final int p1);
    
    void onDataRangeInserted(final int p0, final int p1);
    
    void onDataRangeMoved(final int p0, final int p1, final int p2);
    
    void onDataRangeRemoved(final int p0, final int p1);
    
    public interface Observable
    {
        void addObserver(final DataBufferObserver p0);
        
        void removeObserver(final DataBufferObserver p0);
    }
}
