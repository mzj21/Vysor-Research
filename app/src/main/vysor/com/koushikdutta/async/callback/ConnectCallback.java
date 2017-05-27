// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.callback;

import com.koushikdutta.async.AsyncSocket;

public interface ConnectCallback
{
    void onConnectCompleted(final Exception p0, final AsyncSocket p1);
}
