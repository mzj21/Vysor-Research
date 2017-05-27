// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http;

import android.net.Uri;

public class AsyncHttpPost extends AsyncHttpRequest
{
    public static final String METHOD = "POST";
    
    public AsyncHttpPost(final Uri uri) {
        super(uri, "POST");
    }
    
    public AsyncHttpPost(final String s) {
        this(Uri.parse(s));
    }
}
