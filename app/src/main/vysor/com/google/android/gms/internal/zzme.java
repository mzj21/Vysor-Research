// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.net.URISyntaxException;
import com.google.android.gms.common.internal.zzab;
import java.net.URI;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import android.webkit.WebViewClient;

@zziy
public class zzme extends WebViewClient
{
    private final zzlt zzbkr;
    private final zzii zzccz;
    private final String zzcxd;
    private boolean zzcxe;
    
    public zzme(final zzii zzccz, final zzlt zzbkr, final String s) {
        this.zzcxd = this.zzdn(s);
        this.zzcxe = false;
        this.zzbkr = zzbkr;
        this.zzccz = zzccz;
    }
    
    private String zzdn(String substring) {
        if (!TextUtils.isEmpty((CharSequence)substring)) {
            try {
                if (substring.endsWith("/")) {
                    substring = substring.substring(0, -1 + substring.length());
                }
            }
            catch (IndexOutOfBoundsException ex) {
                zzb.e(ex.getMessage());
            }
        }
        return substring;
    }
    
    public void onLoadResource(final WebView webView, final String s) {
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "JavascriptAdWebViewClient::onLoadResource: ".concat(value);
        }
        else {
            concat = new String("JavascriptAdWebViewClient::onLoadResource: ");
        }
        zzb.zzdd(concat);
        if (!this.zzdm(s)) {
            this.zzbkr.zzvr().onLoadResource(this.zzbkr.getWebView(), s);
        }
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "JavascriptAdWebViewClient::onPageFinished: ".concat(value);
        }
        else {
            concat = new String("JavascriptAdWebViewClient::onPageFinished: ");
        }
        zzb.zzdd(concat);
        if (!this.zzcxe) {
            this.zzccz.zzqx();
            this.zzcxe = true;
        }
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "JavascriptAdWebViewClient::shouldOverrideUrlLoading: ".concat(value);
        }
        else {
            concat = new String("JavascriptAdWebViewClient::shouldOverrideUrlLoading: ");
        }
        zzb.zzdd(concat);
        boolean shouldOverrideUrlLoading;
        if (this.zzdm(s)) {
            zzb.zzdd("shouldOverrideUrlLoading: received passback url");
            shouldOverrideUrlLoading = true;
        }
        else {
            shouldOverrideUrlLoading = this.zzbkr.zzvr().shouldOverrideUrlLoading(this.zzbkr.getWebView(), s);
        }
        return shouldOverrideUrlLoading;
    }
    
    protected boolean zzdm(final String s) {
        final String zzdn = this.zzdn(s);
        final boolean empty = TextUtils.isEmpty((CharSequence)zzdn);
        boolean b = false;
        if (!empty) {
            try {
                final URI uri = new URI(zzdn);
                final boolean equals = "passback".equals(uri.getScheme());
                b = false;
                if (equals) {
                    zzb.zzdd("Passback received");
                    this.zzccz.zzqy();
                    b = true;
                }
                else {
                    final boolean empty2 = TextUtils.isEmpty((CharSequence)this.zzcxd);
                    b = false;
                    if (!empty2) {
                        final URI uri2 = new URI(this.zzcxd);
                        final String host = uri2.getHost();
                        final String host2 = uri.getHost();
                        final String path = uri2.getPath();
                        final String path2 = uri.getPath();
                        final boolean equal = zzab.equal(host, host2);
                        b = false;
                        if (equal) {
                            final boolean equal2 = zzab.equal(path, path2);
                            b = false;
                            if (equal2) {
                                zzb.zzdd("Passback received");
                                this.zzccz.zzqy();
                                b = true;
                            }
                        }
                    }
                }
            }
            catch (URISyntaxException ex) {
                zzb.e(ex.getMessage());
            }
        }
        return b;
    }
}
