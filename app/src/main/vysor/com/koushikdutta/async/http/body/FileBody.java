// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import java.io.File;

public class FileBody implements AsyncHttpRequestBody<File>
{
    public static final String CONTENT_TYPE = "application/binary";
    String contentType;
    File file;
    
    public FileBody(final File file) {
        this.contentType = "application/binary";
        this.file = file;
    }
    
    public FileBody(final File file, final String contentType) {
        this.contentType = "application/binary";
        this.file = file;
        this.contentType = contentType;
    }
    
    @Override
    public File get() {
        return this.file;
    }
    
    @Override
    public String getContentType() {
        return this.contentType;
    }
    
    @Override
    public int length() {
        return (int)this.file.length();
    }
    
    @Override
    public void parse(final DataEmitter dataEmitter, final CompletedCallback completedCallback) {
        throw new AssertionError((Object)"not implemented");
    }
    
    @Override
    public boolean readFullyOnRequest() {
        throw new AssertionError((Object)"not implemented");
    }
    
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    
    @Override
    public void write(final AsyncHttpRequest asyncHttpRequest, final DataSink dataSink, final CompletedCallback completedCallback) {
        Util.pump(this.file, dataSink, completedCallback);
    }
}
