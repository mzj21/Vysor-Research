// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zzlj;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzkt;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.zzkm;
import android.view.MotionEvent;
import com.google.android.gms.internal.zzku;
import com.google.android.gms.internal.zzkr;
import java.util.Collections;
import com.google.android.gms.dynamic.zze;
import android.content.res.Configuration;
import java.util.Map;
import android.view.ViewParent;
import android.view.Window;
import android.widget.RelativeLayout;
import android.view.ViewGroup;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.ads.internal.safebrowsing.zzc;
import com.google.android.gms.internal.zzhn;
import com.google.android.gms.internal.zzez;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzau;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import android.content.Context;
import java.util.concurrent.Future;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import com.google.android.gms.internal.zzlt;
import android.app.Activity;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzhp;

@zziy
public class zzd extends zzhp.zza implements zzu
{
    static final int zzbxe;
    private final Activity mActivity;
    zzlt zzbkr;
    AdOverlayInfoParcel zzbxf;
    zzc zzbxg;
    zzo zzbxh;
    boolean zzbxi;
    FrameLayout zzbxj;
    WebChromeClient$CustomViewCallback zzbxk;
    boolean zzbxl;
    boolean zzbxm;
    zzb zzbxn;
    boolean zzbxo;
    int zzbxp;
    zzl zzbxq;
    private final Object zzbxr;
    private Runnable zzbxs;
    private boolean zzbxt;
    private boolean zzbxu;
    private boolean zzbxv;
    private boolean zzbxw;
    private boolean zzbxx;
    
    static {
        zzbxe = Color.argb(0, 0, 0, 0);
    }
    
    public zzd(final Activity mActivity) {
        this.zzbxi = false;
        this.zzbxl = false;
        this.zzbxm = false;
        this.zzbxo = false;
        this.zzbxp = 0;
        this.zzbxr = new Object();
        this.zzbxv = false;
        this.zzbxw = false;
        this.zzbxx = true;
        this.mActivity = mActivity;
        this.zzbxq = new zzs();
    }
    
    public void close() {
        this.zzbxp = 2;
        this.mActivity.finish();
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
    }
    
    public void onBackPressed() {
        this.zzbxp = 0;
    }
    
    public void onCreate(final Bundle bundle) {
        this.mActivity.requestWindowFeature(1);
        boolean boolean1 = false;
        if (bundle != null) {
            boolean1 = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzbxl = boolean1;
        Label_0082: {
            try {
                this.zzbxf = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
                if (this.zzbxf == null) {
                    throw new zza("Could not get info for ad overlay.");
                }
                break Label_0082;
            }
            catch (zza zza) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(zza.getMessage());
                this.zzbxp = 3;
                this.mActivity.finish();
            }
            return;
        }
        if (this.zzbxf.zzaqv.zzctt > 7500000) {
            this.zzbxp = 3;
        }
        if (this.mActivity.getIntent() != null) {
            this.zzbxx = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
        }
        if (this.zzbxf.zzbyq != null) {
            this.zzbxm = this.zzbxf.zzbyq.zzanx;
        }
        else {
            this.zzbxm = false;
        }
        if (zzdi.zzbfn.get() && this.zzbxm && this.zzbxf.zzbyq.zzaoc != -1) {
            final Future future = (Future)new zzd().zzqw();
        }
        if (bundle == null) {
            if (this.zzbxf.zzbyg != null && this.zzbxx) {
                this.zzbxf.zzbyg.zzee();
            }
            if (this.zzbxf.zzbyn != 1 && this.zzbxf.zzbyf != null) {
                this.zzbxf.zzbyf.onAdClicked();
            }
        }
        (this.zzbxn = new zzb((Context)this.mActivity, this.zzbxf.zzbyp)).setId(1000);
        switch (this.zzbxf.zzbyn) {
            default: {
                throw new zza("Could not determine ad overlay type.");
            }
            case 1: {
                this.zzab(false);
            }
            case 2: {
                this.zzbxg = new zzc(this.zzbxf.zzbyh);
                this.zzab(false);
            }
            case 3: {
                this.zzab(true);
            }
            case 4: {
                if (this.zzbxl) {
                    this.zzbxp = 3;
                    this.mActivity.finish();
                    return;
                }
                if (!com.google.android.gms.ads.internal.zzu.zzfw().zza((Context)this.mActivity, this.zzbxf.zzbye, this.zzbxf.zzbym)) {
                    this.zzbxp = 3;
                    this.mActivity.finish();
                }
            }
        }
    }
    
