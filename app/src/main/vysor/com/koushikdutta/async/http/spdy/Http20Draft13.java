// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import java.util.List;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.nio.ByteOrder;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitterReader;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.http.Protocol;
import java.util.Locale;
import java.io.IOException;
import java.util.logging.Logger;

final class Http20Draft13 implements Variant
{
    private static final ByteString CONNECTION_PREFACE;
    static final byte FLAG_ACK = 1;
    static final byte FLAG_COMPRESSED = 32;
    static final byte FLAG_END_HEADERS = 4;
    static final byte FLAG_END_PUSH_PROMISE = 4;
    static final byte FLAG_END_SEGMENT = 2;
    static final byte FLAG_END_STREAM = 1;
    static final byte FLAG_NONE = 0;
    static final byte FLAG_PADDED = 8;
    static final byte FLAG_PRIORITY = 32;
    static final int MAX_FRAME_SIZE = 16383;
    static final byte TYPE_CONTINUATION = 9;
    static final byte TYPE_DATA = 0;
    static final byte TYPE_GOAWAY = 7;
    static final byte TYPE_HEADERS = 1;
    static final byte TYPE_PING = 6;
    static final byte TYPE_PRIORITY = 2;
    static final byte TYPE_PUSH_PROMISE = 5;
    static final byte TYPE_RST_STREAM = 3;
    static final byte TYPE_SETTINGS = 4;
    static final byte TYPE_WINDOW_UPDATE = 8;
    private static final Logger logger;
    
    static {
        logger = Logger.getLogger(Http20Draft13.class.getName());
        CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    }
    
    private static IllegalArgumentException illegalArgument(final String s, final Object... array) {
        throw new IllegalArgumentException(String.format(Locale.ENGLISH, s, array));
    }
    
    private static IOException ioException(final String s, final Object... array) throws IOException {
        throw new IOException(String.format(Locale.ENGLISH, s, array));
    }
    
    private static short lengthWithoutPadding(short n, final byte b, final short n2) throws IOException {
        if ((b & 0x8) != 0x0) {
            --n;
        }
        if (n2 > n) {
            throw ioException("PROTOCOL_ERROR padding %s > remaining length %s", n2, n);
        }
        return (short)(n - n2);
    }
    
