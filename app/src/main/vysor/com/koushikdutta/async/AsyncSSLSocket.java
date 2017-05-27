// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import javax.net.ssl.SSLEngine;
import java.security.cert.X509Certificate;

public interface AsyncSSLSocket extends AsyncSocket
{
    X509Certificate[] getPeerCertificates();
    
    SSLEngine getSSLEngine();
}
