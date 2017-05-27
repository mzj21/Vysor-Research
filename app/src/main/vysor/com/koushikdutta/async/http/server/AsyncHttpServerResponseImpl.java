// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.server;

import com.koushikdutta.async.util.StreamUtility;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.Util;
import java.util.Locale;
import android.text.TextUtils;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.http.filter.ChunkedOutputFilter;
import com.koushikdutta.async.http.HttpUtil;
import com.koushikdutta.async.http.Protocol;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.callback.CompletedCallback;

public class AsyncHttpServerResponseImpl implements AsyncHttpServerResponse
{
    CompletedCallback closedCallback;
    int code;
    boolean ended;
    boolean headWritten;
    private long mContentLength;
    boolean mEnded;
    private Headers mRawHeaders;
    AsyncHttpServerRequestImpl mRequest;
    DataSink mSink;
    AsyncSocket mSocket;
    WritableCallback writable;
    
    AsyncHttpServerResponseImpl(final AsyncSocket mSocket, final AsyncHttpServerRequestImpl mRequest) {
        this.mRawHeaders = new Headers();
        this.mContentLength = -1L;
        this.headWritten = false;
        this.code = 200;
        this.mSocket = mSocket;
        this.mRequest = mRequest;
        if (HttpUtil.isKeepAlive(Protocol.HTTP_1_1, mRequest.getHeaders())) {
            this.mRawHeaders.set("Connection", "Keep-Alive");
        }
    }
    
    @Override
    public int code() {
        return this.code;
    }
    
    @Override
    public AsyncHttpServerResponse code(final int code) {
        this.code = code;
        return this;
    }
    
    @Override
    public void end() {
        if (!this.ended) {
            this.ended = true;
            if (!this.headWritten || this.mSink != null) {
                if (!this.headWritten) {
                    this.mRawHeaders.remove("Transfer-Encoding");
                }
                if (this.mSink instanceof ChunkedOutputFilter) {
                    ((ChunkedOutputFilter)this.mSink).setMaxBuffer(Integer.MAX_VALUE);
                    this.mSink.write(new ByteBufferList());
                    this.onEnd();
                }
                else if (!this.headWritten) {
                    if (!this.mRequest.getMethod().equalsIgnoreCase("HEAD")) {
                        this.send("text/html", "");
                    }
                    else {
                        this.writeHead();
                        this.onEnd();
                    }
                }
                else {
                    this.onEnd();
                }
            }
        }
    }
    
    @Override
    public CompletedCallback getClosedCallback() {
        CompletedCallback completedCallback;
        if (this.mSink != null) {
            completedCallback = this.mSink.getClosedCallback();
        }
        else {
            completedCallback = this.closedCallback;
        }
        return completedCallback;
    }
    
    @Override
    public Headers getHeaders() {
        return this.mRawHeaders;
    }
    
    @Override
    public AsyncServer getServer() {
        return this.mSocket.getServer();
    }
    
    @Override
    public AsyncSocket getSocket() {
        return this.mSocket;
    }
    
    @Override
    public WritableCallback getWriteableCallback() {
        WritableCallback writableCallback;
        if (this.mSink != null) {
            writableCallback = this.mSink.getWriteableCallback();
        }
        else {
            writableCallback = this.writable;
        }
        return writableCallback;
    }
    
