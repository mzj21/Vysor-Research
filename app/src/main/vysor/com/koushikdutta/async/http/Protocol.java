// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import java.util.Hashtable;

public enum Protocol
{
    HTTP_1_0("http/1.0"), 
    HTTP_1_1("http/1.1"), 
    HTTP_2("h2-13") {
        @Override
        public boolean needsSpdyConnection() {
            return true;
        }
    }, 
    SPDY_3("spdy/3.1") {
        @Override
        public boolean needsSpdyConnection() {
            return true;
        }
    };
    
    private static final Hashtable<String, Protocol> protocols;
    private final String protocol;
    
    static {
        (protocols = new Hashtable<String, Protocol>()).put(Protocol.HTTP_1_0.toString(), Protocol.HTTP_1_0);
        Protocol.protocols.put(Protocol.HTTP_1_1.toString(), Protocol.HTTP_1_1);
        Protocol.protocols.put(Protocol.SPDY_3.toString(), Protocol.SPDY_3);
        Protocol.protocols.put(Protocol.HTTP_2.toString(), Protocol.HTTP_2);
    }
    
    private Protocol(final String protocol) {
        this.protocol = protocol;
    }
    
    public static Protocol get(final String s) {
        Protocol protocol;
        if (s == null) {
            protocol = null;
        }
        else {
            protocol = Protocol.protocols.get(s.toLowerCase());
        }
        return protocol;
    }
    
    public boolean needsSpdyConnection() {
        return false;
    }
    
    @Override
    public String toString() {
        return this.protocol;
    }
}
