// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.wrapper;

import com.koushikdutta.async.AsyncSocket;

public interface AsyncSocketWrapper extends AsyncSocket, DataEmitterWrapper
{
    AsyncSocket getSocket();
}
