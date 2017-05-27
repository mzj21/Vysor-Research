// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

public interface RequestLine
{
    String getMethod();
    
    ProtocolVersion getProtocolVersion();
    
    String getUri();
}
