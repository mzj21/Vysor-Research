// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.AsyncSocket;

public interface WebSocket extends AsyncSocket
{
    PongCallback getPongCallback();
    
    AsyncSocket getSocket();
    
    StringCallback getStringCallback();
    
    boolean isBuffering();
    
    void ping(final String p0);
    
    void pong(final String p0);
    
    void send(final String p0);
    
    void send(final byte[] p0);
    
    void send(final byte[] p0, final int p1, final int p2);
    
    void setPingCallback(final PingCallback p0);
    
    void setPongCallback(final PongCallback p0);
    
    void setStringCallback(final StringCallback p0);
    
    public interface PingCallback
    {
        void onPingReceived(final String p0);
    }
    
    public interface PongCallback
    {
        void onPongReceived(final String p0);
    }
    
    public interface StringCallback
    {
        void onStringAvailable(final String p0);
    }
}
