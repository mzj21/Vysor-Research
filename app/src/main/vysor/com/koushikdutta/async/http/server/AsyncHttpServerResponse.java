// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.server;

import java.io.InputStream;
import java.io.File;
import org.json.JSONObject;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataSink;

public interface AsyncHttpServerResponse extends DataSink, CompletedCallback
{
    int code();
    
    AsyncHttpServerResponse code(final int p0);
    
    void end();
    
    Headers getHeaders();
    
    AsyncSocket getSocket();
    
    void onCompleted(final Exception p0);
    
    void proxy(final AsyncHttpResponse p0);
    
    void redirect(final String p0);
    
    void send(final String p0);
    
    void send(final String p0, final String p1);
    
    void send(final String p0, final byte[] p1);
    
    void send(final JSONObject p0);
    
    void sendFile(final File p0);
    
    void sendStream(final InputStream p0, final long p1);
    
    void setContentType(final String p0);
    
    void writeHead();
}