    void initFirstWrite() {
        if (!this.headWritten) {
            this.headWritten = true;
            final String value = this.mRawHeaders.get("Transfer-Encoding");
            if ("".equals(value)) {
                this.mRawHeaders.removeAll("Transfer-Encoding");
            }
            boolean b;
            if (("Chunked".equalsIgnoreCase(value) || value == null) && !"close".equalsIgnoreCase(this.mRawHeaders.get("Connection"))) {
                b = true;
            }
            else {
                b = false;
            }
            if (this.mContentLength < 0L) {
                final String value2 = this.mRawHeaders.get("Content-Length");
                if (!TextUtils.isEmpty((CharSequence)value2)) {
                    this.mContentLength = Long.valueOf(value2);
                }
            }
            boolean b2;
            if (this.mContentLength < 0L && b) {
                this.mRawHeaders.set("Transfer-Encoding", "Chunked");
                b2 = true;
            }
            else {
                b2 = false;
            }
            Util.writeAll(this.mSocket, this.mRawHeaders.toPrefixString(String.format(Locale.ENGLISH, "HTTP/1.1 %s %s", this.code, AsyncHttpServer.getResponseCodeDescription(this.code))).getBytes(), new CompletedCallback() {
                @Override
                public void onCompleted(final Exception ex) {
                    if (ex != null) {
                        AsyncHttpServerResponseImpl.this.report(ex);
                    }
                    else {
                        if (b2) {
                            final ChunkedOutputFilter mSink = new ChunkedOutputFilter(AsyncHttpServerResponseImpl.this.mSocket);
                            mSink.setMaxBuffer(0);
                            AsyncHttpServerResponseImpl.this.mSink = mSink;
                        }
                        else {
                            AsyncHttpServerResponseImpl.this.mSink = AsyncHttpServerResponseImpl.this.mSocket;
                        }
                        AsyncHttpServerResponseImpl.this.mSink.setClosedCallback(AsyncHttpServerResponseImpl.this.closedCallback);
                        AsyncHttpServerResponseImpl.this.closedCallback = null;
                        AsyncHttpServerResponseImpl.this.mSink.setWriteableCallback(AsyncHttpServerResponseImpl.this.writable);
                        AsyncHttpServerResponseImpl.this.writable = null;
                        if (AsyncHttpServerResponseImpl.this.ended) {
                            AsyncHttpServerResponseImpl.this.end();
                        }
                        else {
                            AsyncHttpServerResponseImpl.this.getServer().post(new Runnable() {
                                @Override
                                public void run() {
                                    final WritableCallback writeableCallback = AsyncHttpServerResponseImpl.this.getWriteableCallback();
                                    if (writeableCallback != null) {
                                        writeableCallback.onWriteable();
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }
    
    @Override
    public boolean isOpen() {
        boolean b;
        if (this.mSink != null) {
            b = this.mSink.isOpen();
        }
        else {
            b = this.mSocket.isOpen();
        }
        return b;
    }
    
    @Override
    public void onCompleted(final Exception ex) {
        this.end();
    }
    
    protected void onEnd() {
        this.mEnded = true;
    }
    
    @Override
    public void proxy(final AsyncHttpResponse asyncHttpResponse) {
        this.code(asyncHttpResponse.code());
        asyncHttpResponse.headers().removeAll("Transfer-Encoding");
        asyncHttpResponse.headers().removeAll("Content-Encoding");
        asyncHttpResponse.headers().removeAll("Connection");
        this.getHeaders().addAll(asyncHttpResponse.headers());
        asyncHttpResponse.headers().set("Connection", "close");
        Util.pump(asyncHttpResponse, this, new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                asyncHttpResponse.setEndCallback(new NullCompletedCallback());
                asyncHttpResponse.setDataCallback(new DataCallback.NullDataCallback());
                AsyncHttpServerResponseImpl.this.end();
            }
        });
    }
    
    @Override
    public void redirect(final String s) {
        this.code(302);
        this.mRawHeaders.set("Location", s);
        this.end();
    }
    
    protected void report(final Exception ex) {
    }
    
    @Override
    public void send(final String s) {
        String value = this.mRawHeaders.get("Content-Type");
        if (value == null) {
            value = "text/html; charset=utf-8";
        }
        this.send(value, s);
    }
    
    @Override
    public void send(final String s, final String s2) {
        try {
            this.send(s, s2.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public void send(final String s, final byte[] array) {
        assert this.mContentLength < 0L;
        this.mContentLength = array.length;
        this.mRawHeaders.set("Content-Length", Integer.toString(array.length));
        this.mRawHeaders.set("Content-Type", s);
        Util.writeAll(this, array, new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                AsyncHttpServerResponseImpl.this.onEnd();
            }
        });
    }
    
    @Override
    public void send(final JSONObject jsonObject) {
        this.send("application/json; charset=utf-8", jsonObject.toString());
    }
    
    @Override
    public void sendFile(final File file) {
        try {
            if (this.mRawHeaders.get("Content-Type") == null) {
                this.mRawHeaders.set("Content-Type", AsyncHttpServer.getContentType(file.getAbsolutePath()));
            }
            this.sendStream(new BufferedInputStream(new FileInputStream(file), 64000), file.length());
        }
        catch (FileNotFoundException ex) {
            this.code(404);
            this.end();
        }
    }
    
    @Override
    public void sendStream(final InputStream inputStream, final long n) {
        long long1 = 0L;
        long long2 = n - 1L;
        final String value = this.mRequest.getHeaders().get("Range");
        while (true) {
            Label_0270: {
                Label_0278: {
                    Label_0230: {
                        if (value == null) {
                            break Label_0230;
                        }
                        final String[] split = value.split("=");
                        if (split.length != 2 || !"bytes".equals(split[0])) {
                            this.code(416);
                            this.end();
                        }
                        else {
                            final String[] split2 = split[1].split("-");
                            try {
                                if (split2.length > 2) {
                                    throw new MalformedRangeException();
                                }
                            }
                            catch (Exception ex) {
                                this.code(416);
                                this.end();
                                return;
                            }
                            if (!TextUtils.isEmpty((CharSequence)split2[0])) {
                                long1 = Long.parseLong(split2[0]);
                            }
                            if (split2.length == 2 && !TextUtils.isEmpty((CharSequence)split2[1])) {
                                long2 = Long.parseLong(split2[1]);
                                break Label_0160;
                            }
                            break Label_0270;
                        }
                        return;
                        this.code(206);
                        this.getHeaders().set("Content-Range", String.format(Locale.ENGLISH, "bytes %d-%d/%d", long1, long2, n));
                        try {
                            if (long1 != inputStream.skip(long1)) {
                                throw new StreamSkipException("skip failed to skip requested amount");
                            }
                            break Label_0278;
                        }
                        catch (Exception ex2) {
                            this.code(500);
                            this.end();
                            return;
                        }
                    }
                    break Label_0270;
                }
                this.mContentLength = 1L + (long2 - long1);
                this.mRawHeaders.set("Content-Length", String.valueOf(this.mContentLength));
                this.mRawHeaders.set("Accept-Ranges", "bytes");
                if (this.mRequest.getMethod().equals("HEAD")) {
                    this.writeHead();
                    this.onEnd();
                    return;
                }
                Util.pump(inputStream, this.mContentLength, this, new CompletedCallback() {
                    @Override
                    public void onCompleted(final Exception ex) {
                        StreamUtility.closeQuietly(inputStream);
                        AsyncHttpServerResponseImpl.this.onEnd();
                    }
                });
                return;
            }
            long2 = n - 1L;
            continue;
        }
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback completedCallback) {
        if (this.mSink != null) {
            this.mSink.setClosedCallback(completedCallback);
        }
        else {
            this.closedCallback = completedCallback;
        }
    }
    
    @Override
    public void setContentType(final String s) {
        this.mRawHeaders.set("Content-Type", s);
    }
    
    @Override
    public void setWriteableCallback(final WritableCallback writableCallback) {
        if (this.mSink != null) {
            this.mSink.setWriteableCallback(writableCallback);
        }
        else {
            this.writable = writableCallback;
        }
    }
    
    @Override
    public String toString() {
        String s;
        if (this.mRawHeaders == null) {
            s = super.toString();
        }
        else {
            s = this.mRawHeaders.toPrefixString(String.format(Locale.ENGLISH, "HTTP/1.1 %s %s", this.code, AsyncHttpServer.getResponseCodeDescription(this.code)));
        }
        return s;
    }
    
    @Override
    public void write(final ByteBufferList list) {
        assert !this.mEnded;
        if (!this.headWritten) {
            this.initFirstWrite();
        }
        if (list.remaining() != 0 && this.mSink != null) {
            this.mSink.write(list);
        }
    }
    
    @Override
    public void writeHead() {
        this.initFirstWrite();
    }
}
