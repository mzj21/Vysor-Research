// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import java.util.zip.DataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import java.util.Arrays;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.DataEmitterReader;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;
import java.util.List;

abstract class HybiParser
{
    private static final long BASE = 2L;
    private static final int BYTE = 255;
    private static final int FIN = 128;
    private static final List<Integer> FRAGMENTED_OPCODES;
    private static final int LENGTH = 127;
    private static final int MASK = 128;
    private static final int MODE_BINARY = 2;
    private static final int MODE_TEXT = 1;
    private static final int OPCODE = 15;
    private static final List<Integer> OPCODES;
    private static final int OP_BINARY = 2;
    private static final int OP_CLOSE = 8;
    private static final int OP_CONTINUATION = 0;
    private static final int OP_PING = 9;
    private static final int OP_PONG = 10;
    private static final int OP_TEXT = 1;
    private static final int RSV1 = 64;
    private static final int RSV2 = 32;
    private static final int RSV3 = 16;
    private static final String TAG = "HybiParser";
    private static final long _2_TO_16_ = 65536L;
    private static final long _2_TO_24 = 16777216L;
    private static final long _2_TO_32_ = 4294967296L;
    private static final long _2_TO_40_ = 1099511627776L;
    private static final long _2_TO_48_ = 281474976710656L;
    private static final long _2_TO_56_ = 72057594037927936L;
    private static final long _2_TO_8_ = 256L;
    private ByteArrayOutputStream mBuffer;
    private boolean mClosed;
    private boolean mDeflate;
    private boolean mDeflated;
    private boolean mFinal;
    private byte[] mInflateBuffer;
    private Inflater mInflater;
    private int mLength;
    private int mLengthSize;
    private byte[] mMask;
    private boolean mMasked;
    private boolean mMasking;
    private int mMode;
    private int mOpcode;
    private byte[] mPayload;
    private DataEmitterReader mReader;
    private int mStage;
    DataCallback mStage0;
    DataCallback mStage1;
    DataCallback mStage2;
    DataCallback mStage3;
    DataCallback mStage4;
    
    static {
        OPCODES = Arrays.asList(0, 1, 2, 8, 9, 10);
        FRAGMENTED_OPCODES = Arrays.asList(0, 1, 2);
    }
    
