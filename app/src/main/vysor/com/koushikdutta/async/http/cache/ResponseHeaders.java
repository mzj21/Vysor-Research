// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.cache;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Comparator;
import java.util.TreeSet;
import com.koushikdutta.async.http.HttpDate;
import java.util.Collections;
import java.util.Set;
import android.net.Uri;
import java.util.Date;

final class ResponseHeaders
{
    private static final String RECEIVED_MILLIS = "X-Android-Received-Millis";
    private static final String SENT_MILLIS = "X-Android-Sent-Millis";
    private int ageSeconds;
    private String connection;
    private String contentEncoding;
    private long contentLength;
    private String etag;
    private Date expires;
    private final RawHeaders headers;
    private boolean isPublic;
    private Date lastModified;
    private int maxAgeSeconds;
    private boolean mustRevalidate;
    private boolean noCache;
    private boolean noStore;
    private String proxyAuthenticate;
    private long receivedResponseMillis;
    private int sMaxAgeSeconds;
    private long sentRequestMillis;
    private Date servedDate;
    private String transferEncoding;
    private final Uri uri;
    private Set<String> varyFields;
    private String wwwAuthenticate;
    
    public ResponseHeaders(final Uri uri, final RawHeaders headers) {
        this.maxAgeSeconds = -1;
        this.sMaxAgeSeconds = -1;
        this.ageSeconds = -1;
        this.varyFields = Collections.emptySet();
        this.contentLength = -1L;
        this.uri = uri;
        this.headers = headers;
        final HeaderParser.CacheControlHandler cacheControlHandler = new HeaderParser.CacheControlHandler() {
            @Override
            public void handle(final String s, final String s2) {
                if (s.equalsIgnoreCase("no-cache")) {
                    ResponseHeaders.this.noCache = true;
                }
                else if (s.equalsIgnoreCase("no-store")) {
                    ResponseHeaders.this.noStore = true;
                }
                else if (s.equalsIgnoreCase("max-age")) {
                    ResponseHeaders.this.maxAgeSeconds = HeaderParser.parseSeconds(s2);
                }
                else if (s.equalsIgnoreCase("s-maxage")) {
                    ResponseHeaders.this.sMaxAgeSeconds = HeaderParser.parseSeconds(s2);
                }
                else if (s.equalsIgnoreCase("public")) {
                    ResponseHeaders.this.isPublic = true;
                }
                else if (s.equalsIgnoreCase("must-revalidate")) {
                    ResponseHeaders.this.mustRevalidate = true;
                }
            }
        };
        for (int i = 0; i < headers.length(); ++i) {
            final String fieldName = headers.getFieldName(i);
            final String value = headers.getValue(i);
            if ("Cache-Control".equalsIgnoreCase(fieldName)) {
                HeaderParser.parseCacheControl(value, (HeaderParser.CacheControlHandler)cacheControlHandler);
            }
            else if ("Date".equalsIgnoreCase(fieldName)) {
                this.servedDate = HttpDate.parse(value);
            }
            else if ("Expires".equalsIgnoreCase(fieldName)) {
                this.expires = HttpDate.parse(value);
            }
            else if ("Last-Modified".equalsIgnoreCase(fieldName)) {
                this.lastModified = HttpDate.parse(value);
            }
            else if ("ETag".equalsIgnoreCase(fieldName)) {
                this.etag = value;
            }
            else if ("Pragma".equalsIgnoreCase(fieldName)) {
                if (value.equalsIgnoreCase("no-cache")) {
                    this.noCache = true;
                }
            }
            else if ("Age".equalsIgnoreCase(fieldName)) {
                this.ageSeconds = HeaderParser.parseSeconds(value);
            }
            else if ("Vary".equalsIgnoreCase(fieldName)) {
                if (this.varyFields.isEmpty()) {
                    this.varyFields = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
                }
                final String[] split = value.split(",");
                for (int length = split.length, j = 0; j < length; ++j) {
                    this.varyFields.add(split[j].trim().toLowerCase());
                }
            }
            else if ("Content-Encoding".equalsIgnoreCase(fieldName)) {
                this.contentEncoding = value;
            }
            else if ("Transfer-Encoding".equalsIgnoreCase(fieldName)) {
                this.transferEncoding = value;
            }
            else if ("Content-Length".equalsIgnoreCase(fieldName)) {
                try {
                    this.contentLength = Long.parseLong(value);
                }
                catch (NumberFormatException ex) {}
            }
            else if ("Connection".equalsIgnoreCase(fieldName)) {
                this.connection = value;
            }
            else if ("Proxy-Authenticate".equalsIgnoreCase(fieldName)) {
                this.proxyAuthenticate = value;
            }
            else if ("WWW-Authenticate".equalsIgnoreCase(fieldName)) {
                this.wwwAuthenticate = value;
            }
            else if ("X-Android-Sent-Millis".equalsIgnoreCase(fieldName)) {
                this.sentRequestMillis = Long.parseLong(value);
            }
            else if ("X-Android-Received-Millis".equalsIgnoreCase(fieldName)) {
                this.receivedResponseMillis = Long.parseLong(value);
            }
        }
    }
    
