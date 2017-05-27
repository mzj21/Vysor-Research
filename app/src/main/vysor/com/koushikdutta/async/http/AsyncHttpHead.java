// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import android.net.Uri;

public class AsyncHttpHead extends AsyncHttpRequest
{
    public static final String METHOD = "HEAD";
    
    public AsyncHttpHead(final Uri uri) {
        super(uri, "HEAD");
    }
}
