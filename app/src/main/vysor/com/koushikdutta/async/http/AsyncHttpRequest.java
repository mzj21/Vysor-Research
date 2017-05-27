// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.AsyncSSLException;
import android.util.Log;
import java.util.Locale;
import android.net.Uri;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;

public class AsyncHttpRequest
{
    public static final int DEFAULT_TIMEOUT = 30000;
    String LOGTAG;
    long executionTime;
    int logLevel;
    private AsyncHttpRequestBody mBody;
    private boolean mFollowRedirect;
    private String mMethod;
    private Headers mRawHeaders;
    int mTimeout;
    String proxyHost;
    int proxyPort;
    Uri uri;
    
    public AsyncHttpRequest(final Uri uri, final String s) {
        this(uri, s, null);
    }
    
    public AsyncHttpRequest(final Uri uri, final String mMethod, final Headers mRawHeaders) {
        this.mRawHeaders = new Headers();
        this.mFollowRedirect = true;
        this.mTimeout = 30000;
        this.proxyPort = -1;
        assert uri != null;
        this.mMethod = mMethod;
        this.uri = uri;
        if (mRawHeaders == null) {
            this.mRawHeaders = new Headers();
        }
        else {
            this.mRawHeaders = mRawHeaders;
        }
        if (mRawHeaders == null) {
            setDefaultHeaders(this.mRawHeaders, uri);
        }
    }
    
    protected static String getDefaultUserAgent() {
        String s = System.getProperty("http.agent");
        if (s == null) {
            s = "Java" + System.getProperty("java.version");
        }
        return s;
    }
    
    private String getLogMessage(final String s) {
        long n;
        if (this.executionTime != 0L) {
            n = System.currentTimeMillis() - this.executionTime;
        }
        else {
            n = 0L;
        }
        return String.format(Locale.ENGLISH, "(%d ms) %s: %s", n, this.getUri(), s);
    }
    
    public static void setDefaultHeaders(final Headers headers, final Uri uri) {
        if (uri != null) {
            String s = uri.getHost();
            if (uri.getPort() != -1) {
                s = s + ":" + uri.getPort();
            }
            if (s != null) {
                headers.set("Host", s);
            }
        }
        headers.set("User-Agent", getDefaultUserAgent());
        headers.set("Accept-Encoding", "gzip, deflate");
        headers.set("Connection", "keep-alive");
        headers.set("Accept", "*/*");
    }
    
    public AsyncHttpRequest addHeader(final String s, final String s2) {
        this.getHeaders().add(s, s2);
        return this;
    }
    
    public void disableProxy() {
        this.proxyHost = null;
        this.proxyPort = -1;
    }
    
    public void enableProxy(final String proxyHost, final int proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }
    
    public AsyncHttpRequestBody getBody() {
        return this.mBody;
    }
    
    public boolean getFollowRedirect() {
        return this.mFollowRedirect;
    }
    
    public Headers getHeaders() {
        return this.mRawHeaders;
    }
    
    public int getLogLevel() {
        return this.logLevel;
    }
    
    public String getLogTag() {
        return this.LOGTAG;
    }
    
    public String getMethod() {
        return this.mMethod;
    }
    
    public String getProxyHost() {
        return this.proxyHost;
    }
    
    public int getProxyPort() {
        return this.proxyPort;
    }
    
    public RequestLine getRequestLine() {
        return new RequestLine() {
            @Override
            public String getMethod() {
                return AsyncHttpRequest.this.mMethod;
            }
            
            @Override
            public ProtocolVersion getProtocolVersion() {
                return new ProtocolVersion("HTTP", 1, 1);
            }
            
            @Override
            public String getUri() {
                return AsyncHttpRequest.this.getUri().toString();
            }
            
            @Override
            public String toString() {
                String s;
                if (AsyncHttpRequest.this.proxyHost != null) {
                    s = String.format(Locale.ENGLISH, "%s %s HTTP/1.1", AsyncHttpRequest.this.mMethod, AsyncHttpRequest.this.getUri());
                }
                else {
                    String s2 = AsyncHttpRequest.this.getUri().getEncodedPath();
                    if (s2 == null || s2.length() == 0) {
                        s2 = "/";
                    }
                    final String encodedQuery = AsyncHttpRequest.this.getUri().getEncodedQuery();
                    if (encodedQuery != null && encodedQuery.length() != 0) {
                        s2 = s2 + "?" + encodedQuery;
                    }
                    s = String.format(Locale.ENGLISH, "%s %s HTTP/1.1", AsyncHttpRequest.this.mMethod, s2);
                }
                return s;
            }
        };
    }
    
    public int getTimeout() {
        return this.mTimeout;
    }
    
    public Uri getUri() {
        return this.uri;
    }
    
    public void logd(final String s) {
        if (this.LOGTAG != null && this.logLevel <= 3) {
            Log.d(this.LOGTAG, this.getLogMessage(s));
        }
    }
    
    public void logd(final String s, final Exception ex) {
        if (this.LOGTAG != null && this.logLevel <= 3) {
            Log.d(this.LOGTAG, this.getLogMessage(s));
            Log.d(this.LOGTAG, ex.getMessage(), (Throwable)ex);
        }
    }
    
    public void loge(final String s) {
        if (this.LOGTAG != null && this.logLevel <= 6) {
            Log.e(this.LOGTAG, this.getLogMessage(s));
        }
    }
    
    public void loge(final String s, final Exception ex) {
        if (this.LOGTAG != null && this.logLevel <= 6) {
            Log.e(this.LOGTAG, this.getLogMessage(s));
            Log.e(this.LOGTAG, ex.getMessage(), (Throwable)ex);
        }
    }
    
    public void logi(final String s) {
        if (this.LOGTAG != null && this.logLevel <= 4) {
            Log.i(this.LOGTAG, this.getLogMessage(s));
        }
    }
    
    public void logv(final String s) {
        if (this.LOGTAG != null && this.logLevel <= 2) {
            Log.v(this.LOGTAG, this.getLogMessage(s));
        }
    }
    
    public void logw(final String s) {
        if (this.LOGTAG != null && this.logLevel <= 5) {
            Log.w(this.LOGTAG, this.getLogMessage(s));
        }
    }
    
    public void onHandshakeException(final AsyncSSLException ex) {
    }
    
    public void setBody(final AsyncHttpRequestBody mBody) {
        this.mBody = mBody;
    }
    
    public AsyncHttpRequest setFollowRedirect(final boolean mFollowRedirect) {
        this.mFollowRedirect = mFollowRedirect;
        return this;
    }
    
    public AsyncHttpRequest setHeader(final String s, final String s2) {
        this.getHeaders().set(s, s2);
        return this;
    }
    
    public void setLogging(final String logtag, final int logLevel) {
        this.LOGTAG = logtag;
        this.logLevel = logLevel;
    }
    
    public AsyncHttpRequest setMethod(final String mMethod) {
        if (this.getClass() != AsyncHttpRequest.class) {
            throw new UnsupportedOperationException("can't change method on a subclass of AsyncHttpRequest");
        }
        this.mMethod = mMethod;
        return this;
    }
    
    public AsyncHttpRequest setTimeout(final int mTimeout) {
        this.mTimeout = mTimeout;
        return this;
    }
    
    @Override
    public String toString() {
        String s;
        if (this.mRawHeaders == null) {
            s = super.toString();
        }
        else {
            s = this.mRawHeaders.toPrefixString(this.uri.toString());
        }
        return s;
    }
}
