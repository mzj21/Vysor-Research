// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.util.UntypedHashtable;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.ConnectCallback;
import com.koushikdutta.async.future.Cancellable;

public interface AsyncHttpClientMiddleware
{
    boolean exchangeHeaders(final OnExchangeHeaderData p0);
    
    Cancellable getSocket(final GetSocketData p0);
    
    void onBodyDecoder(final OnBodyDataOnRequestSentData p0);
    
    void onHeadersReceived(final OnHeadersReceivedDataOnRequestSentData p0);
    
    void onRequest(final OnRequestData p0);
    
    void onRequestSent(final OnRequestSentData p0);
    
    void onResponseComplete(final OnResponseCompleteDataOnRequestSentData p0);
    
    public static class GetSocketData extends OnRequestData
    {
        public ConnectCallback connectCallback;
        public String protocol;
        public Cancellable socketCancellable;
    }
    
    public static class OnBodyDataOnRequestSentData extends OnHeadersReceivedDataOnRequestSentData
    {
        public DataEmitter bodyEmitter;
    }
    
    public static class OnExchangeHeaderData extends GetSocketData
    {
        public CompletedCallback receiveHeadersCallback;
        public ResponseHead response;
        public CompletedCallback sendHeadersCallback;
        public AsyncSocket socket;
    }
    
    public static class OnHeadersReceivedDataOnRequestSentData extends OnRequestSentData
    {
    }
    
    public static class OnRequestData
    {
        public AsyncHttpRequest request;
        public UntypedHashtable state;
        
        public OnRequestData() {
            this.state = new UntypedHashtable();
        }
    }
    
    public static class OnRequestSentData extends OnExchangeHeaderData
    {
    }
    
    public static class OnResponseCompleteDataOnRequestSentData extends OnBodyDataOnRequestSentData
    {
        public Exception exception;
    }
    
    public interface ResponseHead
    {
        int code();
        
        ResponseHead code(final int p0);
        
        DataEmitter emitter();
        
        ResponseHead emitter(final DataEmitter p0);
        
        ResponseHead headers(final Headers p0);
        
        Headers headers();
        
        ResponseHead message(final String p0);
        
        String message();
        
        ResponseHead protocol(final String p0);
        
        String protocol();
        
        DataSink sink();
        
        ResponseHead sink(final DataSink p0);
        
        AsyncSocket socket();
    }
}