    @Override
    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }
    
    @Override
    public int maxFrameSize() {
        return 16383;
    }
    
    @Override
    public FrameReader newReader(final DataEmitter dataEmitter, final FrameReader.Handler handler, final boolean b) {
        return new Reader(dataEmitter, handler, 4096, b);
    }
    
    @Override
    public FrameWriter newWriter(final BufferedDataSink bufferedDataSink, final boolean b) {
        return new Writer(bufferedDataSink, b);
    }
    
    static final class FrameLogger
    {
        private static final String[] BINARY;
        private static final String[] FLAGS;
        private static final String[] TYPES;
        
        static {
            TYPES = new String[] { "DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION" };
            FLAGS = new String[64];
            BINARY = new String[256];
            for (int i = 0; i < FrameLogger.BINARY.length; ++i) {
                FrameLogger.BINARY[i] = String.format(Locale.ENGLISH, "%8s", Integer.toBinaryString(i)).replace(' ', '0');
            }
            FrameLogger.FLAGS[0] = "";
            FrameLogger.FLAGS[1] = "END_STREAM";
            FrameLogger.FLAGS[2] = "END_SEGMENT";
            FrameLogger.FLAGS[3] = "END_STREAM|END_SEGMENT";
            final int[] array = { 1, 2, 3 };
            FrameLogger.FLAGS[8] = "PADDED";
            for (final int n : array) {
                FrameLogger.FLAGS[n | 0x8] = FrameLogger.FLAGS[n] + "|PADDED";
            }
            FrameLogger.FLAGS[4] = "END_HEADERS";
            FrameLogger.FLAGS[32] = "PRIORITY";
            FrameLogger.FLAGS[36] = "END_HEADERS|PRIORITY";
            for (final int n2 : new int[] { 4, 32, 36 }) {
                for (final int n3 : array) {
                    FrameLogger.FLAGS[n3 | n2] = FrameLogger.FLAGS[n3] + '|' + FrameLogger.FLAGS[n2];
                    FrameLogger.FLAGS[0x8 | (n3 | n2)] = FrameLogger.FLAGS[n3] + '|' + FrameLogger.FLAGS[n2] + "|PADDED";
                }
            }
            for (int n4 = 0; n4 < FrameLogger.FLAGS.length; ++n4) {
                if (FrameLogger.FLAGS[n4] == null) {
                    FrameLogger.FLAGS[n4] = FrameLogger.BINARY[n4];
                }
            }
        }
        
        static String formatFlags(final byte b, final byte b2) {
            String s = null;
            if (b2 == 0) {
                s = "";
            }
            else {
                switch (b) {
                    default: {
                        String s2;
                        if (b2 < FrameLogger.FLAGS.length) {
                            s2 = FrameLogger.FLAGS[b2];
                        }
                        else {
                            s2 = FrameLogger.BINARY[b2];
                        }
                        if (b == 5 && (b2 & 0x4) != 0x0) {
                            s = s2.replace("HEADERS", "PUSH_PROMISE");
                            break;
                        }
                        if (b == 0 && (b2 & 0x20) != 0x0) {
                            s = s2.replace("PRIORITY", "COMPRESSED");
                            break;
                        }
                        s = s2;
                        break;
                    }
                    case 4:
                    case 6: {
                        if (b2 == 1) {
                            s = "ACK";
                            break;
                        }
                        s = FrameLogger.BINARY[b2];
                        break;
                    }
                    case 2:
                    case 3:
                    case 7:
                    case 8: {
                        s = FrameLogger.BINARY[b2];
                        break;
                    }
                }
            }
            return s;
        }
        
        static String formatHeader(final boolean b, final int n, final int n2, final byte b2, final byte b3) {
            String format;
            if (b2 < FrameLogger.TYPES.length) {
                format = FrameLogger.TYPES[b2];
            }
            else {
                format = String.format(Locale.ENGLISH, "0x%02x", b2);
            }
            final String formatFlags = formatFlags(b2, b3);
            final Locale english = Locale.ENGLISH;
            final Object[] array = new Object[5];
            String s;
            if (b) {
                s = "<<";
            }
            else {
                s = ">>";
            }
            array[0] = s;
            array[1] = n;
            array[2] = n2;
            array[3] = format;
            array[4] = formatFlags;
            return String.format(english, "%s 0x%08x %5d %-13s %s", array);
        }
    }
    
    static final class Reader implements FrameReader
    {
        private final boolean client;
        int continuingStreamId;
        private final DataEmitter emitter;
        byte flags;
        private final Handler handler;
        final HpackDraft08.Reader hpackReader;
        short length;
        private final DataCallback onFrame;
        private final DataCallback onFullFrame;
        byte pendingHeaderType;
        int promisedStreamId;
        private final DataEmitterReader reader;
        int streamId;
        byte type;
        int w1;
        int w2;
        
        Reader(final DataEmitter emitter, final Handler handler, final int n, final boolean client) {
            this.onFrame = new DataCallback() {
                @Override
                public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                    list.order(ByteOrder.BIG_ENDIAN);
                    Reader.this.w1 = list.getInt();
                    Reader.this.w2 = list.getInt();
                    Reader.this.length = (short)((0x3FFF0000 & Reader.this.w1) >> 16);
                    Reader.this.type = (byte)((0xFF00 & Reader.this.w1) >> 8);
                    Reader.this.flags = (byte)(0xFF & Reader.this.w1);
                    Reader.this.streamId = (Integer.MAX_VALUE & Reader.this.w2);
                    if (Http20Draft13.logger.isLoggable(Level.FINE)) {
                        Http20Draft13.logger.fine(FrameLogger.formatHeader(true, Reader.this.streamId, Reader.this.length, Reader.this.type, Reader.this.flags));
                    }
                    Reader.this.reader.read(Reader.this.length, Reader.this.onFullFrame);
                }
            };
            this.onFullFrame = new DataCallback() {
                @Override
                public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                    while (true) {
                        while (true) {
                            Label_0379: {
                                Label_0347: {
                                    Label_0315: {
                                        Label_0283: {
                                            Label_0251: {
                                                Label_0219: {
                                                    Label_0187: {
                                                        Label_0155: {
                                                            Label_0123: {
                                                                try {
                                                                    switch (Reader.this.type) {
                                                                        default: {
                                                                            list.recycle();
                                                                            break;
                                                                        }
                                                                        case 0: {
                                                                            Reader.this.readData(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                                                            break;
                                                                        }
                                                                        case 1: {
                                                                            break Label_0123;
                                                                        }
                                                                        case 2: {
                                                                            break Label_0155;
                                                                        }
                                                                        case 3: {
                                                                            break Label_0187;
                                                                        }
                                                                        case 4: {
                                                                            break Label_0219;
                                                                        }
                                                                        case 5: {
                                                                            break Label_0251;
                                                                        }
                                                                        case 6: {
                                                                            break Label_0283;
                                                                        }
                                                                        case 7: {
                                                                            break Label_0315;
                                                                        }
                                                                        case 8: {
                                                                            break Label_0347;
                                                                        }
                                                                        case 9: {
                                                                            break Label_0379;
                                                                        }
                                                                    }
                                                                    Reader.this.parseFrameHeader();
                                                                    break;
                                                                }
                                                                catch (IOException ex) {
                                                                    Reader.this.handler.error(ex);
                                                                    break;
                                                                }
                                                            }
                                                            Reader.this.readHeaders(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                                            continue;
                                                        }
                                                        Reader.this.readPriority(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                                        continue;
                                                    }
                                                    Reader.this.readRstStream(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                                    continue;
                                                }
                                                Reader.this.readSettings(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                                continue;
                                            }
                                            Reader.this.readPushPromise(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                            continue;
                                        }
                                        Reader.this.readPing(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                        continue;
                                    }
                                    Reader.this.readGoAway(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                    continue;
                                }
                                Reader.this.readWindowUpdate(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                                continue;
                            }
                            Reader.this.readContinuation(list, Reader.this.length, Reader.this.flags, Reader.this.streamId);
                            continue;
                        }
                    }
                }
            };
            this.emitter = emitter;
            this.client = client;
            this.hpackReader = new HpackDraft08.Reader(n);
            this.handler = handler;
            this.reader = new DataEmitterReader();
            this.parseFrameHeader();
        }
        
        private void parseFrameHeader() {
            this.emitter.setDataCallback(this.reader);
            this.reader.read(8, this.onFrame);
        }
        
        private void readContinuation(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            if (n2 != this.continuingStreamId) {
                throw new IOException("continuation stream id mismatch");
            }
            this.readHeaderBlock(list, n, (short)0, b, n2);
        }
        
        private void readData(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            boolean b2 = true;
            final boolean b3 = (b & 0x1) != 0x0 && b2;
            if ((b & 0x20) == 0x0) {
                b2 = false;
            }
            if (b2) {
                throw ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            }
            final byte b4 = (byte)(b & 0x8);
            short n3 = 0;
            if (b4 != 0) {
                n3 = (short)(0xFF & list.get());
            }
            lengthWithoutPadding(n, b, n3);
            this.handler.data(b3, n2, list);
            list.skip(n3);
        }
        
        private void readGoAway(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            if (n < 8) {
                throw ioException("TYPE_GOAWAY length < 8: %s", new Object[] { n });
            }
            if (n2 != 0) {
                throw ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
            }
            final int int1 = list.getInt();
            final int int2 = list.getInt();
            final short n3 = (short)(n - 8);
            final ErrorCode fromHttp2 = ErrorCode.fromHttp2(int2);
            if (fromHttp2 == null) {
                throw ioException("TYPE_GOAWAY unexpected error code: %d", new Object[] { int2 });
            }
            ByteString byteString = ByteString.EMPTY;
            if (n3 > 0) {
                byteString = ByteString.of(list.getBytes(n3));
            }
            this.handler.goAway(int1, fromHttp2, byteString);
        }
        
        private void readHeaderBlock(final ByteBufferList list, final short n, final short n2, final byte b, final int continuingStreamId) throws IOException {
            boolean b2 = true;
            list.skip(n2);
            this.hpackReader.refill(list);
            this.hpackReader.readHeaders();
            this.hpackReader.emitReferenceSet();
            if ((b & 0x4) != 0x0) {
                if (this.pendingHeaderType == (b2 ? 1 : 0)) {
                    if ((b & 0x1) == 0x0) {
                        b2 = false;
                    }
                    this.handler.headers(false, b2, continuingStreamId, -1, this.hpackReader.getAndReset(), HeadersMode.HTTP_20_HEADERS);
                }
                else {
                    if (this.pendingHeaderType != 5) {
                        throw new AssertionError((Object)"unknown header type");
                    }
                    this.handler.pushPromise(continuingStreamId, this.promisedStreamId, this.hpackReader.getAndReset());
                }
            }
            else {
                this.continuingStreamId = continuingStreamId;
            }
        }
        
        private void readHeaders(final ByteBufferList list, short n, final byte b, final int n2) throws IOException {
            if (n2 == 0) {
                throw ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            }
            final byte b2 = (byte)(b & 0x8);
            short n3 = 0;
            if (b2 != 0) {
                n3 = (short)(0xFF & list.get());
            }
            if ((b & 0x20) != 0x0) {
                this.readPriority(list, n2);
                n -= 5;
            }
            final short access$1600 = lengthWithoutPadding(n, b, n3);
            this.pendingHeaderType = this.type;
            this.readHeaderBlock(list, access$1600, n3, b, n2);
        }
        
        private void readPing(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            int n3 = 1;
            if (n != 8) {
                final Object[] array = new Object[n3];
                array[0] = n;
                throw ioException("TYPE_PING length != 8: %s", array);
            }
            if (n2 != 0) {
                throw ioException("TYPE_PING streamId != 0", new Object[0]);
            }
            final int int1 = list.getInt();
            final int int2 = list.getInt();
            if ((b & 0x1) == 0x0) {
                n3 = 0;
            }
            this.handler.ping(n3 != 0, int1, int2);
        }
        
        private void readPriority(final ByteBufferList list, final int n) throws IOException {
            final int int1 = list.getInt();
            this.handler.priority(n, int1 & Integer.MAX_VALUE, 1 + (0xFF & list.get()), (Integer.MIN_VALUE & int1) != 0x0);
        }
        
        private void readPriority(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            if (n != 5) {
                throw ioException("TYPE_PRIORITY length: %d != 5", new Object[] { n });
            }
            if (n2 == 0) {
                throw ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
            }
            this.readPriority(list, n2);
        }
        
        private void readPushPromise(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            if (n2 == 0) {
                throw ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            }
            final byte b2 = (byte)(b & 0x8);
            short n3 = 0;
            if (b2 != 0) {
                n3 = (short)(0xFF & list.get());
            }
            this.promisedStreamId = (Integer.MAX_VALUE & list.getInt());
            final short access$1600 = lengthWithoutPadding((short)(n - 4), b, n3);
            this.pendingHeaderType = 5;
            this.readHeaderBlock(list, access$1600, n3, b, n2);
        }
        
        private void readRstStream(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            if (n != 4) {
                throw ioException("TYPE_RST_STREAM length: %d != 4", new Object[] { n });
            }
            if (n2 == 0) {
                throw ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
            }
            final int int1 = list.getInt();
            final ErrorCode fromHttp2 = ErrorCode.fromHttp2(int1);
            if (fromHttp2 == null) {
                throw ioException("TYPE_RST_STREAM unexpected error code: %d", new Object[] { int1 });
            }
            this.handler.rstStream(n2, fromHttp2);
        }
        
        private void readSettings(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            if (n2 != 0) {
                throw ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
            }
            if ((b & 0x1) != 0x0) {
                if (n != 0) {
                    throw ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                }
                this.handler.ackSettings();
            }
            else {
                if (n % 6 != 0) {
                    throw ioException("TYPE_SETTINGS length %% 6 != 0: %s", new Object[] { n });
                }
                final Settings settings = new Settings();
                short n3 = 0;
            Label_0193_Outer:
                while (n3 < n) {
                    short short1 = list.getShort();
                    final int int1 = list.getInt();
                    while (true) {
                        switch (short1) {
                            default: {
                                throw ioException("PROTOCOL_ERROR invalid settings id: %s", new Object[] { short1 });
                            }
                            case 2: {
                                if (int1 != 0 && int1 != 1) {
                                    throw ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                                }
                                break Label_0193;
                            }
                            case 3: {
                                short1 = 4;
                            }
                            case 1:
                            case 5: {
                                settings.set(short1, 0, int1);
                                n3 += 6;
                                continue Label_0193_Outer;
                            }
                            case 4: {
                                short1 = 7;
                                if (int1 < 0) {
                                    throw ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                                }
                                continue;
                            }
                        }
                        break;
                    }
                }
                this.handler.settings(false, settings);
                if (settings.getHeaderTableSize() >= 0) {
                    this.hpackReader.maxHeaderTableByteCountSetting(settings.getHeaderTableSize());
                }
            }
        }
        
        private void readWindowUpdate(final ByteBufferList list, final short n, final byte b, final int n2) throws IOException {
            if (n != 4) {
                throw ioException("TYPE_WINDOW_UPDATE length !=4: %s", new Object[] { n });
            }
            final long n3 = 0x7FFFFFFFL & list.getInt();
            if (n3 == 0L) {
                throw ioException("windowSizeIncrement was 0", new Object[] { n3 });
            }
            this.handler.windowUpdate(n2, n3);
        }
    }
    
    static final class Writer implements FrameWriter
    {
        private final boolean client;
        private boolean closed;
        private final ByteBufferList frameHeader;
        private final HpackDraft08.Writer hpackWriter;
        private final BufferedDataSink sink;
        
        Writer(final BufferedDataSink sink, final boolean client) {
            this.frameHeader = new ByteBufferList();
            this.sink = sink;
            this.client = client;
            this.hpackWriter = new HpackDraft08.Writer();
        }
        
        private void writeContinuationFrames(final ByteBufferList list, final int n) throws IOException {
            while (list.hasRemaining()) {
                final int min = Math.min(16383, list.remaining());
                byte b;
                if (list.remaining() - min == 0) {
                    b = 4;
                }
                else {
                    b = 0;
                }
                this.frameHeader(n, min, (byte)9, b);
                list.get(this.frameHeader, min);
                this.sink.write(this.frameHeader);
            }
        }
        
        @Override
        public void ackSettings() throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            this.frameHeader(0, 0, (byte)4, (byte)1);
        }
        // monitorexit(this)
        
        @Override
        public void close() throws IOException {
            synchronized (this) {
                this.closed = true;
            }
        }
        
        @Override
        public void connectionPreface() throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            if (this.client) {
                if (Http20Draft13.logger.isLoggable(Level.FINE)) {
                    Http20Draft13.logger.fine(String.format(Locale.ENGLISH, ">> CONNECTION %s", Http20Draft13.CONNECTION_PREFACE.hex()));
                }
                this.sink.write(new ByteBufferList(Http20Draft13.CONNECTION_PREFACE.toByteArray()));
            }
        }
        // monitorexit(this)
        
        @Override
        public void data(final boolean b, final int n, final ByteBufferList list) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            byte b2 = 0;
            if (b) {
                b2 = 1;
            }
            this.dataFrame(n, b2, list);
        }
        // monitorexit(this)
        
        void dataFrame(final int n, final byte b, final ByteBufferList list) throws IOException {
            this.frameHeader(n, list.remaining(), (byte)0, b);
            this.sink.write(list);
        }
        
        void frameHeader(final int n, final int n2, final byte b, final byte b2) throws IOException {
            if (Http20Draft13.logger.isLoggable(Level.FINE)) {
                Http20Draft13.logger.fine(FrameLogger.formatHeader(false, n, n2, b, b2));
            }
            if (n2 > 16383) {
                throw illegalArgument("FRAME_SIZE_ERROR length > %d: %d", new Object[] { 16383, n2 });
            }
            if ((Integer.MIN_VALUE & n) != 0x0) {
                throw illegalArgument("reserved bit set: %s", new Object[] { n });
            }
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt((n2 & 0x3FFF) << 16 | (b & 0xFF) << 8 | (b2 & 0xFF));
            order.putInt(Integer.MAX_VALUE & n);
            order.flip();
            this.sink.write(this.frameHeader.add(order));
        }
        
        @Override
        public void goAway(final int n, final ErrorCode errorCode, final byte[] array) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            if (errorCode.httpCode == -1) {
                throw illegalArgument("errorCode.httpCode == -1", new Object[0]);
            }
            this.frameHeader(0, 8 + array.length, (byte)7, (byte)0);
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(n);
            order.putInt(errorCode.httpCode);
            order.put(array);
            order.flip();
            this.sink.write(this.frameHeader.add(order));
        }
        // monitorexit(this)
        
        @Override
        public void headers(final int n, final List<Header> list) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            this.headers(false, n, list);
        }
        // monitorexit(this)
        
        void headers(final boolean b, final int n, final List<Header> list) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            final ByteBufferList writeHeaders = this.hpackWriter.writeHeaders(list);
            final long n2 = writeHeaders.remaining();
            final int n3 = (int)Math.min(16383L, n2);
            byte b2;
            if (n2 == n3) {
                b2 = 4;
            }
            else {
                b2 = 0;
            }
            if (b) {
                b2 |= 0x1;
            }
            this.frameHeader(n, n3, (byte)1, b2);
            writeHeaders.get(this.frameHeader, n3);
            this.sink.write(this.frameHeader);
            if (n2 > n3) {
                this.writeContinuationFrames(writeHeaders, n);
            }
        }
        
        @Override
        public void ping(final boolean b, final int n, final int n2) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            byte b2;
            if (b) {
                b2 = 1;
            }
            else {
                b2 = 0;
            }
            this.frameHeader(0, 8, (byte)6, b2);
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt(n);
            order.putInt(n2);
            order.flip();
            this.sink.write(this.frameHeader.add(order));
        }
        // monitorexit(this)
        
        @Override
        public void pushPromise(final int n, final int n2, final List<Header> list) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            final ByteBufferList writeHeaders = this.hpackWriter.writeHeaders(list);
            final long n3 = writeHeaders.remaining();
            final int n4 = (int)Math.min(16379L, n3);
            byte b;
            if (n3 == n4) {
                b = 4;
            }
            else {
                b = 0;
            }
            this.frameHeader(n, n4 + 4, (byte)5, b);
            final ByteBuffer order = ByteBufferList.obtain(8192).order(ByteOrder.BIG_ENDIAN);
            order.putInt(Integer.MAX_VALUE & n2);
            order.flip();
            this.frameHeader.add(order);
            writeHeaders.get(this.frameHeader, n4);
            this.sink.write(this.frameHeader);
            if (n3 > n4) {
                this.writeContinuationFrames(writeHeaders, n);
            }
        }
        // monitorexit(this)
        
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
            this.frameHeader(n, 4, (byte)3, (byte)0);
            final ByteBuffer order = ByteBufferList.obtain(8192).order(ByteOrder.BIG_ENDIAN);
            order.putInt(errorCode.httpCode);
            order.flip();
            this.sink.write(this.frameHeader.add(order));
        }
        // monitorexit(this)
        
        @Override
        public void settings(final Settings settings) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            this.frameHeader(0, 6 * settings.size(), (byte)4, (byte)0);
            final ByteBuffer order = ByteBufferList.obtain(8192).order(ByteOrder.BIG_ENDIAN);
            for (int i = 0; i < 10; ++i) {
                if (settings.isSet(i)) {
                    int n = i;
                    if (n == 4) {
                        n = 3;
                    }
                    else if (n == 7) {
                        n = 4;
                    }
                    order.putShort((short)n);
                    order.putInt(settings.get(i));
                }
            }
            order.flip();
            this.sink.write(this.frameHeader.add(order));
        }
        // monitorexit(this)
        
        @Override
        public void synReply(final boolean b, final int n, final List<Header> list) throws IOException {
            synchronized (this) {
                if (this.closed) {
                    throw new IOException("closed");
                }
            }
            this.headers(b, n, list);
        }
        // monitorexit(this)
        
        @Override
        public void synStream(final boolean b, final boolean b2, final int n, final int n2, final List<Header> list) throws IOException {
            // monitorenter(this)
            if (b2) {
                try {
                    throw new UnsupportedOperationException();
                }
                finally {
                }
                // monitorexit(this)
            }
            if (this.closed) {
                throw new IOException("closed");
            }
            this.headers(b, n, list);
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
                throw illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[] { n2 });
            }
            this.frameHeader(n, 4, (byte)8, (byte)0);
            final ByteBuffer order = ByteBufferList.obtain(256).order(ByteOrder.BIG_ENDIAN);
            order.putInt((int)n2);
            order.flip();
            this.sink.write(this.frameHeader.add(order));
        }
        // monitorexit(this)
    }
}
