// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.ads.internal.zzu;
import java.util.HashMap;
import android.content.Context;
import java.util.concurrent.TimeoutException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.io.File;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.WebResourceResponse;
import android.support.annotation.Nullable;
import java.util.Map;
import android.webkit.WebView;
import android.annotation.TargetApi;

@zziy
@TargetApi(11)
public class zzmd extends zzlu
{
    public zzmd(final zzlt zzlt, final boolean b) {
        super(zzlt, b);
    }
    
    protected WebResourceResponse zza(final WebView webView, final String s, @Nullable final Map<String, String> map) {
        WebResourceResponse webResourceResponse;
        if (!(webView instanceof zzlt)) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Tried to intercept request from a WebView that wasn't an AdWebView.");
            webResourceResponse = null;
        }
        else {
            final zzlt zzlt = (zzlt)webView;
            if (this.zzcvg != null) {
                this.zzcvg.zzb(s, map);
            }
            if (!"mraid.js".equalsIgnoreCase(new File(s).getName())) {
                webResourceResponse = super.shouldInterceptRequest(webView, s);
            }
            else {
                if (zzlt.zzvr() != null) {
                    zzlt.zzvr().zzov();
                }
                Label_0148: {
                    if (!zzlt.zzdt().zzaxj) {
                        break Label_0148;
                    }
                    String s2 = zzdi.zzbbz.get();
                    try {
                        webResourceResponse = this.zzf(zzlt.getContext(), zzlt.zzvu().zzcs, s2);
                        return webResourceResponse;
                        // iftrue(Label_0172:, !zzlt.zzvv())
                        while (true) {
                            s2 = zzdi.zzbby.get();
                            return this.zzf(zzlt.getContext(), zzlt.zzvu().zzcs, s2);
                            continue;
                        }
                        Label_0172: {
                            s2 = zzdi.zzbbx.get();
                        }
                        return this.zzf(zzlt.getContext(), zzlt.zzvu().zzcs, s2);
                    }
                    catch (InterruptedException ex) {}
                    catch (ExecutionException ex2) {
                        goto Label_0188;
                    }
                    catch (IOException ex2) {
                        goto Label_0188;
                    }
                    catch (TimeoutException ex2) {
                        goto Label_0188;
                    }
                }
            }
        }
        return webResourceResponse;
    }
    
    protected WebResourceResponse zzf(final Context context, final String s, final String s2) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("User-Agent", zzu.zzfz().zzg(context, s));
        hashMap.put("Cache-Control", "max-stale=3600");
        final String s3 = new zzky(context).zzd(s2, hashMap).get(60L, TimeUnit.SECONDS);
        WebResourceResponse webResourceResponse;
        if (s3 == null) {
            webResourceResponse = null;
        }
        else {
            webResourceResponse = new WebResourceResponse("application/javascript", "UTF-8", (InputStream)new ByteArrayInputStream(s3.getBytes("UTF-8")));
        }
        return webResourceResponse;
    }
}
