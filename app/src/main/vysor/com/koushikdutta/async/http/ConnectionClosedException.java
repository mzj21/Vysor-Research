// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

public class ConnectionClosedException extends Exception
{
    public ConnectionClosedException(final String s) {
        super(s);
    }
    
    public ConnectionClosedException(final String s, final Throwable t) {
        super(s, t);
    }
}
