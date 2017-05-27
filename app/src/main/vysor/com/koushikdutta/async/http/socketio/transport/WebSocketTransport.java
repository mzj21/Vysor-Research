// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.socketio.transport;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.WebSocket;

public class WebSocketTransport implements SocketIOTransport
{
    private String sessionId;
    private StringCallback stringCallback;
    private WebSocket webSocket;
    
    public WebSocketTransport(final WebSocket webSocket, final String sessionId) {
        this.webSocket = webSocket;
        this.sessionId = sessionId;
        this.webSocket.setDataCallback(new DataCallback.NullDataCallback());
    }
    
    @Override
    public void disconnect() {
        this.webSocket.close();
    }
    
    @Override
    public AsyncServer getServer() {
        return this.webSocket.getServer();
    }
    
    @Override
    public String getSessionId() {
        return this.sessionId;
    }
    
    @Override
    public boolean heartbeats() {
        return true;
    }
    
    @Override
    public boolean isConnected() {
        return this.webSocket.isOpen();
    }
    
    @Override
    public void send(final String s) {
        this.webSocket.send(s);
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback closedCallback) {
        this.webSocket.setClosedCallback(closedCallback);
    }
    
    @Override
    public void setStringCallback(final StringCallback stringCallback) {
        if (this.stringCallback != stringCallback) {
            if (stringCallback == null) {
                this.webSocket.setStringCallback(null);
            }
            else {
                this.webSocket.setStringCallback((WebSocket.StringCallback)new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(final String s) {
                        stringCallback.onStringAvailable(s);
                    }
                });
            }
            this.stringCallback = stringCallback;
        }
    }
}
