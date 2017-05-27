// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import java.io.IOException;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import java.io.File;
import android.annotation.TargetApi;
import com.koushikdutta.virtualdisplay.OutputBufferCallback;

@TargetApi(18)
public class Mp4Writer implements OutputBufferCallback
{
    File file;
    long firstTimestamp;
    boolean hasGottenKeyFrame;
    MediaMuxer muxer;
    boolean stopped;
    int videoTrack;
    
    Mp4Writer(final File file, final MediaMuxer muxer, final MediaFormat mediaFormat) {
        this.muxer = muxer;
        this.file = file;
        this.videoTrack = muxer.addTrack(mediaFormat);
        muxer.start();
    }
    
    public static Mp4Writer create(final File file, final MediaFormat mediaFormat) throws IOException {
        return new Mp4Writer(file, new MediaMuxer(file.getAbsolutePath(), 0), mediaFormat);
    }
    
    public File getFile() {
        return this.file;
    }
    
    @Override
    public void onOutputBuffer(final ByteBuffer byteBuffer, final MediaCodec$BufferInfo mediaCodec$BufferInfo) {
        if (!this.stopped) {
            if ((0x2 & mediaCodec$BufferInfo.flags) == 0x0) {
                this.muxer.writeSampleData(this.videoTrack, byteBuffer, mediaCodec$BufferInfo);
            }
            else {
                if (!this.hasGottenKeyFrame && (0x1 & mediaCodec$BufferInfo.flags) == 0x1) {
                    this.firstTimestamp = mediaCodec$BufferInfo.presentationTimeUs;
                    this.hasGottenKeyFrame = true;
                }
                if (this.hasGottenKeyFrame) {
                    final MediaCodec$BufferInfo mediaCodec$BufferInfo2 = new MediaCodec$BufferInfo();
                    mediaCodec$BufferInfo2.set(mediaCodec$BufferInfo.offset, mediaCodec$BufferInfo.size, mediaCodec$BufferInfo.presentationTimeUs - this.firstTimestamp, mediaCodec$BufferInfo.flags);
                    this.muxer.writeSampleData(this.videoTrack, byteBuffer, mediaCodec$BufferInfo2);
                }
            }
        }
    }
    
    public void stop() {
        this.stopped = true;
        this.muxer.stop();
        this.muxer.release();
    }
}
