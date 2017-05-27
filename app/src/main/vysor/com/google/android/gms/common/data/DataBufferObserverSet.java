// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import java.util.HashSet;

public final class DataBufferObserverSet implements DataBufferObserver, Observable
{
    private HashSet<DataBufferObserver> zJ;
    
    public DataBufferObserverSet() {
        this.zJ = new HashSet<DataBufferObserver>();
    }
    
    @Override
    public void addObserver(final DataBufferObserver dataBufferObserver) {
        this.zJ.add(dataBufferObserver);
    }
    
    public void clear() {
        this.zJ.clear();
    }
    
    public boolean hasObservers() {
        return !this.zJ.isEmpty();
    }
    
    @Override
    public void onDataChanged() {
        final Iterator<DataBufferObserver> iterator = this.zJ.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataChanged();
        }
    }
    
    @Override
    public void onDataRangeChanged(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zJ.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeChanged(n, n2);
        }
    }
    
    @Override
    public void onDataRangeInserted(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zJ.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeInserted(n, n2);
        }
    }
    
    @Override
    public void onDataRangeMoved(final int n, final int n2, final int n3) {
        final Iterator<DataBufferObserver> iterator = this.zJ.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeMoved(n, n2, n3);
        }
    }
    
    @Override
    public void onDataRangeRemoved(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zJ.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeRemoved(n, n2);
        }
    }
    
    @Override
    public void removeObserver(final DataBufferObserver dataBufferObserver) {
        this.zJ.remove(dataBufferObserver);
    }
}
