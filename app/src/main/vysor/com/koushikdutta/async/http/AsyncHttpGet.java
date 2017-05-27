// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import android.net.Uri;

public class AsyncHttpGet extends AsyncHttpRequest
{
    public static final String METHOD = "GET";
    
    public AsyncHttpGet(final Uri uri) {
        super(uri, "GET");
    }
    
    public AsyncHttpGet(final String s) {
        super(Uri.parse(s), "GET");
    }
}
