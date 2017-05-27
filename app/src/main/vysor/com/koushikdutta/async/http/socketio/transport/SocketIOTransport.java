// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.socketio.transport;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.AsyncServer;

public interface SocketIOTransport
{
    void disconnect();
    
    AsyncServer getServer();
    
    String getSessionId();
    
    boolean heartbeats();
    
    boolean isConnected();
    
    void send(final String p0);
    
    void setClosedCallback(final CompletedCallback p0);
    
    void setStringCallback(final StringCallback p0);
    
    public interface StringCallback
    {
        void onStringAvailable(final String p0);
    }
}
