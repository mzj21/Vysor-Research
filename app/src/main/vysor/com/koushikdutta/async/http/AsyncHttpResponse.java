// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.DataEmitter;

public interface AsyncHttpResponse extends DataEmitter
{
    int code();
    
    AsyncSocket detachSocket();
    
    AsyncHttpRequest getRequest();
    
    Headers headers();
    
    String message();
    
    String protocol();
}