    private long computeAge(final long n) {
        long max = 0L;
        if (this.servedDate != null) {
            max = Math.max(max, this.receivedResponseMillis - this.servedDate.getTime());
        }
        long max2;
        if (this.ageSeconds != -1) {
            max2 = Math.max(max, TimeUnit.SECONDS.toMillis(this.ageSeconds));
        }
        else {
            max2 = max;
        }
        return n - this.receivedResponseMillis + (max2 + (this.receivedResponseMillis - this.sentRequestMillis));
    }
    
    private long computeFreshnessLifetime() {
        long millis = 0L;
        if (this.maxAgeSeconds != -1) {
            millis = TimeUnit.SECONDS.toMillis(this.maxAgeSeconds);
        }
        else if (this.expires != null) {
            long n;
            if (this.servedDate != null) {
                n = this.servedDate.getTime();
            }
            else {
                n = this.receivedResponseMillis;
            }
            long n2 = this.expires.getTime() - n;
            if (n2 <= millis) {
                n2 = millis;
            }
            millis = n2;
        }
        else if (this.lastModified != null && this.uri.getEncodedQuery() == null) {
            long n3;
            if (this.servedDate != null) {
                n3 = this.servedDate.getTime();
            }
            else {
                n3 = this.sentRequestMillis;
            }
            final long n4 = n3 - this.lastModified.getTime();
            if (n4 > millis) {
                millis = n4 / 10L;
            }
        }
        return millis;
    }
    
    private static boolean isEndToEnd(final String s) {
        return !s.equalsIgnoreCase("Connection") && !s.equalsIgnoreCase("Keep-Alive") && !s.equalsIgnoreCase("Proxy-Authenticate") && !s.equalsIgnoreCase("Proxy-Authorization") && !s.equalsIgnoreCase("TE") && !s.equalsIgnoreCase("Trailers") && !s.equalsIgnoreCase("Transfer-Encoding") && !s.equalsIgnoreCase("Upgrade");
    }
    
    private boolean isFreshnessLifetimeHeuristic() {
        return this.maxAgeSeconds == -1 && this.expires == null;
    }
    
