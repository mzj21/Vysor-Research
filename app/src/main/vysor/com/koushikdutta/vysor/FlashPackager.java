// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.media.MediaFormat;
import java.util.concurrent.TimeUnit;
import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.BufferedDataSink;

public class FlashPackager
{
    private static final byte AMF_DATA_TYPE_ARRAY = 10;
    private static final byte AMF_DATA_TYPE_BOOL = 1;
    private static final byte AMF_DATA_TYPE_DATE = 11;
    private static final byte AMF_DATA_TYPE_LONG_STRING = 12;
    private static final byte AMF_DATA_TYPE_MIXEDARRAY = 8;
    private static final byte AMF_DATA_TYPE_NULL = 5;
    private static final byte AMF_DATA_TYPE_NUMBER = 0;
    private static final byte AMF_DATA_TYPE_OBJECT = 3;
    private static final byte AMF_DATA_TYPE_OBJECT_END = 9;
    private static final byte AMF_DATA_TYPE_REFERENCE = 7;
    private static final byte AMF_DATA_TYPE_STRING = 2;
    private static final byte AMF_DATA_TYPE_UNDEFINED = 6;
    private static final byte AMF_DATA_TYPE_UNSUPPORTED = 13;
    private static final byte AMF_END_OF_OBJECT = 9;
    private static final int FLV_CODECID_H264 = 7;
    private static final int FLV_CODECID_PCM = 0;
    private static final byte FLV_FRAME_INTER = 39;
    private static final byte FLV_FRAME_KEY = 23;
    private static final byte FLV_TAG_TYPE_AUDIO = 8;
    private static final byte FLV_TAG_TYPE_META = 18;
    private static final byte FLV_TAG_TYPE_VIDEO = 9;
    private static final byte FLV_VIDEO_FRAMETYPE_OFFSET = 4;
    private static final String LOGTAG = "FLASHY";
    long first;
    BufferedDataSink sink;
    boolean videoOnly;
    
    public FlashPackager(final BufferedDataSink bufferedDataSink, final double n, final double n2, final int n3) {
        this.videoOnly = true;
        this.first = 0L;
        this.sink = new BufferedDataSink(bufferedDataSink);
        final ByteBuffer order = ByteBufferList.obtain(1024).order(ByteOrder.BIG_ENDIAN);
        order.put("FLV".getBytes());
        order.put((byte)1);
        if (this.videoOnly) {
            order.put((byte)1);
        }
        else {
            order.put((byte)5);
        }
        order.putInt(9);
        order.putInt(0);
        order.flip();
        bufferedDataSink.write(new ByteBufferList(new ByteBuffer[] { order }));
        this.setParameter(n, n2, n3);
    }
    
    static ByteBuffer putDouble(final ByteBuffer byteBuffer, final double n) {
        byteBuffer.put((byte)0);
        byteBuffer.putDouble(n);
        return byteBuffer;
    }
    
    static ByteBuffer putShint(final ByteBuffer byteBuffer, final int n) {
        byteBuffer.putShort((short)(0xFFFF & n >> 8));
        byteBuffer.put((byte)(n & 0xFF));
        return byteBuffer;
    }
    
    static ByteBuffer putString(final ByteBuffer byteBuffer, final String s) {
        final byte[] bytes = s.getBytes();
        byteBuffer.putShort((short)bytes.length);
        byteBuffer.put(bytes);
        return byteBuffer;
    }
    
