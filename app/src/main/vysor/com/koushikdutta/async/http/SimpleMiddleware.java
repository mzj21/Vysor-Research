// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.future.Cancellable;

public class SimpleMiddleware implements AsyncHttpClientMiddleware
{
    @Override
    public boolean exchangeHeaders(final OnExchangeHeaderData onExchangeHeaderData) {
        return false;
    }
    
    @Override
    public Cancellable getSocket(final GetSocketData getSocketData) {
        return null;
    }
    
    @Override
    public void onBodyDecoder(final OnBodyDataOnRequestSentData onBodyDataOnRequestSentData) {
    }
    
    @Override
    public void onHeadersReceived(final OnHeadersReceivedDataOnRequestSentData onHeadersReceivedDataOnRequestSentData) {
    }
    
    @Override
    public void onRequest(final OnRequestData onRequestData) {
    }
    
    @Override
    public void onRequestSent(final OnRequestSentData onRequestSentData) {
    }
    
    @Override
    public void onResponseComplete(final OnResponseCompleteDataOnRequestSentData onResponseCompleteDataOnRequestSentData) {
    }
}
