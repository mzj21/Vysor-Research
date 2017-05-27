// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import java.lang.reflect.Field;
import javax.net.ssl.SSLEngine;
import java.util.Hashtable;

public class SSLEngineSNIConfigurator implements AsyncSSLEngineConfigurator
{
    Hashtable<String, EngineHolder> holders;
    
    public SSLEngineSNIConfigurator() {
        this.holders = new Hashtable<String, EngineHolder>();
    }
    
    @Override
    public void configureEngine(final SSLEngine sslEngine, final AsyncHttpClientMiddleware.GetSocketData getSocketData, final String s, final int n) {
        final String canonicalName = sslEngine.getClass().getCanonicalName();
        EngineHolder engineHolder = this.holders.get(canonicalName);
        if (engineHolder == null) {
            engineHolder = new EngineHolder(sslEngine.getClass());
            this.holders.put(canonicalName, engineHolder);
        }
        engineHolder.configureEngine(sslEngine, getSocketData, s, n);
    }
    
    private static class EngineHolder implements AsyncSSLEngineConfigurator
    {
        Field peerHost;
        Field peerPort;
        Field sslParameters;
        Field useSni;
        
        public EngineHolder(final Class clazz) {
            try {
                (this.peerHost = clazz.getSuperclass().getDeclaredField("peerHost")).setAccessible(true);
                (this.peerPort = clazz.getSuperclass().getDeclaredField("peerPort")).setAccessible(true);
                (this.sslParameters = clazz.getDeclaredField("sslParameters")).setAccessible(true);
                (this.useSni = this.sslParameters.getType().getDeclaredField("useSni")).setAccessible(true);
            }
            catch (NoSuchFieldException ex) {}
        }
        
        @Override
        public void configureEngine(final SSLEngine sslEngine, final AsyncHttpClientMiddleware.GetSocketData getSocketData, final String s, final int n) {
            if (this.useSni != null) {
                try {
                    this.peerHost.set(sslEngine, s);
                    this.peerPort.set(sslEngine, n);
                    this.useSni.set(this.sslParameters.get(sslEngine), true);
                }
                catch (IllegalAccessException ex) {}
            }
        }
    }
}
