// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Collections;
import java.util.HashSet;
import android.webkit.WebView;
import java.util.Set;
import android.content.Context;

@zziy
public class zzhv implements zzht
{
    private final Context mContext;
    final Set<WebView> zzcba;
    
    public zzhv(final Context mContext) {
        this.zzcba = Collections.synchronizedSet(new HashSet<WebView>());
        this.mContext = mContext;
    }
    
    @Override
    public void zza(final String s, final String s2, final String s3) {
        zzb.zzdd("Fetching assets for the given html");
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final WebView zzqj = zzhv.this.zzqj();
                zzqj.setWebViewClient((WebViewClient)new WebViewClient() {
                    public void onPageFinished(final WebView webView, final String s) {
                        zzb.zzdd("Loading assets have finished");
                        zzhv.this.zzcba.remove(zzqj);
                    }
                    
                    public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
                        zzb.zzdf("Loading assets have failed.");
                        zzhv.this.zzcba.remove(zzqj);
                    }
                });
                zzhv.this.zzcba.add(zzqj);
                zzqj.loadDataWithBaseURL(s2, s3, "text/html", "UTF-8", (String)null);
                zzb.zzdd("Fetching assets finished.");
            }
        });
    }
    
    public WebView zzqj() {
        final WebView webView = new WebView(this.mContext);
        webView.getSettings().setJavaScriptEnabled(true);
        return webView;
    }
}
