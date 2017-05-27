// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.Map;
import android.webkit.WebResourceResponse;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.annotation.TargetApi;

@zziy
@TargetApi(21)
public class zzmf extends zzmd
{
    public zzmf(final zzlt zzlt, final boolean b) {
        super(zzlt, b);
    }
    
    @Nullable
    public WebResourceResponse shouldInterceptRequest(final WebView webView, final WebResourceRequest webResourceRequest) {
        WebResourceResponse zza;
        if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
            zza = null;
        }
        else {
            zza = this.zza(webView, webResourceRequest.getUrl().toString(), webResourceRequest.getRequestHeaders());
        }
        return zza;
    }
}
