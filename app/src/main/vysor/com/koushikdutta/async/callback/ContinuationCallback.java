// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.callback;

import com.koushikdutta.async.future.Continuation;

public interface ContinuationCallback
{
    void onContinue(final Continuation p0, final CompletedCallback p1) throws Exception;
}
