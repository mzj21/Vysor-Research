// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.net.URLConnection;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

@zziy
public class zzll
{
    public HttpURLConnection zzb(final String s, final int n) throws IOException {
        URL url = new URL(s);
        int n2 = 0;
        while (true) {
            final int n3 = n2 + 1;
            if (n3 > 20) {
                throw new IOException("Too many redirects (20)");
            }
            final URLConnection openConnection = url.openConnection();
            openConnection.setConnectTimeout(n);
            openConnection.setReadTimeout(n);
            if (!(openConnection instanceof HttpURLConnection)) {
                throw new IOException("Invalid protocol.");
            }
            final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
            httpURLConnection.setInstanceFollowRedirects(false);
            if (httpURLConnection.getResponseCode() / 100 != 3) {
                return httpURLConnection;
            }
            final String headerField = httpURLConnection.getHeaderField("Location");
            if (headerField == null) {
                throw new IOException("Missing Location header in redirect");
            }
            final URL url2 = new URL(url, headerField);
            final String protocol = url2.getProtocol();
            if (protocol == null) {
                throw new IOException("Protocol is null");
            }
            if (!protocol.equals("http") && !protocol.equals("https")) {
                final String value = String.valueOf(protocol);
                String concat;
                if (value.length() != 0) {
                    concat = "Unsupported scheme: ".concat(value);
                }
                else {
                    concat = new String("Unsupported scheme: ");
                }
                throw new IOException(concat);
            }
            final String value2 = String.valueOf(headerField);
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Redirecting to ".concat(value2);
            }
            else {
                concat2 = new String("Redirecting to ");
            }
            zzb.zzdd(concat2);
            httpURLConnection.disconnect();
            n2 = n3;
            url = url2;
        }
    }
}
