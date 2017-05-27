// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import java.util.List;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.ProtocolVersion;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import java.util.Map;
import java.io.DataOutputStream;
import java.io.InputStream;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.HttpEntity;
import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.SSLSocketFactory;

public class zzz implements zzy
{
    private final zza zzce;
    private final SSLSocketFactory zzcf;
    
    public zzz() {
        this(null);
    }
    
    public zzz(final zza zza) {
        this(zza, null);
    }
    
    public zzz(final zza zzce, final SSLSocketFactory zzcf) {
        this.zzce = zzce;
        this.zzcf = zzcf;
    }
    
    private HttpURLConnection zza(final URL url, final zzk<?> zzk) throws IOException {
        final HttpURLConnection zza = this.zza(url);
        final int zzs = zzk.zzs();
        zza.setConnectTimeout(zzs);
        zza.setReadTimeout(zzs);
        zza.setUseCaches(false);
        zza.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.zzcf != null) {
            ((HttpsURLConnection)zza).setSSLSocketFactory(this.zzcf);
        }
        return zza;
    }
    
    private static HttpEntity zza(final HttpURLConnection httpURLConnection) {
        final BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        while (true) {
            try {
                final InputStream content = httpURLConnection.getInputStream();
                basicHttpEntity.setContent(content);
                basicHttpEntity.setContentLength((long)httpURLConnection.getContentLength());
                basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
                basicHttpEntity.setContentType(httpURLConnection.getContentType());
                return (HttpEntity)basicHttpEntity;
            }
            catch (IOException ex) {
                final InputStream content = httpURLConnection.getErrorStream();
                continue;
            }
            break;
        }
    }
    
    static void zza(final HttpURLConnection httpURLConnection, final zzk<?> zzk) throws IOException, com.google.android.gms.internal.zza {
        switch (zzk.getMethod()) {
            default: {
                throw new IllegalStateException("Unknown method type.");
            }
            case -1: {
                final byte[] zzl = zzk.zzl();
                if (zzl != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", zzk.zzk());
                    final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(zzl);
                    dataOutputStream.close();
                    break;
                }
                break;
            }
            case 0: {
                httpURLConnection.setRequestMethod("GET");
                break;
            }
            case 3: {
                httpURLConnection.setRequestMethod("DELETE");
                break;
            }
            case 1: {
                httpURLConnection.setRequestMethod("POST");
                zzb(httpURLConnection, zzk);
                break;
            }
            case 2: {
                httpURLConnection.setRequestMethod("PUT");
                zzb(httpURLConnection, zzk);
                break;
            }
            case 4: {
                httpURLConnection.setRequestMethod("HEAD");
                break;
            }
            case 5: {
                httpURLConnection.setRequestMethod("OPTIONS");
                break;
            }
            case 6: {
                httpURLConnection.setRequestMethod("TRACE");
                break;
            }
            case 7: {
                httpURLConnection.setRequestMethod("PATCH");
                zzb(httpURLConnection, zzk);
                break;
            }
        }
    }
    
    private static void zzb(final HttpURLConnection httpURLConnection, final zzk<?> zzk) throws IOException, com.google.android.gms.internal.zza {
        final byte[] zzp = zzk.zzp();
        if (zzp != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", zzk.zzo());
            final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzp);
            dataOutputStream.close();
        }
    }
    
    protected HttpURLConnection zza(final URL url) throws IOException {
        return (HttpURLConnection)url.openConnection();
    }
    
    @Override
    public HttpResponse zza(final zzk<?> zzk, final Map<String, String> map) throws IOException, com.google.android.gms.internal.zza {
        final String url = zzk.getUrl();
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.putAll(zzk.getHeaders());
        hashMap.putAll(map);
        String zzh;
        if (this.zzce != null) {
            zzh = this.zzce.zzh(url);
            if (zzh == null) {
                final String value = String.valueOf(url);
                String concat;
                if (value.length() != 0) {
                    concat = "URL blocked by rewriter: ".concat(value);
                }
                else {
                    concat = new String("URL blocked by rewriter: ");
                }
                throw new IOException(concat);
            }
        }
        else {
            zzh = url;
        }
        final HttpURLConnection zza = this.zza(new URL(zzh), zzk);
        for (final String s : hashMap.keySet()) {
            zza.addRequestProperty(s, hashMap.get(s));
        }
        zza(zza, zzk);
        final ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (zza.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        final BasicHttpResponse basicHttpResponse = new BasicHttpResponse((StatusLine)new BasicStatusLine(protocolVersion, zza.getResponseCode(), zza.getResponseMessage()));
        basicHttpResponse.setEntity(zza(zza));
        for (final Map.Entry<String, List<String>> entry : zza.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader((Header)new BasicHeader((String)entry.getKey(), (String)entry.getValue().get(0)));
            }
        }
        return (HttpResponse)basicHttpResponse;
    }
    
    public interface zza
    {
        String zzh(final String p0);
    }
}
