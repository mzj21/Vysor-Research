// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.app.Activity;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.formats.zzg;
import org.json.JSONObject;
import java.util.Map;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.google.android.gms.ads.internal.zzs;

@zziy
public interface zzlt extends zzs, zzck, zzfz
{
    void destroy();
    
    Context getContext();
    
    ViewGroup$LayoutParams getLayoutParams();
    
    void getLocationOnScreen(final int[] p0);
    
    int getMeasuredHeight();
    
    int getMeasuredWidth();
    
    ViewParent getParent();
    
    String getRequestId();
    
    int getRequestedOrientation();
    
    View getView();
    
    WebView getWebView();
    
    boolean isDestroyed();
    
    void loadData(final String p0, final String p1, final String p2);
    
    void loadDataWithBaseURL(final String p0, final String p1, final String p2, final String p3, @Nullable final String p4);
    
    void loadUrl(final String p0);
    
    void measure(final int p0, final int p1);
    
    void onPause();
    
    void onResume();
    
    void setBackgroundColor(final int p0);
    
    void setContext(final Context p0);
    
    void setOnClickListener(final View$OnClickListener p0);
    
    void setOnTouchListener(final View$OnTouchListener p0);
    
    void setRequestedOrientation(final int p0);
    
    void setWebChromeClient(final WebChromeClient p0);
    
    void setWebViewClient(final WebViewClient p0);
    
    void stopLoading();
    
    void zza(final Context p0, final AdSizeParcel p1, final zzdq p2);
    
    void zza(final AdSizeParcel p0);
    
    void zza(final zzly p0);
    
    void zza(final String p0, final Map<String, ?> p1);
    
    void zza(final String p0, final JSONObject p1);
    
    void zzah(final int p0);
    
    void zzaj(final boolean p0);
    
    void zzak(final boolean p0);
    
    void zzal(final boolean p0);
    
    void zzam(final boolean p0);
    
    void zzb(final zzg p0);
    
    void zzb(final zzd p0);
    
    void zzc(final zzd p0);
    
    void zzdg(final String p0);
    
    void zzdh(final String p0);
    
    com.google.android.gms.ads.internal.zzd zzdp();
    
    AdSizeParcel zzdt();
    
    void zzj(final String p0, final String p1);
    
    void zzoz();
    
    boolean zzpr();
    
    void zzvl();
    
    void zzvm();
    
    Activity zzvn();
    
    Context zzvo();
    
    zzd zzvp();
    
    zzd zzvq();
    
    @Nullable
    zzlu zzvr();
    
    boolean zzvs();
    
    zzau zzvt();
    
    VersionInfoParcel zzvu();
    
    boolean zzvv();
    
    void zzvw();
    
    boolean zzvx();
    
    @Nullable
    zzls zzvy();
    
    @Nullable
    zzdo zzvz();
    
    zzdp zzwa();
    
    @Nullable
    zzly zzwb();
    
    boolean zzwc();
    
    void zzwd();
    
    void zzwe();
    
    @Nullable
    View$OnClickListener zzwf();
    
    zzg zzwg();
    
    void zzwh();
}
