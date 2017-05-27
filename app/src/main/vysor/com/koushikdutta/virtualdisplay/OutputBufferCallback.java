// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.virtualdisplay;

import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;

public interface OutputBufferCallback
{
    void onOutputBuffer(final ByteBuffer p0, final MediaCodec$BufferInfo p1);
}
