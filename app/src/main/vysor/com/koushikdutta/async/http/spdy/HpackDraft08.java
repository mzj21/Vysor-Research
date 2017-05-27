// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import com.koushikdutta.async.ByteBufferList;
import java.util.List;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.util.Map;

final class HpackDraft08
{
    private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX;
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    private static final Header[] STATIC_HEADER_TABLE;
    
    static {
        STATIC_HEADER_TABLE = new Header[] { new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "") };
        NAME_TO_FIRST_INDEX = nameToFirstIndex();
    }
    
    private static ByteString checkLowercase(final ByteString byteString) throws IOException {
        for (int i = 0; i < byteString.size(); ++i) {
            final byte byte1 = byteString.getByte(i);
            if (byte1 >= 65 && byte1 <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
        return byteString;
    }
    
    private static Map<ByteString, Integer> nameToFirstIndex() {
        final LinkedHashMap<ByteString, Integer> linkedHashMap = new LinkedHashMap<ByteString, Integer>(HpackDraft08.STATIC_HEADER_TABLE.length);
        for (int i = 0; i < HpackDraft08.STATIC_HEADER_TABLE.length; ++i) {
            if (!linkedHashMap.containsKey(HpackDraft08.STATIC_HEADER_TABLE[i].name)) {
                linkedHashMap.put(HpackDraft08.STATIC_HEADER_TABLE[i].name, i);
            }
        }
        return Collections.unmodifiableMap((Map<? extends ByteString, ? extends Integer>)linkedHashMap);
    }
    
    static final class Reader
    {
        private final List<Header> emittedHeaders;
        BitArray emittedReferencedHeaders;
        int headerCount;
        Header[] headerTable;
        int headerTableByteCount;
        private int maxHeaderTableByteCount;
        private int maxHeaderTableByteCountSetting;
        int nextHeaderIndex;
        BitArray referencedHeaders;
        private final ByteBufferList source;
        
        Reader(final int n) {
            this.emittedHeaders = new ArrayList<Header>();
            this.source = new ByteBufferList();
            this.headerTable = new Header[8];
            this.nextHeaderIndex = -1 + this.headerTable.length;
            this.headerCount = 0;
            this.referencedHeaders = new BitArray.FixedCapacity();
            this.emittedReferencedHeaders = new BitArray.FixedCapacity();
            this.headerTableByteCount = 0;
            this.maxHeaderTableByteCountSetting = n;
            this.maxHeaderTableByteCount = n;
        }
        
        private void adjustHeaderTableByteCount() {
            if (this.maxHeaderTableByteCount < this.headerTableByteCount) {
                if (this.maxHeaderTableByteCount == 0) {
                    this.clearHeaderTable();
                }
                else {
                    this.evictToRecoverBytes(this.headerTableByteCount - this.maxHeaderTableByteCount);
                }
            }
        }
        
        private void clearHeaderTable() {
            this.clearReferenceSet();
            Arrays.fill(this.headerTable, null);
            this.nextHeaderIndex = -1 + this.headerTable.length;
            this.headerCount = 0;
            this.headerTableByteCount = 0;
        }
        
        private void clearReferenceSet() {
            this.referencedHeaders.clear();
            this.emittedReferencedHeaders.clear();
        }
        
        private int evictToRecoverBytes(int n) {
            int n2 = 0;
            if (n > 0) {
                for (int n3 = -1 + this.headerTable.length; n3 >= this.nextHeaderIndex && n > 0; n -= this.headerTable[n3].hpackSize, this.headerTableByteCount -= this.headerTable[n3].hpackSize, --this.headerCount, ++n2, --n3) {}
                this.referencedHeaders.shiftLeft(n2);
                this.emittedReferencedHeaders.shiftLeft(n2);
                System.arraycopy(this.headerTable, 1 + this.nextHeaderIndex, this.headerTable, n2 + (1 + this.nextHeaderIndex), this.headerCount);
                this.nextHeaderIndex += n2;
            }
            return n2;
        }
        
        private ByteString getName(final int n) {
            ByteString byteString;
            if (this.isStaticHeader(n)) {
                byteString = HpackDraft08.STATIC_HEADER_TABLE[n - this.headerCount].name;
            }
            else {
                byteString = this.headerTable[this.headerTableIndex(n)].name;
            }
            return byteString;
        }
        
        private int headerTableIndex(final int n) {
            return n + (1 + this.nextHeaderIndex);
        }
        
        private void insertIntoHeaderTable(final int n, final Header header) {
            int hpackSize = header.hpackSize;
            if (n != -1) {
                hpackSize -= this.headerTable[this.headerTableIndex(n)].hpackSize;
            }
            if (hpackSize > this.maxHeaderTableByteCount) {
                this.clearHeaderTable();
                this.emittedHeaders.add(header);
            }
            else {
                final int evictToRecoverBytes = this.evictToRecoverBytes(hpackSize + this.headerTableByteCount - this.maxHeaderTableByteCount);
                if (n == -1) {
                    if (1 + this.headerCount > this.headerTable.length) {
                        final Header[] headerTable = new Header[2 * this.headerTable.length];
                        System.arraycopy(this.headerTable, 0, headerTable, this.headerTable.length, this.headerTable.length);
                        if (headerTable.length == 64) {
                            this.referencedHeaders = ((BitArray.FixedCapacity)this.referencedHeaders).toVariableCapacity();
                            this.emittedReferencedHeaders = ((BitArray.FixedCapacity)this.emittedReferencedHeaders).toVariableCapacity();
                        }
                        this.referencedHeaders.shiftLeft(this.headerTable.length);
                        this.emittedReferencedHeaders.shiftLeft(this.headerTable.length);
                        this.nextHeaderIndex = -1 + this.headerTable.length;
                        this.headerTable = headerTable;
                    }
                    final int n2 = this.nextHeaderIndex--;
                    this.referencedHeaders.set(n2);
                    this.headerTable[n2] = header;
                    ++this.headerCount;
                }
                else {
                    final int n3 = n + (evictToRecoverBytes + this.headerTableIndex(n));
                    this.referencedHeaders.set(n3);
                    this.headerTable[n3] = header;
                }
                this.headerTableByteCount += hpackSize;
            }
        }
        
        private boolean isStaticHeader(final int n) {
            return n >= this.headerCount;
        }
        
        private int readByte() throws IOException {
            return 0xFF & this.source.get();
        }
        
        private void readIndexedHeader(final int n) throws IOException {
            if (this.isStaticHeader(n)) {
                final int n2 = n - this.headerCount;
                if (n2 > -1 + HpackDraft08.STATIC_HEADER_TABLE.length) {
                    throw new IOException("Header index too large " + (n2 + 1));
                }
                final Header header = HpackDraft08.STATIC_HEADER_TABLE[n2];
                if (this.maxHeaderTableByteCount == 0) {
                    this.emittedHeaders.add(header);
                }
                else {
                    this.insertIntoHeaderTable(-1, header);
                }
            }
            else {
                final int headerTableIndex = this.headerTableIndex(n);
                if (!this.referencedHeaders.get(headerTableIndex)) {
                    this.emittedHeaders.add(this.headerTable[headerTableIndex]);
                    this.emittedReferencedHeaders.set(headerTableIndex);
                }
                this.referencedHeaders.toggle(headerTableIndex);
            }
        }
        
        private void readLiteralHeaderWithIncrementalIndexingIndexedName(final int n) throws IOException {
            this.insertIntoHeaderTable(-1, new Header(this.getName(n), this.readByteString()));
        }
        
        private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            this.insertIntoHeaderTable(-1, new Header(checkLowercase(this.readByteString()), this.readByteString()));
        }
        
        private void readLiteralHeaderWithoutIndexingIndexedName(final int n) throws IOException {
            this.emittedHeaders.add(new Header(this.getName(n), this.readByteString()));
        }
        
        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            this.emittedHeaders.add(new Header(checkLowercase(this.readByteString()), this.readByteString()));
        }
        
        void emitReferenceSet() {
            for (int i = -1 + this.headerTable.length; i != this.nextHeaderIndex; --i) {
                if (this.referencedHeaders.get(i) && !this.emittedReferencedHeaders.get(i)) {
                    this.emittedHeaders.add(this.headerTable[i]);
                }
            }
        }
        
        List<Header> getAndReset() {
            final ArrayList<Header> list = new ArrayList<Header>(this.emittedHeaders);
            this.emittedHeaders.clear();
            this.emittedReferencedHeaders.clear();
            return list;
        }
        
        int maxHeaderTableByteCount() {
            return this.maxHeaderTableByteCount;
        }
        
        void maxHeaderTableByteCountSetting(final int maxHeaderTableByteCountSetting) {
            this.maxHeaderTableByteCountSetting = maxHeaderTableByteCountSetting;
            this.maxHeaderTableByteCount = this.maxHeaderTableByteCountSetting;
            this.adjustHeaderTableByteCount();
        }
        
        ByteString readByteString() throws IOException {
            final int byte1 = this.readByte();
            int n;
            if ((byte1 & 0x80) == 0x80) {
                n = 1;
            }
            else {
                n = 0;
            }
            final int int1 = this.readInt(byte1, 127);
            ByteString byteString;
            if (n != 0) {
                byteString = ByteString.of(Huffman.get().decode(this.source.getBytes(int1)));
            }
            else {
                byteString = ByteString.of(this.source.getBytes(int1));
            }
            return byteString;
        }
        
        void readHeaders() throws IOException {
            while (this.source.hasRemaining()) {
                final int n = 0xFF & this.source.get();
                if (n == 128) {
                    throw new IOException("index == 0");
                }
                if ((n & 0x80) == 0x80) {
                    this.readIndexedHeader(-1 + this.readInt(n, 127));
                }
                else if (n == 64) {
                    this.readLiteralHeaderWithIncrementalIndexingNewName();
                }
                else if ((n & 0x40) == 0x40) {
                    this.readLiteralHeaderWithIncrementalIndexingIndexedName(-1 + this.readInt(n, 63));
                }
                else if ((n & 0x20) == 0x20) {
                    if ((n & 0x10) == 0x10) {
                        if ((n & 0xF) != 0x0) {
                            throw new IOException("Invalid header table state change " + n);
                        }
                        this.clearReferenceSet();
                    }
                    else {
                        this.maxHeaderTableByteCount = this.readInt(n, 15);
                        if (this.maxHeaderTableByteCount < 0 || this.maxHeaderTableByteCount > this.maxHeaderTableByteCountSetting) {
                            throw new IOException("Invalid header table byte count " + this.maxHeaderTableByteCount);
                        }
                        this.adjustHeaderTableByteCount();
                    }
                }
                else if (n == 16 || n == 0) {
                    this.readLiteralHeaderWithoutIndexingNewName();
                }
                else {
                    this.readLiteralHeaderWithoutIndexingIndexedName(-1 + this.readInt(n, 15));
                }
            }
        }
        
        int readInt(final int n, final int n2) throws IOException {
            int n3 = n & n2;
            if (n3 >= n2) {
                int n4 = n2;
                int n5 = 0;
                int byte1;
                while (true) {
                    byte1 = this.readByte();
                    if ((byte1 & 0x80) == 0x0) {
                        break;
                    }
                    n4 += (byte1 & 0x7F) << n5;
                    n5 += 7;
                }
                n3 = n4 + (byte1 << n5);
            }
            return n3;
        }
        
        public void refill(final ByteBufferList list) {
            list.get(this.source);
        }
    }
    
    static final class Writer
    {
        void writeByteString(final ByteBuffer byteBuffer, final ByteString byteString) throws IOException {
            this.writeInt(byteBuffer, byteString.size(), 127, 0);
            byteBuffer.put(byteString.toByteArray());
        }
        
        ByteBufferList writeHeaders(final List<Header> list) throws IOException {
            final ByteBufferList list2 = new ByteBufferList();
            ByteBuffer byteBuffer = ByteBufferList.obtain(8192);
            for (int i = 0; i < list.size(); ++i) {
                if (byteBuffer.remaining() < byteBuffer.capacity() / 2) {
                    byteBuffer.flip();
                    list2.add(byteBuffer);
                    byteBuffer = ByteBufferList.obtain(2 * byteBuffer.capacity());
                }
                final ByteString asciiLowercase = list.get(i).name.toAsciiLowercase();
                final Integer n = HpackDraft08.NAME_TO_FIRST_INDEX.get(asciiLowercase);
                if (n != null) {
                    this.writeInt(byteBuffer, 1 + n, 15, 0);
                    this.writeByteString(byteBuffer, list.get(i).value);
                }
                else {
                    byteBuffer.put((byte)0);
                    this.writeByteString(byteBuffer, asciiLowercase);
                    this.writeByteString(byteBuffer, list.get(i).value);
                }
            }
            list2.add(byteBuffer);
            return list2;
        }
        
        void writeInt(final ByteBuffer byteBuffer, final int n, final int n2, final int n3) throws IOException {
            if (n < n2) {
                byteBuffer.put((byte)(n3 | n));
            }
            else {
                byteBuffer.put((byte)(n3 | n2));
                int i;
                for (i = n - n2; i >= 128; i >>>= 7) {
                    byteBuffer.put((byte)(0x80 | (i & 0x7F)));
                }
                byteBuffer.put((byte)i);
            }
        }
    }
}
