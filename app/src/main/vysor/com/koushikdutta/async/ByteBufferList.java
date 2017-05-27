// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.util.Charsets;
import java.nio.charset.Charset;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import android.os.Looper;
import java.util.Comparator;
import java.nio.ByteOrder;
import java.util.PriorityQueue;
import java.nio.ByteBuffer;
import android.annotation.TargetApi;

@TargetApi(9)
public class ByteBufferList
{
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final ByteBuffer EMPTY_BYTEBUFFER;
    private static final Object LOCK;
    public static int MAX_ITEM_SIZE;
    private static int MAX_SIZE;
    static int currentSize;
    static int maxItem;
    static PriorityQueue<ByteBuffer> reclaimed;
    ArrayDeque<ByteBuffer> mBuffers;
    ByteOrder order;
    private int remaining;
    
    static {
        ByteBufferList.reclaimed = new PriorityQueue<ByteBuffer>(8, new Reclaimer());
        ByteBufferList.MAX_SIZE = 1048576;
        ByteBufferList.MAX_ITEM_SIZE = 262144;
        ByteBufferList.currentSize = 0;
        ByteBufferList.maxItem = 0;
        LOCK = new Object();
        EMPTY_BYTEBUFFER = ByteBuffer.allocate(0);
    }
    
    public ByteBufferList() {
        this.mBuffers = new ArrayDeque<ByteBuffer>();
        this.order = ByteOrder.BIG_ENDIAN;
        this.remaining = 0;
    }
    
    public ByteBufferList(final byte[] array) {
        this.mBuffers = new ArrayDeque<ByteBuffer>();
        this.order = ByteOrder.BIG_ENDIAN;
        this.remaining = 0;
        this.add(ByteBuffer.wrap(array));
    }
    
    public ByteBufferList(final ByteBuffer... array) {
        this.mBuffers = new ArrayDeque<ByteBuffer>();
        this.order = ByteOrder.BIG_ENDIAN;
        this.remaining = 0;
        this.addAll(array);
    }
    
    private void addRemaining(final int n) {
        if (this.remaining() >= 0) {
            this.remaining += n;
        }
    }
    
    private static PriorityQueue<ByteBuffer> getReclaimed() {
        final Looper mainLooper = Looper.getMainLooper();
        PriorityQueue<ByteBuffer> reclaimed;
        if (mainLooper != null && Thread.currentThread() == mainLooper.getThread()) {
            reclaimed = null;
        }
        else {
            reclaimed = ByteBufferList.reclaimed;
        }
        return reclaimed;
    }
    
    public static ByteBuffer obtain(final int n) {
        if (n <= ByteBufferList.maxItem) {
            final PriorityQueue<ByteBuffer> reclaimed = getReclaimed();
            if (reclaimed != null) {
                // monitorenter(ByteBufferList.LOCK)
            Label_0083_Outer:
                while (true) {
                    Label_0131: {
                        ByteBuffer allocate = null;
                        Label_0118: {
                            while (true) {
                            Label_0112:
                                while (true) {
                                    try {
                                        if (reclaimed.size() <= 0) {
                                            break Label_0131;
                                        }
                                        allocate = reclaimed.remove();
                                        if (reclaimed.size() == 0) {
                                            ByteBufferList.maxItem = 0;
                                        }
                                        ByteBufferList.currentSize -= allocate.capacity();
                                        if (ByteBufferList.$assertionsDisabled) {
                                            break Label_0118;
                                        }
                                        if (reclaimed.size() != 0) {
                                            final int n2 = 1;
                                            if (ByteBufferList.currentSize != 0) {
                                                break Label_0112;
                                            }
                                            final int n3 = 1;
                                            if ((n3 ^ n2) == 0x0) {
                                                throw new AssertionError();
                                            }
                                            break Label_0118;
                                        }
                                    }
                                    finally {
                                    }
                                    // monitorexit(ByteBufferList.LOCK)
                                    final int n2 = 0;
                                    continue Label_0083_Outer;
                                }
                                final int n3 = 0;
                                continue;
                            }
                        }
                        if (allocate.capacity() >= n) {
                            // monitorexit(o)
                            return allocate;
                        }
                        continue;
                    }
                    // monitorexit(o)
                    break;
                }
            }
        }
        return ByteBuffer.allocate(Math.max(8192, n));
    }
    
