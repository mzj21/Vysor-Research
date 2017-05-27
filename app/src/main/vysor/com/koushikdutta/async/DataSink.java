// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.callback.CompletedCallback;

public interface DataSink
{
    void end();
    
    CompletedCallback getClosedCallback();
    
    AsyncServer getServer();
    
    WritableCallback getWriteableCallback();
    
    boolean isOpen();
    
    void setClosedCallback(final CompletedCallback p0);
    
    void setWriteableCallback(final WritableCallback p0);
    
    void write(final ByteBufferList p0);
}
