// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.AsyncServer;
import java.nio.ByteBuffer;
import java.util.UUID;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.Util;
import android.util.Base64;
import java.security.MessageDigest;
import android.text.TextUtils;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.ByteBufferList;
import java.util.LinkedList;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;

public class WebSocketImpl implements WebSocket
{
    static final String MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private DataCallback mDataCallback;
    CompletedCallback mExceptionCallback;
    HybiParser mParser;
    private PingCallback mPingCallback;
    private PongCallback mPongCallback;
    BufferedDataSink mSink;
    private AsyncSocket mSocket;
    private StringCallback mStringCallback;
    private LinkedList<ByteBufferList> pending;
    
    public WebSocketImpl(final AsyncSocket mSocket) {
        this.mSocket = mSocket;
        this.mSink = new BufferedDataSink(this.mSocket);
    }
    
    public WebSocketImpl(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
        this(asyncHttpServerRequest.getSocket());
        final String sha1 = SHA1(asyncHttpServerRequest.getHeaders().get("Sec-WebSocket-Key") + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
        asyncHttpServerRequest.getHeaders().get("Origin");
        asyncHttpServerResponse.code(101);
        asyncHttpServerResponse.getHeaders().set("Upgrade", "WebSocket");
        asyncHttpServerResponse.getHeaders().set("Connection", "Upgrade");
        asyncHttpServerResponse.getHeaders().set("Sec-WebSocket-Accept", sha1);
        final String value = asyncHttpServerRequest.getHeaders().get("Sec-WebSocket-Protocol");
        if (!TextUtils.isEmpty((CharSequence)value)) {
            asyncHttpServerResponse.getHeaders().set("Sec-WebSocket-Protocol", value);
        }
        asyncHttpServerResponse.writeHead();
        this.setupParser(false, false);
    }
    
    private static String SHA1(final String s) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(s.getBytes("iso-8859-1"), 0, s.length());
            return Base64.encodeToString(instance.digest(), 2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void addAndEmit(final ByteBufferList list) {
        if (this.pending == null) {
            Util.emitAllData(this, list);
            if (list.remaining() > 0) {
                (this.pending = new LinkedList<ByteBufferList>()).add(list);
            }
        }
        else {
            while (!this.isPaused()) {
                final ByteBufferList list2 = this.pending.remove();
                Util.emitAllData(this, list2);
                if (list2.remaining() > 0) {
                    this.pending.add(0, list2);
                }
            }
            if (this.pending.size() == 0) {
                this.pending = null;
            }
        }
    }
    
    public static void addWebSocketUpgradeHeaders(final AsyncHttpRequest asyncHttpRequest, final String s) {
        final Headers headers = asyncHttpRequest.getHeaders();
        final String encodeToString = Base64.encodeToString(toByteArray(UUID.randomUUID()), 2);
        headers.set("Sec-WebSocket-Version", "13");
        headers.set("Sec-WebSocket-Key", encodeToString);
        headers.set("Sec-WebSocket-Extensions", "x-webkit-deflate-frame");
        headers.set("Connection", "Upgrade");
        headers.set("Upgrade", "websocket");
        if (s != null) {
            headers.set("Sec-WebSocket-Protocol", s);
        }
        headers.set("Pragma", "no-cache");
        headers.set("Cache-Control", "no-cache");
        if (TextUtils.isEmpty((CharSequence)asyncHttpRequest.getHeaders().get("User-Agent"))) {
            asyncHttpRequest.getHeaders().set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.15 Safari/537.36");
        }
    }
    
    public static WebSocket finishHandshake(final Headers headers, final AsyncHttpResponse asyncHttpResponse) {
        WebSocketImpl webSocketImpl = null;
        if (asyncHttpResponse != null) {
            final int code = asyncHttpResponse.code();
            webSocketImpl = null;
            if (code == 101) {
                final boolean equalsIgnoreCase = "websocket".equalsIgnoreCase(asyncHttpResponse.headers().get("Upgrade"));
                webSocketImpl = null;
                if (equalsIgnoreCase) {
                    final String value = asyncHttpResponse.headers().get("Sec-WebSocket-Accept");
                    webSocketImpl = null;
                    if (value != null) {
                        final String value2 = headers.get("Sec-WebSocket-Key");
                        webSocketImpl = null;
                        if (value2 != null) {
                            final boolean equalsIgnoreCase2 = value.equalsIgnoreCase(SHA1(value2 + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").trim());
                            webSocketImpl = null;
                            if (equalsIgnoreCase2) {
                                final String value3 = headers.get("Sec-WebSocket-Extensions");
                                boolean b = false;
                                if (value3 != null) {
                                    final boolean equals = value3.equals("x-webkit-deflate-frame");
                                    b = false;
                                    if (equals) {
                                        b = true;
                                    }
                                }
                                webSocketImpl = new WebSocketImpl(asyncHttpResponse.detachSocket());
                                webSocketImpl.setupParser(true, b);
                            }
                        }
                    }
                }
            }
        }
        return webSocketImpl;
    }
    
    private void setupParser(final boolean masking, final boolean deflate) {
        (this.mParser = new HybiParser(this.mSocket) {
            @Override
            protected void onDisconnect(final int n, final String s) {
                WebSocketImpl.this.mSocket.close();
            }
            
            @Override
            protected void onMessage(final String s) {
                if (WebSocketImpl.this.mStringCallback != null) {
                    WebSocketImpl.this.mStringCallback.onStringAvailable(s);
                }
            }
            
            @Override
            protected void onMessage(final byte[] array) {
                WebSocketImpl.this.addAndEmit(new ByteBufferList(array));
            }
            
            @Override
            protected void onPing(final String s) {
                if (WebSocketImpl.this.mPingCallback != null) {
                    WebSocketImpl.this.mPingCallback.onPingReceived(s);
                }
            }
            
            @Override
            protected void onPong(final String s) {
                if (WebSocketImpl.this.mPongCallback != null) {
                    WebSocketImpl.this.mPongCallback.onPongReceived(s);
                }
            }
            
            @Override
            protected void report(final Exception ex) {
                if (WebSocketImpl.this.mExceptionCallback != null) {
                    WebSocketImpl.this.mExceptionCallback.onCompleted(ex);
                }
            }
            
            @Override
            protected void sendFrame(final byte[] array) {
                WebSocketImpl.this.mSink.write(new ByteBufferList(array));
            }
        }).setMasking(masking);
        this.mParser.setDeflate(deflate);
        if (this.mSocket.isPaused()) {
            this.mSocket.resume();
        }
    }
    
    private static byte[] toByteArray(final UUID uuid) {
        final byte[] array = new byte[16];
        ByteBuffer.wrap(array).asLongBuffer().put(new long[] { uuid.getMostSignificantBits(), uuid.getLeastSignificantBits() });
        return array;
    }
    
    @Override
    public String charset() {
        return null;
    }
    
    @Override
    public void close() {
        this.mSocket.close();
    }
    
    @Override
    public void end() {
        this.mSocket.end();
    }
    
    @Override
    public CompletedCallback getClosedCallback() {
        return this.mSocket.getClosedCallback();
    }
    
    @Override
    public DataCallback getDataCallback() {
        return this.mDataCallback;
    }
    
    @Override
    public CompletedCallback getEndCallback() {
        return this.mExceptionCallback;
    }
    
    @Override
    public PongCallback getPongCallback() {
        return this.mPongCallback;
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
    public StringCallback getStringCallback() {
        return this.mStringCallback;
    }
    
    @Override
    public WritableCallback getWriteableCallback() {
        return this.mSink.getWriteableCallback();
    }
    
    @Override
    public boolean isBuffering() {
        return this.mSink.remaining() > 0;
    }
    
    @Override
    public boolean isChunked() {
        return false;
    }
    
    @Override
    public boolean isOpen() {
        return this.mSocket.isOpen();
    }
    
    @Override
    public boolean isPaused() {
        return this.mSocket.isPaused();
    }
    
    @Override
    public void pause() {
        this.mSocket.pause();
    }
    
    @Override
    public void ping(final String s) {
        this.mSink.write(new ByteBufferList(new ByteBuffer[] { ByteBuffer.wrap(this.mParser.pingFrame(s)) }));
    }
    
    @Override
    public void pong(final String s) {
        this.mSink.write(new ByteBufferList(new ByteBuffer[] { ByteBuffer.wrap(this.mParser.pongFrame(s)) }));
    }
    
    @Override
    public void resume() {
        this.mSocket.resume();
    }
    
    @Override
    public void send(final String s) {
        this.mSink.write(new ByteBufferList(this.mParser.frame(s)));
    }
    
    @Override
    public void send(final byte[] array) {
        this.mSink.write(new ByteBufferList(this.mParser.frame(array)));
    }
    
    @Override
    public void send(final byte[] array, final int n, final int n2) {
        this.mSink.write(new ByteBufferList(this.mParser.frame(array, n, n2)));
    }
    
    @Override
    public void setClosedCallback(final CompletedCallback closedCallback) {
        this.mSocket.setClosedCallback(closedCallback);
    }
    
    @Override
    public void setDataCallback(final DataCallback mDataCallback) {
        this.mDataCallback = mDataCallback;
    }
    
    @Override
    public void setEndCallback(final CompletedCallback mExceptionCallback) {
        this.mExceptionCallback = mExceptionCallback;
    }
    
    @Override
    public void setPingCallback(final PingCallback mPingCallback) {
        this.mPingCallback = mPingCallback;
    }
    
    @Override
    public void setPongCallback(final PongCallback mPongCallback) {
        this.mPongCallback = mPongCallback;
    }
    
    @Override
    public void setStringCallback(final StringCallback mStringCallback) {
        this.mStringCallback = mStringCallback;
    }
    
    @Override
    public void setWriteableCallback(final WritableCallback writeableCallback) {
        this.mSink.setWriteableCallback(writeableCallback);
    }
    
    @Override
    public void write(final ByteBufferList list) {
        this.send(list.getAllByteArray());
    }
}
