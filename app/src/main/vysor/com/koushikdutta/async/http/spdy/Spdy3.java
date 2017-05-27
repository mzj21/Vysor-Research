// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import java.nio.ByteBuffer;
import java.util.zip.Deflater;
import java.util.List;
import java.util.Locale;
import com.koushikdutta.async.callback.CompletedCallback;
import java.io.IOException;
import java.net.ProtocolException;
import java.nio.ByteOrder;
import com.koushikdutta.async.DataEmitterReader;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.http.Protocol;
import java.io.UnsupportedEncodingException;
import com.koushikdutta.async.util.Charsets;

final class Spdy3 implements Variant
{
    static final byte[] DICTIONARY;
    static final int FLAG_FIN = 1;
    static final int FLAG_UNIDIRECTIONAL = 2;
    static final int TYPE_DATA = 0;
    static final int TYPE_GOAWAY = 7;
    static final int TYPE_HEADERS = 8;
    static final int TYPE_PING = 6;
    static final int TYPE_RST_STREAM = 3;
    static final int TYPE_SETTINGS = 4;
    static final int TYPE_SYN_REPLY = 2;
    static final int TYPE_SYN_STREAM = 1;
    static final int TYPE_WINDOW_UPDATE = 9;
    static final int VERSION = 3;
    
    static {
        try {
            DICTIONARY = "\u0000\u0000\u0000\u0007options\u0000\u0000\u0000\u0004head\u0000\u0000\u0000\u0004post\u0000\u0000\u0000\u0003put\u0000\u0000\u0000\u0006delete\u0000\u0000\u0000\u0005trace\u0000\u0000\u0000\u0006accept\u0000\u0000\u0000\u000eaccept-charset\u0000\u0000\u0000\u000faccept-encoding\u0000\u0000\u0000\u000faccept-language\u0000\u0000\u0000\raccept-ranges\u0000\u0000\u0000\u0003age\u0000\u0000\u0000\u0005allow\u0000\u0000\u0000\rauthorization\u0000\u0000\u0000\rcache-control\u0000\u0000\u0000\nconnection\u0000\u0000\u0000\fcontent-base\u0000\u0000\u0000\u0010content-encoding\u0000\u0000\u0000\u0010content-language\u0000\u0000\u0000\u000econtent-length\u0000\u0000\u0000\u0010content-location\u0000\u0000\u0000\u000bcontent-md5\u0000\u0000\u0000\rcontent-range\u0000\u0000\u0000\fcontent-type\u0000\u0000\u0000\u0004date\u0000\u0000\u0000\u0004etag\u0000\u0000\u0000\u0006expect\u0000\u0000\u0000\u0007expires\u0000\u0000\u0000\u0004from\u0000\u0000\u0000\u0004host\u0000\u0000\u0000\bif-match\u0000\u0000\u0000\u0011if-modified-since\u0000\u0000\u0000\rif-none-match\u0000\u0000\u0000\bif-range\u0000\u0000\u0000\u0013if-unmodified-since\u0000\u0000\u0000\rlast-modified\u0000\u0000\u0000\blocation\u0000\u0000\u0000\fmax-forwards\u0000\u0000\u0000\u0006pragma\u0000\u0000\u0000\u0012proxy-authenticate\u0000\u0000\u0000\u0013proxy-authorization\u0000\u0000\u0000\u0005range\u0000\u0000\u0000\u0007referer\u0000\u0000\u0000\u000bretry-after\u0000\u0000\u0000\u0006server\u0000\u0000\u0000\u0002te\u0000\u0000\u0000\u0007trailer\u0000\u0000\u0000\u0011transfer-encoding\u0000\u0000\u0000\u0007upgrade\u0000\u0000\u0000\nuser-agent\u0000\u0000\u0000\u0004vary\u0000\u0000\u0000\u0003via\u0000\u0000\u0000\u0007warning\u0000\u0000\u0000\u0010www-authenticate\u0000\u0000\u0000\u0006method\u0000\u0000\u0000\u0003get\u0000\u0000\u0000\u0006status\u0000\u0000\u0000\u0006200 OK\u0000\u0000\u0000\u0007version\u0000\u0000\u0000\bHTTP/1.1\u0000\u0000\u0000\u0003url\u0000\u0000\u0000\u0006public\u0000\u0000\u0000\nset-cookie\u0000\u0000\u0000\nkeep-alive\u0000\u0000\u0000\u0006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(Charsets.UTF_8.name());
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError();
        }
    }
    
