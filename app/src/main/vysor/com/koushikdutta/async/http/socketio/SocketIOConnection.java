// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.socketio;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.http.socketio.transport.XHRPollingTransport;
import com.koushikdutta.async.http.socketio.transport.WebSocketTransport;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.future.FutureCallback;
import android.net.Uri;
import com.koushikdutta.async.future.SimpleFuture;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.koushikdutta.async.future.TransformFuture;
import com.koushikdutta.async.http.AsyncHttpRequest;
import java.util.Iterator;
import com.koushikdutta.async.future.DependentCancellable;
import com.koushikdutta.async.callback.CompletedCallback;
import java.util.Locale;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.koushikdutta.async.http.socketio.transport.SocketIOTransport;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.future.Cancellable;
import java.util.ArrayList;
import java.util.Hashtable;

class SocketIOConnection
{
    int ackCount;
    Hashtable<String, Acknowledge> acknowledges;
    ArrayList<SocketIOClient> clients;
    Cancellable connecting;
    int heartbeat;
    AsyncHttpClient httpClient;
    long reconnectDelay;
    SocketIORequest request;
    SocketIOTransport transport;
    
    public SocketIOConnection(final AsyncHttpClient httpClient, final SocketIORequest request) {
        this.clients = new ArrayList<SocketIOClient>();
        this.acknowledges = new Hashtable<String, Acknowledge>();
        this.httpClient = httpClient;
        this.request = request;
        this.reconnectDelay = this.request.config.reconnectDelay;
    }
    
    private Acknowledge acknowledge(final String s, final String s2) {
        Acknowledge acknowledge;
        if (TextUtils.isEmpty((CharSequence)s)) {
            acknowledge = null;
        }
        else {
            acknowledge = new Acknowledge() {
                final /* synthetic */ String val$messageId = s.replaceAll("\\+$", "");
                
                @Override
                public void acknowledge(final JSONArray jsonArray) {
                    String string = "";
                    if (jsonArray != null) {
                        string = string + "+" + jsonArray.toString();
                    }
                    final SocketIOTransport transport = SocketIOConnection.this.transport;
                    if (transport == null) {
                        SocketIOConnection.this.select(s2, (SelectCallback)new SelectCallback() {
                            final /* synthetic */ Exception val$e = new SocketIOException("not connected to server");
                            
                            @Override
                            public void onSelect(final SocketIOClient socketIOClient) {
                                final ExceptionCallback exceptionCallback = socketIOClient.exceptionCallback;
                                if (exceptionCallback != null) {
                                    exceptionCallback.onException(this.val$e);
                                }
                            }
                        });
                    }
                    else {
                        transport.send(String.format(Locale.ENGLISH, "6:::%s%s", this.val$messageId, string));
                    }
                }
            };
        }
        return acknowledge;
    }
    
