// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.ContinuationCallback;
import com.koushikdutta.async.future.Continuation;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import java.util.Iterator;
import java.util.UUID;
import java.io.File;
import java.util.ArrayList;
import com.koushikdutta.async.LineEmitter;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.server.BoundaryEmitter;

public class MultipartFormDataBody extends BoundaryEmitter implements AsyncHttpRequestBody<Multimap>
{
    public static final String CONTENT_TYPE = "multipart/form-data";
    String contentType;
    Headers formData;
    ByteBufferList last;
    String lastName;
    LineEmitter liner;
    MultipartCallback mCallback;
    private ArrayList<Part> mParts;
    int totalToWrite;
    int written;
    
    public MultipartFormDataBody() {
        this.contentType = "multipart/form-data";
    }
    
    public MultipartFormDataBody(final String[] array) {
        this.contentType = "multipart/form-data";
        for (int length = array.length, i = 0; i < length; ++i) {
            final String[] split = array[i].split("=");
            if (split.length == 2 && "boundary".equals(split[0])) {
                this.setBoundary(split[1]);
                return;
            }
        }
        this.report(new Exception("No boundary found for multipart/form-data"));
    }
    
    public void addFilePart(final String s, final File file) {
        this.addPart(new FilePart(s, file));
    }
    
    public void addPart(final Part part) {
        if (this.mParts == null) {
            this.mParts = new ArrayList<Part>();
        }
        this.mParts.add(part);
    }
    
    public void addStringPart(final String s, final String s2) {
        this.addPart(new StringPart(s, s2));
    }
    
    @Override
    public Multimap get() {
        return new Multimap(this.formData.getMultiMap());
    }
    
    @Override
    public String getContentType() {
        if (this.getBoundary() == null) {
            this.setBoundary("----------------------------" + UUID.randomUUID().toString().replace("-", ""));
        }
        return this.contentType + "; boundary=" + this.getBoundary();
    }
    
    public String getField(final String s) {
        String value;
        if (this.formData == null) {
            value = null;
        }
        else {
            value = this.formData.get(s);
        }
        return value;
    }
    
    public MultipartCallback getMultipartCallback() {
        return this.mCallback;
    }
    
    void handleLast() {
        if (this.last != null) {
            if (this.formData == null) {
                this.formData = new Headers();
            }
            this.formData.add(this.lastName, this.last.peekString());
            this.lastName = null;
            this.last = null;
        }
    }
    
    @Override
    public int length() {
        if (this.getBoundary() == null) {
            this.setBoundary("----------------------------" + UUID.randomUUID().toString().replace("-", ""));
        }
        int n = 0;
        for (final Part part : this.mParts) {
            final String prefixString = part.getRawHeaders().toPrefixString(this.getBoundaryStart());
            if (part.length() == -1L) {
                return -1;
            }
            n += (int)(part.length() + prefixString.getBytes().length + "\r\n".length());
        }
        final int totalToWrite = n + this.getBoundaryEnd().getBytes().length;
        this.totalToWrite = totalToWrite;
        return totalToWrite;
    }
    
    @Override
    protected void onBoundaryEnd() {
        super.onBoundaryEnd();
        this.handleLast();
    }
    
    @Override
    protected void onBoundaryStart() {
        (this.liner = new LineEmitter()).setLineCallback((LineEmitter.StringCallback)new LineEmitter.StringCallback() {
            final /* synthetic */ Headers val$headers = new Headers();
            
            @Override
            public void onStringAvailable(final String s) {
                if (!"\r".equals(s)) {
                    this.val$headers.addLine(s);
                }
                else {
                    MultipartFormDataBody.this.handleLast();
                    MultipartFormDataBody.this.liner = null;
                    MultipartFormDataBody.this.setDataCallback(null);
                    final Part part = new Part(this.val$headers);
                    if (MultipartFormDataBody.this.mCallback != null) {
                        MultipartFormDataBody.this.mCallback.onPart(part);
                    }
                    if (MultipartFormDataBody.this.getDataCallback() == null) {
                        if (part.isFile()) {
                            MultipartFormDataBody.this.setDataCallback(new DataCallback.NullDataCallback());
                        }
                        else {
                            MultipartFormDataBody.this.lastName = part.getName();
                            MultipartFormDataBody.this.last = new ByteBufferList();
                            MultipartFormDataBody.this.setDataCallback(new DataCallback() {
                                @Override
                                public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                                    list.get(MultipartFormDataBody.this.last);
                                }
                            });
                        }
                    }
                }
            }
        });
        this.setDataCallback(this.liner);
    }
    
    @Override
    public void parse(final DataEmitter dataEmitter, final CompletedCallback endCallback) {
        this.setDataEmitter(dataEmitter);
        this.setEndCallback(endCallback);
    }
    
    @Override
    public boolean readFullyOnRequest() {
        return false;
    }
    
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }
    
    public void setMultipartCallback(final MultipartCallback mCallback) {
        this.mCallback = mCallback;
    }
    
    @Override
    public void write(final AsyncHttpRequest asyncHttpRequest, final DataSink dataSink, final CompletedCallback completedCallback) {
        if (this.mParts != null) {
            final Continuation continuation = new Continuation(new CompletedCallback() {
                @Override
                public void onCompleted(final Exception ex) {
                    completedCallback.onCompleted(ex);
                }
            });
            for (final Part part : this.mParts) {
                continuation.add(new ContinuationCallback() {
                    @Override
                    public void onContinue(final Continuation continuation, final CompletedCallback completedCallback) throws Exception {
                        final byte[] bytes = part.getRawHeaders().toPrefixString(MultipartFormDataBody.this.getBoundaryStart()).getBytes();
                        Util.writeAll(dataSink, bytes, completedCallback);
                        final MultipartFormDataBody this$0 = MultipartFormDataBody.this;
                        this$0.written += bytes.length;
                    }
                }).add(new ContinuationCallback() {
                    @Override
                    public void onContinue(final Continuation continuation, final CompletedCallback completedCallback) throws Exception {
                        final long length = part.length();
                        if (length >= 0L) {
                            final MultipartFormDataBody this$0 = MultipartFormDataBody.this;
                            this$0.written += (int)length;
                        }
                        part.write(dataSink, completedCallback);
                    }
                }).add(new ContinuationCallback() {
                    @Override
                    public void onContinue(final Continuation continuation, final CompletedCallback completedCallback) throws Exception {
                        final byte[] bytes = "\r\n".getBytes();
                        Util.writeAll(dataSink, bytes, completedCallback);
                        final MultipartFormDataBody this$0 = MultipartFormDataBody.this;
                        this$0.written += bytes.length;
                    }
                });
            }
            continuation.add(new ContinuationCallback() {
                @Override
                public void onContinue(final Continuation continuation, final CompletedCallback completedCallback) throws Exception {
                    final byte[] bytes = MultipartFormDataBody.this.getBoundaryEnd().getBytes();
                    Util.writeAll(dataSink, bytes, completedCallback);
                    final MultipartFormDataBody this$0 = MultipartFormDataBody.this;
                    this$0.written += bytes.length;
                    assert MultipartFormDataBody.this.written == MultipartFormDataBody.this.totalToWrite;
                }
            });
            continuation.start();
        }
    }
    
    public interface MultipartCallback
    {
        void onPart(final Part p0);
    }
}
