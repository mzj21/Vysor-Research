// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.filter;

import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.FilteredDataEmitter;

public class ContentLengthFilter extends FilteredDataEmitter
{
    long contentLength;
    long totalRead;
    ByteBufferList transformed;
    
    public ContentLengthFilter(final long contentLength) {
        this.transformed = new ByteBufferList();
        this.contentLength = contentLength;
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        assert this.totalRead < this.contentLength;
        list.get(this.transformed, (int)Math.min(this.contentLength - this.totalRead, list.remaining()));
        final int remaining = this.transformed.remaining();
        super.onDataAvailable(dataEmitter, this.transformed);
        this.totalRead += remaining - this.transformed.remaining();
        this.transformed.get(list);
        if (this.totalRead == this.contentLength) {
            this.report(null);
        }
    }
    
    @Override
    protected void report(Exception ex) {
        if (ex == null && this.totalRead != this.contentLength) {
            ex = new PrematureDataEndException("End of data reached before content length was read: " + this.totalRead + "/" + this.contentLength + " Paused: " + this.isPaused());
        }
        super.report(ex);
    }
}
