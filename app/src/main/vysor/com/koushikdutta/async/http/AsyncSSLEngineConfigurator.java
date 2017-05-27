// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import javax.net.ssl.SSLEngine;

public interface AsyncSSLEngineConfigurator
{
    void configureEngine(final SSLEngine p0, final AsyncHttpClientMiddleware.GetSocketData p1, final String p2, final int p3);
}
