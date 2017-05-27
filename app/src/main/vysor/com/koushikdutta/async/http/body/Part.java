// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataSink;
import java.io.File;
import java.util.Iterator;
import java.util.Locale;
import com.koushikdutta.async.http.NameValuePair;
import java.util.List;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.http.Multimap;

public class Part
{
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    private long length;
    Multimap mContentDisposition;
    Headers mHeaders;
    
    public Part(final Headers mHeaders) {
        this.length = -1L;
        this.mHeaders = mHeaders;
        this.mContentDisposition = Multimap.parseSemicolonDelimited(this.mHeaders.get("Content-Disposition"));
    }
    
    public Part(final String s, final long length, final List<NameValuePair> list) {
        this.length = -1L;
        this.length = length;
        this.mHeaders = new Headers();
        final StringBuilder sb = new StringBuilder(String.format(Locale.ENGLISH, "form-data; name=\"%s\"", s));
        if (list != null) {
            for (final NameValuePair nameValuePair : list) {
                sb.append(String.format(Locale.ENGLISH, "; %s=\"%s\"", nameValuePair.getName(), nameValuePair.getValue()));
            }
        }
        this.mHeaders.set("Content-Disposition", sb.toString());
        this.mContentDisposition = Multimap.parseSemicolonDelimited(this.mHeaders.get("Content-Disposition"));
    }
    
    public String getContentType() {
        return this.mHeaders.get("Content-Type");
    }
    
    public String getFilename() {
        final String string = this.mContentDisposition.getString("filename");
        String name;
        if (string == null) {
            name = null;
        }
        else {
            name = new File(string).getName();
        }
        return name;
    }
    
    public String getName() {
        return this.mContentDisposition.getString("name");
    }
    
    public Headers getRawHeaders() {
        return this.mHeaders;
    }
    
    public boolean isFile() {
        return this.mContentDisposition.containsKey("filename");
    }
    
    public long length() {
        return this.length;
    }
    
    public void setContentType(final String s) {
        this.mHeaders.set("Content-Type", s);
    }
    
    public void write(final DataSink dataSink, final CompletedCallback completedCallback) {
        assert false;
    }
}
