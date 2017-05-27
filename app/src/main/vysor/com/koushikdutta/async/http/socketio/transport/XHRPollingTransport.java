// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.socketio.transport;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.http.body.StringBody;
import com.koushikdutta.async.http.AsyncHttpPost;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.AsyncHttpGet;
import android.net.Uri;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.AsyncHttpClient;

public class XHRPollingTransport implements SocketIOTransport
{
    private static final String SEPARATOR = "\ufffd";
    private AsyncHttpClient client;
    private CompletedCallback closedCallback;
    private boolean connected;
    private String sessionId;
    private Uri sessionUrl;
    private StringCallback stringCallback;
    
    public XHRPollingTransport(final AsyncHttpClient client, final String s, final String sessionId) {
        this.client = client;
        this.sessionUrl = Uri.parse(s);
        this.sessionId = sessionId;
        this.doLongPolling();
        this.connected = true;
    }
    
    private void close(final Exception ex) {
        if (this.closedCallback != null) {
            this.closedCallback.onCompleted(ex);
        }
    }
    
    private String computedRequestUrl() {
        return this.sessionUrl.buildUpon().appendQueryParameter("t", String.valueOf(System.currentTimeMillis())).build().toString();
    }
    
    private void doLongPolling() {
        this.client.executeString(new AsyncHttpGet(this.computedRequestUrl()), (AsyncHttpClient.StringCallback)new AsyncHttpClient.StringCallback() {
            @Override
            public void onCompleted(final Exception ex, final AsyncHttpResponse asyncHttpResponse, final String s) {
                if (ex != null) {
                    XHRPollingTransport.this.close(ex);
                }
                else {
                    XHRPollingTransport.this.sendResult(s);
                    XHRPollingTransport.this.doLongPolling();
                }
            }
        });
    }
    
    private void postMessage(final String s) {
        if (s.startsWith("5")) {
            final AsyncHttpPost asyncHttpPost = new AsyncHttpPost(this.computedRequestUrl());
            asyncHttpPost.setBody(new StringBody(s));
            this.client.executeString(asyncHttpPost, null);
        }
    }
    
    private void sendResult(final String s) {
        if (this.stringCallback != null) {
            if (!s.contains("\ufffd")) {
                this.stringCallback.onStringAvailable(s);
            }
            else {
                final String[] split = s.split("\ufffd");
                for (int i = 1; i < split.length; i += 2) {
                    this.stringCallback.onStringAvailable(split[i + 1]);
                }
            }
        }
    }
    
    @Override
    public void disconnect() {
        this.connected = false;
        this.close(null);
    }
    
    @Override
    public AsyncServer getServer() {
        return this.client.getServer();
    }
    
    @Override
    public String getSessionId() {
        return this.sessionId;
    }
    
    @Override
    public boolean heartbeats() {
        return false;
    }
    
    @Override
    public boolean isConnected() {
        return this.connected;
    }
    
    @Override
    public void send(final String s) {
        if (s.startsWith("5")) {
            this.postMessage(s);
        }
        else {
            final AsyncHttpPost asyncHttpPost = new AsyncHttpPost(this.computedRequestUrl());
            asyncHttpPost.setBody(new StringBody(s));
            this.client.executeString(asyncHttpPost, (AsyncHttpClient.StringCallback)new AsyncHttpClient.StringCallback() {
                @Override
                public void onCompleted(final Exception ex, final AsyncHttpResponse asyncHttpResponse, final String s) {
                    if (ex != null) {
                        XHRPollingTransport.this.close(ex);
                    }
                    else {
                        XHRPollingTransport.this.sendResult(s);
                    }
                }
            });
        }
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback closedCallback) {
        this.closedCallback = closedCallback;
    }
    
    @Override
    public void setStringCallback(final StringCallback stringCallback) {
        this.stringCallback = stringCallback;
    }
}
