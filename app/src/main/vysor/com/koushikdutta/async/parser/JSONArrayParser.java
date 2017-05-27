// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.parser;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.future.TransformFuture;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.DataEmitter;
import java.lang.reflect.Type;
import org.json.JSONArray;

public class JSONArrayParser implements AsyncParser<JSONArray>
{
    @Override
    public Type getType() {
        return JSONArray.class;
    }
    
    @Override
    public Future<JSONArray> parse(final DataEmitter dataEmitter) {
        return new StringParser().parse(dataEmitter).then((Future<JSONArray>)new TransformFuture<JSONArray, String>() {
            @Override
            protected void transform(final String s) throws Exception {
                this.setComplete((T)new JSONArray(s));
            }
        });
    }
    
    @Override
    public void write(final DataSink dataSink, final JSONArray jsonArray, final CompletedCallback completedCallback) {
        new StringParser().write(dataSink, jsonArray.toString(), completedCallback);
    }
}
