// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaFormat;
import android.media.MediaCodec;
import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.virtualdisplay.EncoderDevice;

public class FlashDevice extends EncoderDevice
{
    private static final String FLASH_DEVICE_NAME = "flash";
    FlashPackager packager;
    
    public FlashDevice(final int n, final int n2, final BufferedDataSink bufferedDataSink) {
        super("Flash", n, n2);
        this.packager = new FlashPackager(bufferedDataSink, n, n2, 30);
    }
    
    @Override
    protected EncoderRunnable onSurfaceCreated(final MediaCodec mediaCodec) {
        return new Streamer(mediaCodec);
    }
    
    class Streamer extends EncoderRunnable
    {
        MediaFormat videoFormat;
        
        Streamer(final MediaCodec mediaCodec) {
            super(mediaCodec);
        }
        
        @Override
        protected void encode() throws Exception {
            int i = 0;
            ByteBuffer[] array = FlashDevice.this.venc.getOutputBuffers();
            while (i == 0) {
                final MediaCodec$BufferInfo mediaCodec$BufferInfo = new MediaCodec$BufferInfo();
                final int dequeueOutputBuffer = FlashDevice.this.venc.dequeueOutputBuffer(mediaCodec$BufferInfo, TimeUnit.SECONDS.toMicros(1L) / 30L);
                if (dequeueOutputBuffer >= 0) {
                    if (array == null) {
                        array = FlashDevice.this.venc.getOutputBuffers();
                    }
                    final ByteBuffer byteBuffer = array[dequeueOutputBuffer];
                    FlashDevice.this.packager.addVideoFrame(byteBuffer, mediaCodec$BufferInfo);
                    byteBuffer.clear();
                    FlashDevice.this.venc.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((0x4 & mediaCodec$BufferInfo.flags) != 0x0) {
                        i = 1;
                    }
                    else {
                        i = 0;
                    }
                }
                else if (dequeueOutputBuffer == -3) {
                    array = null;
                }
                else {
                    if (dequeueOutputBuffer != -2) {
                        continue;
                    }
                    this.videoFormat = FlashDevice.this.venc.getOutputFormat();
                    FlashDevice.this.packager.addVideoHeader(this.videoFormat);
                }
            }
        }
    }
}