    public static void obtainArray(final ByteBuffer[] array, final int n) {
        final PriorityQueue<ByteBuffer> reclaimed = getReclaimed();
        int n2 = 0;
    Label_0127_Outer:
        while (true) {
        Label_0121_Outer:
            while (true) {
            Label_0168_Outer:
                while (true) {
                Label_0115_Outer:
                    while (true) {
                        while (true) {
                            Label_0079: {
                                int n3 = 0;
                                Block_2: {
                                    if (reclaimed != null) {
                                        final Object lock = ByteBufferList.LOCK;
                                        // monitorenter(lock)
                                        n3 = 0;
                                        break Block_2;
                                    }
                                    n3 = 0;
                                    n2 = 0;
                                Label_0171:
                                    while (true) {
                                        break Label_0171;
                                        final Throwable t;
                                        final ByteBuffer byteBuffer;
                                        int n4;
                                        int n5;
                                        int n6;
                                        boolean b;
                                        ByteBuffer allocate;
                                        boolean b2;
                                        Label_0206_Outer:Label_0202_Outer:
                                        while (true) {
                                            while (true) {
                                                try {
                                                    // monitorexit(lock)
                                                    throw t;
                                                    n2 += Math.min(n - n2, byteBuffer.capacity());
                                                    n4 = n3 + 1;
                                                    array[n3] = byteBuffer;
                                                    n3 = n4;
                                                    break;
                                                    Label_0226: {
                                                        return;
                                                    }
                                                    // iftrue(Label_0226:, n5 >= array.length)
                                                    while (true) {
                                                        while (true) {
                                                            array[n5] = ByteBufferList.EMPTY_BYTEBUFFER;
                                                            ++n5;
                                                            continue Label_0206_Outer;
                                                        }
                                                        n5 = n6;
                                                        continue Label_0202_Outer;
                                                    }
                                                    b = false;
                                                    break Label_0115_Outer;
                                                    // iftrue(Label_0232:, n2 >= n)
                                                    while (true) {
                                                        allocate = ByteBuffer.allocate(Math.max(8192, n - n2));
                                                        n6 = n3 + 1;
                                                        array[n3] = allocate;
                                                        continue Label_0121_Outer;
                                                        continue Label_0168_Outer;
                                                    }
                                                    // monitorexit(lock)
                                                    continue Label_0171;
                                                    b2 = false;
                                                    break Label_0079;
                                                }
                                                finally {
                                                    continue Label_0127_Outer;
                                                }
                                                Label_0232: {
                                                    n6 = n3;
                                                }
                                                continue Label_0121_Outer;
                                            }
                                        }
                                        break;
                                    }
                                }
                                try {
                                    if (reclaimed.size() <= 0 || n2 >= n || n3 >= -1 + array.length) {
                                        continue Label_0115_Outer;
                                    }
                                    final ByteBuffer byteBuffer = reclaimed.remove();
                                    ByteBufferList.currentSize -= byteBuffer.capacity();
                                    if (ByteBufferList.$assertionsDisabled) {
                                        continue Label_0121_Outer;
                                    }
                                    if (reclaimed.size() == 0) {
                                        continue;
                                    }
                                    final boolean b2 = true;
                                    if (ByteBufferList.currentSize != 0) {
                                        continue Label_0168_Outer;
                                    }
                                    final boolean b = true;
                                    if (!(b ^ b2)) {
                                        throw new AssertionError();
                                    }
                                    continue Label_0121_Outer;
                                }
                                finally {}
                            }
                            break;
                        }
                        break;
                    }
                    break;
                }
                break;
            }
            continue Label_0127_Outer;
        }
    }
    
