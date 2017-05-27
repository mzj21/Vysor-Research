// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.MutableContextWrapper;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzm;
import android.app.Activity;
import org.json.JSONObject;
import org.json.JSONException;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import android.view.View$MeasureSpec;
import android.view.MotionEvent;
import android.graphics.Canvas;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import android.annotation.TargetApi;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.ValueCallback;
import java.util.HashMap;
import android.webkit.WebSettings;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.View;
import com.google.android.gms.ads.internal.zzu;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.Map;
import android.view.View$OnClickListener;
import java.lang.ref.WeakReference;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.ads.internal.zzs;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import android.webkit.DownloadListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.webkit.WebView;

@zziy
class zzlx extends WebView implements ViewTreeObserver$OnGlobalLayoutListener, DownloadListener, zzlt
{
    private final Object zzakd;
    private final zzd zzalo;
    private final VersionInfoParcel zzanh;
    private AdSizeParcel zzapc;
    private zzlf zzasf;
    private final WindowManager zzasl;
    @Nullable
    private final zzau zzbkp;
    private int zzbvw;
    private int zzbvx;
    private int zzbvz;
    private int zzbwa;
    private String zzcaj;
    private zzdo zzcak;
    private Boolean zzcpv;
    private final zza zzcvp;
    private final zzs zzcvq;
    private zzlu zzcvr;
    private com.google.android.gms.ads.internal.overlay.zzd zzcvs;
    private boolean zzcvt;
    private boolean zzcvu;
    private boolean zzcvv;
    private boolean zzcvw;
    private int zzcvx;
    private boolean zzcvy;
    boolean zzcvz;
    private zzly zzcwa;
    private boolean zzcwb;
    private boolean zzcwc;
    private zzg zzcwd;
    private int zzcwe;
    private int zzcwf;
    private zzdo zzcwg;
    private zzdo zzcwh;
    private zzdp zzcwi;
    private WeakReference<View$OnClickListener> zzcwj;
    private com.google.android.gms.ads.internal.overlay.zzd zzcwk;
    private Map<String, zzfj> zzcwl;
    
    protected zzlx(final zza zzcvp, final AdSizeParcel zzapc, final boolean zzcvv, final boolean b, @Nullable final zzau zzbkp, final VersionInfoParcel zzanh, final zzdq zzdq, final zzs zzcvq, final zzd zzalo) {
        super((Context)zzcvp);
        this.zzakd = new Object();
        this.zzcvy = true;
        this.zzcvz = false;
        this.zzcaj = "";
        this.zzbvx = -1;
        this.zzbvw = -1;
        this.zzbvz = -1;
        this.zzbwa = -1;
        this.zzcvp = zzcvp;
        this.zzapc = zzapc;
        this.zzcvv = zzcvv;
        this.zzcvx = -1;
        this.zzbkp = zzbkp;
        this.zzanh = zzanh;
        this.zzcvq = zzcvq;
        this.zzalo = zzalo;
        this.zzasl = (WindowManager)this.getContext().getSystemService("window");
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build$VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzu.zzfz().zza((Context)zzcvp, zzanh.zzcs, settings);
        zzu.zzgb().zza(this.getContext(), settings);
        this.setDownloadListener((DownloadListener)this);
        this.zzxa();
        if (com.google.android.gms.common.util.zzs.zzaxp()) {
            this.addJavascriptInterface((Object)new zzlz(this), "googleAdsJsInterface");
        }
        if (com.google.android.gms.common.util.zzs.zzaxk()) {
            this.removeJavascriptInterface("accessibility");
            this.removeJavascriptInterface("accessibilityTraversal");
        }
        this.zzasf = new zzlf(this.zzcvp.zzvn(), (View)this, (ViewTreeObserver$OnGlobalLayoutListener)this, null);
        this.zzd(zzdq);
    }
    
