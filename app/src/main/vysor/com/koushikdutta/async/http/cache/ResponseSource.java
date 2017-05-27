// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.cache;

enum ResponseSource
{
    CACHE, 
    CONDITIONAL_CACHE, 
    NETWORK;
    
    public boolean requiresConnection() {
        return this == ResponseSource.CONDITIONAL_CACHE || this == ResponseSource.NETWORK;
    }
}