    private ByteBuffer read(final int n) {
        if (this.remaining() < n) {
            throw new IllegalArgumentException("count : " + this.remaining() + "/" + n);
        }
        ByteBuffer byteBuffer;
        for (byteBuffer = this.mBuffers.peek(); byteBuffer != null && !byteBuffer.hasRemaining(); byteBuffer = this.mBuffers.peek()) {
            reclaim(this.mBuffers.remove());
        }
        ByteBuffer byteBuffer2;
        if (byteBuffer == null) {
            byteBuffer2 = ByteBufferList.EMPTY_BYTEBUFFER;
        }
        else if (byteBuffer.remaining() >= n) {
            byteBuffer2 = byteBuffer.order(this.order);
        }
        else {
            final ByteBuffer obtain = obtain(n);
            obtain.limit(n);
            final byte[] array = obtain.array();
            int i = 0;
            ByteBuffer byteBuffer3 = null;
            while (i < n) {
                byteBuffer3 = this.mBuffers.remove();
                final int min = Math.min(n - i, byteBuffer3.remaining());
                byteBuffer3.get(array, i, min);
                i += min;
                if (byteBuffer3.remaining() == 0) {
                    reclaim(byteBuffer3);
                    byteBuffer3 = null;
                }
            }
            if (byteBuffer3 != null && byteBuffer3.remaining() > 0) {
                this.mBuffers.addFirst(byteBuffer3);
            }
            this.mBuffers.addFirst(obtain);
            byteBuffer2 = obtain.order(this.order);
        }
        return byteBuffer2;
    }
    
    public static void reclaim(final ByteBuffer byteBuffer) {
        if (byteBuffer != null && !byteBuffer.isDirect() && byteBuffer.arrayOffset() == 0 && byteBuffer.array().length == byteBuffer.capacity() && byteBuffer.capacity() >= 8192 && byteBuffer.capacity() <= ByteBufferList.MAX_ITEM_SIZE) {
            final PriorityQueue<ByteBuffer> reclaimed = getReclaimed();
            if (reclaimed != null) {
                synchronized (ByteBufferList.LOCK) {
                    while (ByteBufferList.currentSize > ByteBufferList.MAX_SIZE && reclaimed.size() > 0 && reclaimed.peek().capacity() < byteBuffer.capacity()) {
                        ByteBufferList.currentSize -= ((ByteBuffer)reclaimed.remove()).capacity();
                    }
                }
                if (ByteBufferList.currentSize <= ByteBufferList.MAX_SIZE) {
                    assert !reclaimedContains(byteBuffer);
                    byteBuffer.position(0);
                    byteBuffer.limit(byteBuffer.capacity());
                    ByteBufferList.currentSize += byteBuffer.capacity();
                    reclaimed.add(byteBuffer);
                    if (!ByteBufferList.$assertionsDisabled) {
                        boolean b;
                        if (reclaimed.size() != 0) {
                            b = true;
                        }
                        else {
                            b = false;
                        }
                        boolean b2;
                        if (ByteBufferList.currentSize == 0) {
                            b2 = true;
                        }
                        else {
                            b2 = false;
                        }
                        if (!(b2 ^ b)) {
                            throw new AssertionError();
                        }
                    }
                    ByteBufferList.maxItem = Math.max(ByteBufferList.maxItem, byteBuffer.capacity());
                }
                // monitorexit(o)
            }
        }
    }
    
