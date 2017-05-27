// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.filter;

import java.nio.ByteBuffer;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.FilteredDataSink;

public class ChunkedOutputFilter extends FilteredDataSink
{
    public ChunkedOutputFilter(final DataSink dataSink) {
        super(dataSink);
    }
    
    @Override
    public void end() {
        this.setMaxBuffer(Integer.MAX_VALUE);
        this.write(new ByteBufferList());
        this.setMaxBuffer(0);
    }
    
    @Override
    public ByteBufferList filter(final ByteBufferList list) {
        list.addFirst(ByteBuffer.wrap((Integer.toString(list.remaining(), 16) + "\r\n").getBytes()));
        list.add(ByteBuffer.wrap("\r\n".getBytes()));
        return list;
    }
}
