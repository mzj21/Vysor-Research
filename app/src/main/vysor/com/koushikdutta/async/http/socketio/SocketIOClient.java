// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.socketio;

import com.koushikdutta.async.http.socketio.transport.SocketIOTransport;
import org.json.JSONObject;
import org.json.JSONArray;
import com.koushikdutta.async.future.DependentCancellable;
import android.text.TextUtils;
import com.koushikdutta.async.future.SimpleFuture;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.http.AsyncHttpClient;

@Deprecated
public class SocketIOClient extends EventEmitter
{
    ConnectCallback connectCallback;
    boolean connected;
    SocketIOConnection connection;
    DisconnectCallback disconnectCallback;
    boolean disconnected;
    String endpoint;
    ErrorCallback errorCallback;
    ExceptionCallback exceptionCallback;
    JSONCallback jsonCallback;
    ReconnectCallback reconnectCallback;
    StringCallback stringCallback;
    
    private SocketIOClient(final SocketIOConnection connection, final String endpoint, final ConnectCallback connectCallback) {
        this.endpoint = endpoint;
        this.connection = connection;
        this.connectCallback = connectCallback;
    }
    
    public static Future<SocketIOClient> connect(final AsyncHttpClient asyncHttpClient, final SocketIORequest socketIORequest, final ConnectCallback connectCallback) {
        final SimpleFuture<SocketIOClient> simpleFuture = new SimpleFuture<SocketIOClient>();
        final SocketIOConnection socketIOConnection = new SocketIOConnection(asyncHttpClient, socketIORequest);
        socketIOConnection.clients.add(new SocketIOClient(socketIOConnection, "", new ConnectCallback() {
            @Override
            public void onConnectCompleted(final Exception ex, final SocketIOClient socketIOClient) {
                if (ex != null || TextUtils.isEmpty((CharSequence)socketIORequest.getEndpoint())) {
                    if (connectCallback != null) {
                        connectCallback.onConnectCompleted(ex, socketIOClient);
                    }
                    simpleFuture.setComplete(ex, socketIOClient);
                }
                else {
                    socketIOConnection.clients.remove(socketIOClient);
                    socketIOClient.of(socketIORequest.getEndpoint(), new ConnectCallback() {
                        @Override
                        public void onConnectCompleted(final Exception ex, final SocketIOClient socketIOClient) {
                            if (connectCallback != null) {
                                connectCallback.onConnectCompleted(ex, socketIOClient);
                            }
                            simpleFuture.setComplete(ex, socketIOClient);
                        }
                    });
                }
            }
        }));
        socketIOConnection.reconnect(simpleFuture);
        return simpleFuture;
    }
    
    public static Future<SocketIOClient> connect(final AsyncHttpClient asyncHttpClient, final String s, final ConnectCallback connectCallback) {
        return connect(asyncHttpClient, new SocketIORequest(s), connectCallback);
    }
    
    private void emitRaw(final int n, final String s, final Acknowledge acknowledge) {
        this.connection.emitRaw(n, this, s, acknowledge);
    }
    
    public void disconnect() {
        this.connection.disconnect(this);
        final DisconnectCallback disconnectCallback = this.disconnectCallback;
        if (disconnectCallback != null) {
            disconnectCallback.onDisconnect(null);
        }
    }
    
    public void emit(final String s) {
        this.emit(s, (Acknowledge)null);
    }
    
    public void emit(final String s, final Acknowledge acknowledge) {
        this.emitRaw(3, s, acknowledge);
    }
    
    public void emit(final String s, final JSONArray jsonArray) {
        this.emit(s, jsonArray, null);
    }
    
    public void emit(final String s, final JSONArray jsonArray, final Acknowledge acknowledge) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", (Object)s);
            jsonObject.put("args", (Object)jsonArray);
            this.emitRaw(5, jsonObject.toString(), acknowledge);
        }
        catch (Exception ex) {}
    }
    
    public void emit(final JSONObject jsonObject) {
        this.emit(jsonObject, null);
    }
    
    public void emit(final JSONObject jsonObject, final Acknowledge acknowledge) {
        this.emitRaw(4, jsonObject.toString(), acknowledge);
    }
    
    public void emitEvent(final String s) {
        this.emitEvent(s, null);
    }
    
    public void emitEvent(final String s, final Acknowledge acknowledge) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", (Object)s);
            this.emitRaw(5, jsonObject.toString(), acknowledge);
        }
        catch (Exception ex) {}
    }
    
    public DisconnectCallback getDisconnectCallback() {
        return this.disconnectCallback;
    }
    
    public ErrorCallback getErrorCallback() {
        return this.errorCallback;
    }
    
    public ExceptionCallback getExceptionCallback() {
        return this.exceptionCallback;
    }
    
    public JSONCallback getJSONCallback() {
        return this.jsonCallback;
    }
    
    public ReconnectCallback getReconnectCallback() {
        return this.reconnectCallback;
    }
    
    public StringCallback getStringCallback() {
        return this.stringCallback;
    }
    
    public SocketIOTransport getTransport() {
        return this.connection.transport;
    }
    
    public boolean isConnected() {
        return this.connected && !this.disconnected && this.connection.isConnected();
    }
    
    public void of(final String s, final ConnectCallback connectCallback) {
        this.connection.connect(new SocketIOClient(this.connection, s, connectCallback));
    }
    
    public void reconnect() {
        this.connection.reconnect(null);
    }
    
    public void setDisconnectCallback(final DisconnectCallback disconnectCallback) {
        this.disconnectCallback = disconnectCallback;
    }
    
    public void setErrorCallback(final ErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
    }
    
    public void setExceptionCallback(final ExceptionCallback exceptionCallback) {
        this.exceptionCallback = exceptionCallback;
    }
    
    public void setJSONCallback(final JSONCallback jsonCallback) {
        this.jsonCallback = jsonCallback;
    }
    
    public void setReconnectCallback(final ReconnectCallback reconnectCallback) {
        this.reconnectCallback = reconnectCallback;
    }
    
    public void setStringCallback(final StringCallback stringCallback) {
        this.stringCallback = stringCallback;
    }
}
