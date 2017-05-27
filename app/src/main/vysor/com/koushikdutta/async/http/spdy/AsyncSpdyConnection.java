// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.future.SimpleFuture;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.ByteBufferList;
import java.util.Iterator;
import com.koushikdutta.async.Util;
import java.io.IOException;
import java.util.List;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.DataSink;
import java.util.Hashtable;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.http.Protocol;
import java.util.Map;
import com.koushikdutta.async.BufferedDataSink;

public class AsyncSpdyConnection implements Handler
{
    private static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    BufferedDataSink bufferedSocket;
    long bytesLeftInWriteWindow;
    boolean client;
    private int lastGoodStreamId;
    private int nextPingId;
    private int nextStreamId;
    final Settings okHttpSettings;
    Settings peerSettings;
    private Map<Integer, Ping> pings;
    Protocol protocol;
    FrameReader reader;
    private boolean receivedInitialPeerSettings;
    boolean shutdown;
    AsyncSocket socket;
    Hashtable<Integer, SpdySocket> sockets;
    int totalWindowRead;
    Variant variant;
    FrameWriter writer;
    
    public AsyncSpdyConnection(final AsyncSocket socket, final Protocol protocol) {
        int n = 1;
        this.sockets = new Hashtable<Integer, SpdySocket>();
        this.client = (n != 0);
        this.okHttpSettings = new Settings();
        this.peerSettings = new Settings();
        this.receivedInitialPeerSettings = false;
        this.protocol = protocol;
        this.socket = socket;
        this.bufferedSocket = new BufferedDataSink(socket);
        if (protocol == Protocol.SPDY_3) {
            this.variant = new Spdy3();
        }
        else if (protocol == Protocol.HTTP_2) {
            this.variant = new Http20Draft13();
        }
        this.reader = this.variant.newReader(socket, this, n != 0);
        this.writer = this.variant.newWriter(this.bufferedSocket, n != 0);
        int nextStreamId;
        if (true) {
            nextStreamId = n;
        }
        else {
            nextStreamId = 2;
        }
        this.nextStreamId = nextStreamId;
        if (true && protocol == Protocol.HTTP_2) {
            this.nextStreamId += 2;
        }
        if (!true) {
            n = 2;
        }
        this.nextPingId = n;
        if (true) {
            this.okHttpSettings.set(7, 0, 16777216);
        }
    }
    
