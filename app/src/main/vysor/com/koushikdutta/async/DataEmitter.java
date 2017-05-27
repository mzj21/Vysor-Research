// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;

public interface DataEmitter
{
    String charset();
    
    void close();
    
    DataCallback getDataCallback();
    
    CompletedCallback getEndCallback();
    
    AsyncServer getServer();
    
    boolean isChunked();
    
    boolean isPaused();
    
    void pause();
    
    void resume();
    
    void setDataCallback(final DataCallback p0);
    
    void setEndCallback(final CompletedCallback p0);
}
