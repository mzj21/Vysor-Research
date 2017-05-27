package com.koushikdutta.virtualdisplay;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;

import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.http.WebSocket;
import com.mzj.vysor.ClientConfig;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

public class StdOutDevice extends EncoderDevice {
    int bitrate;
    ByteBuffer codecPacket;
    OutputBufferCallback outputBufferCallback;
    MediaFormat outputFormat;
    WebSocket mWebSocket;
    BufferedDataSink sink;

    public StdOutDevice(WebSocket webSocket) {
        super("stdout");
        bitrate = 500000;
        mWebSocket = webSocket;
    }

    public StdOutDevice(BufferedDataSink sink) {
        super("stdout");
        bitrate = 500000;
        this.sink = sink;
    }

    @Override
    public int getBitrate(final int n) {
        return bitrate;
    }

    public ByteBuffer getCodecPacket() {
        return codecPacket.duplicate();
    }

    public MediaFormat getOutputFormat() {
        return outputFormat;
    }

    @Override
    protected EncoderRunnable onSurfaceCreated(final MediaCodec mediaCodec) {
        return new Writer(mediaCodec);
    }

    public void setBitrate(final int bitrate) {
        System.out.print("Bitrate: " + bitrate);
        if (mMediaCodec != null) {
            this.bitrate = bitrate;
            final Bundle parameters = new Bundle();
            parameters.putInt("video-bitrate", bitrate);
            mMediaCodec.setParameters(parameters);
        }
    }

    class Writer extends EncoderRunnable {
        public Writer(final MediaCodec mediaCodec) {
            super(mediaCodec);
        }

        @Override
        protected void encode() throws Exception {
            System.out.print("Writer started." + "\n");
            ByteBuffer[] outputBuffers = null;
            int i = 0;
            while (i == 0) {
                final MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                final int dequeueOutputBuffer = mEncoder.dequeueOutputBuffer(bufferInfo, -1L);
                if (dequeueOutputBuffer >= 0) {
                    if (outputBuffers == null) {
                        outputBuffers = mEncoder.getOutputBuffers();
                    }
                    final ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                    if ((0x2 & bufferInfo.flags) != 0x0) {
                        byteBuffer.position(bufferInfo.offset);
                        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                        codecPacket = ByteBuffer.allocate(bufferInfo.size);
                        codecPacket.put(byteBuffer);
                        codecPacket.flip();
                    }
                    ByteBuffer order = ByteBufferList.obtain(12 + bufferInfo.size).order(ByteOrder.LITTLE_ENDIAN);
                    order.putInt(-4 + (12 + bufferInfo.size));
                    order.putInt((int) TimeUnit.MICROSECONDS.toMillis(bufferInfo.presentationTimeUs));
                    int n2;
                    if ((0x1 & bufferInfo.flags) != 0x0) {
                        n2 = 1;
                    } else {
                        n2 = 0;
                    }
                    order.putInt(n2);
                    byteBuffer.position(bufferInfo.offset);
                    byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                    order.put(byteBuffer);
                    order.flip();
                    byteBuffer.clear();
                    byte[] byteBuffers = toBytes(order);
                    if (sink != null && sink.isOpen()) {
                        sink.write(new ByteBufferList(byteBuffers));
                    }
                    if (mWebSocket != null && mWebSocket.isOpen()) {
                        mWebSocket.send(byteBuffers);
                    }
                    if (outputBufferCallback != null) {
                        outputBufferCallback.onOutputBuffer(byteBuffer, bufferInfo);
                    }
                    mEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((0x4 & bufferInfo.flags) != 0x0) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                } else {
                    if (dequeueOutputBuffer != -2) {
                        continue;
                    }
                    System.out.print("MediaCodec.INFO_OUTPUT_FORMAT_CHANGED" + "\n");
                    outputFormat = mEncoder.getOutputFormat();
                    System.out.print("output mWidth: " + outputFormat.getInteger("mWidth") + "\n");
                    System.out.print("output mHeight: " + outputFormat.getInteger("mHeight") + "\n");
                }
            }
            if (sink != null && sink.isOpen()) {
                sink.end();
            }
        }

        public byte[] toBytes(ByteBuffer frameData) {
            int frameDataLength = frameData.remaining();
            byte[] frameDataByte = new byte[frameDataLength];
            frameData.get(frameDataByte, 0, frameDataLength);
            return frameDataByte;
        }
    }

    public static StdOutDevice current;

    public static StdOutDevice genStdOutDevice(WebSocket webSocket) {
        if (current != null) {
            current.stop();
        }
        current = null;
        current = new StdOutDevice(webSocket);
        if (ClientConfig.resolution != 0.0) {
            current.setUseEncodingConstraints(false);
        }
        current.registerVirtualDisplay(new SurfaceControlVirtualDisplayFactory(), 0);
        return current;
    }

    public static StdOutDevice genStdOutDevice(BufferedDataSink sink) {
        if (current != null) {
            current.stop();
        }
        current = null;
        current = new StdOutDevice(sink);
        if (ClientConfig.resolution != 0.0) {
            current.setUseEncodingConstraints(false);
        }
        current.registerVirtualDisplay(new SurfaceControlVirtualDisplayFactory(), 0);
        return current;
    }
}
