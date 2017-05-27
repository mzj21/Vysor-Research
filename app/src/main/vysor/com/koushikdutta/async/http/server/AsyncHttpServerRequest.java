// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.server;

import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.http.Multimap;
import java.util.regex.Matcher;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.DataEmitter;

public interface AsyncHttpServerRequest extends DataEmitter
{
    AsyncHttpRequestBody getBody();
    
    Headers getHeaders();
    
    Matcher getMatcher();
    
    String getMethod();
    
    String getPath();
    
    Multimap getQuery();
    
    AsyncSocket getSocket();
}
