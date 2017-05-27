// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzd;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import android.view.KeyEvent;
import android.annotation.TargetApi;
import com.google.android.gms.ads.internal.cache.CacheEntryParcel;
import com.google.android.gms.ads.internal.cache.CacheOffering;
import android.webkit.WebResourceResponse;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import android.os.Bundle;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.safebrowsing.zzc;
import com.google.android.gms.ads.internal.overlay.zzp;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.overlay.zzg;
import java.util.List;
import java.util.HashMap;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.client.zza;
import android.webkit.WebViewClient;

@zziy
public class zzlu extends WebViewClient
{
    private static final String[] zzcus;
    private static final String[] zzcut;
    private final Object zzakd;
    private boolean zzatj;
    private com.google.android.gms.ads.internal.client.zza zzawb;
    protected zzlt zzbkr;
    private zzer zzbma;
    private zzez zzbnj;
    private com.google.android.gms.ads.internal.zze zzbnl;
    private zzhh zzbnm;
    private zzex zzbno;
    private zzhn zzbve;
    private zza zzcct;
    private final HashMap<String, List<zzev>> zzcuu;
    private zzg zzcuv;
    private zzb zzcuw;
    private zzc zzcux;
    private boolean zzcuy;
    private boolean zzcuz;
    private ViewTreeObserver$OnGlobalLayoutListener zzcva;
    private ViewTreeObserver$OnScrollChangedListener zzcvb;
    private boolean zzcvc;
    private zzp zzcvd;
    private final zzhl zzcve;
    private zze zzcvf;
    @Nullable
    protected com.google.android.gms.ads.internal.safebrowsing.zzc zzcvg;
    private boolean zzcvh;
    private boolean zzcvi;
    private boolean zzcvj;
    private int zzcvk;
    
    static {
        zzcus = new String[] { "UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS" };
        zzcut = new String[] { "NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID" };
    }
    
    public zzlu(final zzlt zzlt, final boolean b) {
        this(zzlt, b, new zzhl(zzlt, zzlt.zzvo(), new zzda(zzlt.getContext())), null);
    }
    
    zzlu(final zzlt zzbkr, final boolean zzatj, final zzhl zzcve, final zzhh zzbnm) {
        this.zzcuu = new HashMap<String, List<zzev>>();
        this.zzakd = new Object();
        this.zzcuy = false;
        this.zzbkr = zzbkr;
        this.zzatj = zzatj;
        this.zzcve = zzcve;
        this.zzbnm = zzbnm;
    }
    
    private void zzb(final Context context, final String s, final String s2, final String s3) {
        if (zzdi.zzbdz.get()) {
            final Bundle bundle = new Bundle();
            bundle.putString("err", s);
            bundle.putString("code", s2);
            bundle.putString("host", this.zzdi(s3));
            zzu.zzfz().zza(context, this.zzbkr.zzvu().zzcs, "gmob-apps", bundle, true);
        }
    }
    
    private String zzdi(final String s) {
        String host;
        if (TextUtils.isEmpty((CharSequence)s)) {
            host = "";
        }
        else {
            final Uri parse = Uri.parse(s);
            if (parse.getHost() != null) {
                host = parse.getHost();
            }
            else {
                host = "";
            }
        }
        return host;
    }
    
    private static boolean zzi(final Uri uri) {
        final String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }
    
    private void zzws() {
        if (this.zzcuw != null) {
            this.zzcuw.zzk(this.zzbkr);
            this.zzcuw = null;
        }
    }
    
