package com.koushikdutta.virtualdisplay;

import android.media.MediaCodec;

import java.nio.ByteBuffer;

public interface OutputBufferCallback {
    void onOutputBuffer(final ByteBuffer p0, final MediaCodec.BufferInfo p1);
}
