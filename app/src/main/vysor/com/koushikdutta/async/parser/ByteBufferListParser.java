// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.parser;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.future.SimpleFuture;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.DataEmitter;
import java.lang.reflect.Type;
import com.koushikdutta.async.ByteBufferList;

public class ByteBufferListParser implements AsyncParser<ByteBufferList>
{
    @Override
    public Type getType() {
        return ByteBufferList.class;
    }
    
    @Override
    public Future<ByteBufferList> parse(final DataEmitter dataEmitter) {
        final ByteBufferList list = new ByteBufferList();
        final SimpleFuture<ByteBufferList> simpleFuture = new SimpleFuture<ByteBufferList>() {
            @Override
            protected void cancelCleanup() {
                dataEmitter.close();
            }
        };
        dataEmitter.setDataCallback(new DataCallback() {
            @Override
            public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
                list.get(list);
            }
        });
        dataEmitter.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(final Exception complete) {
                if (complete != null) {
                    simpleFuture.setComplete(complete);
                }
                else {
                    try {
                        simpleFuture.setComplete(list);
                    }
                    catch (Exception complete2) {
                        simpleFuture.setComplete(complete2);
                    }
                }
            }
        });
        return simpleFuture;
    }
    
    @Override
    public void write(final DataSink dataSink, final ByteBufferList list, final CompletedCallback completedCallback) {
        Util.writeAll(dataSink, list, completedCallback);
    }
}
