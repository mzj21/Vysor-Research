// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.Util;
import android.text.TextUtils;
import java.io.IOException;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.LineEmitter;
import com.koushikdutta.async.callback.CompletedCallback;
import java.util.Locale;
import android.net.Uri;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.AsyncSSLSocket;
import com.koushikdutta.async.AsyncSSLSocketWrapper;
import com.koushikdutta.async.callback.ConnectCallback;
import java.util.Iterator;
import javax.net.ssl.SSLEngine;
import java.util.ArrayList;
import javax.net.ssl.TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HostnameVerifier;
import java.util.List;

public class AsyncSSLSocketMiddleware extends AsyncSocketMiddleware
{
    protected List<AsyncSSLEngineConfigurator> engineConfigurators;
    protected HostnameVerifier hostnameVerifier;
    protected SSLContext sslContext;
    protected TrustManager[] trustManagers;
    
    public AsyncSSLSocketMiddleware(final AsyncHttpClient asyncHttpClient) {
        super(asyncHttpClient, "https", 443);
        this.engineConfigurators = new ArrayList<AsyncSSLEngineConfigurator>();
    }
    
    public void addEngineConfigurator(final AsyncSSLEngineConfigurator asyncSSLEngineConfigurator) {
        this.engineConfigurators.add(asyncSSLEngineConfigurator);
    }
    
    public void clearEngineConfigurators() {
        this.engineConfigurators.clear();
    }
    
    protected SSLEngine createConfiguredSSLEngine(final GetSocketData getSocketData, final String s, final int n) {
        final SSLEngine sslEngine = this.getSSLContext().createSSLEngine();
        final Iterator<AsyncSSLEngineConfigurator> iterator = this.engineConfigurators.iterator();
        while (iterator.hasNext()) {
            iterator.next().configureEngine(sslEngine, getSocketData, s, n);
        }
        return sslEngine;
    }
    
    protected AsyncSSLSocketWrapper.HandshakeCallback createHandshakeCallback(final GetSocketData getSocketData, final ConnectCallback connectCallback) {
        return new AsyncSSLSocketWrapper.HandshakeCallback() {
            @Override
            public void onHandshakeCompleted(final Exception ex, final AsyncSSLSocket asyncSSLSocket) {
                connectCallback.onConnectCompleted(ex, asyncSSLSocket);
            }
        };
    }
    
    public SSLContext getSSLContext() {
        SSLContext sslContext;
        if (this.sslContext != null) {
            sslContext = this.sslContext;
        }
        else {
            sslContext = AsyncSSLSocketWrapper.getDefaultSSLContext();
        }
        return sslContext;
    }
    
    public void setHostnameVerifier(final HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
    }
    
    public void setSSLContext(final SSLContext sslContext) {
        this.sslContext = sslContext;
    }
    
    public void setTrustManagers(final TrustManager[] trustManagers) {
        this.trustManagers = trustManagers;
    }
    
    protected void tryHandshake(final AsyncSocket asyncSocket, final GetSocketData getSocketData, final Uri uri, final int n, final ConnectCallback connectCallback) {
        AsyncSSLSocketWrapper.handshake(asyncSocket, uri.getHost(), n, this.createConfiguredSSLEngine(getSocketData, uri.getHost(), n), this.trustManagers, this.hostnameVerifier, true, this.createHandshakeCallback(getSocketData, connectCallback));
    }
    
    @Override
    protected ConnectCallback wrapCallback(final GetSocketData getSocketData, final Uri uri, final int n, final boolean b, final ConnectCallback connectCallback) {
        return new ConnectCallback() {
            @Override
            public void onConnectCompleted(final Exception ex, final AsyncSocket asyncSocket) {
                if (ex != null) {
                    connectCallback.onConnectCompleted(ex, asyncSocket);
                }
                else if (!b) {
                    AsyncSSLSocketMiddleware.this.tryHandshake(asyncSocket, getSocketData, uri, n, connectCallback);
                }
                else {
                    final String format = String.format(Locale.ENGLISH, "CONNECT %s:%s HTTP/1.1\r\nHost: %s\r\n\r\n", uri.getHost(), n, uri.getHost());
                    getSocketData.request.logv("Proxying: " + format);
                    Util.writeAll(asyncSocket, format.getBytes(), new CompletedCallback() {
                        @Override
                        public void onCompleted(final Exception ex) {
                            if (ex != null) {
                                connectCallback.onConnectCompleted(ex, asyncSocket);
                            }
                            else {
                                final LineEmitter dataCallback = new LineEmitter();
                                dataCallback.setLineCallback((LineEmitter.StringCallback)new LineEmitter.StringCallback() {
                                    String statusLine;
                                    
                                    @Override
                                    public void onStringAvailable(final String s) {
                                        getSocketData.request.logv(s);
                                        if (this.statusLine == null) {
                                            this.statusLine = s.trim();
                                            if (!this.statusLine.matches("HTTP/1.\\d 2\\d\\d .*")) {
                                                asyncSocket.setDataCallback(null);
                                                asyncSocket.setEndCallback(null);
                                                connectCallback.onConnectCompleted(new IOException("non 2xx status line: " + this.statusLine), asyncSocket);
                                            }
                                        }
                                        else if (TextUtils.isEmpty((CharSequence)s.trim())) {
                                            asyncSocket.setDataCallback(null);
                                            asyncSocket.setEndCallback(null);
                                            AsyncSSLSocketMiddleware.this.tryHandshake(asyncSocket, getSocketData, uri, n, connectCallback);
                                        }
                                    }
                                });
                                asyncSocket.setDataCallback(dataCallback);
                                asyncSocket.setEndCallback(new CompletedCallback() {
                                    @Override
                                    public void onCompleted(Exception ex) {
                                        if (!asyncSocket.isOpen() && ex == null) {
                                            ex = new IOException("socket closed before proxy connect response");
                                        }
                                        connectCallback.onConnectCompleted(ex, asyncSocket);
                                    }
                                });
                            }
                        }
                    });
                }
            }
        };
    }
}
