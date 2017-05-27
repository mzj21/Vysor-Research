// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import com.koushikdutta.async.http.Protocol;
import java.util.List;

final class SpdyTransport
{
    private static final List<String> HTTP_2_PROHIBITED_HEADERS;
    private static final List<String> SPDY_3_PROHIBITED_HEADERS;
    
    static {
        SPDY_3_PROHIBITED_HEADERS = Util.immutableList("connection", "host", "keep-alive", "proxy-connection", "transfer-encoding");
        HTTP_2_PROHIBITED_HEADERS = Util.immutableList("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");
    }
    
    static boolean isProhibitedHeader(final Protocol protocol, final String s) {
        boolean b;
        if (protocol == Protocol.SPDY_3) {
            b = SpdyTransport.SPDY_3_PROHIBITED_HEADERS.contains(s.toLowerCase());
        }
        else {
            if (protocol != Protocol.HTTP_2) {
                throw new AssertionError(protocol);
            }
            b = SpdyTransport.HTTP_2_PROHIBITED_HEADERS.contains(s.toLowerCase());
        }
        return b;
    }
}
