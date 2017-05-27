// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.parser.DocumentParser;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataEmitter;
import javax.xml.transform.Transformer;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.Writer;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.koushikdutta.async.util.Charsets;
import javax.xml.transform.TransformerFactory;
import org.w3c.dom.Node;
import javax.xml.transform.dom.DOMSource;
import java.io.ByteArrayOutputStream;
import org.w3c.dom.Document;

public class DocumentBody implements AsyncHttpRequestBody<Document>
{
    public static final String CONTENT_TYPE = "application/xml";
    ByteArrayOutputStream bout;
    Document document;
    
    public DocumentBody() {
        this(null);
    }
    
    public DocumentBody(final Document document) {
        this.document = document;
    }
    
    private void prepare() {
        if (this.bout == null) {
            try {
                final DOMSource domSource = new DOMSource(this.document);
                final Transformer transformer = TransformerFactory.newInstance().newTransformer();
                this.bout = new ByteArrayOutputStream();
                final OutputStreamWriter writer = new OutputStreamWriter(this.bout, Charsets.UTF_8);
                transformer.transform(domSource, new StreamResult(writer));
                writer.flush();
            }
            catch (Exception ex) {}
        }
    }
    
    @Override
    public Document get() {
        return this.document;
    }
    
    @Override
    public String getContentType() {
        return "application/xml";
    }
    
    @Override
    public int length() {
        this.prepare();
        return this.bout.size();
    }
    
    @Override
    public void parse(final DataEmitter dataEmitter, final CompletedCallback completedCallback) {
        new DocumentParser().parse(dataEmitter).setCallback(new FutureCallback<Document>() {
            @Override
            public void onCompleted(final Exception ex, final Document document) {
                DocumentBody.this.document = document;
                completedCallback.onCompleted(ex);
            }
        });
    }
    
    @Override
    public boolean readFullyOnRequest() {
        return true;
    }
    
    @Override
    public void write(final AsyncHttpRequest asyncHttpRequest, final DataSink dataSink, final CompletedCallback completedCallback) {
        this.prepare();
        Util.writeAll(dataSink, this.bout.toByteArray(), completedCallback);
    }
}