    public final void onLoadResource(final WebView webView, final String s) {
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "Loading resource: ".concat(value);
        }
        else {
            concat = new String("Loading resource: ");
        }
        zzkn.v(concat);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.zzj(parse);
        }
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        synchronized (this.zzakd) {
            if (this.zzcvh) {
                zzkn.v("Blank page loaded, 1...");
                this.zzbkr.zzvw();
            }
            else {
                // monitorexit(this.zzakd)
                this.zzcvi = true;
                this.zzws();
                this.zzwt();
            }
        }
    }
    
    public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        String value;
        if (n < 0 && -1 + -n < zzlu.zzcus.length) {
            value = zzlu.zzcus[-1 + -n];
        }
        else {
            value = String.valueOf(n);
        }
        this.zzb(this.zzbkr.getContext(), "http_err", value, s2);
        super.onReceivedError(webView, n, s, s2);
    }
    
    public final void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        if (sslError != null) {
            final int primaryError = sslError.getPrimaryError();
            String value;
            if (primaryError >= 0 && primaryError < zzlu.zzcut.length) {
                value = zzlu.zzcut[primaryError];
            }
            else {
                value = String.valueOf(primaryError);
            }
            this.zzb(this.zzbkr.getContext(), "ssl_err", value, zzu.zzgb().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
    
    public final void reset() {
        if (this.zzcvg != null) {
            this.zzcvg.zzsy();
            this.zzcvg = null;
        }
        synchronized (this.zzakd) {
            this.zzcuu.clear();
            this.zzawb = null;
            this.zzcuv = null;
            this.zzcct = null;
            this.zzcuw = null;
            this.zzbma = null;
            this.zzcuy = false;
            this.zzatj = false;
            this.zzcuz = false;
            this.zzcvc = false;
            this.zzbno = null;
            this.zzcvd = null;
            this.zzcux = null;
            if (this.zzbnm != null) {
                this.zzbnm.zzt(true);
                this.zzbnm = null;
            }
        }
    }
    
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        WebResourceResponse webResourceResponse = null;
        try {
            final CacheOffering zzag = CacheOffering.zzag(s);
            if (zzag == null) {
                webResourceResponse = null;
            }
            else {
                final CacheEntryParcel zza = zzu.zzge().zza(zzag);
                webResourceResponse = null;
                if (zza != null) {
                    final boolean zziu = zza.zziu();
                    webResourceResponse = null;
                    if (zziu) {
                        webResourceResponse = new WebResourceResponse("", "", zza.zziv());
                    }
                }
            }
        }
        catch (Throwable t) {}
        return webResourceResponse;
    }
    
    public boolean shouldOverrideKeyEvent(final WebView webView, final KeyEvent keyEvent) {
        boolean b = false;
        switch (keyEvent.getKeyCode()) {
            default: {
                b = false;
                break;
            }
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 222: {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "AdWebView shouldOverrideUrlLoading: ".concat(value);
        }
        else {
            concat = new String("AdWebView shouldOverrideUrlLoading: ");
        }
        zzkn.v(concat);
        Uri uri = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(uri.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            this.zzj(uri);
        }
        else {
            if (this.zzcuy && webView == this.zzbkr.getWebView() && zzi(uri)) {
                if (this.zzawb != null && zzdi.zzbcq.get()) {
                    this.zzawb.onAdClicked();
                    if (this.zzcvg != null) {
                        this.zzcvg.zzcq(s);
                    }
                    this.zzawb = null;
                }
                return super.shouldOverrideUrlLoading(webView, s);
            }
            if (!this.zzbkr.getWebView().willNotDraw()) {
                while (true) {
                    try {
                        final zzau zzvt = this.zzbkr.zzvt();
                        if (zzvt != null && zzvt.zzc(uri)) {
                            uri = zzvt.zza(uri, this.zzbkr.getContext(), this.zzbkr.getView());
                        }
                        final Uri uri2 = uri;
                        if (this.zzbnl == null || this.zzbnl.zzer()) {
                            this.zza(new AdLauncherIntentInfoParcel("android.intent.action.VIEW", uri2.toString(), null, null, null, null, null));
                            return true;
                        }
                    }
                    catch (zzav zzav) {
                        final String value2 = String.valueOf(s);
                        String concat2;
                        if (value2.length() != 0) {
                            concat2 = "Unable to append parameter to URL: ".concat(value2);
                        }
                        else {
                            concat2 = new String("Unable to append parameter to URL: ");
                        }
                        com.google.android.gms.ads.internal.util.client.zzb.zzdf(concat2);
                        final Uri uri2 = uri;
                        continue;
                    }
                    break;
                }
                this.zzbnl.zzv(s);
            }
            else {
                final String value3 = String.valueOf(s);
                String concat3;
                if (value3.length() != 0) {
                    concat3 = "AdWebView unable to handle URL: ".concat(value3);
                }
                else {
                    concat3 = new String("AdWebView unable to handle URL: ");
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(concat3);
            }
        }
        return true;
    }
    
    public void zza(final int n, final int n2, final boolean b) {
        this.zzcve.zze(n, n2);
        if (this.zzbnm != null) {
            this.zzbnm.zza(n, n2, b);
        }
    }
    
    public final void zza(final ViewTreeObserver$OnGlobalLayoutListener zzcva, final ViewTreeObserver$OnScrollChangedListener zzcvb) {
        synchronized (this.zzakd) {
            this.zzcuz = true;
            this.zzbkr.zzwd();
            this.zzcva = zzcva;
            this.zzcvb = zzcvb;
        }
    }
    
    public void zza(final com.google.android.gms.ads.internal.client.zza zzawb, final zzg zzcuv, final zzer zzbma, final zzp zzcvd, final boolean b, final zzex zzbno, @Nullable final zzez zzbnj, com.google.android.gms.ads.internal.zze zzbnl, final zzhn zzbve, @Nullable final com.google.android.gms.ads.internal.safebrowsing.zzc zzcvg) {
        if (zzbnl == null) {
            zzbnl = new com.google.android.gms.ads.internal.zze(this.zzbkr.getContext());
        }
        this.zzbnm = new zzhh(this.zzbkr, zzbve);
        this.zzcvg = zzcvg;
        this.zza("/appEvent", new zzeq(zzbma));
        this.zza("/backButton", zzeu.zzbmm);
        this.zza("/refresh", zzeu.zzbmn);
        this.zza("/canOpenURLs", zzeu.zzbmc);
        this.zza("/canOpenIntents", zzeu.zzbmd);
        this.zza("/click", zzeu.zzbme);
        this.zza("/close", zzeu.zzbmf);
        this.zza("/customClose", zzeu.zzbmh);
        this.zza("/instrument", zzeu.zzbmr);
        this.zza("/delayPageLoaded", zzeu.zzbmt);
        this.zza("/delayPageClosed", zzeu.zzbmu);
        this.zza("/getLocationInfo", zzeu.zzbmv);
        this.zza("/httpTrack", zzeu.zzbmi);
        this.zza("/log", zzeu.zzbmj);
        this.zza("/mraid", new zzfb(zzbnl, this.zzbnm));
        this.zza("/mraidLoaded", this.zzcve);
        this.zza("/open", new zzfc(zzbno, zzbnl, this.zzbnm));
        this.zza("/precache", zzeu.zzbmq);
        this.zza("/touch", zzeu.zzbml);
        this.zza("/video", zzeu.zzbmo);
        this.zza("/videoMeta", zzeu.zzbmp);
        this.zza("/appStreaming", zzeu.zzbmg);
        if (zzbnj != null) {
            this.zza("/setInterstitialProperties", new zzey(zzbnj));
        }
        this.zzawb = zzawb;
        this.zzcuv = zzcuv;
        this.zzbma = zzbma;
        this.zzbno = zzbno;
        this.zzcvd = zzcvd;
        this.zzbnl = zzbnl;
        this.zzbve = zzbve;
        this.zzbnj = zzbnj;
        this.zzan(b);
    }
    
    public final void zza(final AdLauncherIntentInfoParcel adLauncherIntentInfoParcel) {
        final boolean zzvv = this.zzbkr.zzvv();
        com.google.android.gms.ads.internal.client.zza zzawb;
        if (zzvv && !this.zzbkr.zzdt().zzaxj) {
            zzawb = null;
        }
        else {
            zzawb = this.zzawb;
        }
        zzg zzcuv = null;
        if (!zzvv) {
            zzcuv = this.zzcuv;
        }
        this.zza(new AdOverlayInfoParcel(adLauncherIntentInfoParcel, zzawb, zzcuv, this.zzcvd, this.zzbkr.zzvu()));
    }
    
    public void zza(final AdOverlayInfoParcel adOverlayInfoParcel) {
        final boolean b = this.zzbnm != null && this.zzbnm.zznu();
        final com.google.android.gms.ads.internal.overlay.zze zzfx = zzu.zzfx();
        final Context context = this.zzbkr.getContext();
        boolean b2 = false;
        if (!b) {
            b2 = true;
        }
        zzfx.zza(context, adOverlayInfoParcel, b2);
        if (this.zzcvg != null) {
            String s = adOverlayInfoParcel.url;
            if (s == null && adOverlayInfoParcel.zzbye != null) {
                s = adOverlayInfoParcel.zzbye.url;
            }
            this.zzcvg.zzcq(s);
        }
    }
    
    public void zza(final zza zzcct) {
        this.zzcct = zzcct;
    }
    
    public void zza(final zzb zzcuw) {
        this.zzcuw = zzcuw;
    }
    
    public void zza(final zzc zzcux) {
        this.zzcux = zzcux;
    }
    
    public void zza(final zze zzcvf) {
        this.zzcvf = zzcvf;
    }
    
    public void zza(final String s, final zzev zzev) {
        synchronized (this.zzakd) {
            List<zzev> list = this.zzcuu.get(s);
            if (list == null) {
                list = new CopyOnWriteArrayList<zzev>();
                this.zzcuu.put(s, list);
            }
            list.add(zzev);
        }
    }
    
    public final void zza(final boolean b, final int n) {
        com.google.android.gms.ads.internal.client.zza zzawb;
        if (this.zzbkr.zzvv() && !this.zzbkr.zzdt().zzaxj) {
            zzawb = null;
        }
        else {
            zzawb = this.zzawb;
        }
        this.zza(new AdOverlayInfoParcel(zzawb, this.zzcuv, this.zzcvd, this.zzbkr, b, n, this.zzbkr.zzvu()));
    }
    
    public final void zza(final boolean b, final int n, final String s) {
        final boolean zzvv = this.zzbkr.zzvv();
        com.google.android.gms.ads.internal.client.zza zzawb;
        if (zzvv && !this.zzbkr.zzdt().zzaxj) {
            zzawb = null;
        }
        else {
            zzawb = this.zzawb;
        }
        zzg zzg = null;
        if (!zzvv) {
            zzg = new zzd(this.zzbkr, this.zzcuv);
        }
        this.zza(new AdOverlayInfoParcel(zzawb, zzg, this.zzbma, this.zzcvd, this.zzbkr, b, n, s, this.zzbkr.zzvu(), this.zzbno));
    }
    
    public final void zza(final boolean b, final int n, final String s, final String s2) {
        final boolean zzvv = this.zzbkr.zzvv();
        com.google.android.gms.ads.internal.client.zza zzawb;
        if (zzvv && !this.zzbkr.zzdt().zzaxj) {
            zzawb = null;
        }
        else {
            zzawb = this.zzawb;
        }
        zzg zzg;
        if (zzvv) {
            zzg = null;
        }
        else {
            zzg = new zzd(this.zzbkr, this.zzcuv);
        }
        this.zza(new AdOverlayInfoParcel(zzawb, zzg, this.zzbma, this.zzcvd, this.zzbkr, b, n, s, s2, this.zzbkr.zzvu(), this.zzbno));
    }
    
    public void zzan(final boolean zzcuy) {
        this.zzcuy = zzcuy;
    }
    
    public void zzb(final String s, final zzev zzev) {
        synchronized (this.zzakd) {
            final List<zzev> list = this.zzcuu.get(s);
            if (list != null) {
                list.remove(zzev);
            }
        }
    }
    
    public void zzd(final int n, final int n2) {
        if (this.zzbnm != null) {
            this.zzbnm.zzd(n, n2);
        }
    }
    
    public boolean zzib() {
        synchronized (this.zzakd) {
            return this.zzatj;
        }
    }
    
    public void zzj(final Uri uri) {
        final String path = uri.getPath();
        final List<zzev> list = this.zzcuu.get(path);
        if (list != null) {
            final Map<String, String> zzg = zzu.zzfz().zzg(uri);
            if (com.google.android.gms.ads.internal.util.client.zzb.zzbf(2)) {
                final String value = String.valueOf(path);
                String concat;
                if (value.length() != 0) {
                    concat = "Received GMSG: ".concat(value);
                }
                else {
                    concat = new String("Received GMSG: ");
                }
                zzkn.v(concat);
                for (final String s : zzg.keySet()) {
                    final String s2 = zzg.get(s);
                    zzkn.v(new StringBuilder(4 + String.valueOf(s).length() + String.valueOf(s2).length()).append("  ").append(s).append(": ").append(s2).toString());
                }
            }
            final Iterator<zzev> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().zza(this.zzbkr, zzg);
            }
        }
        else {
            final String value2 = String.valueOf(uri);
            zzkn.v(new StringBuilder(32 + String.valueOf(value2).length()).append("No GMSG handler found for GMSG: ").append(value2).toString());
        }
    }
    
    public void zzo(final zzlt zzbkr) {
        this.zzbkr = zzbkr;
    }
    
    public final void zzov() {
        synchronized (this.zzakd) {
            this.zzcuy = false;
            this.zzatj = true;
            zzu.zzfz().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    zzlu.this.zzbkr.zzwd();
                    final com.google.android.gms.ads.internal.overlay.zzd zzvp = zzlu.this.zzbkr.zzvp();
                    if (zzvp != null) {
                        zzvp.zzov();
                    }
                    if (zzlu.this.zzcux != null) {
                        zzlu.this.zzcux.zzet();
                        zzlu.this.zzcux = null;
                    }
                }
            });
        }
    }
    
    public com.google.android.gms.ads.internal.zze zzwi() {
        return this.zzbnl;
    }
    
    public boolean zzwj() {
        synchronized (this.zzakd) {
            return this.zzcuz;
        }
    }
    
    public ViewTreeObserver$OnGlobalLayoutListener zzwk() {
        synchronized (this.zzakd) {
            return this.zzcva;
        }
    }
    
    public ViewTreeObserver$OnScrollChangedListener zzwl() {
        synchronized (this.zzakd) {
            return this.zzcvb;
        }
    }
    
    public boolean zzwm() {
        synchronized (this.zzakd) {
            return this.zzcvc;
        }
    }
    
    public void zzwn() {
        synchronized (this.zzakd) {
            zzkn.v("Loading blank page in WebView, 2...");
            this.zzcvh = true;
            this.zzbkr.zzdg("about:blank");
        }
    }
    
    public void zzwo() {
        if (this.zzcvg != null) {
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (zzlu.this.zzcvg != null) {
                        zzlu.this.zzcvg.zzj(zzlu.this.zzbkr.getView());
                    }
                }
            });
        }
    }
    
    public void zzwp() {
        synchronized (this.zzakd) {
            this.zzcvc = true;
            // monitorexit(this.zzakd)
            ++this.zzcvk;
            this.zzwt();
        }
    }
    
    public void zzwq() {
        --this.zzcvk;
        this.zzwt();
    }
    
    public void zzwr() {
        this.zzcvj = true;
        this.zzwt();
    }
    
    public final void zzwt() {
        if (this.zzcct != null && ((this.zzcvi && this.zzcvk <= 0) || this.zzcvj)) {
            this.zzcct.zza(this.zzbkr, !this.zzcvj);
            this.zzcct = null;
        }
        this.zzbkr.zzwe();
    }
    
    public zze zzwu() {
        return this.zzcvf;
    }
    
    public interface zza
    {
        void zza(final zzlt p0, final boolean p1);
    }
    
    public interface zzb
    {
        void zzk(final zzlt p0);
    }
    
    public interface zzc
    {
        void zzet();
    }
    
    private static class zzd implements zzg
    {
        private zzg zzcuv;
        private zzlt zzcvm;
        
        public zzd(final zzlt zzcvm, final zzg zzcuv) {
            this.zzcvm = zzcvm;
            this.zzcuv = zzcuv;
        }
        
        @Override
        public void onPause() {
        }
        
        @Override
        public void onResume() {
        }
        
        @Override
        public void zzed() {
            this.zzcuv.zzed();
            this.zzcvm.zzvl();
        }
        
        @Override
        public void zzee() {
            this.zzcuv.zzee();
            this.zzcvm.zzoz();
        }
    }
    
    public interface zze
    {
        void zzes();
    }
}