    public HybiParser(final DataEmitter dataEmitter) {
        this.mMasking = true;
        this.mDeflate = false;
        this.mMask = new byte[0];
        this.mPayload = new byte[0];
        this.mClosed = false;
        this.mBuffer = new ByteArrayOutputStream();
        this.mInflater = new Inflater(true);
        this.mInflateBuffer = new byte[4096];
        this.mStage0 = new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                while (true) {
                    try {
                        HybiParser.this.parseOpcode(list.get());
                        HybiParser.this.parse();
                    }
                    catch (ProtocolError protocolError) {
                        HybiParser.this.report(protocolError);
                        protocolError.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
        };
        this.mStage1 = new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                HybiParser.this.parseLength(list.get());
                HybiParser.this.parse();
            }
        };
        this.mStage2 = new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                final byte[] array = new byte[HybiParser.this.mLengthSize];
                list.get(array);
                while (true) {
                    try {
                        HybiParser.this.parseExtendedLength(array);
                        HybiParser.this.parse();
                    }
                    catch (ProtocolError protocolError) {
                        HybiParser.this.report(protocolError);
                        protocolError.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
        };
        this.mStage3 = new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                HybiParser.this.mMask = new byte[4];
                list.get(HybiParser.this.mMask);
                HybiParser.this.mStage = 4;
                HybiParser.this.parse();
            }
        };
        this.mStage4 = new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                assert list.remaining() == HybiParser.this.mLength;
                HybiParser.this.mPayload = new byte[HybiParser.this.mLength];
                list.get(HybiParser.this.mPayload);
                while (true) {
                    try {
                        HybiParser.this.emitFrame();
                        HybiParser.this.mStage = 0;
                        HybiParser.this.parse();
                    }
                    catch (IOException ex) {
                        HybiParser.this.report(ex);
                        ex.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
        };
        dataEmitter.setDataCallback(this.mReader = new DataEmitterReader());
        this.parse();
    }
    
    private static long byteArrayToLong(final byte[] array, final int n, final int n2) {
        if (array.length < n2) {
            throw new IllegalArgumentException("length must be less than or equal to b.length");
        }
        long n3 = 0L;
        for (int i = 0; i < n2; ++i) {
            n3 += (0xFF & array[i + n]) << 8 * (n2 - 1 - i);
        }
        return n3;
    }
    
    private byte[] decode(final String s) {
        try {
            return s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void emitFrame() throws IOException {
        byte[] array = mask(this.mPayload, this.mMask, 0);
        int mOpcode = 0;
        Label_0126: {
            Label_0030: {
                if (!this.mDeflated) {
                    break Label_0030;
                }
                try {
                    array = this.inflate(array);
                    mOpcode = this.mOpcode;
                    if (mOpcode != 0) {
                        break Label_0126;
                    }
                    if (this.mMode == 0) {
                        throw new ProtocolError("Mode was not set.");
                    }
                }
                catch (DataFormatException ex) {
                    throw new IOException("Invalid deflated data");
                }
            }
            this.mBuffer.write(array);
            if (this.mFinal) {
                final byte[] byteArray = this.mBuffer.toByteArray();
                if (this.mMode == 1) {
                    this.onMessage(this.encode(byteArray));
                }
                else {
                    this.onMessage(byteArray);
                }
                this.reset();
            }
            return;
        }
        if (mOpcode == 1) {
            if (this.mFinal) {
                this.onMessage(this.encode(array));
                return;
            }
            this.mMode = 1;
            this.mBuffer.write(array);
        }
        else if (mOpcode == 2) {
            if (this.mFinal) {
                this.onMessage(array);
                return;
            }
            this.mMode = 2;
            this.mBuffer.write(array);
        }
        else {
            if (mOpcode == 8) {
                final int length = array.length;
                int n = 0;
                if (length >= 2) {
                    n = 256 * (0xFF & array[0]) + (0xFF & array[1]);
                }
                String encode;
                if (array.length > 2) {
                    encode = this.encode(this.slice(array, 2));
                }
                else {
                    encode = null;
                }
                this.onDisconnect(n, encode);
                return;
            }
            if (mOpcode == 9) {
                if (array.length > 125) {
                    throw new ProtocolError("Ping payload too large");
                }
                final String encode2 = this.encode(array);
                this.sendFrame(this.frame(10, array, -1));
                this.onPing(encode2);
            }
            else if (mOpcode == 10) {
                this.onPong(this.encode(array));
            }
        }
    }
    
    private String encode(final byte[] array) {
        try {
            return new String(array, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private byte[] frame(final int n, final String s, final int n2) {
        return this.frame(n, this.decode(s), n2);
    }
    
    private byte[] frame(final int n, final byte[] array, final int n2) {
        return this.frame(n, array, n2, 0, array.length);
    }
    
    private byte[] frame(final int n, final byte[] array, final int n2, final int n3, final int n4) {
        byte[] array2;
        if (this.mClosed) {
            array2 = null;
        }
        else {
            int n5;
            if (n2 > 0) {
                n5 = 2;
            }
            else {
                n5 = 0;
            }
            final int n6 = n4 + n5 - n3;
            int n7;
            if (n6 <= 125) {
                n7 = 2;
            }
            else if (n6 <= 65535) {
                n7 = 4;
            }
            else {
                n7 = 10;
            }
            int n8;
            if (this.mMasking) {
                n8 = 4;
            }
            else {
                n8 = 0;
            }
            final int n9 = n7 + n8;
            int n10;
            if (this.mMasking) {
                n10 = 128;
            }
            else {
                n10 = 0;
            }
            array2 = new byte[n6 + n9];
            array2[0] = (byte)(0xFFFFFF80 | (byte)n);
            if (n6 <= 125) {
                array2[1] = (byte)(n10 | n6);
            }
            else if (n6 <= 65535) {
                array2[1] = (byte)(n10 | 0x7E);
                array2[2] = (byte)(n6 / 256);
                array2[3] = (byte)(n6 & 0xFF);
            }
            else {
                array2[1] = (byte)(n10 | 0x7F);
                array2[2] = (byte)(0xFFL & n6 / 72057594037927936L);
                array2[3] = (byte)(0xFFL & n6 / 281474976710656L);
                array2[4] = (byte)(0xFFL & n6 / 1099511627776L);
                array2[5] = (byte)(0xFFL & n6 / 4294967296L);
                array2[6] = (byte)(0xFFL & n6 / 16777216L);
                array2[7] = (byte)(0xFFL & n6 / 65536L);
                array2[8] = (byte)(0xFFL & n6 / 256L);
                array2[9] = (byte)(n6 & 0xFF);
            }
            if (n2 > 0) {
                array2[n9] = (byte)(0xFF & n2 / 256);
                array2[n9 + 1] = (byte)(n2 & 0xFF);
            }
            System.arraycopy(array, n3, array2, n9 + n5, n4 - n3);
            if (this.mMasking) {
                final byte[] array3 = { (byte)Math.floor(256.0 * Math.random()), (byte)Math.floor(256.0 * Math.random()), (byte)Math.floor(256.0 * Math.random()), (byte)Math.floor(256.0 * Math.random()) };
                System.arraycopy(array3, 0, array2, n7, array3.length);
                mask(array2, array3, n9);
            }
        }
        return array2;
    }
    
    private int getInteger(final byte[] array) throws ProtocolError {
        final long byteArrayToLong = byteArrayToLong(array, 0, array.length);
        if (byteArrayToLong < 0L || byteArrayToLong > 2147483647L) {
            throw new ProtocolError("Bad integer: " + byteArrayToLong);
        }
        return (int)byteArrayToLong;
    }
    
    private byte[] inflate(final byte[] input) throws DataFormatException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.mInflater.setInput(input);
        while (!this.mInflater.needsInput()) {
            byteArrayOutputStream.write(this.mInflateBuffer, 0, this.mInflater.inflate(this.mInflateBuffer));
        }
        this.mInflater.setInput(new byte[] { 0, 0, -1, -1 });
        while (!this.mInflater.needsInput()) {
            byteArrayOutputStream.write(this.mInflateBuffer, 0, this.mInflater.inflate(this.mInflateBuffer));
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    private static byte[] mask(final byte[] array, final byte[] array2, final int n) {
        if (array2.length != 0) {
            for (int i = 0; i < array.length - n; ++i) {
                array[n + i] ^= array2[i % 4];
            }
        }
        return array;
    }
    
    private void parseExtendedLength(final byte[] array) throws ProtocolError {
        this.mLength = this.getInteger(array);
        int mStage;
        if (this.mMasked) {
            mStage = 3;
        }
        else {
            mStage = 4;
        }
        this.mStage = mStage;
    }
    
    private void parseLength(final byte b) {
        this.mMasked = ((b & 0x80) == 0x80);
        this.mLength = (b & 0x7F);
        if (this.mLength >= 0 && this.mLength <= 125) {
            int mStage;
            if (this.mMasked) {
                mStage = 3;
            }
            else {
                mStage = 4;
            }
            this.mStage = mStage;
        }
        else {
            int mLengthSize;
            if (this.mLength == 126) {
                mLengthSize = 2;
            }
            else {
                mLengthSize = 8;
            }
            this.mLengthSize = mLengthSize;
            this.mStage = 2;
        }
    }
    
    private void parseOpcode(final byte b) throws ProtocolError {
        final boolean mDeflated = (b & 0x40) == 0x40;
        boolean b2;
        if ((b & 0x20) == 0x20) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if ((b & 0x10) == 0x10) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        if ((!this.mDeflate && mDeflated) || b2 || b3) {
            throw new ProtocolError("RSV not zero");
        }
        this.mFinal = ((b & 0x80) == 0x80);
        this.mOpcode = (b & 0xF);
        this.mDeflated = mDeflated;
        this.mMask = new byte[0];
        this.mPayload = new byte[0];
        if (!HybiParser.OPCODES.contains(this.mOpcode)) {
            throw new ProtocolError("Bad opcode");
        }
        if (!HybiParser.FRAGMENTED_OPCODES.contains(this.mOpcode) && !this.mFinal) {
            throw new ProtocolError("Expected non-final packet");
        }
        this.mStage = 1;
    }
    
    private void reset() {
        this.mMode = 0;
        this.mBuffer.reset();
    }
    
    private byte[] slice(final byte[] array, final int n) {
        final byte[] array2 = new byte[array.length - n];
        System.arraycopy(array, n, array2, 0, array.length - n);
        return array2;
    }
    
    public void close(final int n, final String s) {
        if (!this.mClosed) {
            this.sendFrame(this.frame(8, s, n));
            this.mClosed = true;
        }
    }
    
    public byte[] frame(final String s) {
        return this.frame(1, s, -1);
    }
    
    public byte[] frame(final byte[] array) {
        return this.frame(2, array, -1);
    }
    
    public byte[] frame(final byte[] array, final int n, final int n2) {
        return this.frame(2, array, -1, n, n2);
    }
    
    protected abstract void onDisconnect(final int p0, final String p1);
    
    protected abstract void onMessage(final String p0);
    
    protected abstract void onMessage(final byte[] p0);
    
    protected abstract void onPing(final String p0);
    
    protected abstract void onPong(final String p0);
    
    void parse() {
        switch (this.mStage) {
            case 0: {
                this.mReader.read(1, this.mStage0);
                break;
            }
            case 1: {
                this.mReader.read(1, this.mStage1);
                break;
            }
            case 2: {
                this.mReader.read(this.mLengthSize, this.mStage2);
                break;
            }
            case 3: {
                this.mReader.read(4, this.mStage3);
                break;
            }
            case 4: {
                this.mReader.read(this.mLength, this.mStage4);
                break;
            }
        }
    }
    
    public byte[] pingFrame(final String s) {
        return this.frame(9, s, -1);
    }
    
    public byte[] pongFrame(final String s) {
        return this.frame(10, s, -1);
    }
    
    protected abstract void report(final Exception p0);
    
    protected abstract void sendFrame(final byte[] p0);
    
    public void setDeflate(final boolean mDeflate) {
        this.mDeflate = mDeflate;
    }
    
    public void setMasking(final boolean mMasking) {
        this.mMasking = mMasking;
    }
    
    public static class ProtocolError extends IOException
    {
        public ProtocolError(final String s) {
            super(s);
        }
    }
}
