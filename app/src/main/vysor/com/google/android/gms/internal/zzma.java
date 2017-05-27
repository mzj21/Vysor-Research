// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.WindowManager$BadTokenException;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.webkit.GeolocationPermissions$Callback;
import android.webkit.WebStorage$QuotaUpdater;
import android.webkit.WebViewClient;
import android.webkit.WebView$WebViewTransport;
import android.os.Message;
import android.webkit.ConsoleMessage;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.webkit.JsResult;
import android.app.AlertDialog$Builder;
import android.content.Context;
import android.webkit.WebView;
import android.annotation.TargetApi;
import android.webkit.WebChromeClient;

@zziy
@TargetApi(11)
public class zzma extends WebChromeClient
{
    private final zzlt zzbkr;
    
    public zzma(final zzlt zzbkr) {
        this.zzbkr = zzbkr;
    }
    
    private final Context zza(final WebView webView) {
        Object o;
        if (!(webView instanceof zzlt)) {
            o = webView.getContext();
        }
        else {
            final zzlt zzlt = (zzlt)webView;
            o = zzlt.zzvn();
            if (o == null) {
                o = zzlt.getContext();
            }
        }
        return (Context)o;
    }
    
    private static void zza(final AlertDialog$Builder alertDialog$Builder, final String message, final JsResult jsResult) {
        alertDialog$Builder.setMessage((CharSequence)message).setPositiveButton(17039370, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                jsResult.confirm();
            }
        }).setNegativeButton(17039360, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                jsResult.cancel();
            }
        }).setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                jsResult.cancel();
            }
        }).create().show();
    }
    
    private static void zza(final Context context, final AlertDialog$Builder alertDialog$Builder, final String text, final String text2, final JsPromptResult jsPromptResult) {
        final LinearLayout view = new LinearLayout(context);
        view.setOrientation(1);
        final TextView textView = new TextView(context);
        textView.setText((CharSequence)text);
        final EditText editText = new EditText(context);
        editText.setText((CharSequence)text2);
        view.addView((View)textView);
        view.addView((View)editText);
        alertDialog$Builder.setView((View)view).setPositiveButton(17039370, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                jsPromptResult.confirm(editText.getText().toString());
            }
        }).setNegativeButton(17039360, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                jsPromptResult.cancel();
            }
        }).setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                jsPromptResult.cancel();
            }
        }).create().show();
    }
    
    private final boolean zzxf() {
        return zzu.zzfz().zza(this.zzbkr.getContext().getPackageManager(), this.zzbkr.getContext().getPackageName(), "android.permission.ACCESS_FINE_LOCATION") || zzu.zzfz().zza(this.zzbkr.getContext().getPackageManager(), this.zzbkr.getContext().getPackageName(), "android.permission.ACCESS_COARSE_LOCATION");
    }
    
    public final void onCloseWindow(final WebView webView) {
        if (!(webView instanceof zzlt)) {
            zzb.zzdf("Tried to close a WebView that wasn't an AdWebView.");
        }
        else {
            final zzd zzvp = ((zzlt)webView).zzvp();
            if (zzvp == null) {
                zzb.zzdf("Tried to close an AdWebView not associated with an overlay.");
            }
            else {
                zzvp.close();
            }
        }
    }
    
    public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
        final String value = String.valueOf(consoleMessage.message());
        final String value2 = String.valueOf(consoleMessage.sourceId());
        final String string = new StringBuilder(19 + String.valueOf(value).length() + String.valueOf(value2).length()).append("JS: ").append(value).append(" (").append(value2).append(":").append(consoleMessage.lineNumber()).append(")").toString();
        boolean b;
        if (string.contains("Application Cache")) {
            b = super.onConsoleMessage(consoleMessage);
        }
        else {
            switch (zzma$7.zzcxc[consoleMessage.messageLevel().ordinal()]) {
                default: {
                    zzb.zzde(string);
                    break;
                }
                case 1: {
                    zzb.e(string);
                    break;
                }
                case 2: {
                    zzb.zzdf(string);
                    break;
                }
                case 3:
                case 4: {
                    zzb.zzde(string);
                    break;
                }
                case 5: {
                    zzb.zzdd(string);
                    break;
                }
            }
            b = super.onConsoleMessage(consoleMessage);
        }
        return b;
    }
    
    public final boolean onCreateWindow(final WebView webView, final boolean b, final boolean b2, final Message message) {
        final WebView$WebViewTransport webView$WebViewTransport = (WebView$WebViewTransport)message.obj;
        final WebView webView2 = new WebView(webView.getContext());
        webView2.setWebViewClient((WebViewClient)this.zzbkr.zzvr());
        webView$WebViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }
    
    public final void onExceededDatabaseQuota(final String s, final String s2, long n, long min, final long n2, final WebStorage$QuotaUpdater webStorage$QuotaUpdater) {
        final long n3 = 5242880L - n2;
        if (n3 <= 0L) {
            webStorage$QuotaUpdater.updateQuota(n);
        }
        else {
            if (n == 0L) {
                if (min > n3 || min > 1048576L) {
                    min = 0L;
                }
            }
            else if (min == 0L) {
                min = Math.min(n + Math.min(131072L, n3), 1048576L);
            }
            else {
                if (min <= Math.min(1048576L - n, n3)) {
                    n += min;
                }
                min = n;
            }
            webStorage$QuotaUpdater.updateQuota(min);
        }
    }
    
    public final void onGeolocationPermissionsShowPrompt(final String s, final GeolocationPermissions$Callback geolocationPermissions$Callback) {
        if (geolocationPermissions$Callback != null) {
            geolocationPermissions$Callback.invoke(s, this.zzxf(), true);
        }
    }
    
    public final void onHideCustomView() {
        final zzd zzvp = this.zzbkr.zzvp();
        if (zzvp == null) {
            zzb.zzdf("Could not get ad overlay when hiding custom view.");
        }
        else {
            zzvp.zzos();
        }
    }
    
    public final boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.zza(this.zza(webView), s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsBeforeUnload(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.zza(this.zza(webView), s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsConfirm(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.zza(this.zza(webView), s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsPrompt(final WebView webView, final String s, final String s2, final String s3, final JsPromptResult jsPromptResult) {
        return this.zza(this.zza(webView), s, s2, s3, null, jsPromptResult, true);
    }
    
    public final void onReachedMaxAppCacheSize(final long n, final long n2, final WebStorage$QuotaUpdater webStorage$QuotaUpdater) {
        final long n3 = 5242880L - n2;
        final long n4 = 131072L + n;
        if (n3 < n4) {
            webStorage$QuotaUpdater.updateQuota(0L);
        }
        else {
            webStorage$QuotaUpdater.updateQuota(n4);
        }
    }
    
    public final void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        this.zza(view, -1, webChromeClient$CustomViewCallback);
    }
    
    protected final void zza(final View view, final int requestedOrientation, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        final zzd zzvp = this.zzbkr.zzvp();
        if (zzvp == null) {
            zzb.zzdf("Could not get ad overlay when showing custom view.");
            webChromeClient$CustomViewCallback.onCustomViewHidden();
        }
        else {
            zzvp.zza(view, webChromeClient$CustomViewCallback);
            zzvp.setRequestedOrientation(requestedOrientation);
        }
    }
    
    protected boolean zza(final Context context, final String title, final String s, final String s2, final JsResult jsResult, final JsPromptResult jsPromptResult, final boolean b) {
        try {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
            alertDialog$Builder.setTitle((CharSequence)title);
            if (b) {
                zza(context, alertDialog$Builder, s, s2, jsPromptResult);
            }
            else {
                zza(alertDialog$Builder, s, jsResult);
            }
        }
        catch (WindowManager$BadTokenException ex) {
            zzb.zzd("Fail to display Dialog.", (Throwable)ex);
        }
        return true;
    }
}
