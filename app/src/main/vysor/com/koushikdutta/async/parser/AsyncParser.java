// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.parser;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.DataEmitter;
import java.lang.reflect.Type;

public interface AsyncParser<T>
{
    Type getType();
    
    Future<T> parse(final DataEmitter p0);
    
    void write(final DataSink p0, final T p1, final CompletedCallback p2);
}
