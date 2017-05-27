// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.socketio;

import android.net.Uri;
import com.koushikdutta.async.http.AsyncHttpPost;

public class SocketIORequest extends AsyncHttpPost
{
    Config config;
    String endpoint;
    String query;
    
    public SocketIORequest(final String s) {
        this(s, "");
    }
    
    public SocketIORequest(final String s, final String s2) {
        this(s, s2, null);
    }
    
    public SocketIORequest(final String s, final String s2, final String s3) {
        this(s, s2, s3, null);
    }
    
    public SocketIORequest(final String s, final String endpoint, final String query, Config config) {
        final StringBuilder append = new StringBuilder().append(s);
        String string;
        if (query == null) {
            string = "";
        }
        else {
            string = "?" + query;
        }
        super(Uri.parse(append.append(string).toString()).buildUpon().encodedPath("/socket.io/1/").build().toString());
        if (config == null) {
            config = new Config();
        }
        this.config = config;
        this.endpoint = endpoint;
        this.query = query;
    }
    
    public Config getConfig() {
        return this.config;
    }
    
    public String getEndpoint() {
        return this.endpoint;
    }
    
    public String getQuery() {
        return this.query;
    }
    
    public static class Config
    {
        boolean randomizeReconnectDelay;
        long reconnectDelay;
        long reconnectDelayMax;
        
        public Config() {
            this.randomizeReconnectDelay = false;
            this.reconnectDelay = 1000L;
            this.reconnectDelayMax = 0L;
        }
        
        public long getReconnectDelay() {
            return this.reconnectDelay;
        }
        
        public long getReconnectDelayMax() {
            return this.reconnectDelayMax;
        }
        
        public boolean isRandomizeReconnectDelay() {
            return this.randomizeReconnectDelay;
        }
        
        public void setRandomizeReconnectDelay(final boolean randomizeReconnectDelay) {
            this.randomizeReconnectDelay = randomizeReconnectDelay;
        }
        
        public void setReconnectDelay(final long reconnectDelay) {
            if (reconnectDelay < 0L) {
                throw new IllegalArgumentException("reconnectDelay must be >= 0");
            }
            this.reconnectDelay = reconnectDelay;
        }
        
        public void setReconnectDelayMax(final long reconnectDelayMax) {
            if (this.reconnectDelay < 0L) {
                throw new IllegalArgumentException("reconnectDelayMax must be >= 0");
            }
            this.reconnectDelayMax = reconnectDelayMax;
        }
    }
}
