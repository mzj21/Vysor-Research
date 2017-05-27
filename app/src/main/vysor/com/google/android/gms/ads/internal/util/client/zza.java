// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import android.os.Build;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.Locale;
import java.security.MessageDigest;
import java.util.StringTokenizer;
import android.annotation.TargetApi;
import android.view.Display;
import com.google.android.gms.common.util.zzs;
import android.view.WindowManager;
import android.content.ContentResolver;
import android.provider.Settings$Secure;
import java.net.HttpURLConnection;
import java.util.Iterator;
import android.net.Uri$Builder;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.util.DisplayMetrics;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import android.os.Looper;
import android.os.Handler;
import com.google.android.gms.internal.zziy;

@zziy
public class zza
{
    public static final Handler zzctj;
    private static final String zzctk;
    private static final String zzctl;
    private static final String zzctm;
    private static final String zzctn;
    private static final String zzcto;
    private static final String zzctp;
    
    static {
        zzctj = new Handler(Looper.getMainLooper());
        zzctk = AdView.class.getName();
        zzctl = InterstitialAd.class.getName();
        zzctm = PublisherAdView.class.getName();
        zzctn = PublisherInterstitialAd.class.getName();
        zzcto = SearchAdView.class.getName();
        zzctp = AdLoader.class.getName();
    }
    
    private void zza(final ViewGroup viewGroup, final AdSizeParcel adSizeParcel, final String text, final int n, final int backgroundColor) {
        if (viewGroup.getChildCount() == 0) {
            final Context context = viewGroup.getContext();
            final TextView textView = new TextView(context);
            textView.setGravity(17);
            textView.setText((CharSequence)text);
            textView.setTextColor(n);
            textView.setBackgroundColor(backgroundColor);
            final FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(n);
            final int zzb = this.zzb(context, 3);
            frameLayout.addView((View)textView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(adSizeParcel.widthPixels - zzb, adSizeParcel.heightPixels - zzb, 17));
            viewGroup.addView((View)frameLayout, adSizeParcel.widthPixels, adSizeParcel.heightPixels);
        }
    }
    
