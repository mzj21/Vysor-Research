// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

public class AsyncSSLException extends Exception
{
    private boolean mIgnore;
    
    public AsyncSSLException(final Throwable t) {
        super("Peer not trusted by any of the system trust managers.", t);
        this.mIgnore = false;
    }
    
    public boolean getIgnore() {
        return this.mIgnore;
    }
    
    public void setIgnore(final boolean mIgnore) {
        this.mIgnore = mIgnore;
    }
}
