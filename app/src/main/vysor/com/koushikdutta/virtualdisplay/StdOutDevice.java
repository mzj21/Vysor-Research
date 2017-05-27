// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.virtualdisplay;

import java.util.concurrent.TimeUnit;
import java.nio.ByteOrder;
import com.koushikdutta.async.ByteBufferList;
import android.media.MediaCodec$BufferInfo;
import android.util.Log;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.media.MediaCodec;
import com.koushikdutta.async.BufferedDataSink;
import android.media.MediaFormat;
import java.nio.ByteBuffer;

public class StdOutDevice extends EncoderDevice
{
    int bitrate;
    ByteBuffer codecPacket;
    OutputBufferCallback outputBufferCallback;
    MediaFormat outputFormat;
    BufferedDataSink sink;
    
    public StdOutDevice(final int n, final int n2, final BufferedDataSink sink) {
        super("stdout", n, n2);
        this.bitrate = 500000;
        this.sink = sink;
    }
    
    @Override
    public int getBitrate(final int n) {
        return this.bitrate;
    }
    
    public ByteBuffer getCodecPacket() {
        return this.codecPacket.duplicate();
    }
    
    public MediaFormat getOutputFormat() {
        return this.outputFormat;
    }
    
    @Override
    protected EncoderRunnable onSurfaceCreated(final MediaCodec mediaCodec) {
        return new Writer(mediaCodec);
    }
    
    @TargetApi(19)
    public void requestSyncFrame() {
        final Bundle parameters = new Bundle();
        parameters.putInt("request-sync", 0);
        this.venc.setParameters(parameters);
    }
    
    @TargetApi(19)
    public void setBitrate(final int bitrate) {
        Log.i(this.LOGTAG, "Bitrate: " + bitrate);
        if (this.venc != null) {
            this.bitrate = bitrate;
            final Bundle parameters = new Bundle();
            parameters.putInt("video-bitrate", bitrate);
            this.venc.setParameters(parameters);
        }
    }
    
    public void setOutputBufferCallack(final OutputBufferCallback outputBufferCallback) {
        this.outputBufferCallback = outputBufferCallback;
    }
    
    class Writer extends EncoderRunnable
    {
        public Writer(final MediaCodec mediaCodec) {
            super(mediaCodec);
        }
        
        @Override
        protected void encode() throws Exception {
            Log.i(StdOutDevice.this.LOGTAG, "Writer started.");
            ByteBuffer[] outputBuffers = null;
            int i = 0;
            int n = 0;
            while (i == 0) {
                final MediaCodec$BufferInfo mediaCodec$BufferInfo = new MediaCodec$BufferInfo();
                final int dequeueOutputBuffer = this.venc.dequeueOutputBuffer(mediaCodec$BufferInfo, -1L);
                if (dequeueOutputBuffer >= 0) {
                    if (n == 0) {
                        n = 1;
                        Log.i(StdOutDevice.this.LOGTAG, "Got first buffer");
                    }
                    if (outputBuffers == null) {
                        outputBuffers = this.venc.getOutputBuffers();
                    }
                    final ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                    if ((0x2 & mediaCodec$BufferInfo.flags) != 0x0) {
                        byteBuffer.position(mediaCodec$BufferInfo.offset);
                        byteBuffer.limit(mediaCodec$BufferInfo.offset + mediaCodec$BufferInfo.size);
                        (StdOutDevice.this.codecPacket = ByteBuffer.allocate(mediaCodec$BufferInfo.size)).put(byteBuffer);
                        StdOutDevice.this.codecPacket.flip();
                    }
                    final ByteBuffer order = ByteBufferList.obtain(12 + mediaCodec$BufferInfo.size).order(ByteOrder.LITTLE_ENDIAN);
                    order.putInt(-4 + (12 + mediaCodec$BufferInfo.size));
                    order.putInt((int)TimeUnit.MICROSECONDS.toMillis(mediaCodec$BufferInfo.presentationTimeUs));
                    int n2;
                    if ((0x1 & mediaCodec$BufferInfo.flags) != 0x0) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    order.putInt(n2);
                    byteBuffer.position(mediaCodec$BufferInfo.offset);
                    byteBuffer.limit(mediaCodec$BufferInfo.offset + mediaCodec$BufferInfo.size);
                    order.put(byteBuffer);
                    order.flip();
                    byteBuffer.clear();
                    StdOutDevice.this.sink.write(new ByteBufferList(new ByteBuffer[] { order }));
                    if (StdOutDevice.this.outputBufferCallback != null) {
                        StdOutDevice.this.outputBufferCallback.onOutputBuffer(byteBuffer, mediaCodec$BufferInfo);
                    }
                    this.venc.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((0x4 & mediaCodec$BufferInfo.flags) != 0x0) {
                        i = 1;
                    }
                    else {
                        i = 0;
                    }
                }
                else if (dequeueOutputBuffer == -3) {
                    Log.i(StdOutDevice.this.LOGTAG, "MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED");
                    outputBuffers = null;
                }
                else {
                    if (dequeueOutputBuffer != -2) {
                        continue;
                    }
                    Log.i(StdOutDevice.this.LOGTAG, "MediaCodec.INFO_OUTPUT_FORMAT_CHANGED");
                    StdOutDevice.this.outputFormat = this.venc.getOutputFormat();
                    Log.i(StdOutDevice.this.LOGTAG, "output mWidth: " + StdOutDevice.this.outputFormat.getInteger("mWidth"));
                    Log.i(StdOutDevice.this.LOGTAG, "output mHeight: " + StdOutDevice.this.outputFormat.getInteger("mHeight"));
                }
            }
            StdOutDevice.this.sink.end();
            Log.i(StdOutDevice.this.LOGTAG, "Writer done");
        }
    }
}
