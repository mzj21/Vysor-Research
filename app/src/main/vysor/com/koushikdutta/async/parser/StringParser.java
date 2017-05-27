// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.parser;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.future.TransformFuture;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.DataEmitter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

public class StringParser implements AsyncParser<String>
{
    Charset forcedCharset;
    
    public StringParser() {
    }
    
    public StringParser(final Charset forcedCharset) {
        this.forcedCharset = forcedCharset;
    }
    
    @Override
    public Type getType() {
        return String.class;
    }
    
    @Override
    public Future<String> parse(final DataEmitter dataEmitter) {
        return new ByteBufferListParser().parse(dataEmitter).then((Future<String>)new TransformFuture<String, ByteBufferList>() {
            final /* synthetic */ String val$charset = dataEmitter.charset();
            
            @Override
            protected void transform(final ByteBufferList list) throws Exception {
                Charset charset = StringParser.this.forcedCharset;
                if (charset == null && this.val$charset != null) {
                    charset = Charset.forName(this.val$charset);
                }
                this.setComplete((T)list.readString(charset));
            }
        });
    }
    
    @Override
    public void write(final DataSink dataSink, final String s, final CompletedCallback completedCallback) {
        new ByteBufferListParser().write(dataSink, new ByteBufferList(s.getBytes()), completedCallback);
    }
}
