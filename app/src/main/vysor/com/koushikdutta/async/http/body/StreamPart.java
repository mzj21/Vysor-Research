// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataSink;
import java.io.IOException;
import java.io.InputStream;
import com.koushikdutta.async.http.NameValuePair;
import java.util.List;

public abstract class StreamPart extends Part
{
    public StreamPart(final String s, final long n, final List<NameValuePair> list) {
        super(s, n, list);
    }
    
    protected abstract InputStream getInputStream() throws IOException;
    
    @Override
    public void write(final DataSink dataSink, final CompletedCallback completedCallback) {
        try {
            Util.pump(this.getInputStream(), dataSink, completedCallback);
        }
        catch (Exception ex) {
            completedCallback.onCompleted(ex);
        }
    }
}
