// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import android.net.Uri;

public class AsyncHttpPut extends AsyncHttpRequest
{
    public static final String METHOD = "PUT";
    
    public AsyncHttpPut(final Uri uri) {
        super(uri, "PUT");
    }
    
    public AsyncHttpPut(final String s) {
        this(Uri.parse(s));
    }
}