    private SpdySocket newStream(final int n, final List<Header> list, final boolean b, final boolean b2) {
        final boolean b3 = !b;
        final boolean b4 = !b2;
        SpdySocket spdySocket;
        if (this.shutdown) {
            spdySocket = null;
        }
        else {
            final int nextStreamId = this.nextStreamId;
            this.nextStreamId += 2;
            spdySocket = new SpdySocket(nextStreamId, b3, b4, list);
            if (spdySocket.isOpen()) {
                this.sockets.put(nextStreamId, spdySocket);
            }
            if (n == 0) {
                try {
                    this.writer.synStream(b3, b4, nextStreamId, n, list);
                    return spdySocket;
                }
                catch (IOException ex) {
                    throw new AssertionError((Object)ex);
                }
            }
            if (this.client) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            }
            this.writer.pushPromise(n, nextStreamId, list);
        }
        return spdySocket;
    }
    
    private boolean pushedStream(final int n) {
        return this.protocol == Protocol.HTTP_2 && n != 0 && (n & 0x1) == 0x0;
    }
    
    private Ping removePing(final int n) {
        synchronized (this) {
            Ping ping;
            if (this.pings != null) {
                ping = this.pings.remove(n);
            }
            else {
                ping = null;
            }
            return ping;
        }
    }
    
    private void writePing(final boolean b, final int n, final int n2, final Ping ping) throws IOException {
        if (ping != null) {
            ping.send();
        }
        this.writer.ping(b, n, n2);
    }
    
    @Override
    public void ackSettings() {
        try {
            this.writer.ackSettings();
        }
        catch (IOException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    void addBytesToWriteWindow(final long n) {
        this.bytesLeftInWriteWindow += n;
        final Iterator<SpdySocket> iterator = this.sockets.values().iterator();
        while (iterator.hasNext()) {
            Util.writable(iterator.next());
        }
    }
    
    @Override
    public void alternateService(final int n, final String s, final ByteString byteString, final String s2, final int n2, final long n3) {
    }
    
    @Override
    public void data(final boolean b, final int n, final ByteBufferList list) {
        if (this.pushedStream(n)) {
            throw new AssertionError((Object)"push");
        }
        final SpdySocket spdySocket = this.sockets.get(n);
        Label_0069: {
            if (spdySocket != null) {
                break Label_0069;
            }
            try {
                this.writer.rstStream(n, ErrorCode.INVALID_STREAM);
                list.recycle();
                return;
            }
            catch (IOException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        final int remaining = list.remaining();
        list.get(spdySocket.pending);
        spdySocket.updateWindowRead(remaining);
        Util.emitAllData(spdySocket, spdySocket.pending);
        if (b) {
            this.sockets.remove(n);
            spdySocket.close();
            Util.end(spdySocket, null);
        }
    }
    
    @Override
    public void error(final Exception ex) {
        this.socket.close();
        final Iterator<Map.Entry<Integer, SpdySocket>> iterator = this.sockets.entrySet().iterator();
        while (iterator.hasNext()) {
            Util.end(iterator.next().getValue(), ex);
            iterator.remove();
        }
    }
    
    @Override
    public void goAway(final int n, final ErrorCode errorCode, final ByteString byteString) {
        this.shutdown = true;
        final Iterator<Map.Entry<Integer, SpdySocket>> iterator = this.sockets.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Integer, SpdySocket> entry = iterator.next();
            if (entry.getKey() > n && entry.getValue().isLocallyInitiated()) {
                Util.end(entry.getValue(), new IOException(ErrorCode.REFUSED_STREAM.toString()));
                iterator.remove();
            }
        }
    }
    
    @Override
    public void headers(final boolean b, final boolean b2, final int n, final int n2, final List<Header> list, final HeadersMode headersMode) {
        if (this.pushedStream(n)) {
            throw new AssertionError((Object)"push");
        }
        if (!this.shutdown) {
            final SpdySocket spdySocket = this.sockets.get(n);
            if (spdySocket == null) {
                if (headersMode.failIfStreamAbsent()) {
                    try {
                        this.writer.rstStream(n, ErrorCode.INVALID_STREAM);
                        return;
                    }
                    catch (IOException ex) {
                        throw new AssertionError((Object)ex);
                    }
                }
                if (n > this.lastGoodStreamId && n % 2 != this.nextStreamId % 2) {
                    throw new AssertionError((Object)"unexpected receive stream");
                }
            }
            else {
                if (headersMode.failIfStreamPresent()) {
                    try {
                        this.writer.rstStream(n, ErrorCode.INVALID_STREAM);
                        this.sockets.remove(n);
                        return;
                    }
                    catch (IOException ex2) {
                        throw new AssertionError((Object)ex2);
                    }
                }
                spdySocket.receiveHeaders(list, headersMode);
                if (b2) {
                    this.sockets.remove(n);
                    Util.end(spdySocket, null);
                }
            }
        }
    }
    
    public SpdySocket newStream(final List<Header> list, final boolean b, final boolean b2) {
        return this.newStream(0, list, b, b2);
    }
    
    @Override
    public void ping(final boolean b, final int n, final int n2) {
        if (b) {
            final Ping removePing = this.removePing(n);
            if (removePing != null) {
                removePing.receive();
            }
        }
        else {
            try {
                this.writePing(true, n, n2, null);
            }
            catch (IOException ex) {
                throw new AssertionError((Object)ex);
            }
        }
    }
    
    @Override
    public void priority(final int n, final int n2, final int n3, final boolean b) {
    }
    
    @Override
    public void pushPromise(final int n, final int n2, final List<Header> list) {
        throw new AssertionError((Object)"pushPromise");
    }
    
    @Override
    public void rstStream(final int n, final ErrorCode errorCode) {
        if (this.pushedStream(n)) {
            throw new AssertionError((Object)"push");
        }
        final SpdySocket spdySocket = this.sockets.remove(n);
        if (spdySocket != null) {
            Util.end(spdySocket, new IOException(errorCode.toString()));
        }
    }
    
    public void sendConnectionPreface() throws IOException {
        this.writer.connectionPreface();
        this.writer.settings(this.okHttpSettings);
        final int initialWindowSize = this.okHttpSettings.getInitialWindowSize(65536);
        if (initialWindowSize != 65536) {
            this.writer.windowUpdate(0, initialWindowSize - 65536);
        }
    }
    
    @Override
    public void settings(final boolean b, final Settings settings) {
        long n = 0L;
        final int initialWindowSize = this.peerSettings.getInitialWindowSize(65536);
        if (b) {
            this.peerSettings.clear();
        }
        this.peerSettings.merge(settings);
        try {
            this.writer.ackSettings();
            final int initialWindowSize2 = this.peerSettings.getInitialWindowSize(65536);
            if (initialWindowSize2 != -1 && initialWindowSize2 != initialWindowSize) {
                n = initialWindowSize2 - initialWindowSize;
                if (!this.receivedInitialPeerSettings) {
                    this.addBytesToWriteWindow(n);
                    this.receivedInitialPeerSettings = true;
                }
            }
            final Iterator<SpdySocket> iterator = this.sockets.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().addBytesToWriteWindow(n);
            }
        }
        catch (IOException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    void updateWindowRead(final int n) {
        this.totalWindowRead += n;
        if (this.totalWindowRead < this.okHttpSettings.getInitialWindowSize(65536) / 2) {
            return;
        }
        try {
            this.writer.windowUpdate(0, this.totalWindowRead);
            this.totalWindowRead = 0;
        }
        catch (IOException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public void windowUpdate(final int n, final long n2) {
        if (n == 0) {
            this.addBytesToWriteWindow(n2);
        }
        else {
            final SpdySocket spdySocket = this.sockets.get(n);
            if (spdySocket != null) {
                spdySocket.addBytesToWriteWindow(n2);
            }
        }
    }
    
    public class SpdySocket implements AsyncSocket
    {
        long bytesLeftInWriteWindow;
        CompletedCallback closedCallback;
        DataCallback dataCallback;
        CompletedCallback endCallback;
        SimpleFuture<List<Header>> headers;
        final int id;
        boolean isOpen;
        boolean paused;
        ByteBufferList pending;
        int totalWindowRead;
        WritableCallback writable;
        ByteBufferList writing;
        
        public SpdySocket(final int id, final boolean b, final boolean b2, final List<Header> list) {
            this.bytesLeftInWriteWindow = AsyncSpdyConnection.this.peerSettings.getInitialWindowSize(65536);
            this.pending = new ByteBufferList();
            this.headers = new SimpleFuture<List<Header>>();
            this.isOpen = true;
            this.writing = new ByteBufferList();
            this.id = id;
        }
        
        public void addBytesToWriteWindow(final long n) {
            final long bytesLeftInWriteWindow = this.bytesLeftInWriteWindow;
            this.bytesLeftInWriteWindow += n;
            if (this.bytesLeftInWriteWindow > 0L && bytesLeftInWriteWindow <= 0L) {
                Util.writable(this.writable);
            }
        }
        
        @Override
        public String charset() {
            return null;
        }
        
        @Override
        public void close() {
            this.isOpen = false;
        }
        
        @Override
        public void end() {
            try {
                AsyncSpdyConnection.this.writer.data(true, this.id, this.writing);
            }
            catch (IOException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        
        @Override
        public CompletedCallback getClosedCallback() {
            return this.closedCallback;
        }
        
        public AsyncSpdyConnection getConnection() {
            return AsyncSpdyConnection.this;
        }
        
        @Override
        public DataCallback getDataCallback() {
            return this.dataCallback;
        }
        
        @Override
        public CompletedCallback getEndCallback() {
            return this.endCallback;
        }
        
        @Override
        public AsyncServer getServer() {
            return AsyncSpdyConnection.this.socket.getServer();
        }
        
        @Override
        public WritableCallback getWriteableCallback() {
            return this.writable;
        }
        
        public SimpleFuture<List<Header>> headers() {
            return this.headers;
        }
        
        @Override
        public boolean isChunked() {
            return false;
        }
        
        public boolean isLocallyInitiated() {
            boolean b = true;
            if (AsyncSpdyConnection.this.client != ((0x1 & this.id) == (b ? 1 : 0) && b)) {
                b = false;
            }
            return b;
        }
        
        @Override
        public boolean isOpen() {
            return this.isOpen;
        }
        
        @Override
        public boolean isPaused() {
            return this.paused;
        }
        
        @Override
        public void pause() {
            this.paused = true;
        }
        
        public void receiveHeaders(final List<Header> complete, final HeadersMode headersMode) {
            this.headers.setComplete(complete);
        }
        
        @Override
        public void resume() {
            this.paused = false;
        }
        
        @Override
        public void setClosedCallback(final CompletedCallback closedCallback) {
            this.closedCallback = closedCallback;
        }
        
        @Override
        public void setDataCallback(final DataCallback dataCallback) {
            this.dataCallback = dataCallback;
        }
        
        @Override
        public void setEndCallback(final CompletedCallback endCallback) {
            this.endCallback = endCallback;
        }
        
        @Override
        public void setWriteableCallback(final WritableCallback writable) {
            this.writable = writable;
        }
        
        void updateWindowRead(final int n) {
            this.totalWindowRead += n;
            Label_0057: {
                if (this.totalWindowRead < AsyncSpdyConnection.this.okHttpSettings.getInitialWindowSize(65536) / 2) {
                    break Label_0057;
                }
                try {
                    AsyncSpdyConnection.this.writer.windowUpdate(this.id, this.totalWindowRead);
                    this.totalWindowRead = 0;
                    AsyncSpdyConnection.this.updateWindowRead(n);
                }
                catch (IOException ex) {
                    throw new AssertionError((Object)ex);
                }
            }
        }
        
        @Override
        public void write(ByteBufferList writing) {
            final int min = Math.min(writing.remaining(), (int)Math.min(this.bytesLeftInWriteWindow, AsyncSpdyConnection.this.bytesLeftInWriteWindow));
            if (min != 0) {
                if (min < writing.remaining()) {
                    if (this.writing.hasRemaining()) {
                        throw new AssertionError((Object)"wtf");
                    }
                    writing.get(this.writing, min);
                    writing = this.writing;
                }
                try {
                    AsyncSpdyConnection.this.writer.data(false, this.id, writing);
                    this.bytesLeftInWriteWindow -= min;
                }
                catch (IOException ex) {
                    throw new AssertionError((Object)ex);
                }
            }
        }
    }
}
