// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.parser;

import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.http.body.DocumentBody;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataSink;
import java.io.InputStream;
import com.koushikdutta.async.stream.ByteBufferListInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.future.TransformFuture;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.DataEmitter;
import java.lang.reflect.Type;
import org.w3c.dom.Document;

public class DocumentParser implements AsyncParser<Document>
{
    @Override
    public Type getType() {
        return Document.class;
    }
    
    @Override
    public Future<Document> parse(final DataEmitter dataEmitter) {
        return new ByteBufferListParser().parse(dataEmitter).then((Future<Document>)new TransformFuture<Document, ByteBufferList>() {
            @Override
            protected void transform(final ByteBufferList list) throws Exception {
                this.setComplete((T)DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteBufferListInputStream(list)));
            }
        });
    }
    
    @Override
    public void write(final DataSink dataSink, final Document document, final CompletedCallback completedCallback) {
        new DocumentBody(document).write(null, dataSink, completedCallback);
    }
}
