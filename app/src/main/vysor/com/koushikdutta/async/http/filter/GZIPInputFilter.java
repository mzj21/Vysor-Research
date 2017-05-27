// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.filter;

import java.util.Locale;
import java.nio.ByteBuffer;
import com.koushikdutta.async.callback.DataCallback;
import java.io.IOException;
import com.koushikdutta.async.PushParser;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import java.nio.ByteOrder;
import java.util.zip.Inflater;
import java.util.zip.CRC32;

public class GZIPInputFilter extends InflaterInputFilter
{
    private static final int FCOMMENT = 16;
    private static final int FEXTRA = 4;
    private static final int FHCRC = 2;
    private static final int FNAME = 8;
    protected CRC32 crc;
    boolean mNeedsHeader;
    
    public GZIPInputFilter() {
        super(new Inflater(true));
        this.mNeedsHeader = true;
        this.crc = new CRC32();
    }
    
    static short peekShort(final byte[] array, final int n, final ByteOrder byteOrder) {
        short n2;
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            n2 = (short)(array[n] << 8 | (0xFF & array[n + 1]));
        }
        else {
            n2 = (short)(array[n + 1] << 8 | (0xFF & array[n]));
        }
        return n2;
    }
    
    public static int unsignedToBytes(final byte b) {
        return b & 0xFF;
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        if (this.mNeedsHeader) {
            final PushParser pushParser = new PushParser(dataEmitter);
            pushParser.readByteArray(10, (PushParser.ParseCallback<byte[]>)new PushParser.ParseCallback<byte[]>() {
                int flags;
                boolean hcrc;
                
                private void done() {
                    if (this.hcrc) {
                        pushParser.readByteArray(2, (PushParser.ParseCallback<byte[]>)new ParseCallback<byte[]>() {
                            public void parsed(final byte[] array) {
                                if ((short)GZIPInputFilter.this.crc.getValue() != GZIPInputFilter.peekShort(array, 0, ByteOrder.LITTLE_ENDIAN)) {
                                    GZIPInputFilter.this.report(new IOException("CRC mismatch"));
                                }
                                else {
                                    GZIPInputFilter.this.crc.reset();
                                    GZIPInputFilter.this.mNeedsHeader = false;
                                    GZIPInputFilter.this.setDataEmitter(dataEmitter);
                                }
                            }
                        });
                    }
                    else {
                        GZIPInputFilter.this.mNeedsHeader = false;
                        GZIPInputFilter.this.setDataEmitter(dataEmitter);
                    }
                }
                
                private void next() {
                    final PushParser pushParser = new PushParser(dataEmitter);
                    final DataCallback dataCallback = new DataCallback() {
                        @Override
                        public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                            if (ParseCallback.this.hcrc) {
                                while (list.size() > 0) {
                                    final ByteBuffer remove = list.remove();
                                    GZIPInputFilter.this.crc.update(remove.array(), remove.arrayOffset() + remove.position(), remove.remaining());
                                    ByteBufferList.reclaim(remove);
                                }
                            }
                            list.recycle();
                            ParseCallback.this.done();
                        }
                    };
                    if ((0x8 & this.flags) != 0x0) {
                        pushParser.until((byte)0, dataCallback);
                    }
                    else if ((0x10 & this.flags) != 0x0) {
                        pushParser.until((byte)0, dataCallback);
                    }
                    else {
                        this.done();
                    }
                }
                
                public void parsed(final byte[] array) {
                    int hcrc = 1;
                    final short peekShort = GZIPInputFilter.peekShort(array, 0, ByteOrder.LITTLE_ENDIAN);
                    if (peekShort != -29921) {
                        final GZIPInputFilter this$0 = GZIPInputFilter.this;
                        final Locale english = Locale.ENGLISH;
                        final Object[] array2 = new Object[hcrc];
                        array2[0] = peekShort;
                        this$0.report(new IOException(String.format(english, "unknown format (magic number %x)", array2)));
                        dataEmitter.setDataCallback(new DataCallback.NullDataCallback());
                    }
                    else {
                        this.flags = array[3];
                        if ((0x2 & this.flags) == 0x0) {
                            hcrc = 0;
                        }
                        this.hcrc = (hcrc != 0);
                        if (this.hcrc) {
                            GZIPInputFilter.this.crc.update(array, 0, array.length);
                        }
                        if ((0x4 & this.flags) != 0x0) {
                            pushParser.readByteArray(2, (PushParser.ParseCallback<byte[]>)new ParseCallback<byte[]>() {
                                public void parsed(final byte[] array) {
                                    if (ParseCallback.this.hcrc) {
                                        GZIPInputFilter.this.crc.update(array, 0, 2);
                                    }
                                    pushParser.readByteArray(0xFFFF & GZIPInputFilter.peekShort(array, 0, ByteOrder.LITTLE_ENDIAN), (PushParser.ParseCallback<byte[]>)new ParseCallback<byte[]>() {
                                        public void parsed(final byte[] array) {
                                            if (ParseCallback.this.hcrc) {
                                                GZIPInputFilter.this.crc.update(array, 0, array.length);
                                            }
                                            ParseCallback.this.next();
                                        }
                                    });
                                }
                            });
                        }
                        else {
                            this.next();
                        }
                    }
                }
            });
        }
        else {
            super.onDataAvailable(dataEmitter, list);
        }
    }
}
