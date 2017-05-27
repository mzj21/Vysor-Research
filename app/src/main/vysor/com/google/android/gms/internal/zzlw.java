// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.app.Activity;
import com.google.android.gms.ads.internal.overlay.zzd;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.formats.zzg;
import org.json.JSONObject;
import java.util.Map;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;
import android.content.Context;
import android.webkit.WebView;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.FrameLayout;

@zziy
class zzlw extends FrameLayout implements zzlt
{
    private static final int zzbxe;
    private final zzlt zzcvn;
    private final zzls zzcvo;
    
    static {
        zzbxe = Color.argb(0, 0, 0, 0);
    }
    
    public zzlw(final zzlt zzcvn) {
        super(zzcvn.getContext());
        this.zzcvn = zzcvn;
        this.zzcvo = new zzls(zzcvn.zzvo(), (ViewGroup)this, this);
        final zzlu zzvr = this.zzcvn.zzvr();
        if (zzvr != null) {
            zzvr.zzo(this);
        }
        this.addView(this.zzcvn.getView());
    }
    
    public void destroy() {
        this.zzcvn.destroy();
    }
    
    public String getRequestId() {
        return this.zzcvn.getRequestId();
    }
    
    public int getRequestedOrientation() {
        return this.zzcvn.getRequestedOrientation();
    }
    
    public View getView() {
        return (View)this;
    }
    
    public WebView getWebView() {
        return this.zzcvn.getWebView();
    }
    
    public boolean isDestroyed() {
        return this.zzcvn.isDestroyed();
    }
    
    public void loadData(final String s, final String s2, final String s3) {
        this.zzcvn.loadData(s, s2, s3);
    }
    
    public void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.zzcvn.loadDataWithBaseURL(s, s2, s3, s4, s5);
    }
    
    public void loadUrl(final String s) {
        this.zzcvn.loadUrl(s);
    }
    
    public void onPause() {
        this.zzcvo.onPause();
        this.zzcvn.onPause();
    }
    
    public void onResume() {
        this.zzcvn.onResume();
    }
    
    public void setContext(final Context context) {
        this.zzcvn.setContext(context);
    }
    
    public void setOnClickListener(final View$OnClickListener onClickListener) {
        this.zzcvn.setOnClickListener(onClickListener);
    }
    
    public void setOnTouchListener(final View$OnTouchListener onTouchListener) {
        this.zzcvn.setOnTouchListener(onTouchListener);
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        this.zzcvn.setRequestedOrientation(requestedOrientation);
    }
    
    public void setWebChromeClient(final WebChromeClient webChromeClient) {
        this.zzcvn.setWebChromeClient(webChromeClient);
    }
    
    public void setWebViewClient(final WebViewClient webViewClient) {
        this.zzcvn.setWebViewClient(webViewClient);
    }
    
    public void stopLoading() {
        this.zzcvn.stopLoading();
    }
    
    public void zza(final Context context, final AdSizeParcel adSizeParcel, final zzdq zzdq) {
        this.zzcvo.onDestroy();
        this.zzcvn.zza(context, adSizeParcel, zzdq);
    }
    
    public void zza(final AdSizeParcel adSizeParcel) {
        this.zzcvn.zza(adSizeParcel);
    }
    
    public void zza(final zzcj zzcj, final boolean b) {
        this.zzcvn.zza(zzcj, b);
    }
    
    public void zza(final zzly zzly) {
        this.zzcvn.zza(zzly);
    }
    
    public void zza(final String s, final zzev zzev) {
        this.zzcvn.zza(s, zzev);
    }
    
    public void zza(final String s, final Map<String, ?> map) {
        this.zzcvn.zza(s, map);
    }
    
    public void zza(final String s, final JSONObject jsonObject) {
        this.zzcvn.zza(s, jsonObject);
    }
    
    public void zzah(final int n) {
        this.zzcvn.zzah(n);
    }
    
    public void zzaj(final boolean b) {
        this.zzcvn.zzaj(b);
    }
    
    public void zzak(final boolean b) {
        this.zzcvn.zzak(b);
    }
    
    public void zzal(final boolean b) {
        this.zzcvn.zzal(b);
    }
    
    public void zzam(final boolean b) {
        this.zzcvn.zzam(b);
    }
    
    public void zzb(@Nullable final zzg zzg) {
        this.zzcvn.zzb(zzg);
    }
    
    public void zzb(final zzd zzd) {
        this.zzcvn.zzb(zzd);
    }
    
    public void zzb(final String s, final zzev zzev) {
        this.zzcvn.zzb(s, zzev);
    }
    
    public void zzb(final String s, final JSONObject jsonObject) {
        this.zzcvn.zzb(s, jsonObject);
    }
    
    public void zzc(final zzd zzd) {
        this.zzcvn.zzc(zzd);
    }
    
    public void zzdg(final String s) {
        this.zzcvn.zzdg(s);
    }
    
    public void zzdh(final String s) {
        this.zzcvn.zzdh(s);
    }
    
    public com.google.android.gms.ads.internal.zzd zzdp() {
        return this.zzcvn.zzdp();
    }
    
    public AdSizeParcel zzdt() {
        return this.zzcvn.zzdt();
    }
    
    public void zzel() {
        this.zzcvn.zzel();
    }
    
    public void zzem() {
        this.zzcvn.zzem();
    }
    
    public void zzj(final String s, final String s2) {
        this.zzcvn.zzj(s, s2);
    }
    
    public void zzoz() {
        this.zzcvn.zzoz();
    }
    
    public boolean zzpr() {
        return this.zzcvn.zzpr();
    }
    
    public void zzvl() {
        this.zzcvn.zzvl();
    }
    
    public void zzvm() {
        this.zzcvn.zzvm();
    }
    
    public Activity zzvn() {
        return this.zzcvn.zzvn();
    }
    
    public Context zzvo() {
        return this.zzcvn.zzvo();
    }
    
    public zzd zzvp() {
        return this.zzcvn.zzvp();
    }
    
    public zzd zzvq() {
        return this.zzcvn.zzvq();
    }
    
    public zzlu zzvr() {
        return this.zzcvn.zzvr();
    }
    
    public boolean zzvs() {
        return this.zzcvn.zzvs();
    }
    
    public zzau zzvt() {
        return this.zzcvn.zzvt();
    }
    
    public VersionInfoParcel zzvu() {
        return this.zzcvn.zzvu();
    }
    
    public boolean zzvv() {
        return this.zzcvn.zzvv();
    }
    
    public void zzvw() {
        this.zzcvo.onDestroy();
        this.zzcvn.zzvw();
    }
    
    public boolean zzvx() {
        return this.zzcvn.zzvx();
    }
    
    public zzls zzvy() {
        return this.zzcvo;
    }
    
    public zzdo zzvz() {
        return this.zzcvn.zzvz();
    }
    
    public zzdp zzwa() {
        return this.zzcvn.zzwa();
    }
    
    public zzly zzwb() {
        return this.zzcvn.zzwb();
    }
    
    public boolean zzwc() {
        return this.zzcvn.zzwc();
    }
    
    public void zzwd() {
        this.zzcvn.zzwd();
    }
    
    public void zzwe() {
        this.zzcvn.zzwe();
    }
    
    public View$OnClickListener zzwf() {
        return this.zzcvn.zzwf();
    }
    
    @Nullable
    public zzg zzwg() {
        return this.zzcvn.zzwg();
    }
    
    public void zzwh() {
        this.setBackgroundColor(zzlw.zzbxe);
        this.zzcvn.setBackgroundColor(zzlw.zzbxe);
    }
}
