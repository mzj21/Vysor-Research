// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.List;
import com.koushikdutta.async.ByteBufferList;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

class HeaderReader
{
    Inflater inflater;
    
    public HeaderReader() {
        this.inflater = new Inflater() {
            @Override
            public int inflate(final byte[] array, final int n, final int n2) throws DataFormatException {
                int n3 = super.inflate(array, n, n2);
                if (n3 == 0 && this.needsDictionary()) {
                    this.setDictionary(Spdy3.DICTIONARY);
                    n3 = super.inflate(array, n, n2);
                }
                return n3;
            }
        };
    }
    
    private static ByteString readByteString(final ByteBufferList list) {
        return ByteString.of(list.getBytes(list.getInt()));
    }
    
    public List<Header> readHeader(final ByteBufferList list, final int n) throws IOException {
        final byte[] input = new byte[n];
        list.get(input);
        this.inflater.setInput(input);
        final ByteBufferList order = new ByteBufferList().order(ByteOrder.BIG_ENDIAN);
        while (!this.inflater.needsInput()) {
            final ByteBuffer obtain = ByteBufferList.obtain(8192);
            try {
                obtain.limit(this.inflater.inflate(obtain.array()));
                order.add(obtain);
                continue;
            }
            catch (DataFormatException ex) {
                throw new IOException(ex);
            }
            break;
        }
        final int int1 = order.getInt();
        final ArrayList list2 = new ArrayList<Header>(int1);
        for (int i = 0; i < int1; ++i) {
            final ByteString asciiLowercase = readByteString(order).toAsciiLowercase();
            final ByteString byteString = readByteString(order);
            if (asciiLowercase.size() == 0) {
                throw new IOException("name.size == 0");
            }
            list2.add(new Header(asciiLowercase, byteString));
        }
        return (List<Header>)list2;
    }
}
