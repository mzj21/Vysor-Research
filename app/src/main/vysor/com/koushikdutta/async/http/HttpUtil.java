// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.FilteredDataEmitter;
import com.koushikdutta.async.http.filter.ChunkedInputFilter;
import com.koushikdutta.async.http.filter.InflaterInputFilter;
import com.koushikdutta.async.http.filter.GZIPInputFilter;
import com.koushikdutta.async.http.filter.ContentLengthFilter;
import com.koushikdutta.async.http.body.MultipartFormDataBody;
import com.koushikdutta.async.http.body.StringBody;
import com.koushikdutta.async.http.body.JSONObjectBody;
import com.koushikdutta.async.http.body.UrlEncodedFormBody;
import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;

public class HttpUtil
{
    public static int contentLength(final Headers headers) {
        int int1 = -1;
        final String value = headers.get("Content-Length");
        if (value != null) {
            try {
                int1 = Integer.parseInt(value);
            }
            catch (NumberFormatException ex) {}
        }
        return int1;
    }
    
    public static AsyncHttpRequestBody getBody(final DataEmitter dataEmitter, final CompletedCallback completedCallback, final Headers headers) {
        final String value = headers.get("Content-Type");
        if (value != null) {
            final String[] split = value.split(";");
            for (int i = 0; i < split.length; ++i) {
                split[i] = split[i].trim();
            }
            for (final String s : split) {
                if ("application/x-www-form-urlencoded".equals(s)) {
                    final Object o = new UrlEncodedFormBody();
                    return (AsyncHttpRequestBody)o;
                }
                if ("application/json".equals(s)) {
                    final Object o = new JSONObjectBody();
                    return (AsyncHttpRequestBody)o;
                }
                if ("text/plain".equals(s)) {
                    final Object o = new StringBody();
                    return (AsyncHttpRequestBody)o;
                }
                if ("multipart/form-data".equals(s)) {
                    final Object o = new MultipartFormDataBody(split);
                    return (AsyncHttpRequestBody)o;
                }
            }
            return null;
        }
        return null;
        final Object o = null;
        return (AsyncHttpRequestBody)o;
    }
    
    public static DataEmitter getBodyDecoder(DataEmitter dataEmitter, final Protocol protocol, final Headers headers, final boolean b) {
    Label_0127_Outer:
        while (true) {
            while (true) {
                Label_0162: {
                    long n;
                    while (true) {
                        try {
                            final long long1 = Long.parseLong(headers.get("Content-Length"));
                            n = long1;
                            if (-1L == n) {
                                break Label_0162;
                            }
                            if (n < 0L) {
                                final EndEmitter create = EndEmitter.create(dataEmitter.getServer(), new BodyDecoderException("not using chunked encoding, and no content-length found."));
                                create.setDataEmitter(dataEmitter);
                                dataEmitter = create;
                                return dataEmitter;
                            }
                        }
                        catch (Exception ex) {
                            final long long1 = -1L;
                            continue Label_0127_Outer;
                        }
                        break;
                    }
                    if (n == 0L) {
                        final EndEmitter create2 = EndEmitter.create(dataEmitter.getServer(), null);
                        create2.setDataEmitter(dataEmitter);
                        dataEmitter = create2;
                        return dataEmitter;
                    }
                    final FilteredDataEmitter filteredDataEmitter = new ContentLengthFilter(n);
                    filteredDataEmitter.setDataEmitter(dataEmitter);
                    dataEmitter = filteredDataEmitter;
                    if ("gzip".equals(headers.get("Content-Encoding"))) {
                        final FilteredDataEmitter filteredDataEmitter2 = new GZIPInputFilter();
                        filteredDataEmitter2.setDataEmitter(dataEmitter);
                        dataEmitter = filteredDataEmitter2;
                        return dataEmitter;
                    }
                    if ("deflate".equals(headers.get("Content-Encoding"))) {
                        final FilteredDataEmitter filteredDataEmitter3 = new InflaterInputFilter();
                        filteredDataEmitter3.setDataEmitter(dataEmitter);
                        dataEmitter = filteredDataEmitter3;
                        return dataEmitter;
                    }
                    return dataEmitter;
                }
                if ("chunked".equalsIgnoreCase(headers.get("Transfer-Encoding"))) {
                    final FilteredDataEmitter filteredDataEmitter4 = new ChunkedInputFilter();
                    filteredDataEmitter4.setDataEmitter(dataEmitter);
                    dataEmitter = filteredDataEmitter4;
                    continue;
                }
                if ((b || protocol == Protocol.HTTP_1_1) && !"close".equalsIgnoreCase(headers.get("Connection"))) {
                    final EndEmitter create3 = EndEmitter.create(dataEmitter.getServer(), null);
                    create3.setDataEmitter(dataEmitter);
                    dataEmitter = create3;
                    return dataEmitter;
                }
                continue;
            }
        }
    }
    
    public static boolean isKeepAlive(final Protocol protocol, final Headers headers) {
        final String value = headers.get("Connection");
        boolean equalsIgnoreCase;
        if (value == null) {
            equalsIgnoreCase = (protocol == Protocol.HTTP_1_1);
        }
        else {
            equalsIgnoreCase = "keep-alive".equalsIgnoreCase(value);
        }
        return equalsIgnoreCase;
    }
    
    public static boolean isKeepAlive(final String s, final Headers headers) {
        final String value = headers.get("Connection");
        boolean equalsIgnoreCase;
        if (value == null) {
            equalsIgnoreCase = (Protocol.get(s) == Protocol.HTTP_1_1);
        }
        else {
            equalsIgnoreCase = "keep-alive".equalsIgnoreCase(value);
        }
        return equalsIgnoreCase;
    }
    
    static class EndEmitter extends FilteredDataEmitter
    {
        public static EndEmitter create(final AsyncServer asyncServer, final Exception ex) {
            final EndEmitter endEmitter = new EndEmitter();
            asyncServer.post(new Runnable() {
                @Override
                public void run() {
                    endEmitter.report(ex);
                }
            });
            return endEmitter;
        }
    }
}