    public void onDestroy() {
        if (this.zzbkr != null) {
            this.zzbxn.removeView(this.zzbkr.getView());
        }
        this.zzow();
    }
    
    public void onPause() {
        this.zzbxq.pause();
        this.zzos();
        if (this.zzbxf.zzbyg != null) {
            this.zzbxf.zzbyg.onPause();
        }
        if (this.zzbkr != null && (!this.mActivity.isFinishing() || this.zzbxg == null)) {
            com.google.android.gms.ads.internal.zzu.zzgb().zzl(this.zzbkr);
        }
        this.zzow();
    }
    
    public void onRestart() {
    }
    
    public void onResume() {
        if (this.zzbxf != null && this.zzbxf.zzbyn == 4) {
            if (this.zzbxl) {
                this.zzbxp = 3;
                this.mActivity.finish();
            }
            else {
                this.zzbxl = true;
            }
        }
        if (this.zzbxf.zzbyg != null) {
            this.zzbxf.zzbyg.onResume();
        }
        if (this.zzbkr != null && !this.zzbkr.isDestroyed()) {
            com.google.android.gms.ads.internal.zzu.zzgb().zzm(this.zzbkr);
        }
        else {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("The webview does not exit. Ignoring action.");
        }
        this.zzbxq.resume();
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbxl);
    }
    
    public void onStart() {
    }
    
    public void onStop() {
        this.zzow();
    }
    
    public void setRequestedOrientation(final int requestedOrientation) {
        this.mActivity.setRequestedOrientation(requestedOrientation);
    }
    
    public void zza(final View view, final WebChromeClient$CustomViewCallback zzbxk) {
        (this.zzbxj = new FrameLayout((Context)this.mActivity)).setBackgroundColor(-16777216);
        this.zzbxj.addView(view, -1, -1);
        this.mActivity.setContentView((View)this.zzbxj);
        this.zzdf();
        this.zzbxk = zzbxk;
        this.zzbxi = true;
    }
    
    public void zza(final boolean b, final boolean b2) {
        if (this.zzbxh != null) {
            this.zzbxh.zza(b, b2);
        }
    }
    
    public void zzaa(final boolean b) {
        int n;
        if (b) {
            n = 50;
        }
        else {
            n = 32;
        }
        this.zzbxh = new zzo((Context)this.mActivity, n, this);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        relativeLayout$LayoutParams.addRule(10);
        int n2;
        if (b) {
            n2 = 11;
        }
        else {
            n2 = 9;
        }
        relativeLayout$LayoutParams.addRule(n2);
        this.zzbxh.zza(b, this.zzbxf.zzbyk);
        this.zzbxn.addView((View)this.zzbxh, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    protected void zzab(final boolean b) throws zza {
        if (!this.zzbxu) {
            this.mActivity.requestWindowFeature(1);
        }
        final Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zza("Invalid activity, no window available.");
        }
        final boolean b2 = !com.google.android.gms.common.util.zzs.zzaxw() || !zzdi.zzbhx.get() || com.google.android.gms.ads.internal.zzu.zzfz().zza(this.mActivity, this.mActivity.getResources().getConfiguration());
        boolean b3;
        if (this.zzbxf.zzbyq != null && this.zzbxf.zzbyq.zzany) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        if ((!this.zzbxm || b3) && b2) {
            window.setFlags(1024, 1024);
        }
        final zzlu zzvr = this.zzbxf.zzbyh.zzvr();
        final boolean b4 = zzvr != null && zzvr.zzib();
        this.zzbxo = false;
        if (b4) {
            if (this.zzbxf.orientation == com.google.android.gms.ads.internal.zzu.zzgb().zzum()) {
                this.zzbxo = (this.mActivity.getResources().getConfiguration().orientation == 1);
            }
            else if (this.zzbxf.orientation == com.google.android.gms.ads.internal.zzu.zzgb().zzun()) {
                this.zzbxo = (this.mActivity.getResources().getConfiguration().orientation == 2);
            }
        }
        com.google.android.gms.ads.internal.util.client.zzb.zzdd(new StringBuilder(46).append("Delay onShow to next orientation change: ").append(this.zzbxo).toString());
        this.setRequestedOrientation(this.zzbxf.orientation);
        if (com.google.android.gms.ads.internal.zzu.zzgb().zza(window)) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Hardware acceleration on the AdActivity window enabled.");
        }
        if (!this.zzbxm) {
            this.zzbxn.setBackgroundColor(-16777216);
        }
        else {
            this.zzbxn.setBackgroundColor(zzd.zzbxe);
        }
        this.mActivity.setContentView((View)this.zzbxn);
        this.zzdf();
        if (b) {
            this.zzbkr = com.google.android.gms.ads.internal.zzu.zzga().zza((Context)this.mActivity, this.zzbxf.zzbyh.zzdt(), true, b4, null, this.zzbxf.zzaqv, null, null, this.zzbxf.zzbyh.zzdp());
            this.zzbkr.zzvr().zza(null, null, this.zzbxf.zzbyi, this.zzbxf.zzbym, true, this.zzbxf.zzbyo, null, this.zzbxf.zzbyh.zzvr().zzwi(), null, null);
            this.zzbkr.zzvr().zza((zzlu.zza)new zzlu.zza() {
                @Override
                public void zza(final zzlt zzlt, final boolean b) {
                    zzlt.zzoz();
                }
            });
            if (this.zzbxf.url != null) {
                this.zzbkr.loadUrl(this.zzbxf.url);
            }
            else {
                if (this.zzbxf.zzbyl == null) {
                    throw new zza("No URL or HTML to display in ad overlay.");
                }
                this.zzbkr.loadDataWithBaseURL(this.zzbxf.zzbyj, this.zzbxf.zzbyl, "text/html", "UTF-8", null);
            }
            if (this.zzbxf.zzbyh != null) {
                this.zzbxf.zzbyh.zzc(this);
            }
        }
        else {
            (this.zzbkr = this.zzbxf.zzbyh).setContext((Context)this.mActivity);
        }
        this.zzbkr.zzb(this);
        final ViewParent parent = this.zzbkr.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView(this.zzbkr.getView());
        }
        if (this.zzbxm) {
            this.zzbkr.zzwh();
        }
        this.zzbxn.addView(this.zzbkr.getView(), -1, -1);
        if (!b && !this.zzbxo) {
            this.zzoz();
        }
        this.zzaa(b4);
        if (this.zzbkr.zzvs()) {
            this.zza(b4, true);
        }
        final zzd zzdp = this.zzbkr.zzdp();
        zzm zzame;
        if (zzdp != null) {
            zzame = zzdp.zzame;
        }
        else {
            zzame = null;
        }
        if (zzame != null) {
            this.zzbxq = zzame.zza(this.mActivity, this.zzbkr, this.zzbxn);
        }
        else {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Appstreaming controller is null.");
        }
    }
    
    protected void zzah(final int n) {
        this.zzbkr.zzah(n);
    }
    
    public void zzdf() {
        this.zzbxu = true;
    }
    
    public void zzf(final zzlt zzlt, final Map<String, String> map) {
        this.zzbxq.zzf(zzlt, map);
    }
    
    public void zzn(final com.google.android.gms.dynamic.zzd zzd) {
        if (zzdi.zzbhx.get() && com.google.android.gms.common.util.zzs.zzaxw()) {
            if (com.google.android.gms.ads.internal.zzu.zzfz().zza(this.mActivity, zze.zzae(zzd))) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
            }
            else {
                this.mActivity.getWindow().addFlags(2048);
                this.mActivity.getWindow().clearFlags(1024);
            }
        }
    }
    
    public void zzos() {
        if (this.zzbxf != null && this.zzbxi) {
            this.setRequestedOrientation(this.zzbxf.orientation);
        }
        if (this.zzbxj != null) {
            this.mActivity.setContentView((View)this.zzbxn);
            this.zzdf();
            this.zzbxj.removeAllViews();
            this.zzbxj = null;
        }
        if (this.zzbxk != null) {
            this.zzbxk.onCustomViewHidden();
            this.zzbxk = null;
        }
        this.zzbxi = false;
    }
    
    @Override
    public void zzot() {
        this.zzbxp = 1;
        this.mActivity.finish();
    }
    
    public boolean zzou() {
        boolean b = true;
        this.zzbxp = 0;
        if (this.zzbkr != null) {
            if (!this.zzbkr.zzpr() || !this.zzbxq.zzpr()) {
                b = false;
            }
            if (!b) {
                this.zzbkr.zza("onbackblocked", Collections.emptyMap());
            }
        }
        return b;
    }
    
    public void zzov() {
        this.zzbxn.removeView((View)this.zzbxh);
        this.zzaa(true);
    }
    
    protected void zzow() {
        if (this.mActivity.isFinishing() && !this.zzbxv) {
            this.zzbxv = true;
            if (this.zzbkr != null) {
                this.zzah(this.zzbxp);
                synchronized (this.zzbxr) {
                    if (!this.zzbxt && this.zzbkr.zzwc()) {
                        this.zzbxs = new Runnable() {
                            @Override
                            public void run() {
                                zzd.this.zzox();
                            }
                        };
                        zzkr.zzcrf.postDelayed(this.zzbxs, (long)zzdi.zzbdh.get());
                        return;
                    }
                }
            }
            // monitorexit(o)
            this.zzox();
        }
    }
    
    void zzox() {
        if (!this.zzbxw) {
            this.zzbxw = true;
            if (this.zzbkr != null) {
                this.zzbxn.removeView(this.zzbkr.getView());
                if (this.zzbxg != null) {
                    this.zzbkr.setContext(this.zzbxg.zzahn);
                    this.zzbkr.zzaj(false);
                    this.zzbxg.zzbyb.addView(this.zzbkr.getView(), this.zzbxg.index, this.zzbxg.zzbya);
                    this.zzbxg = null;
                }
                else if (this.mActivity.getApplicationContext() != null) {
                    this.zzbkr.setContext(this.mActivity.getApplicationContext());
                }
                this.zzbkr = null;
            }
            if (this.zzbxf != null && this.zzbxf.zzbyg != null) {
                this.zzbxf.zzbyg.zzed();
            }
            this.zzbxq.destroy();
        }
    }
    
    public void zzoy() {
        if (this.zzbxo) {
            this.zzbxo = false;
            this.zzoz();
        }
    }
    
    protected void zzoz() {
        this.zzbkr.zzoz();
    }
    
    public void zzpa() {
        this.zzbxn.disable();
    }
    
    public void zzpb() {
        synchronized (this.zzbxr) {
            this.zzbxt = true;
            if (this.zzbxs != null) {
                zzkr.zzcrf.removeCallbacks(this.zzbxs);
                zzkr.zzcrf.post(this.zzbxs);
            }
        }
    }
    
    @zziy
    private static final class zza extends Exception
    {
        public zza(final String s) {
            super(s);
        }
    }
    
    @zziy
    static class zzb extends RelativeLayout
    {
        zzku zzase;
        boolean zzbxz;
        
        public zzb(final Context context, final String s) {
            super(context);
            this.zzase = new zzku(context, s);
        }
        
        void disable() {
            this.zzbxz = true;
        }
        
        public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
            if (!this.zzbxz) {
                this.zzase.zzg(motionEvent);
            }
            return false;
        }
    }
    
    @zziy
    public static class zzc
    {
        public final int index;
        public final Context zzahn;
        public final ViewGroup$LayoutParams zzbya;
        public final ViewGroup zzbyb;
        
        public zzc(final zzlt zzlt) throws zza {
            this.zzbya = zzlt.getLayoutParams();
            final ViewParent parent = zzlt.getParent();
            this.zzahn = zzlt.zzvo();
            if (parent != null && parent instanceof ViewGroup) {
                this.zzbyb = (ViewGroup)parent;
                this.index = this.zzbyb.indexOfChild(zzlt.getView());
                this.zzbyb.removeView(zzlt.getView());
                zzlt.zzaj(true);
                return;
            }
            throw new zza("Could not get the parent of the WebView for an overlay.");
        }
    }
    
    @zziy
    private class zzd extends zzkm
    {
        @Override
        public void onStop() {
        }
        
        @Override
        public void zzfc() {
            final Bitmap zza = com.google.android.gms.ads.internal.zzu.zzgu().zza(com.google.android.gms.ads.internal.overlay.zzd.this.zzbxf.zzbyq.zzaoc);
            if (zza != null) {
                zzkr.zzcrf.post((Runnable)new Runnable() {
                    final /* synthetic */ Drawable zzbyc = com.google.android.gms.ads.internal.zzu.zzgb().zza((Context)com.google.android.gms.ads.internal.overlay.zzd.this.mActivity, zza, com.google.android.gms.ads.internal.overlay.zzd.this.zzbxf.zzbyq.zzaoa, com.google.android.gms.ads.internal.overlay.zzd.this.zzbxf.zzbyq.zzaob);
                    
                    @Override
                    public void run() {
                        com.google.android.gms.ads.internal.overlay.zzd.this.mActivity.getWindow().setBackgroundDrawable(this.zzbyc);
                    }
                });
            }
        }
    }
}