    private void attach() {
        if (this.transport.heartbeats()) {
            this.setupHeartbeat();
        }
        this.transport.setClosedCallback(new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                SocketIOConnection.this.transport = null;
                SocketIOConnection.this.reportDisconnect(ex);
            }
        });
        this.transport.setStringCallback((SocketIOTransport.StringCallback)new SocketIOTransport.StringCallback() {
            @Override
            public void onStringAvailable(final String s) {
                String[] split = null;
                Label_0373: {
                    Label_0299: {
                        Label_0239: {
                            Label_0199: {
                                Label_0170: {
                                    Label_0153: {
                                        Label_0140: {
                                            Label_0117: {
                                                try {
                                                    split = s.split(":", 4);
                                                    switch (Integer.parseInt(split[0])) {
                                                        default: {
                                                            throw new SocketIOException("unknown code");
                                                        }
                                                        case 8: {
                                                            break;
                                                        }
                                                        case 0: {
                                                            break Label_0117;
                                                        }
                                                        case 1: {
                                                            break Label_0140;
                                                        }
                                                        case 2: {
                                                            break Label_0153;
                                                        }
                                                        case 3: {
                                                            break Label_0170;
                                                        }
                                                        case 4: {
                                                            break Label_0199;
                                                        }
                                                        case 5: {
                                                            break Label_0239;
                                                        }
                                                        case 6: {
                                                            break Label_0299;
                                                        }
                                                        case 7: {
                                                            break Label_0373;
                                                        }
                                                    }
                                                }
                                                catch (Exception ex) {
                                                    SocketIOConnection.this.transport.setClosedCallback(null);
                                                    SocketIOConnection.this.transport.disconnect();
                                                    SocketIOConnection.this.transport = null;
                                                    SocketIOConnection.this.reportDisconnect(ex);
                                                }
                                                return;
                                            }
                                            SocketIOConnection.this.transport.disconnect();
                                            SocketIOConnection.this.reportDisconnect(null);
                                            return;
                                        }
                                        SocketIOConnection.this.reportConnect(split[2]);
                                        return;
                                    }
                                    SocketIOConnection.this.transport.send("2::");
                                    return;
                                }
                                SocketIOConnection.this.reportString(split[2], split[3], SocketIOConnection.this.acknowledge(split[1], split[2]));
                                return;
                            }
                            SocketIOConnection.this.reportJson(split[2], new JSONObject(split[3]), SocketIOConnection.this.acknowledge(split[1], split[2]));
                            return;
                        }
                        final JSONObject jsonObject = new JSONObject(split[3]);
                        SocketIOConnection.this.reportEvent(split[2], jsonObject.getString("name"), jsonObject.optJSONArray("args"), SocketIOConnection.this.acknowledge(split[1], split[2]));
                        return;
                    }
                    final String[] split2 = split[3].split("\\+", 2);
                    final Acknowledge acknowledge = SocketIOConnection.this.acknowledges.remove(split2[0]);
                    if (acknowledge != null) {
                        final int length = split2.length;
                        JSONArray jsonArray = null;
                        if (length == 2) {
                            jsonArray = new JSONArray(split2[1]);
                        }
                        acknowledge.acknowledge(jsonArray);
                        return;
                    }
                    return;
                }
                SocketIOConnection.this.reportError(split[2], split[3]);
            }
        });
        this.select(null, (SelectCallback)new SelectCallback() {
            @Override
            public void onSelect(final SocketIOClient socketIOClient) {
                if (!TextUtils.isEmpty((CharSequence)socketIOClient.endpoint)) {
                    SocketIOConnection.this.connect(socketIOClient);
                }
            }
        });
    }
    
    private void delayReconnect() {
        if (this.transport == null && this.clients.size() != 0) {
            final Iterator<SocketIOClient> iterator = this.clients.iterator();
            while (true) {
                do {
                    final boolean hasNext = iterator.hasNext();
                    final boolean b = false;
                    if (hasNext) {
                        continue;
                    }
                    if (!b) {
                        return;
                    }
                    this.httpClient.getServer().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SocketIOConnection.this.reconnect(null);
                        }
                    }, this.nextReconnectDelay(this.reconnectDelay));
                    this.reconnectDelay *= 2L;
                    if (this.request.config.reconnectDelayMax > 0L) {
                        this.reconnectDelay = Math.min(this.reconnectDelay, this.request.config.reconnectDelayMax);
                    }
                    return;
                } while (!iterator.next().disconnected);
                final boolean b = true;
                continue;
            }
        }
    }
    
    private long nextReconnectDelay(long n) {
        if (n >= 2L && n <= 4611686018427387903L && this.request.config.randomizeReconnectDelay) {
            n = (n >> 1) + (long)(n * Math.random());
        }
        return n;
    }
    
    private void reportConnect(final String s) {
        this.select(s, (SelectCallback)new SelectCallback() {
            @Override
            public void onSelect(final SocketIOClient socketIOClient) {
                if (!socketIOClient.isConnected()) {
                    if (!socketIOClient.connected) {
                        socketIOClient.connected = true;
                        final ConnectCallback connectCallback = socketIOClient.connectCallback;
                        if (connectCallback != null) {
                            connectCallback.onConnectCompleted(null, socketIOClient);
                        }
                    }
                    else if (socketIOClient.disconnected) {
                        socketIOClient.disconnected = false;
                        final ReconnectCallback reconnectCallback = socketIOClient.reconnectCallback;
                        if (reconnectCallback != null) {
                            reconnectCallback.onReconnect();
                        }
                    }
                }
            }
        });
    }
    
    private void reportDisconnect(final Exception ex) {
        if (ex != null) {
            this.request.loge("socket.io disconnected", ex);
        }
        else {
            this.request.logi("socket.io disconnected");
        }
        this.select(null, (SelectCallback)new SelectCallback() {
            @Override
            public void onSelect(final SocketIOClient socketIOClient) {
                if (socketIOClient.connected) {
                    socketIOClient.disconnected = true;
                    final DisconnectCallback disconnectCallback = socketIOClient.getDisconnectCallback();
                    if (disconnectCallback != null) {
                        disconnectCallback.onDisconnect(ex);
                    }
                }
                else {
                    final ConnectCallback connectCallback = socketIOClient.connectCallback;
                    if (connectCallback != null) {
                        connectCallback.onConnectCompleted(ex, socketIOClient);
                    }
                }
            }
        });
        this.delayReconnect();
    }
    
    private void reportError(final String s, final String s2) {
        this.select(s, (SelectCallback)new SelectCallback() {
            @Override
            public void onSelect(final SocketIOClient socketIOClient) {
                final ErrorCallback errorCallback = socketIOClient.errorCallback;
                if (errorCallback != null) {
                    errorCallback.onError(s2);
                }
            }
        });
    }
    
    private void reportEvent(final String s, final String s2, final JSONArray jsonArray, final Acknowledge acknowledge) {
        this.select(s, (SelectCallback)new SelectCallback() {
            @Override
            public void onSelect(final SocketIOClient socketIOClient) {
                socketIOClient.onEvent(s2, jsonArray, acknowledge);
            }
        });
    }
    
    private void reportJson(final String s, final JSONObject jsonObject, final Acknowledge acknowledge) {
        this.select(s, (SelectCallback)new SelectCallback() {
            @Override
            public void onSelect(final SocketIOClient socketIOClient) {
                final JSONCallback jsonCallback = socketIOClient.jsonCallback;
                if (jsonCallback != null) {
                    jsonCallback.onJSON(jsonObject, acknowledge);
                }
            }
        });
    }
    
    private void reportString(final String s, final String s2, final Acknowledge acknowledge) {
        this.select(s, (SelectCallback)new SelectCallback() {
            @Override
            public void onSelect(final SocketIOClient socketIOClient) {
                final StringCallback stringCallback = socketIOClient.stringCallback;
                if (stringCallback != null) {
                    stringCallback.onString(s2, acknowledge);
                }
            }
        });
    }
    
    private void select(final String s, final SelectCallback selectCallback) {
        for (final SocketIOClient socketIOClient : this.clients) {
            if (s == null || TextUtils.equals((CharSequence)socketIOClient.endpoint, (CharSequence)s)) {
                selectCallback.onSelect(socketIOClient);
            }
        }
    }
    
    public void connect(final SocketIOClient socketIOClient) {
        if (!this.clients.contains(socketIOClient)) {
            this.clients.add(socketIOClient);
        }
        this.transport.send(String.format(Locale.ENGLISH, "1::%s", socketIOClient.endpoint));
    }
    
    public void disconnect(final SocketIOClient socketIOClient) {
        this.clients.remove(socketIOClient);
        boolean b = true;
        final Iterator<SocketIOClient> iterator = this.clients.iterator();
        while (iterator.hasNext()) {
            if (TextUtils.equals((CharSequence)iterator.next().endpoint, (CharSequence)socketIOClient.endpoint) || TextUtils.isEmpty((CharSequence)socketIOClient.endpoint)) {
                b = false;
                break;
            }
        }
        final SocketIOTransport transport = this.transport;
        if (b && transport != null) {
            transport.send(String.format(Locale.ENGLISH, "0::%s", socketIOClient.endpoint));
        }
        if (this.clients.size() <= 0 && transport != null) {
            transport.setStringCallback(null);
            transport.setClosedCallback(null);
            transport.disconnect();
            this.transport = null;
        }
    }
    
    public void emitRaw(final int n, final SocketIOClient socketIOClient, final String s, final Acknowledge acknowledge) {
        String string = "";
        if (acknowledge != null) {
            final String string2 = "" + this.ackCount++;
            string = string2 + "+";
            this.acknowledges.put(string2, acknowledge);
        }
        this.transport.send(String.format(Locale.ENGLISH, "%d:%s:%s:%s", n, string, socketIOClient.endpoint, s));
    }
    
    public boolean isConnected() {
        return this.transport != null && this.transport.isConnected();
    }
    
    void reconnect(final DependentCancellable dependentCancellable) {
        if (!this.isConnected()) {
            if (this.connecting != null && !this.connecting.isDone() && !this.connecting.isCancelled()) {
                if (dependentCancellable != null) {
                    dependentCancellable.setParent(this.connecting);
                }
            }
            else {
                this.request.logi("Reconnecting socket.io");
                this.connecting = this.httpClient.executeString(this.request, null).then(new TransformFuture<SocketIOTransport, String>() {
                    @Override
                    protected void transform(final String s) throws Exception {
                        final String[] split = s.split(":");
                        final String s2 = split[0];
                        if (!"".equals(split[1])) {
                            SocketIOConnection.this.heartbeat = 1000 * (Integer.parseInt(split[1]) / 2);
                        }
                        else {
                            SocketIOConnection.this.heartbeat = 0;
                        }
                        final HashSet set = new HashSet(Arrays.asList(split[3].split(",")));
                        final SimpleFuture<Object> complete = new SimpleFuture<Object>();
                        if (set.contains("websocket")) {
                            SocketIOConnection.this.httpClient.websocket(Uri.parse(SocketIOConnection.this.request.getUri().toString()).buildUpon().appendPath("websocket").appendPath(s2).build().toString(), null, null).setCallback(new FutureCallback<WebSocket>() {
                                @Override
                                public void onCompleted(final Exception complete, final WebSocket webSocket) {
                                    if (complete != null) {
                                        complete.setComplete(complete);
                                    }
                                    else {
                                        complete.setComplete(new WebSocketTransport(webSocket, s2));
                                    }
                                }
                            });
                        }
                        else {
                            if (!set.contains("xhr-polling")) {
                                throw new SocketIOException("transport not supported");
                            }
                            complete.setComplete(new XHRPollingTransport(SocketIOConnection.this.httpClient, Uri.parse(SocketIOConnection.this.request.getUri().toString()).buildUpon().appendPath("xhr-polling").appendPath(s2).build().toString(), s2));
                        }
                        this.setComplete((Future<T>)complete);
                    }
                }).setCallback((FutureCallback<T>)new FutureCallback<SocketIOTransport>() {
                    @Override
                    public void onCompleted(final Exception ex, final SocketIOTransport transport) {
                        if (ex != null) {
                            SocketIOConnection.this.reportDisconnect(ex);
                        }
                        else {
                            SocketIOConnection.this.reconnectDelay = SocketIOConnection.this.request.config.reconnectDelay;
                            SocketIOConnection.this.transport = transport;
                            SocketIOConnection.this.attach();
                        }
                    }
                });
                if (dependentCancellable != null) {
                    dependentCancellable.setParent(this.connecting);
                }
            }
        }
    }
    
    void setupHeartbeat() {
        new Runnable() {
            @Override
            public void run() {
                final SocketIOTransport transport = SocketIOConnection.this.transport;
                if (SocketIOConnection.this.heartbeat > 0 && transport != null && transport.isConnected()) {
                    transport.send("2:::");
                    transport.getServer().postDelayed(this, SocketIOConnection.this.heartbeat);
                }
            }
        }.run();
    }
    
    private interface SelectCallback
    {
        void onSelect(final SocketIOClient p0);
    }
}
