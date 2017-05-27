// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

public enum HeadersMode
{
    HTTP_20_HEADERS, 
    SPDY_HEADERS, 
    SPDY_REPLY, 
    SPDY_SYN_STREAM;
    
    public boolean failIfHeadersAbsent() {
        return this == HeadersMode.SPDY_HEADERS;
    }
    
    public boolean failIfHeadersPresent() {
        return this == HeadersMode.SPDY_REPLY;
    }
    
    public boolean failIfStreamAbsent() {
        return this == HeadersMode.SPDY_REPLY || this == HeadersMode.SPDY_HEADERS;
    }
    
    public boolean failIfStreamPresent() {
        return this == HeadersMode.SPDY_SYN_STREAM;
    }
}