    public ResponseSource chooseResponseSource(final long n, final RequestHeaders requestHeaders) {
        ResponseSource responseSource;
        if (!this.isCacheable(requestHeaders)) {
            responseSource = ResponseSource.NETWORK;
        }
        else if (requestHeaders.isNoCache() || requestHeaders.hasConditions()) {
            responseSource = ResponseSource.NETWORK;
        }
        else {
            final long computeAge = this.computeAge(n);
            long n2 = this.computeFreshnessLifetime();
            if (requestHeaders.getMaxAgeSeconds() != -1) {
                n2 = Math.min(n2, TimeUnit.SECONDS.toMillis(requestHeaders.getMaxAgeSeconds()));
            }
            long millis = 0L;
            if (requestHeaders.getMinFreshSeconds() != -1) {
                millis = TimeUnit.SECONDS.toMillis(requestHeaders.getMinFreshSeconds());
            }
            long millis2 = 0L;
            if (!this.mustRevalidate && requestHeaders.getMaxStaleSeconds() != -1) {
                millis2 = TimeUnit.SECONDS.toMillis(requestHeaders.getMaxStaleSeconds());
            }
            if (!this.noCache && computeAge + millis < n2 + millis2) {
                if (computeAge + millis >= n2) {
                    this.headers.add("Warning", "110 HttpURLConnection \"Response is stale\"");
                }
                if (computeAge > 86400000L && this.isFreshnessLifetimeHeuristic()) {
                    this.headers.add("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                }
                responseSource = ResponseSource.CACHE;
            }
            else {
                if (this.etag != null) {
                    requestHeaders.setIfNoneMatch(this.etag);
                }
                else if (this.lastModified != null) {
                    requestHeaders.setIfModifiedSince(this.lastModified);
                }
                else if (this.servedDate != null) {
                    requestHeaders.setIfModifiedSince(this.servedDate);
                }
                if (requestHeaders.hasConditions()) {
                    responseSource = ResponseSource.CONDITIONAL_CACHE;
                }
                else {
                    responseSource = ResponseSource.NETWORK;
                }
            }
        }
        return responseSource;
    }
    
    public ResponseHeaders combine(final ResponseHeaders responseHeaders) {
        final RawHeaders rawHeaders = new RawHeaders();
        for (int i = 0; i < this.headers.length(); ++i) {
            final String fieldName = this.headers.getFieldName(i);
            final String value = this.headers.getValue(i);
            if ((!fieldName.equals("Warning") || !value.startsWith("1")) && (!isEndToEnd(fieldName) || responseHeaders.headers.get(fieldName) == null)) {
                rawHeaders.add(fieldName, value);
            }
        }
        for (int j = 0; j < responseHeaders.headers.length(); ++j) {
            final String fieldName2 = responseHeaders.headers.getFieldName(j);
            if (isEndToEnd(fieldName2)) {
                rawHeaders.add(fieldName2, responseHeaders.headers.getValue(j));
            }
        }
        return new ResponseHeaders(this.uri, rawHeaders);
    }
    
    public String getConnection() {
        return this.connection;
    }
    
    public String getContentEncoding() {
        return this.contentEncoding;
    }
    
    public long getContentLength() {
        return this.contentLength;
    }
    
    public String getEtag() {
        return this.etag;
    }
    
    public Date getExpires() {
        return this.expires;
    }
    
    public RawHeaders getHeaders() {
        return this.headers;
    }
    
    public Date getLastModified() {
        return this.lastModified;
    }
    
    public int getMaxAgeSeconds() {
        return this.maxAgeSeconds;
    }
    
    public String getProxyAuthenticate() {
        return this.proxyAuthenticate;
    }
    
    public int getSMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }
    
    public Date getServedDate() {
        return this.servedDate;
    }
    
    public Uri getUri() {
        return this.uri;
    }
    
    public Set<String> getVaryFields() {
        return this.varyFields;
    }
    
    public String getWwwAuthenticate() {
        return this.wwwAuthenticate;
    }
    
    public boolean hasConnectionClose() {
        return "close".equalsIgnoreCase(this.connection);
    }
    
    public boolean hasVaryAll() {
        return this.varyFields.contains("*");
    }
    
    public boolean isCacheable(final RequestHeaders requestHeaders) {
        final int responseCode = this.headers.getResponseCode();
        Label_0049: {
            if (responseCode == 200 || responseCode == 203 || responseCode == 300 || responseCode == 301) {
                break Label_0049;
            }
            final boolean b = false;
            if (responseCode == 410) {
                break Label_0049;
            }
            return b;
        }
        if (requestHeaders.hasAuthorization() && !this.isPublic && !this.mustRevalidate) {
            final int sMaxAgeSeconds = this.sMaxAgeSeconds;
            final boolean b = false;
            if (sMaxAgeSeconds == -1) {
                return b;
            }
        }
        final boolean noStore = this.noStore;
        boolean b = false;
        if (!noStore) {
            b = true;
            return b;
        }
        return b;
    }
    
    public boolean isChunked() {
        return "chunked".equalsIgnoreCase(this.transferEncoding);
    }
    
    public boolean isContentEncodingGzip() {
        return "gzip".equalsIgnoreCase(this.contentEncoding);
    }
    
    public boolean isMustRevalidate() {
        return this.mustRevalidate;
    }
    
    public boolean isNoCache() {
        return this.noCache;
    }
    
    public boolean isNoStore() {
        return this.noStore;
    }
    
    public boolean isPublic() {
        return this.isPublic;
    }
    
    public void setLocalTimestamps(final long sentRequestMillis, final long receivedResponseMillis) {
        this.sentRequestMillis = sentRequestMillis;
        this.headers.add("X-Android-Sent-Millis", Long.toString(sentRequestMillis));
        this.receivedResponseMillis = receivedResponseMillis;
        this.headers.add("X-Android-Received-Millis", Long.toString(receivedResponseMillis));
    }
    
    public void stripContentEncoding() {
        this.contentEncoding = null;
        this.headers.removeAll("Content-Encoding");
    }
    
    public boolean validate(final ResponseHeaders responseHeaders) {
        boolean b = true;
        if (responseHeaders.headers.getResponseCode() != 304 && (this.lastModified == null || responseHeaders.lastModified == null || responseHeaders.lastModified.getTime() >= this.lastModified.getTime())) {
            b = false;
        }
        return b;
    }
    
    public boolean varyMatches(final Map<String, List<String>> map, final Map<String, List<String>> map2) {
        for (final String s : this.varyFields) {
            if (!Objects.equal(map.get(s), map2.get(s))) {
                return false;
            }
        }
        return true;
    }
}
