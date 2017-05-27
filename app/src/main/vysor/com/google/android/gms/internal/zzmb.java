// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.annotation.TargetApi;

@zziy
@TargetApi(11)
public class zzmb extends zzmd
{
    public zzmb(final zzlt zzlt, final boolean b) {
        super(zzlt, b);
    }
    
    @Override
    public WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        return this.zza(webView, s, null);
    }
}
