// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import android.graphics.Canvas;
import android.view.View$MeasureSpec;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.os.AsyncTask;
import com.google.android.gms.ads.internal.zzu;
import android.text.TextUtils;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import android.os.Looper;
import android.os.Handler;

@zziy
public class zzii implements Runnable
{
    private final int zzajw;
    private final int zzajx;
    protected final zzlt zzbkr;
    private final Handler zzccq;
    private final long zzccr;
    private long zzccs;
    private zzlu.zza zzcct;
    protected boolean zzccu;
    protected boolean zzccv;
    
    public zzii(final zzlu.zza zza, final zzlt zzlt, final int n, final int n2) {
        this(zza, zzlt, n, n2, 200L, 50L);
    }
    
    public zzii(final zzlu.zza zzcct, final zzlt zzbkr, final int zzajw, final int zzajx, final long zzccr, final long zzccs) {
        this.zzccr = zzccr;
        this.zzccs = zzccs;
        this.zzccq = new Handler(Looper.getMainLooper());
        this.zzbkr = zzbkr;
        this.zzcct = zzcct;
        this.zzccu = false;
        this.zzccv = false;
        this.zzajx = zzajx;
        this.zzajw = zzajw;
    }
    
    @Override
    public void run() {
        if (this.zzbkr == null || this.zzqz()) {
            this.zzcct.zza(this.zzbkr, true);
        }
        else {
            new zza(this.zzbkr.getWebView()).execute((Object[])new Void[0]);
        }
    }
    
    public void zza(final AdResponseParcel adResponseParcel) {
        this.zza(adResponseParcel, new zzme(this, this.zzbkr, adResponseParcel.zzchj));
    }
    
    public void zza(final AdResponseParcel adResponseParcel, final zzme webViewClient) {
        this.zzbkr.setWebViewClient(webViewClient);
        final zzlt zzbkr = this.zzbkr;
        String zzcv;
        if (TextUtils.isEmpty((CharSequence)adResponseParcel.zzbyj)) {
            zzcv = null;
        }
        else {
            zzcv = zzu.zzfz().zzcv(adResponseParcel.zzbyj);
        }
        zzbkr.loadDataWithBaseURL(zzcv, adResponseParcel.body, "text/html", "UTF-8", null);
    }
    
    public void zzqx() {
        this.zzccq.postDelayed((Runnable)this, this.zzccr);
    }
    
    public void zzqy() {
        synchronized (this) {
            this.zzccu = true;
        }
    }
    
    public boolean zzqz() {
        synchronized (this) {
            return this.zzccu;
        }
    }
    
    public boolean zzra() {
        return this.zzccv;
    }
    
    protected final class zza extends AsyncTask<Void, Void, Boolean>
    {
        private final WebView zzccw;
        private Bitmap zzccx;
        
        public zza(final WebView zzccw) {
            this.zzccw = zzccw;
        }
        
        protected void onPreExecute() {
            synchronized (this) {
                this.zzccx = Bitmap.createBitmap(zzii.this.zzajw, zzii.this.zzajx, Bitmap$Config.ARGB_8888);
                this.zzccw.setVisibility(0);
                this.zzccw.measure(View$MeasureSpec.makeMeasureSpec(zzii.this.zzajw, 0), View$MeasureSpec.makeMeasureSpec(zzii.this.zzajx, 0));
                this.zzccw.layout(0, 0, zzii.this.zzajw, zzii.this.zzajx);
                this.zzccw.draw(new Canvas(this.zzccx));
                this.zzccw.invalidate();
            }
        }
        
        protected Boolean zza(final Void... array) {
            while (true) {
            Label_0058_Outer:
                while (true) {
                    int n = 0;
                Label_0139:
                    while (true) {
                        int n3 = 0;
                        Label_0133: {
                            synchronized (this) {
                                final int width = this.zzccx.getWidth();
                                final int height = this.zzccx.getHeight();
                                Boolean b;
                                if (width == 0 || height == 0) {
                                    b = false;
                                }
                                else {
                                    n = 0;
                                    int n2 = 0;
                                    if (n < width) {
                                        n3 = 0;
                                        if (n3 >= height) {
                                            break Label_0139;
                                        }
                                        if (this.zzccx.getPixel(n, n3) != 0) {
                                            ++n2;
                                        }
                                        break Label_0133;
                                    }
                                    else {
                                        b = (n2 / (width * height / 100.0) > 0.1);
                                    }
                                }
                                return b;
                            }
                        }
                        n3 += 10;
                        continue;
                    }
                    n += 10;
                    continue Label_0058_Outer;
                }
            }
        }
        
        protected void zza(final Boolean b) {
            --zzii.this.zzccs;
            if (b || zzii.this.zzqz() || zzii.this.zzccs <= 0L) {
                zzii.this.zzccv = b;
                zzii.this.zzcct.zza(zzii.this.zzbkr, true);
            }
            else if (zzii.this.zzccs > 0L) {
                if (zzb.zzbf(2)) {
                    zzb.zzdd("Ad not detected, scheduling another run.");
                }
                zzii.this.zzccq.postDelayed((Runnable)zzii.this, zzii.this.zzccr);
            }
        }
    }
}
