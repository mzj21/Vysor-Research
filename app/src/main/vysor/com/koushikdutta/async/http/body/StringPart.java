// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.koushikdutta.async.http.NameValuePair;
import java.util.List;

public class StringPart extends StreamPart
{
    String value;
    
    public StringPart(final String s, final String value) {
        super(s, value.getBytes().length, null);
        this.value = value;
    }
    
    @Override
    protected InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.value.getBytes());
    }
}
