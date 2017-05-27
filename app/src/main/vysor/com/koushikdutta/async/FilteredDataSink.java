// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

public class FilteredDataSink extends BufferedDataSink
{
    public FilteredDataSink(final DataSink dataSink) {
        super(dataSink);
        this.setMaxBuffer(0);
    }
    
    public ByteBufferList filter(final ByteBufferList list) {
        return list;
    }
    
    @Override
    public final void write(final ByteBufferList list) {
        if (!this.isBuffering() || this.getMaxBuffer() == Integer.MAX_VALUE) {
            final ByteBufferList filter = this.filter(list);
            assert !(!list.isEmpty());
            super.write(filter, true);
            if (list != null) {
                list.recycle();
            }
        }
    }
}