    public int zza(final DisplayMetrics displayMetrics, final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, displayMetrics);
    }
    
    @Nullable
    public String zza(final StackTraceElement[] array, final String s) {
        while (true) {
            for (int n = 0; n + 1 < array.length; ++n) {
                final StackTraceElement stackTraceElement = array[n];
                final String className = stackTraceElement.getClassName();
                if ("loadAd".equalsIgnoreCase(stackTraceElement.getMethodName()) && (zza.zzctk.equalsIgnoreCase(className) || zza.zzctl.equalsIgnoreCase(className) || zza.zzctm.equalsIgnoreCase(className) || zza.zzctn.equalsIgnoreCase(className) || zza.zzcto.equalsIgnoreCase(className) || zza.zzctp.equalsIgnoreCase(className))) {
                    String className2 = array[n + 1].getClassName();
                    if (s == null) {
                        return null;
                    }
                    final String zzb = this.zzb(s, ".", 3);
                    if (className2 == null || className2.contains(zzb)) {
                        return null;
                    }
                    return className2;
                    className2 = null;
                    return className2;
                }
            }
            String className2 = null;
            continue;
        }
    }
    
    public void zza(final Context context, @Nullable final String s, final String s2, final Bundle bundle, final boolean b) {
        this.zza(context, s, s2, bundle, b, (zza)new zza() {
            @Override
            public void zzcy(final String s) {
                new Thread() {
                    @Override
                    public void run() {
                        new zzc().zzcy(s);
                    }
                }.start();
            }
        });
    }
    
    public void zza(final Context context, @Nullable String string, final String s, final Bundle bundle, final boolean b, final zza zza) {
        if (b) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            bundle.putString("os", Build$VERSION.RELEASE);
            bundle.putString("api", String.valueOf(Build$VERSION.SDK_INT));
            bundle.putString("appid", applicationContext.getPackageName());
            if (string == null) {
                string = new StringBuilder(23).append(com.google.android.gms.common.zzc.zzapd().zzbo(context)).append(".").append(9683000).toString();
            }
            bundle.putString("js", string);
        }
        final Uri$Builder appendQueryParameter = new Uri$Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", s);
        for (final String s2 : bundle.keySet()) {
            appendQueryParameter.appendQueryParameter(s2, bundle.getString(s2));
        }
        zza.zzcy(appendQueryParameter.toString());
    }
    
    public void zza(final ViewGroup viewGroup, final AdSizeParcel adSizeParcel, final String s) {
        this.zza(viewGroup, adSizeParcel, s, -16777216, -1);
    }
    
    public void zza(final ViewGroup viewGroup, final AdSizeParcel adSizeParcel, final String s, final String s2) {
        zzb.zzdf(s2);
        this.zza(viewGroup, adSizeParcel, s, -65536, -16777216);
    }
    
    public void zza(final boolean instanceFollowRedirects, final HttpURLConnection httpURLConnection, @Nullable final String s) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(instanceFollowRedirects);
        httpURLConnection.setReadTimeout(60000);
        if (s != null) {
            httpURLConnection.setRequestProperty("User-Agent", s);
        }
        httpURLConnection.setUseCaches(false);
    }
    
    public String zzar(final Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        String string;
        if (contentResolver == null) {
            string = null;
        }
        else {
            string = Settings$Secure.getString(contentResolver, "android_id");
        }
        if (string == null || this.zzve()) {
            string = "emulator";
        }
        return this.zzdc(string);
    }
    
    public boolean zzas(final Context context) {
        return com.google.android.gms.common.zzc.zzapd().isGooglePlayServicesAvailable(context) == 0;
    }
    
    public boolean zzat(final Context context) {
        final int orientation = context.getResources().getConfiguration().orientation;
        boolean b = false;
        if (orientation == 2) {
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            final int n = (int)(displayMetrics.heightPixels / displayMetrics.density);
            b = false;
            if (n < 600) {
                b = true;
            }
        }
        return b;
    }
    
    @TargetApi(17)
    public boolean zzau(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        Label_0088: {
            if (!zzs.zzaxp()) {
                break Label_0088;
            }
            defaultDisplay.getRealMetrics(displayMetrics);
            int n = displayMetrics.heightPixels;
            int n2 = displayMetrics.widthPixels;
            while (true) {
                defaultDisplay.getMetrics(displayMetrics);
                final int heightPixels = displayMetrics.heightPixels;
                final int widthPixels = displayMetrics.widthPixels;
                Label_0153: {
                    if (heightPixels != n || widthPixels != n2) {
                        break Label_0153;
                    }
                    boolean b = true;
                    return b;
                    try {
                        n = (int)Display.class.getMethod("getRawHeight", (Class<?>[])new Class[0]).invoke(defaultDisplay, new Object[0]);
                        n2 = (int)Display.class.getMethod("getRawWidth", (Class<?>[])new Class[0]).invoke(defaultDisplay, new Object[0]);
                        continue;
                        b = false;
                        return b;
                    }
                    catch (Exception ex) {
                        b2 = false;
                        return b2;
                    }
                }
                break;
            }
        }
    }
    
    public int zzav(final Context context) {
        final int identifier = context.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
        int dimensionPixelSize;
        if (identifier > 0) {
            dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
        }
        else {
            dimensionPixelSize = 0;
        }
        return dimensionPixelSize;
    }
    
    public int zzb(final Context context, final int n) {
        return this.zza(context.getResources().getDisplayMetrics(), n);
    }
    
    public int zzb(final DisplayMetrics displayMetrics, final int n) {
        return Math.round(n / displayMetrics.density);
    }
    
    String zzb(String string, final String s, final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(string, s);
        final StringBuilder sb = new StringBuilder();
        int n2 = n - 1;
        if (n > 0 && stringTokenizer.hasMoreElements()) {
            sb.append(stringTokenizer.nextToken());
            while (true) {
                final int n3 = n2 - 1;
                if (n2 <= 0 || !stringTokenizer.hasMoreElements()) {
                    break;
                }
                sb.append(".").append(stringTokenizer.nextToken());
                n2 = n3;
            }
            string = sb.toString();
        }
        return string;
    }
    
    public int zzc(final Context context, final int n) {
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return this.zzb(displayMetrics, n);
    }
    
    public String zzdc(final String s) {
        int n = 0;
        while (true) {
            if (n >= 2) {
                return null;
            }
            try {
                final MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(s.getBytes());
                return String.format(Locale.US, "%032X", new BigInteger(1, instance.digest()));
            }
            catch (NoSuchAlgorithmException ex) {
                ++n;
                continue;
            }
            return null;
        }
    }
    
    public boolean zzve() {
        return Build.DEVICE.startsWith("generic");
    }
    
    public boolean zzvf() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
    
    public interface zza
    {
        void zzcy(final String p0);
    }
}