    private static boolean reclaimedContains(final ByteBuffer byteBuffer) {
        final Iterator<ByteBuffer> iterator = ByteBufferList.reclaimed.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == byteBuffer) {
                return true;
            }
        }
        return false;
    }
    
    public static void setMaxItemSize(final int max_ITEM_SIZE) {
        ByteBufferList.MAX_ITEM_SIZE = max_ITEM_SIZE;
    }
    
    public static void setMaxPoolSize(final int max_SIZE) {
        ByteBufferList.MAX_SIZE = max_SIZE;
    }
    
    public static void writeOutputStream(final OutputStream outputStream, final ByteBuffer byteBuffer) throws IOException {
        byte[] array;
        int n;
        int n2;
        if (byteBuffer.isDirect()) {
            array = new byte[byteBuffer.remaining()];
            n = 0;
            n2 = byteBuffer.remaining();
            byteBuffer.get(array);
        }
        else {
            array = byteBuffer.array();
            n = byteBuffer.arrayOffset() + byteBuffer.position();
            n2 = byteBuffer.remaining();
        }
        outputStream.write(array, n, n2);
    }
    
    public ByteBufferList add(final ByteBufferList list) {
        list.get(this);
        return this;
    }
    
    public ByteBufferList add(final ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= 0) {
            reclaim(byteBuffer);
        }
        else {
            this.addRemaining(byteBuffer.remaining());
            if (this.mBuffers.size() > 0) {
                final ByteBuffer byteBuffer2 = this.mBuffers.getLast();
                if (byteBuffer2.capacity() - byteBuffer2.limit() >= byteBuffer.remaining()) {
                    byteBuffer2.mark();
                    byteBuffer2.position(byteBuffer2.limit());
                    byteBuffer2.limit(byteBuffer2.capacity());
                    byteBuffer2.put(byteBuffer);
                    byteBuffer2.limit(byteBuffer2.position());
                    byteBuffer2.reset();
                    reclaim(byteBuffer);
                    this.trim();
                    return this;
                }
            }
            this.mBuffers.add(byteBuffer);
            this.trim();
        }
        return this;
    }
    
    public ByteBufferList addAll(final ByteBufferList... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].get(this);
        }
        return this;
    }
    
    public ByteBufferList addAll(final ByteBuffer... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.add(array[i]);
        }
        return this;
    }
    
    public void addFirst(final ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= 0) {
            reclaim(byteBuffer);
        }
        else {
            this.addRemaining(byteBuffer.remaining());
            if (this.mBuffers.size() > 0) {
                final ByteBuffer byteBuffer2 = this.mBuffers.getFirst();
                if (byteBuffer2.position() >= byteBuffer.remaining()) {
                    byteBuffer2.position(byteBuffer2.position() - byteBuffer.remaining());
                    byteBuffer2.mark();
                    byteBuffer2.put(byteBuffer);
                    byteBuffer2.reset();
                    reclaim(byteBuffer);
                    return;
                }
            }
            this.mBuffers.addFirst(byteBuffer);
        }
    }
    
    public byte get() {
        final byte value = this.read(1).get();
        --this.remaining;
        return value;
    }
    
    public ByteBufferList get(final int n) {
        final ByteBufferList list = new ByteBufferList();
        this.get(list, n);
        return list.order(this.order);
    }
    
    public void get(final ByteBufferList list) {
        this.get(list, this.remaining());
    }
    
    public void get(final ByteBufferList list, final int n) {
        if (this.remaining() < n) {
            throw new IllegalArgumentException("length");
        }
        int i = 0;
        while (i < n) {
            final ByteBuffer byteBuffer = this.mBuffers.remove();
            final int remaining = byteBuffer.remaining();
            if (remaining == 0) {
                reclaim(byteBuffer);
            }
            else if (i + remaining > n) {
                final int n2 = n - i;
                final ByteBuffer obtain = obtain(n2);
                obtain.limit(n2);
                byteBuffer.get(obtain.array(), 0, n2);
                list.add(obtain);
                this.mBuffers.addFirst(byteBuffer);
                assert obtain.capacity() >= n2;
                assert obtain.position() == 0;
                break;
            }
            else {
                list.add(byteBuffer);
                i += remaining;
            }
        }
        this.remaining -= n;
    }
    
    public void get(final byte[] array) {
        this.get(array, 0, array.length);
    }
    
    public void get(final byte[] array, int n, final int n2) {
        if (this.remaining() < n2) {
            throw new IllegalArgumentException("length");
        }
        int i = n2;
        while (i > 0) {
            final ByteBuffer byteBuffer = this.mBuffers.peek();
            final int min = Math.min(byteBuffer.remaining(), i);
            if (array != null) {
                byteBuffer.get(array, n, min);
            }
            else {
                byteBuffer.position(min + byteBuffer.position());
            }
            i -= min;
            n += min;
            if (byteBuffer.remaining() == 0) {
                final ByteBuffer byteBuffer2 = this.mBuffers.remove();
                assert byteBuffer == byteBuffer2;
                reclaim(byteBuffer);
            }
        }
        this.remaining -= n2;
    }
    
    public ByteBuffer getAll() {
        ByteBuffer byteBuffer;
        if (this.remaining() == 0) {
            byteBuffer = ByteBufferList.EMPTY_BYTEBUFFER;
        }
        else {
            this.read(this.remaining());
            byteBuffer = this.remove();
        }
        return byteBuffer;
    }
    
    public ByteBuffer[] getAllArray() {
        final ByteBuffer[] array = this.mBuffers.toArray(new ByteBuffer[this.mBuffers.size()]);
        this.mBuffers.clear();
        this.remaining = 0;
        return array;
    }
    
    public byte[] getAllByteArray() {
        Label_0061: {
            if (this.mBuffers.size() != 1) {
                break Label_0061;
            }
            final ByteBuffer byteBuffer = this.mBuffers.peek();
            if (byteBuffer.capacity() != this.remaining() || !byteBuffer.isDirect()) {
                break Label_0061;
            }
            this.remaining = 0;
            return this.mBuffers.remove().array();
        }
        final byte[] array = new byte[this.remaining()];
        this.get(array);
        return array;
    }
    
    public char getByteChar() {
        final char c = (char)this.read(1).get();
        --this.remaining;
        return c;
    }
    
    public byte[] getBytes(final int n) {
        final byte[] array = new byte[n];
        this.get(array);
        return array;
    }
    
    public int getInt() {
        final int int1 = this.read(4).getInt();
        this.remaining -= 4;
        return int1;
    }
    
    public long getLong() {
        final long long1 = this.read(8).getLong();
        this.remaining -= 8;
        return long1;
    }
    
    public short getShort() {
        final short short1 = this.read(2).getShort();
        this.remaining -= 2;
        return short1;
    }
    
    public boolean hasRemaining() {
        return this.remaining() > 0;
    }
    
    public boolean isEmpty() {
        return this.remaining == 0;
    }
    
    public ByteBufferList order(final ByteOrder order) {
        this.order = order;
        return this;
    }
    
    public ByteOrder order() {
        return this.order;
    }
    
    public byte[] peekBytes(final int n) {
        final byte[] array = new byte[n];
        this.read(n).duplicate().get(array);
        return array;
    }
    
    public int peekInt() {
        return this.read(4).duplicate().getInt();
    }
    
    public long peekLong() {
        return this.read(8).duplicate().getLong();
    }
    
    public short peekShort() {
        return this.read(2).duplicate().getShort();
    }
    
    public String peekString() {
        return this.peekString(null);
    }
    
    public String peekString(Charset us_ASCII) {
        if (us_ASCII == null) {
            us_ASCII = Charsets.US_ASCII;
        }
        final StringBuilder sb = new StringBuilder();
        for (final ByteBuffer byteBuffer : this.mBuffers) {
            byte[] array;
            int n;
            int n2;
            if (byteBuffer.isDirect()) {
                array = new byte[byteBuffer.remaining()];
                n = 0;
                n2 = byteBuffer.remaining();
                byteBuffer.get(array);
            }
            else {
                array = byteBuffer.array();
                n = byteBuffer.arrayOffset() + byteBuffer.position();
                n2 = byteBuffer.remaining();
            }
            sb.append(new String(array, n, n2, us_ASCII));
        }
        return sb.toString();
    }
    
    public String readString() {
        return this.readString(null);
    }
    
    public String readString(final Charset charset) {
        final String peekString = this.peekString(charset);
        this.recycle();
        return peekString;
    }
    
    public void recycle() {
        while (this.mBuffers.size() > 0) {
            reclaim(this.mBuffers.remove());
        }
        assert this.mBuffers.size() == 0;
        this.remaining = 0;
    }
    
    public int remaining() {
        return this.remaining;
    }
    
    public ByteBuffer remove() {
        final ByteBuffer byteBuffer = this.mBuffers.remove();
        this.remaining -= byteBuffer.remaining();
        return byteBuffer;
    }
    
    public int size() {
        return this.mBuffers.size();
    }
    
    public ByteBufferList skip(final int n) {
        this.get(null, 0, n);
        return this;
    }
    
    public void spewString() {
        System.out.println(this.peekString());
    }
    
    public void trim() {
        this.read(0);
    }
    
    static class Reclaimer implements Comparator<ByteBuffer>
    {
        @Override
        public int compare(final ByteBuffer byteBuffer, final ByteBuffer byteBuffer2) {
            int n;
            if (byteBuffer.capacity() == byteBuffer2.capacity()) {
                n = 0;
            }
            else if (byteBuffer.capacity() > byteBuffer2.capacity()) {
                n = 1;
            }
            else {
                n = -1;
            }
            return n;
        }
    }
}
