// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.renderscript.Allocation;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.graphics.Paint;
import java.util.concurrent.Callable;
import android.view.ViewGroup$LayoutParams;
import android.webkit.WebChromeClient;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.Set;
import android.net.Uri;
import android.app.Activity;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.webkit.CookieSyncManager;
import android.webkit.CookieManager;
import android.view.Window;
import android.webkit.WebSettings;
import android.app.DownloadManager$Request;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.net.http.SslError;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.view.View;
import android.content.Context;
import android.annotation.TargetApi;

@zziy
@TargetApi(8)
public class zzks
{
    public static zzks zzbe(final int n) {
        zzks zzks;
        if (n >= 21) {
            zzks = new zzh();
        }
        else if (n >= 19) {
            zzks = new zzg();
        }
        else if (n >= 18) {
            zzks = new zze();
        }
        else if (n >= 17) {
            zzks = new zzd();
        }
        else if (n >= 16) {
            zzks = new zzf();
        }
        else if (n >= 14) {
            zzks = new zzc();
        }
        else if (n >= 11) {
            zzks = new zzb();
        }
        else if (n >= 9) {
            zzks = new zza();
        }
        else {
            zzks = new zzks();
        }
        return zzks;
    }
    
    public String getDefaultUserAgent(final Context context) {
        return "";
    }
    
    public boolean isAttachedToWindow(final View view) {
        return view.getWindowToken() != null || view.getWindowVisibility() != 8;
    }
    
    public Drawable zza(final Context context, final Bitmap bitmap, final boolean b, final float n) {
        return (Drawable)new BitmapDrawable(context.getResources(), bitmap);
    }
    
    public String zza(final SslError sslError) {
        return "";
    }
    
    public void zza(final View view, final Drawable backgroundDrawable) {
        view.setBackgroundDrawable(backgroundDrawable);
    }
    
    public void zza(final ViewTreeObserver viewTreeObserver, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        viewTreeObserver.removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
    }
    
    public boolean zza(final DownloadManager$Request downloadManager$Request) {
        return false;
    }
    
    public boolean zza(final Context context, final WebSettings webSettings) {
        return false;
    }
    
    public boolean zza(final Window window) {
        return false;
    }
    
