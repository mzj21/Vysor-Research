// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.filter;

import java.nio.ByteBuffer;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.ByteBufferList;
import java.util.zip.Inflater;
import com.koushikdutta.async.FilteredDataEmitter;

public class InflaterInputFilter extends FilteredDataEmitter
{
    private Inflater mInflater;
    ByteBufferList transformed;
    
    public InflaterInputFilter() {
        this(new Inflater());
    }
    
    public InflaterInputFilter(final Inflater mInflater) {
        this.transformed = new ByteBufferList();
        this.mInflater = mInflater;
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        ByteBuffer byteBuffer = null;
        Label_0197: {
            while (true) {
                while (true) {
                    ByteBuffer remove = null;
                    Label_0189: {
                        while (true) {
                            Label_0169: {
                                Label_0157: {
                                    try {
                                        byteBuffer = ByteBufferList.obtain(2 * list.remaining());
                                        if (list.size() <= 0) {
                                            break Label_0197;
                                        }
                                        remove = list.remove();
                                        if (!remove.hasRemaining()) {
                                            break Label_0189;
                                        }
                                        final int remaining = remove.remaining();
                                        this.mInflater.setInput(remove.array(), remove.arrayOffset() + remove.position(), remove.remaining());
                                        byteBuffer.position(this.mInflater.inflate(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) + byteBuffer.position());
                                        if (byteBuffer.hasRemaining()) {
                                            break Label_0169;
                                        }
                                        byteBuffer.flip();
                                        this.transformed.add(byteBuffer);
                                        assert remaining != 0;
                                        break Label_0157;
                                    }
                                    catch (Exception ex) {
                                        this.report(ex);
                                    }
                                    break;
                                }
                                byteBuffer = ByteBufferList.obtain(2 * byteBuffer.capacity());
                            }
                            if (!this.mInflater.needsInput() && !this.mInflater.finished()) {
                                continue;
                            }
                            break;
                        }
                    }
                    ByteBufferList.reclaim(remove);
                    continue;
                }
            }
            return;
        }
        byteBuffer.flip();
        this.transformed.add(byteBuffer);
        Util.emitAllData(this, this.transformed);
    }
    
    @Override
    protected void report(Exception ex) {
        this.mInflater.end();
        if (ex != null && this.mInflater.getRemaining() > 0) {
            ex = new DataRemainingException("data still remaining in inflater", ex);
        }
        super.report(ex);
    }
}