    private void zzao(final boolean b) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String s;
        if (b) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("isVisible", s);
        this.zza("onAdVisibilityChanged", hashMap);
    }
    
    static zzlx zzb(final Context context, final AdSizeParcel adSizeParcel, final boolean b, final boolean b2, @Nullable final zzau zzau, final VersionInfoParcel versionInfoParcel, final zzdq zzdq, final zzs zzs, final zzd zzd) {
        return new zzlx(new zza(context), adSizeParcel, b, b2, zzau, versionInfoParcel, zzdq, zzs, zzd);
    }
    
    static /* synthetic */ void zzc(final zzlx zzlx) {
        zzlx.destroy();
    }
    
    private void zzd(final zzdq zzdq) {
        this.zzxe();
        this.zzcwi = new zzdp(new zzdq(true, "make_wv", this.zzapc.zzaxi));
        this.zzcwi.zzkz().zzc(zzdq);
        this.zzcak = zzdm.zzb(this.zzcwi.zzkz());
        this.zzcwi.zza("native:view_create", this.zzcak);
        this.zzcwh = null;
        this.zzcwg = null;
    }
    
    private void zzww() {
        synchronized (this.zzakd) {
            this.zzcpv = zzu.zzgd().zztr();
            if (this.zzcpv != null) {
                return;
            }
            try {
                this.evaluateJavascript("(function(){})()", null);
                this.zzb(Boolean.valueOf(true));
            }
            catch (IllegalStateException ex) {
                this.zzb(Boolean.valueOf(false));
            }
        }
    }
    
    private void zzwx() {
        zzdm.zza(this.zzcwi.zzkz(), this.zzcak, "aeh2");
    }
    
    private void zzwy() {
        zzdm.zza(this.zzcwi.zzkz(), this.zzcak, "aebb2");
    }
    
    private void zzxa() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzcvv || this.zzapc.zzaxj) {
                    if (Build$VERSION.SDK_INT < 14) {
                        zzb.zzdd("Disabling hardware acceleration on an overlay.");
                        this.zzxb();
                    }
                    else {
                        zzb.zzdd("Enabling hardware acceleration on an overlay.");
                        this.zzxc();
                    }
                    return;
                }
            }
            if (Build$VERSION.SDK_INT < 18) {
                zzb.zzdd("Disabling hardware acceleration on an AdView.");
                this.zzxb();
                return;
            }
            zzb.zzdd("Enabling hardware acceleration on an AdView.");
            this.zzxc();
        }
    }
    
    private void zzxb() {
        synchronized (this.zzakd) {
            if (!this.zzcvw) {
                zzu.zzgb().zzp((View)this);
            }
            this.zzcvw = true;
        }
    }
    
    private void zzxc() {
        synchronized (this.zzakd) {
            if (this.zzcvw) {
                zzu.zzgb().zzo((View)this);
            }
            this.zzcvw = false;
        }
    }
    
    private void zzxd() {
        synchronized (this.zzakd) {
            this.zzcwl = null;
        }
    }
    
    private void zzxe() {
        if (this.zzcwi != null) {
            final zzdq zzkz = this.zzcwi.zzkz();
            if (zzkz != null && zzu.zzgd().zztm() != null) {
                zzu.zzgd().zztm().zza(zzkz);
            }
        }
    }
    
    public void destroy() {
        synchronized (this.zzakd) {
            this.zzxe();
            this.zzasf.zzvb();
            if (this.zzcvs != null) {
                this.zzcvs.close();
                this.zzcvs.onDestroy();
                this.zzcvs = null;
            }
            this.zzcvr.reset();
            if (!this.zzcvu) {
                zzu.zzgw().zze(this);
                this.zzxd();
                this.zzcvu = true;
                zzkn.v("Initiating WebView self destruct sequence in 3...");
                this.zzcvr.zzwn();
            }
        }
    }
    
    @TargetApi(19)
    public void evaluateJavascript(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this.zzakd) {
            if (this.isDestroyed()) {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
            }
            else {
                super.evaluateJavascript(s, (ValueCallback)valueCallback);
            }
        }
    }
    
    protected void finalize() throws Throwable {
        synchronized (this.zzakd) {
            if (!this.zzcvu) {
                this.zzcvr.reset();
                zzu.zzgw().zze(this);
                this.zzxd();
            }
            // monitorexit(this.zzakd)
            super.finalize();
        }
    }
    
    public String getRequestId() {
        synchronized (this.zzakd) {
            return this.zzcaj;
        }
    }
    
    public int getRequestedOrientation() {
        synchronized (this.zzakd) {
            return this.zzcvx;
        }
    }
    
    public View getView() {
        return (View)this;
    }
    
    public WebView getWebView() {
        return this;
    }
    
    public boolean isDestroyed() {
        synchronized (this.zzakd) {
            return this.zzcvu;
        }
    }
    
    public void loadData(final String s, final String s2, final String s3) {
        synchronized (this.zzakd) {
            if (!this.isDestroyed()) {
                super.loadData(s, s2, s3);
            }
            else {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        synchronized (this.zzakd) {
            if (!this.isDestroyed()) {
                super.loadDataWithBaseURL(s, s2, s3, s4, s5);
            }
            else {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public void loadUrl(final String s) {
        while (true) {
            synchronized (this.zzakd) {
                if (!this.isDestroyed()) {
                    try {
                        super.loadUrl(s);
                        return;
                    }
                    catch (Throwable t) {
                        final String value = String.valueOf(t);
                        zzb.zzdf(new StringBuilder(24 + String.valueOf(value).length()).append("Could not call loadUrl. ").append(value).toString());
                    }
                }
            }
            zzb.zzdf("The webview is destroyed. Ignoring action.");
        }
    }
    
    protected void onAttachedToWindow() {
        while (true) {
            boolean b = true;
            while (true) {
                final boolean zzcwb;
                synchronized (this.zzakd) {
                    super.onAttachedToWindow();
                    if (!this.isDestroyed()) {
                        this.zzasf.onAttachedToWindow();
                    }
                    zzcwb = this.zzcwb;
                    if (this.zzvr() != null && this.zzvr().zzwj()) {
                        if (!this.zzcwc) {
                            final ViewTreeObserver$OnGlobalLayoutListener zzwk = this.zzvr().zzwk();
                            if (zzwk != null) {
                                zzu.zzgx().zza(this.getView(), zzwk);
                            }
                            final ViewTreeObserver$OnScrollChangedListener zzwl = this.zzvr().zzwl();
                            if (zzwl != null) {
                                zzu.zzgx().zza(this.getView(), zzwl);
                            }
                            this.zzcwc = true;
                        }
                        this.zzao(b);
                        return;
                    }
                }
                b = zzcwb;
                continue;
            }
        }
    }
    
    protected void onDetachedFromWindow() {
        synchronized (this.zzakd) {
            if (!this.isDestroyed()) {
                this.zzasf.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzcwc && this.zzvr() != null && this.zzvr().zzwj() && this.getViewTreeObserver() != null && this.getViewTreeObserver().isAlive()) {
                final ViewTreeObserver$OnGlobalLayoutListener zzwk = this.zzvr().zzwk();
                if (zzwk != null) {
                    zzu.zzgb().zza(this.getViewTreeObserver(), zzwk);
                }
                final ViewTreeObserver$OnScrollChangedListener zzwl = this.zzvr().zzwl();
                if (zzwl != null) {
                    this.getViewTreeObserver().removeOnScrollChangedListener(zzwl);
                }
                this.zzcwc = false;
            }
            // monitorexit(this.zzakd)
            this.zzao(false);
        }
    }
    
    public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
        try {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(s), s4);
            zzu.zzfz().zzb(this.getContext(), intent);
        }
        catch (ActivityNotFoundException ex) {
            zzb.zzdd(new StringBuilder(51 + String.valueOf(s).length() + String.valueOf(s4).length()).append("Couldn't find an Activity to view url/mimetype: ").append(s).append(" / ").append(s4).toString());
        }
    }
    
    @TargetApi(21)
    protected void onDraw(final Canvas canvas) {
        if (!this.isDestroyed() && (Build$VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || this.isAttachedToWindow())) {
            super.onDraw(canvas);
            if (this.zzvr() != null && this.zzvr().zzwu() != null) {
                this.zzvr().zzwu().zzes();
            }
        }
    }
    
    public boolean onGenericMotionEvent(final MotionEvent motionEvent) {
        if (!zzdi.zzbcx.get()) {
            return super.onGenericMotionEvent(motionEvent);
        }
        final float axisValue = motionEvent.getAxisValue(9);
        final float axisValue2 = motionEvent.getAxisValue(10);
        boolean b;
        if (motionEvent.getActionMasked() == 8) {
            b = true;
        }
        else {
            b = false;
        }
        if (!b || ((axisValue <= 0.0f || this.canScrollVertically(-1)) && (axisValue >= 0.0f || this.canScrollVertically(1)) && (axisValue2 <= 0.0f || this.canScrollHorizontally(-1)) && (axisValue2 >= 0.0f || this.canScrollHorizontally(1)))) {
            return super.onGenericMotionEvent(motionEvent);
        }
        return false;
        onGenericMotionEvent = super.onGenericMotionEvent(motionEvent);
        return onGenericMotionEvent;
    }
    
    public void onGlobalLayout() {
        final boolean zzwv = this.zzwv();
        final com.google.android.gms.ads.internal.overlay.zzd zzvp = this.zzvp();
        if (zzvp != null && zzwv) {
            zzvp.zzoy();
        }
    }
    
    @SuppressLint({ "DrawAllocation" })
    protected void onMeasure(final int n, final int n2) {
        int n3 = Integer.MAX_VALUE;
        synchronized (this.zzakd) {
            if (this.isDestroyed()) {
                this.setMeasuredDimension(0, 0);
                return;
            }
            if (this.isInEditMode() || this.zzcvv || this.zzapc.zzaxl) {
                super.onMeasure(n, n2);
                return;
            }
        }
        if (this.zzapc.zzaxm) {
            if (zzdi.zzbgf.get() || !com.google.android.gms.common.util.zzs.zzaxp()) {
                super.onMeasure(n, n2);
            }
            // monitorexit(o)
            else {
                this.zza("/contentHeight", this.zzwz());
                this.zzdk("(function() {  var mHeight = -1;  if (document.body) { mHeight = document.body.offsetHeight;}  else if (document.documentElement) {      mHeight = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'mHeight=' + mHeight;  window.googleAdsJsInterface.notify(url);  })();");
                final float density = this.zzcvp.getResources().getDisplayMetrics().density;
                final int size = View$MeasureSpec.getSize(n);
                int size2 = 0;
                switch (this.zzcwf) {
                    default: {
                        size2 = (int)(density * this.zzcwf);
                        break;
                    }
                    case -1: {
                        size2 = View$MeasureSpec.getSize(n2);
                        break;
                    }
                }
                this.setMeasuredDimension(size, size2);
            }
            // monitorexit(o)
        }
        else if (this.zzapc.zzaxj) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            this.zzasl.getDefaultDisplay().getMetrics(displayMetrics);
            this.setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        // monitorexit(o)
        else {
            final int mode = View$MeasureSpec.getMode(n);
            final int size3 = View$MeasureSpec.getSize(n);
            final int mode2 = View$MeasureSpec.getMode(n2);
            final int size4 = View$MeasureSpec.getSize(n2);
            int n4;
            if (mode != Integer.MIN_VALUE && mode != 1073741824) {
                n4 = n3;
            }
            else {
                n4 = size3;
            }
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                n3 = size4;
            }
            if (this.zzapc.widthPixels > n4 || this.zzapc.heightPixels > n3) {
                final float density2 = this.zzcvp.getResources().getDisplayMetrics().density;
                zzb.zzdf(new StringBuilder(103).append("Not enough space to show ad. Needs ").append((int)(this.zzapc.widthPixels / density2)).append("x").append((int)(this.zzapc.heightPixels / density2)).append(" dp, but only has ").append((int)(size3 / density2)).append("x").append((int)(size4 / density2)).append(" dp.").toString());
                if (this.getVisibility() != 8) {
                    this.setVisibility(4);
                }
                this.setMeasuredDimension(0, 0);
            }
            else {
                if (this.getVisibility() != 8) {
                    this.setVisibility(0);
                }
                this.setMeasuredDimension(this.zzapc.widthPixels, this.zzapc.heightPixels);
            }
        }
        // monitorexit(o)
    }
    
    public void onPause() {
        if (!this.isDestroyed()) {
            try {
                if (com.google.android.gms.common.util.zzs.zzaxk()) {
                    super.onPause();
                }
            }
            catch (Exception ex) {
                zzb.zzb("Could not pause webview.", ex);
            }
        }
    }
    
    public void onResume() {
        if (!this.isDestroyed()) {
            try {
                if (com.google.android.gms.common.util.zzs.zzaxk()) {
                    super.onResume();
                }
            }
            catch (Exception ex) {
                zzb.zzb("Could not resume webview.", ex);
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        while (true) {
            Label_0054: {
                if (!this.zzvr().zzwj()) {
                    break Label_0054;
                }
                while (true) {
                    synchronized (this.zzakd) {
                        if (this.zzcwd != null) {
                            this.zzcwd.zzc(motionEvent);
                        }
                        // monitorexit(this.zzakd)
                        if (this.isDestroyed()) {
                            return false;
                        }
                        return super.onTouchEvent(motionEvent);
                    }
                    break;
                    return super.onTouchEvent(motionEvent);
                }
            }
            if (this.zzbkp != null) {
                this.zzbkp.zza(motionEvent);
                continue;
            }
            continue;
        }
    }
    
    public void setContext(final Context baseContext) {
        this.zzcvp.setBaseContext(baseContext);
        this.zzasf.zzl(this.zzcvp.zzvn());
    }
    
    public void setOnClickListener(final View$OnClickListener onClickListener) {
        this.zzcwj = new WeakReference<View$OnClickListener>(onClickListener);
        super.setOnClickListener(onClickListener);
    }
    
    public void setRequestedOrientation(final int zzcvx) {
        synchronized (this.zzakd) {
            this.zzcvx = zzcvx;
            if (this.zzcvs != null) {
                this.zzcvs.setRequestedOrientation(this.zzcvx);
            }
        }
    }
    
    public void setWebViewClient(final WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzlu) {
            this.zzcvr = (zzlu)webViewClient;
        }
    }
    
    public void stopLoading() {
        if (!this.isDestroyed()) {
            try {
                super.stopLoading();
            }
            catch (Exception ex) {
                zzb.zzb("Could not stop loading webview.", ex);
            }
        }
    }
    
    public void zza(final Context context, final AdSizeParcel zzapc, final zzdq zzdq) {
        synchronized (this.zzakd) {
            this.zzasf.zzvb();
            this.setContext(context);
            this.zzcvs = null;
            this.zzapc = zzapc;
            this.zzcvv = false;
            this.zzcvt = false;
            this.zzcaj = "";
            this.zzcvx = -1;
            zzu.zzgb().zzm(this);
            this.loadUrl("about:blank");
            this.zzcvr.reset();
            this.setOnTouchListener(null);
            this.setOnClickListener(null);
            this.zzcvy = true;
            this.zzcvz = false;
            this.zzcwa = null;
            this.zzd(zzdq);
            this.zzcwb = false;
            this.zzcwe = 0;
            zzu.zzgw().zze(this);
            this.zzxd();
        }
    }
    
    public void zza(final AdSizeParcel zzapc) {
        synchronized (this.zzakd) {
            this.zzapc = zzapc;
            this.requestLayout();
        }
    }
    
    public void zza(final zzcj zzcj, final boolean zzcwb) {
        synchronized (this.zzakd) {
            this.zzcwb = zzcwb;
            // monitorexit(this.zzakd)
            this.zzao(zzcwb);
        }
    }
    
    public void zza(final zzly zzcwa) {
        synchronized (this.zzakd) {
            if (this.zzcwa != null) {
                zzb.e("Attempt to create multiple AdWebViewVideoControllers.");
            }
            else {
                this.zzcwa = zzcwa;
            }
        }
    }
    
    @TargetApi(19)
    protected void zza(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this.zzakd) {
            if (!this.isDestroyed()) {
                this.evaluateJavascript(s, valueCallback);
            }
            else {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
            }
        }
    }
    
    public void zza(final String s, final zzev zzev) {
        if (this.zzcvr != null) {
            this.zzcvr.zza(s, zzev);
        }
    }
    
    public void zza(final String s, final Map<String, ?> map) {
        try {
            this.zzb(s, zzu.zzfz().zzan(map));
        }
        catch (JSONException ex) {
            zzb.zzdf("Could not convert parameters to JSON.");
        }
    }
    
    public void zza(final String s, JSONObject jsonObject) {
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        this.zzj(s, jsonObject.toString());
    }
    
    public void zzah(final int n) {
        if (n == 0) {
            this.zzwy();
        }
        this.zzwx();
        if (this.zzcwi.zzkz() != null) {
            this.zzcwi.zzkz().zzh("close_type", String.valueOf(n));
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put("closetype", String.valueOf(n));
        hashMap.put("version", this.zzanh.zzcs);
        this.zza("onhide", hashMap);
    }
    
    public void zzaj(final boolean zzcvv) {
        synchronized (this.zzakd) {
            this.zzcvv = zzcvv;
            this.zzxa();
        }
    }
    
    public void zzak(final boolean zzcvt) {
        synchronized (this.zzakd) {
            if (this.zzcvs != null) {
                this.zzcvs.zza(this.zzcvr.zzib(), zzcvt);
            }
            else {
                this.zzcvt = zzcvt;
            }
        }
    }
    
    public void zzal(final boolean zzcvy) {
        synchronized (this.zzakd) {
            this.zzcvy = zzcvy;
        }
    }
    
    public void zzam(final boolean b) {
        while (true) {
            while (true) {
                synchronized (this.zzakd) {
                    final int zzcwe = this.zzcwe;
                    if (b) {
                        final int n = 1;
                        this.zzcwe = n + zzcwe;
                        if (this.zzcwe <= 0 && this.zzcvs != null) {
                            this.zzcvs.zzpb();
                        }
                        return;
                    }
                }
                final int n = -1;
                continue;
            }
        }
    }
    
    public void zzb(final zzg zzcwd) {
        synchronized (this.zzakd) {
            this.zzcwd = zzcwd;
        }
    }
    
    public void zzb(final com.google.android.gms.ads.internal.overlay.zzd zzcvs) {
        synchronized (this.zzakd) {
            this.zzcvs = zzcvs;
        }
    }
    
    void zzb(final Boolean zzcpv) {
        synchronized (this.zzakd) {
            this.zzcpv = zzcpv;
            // monitorexit(this.zzakd)
            zzu.zzgd().zzb(zzcpv);
        }
    }
    
    public void zzb(final String s, final zzev zzev) {
        if (this.zzcvr != null) {
            this.zzcvr.zzb(s, zzev);
        }
    }
    
    public void zzb(final String s, JSONObject jsonObject) {
        if (jsonObject == null) {
            jsonObject = new JSONObject();
        }
        final String string = jsonObject.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(s);
        sb.append("'");
        sb.append(",");
        sb.append(string);
        sb.append(");");
        final String value = String.valueOf(sb.toString());
        String concat;
        if (value.length() != 0) {
            concat = "Dispatching AFMA event: ".concat(value);
        }
        else {
            concat = new String("Dispatching AFMA event: ");
        }
        zzb.zzdd(concat);
        this.zzdk(sb.toString());
    }
    
    public void zzc(final com.google.android.gms.ads.internal.overlay.zzd zzcwk) {
        synchronized (this.zzakd) {
            this.zzcwk = zzcwk;
        }
    }
    
    public void zzdg(final String s) {
        synchronized (this.zzakd) {
            try {
                super.loadUrl(s);
            }
            catch (Throwable t) {
                final String value = String.valueOf(t);
                zzb.zzdf(new StringBuilder(24 + String.valueOf(value).length()).append("Could not call loadUrl. ").append(value).toString());
            }
        }
    }
    
    public void zzdh(String zzcaj) {
        final Object zzakd = this.zzakd;
        // monitorenter(zzakd)
        Label_0014: {
            if (zzcaj != null) {
                break Label_0014;
            }
            try {
                zzcaj = "";
                this.zzcaj = zzcaj;
            }
            finally {
            }
            // monitorexit(zzakd)
        }
    }
    
    protected void zzdj(final String s) {
        synchronized (this.zzakd) {
            if (!this.isDestroyed()) {
                this.loadUrl(s);
            }
            else {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    protected void zzdk(final String s) {
        if (com.google.android.gms.common.util.zzs.zzaxr()) {
            if (this.zztr() == null) {
                this.zzww();
            }
            if (this.zztr()) {
                this.zza(s, (ValueCallback<String>)null);
            }
            else {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "javascript:".concat(value);
                }
                else {
                    concat = new String("javascript:");
                }
                this.zzdj(concat);
            }
        }
        else {
            final String value2 = String.valueOf(s);
            String concat2;
            if (value2.length() != 0) {
                concat2 = "javascript:".concat(value2);
            }
            else {
                concat2 = new String("javascript:");
            }
            this.zzdj(concat2);
        }
    }
    
    public zzd zzdp() {
        return this.zzalo;
    }
    
    public AdSizeParcel zzdt() {
        synchronized (this.zzakd) {
            return this.zzapc;
        }
    }
    
    public void zzel() {
        synchronized (this.zzakd) {
            this.zzcvz = true;
            if (this.zzcvq != null) {
                this.zzcvq.zzel();
            }
        }
    }
    
    public void zzem() {
        synchronized (this.zzakd) {
            this.zzcvz = false;
            if (this.zzcvq != null) {
                this.zzcvq.zzem();
            }
        }
    }
    
    public void zzj(final String s, final String s2) {
        this.zzdk(new StringBuilder(3 + String.valueOf(s).length() + String.valueOf(s2).length()).append(s).append("(").append(s2).append(");").toString());
    }
    
    public void zzoz() {
        if (this.zzcwg == null) {
            zzdm.zza(this.zzcwi.zzkz(), this.zzcak, "aes2");
            this.zzcwg = zzdm.zzb(this.zzcwi.zzkz());
            this.zzcwi.zza("native:view_show", this.zzcwg);
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzanh.zzcs);
        this.zza("onshow", hashMap);
    }
    
    public boolean zzpr() {
        synchronized (this.zzakd) {
            return this.zzcvy;
        }
    }
    
    Boolean zztr() {
        synchronized (this.zzakd) {
            return this.zzcpv;
        }
    }
    
    public void zzvl() {
        this.zzwx();
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzanh.zzcs);
        this.zza("onhide", hashMap);
    }
    
    public void zzvm() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(3);
        hashMap.put("app_muted", String.valueOf(zzu.zzfz().zzfg()));
        hashMap.put("app_volume", String.valueOf(zzu.zzfz().zzfe()));
        hashMap.put("device_volume", String.valueOf(zzu.zzfz().zzal(this.getContext())));
        this.zza("volume", hashMap);
    }
    
    public Activity zzvn() {
        return this.zzcvp.zzvn();
    }
    
    public Context zzvo() {
        return this.zzcvp.zzvo();
    }
    
    public com.google.android.gms.ads.internal.overlay.zzd zzvp() {
        synchronized (this.zzakd) {
            return this.zzcvs;
        }
    }
    
    public com.google.android.gms.ads.internal.overlay.zzd zzvq() {
        synchronized (this.zzakd) {
            return this.zzcwk;
        }
    }
    
    public zzlu zzvr() {
        return this.zzcvr;
    }
    
    public boolean zzvs() {
        synchronized (this.zzakd) {
            return this.zzcvt;
        }
    }
    
    public zzau zzvt() {
        return this.zzbkp;
    }
    
    public VersionInfoParcel zzvu() {
        return this.zzanh;
    }
    
    public boolean zzvv() {
        synchronized (this.zzakd) {
            return this.zzcvv;
        }
    }
    
    public void zzvw() {
        synchronized (this.zzakd) {
            zzkn.v("Destroying WebView!");
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    zzlx.zzc(zzlx.this);
                }
            });
        }
    }
    
    public boolean zzvx() {
        synchronized (this.zzakd) {
            return this.zzcvz;
        }
    }
    
    public zzls zzvy() {
        return null;
    }
    
    public zzdo zzvz() {
        return this.zzcak;
    }
    
    public zzdp zzwa() {
        return this.zzcwi;
    }
    
    public zzly zzwb() {
        synchronized (this.zzakd) {
            return this.zzcwa;
        }
    }
    
    public boolean zzwc() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzcwe > 0) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public void zzwd() {
        this.zzasf.zzva();
    }
    
    public void zzwe() {
        if (this.zzcwh == null) {
            this.zzcwh = zzdm.zzb(this.zzcwi.zzkz());
            this.zzcwi.zza("native:view_load", this.zzcwh);
        }
    }
    
    public View$OnClickListener zzwf() {
        return this.zzcwj.get();
    }
    
    public zzg zzwg() {
        synchronized (this.zzakd) {
            return this.zzcwd;
        }
    }
    
    public void zzwh() {
        this.setBackgroundColor(0);
    }
    
    public boolean zzwv() {
        Label_0030: {
            if (this.zzvr().zzib()) {
                break Label_0030;
            }
            final boolean zzwj = this.zzvr().zzwj();
            final boolean b = false;
            if (zzwj) {
                break Label_0030;
            }
            return b;
        }
        final DisplayMetrics zza = zzu.zzfz().zza(this.zzasl);
        final int zzb = zzm.zzjr().zzb(zza, zza.widthPixels);
        final int zzb2 = zzm.zzjr().zzb(zza, zza.heightPixels);
        final Activity zzvn = this.zzvn();
        int zzb3;
        int zzb4;
        if (zzvn == null || zzvn.getWindow() == null) {
            zzb3 = zzb2;
            zzb4 = zzb;
        }
        else {
            final int[] zzh = zzu.zzfz().zzh(zzvn);
            zzb4 = zzm.zzjr().zzb(zza, zzh[0]);
            zzb3 = zzm.zzjr().zzb(zza, zzh[1]);
        }
        if (this.zzbvw == zzb && this.zzbvx == zzb2 && this.zzbvz == zzb4) {
            final int zzbwa = this.zzbwa;
            final boolean b = false;
            if (zzbwa == zzb3) {
                return b;
            }
        }
        final boolean b2 = this.zzbvw != zzb || this.zzbvx != zzb2;
        this.zzbvw = zzb;
        this.zzbvx = zzb2;
        this.zzbvz = zzb4;
        this.zzbwa = zzb3;
        new zzhm(this).zza(zzb, zzb2, zzb4, zzb3, zza.density, this.zzasl.getDefaultDisplay().getRotation());
        return b2;
    }
    
    zzev zzwz() {
        return new zzev() {
            @Override
            public void zza(final zzlt zzlt, final Map<String, String> map) {
                if (map != null) {
                    final String s = map.get("mHeight");
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        try {
                            final int int1 = Integer.parseInt(s);
                            synchronized (zzlx.this.zzakd) {
                                if (zzlx.this.zzcwf != int1) {
                                    zzlx.this.zzcwf = int1;
                                    zzlx.this.requestLayout();
                                }
                            }
                        }
                        catch (Exception ex) {
                            zzb.zzd("Exception occurred while getting webview content mHeight", ex);
                        }
                    }
                }
            }
        };
    }
    
    @zziy
    public static class zza extends MutableContextWrapper
    {
        private Context zzask;
        private Activity zzctd;
        private Context zzcwn;
        
        public zza(final Context baseContext) {
            super(baseContext);
            this.setBaseContext(baseContext);
        }
        
        public Object getSystemService(final String s) {
            return this.zzcwn.getSystemService(s);
        }
        
        public void setBaseContext(final Context zzcwn) {
            this.zzask = zzcwn.getApplicationContext();
            Activity zzctd;
            if (zzcwn instanceof Activity) {
                zzctd = (Activity)zzcwn;
            }
            else {
                zzctd = null;
            }
            this.zzctd = zzctd;
            this.zzcwn = zzcwn;
            super.setBaseContext(this.zzask);
        }
        
        public void startActivity(final Intent intent) {
            if (this.zzctd != null) {
                this.zzctd.startActivity(intent);
            }
            else {
                intent.setFlags(268435456);
                this.zzask.startActivity(intent);
            }
        }
        
        public Activity zzvn() {
            return this.zzctd;
        }
        
        public Context zzvo() {
            return this.zzcwn;
        }
    }
}
