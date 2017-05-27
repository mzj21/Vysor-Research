// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.http.Protocol;

interface Variant
{
    Protocol getProtocol();
    
    int maxFrameSize();
    
    FrameReader newReader(final DataEmitter p0, final FrameReader.Handler p1, final boolean p2);
    
    FrameWriter newWriter(final BufferedDataSink p0, final boolean p1);
}