    @Override
    public Protocol getProtocol() {
        return Protocol.SPDY_3;
    }
    
    @Override
    public int maxFrameSize() {
        return 16383;
    }
    
    @Override
    public FrameReader newReader(final DataEmitter dataEmitter, final FrameReader.Handler handler, final boolean b) {
        return new Reader(dataEmitter, handler, b);
    }
    
    @Override
    public FrameWriter newWriter(final BufferedDataSink bufferedDataSink, final boolean b) {
        return new Writer(bufferedDataSink, b);
    }
    
    static final class Reader implements FrameReader
    {
        private final boolean client;
        private final DataEmitter emitter;
        private final ByteBufferList emptyList;
        int flags;
        private final Handler handler;
        private final HeaderReader headerReader;
        boolean inFinished;
        int length;
        private final DataCallback onDataFrame;
        private final DataCallback onFrame;
        private final DataCallback onFullFrame;
        ByteBufferList partial;
        private final DataEmitterReader reader;
        int streamId;
        int w1;
        int w2;
        
        Reader(final DataEmitter emitter, final Handler handler, final boolean client) {
            this.headerReader = new HeaderReader();
            this.emptyList = new ByteBufferList();
            this.onFrame = new DataCallback() {
                @Override
                public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                    boolean inFinished = true;
                    list.order(ByteOrder.BIG_ENDIAN);
                    Reader.this.w1 = list.getInt();
                    Reader.this.w2 = list.getInt();
                    final boolean b = (Integer.MIN_VALUE & Reader.this.w1) != 0x0 && inFinished;
                    Reader.this.flags = (0xFF000000 & Reader.this.w2) >>> 24;
                    Reader.this.length = (0xFFFFFF & Reader.this.w2);
                    if (!b) {
                        Reader.this.streamId = (Integer.MAX_VALUE & Reader.this.w1);
                        final Reader this$0 = Reader.this;
                        if ((0x1 & Reader.this.flags) == 0x0) {
                            inFinished = false;
                        }
                        this$0.inFinished = inFinished;
                        dataEmitter.setDataCallback(Reader.this.onDataFrame);
                        if (Reader.this.length == 0) {
                            Reader.this.onDataFrame.onDataAvailable(dataEmitter, Reader.this.emptyList);
                        }
                    }
                    else {
                        Reader.this.reader.read(Reader.this.length, Reader.this.onFullFrame);
                    }
                }
            };
            this.partial = new ByteBufferList();
            this.onDataFrame = new DataCallback() {
                @Override
                public void onDataAvailable(final DataEmitter dataEmitter, ByteBufferList partial) {
                    final int min = Math.min(partial.remaining(), Reader.this.length);
                    if (min < partial.remaining()) {
                        partial.get(Reader.this.partial, min);
                        partial = Reader.this.partial;
                    }
                    final Reader this$0 = Reader.this;
                    this$0.length -= min;
                    Reader.this.handler.data(Reader.this.length == 0 && Reader.this.inFinished, Reader.this.streamId, partial);
                    if (Reader.this.length == 0) {
                        Reader.this.parseFrameHeader();
                    }
                }
            };
            this.onFullFrame = new DataCallback() {
                @Override
                public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                    list.order(ByteOrder.BIG_ENDIAN);
                    final int n = (0x7FFF0000 & Reader.this.w1) >>> 16;
                    final int n2 = 0xFFFF & Reader.this.w1;
                    if (n != 3) {
                        try {
                            throw new ProtocolException("version != 3: " + n);
                        }
                        catch (IOException ex) {
                            Reader.this.handler.error(ex);
                        }
                    }
                    else {
                        switch (n2) {
                            default: {
                                list.recycle();
                                break;
                            }
                            case 1: {
                                Reader.this.readSynStream(list, Reader.this.flags, Reader.this.length);
                                break;
                            }
                            case 2: {
                                Reader.this.readSynReply(list, Reader.this.flags, Reader.this.length);
                                break;
                            }
                            case 3: {
                                Reader.this.readRstStream(list, Reader.this.flags, Reader.this.length);
                                break;
                            }
                            case 4: {
                                Reader.this.readSettings(list, Reader.this.flags, Reader.this.length);
                                break;
                            }
                            case 6: {
                                Reader.this.readPing(list, Reader.this.flags, Reader.this.length);
                                break;
                            }
                            case 7: {
                                Reader.this.readGoAway(list, Reader.this.flags, Reader.this.length);
                                break;
                            }
                            case 8: {
                                Reader.this.readHeaders(list, Reader.this.flags, Reader.this.length);
                                break;
                            }
                            case 9: {
                                Reader.this.readWindowUpdate(list, Reader.this.flags, Reader.this.length);
                                break;
                            }
                        }
                        Reader.this.parseFrameHeader();
                    }
                }
            };
            this.emitter = emitter;
            this.handler = handler;
            this.client = client;
            emitter.setEndCallback(new CompletedCallback() {
                @Override
                public void onCompleted(final Exception ex) {
                }
            });
            this.reader = new DataEmitterReader();
            this.parseFrameHeader();
        }
        
        private static IOException ioException(final String s, final Object... array) throws IOException {
            throw new IOException(String.format(Locale.ENGLISH, s, array));
        }
        
        private void parseFrameHeader() {
            this.emitter.setDataCallback(this.reader);
            this.reader.read(8, this.onFrame);
        }
        
        private void readGoAway(final ByteBufferList list, final int n, final int n2) throws IOException {
            if (n2 != 8) {
                throw ioException("TYPE_GOAWAY length: %d != 8", n2);
            }
            final int n3 = Integer.MAX_VALUE & list.getInt();
            final int int1 = list.getInt();
            final ErrorCode fromSpdyGoAway = ErrorCode.fromSpdyGoAway(int1);
            if (fromSpdyGoAway == null) {
                throw ioException("TYPE_GOAWAY unexpected error code: %d", int1);
            }
            this.handler.goAway(n3, fromSpdyGoAway, ByteString.EMPTY);
        }
        
        private void readHeaders(final ByteBufferList list, final int n, final int n2) throws IOException {
            this.handler.headers(false, false, Integer.MAX_VALUE & list.getInt(), -1, this.headerReader.readHeader(list, n2 - 4), HeadersMode.SPDY_HEADERS);
        }
        
        private void readPing(final ByteBufferList list, final int n, final int n2) throws IOException {
            int n3 = 1;
            if (n2 != 4) {
                final Object[] array = new Object[n3];
                array[0] = n2;
                throw ioException("TYPE_PING length: %d != 4", array);
            }
            final int int1 = list.getInt();
            if (this.client != ((int1 & 0x1) == n3 && n3)) {
                n3 = 0;
            }
            this.handler.ping(n3 != 0, int1, 0);
        }
        
        private void readRstStream(final ByteBufferList list, final int n, final int n2) throws IOException {
            if (n2 != 8) {
                throw ioException("TYPE_RST_STREAM length: %d != 8", n2);
            }
            final int n3 = Integer.MAX_VALUE & list.getInt();
            final int int1 = list.getInt();
            final ErrorCode fromSpdy3Rst = ErrorCode.fromSpdy3Rst(int1);
            if (fromSpdy3Rst == null) {
                throw ioException("TYPE_RST_STREAM unexpected error code: %d", int1);
            }
            this.handler.rstStream(n3, fromSpdy3Rst);
        }
        
        private void readSettings(final ByteBufferList list, final int n, final int n2) throws IOException {
            int n3 = 1;
            final int int1 = list.getInt();
            if (n2 != 4 + int1 * 8) {
                final Object[] array = { n2, null };
                array[n3] = int1;
                throw ioException("TYPE_SETTINGS length: %d != 4 + 8 * %d", array);
            }
            final Settings settings = new Settings();
            for (int i = 0; i < int1; ++i) {
                final int int2 = list.getInt();
                settings.set(int2 & 0xFFFFFF, (0xFF000000 & int2) >>> 24, list.getInt());
            }
            if ((n & 0x1) == 0x0) {
                n3 = 0;
            }
            this.handler.settings(n3 != 0, settings);
        }
        
        private void readSynReply(final ByteBufferList list, final int n, final int n2) throws IOException {
            this.handler.headers(false, (n & 0x1) != 0x0, Integer.MAX_VALUE & list.getInt(), -1, this.headerReader.readHeader(list, n2 - 4), HeadersMode.SPDY_REPLY);
        }
        
        private void readSynStream(final ByteBufferList list, final int n, final int n2) throws IOException {
            boolean b = true;
            final int int1 = list.getInt();
            final int int2 = list.getInt();
            final int n3 = int1 & Integer.MAX_VALUE;
            final int n4 = int2 & Integer.MAX_VALUE;
            list.getShort();
            final List<Header> header = this.headerReader.readHeader(list, n2 - 10);
            final boolean b2 = (n & 0x1) != 0x0 && b;
            if ((n & 0x2) == 0x0) {
                b = false;
            }
            this.handler.headers(b, b2, n3, n4, header, HeadersMode.SPDY_SYN_STREAM);
        }
        
        private void readWindowUpdate(final ByteBufferList list, final int n, final int n2) throws IOException {
            if (n2 != 8) {
                throw ioException("TYPE_WINDOW_UPDATE length: %d != 8", n2);
            }
            final int int1 = list.getInt();
            final int int2 = list.getInt();
            final int n3 = int1 & Integer.MAX_VALUE;
            final long n4 = int2 & Integer.MAX_VALUE;
            if (n4 == 0L) {
                throw ioException("windowSizeIncrement was 0", n4);
            }
            this.handler.windowUpdate(n3, n4);
        }
    }
    
    static final class Writer implements FrameWriter
    {
        private final boolean client;
        private boolean closed;
        ByteBufferList dataList;
        private final Deflater deflater;
        private ByteBufferList frameHeader;
        ByteBufferList headerBlockList;
        private final BufferedDataSink sink;
        
        Writer(final BufferedDataSink sink, final boolean client) {
            this.frameHeader = new ByteBufferList();
            this.deflater = new Deflater();
            this.dataList = new ByteBufferList();
            this.headerBlockList = new ByteBufferList();
            this.sink = sink;
            this.client = client;
            this.deflater.setDictionary(Spdy3.DICTIONARY);
        }
        
        private ByteBufferList writeNameValueBlockToBuffer(final List<Header> list) throws IOException {
            if (this.headerBlockList.hasRemaining()) {
                throw new IllegalStateException();
            }
            ByteBuffer order = ByteBufferList.obtain(8192).order(ByteOrder.BIG_ENDIAN);
            order.putInt(list.size());
            for (int i = 0; i < list.size(); ++i) {
                final ByteString name = list.get(i).name;
                order.putInt(name.size());
                order.put(name.toByteArray());
                final ByteString value = list.get(i).value;
                order.putInt(value.size());
                order.put(value.toByteArray());
                if (order.remaining() < order.capacity() / 2) {
                    final ByteBuffer order2 = ByteBufferList.obtain(2 * order.capacity()).order(ByteOrder.BIG_ENDIAN);
                    order.flip();
                    order2.put(order);
                    ByteBufferList.reclaim(order);
                    order = order2;
                }
            }
            order.flip();
            this.deflater.setInput(order.array(), 0, order.remaining());
            while (!this.deflater.needsInput()) {
                final ByteBuffer order3 = ByteBufferList.obtain(order.capacity()).order(ByteOrder.BIG_ENDIAN);
                order3.limit(this.deflater.deflate(order3.array(), 0, order3.capacity(), 2));
                this.headerBlockList.add(order3);
            }
            ByteBufferList.reclaim(order);
            return this.headerBlockList;
        }
        
        @Override
        public void ackSettings() {
        }
        
        @Override
        public void close() throws IOException {
            synchronized (this) {
                this.closed = true;
            }
        }
        
        @Override
        public void connectionPreface() {
        }
        // monitorenter(this)
        // monitorexit(this)
        
        @Override
        public void data(final boolean b, final int n, final ByteBufferList list) throws IOException {
            // monitorenter(this)
            Label_0020: {
                if (!b) {
                    break Label_0020;
                }
                int n2 = 1;
                try {
                    while (true) {
                        this.sendDataFrame(n, n2, list);
                        return;
                        n2 = 0;
                        continue;
                    }
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        
        @Override
        public void goAway(final int n, final ErrorCode errorCode, final byte[] array) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            if (errorCode.spdyGoAwayCode == -1) {
                throw new IllegalArgumentException("errorCode.spdyGoAwayCode == -1");
            }
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(-2147287033);
            order.putInt(8);
            order.putInt(n);
            order.putInt(errorCode.spdyGoAwayCode);
            order.flip();
            this.sink.write(this.frameHeader.addAll(order));
        }
        // monitorexit(this)
        
        @Override
        public void headers(final int n, final List<Header> list) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            final ByteBufferList writeNameValueBlockToBuffer = this.writeNameValueBlockToBuffer(list);
            final int n2 = 4 + writeNameValueBlockToBuffer.remaining();
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(-2147287032);
            order.putInt(0x0 | (0xFFFFFF & n2));
            order.putInt(Integer.MAX_VALUE & n);
            order.flip();
            this.sink.write(this.frameHeader.add(order).add(writeNameValueBlockToBuffer));
        }
        // monitorexit(this)
        
        @Override
        public void ping(final boolean b, final int n, final int n2) throws IOException {
            boolean b2 = true;
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            if (this.client == ((n & 0x1) == (b2 ? 1 : 0) && b2)) {
                b2 = false;
            }
            if (b != b2) {
                throw new IllegalArgumentException("payload != reply");
            }
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(-2147287034);
            order.putInt(4);
            order.putInt(n);
            order.flip();
            this.sink.write(this.frameHeader.addAll(order));
        }
        // monitorexit(this)
        
        @Override
        public void pushPromise(final int n, final int n2, final List<Header> list) throws IOException {
        }
        
        @Override
        public void rstStream(final int n, final ErrorCode errorCode) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            if (errorCode.spdyRstCode == -1) {
                throw new IllegalArgumentException();
            }
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(-2147287037);
            order.putInt(8);
            order.putInt(Integer.MAX_VALUE & n);
            order.putInt(errorCode.spdyRstCode);
            order.flip();
            this.sink.write(this.frameHeader.addAll(order));
        }
        // monitorexit(this)
        
        void sendDataFrame(final int n, final int n2, final ByteBufferList list) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            final int remaining = list.remaining();
            if (remaining > 16777215L) {
                throw new IllegalArgumentException("FRAME_TOO_LARGE max size is 16Mib: " + remaining);
            }
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(Integer.MAX_VALUE & n);
            order.putInt((n2 & 0xFF) << 24 | (0xFFFFFF & remaining));
            order.flip();
            this.dataList.add(order).add(list);
            this.sink.write(this.dataList);
        }
        
        @Override
        public void settings(final Settings settings) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            final int size = settings.size();
            final int n = 4 + size * 8;
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(-2147287036);
            order.putInt(0x0 | (n & 0xFFFFFF));
            order.putInt(size);
            for (int i = 0; i <= 10; ++i) {
                if (settings.isSet(i)) {
                    order.putInt((0xFF & settings.flags(i)) << 24 | (i & 0xFFFFFF));
                    order.putInt(settings.get(i));
                }
            }
            order.flip();
            this.sink.write(this.frameHeader.addAll(order));
        }
        // monitorexit(this)
        
        @Override
        public void synReply(final boolean b, final int n, final List<Header> list) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            final ByteBufferList writeNameValueBlockToBuffer = this.writeNameValueBlockToBuffer(list);
            boolean b2;
            if (b) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            final int n2 = 4 + writeNameValueBlockToBuffer.remaining();
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(-2147287038);
            order.putInt(((b2 ? 1 : 0) & 0xFF) << 24 | (0xFFFFFF & n2));
            order.putInt(Integer.MAX_VALUE & n);
            order.flip();
            this.sink.write(this.frameHeader.add(order).add(writeNameValueBlockToBuffer));
        }
        // monitorexit(this)
        
        @Override
        public void synStream(final boolean b, final boolean b2, final int n, final int n2, final List<Header> list) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            final ByteBufferList writeNameValueBlockToBuffer = this.writeNameValueBlockToBuffer(list);
            final int n3 = 10 + writeNameValueBlockToBuffer.remaining();
            boolean b3;
            if (b) {
                b3 = true;
            }
            else {
                b3 = false;
            }
            int n4;
            if (b2) {
                n4 = 2;
            }
            else {
                n4 = 0;
            }
            final boolean b4 = ((b3 ? 1 : 0) | n4) != 0x0;
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(-2147287039);
            order.putInt(((b4 ? 1 : 0) & 0xFF) << 24 | (0xFFFFFF & n3));
            order.putInt(n & Integer.MAX_VALUE);
            order.putInt(n2 & Integer.MAX_VALUE);
            order.putShort((short)0);
            order.flip();
            this.sink.write(this.frameHeader.add(order).add(writeNameValueBlockToBuffer));
        }
        // monitorexit(this)
        
        @Override
        public void windowUpdate(final int n, final long n2) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            if (n2 == 0L || n2 > 2147483647L) {
                throw new IllegalArgumentException("windowSizeIncrement must be between 1 and 0x7fffffff: " + n2);
            }
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(-2147287031);
            order.putInt(8);
            order.putInt(n);
            order.putInt((int)n2);
            order.flip();
            this.sink.write(this.frameHeader.addAll(order));
        }
        // monitorexit(this)
    }
}
