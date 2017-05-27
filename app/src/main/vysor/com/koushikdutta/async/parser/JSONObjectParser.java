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
import org.json.JSONObject;

public class JSONObjectParser implements AsyncParser<JSONObject>
{
    @Override
    public Type getType() {
        return JSONObject.class;
    }
    
    @Override
    public Future<JSONObject> parse(final DataEmitter dataEmitter) {
        return new StringParser().parse(dataEmitter).then((Future<JSONObject>)new TransformFuture<JSONObject, String>() {
            @Override
            protected void transform(final String s) throws Exception {
                this.setComplete((T)new JSONObject(s));
            }
        });
    }
    
    @Override
    public void write(final DataSink dataSink, final JSONObject jsonObject, final CompletedCallback completedCallback) {
        new StringParser().write(dataSink, jsonObject.toString(), completedCallback);
    }
}