    public CookieManager zzao(final Context context) {
        try {
            CookieSyncManager.createInstance(context);
            return CookieManager.getInstance();
        }
        catch (Exception ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Failed to obtain CookieManager.", ex);
            zzu.zzgd().zza(ex, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }
    
    public zzlu zzb(final zzlt zzlt, final boolean b) {
        return new zzlu(zzlt, b);
    }
    
    public void zzb(final Activity activity, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            this.zza(window.getDecorView().getViewTreeObserver(), viewTreeObserver$OnGlobalLayoutListener);
        }
    }
    
    public Set<String> zzh(final Uri uri) {
        Set<String> set;
        if (uri.isOpaque()) {
            set = Collections.emptySet();
        }
        else {
            final String encodedQuery = uri.getEncodedQuery();
            if (encodedQuery == null) {
                set = Collections.emptySet();
            }
            else {
                final LinkedHashSet<String> set2 = new LinkedHashSet<String>();
                int i = 0;
                do {
                    int n = encodedQuery.indexOf(38, i);
                    if (n == -1) {
                        n = encodedQuery.length();
                    }
                    int index = encodedQuery.indexOf(61, i);
                    if (index > n || index == -1) {
                        index = n;
                    }
                    set2.add(Uri.decode(encodedQuery.substring(i, index)));
                    i = n + 1;
                } while (i < encodedQuery.length());
                set = (Set<String>)Collections.unmodifiableSet((Set<?>)set2);
            }
        }
        return set;
    }
    
    public boolean zzl(final zzlt zzlt) {
        boolean b;
        if (zzlt == null) {
            b = false;
        }
        else {
            zzlt.onPause();
            b = true;
        }
        return b;
    }
    
    public boolean zzm(final zzlt zzlt) {
        boolean b;
        if (zzlt == null) {
            b = false;
        }
        else {
            zzlt.onResume();
            b = true;
        }
        return b;
    }
    
    public WebChromeClient zzn(final zzlt zzlt) {
        return null;
    }
    
    public boolean zzo(final View view) {
        return false;
    }
    
    public boolean zzp(final View view) {
        return false;
    }
    
    public int zzum() {
        return 0;
    }
    
    public int zzun() {
        return 1;
    }
    
    public int zzuo() {
        return 5;
    }
    
    public ViewGroup$LayoutParams zzup() {
        return new ViewGroup$LayoutParams(-2, -2);
    }
    
    @TargetApi(9)
    public static class zza extends zzks
    {
        public zza() {
            super(null);
        }
        
        @Override
        public boolean zza(final DownloadManager$Request downloadManager$Request) {
            downloadManager$Request.setShowRunningNotification(true);
            return true;
        }
        
        @Override
        public int zzum() {
            return 6;
        }
        
        @Override
        public int zzun() {
            return 7;
        }
    }
    
    @TargetApi(11)
    public static class zzb extends zza
    {
        @Override
        public boolean zza(final DownloadManager$Request downloadManager$Request) {
            downloadManager$Request.allowScanningByMediaScanner();
            downloadManager$Request.setNotificationVisibility(1);
            return true;
        }
        
        @Override
        public boolean zza(final Context context, final WebSettings webSettings) {
            super.zza(context, webSettings);
            return zzle.zzb((Callable<Boolean>)new Callable<Boolean>() {
                public Boolean zzuq() {
                    if (context.getCacheDir() != null) {
                        webSettings.setAppCachePath(context.getCacheDir().getAbsolutePath());
                        webSettings.setAppCacheMaxSize(0L);
                        webSettings.setAppCacheEnabled(true);
                    }
                    webSettings.setDatabasePath(context.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
                    webSettings.setDatabaseEnabled(true);
                    webSettings.setDomStorageEnabled(true);
                    webSettings.setDisplayZoomControls(false);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setSupportZoom(true);
                    webSettings.setAllowContentAccess(false);
                    return true;
                }
            });
        }
        
        @Override
        public boolean zza(final Window window) {
            window.setFlags(16777216, 16777216);
            return true;
        }
        
        @Override
        public zzlu zzb(final zzlt zzlt, final boolean b) {
            return new zzmb(zzlt, b);
        }
        
        @Override
        public Set<String> zzh(final Uri uri) {
            return (Set<String>)uri.getQueryParameterNames();
        }
        
        @Override
        public WebChromeClient zzn(final zzlt zzlt) {
            return new zzma(zzlt);
        }
        
        @Override
        public boolean zzo(final View view) {
            view.setLayerType(0, (Paint)null);
            return true;
        }
        
        @Override
        public boolean zzp(final View view) {
            view.setLayerType(1, (Paint)null);
            return true;
        }
    }
    
    @TargetApi(14)
    public static class zzc extends zzb
    {
        @Override
        public String zza(final SslError sslError) {
            return sslError.getUrl();
        }
        
        @Override
        public WebChromeClient zzn(final zzlt zzlt) {
            return new zzmc(zzlt);
        }
    }
    
    @TargetApi(17)
    public static class zzd extends zzf
    {
        @Override
        public String getDefaultUserAgent(final Context context) {
            return WebSettings.getDefaultUserAgent(context);
        }
        
        @Override
        public Drawable zza(final Context context, final Bitmap bitmap, final boolean b, final float radius) {
            BitmapDrawable bitmapDrawable;
            if (!b || radius <= 0.0f || radius > 25.0f) {
                bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
            }
            else {
                try {
                    final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
                    final Bitmap bitmap2 = Bitmap.createBitmap(scaledBitmap);
                    final RenderScript create = RenderScript.create(context);
                    final ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
                    final Allocation fromBitmap = Allocation.createFromBitmap(create, scaledBitmap);
                    final Allocation fromBitmap2 = Allocation.createFromBitmap(create, bitmap2);
                    create2.setRadius(radius);
                    create2.setInput(fromBitmap);
                    create2.forEach(fromBitmap2);
                    fromBitmap2.copyTo(bitmap2);
                    bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap2);
                }
                catch (RuntimeException ex) {
                    bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
                }
            }
            return (Drawable)bitmapDrawable;
        }
        
        @Override
        public boolean zza(final Context context, final WebSettings webSettings) {
            super.zza(context, webSettings);
            webSettings.setMediaPlaybackRequiresUserGesture(false);
            return true;
        }
    }
    
    @TargetApi(18)
    public static class zze extends zzd
    {
        @Override
        public boolean isAttachedToWindow(final View view) {
            return super.isAttachedToWindow(view) || view.getWindowId() != null;
        }
        
        @Override
        public int zzuo() {
            return 14;
        }
    }
    
    @TargetApi(16)
    public static class zzf extends zzc
    {
        @Override
        public void zza(final View view, final Drawable background) {
            view.setBackground(background);
        }
        
        @Override
        public void zza(final ViewTreeObserver viewTreeObserver, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
            viewTreeObserver.removeOnGlobalLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
        }
        
        @Override
        public boolean zza(final Context context, final WebSettings webSettings) {
            super.zza(context, webSettings);
            webSettings.setAllowFileAccessFromFileURLs(false);
            webSettings.setAllowUniversalAccessFromFileURLs(false);
            return true;
        }
        
        @Override
        public void zzb(final Activity activity, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
            final Window window = activity.getWindow();
            if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
                this.zza(window.getDecorView().getViewTreeObserver(), viewTreeObserver$OnGlobalLayoutListener);
            }
        }
    }
    
    @TargetApi(19)
    public static class zzg extends zze
    {
        @Override
        public boolean isAttachedToWindow(final View view) {
            return view.isAttachedToWindow();
        }
        
        @Override
        public ViewGroup$LayoutParams zzup() {
            return new ViewGroup$LayoutParams(-1, -1);
        }
    }
    
    @TargetApi(21)
    public static class zzh extends zzg
    {
        @Override
        public CookieManager zzao(final Context context) {
            return CookieManager.getInstance();
        }
        
        @Override
        public zzlu zzb(final zzlt zzlt, final boolean b) {
            return new zzmf(zzlt, b);
        }
    }
}
