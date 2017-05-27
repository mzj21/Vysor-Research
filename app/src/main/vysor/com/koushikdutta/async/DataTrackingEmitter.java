// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

public interface DataTrackingEmitter extends DataEmitter
{
    int getBytesRead();
    
    DataTracker getDataTracker();
    
    void setDataEmitter(final DataEmitter p0);
    
    void setDataTracker(final DataTracker p0);
    
    public interface DataTracker
    {
        void onData(final int p0);
    }
}
