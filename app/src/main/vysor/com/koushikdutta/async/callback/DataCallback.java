// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.callback;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;

public interface DataCallback
{
    void onDataAvailable(final DataEmitter p0, final ByteBufferList p1);
    
    public static class NullDataCallback implements DataCallback
    {
        @Override
        public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
            list.recycle();
        }
    }
}