    private void setParameter(final double n, final double n2, final int n3) {
        final ByteBuffer order = ByteBufferList.obtain(1024).order(ByteOrder.BIG_ENDIAN);
        order.put((byte)18);
        if (this.videoOnly) {
            putShint(order, 182);
        }
        else {
            putShint(order, 290);
        }
        putShint(order, 0);
        order.putInt(0);
        final int position = order.position();
        order.put((byte)2);
        putString(order, "onMetaData");
        order.put((byte)8);
        if (this.videoOnly) {
            order.putInt(7);
        }
        else {
            order.putInt(12);
        }
        putString(order, "duration");
        putDouble(order, 0.0);
        putString(order, "mWidth");
        putDouble(order, n);
        putString(order, "mHeight");
        putDouble(order, n2);
        putString(order, "framerate");
        putDouble(order, n3);
        putString(order, "videocodecid");
        putDouble(order, 7.0);
        putString(order, "videodatarate");
        putDouble(order, 0.0);
        if (!this.videoOnly) {
            putString(order, "audiodatarate");
            putDouble(order, 62.5);
            putString(order, "audiosamplerate");
            putDouble(order, 5112.0);
            putString(order, "audiosamplesize");
            putDouble(order, 8.0);
            putString(order, "stereo");
            order.put((byte)0);
            putString(order, "audiocodecid");
            putDouble(order, 0.0);
        }
        putString(order, "encoder");
        order.put((byte)2);
        putString(order, "Lavf52.87.1");
        putString(order, "filesize");
        putDouble(order, 0.0);
        putString(order, "");
        order.put((byte)9);
        order.putInt(11 + (order.position() - position));
        order.flip();
        this.sink.write(new ByteBufferList(new ByteBuffer[] { order }));
    }
    
    public void addVideoFrame(final ByteBuffer byteBuffer, final MediaCodec$BufferInfo mediaCodec$BufferInfo) {
        final ByteBuffer order = ByteBufferList.obtain(1024).order(ByteOrder.BIG_ENDIAN);
        order.put((byte)9);
        putShint(order, 5 + mediaCodec$BufferInfo.size);
        if (this.first == 0L) {
            this.first = mediaCodec$BufferInfo.presentationTimeUs;
        }
        final long millis = TimeUnit.MICROSECONDS.toMillis(mediaCodec$BufferInfo.presentationTimeUs - this.first);
        putShint(order, (int)millis);
        order.put((byte)(0xFFL & millis >> 24));
        putShint(order, 0);
        byte b;
        if ((0x1 & mediaCodec$BufferInfo.flags) != 0x0) {
            b = 23;
        }
        else {
            b = 39;
        }
        order.put(b);
        order.put((byte)1);
        putShint(order, 0);
        order.flip();
        byteBuffer.position(mediaCodec$BufferInfo.offset);
        byteBuffer.limit(mediaCodec$BufferInfo.offset + mediaCodec$BufferInfo.size);
        final ByteBuffer put = ByteBufferList.obtain(byteBuffer.remaining()).put(byteBuffer);
        put.flip();
        put.order(ByteOrder.BIG_ENDIAN);
        put.putInt(-4 + mediaCodec$BufferInfo.size);
        put.position(0);
        final ByteBuffer order2 = ByteBufferList.obtain(1024).order(ByteOrder.BIG_ENDIAN);
        order2.putInt(16 + mediaCodec$BufferInfo.size);
        order2.flip();
        this.sink.write(new ByteBufferList(new ByteBuffer[] { order, put, order2 }));
    }
    
    public void addVideoHeader(final MediaFormat mediaFormat) {
        final ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-0");
        final byte[] array = new byte[byteBuffer.remaining()];
        byteBuffer.duplicate().get(array);
        final ByteBuffer byteBuffer2 = mediaFormat.getByteBuffer("csd-1");
        final byte[] array2 = new byte[byteBuffer2.remaining()];
        byteBuffer2.duplicate().get(array2);
        final ByteBuffer order = ByteBufferList.obtain(1024).order(ByteOrder.BIG_ENDIAN);
        final int n = 16 + (-4 + (-4 + array.length + array2.length));
        order.put((byte)9);
        putShint(order, n);
        putShint(order, 0);
        order.put((byte)0);
        putShint(order, 0);
        order.put((byte)23);
        order.put((byte)0);
        putShint(order, 0);
        order.put((byte)1);
        order.put(array[5]);
        order.put(array[6]);
        order.put(array[7]);
        order.put((byte)(-1));
        order.put((byte)(-31));
        order.putShort((short)(-4 + array.length));
        order.put(array, 4, -4 + array.length);
        order.put((byte)1);
        order.putShort((short)(-4 + array2.length));
        order.put(array2, 4, -4 + array2.length);
        assert order.position() == n + 11;
        order.putInt(n + 11);
        order.flip();
        this.sink.write(new ByteBufferList(new ByteBuffer[] { order }));
    }
}
