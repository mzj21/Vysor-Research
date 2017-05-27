// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.callback;

import com.koushikdutta.async.AsyncServerSocket;
import com.koushikdutta.async.AsyncSocket;

public interface ListenCallback extends CompletedCallback
{
    void onAccepted(final AsyncSocket p0);
    
    void onListening(final AsyncServerSocket p0);
}
