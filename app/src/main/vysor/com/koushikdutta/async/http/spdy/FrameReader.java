// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

import java.util.List;
import com.koushikdutta.async.ByteBufferList;

interface FrameReader
{
    public interface Handler
    {
        void ackSettings();
        
        void alternateService(final int p0, final String p1, final ByteString p2, final String p3, final int p4, final long p5);
        
        void data(final boolean p0, final int p1, final ByteBufferList p2);
        
        void error(final Exception p0);
        
        void goAway(final int p0, final ErrorCode p1, final ByteString p2);
        
        void headers(final boolean p0, final boolean p1, final int p2, final int p3, final List<Header> p4, final HeadersMode p5);
        
        void ping(final boolean p0, final int p1, final int p2);
        
        void priority(final int p0, final int p1, final int p2, final boolean p3);
        
        void pushPromise(final int p0, final int p1, final List<Header> p2);
        
        void rstStream(final int p0, final ErrorCode p1);
        
        void settings(final boolean p0, final Settings p1);
        
        void windowUpdate(final int p0, final long p1);
    }
}
