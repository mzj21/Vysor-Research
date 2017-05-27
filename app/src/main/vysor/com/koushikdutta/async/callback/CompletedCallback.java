// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.callback;

public interface CompletedCallback
{
    void onCompleted(final Exception p0);
    
    public static class NullCompletedCallback implements CompletedCallback
    {
        @Override
        public void onCompleted(final Exception ex) {
        }
    }
}
