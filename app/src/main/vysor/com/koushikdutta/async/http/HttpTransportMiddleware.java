// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.http.body.AsyncHttpRequestBody;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.DataEmitter;
import java.io.IOException;
import android.text.TextUtils;
import com.koushikdutta.async.LineEmitter;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.async.http.filter.ChunkedOutputFilter;
import com.koushikdutta.async.DataSink;

public class HttpTransportMiddleware extends SimpleMiddleware
{
    @Override
    public boolean exchangeHeaders(final OnExchangeHeaderData onExchangeHeaderData) {
        final Protocol value = Protocol.get(onExchangeHeaderData.protocol);
        boolean exchangeHeaders;
        if (value != null && value != Protocol.HTTP_1_0 && value != Protocol.HTTP_1_1) {
            exchangeHeaders = super.exchangeHeaders(onExchangeHeaderData);
        }
        else {
            final AsyncHttpRequest request = onExchangeHeaderData.request;
            final AsyncHttpRequestBody body = onExchangeHeaderData.request.getBody();
            if (body != null) {
                if (body.length() >= 0) {
                    request.getHeaders().set("Content-Length", String.valueOf(body.length()));
                    onExchangeHeaderData.response.sink(onExchangeHeaderData.socket);
                }
                else if ("close".equals(request.getHeaders().get("Connection"))) {
                    onExchangeHeaderData.response.sink(onExchangeHeaderData.socket);
                }
                else {
                    request.getHeaders().set("Transfer-Encoding", "Chunked");
                    onExchangeHeaderData.response.sink(new ChunkedOutputFilter(onExchangeHeaderData.socket));
                }
            }
            final String prefixString = request.getHeaders().toPrefixString(request.getRequestLine().toString());
            final byte[] bytes = prefixString.getBytes();
            int n;
            if (body != null && body.length() >= 0 && body.length() + bytes.length < 1024) {
                n = 1;
            }
            else {
                n = 0;
            }
            BufferedDataSink bufferedDataSink;
            Object socket;
            if (n != 0) {
                bufferedDataSink = new BufferedDataSink(onExchangeHeaderData.response.sink());
                bufferedDataSink.forceBuffering(true);
                onExchangeHeaderData.response.sink(bufferedDataSink);
                socket = bufferedDataSink;
            }
            else {
                socket = onExchangeHeaderData.socket;
                bufferedDataSink = null;
            }
            request.logv("\n" + prefixString);
            Util.writeAll((DataSink)socket, bytes, new CompletedCallback() {
                final /* synthetic */ CompletedCallback val$sentCallback = onExchangeHeaderData.sendHeadersCallback;
                
                @Override
                public void onCompleted(final Exception ex) {
                    Util.end(this.val$sentCallback, ex);
                    if (bufferedDataSink != null) {
                        bufferedDataSink.forceBuffering(false);
                        bufferedDataSink.setMaxBuffer(0);
                    }
                }
            });
            final LineEmitter.StringCallback lineCallback = new LineEmitter.StringCallback() {
                Headers mRawHeaders = new Headers();
                String statusLine;
                
                @Override
                public void onStringAvailable(final String s) {
                    try {
                        final String trim = s.trim();
                        if (this.statusLine == null) {
                            this.statusLine = trim;
                            return;
                        }
                        if (!TextUtils.isEmpty((CharSequence)trim)) {
                            this.mRawHeaders.addLine(trim);
                            return;
                        }
                    }
                    catch (Exception ex) {
                        onExchangeHeaderData.receiveHeadersCallback.onCompleted(ex);
                        return;
                    }
                    final String[] split = this.statusLine.split(" ", 3);
                    if (split.length < 2) {
                        throw new Exception(new IOException("Not HTTP"));
                    }
                    onExchangeHeaderData.response.headers(this.mRawHeaders);
                    final String s2 = split[0];
                    onExchangeHeaderData.response.protocol(s2);
                    onExchangeHeaderData.response.code(Integer.parseInt(split[1]));
                    final ResponseHead response = onExchangeHeaderData.response;
                    String s3;
                    if (split.length == 3) {
                        s3 = split[2];
                    }
                    else {
                        s3 = "";
                    }
                    response.message(s3);
                    onExchangeHeaderData.receiveHeadersCallback.onCompleted(null);
                    final AsyncSocket socket = onExchangeHeaderData.response.socket();
                    if (socket != null) {
                        DataEmitter dataEmitter;
                        if ("HEAD".equalsIgnoreCase(onExchangeHeaderData.request.getMethod())) {
                            dataEmitter = HttpUtil.EndEmitter.create(socket.getServer(), null);
                        }
                        else {
                            dataEmitter = HttpUtil.getBodyDecoder(socket, Protocol.get(s2), this.mRawHeaders, false);
                        }
                        onExchangeHeaderData.response.emitter(dataEmitter);
                    }
                }
            };
            final LineEmitter dataCallback = new LineEmitter();
            onExchangeHeaderData.socket.setDataCallback(dataCallback);
            dataCallback.setLineCallback((LineEmitter.StringCallback)lineCallback);
            exchangeHeaders = true;
        }
        return exchangeHeaders;
    }
    
    @Override
    public void onRequestSent(final OnRequestSentData onRequestSentData) {
        final Protocol value = Protocol.get(onRequestSentData.protocol);
        if ((value == null || value == Protocol.HTTP_1_0 || value == Protocol.HTTP_1_1) && onRequestSentData.response.sink() instanceof ChunkedOutputFilter) {
            onRequestSentData.response.sink().end();
        }
    }
}
