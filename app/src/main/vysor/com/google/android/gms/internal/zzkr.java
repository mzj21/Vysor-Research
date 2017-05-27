// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcelable;
import android.os.Debug;
import android.os.Debug$MemoryInfo;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.UUID;
import android.os.Build;
import java.util.Locale;
import android.os.Build$VERSION;
import android.widget.AdapterView;
import java.io.InputStream;
import com.google.android.gms.ads.internal.zzo;
import java.io.FileOutputStream;
import java.util.ArrayList;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.ClientApi;
import android.content.pm.ApplicationInfo;
import android.media.AudioManager;
import android.os.Process;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager$RunningTaskInfo;
import android.app.ActivityManager;
import android.app.AlertDialog$Builder;
import android.webkit.WebView;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import java.net.HttpURLConnection;
import java.util.concurrent.Future;
import java.util.List;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.client.zzm;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.util.zzs;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.Window;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.app.Activity;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import android.net.Uri;
import android.view.ViewParent;
import android.view.ViewGroup;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.widget.PopupWindow;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;
import android.content.Context;
import android.os.PowerManager;
import android.app.KeyguardManager;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.Map;
import android.os.Bundle;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.Collection;
import android.os.Looper;
import android.os.Handler;

@zziy
public class zzkr
{
    public static final Handler zzcrf;
    private final Object zzakd;
    private String zzbnw;
    private zzfy zzcjn;
    private boolean zzcrg;
    private boolean zzcrh;
    
    static {
        zzcrf = new zzko(Looper.getMainLooper());
    }
    
    public zzkr() {
        this.zzakd = new Object();
        this.zzcrg = true;
        this.zzcrh = false;
    }
    
    private JSONArray zza(final Collection<?> collection) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.zza(jsonArray, iterator.next());
        }
        return jsonArray;
    }
    
    private void zza(final JSONArray jsonArray, final Object o) throws JSONException {
        if (o instanceof Bundle) {
            jsonArray.put((Object)this.zzi((Bundle)o));
        }
        else if (o instanceof Map) {
            jsonArray.put((Object)this.zzan((Map<String, ?>)o));
        }
        else if (o instanceof Collection) {
            jsonArray.put((Object)this.zza((Collection<?>)o));
        }
        else if (o instanceof Object[]) {
            jsonArray.put((Object)this.zza((Object[])o));
        }
        else {
            jsonArray.put(o);
        }
    }
    
    private void zza(final JSONObject jsonObject, String s, final Object o) throws JSONException {
        if (o instanceof Bundle) {
            jsonObject.put(s, (Object)this.zzi((Bundle)o));
        }
        else if (o instanceof Map) {
            jsonObject.put(s, (Object)this.zzan((Map<String, ?>)o));
        }
        else if (o instanceof Collection) {
            if (s == null) {
                s = "null";
            }
            jsonObject.put(s, (Object)this.zza((Collection<?>)o));
        }
        else if (o instanceof Object[]) {
            jsonObject.put(s, (Object)this.zza(Arrays.asList((Object[])o)));
        }
        else {
            jsonObject.put(s, o);
        }
    }
    
    private boolean zza(final KeyguardManager keyguardManager) {
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }
    
    private boolean zza(final PowerManager powerManager) {
        return powerManager == null || powerManager.isScreenOn();
    }
    
    public static void zzb(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        }
        else {
            zzkq.zza(runnable);
        }
    }
    
    private JSONObject zzi(final Bundle bundle) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            this.zza(jsonObject, s, bundle.get(s));
        }
        return jsonObject;
    }
    
    private boolean zzi(final Context context) {
        final PowerManager powerManager = (PowerManager)context.getSystemService("power");
        return powerManager != null && powerManager.isScreenOn();
    }
    
    private Bitmap zzl(@NonNull final View view) {
        Bitmap bitmap;
        try {
            final int width = view.getWidth();
            final int height = view.getHeight();
            if (width == 0 || height == 0) {
                zzb.zzdf("Width or mHeight of view is zero");
                bitmap = null;
            }
            else {
                bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap$Config.RGB_565);
                final Canvas canvas = new Canvas(bitmap);
                view.layout(0, 0, width, height);
                view.draw(canvas);
            }
        }
        catch (RuntimeException ex) {
            zzb.zzb("Fail to capture the webview", ex);
            bitmap = null;
        }
        return bitmap;
    }
    
    private Bitmap zzm(@NonNull final View p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   android/view/View.isDrawingCacheEnabled:()Z
        //     4: istore          5
        //     6: aload_1        
        //     7: iconst_1       
        //     8: invokevirtual   android/view/View.setDrawingCacheEnabled:(Z)V
        //    11: aload_1        
        //    12: invokevirtual   android/view/View.getDrawingCache:()Landroid/graphics/Bitmap;
        //    15: astore          6
        //    17: aload           6
        //    19: ifnull          61
        //    22: aload           6
        //    24: invokestatic    android/graphics/Bitmap.createBitmap:(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
        //    27: astore          7
        //    29: aload           7
        //    31: astore_3       
        //    32: aload_1        
        //    33: iload           5
        //    35: invokevirtual   android/view/View.setDrawingCacheEnabled:(Z)V
        //    38: aload_3        
        //    39: areturn        
        //    40: astore_2       
        //    41: aconst_null    
        //    42: astore_3       
        //    43: aload_2        
        //    44: astore          4
        //    46: ldc             "Fail to capture the web view"
        //    48: aload           4
        //    50: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    53: goto            38
        //    56: astore          4
        //    58: goto            46
        //    61: aconst_null    
        //    62: astore_3       
        //    63: goto            32
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      29     40     46     Ljava/lang/RuntimeException;
        //  32     38     56     61     Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void runOnUiThread(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        }
        else {
            zzkr.zzcrf.post(runnable);
        }
    }
    
    public DisplayMetrics zza(final WindowManager windowManager) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
    
    public PopupWindow zza(final View view, final int n, final int n2, final boolean b) {
        return new PopupWindow(view, n, n2, b);
    }
    
    public String zza(final Context context, View view, final AdSizeParcel adSizeParcel) {
        final boolean booleanValue = zzdi.zzbct.get();
        String string = null;
        if (booleanValue) {
        Label_0264_Outer:
            while (true) {
            Label_0264:
                while (true) {
                Label_0270:
                    while (true) {
                        try {
                            final JSONObject jsonObject = new JSONObject();
                            final JSONObject jsonObject2 = new JSONObject();
                            jsonObject2.put("mWidth", adSizeParcel.width);
                            jsonObject2.put("mHeight", adSizeParcel.height);
                            jsonObject.put("size", (Object)jsonObject2);
                            jsonObject.put("activity", (Object)this.zzah(context));
                            if (!adSizeParcel.zzaxj) {
                                final JSONArray jsonArray = new JSONArray();
                                if (view != null) {
                                    final ViewParent parent = view.getParent();
                                    if (parent != null) {
                                        int indexOfChild = -1;
                                        if (parent instanceof ViewGroup) {
                                            indexOfChild = ((ViewGroup)parent).indexOfChild(view);
                                        }
                                        final JSONObject jsonObject3 = new JSONObject();
                                        jsonObject3.put("type", (Object)((ViewGroup)parent).getClass().getName());
                                        jsonObject3.put("index_of_child", indexOfChild);
                                        jsonArray.put((Object)jsonObject3);
                                    }
                                    if (parent != null && parent instanceof View) {
                                        final View view2 = (View)parent;
                                        break Label_0264;
                                    }
                                    break Label_0270;
                                }
                                else if (jsonArray.length() > 0) {
                                    jsonObject.put("parents", (Object)jsonArray);
                                }
                            }
                            string = jsonObject.toString();
                        }
                        catch (JSONException ex) {
                            zzb.zzd("Fail to get view hierarchy json", (Throwable)ex);
                            string = null;
                        }
                        break;
                        View view2 = null;
                        view = view2;
                        continue Label_0264_Outer;
                    }
                    final View view2 = null;
                    continue Label_0264;
                }
            }
        }
        return string;
    }
    
    public String zza(final Context context, final zzau zzau, String string, final View view) {
        if (zzau != null) {
            try {
                Uri uri = Uri.parse(string);
                if (zzau.zzd(uri)) {
                    uri = zzau.zza(uri, context, view);
                }
                string = uri.toString();
            }
            catch (Exception ex) {}
        }
        return string;
    }
    
    public String zza(final zzlt zzlt, final String s) {
        return this.zza(zzlt.getContext(), zzlt.zzvt(), s, zzlt.getView());
    }
    
    public String zza(final InputStreamReader inputStreamReader) throws IOException {
        final StringBuilder sb = new StringBuilder(8192);
        final char[] array = new char[2048];
        while (true) {
            final int read = inputStreamReader.read(array);
            if (read == -1) {
                break;
            }
            sb.append(array, 0, read);
        }
        return sb.toString();
    }
    
    public Map<String, Integer> zza(final View view, final WindowManager windowManager) {
        final DisplayMetrics zza = this.zza(windowManager);
        final int widthPixels = zza.widthPixels;
        final int heightPixels = zza.heightPixels;
        final int[] array = new int[2];
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        view.getLocationInWindow(array);
        hashMap.put("xInPixels", array[0]);
        hashMap.put("yInPixels", array[1]);
        hashMap.put("windowWidthInPixels", widthPixels);
        hashMap.put("windowHeightInPixels", heightPixels);
        return hashMap;
    }
    
    JSONArray zza(final Object[] array) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        for (int length = array.length, i = 0; i < length; ++i) {
            this.zza(jsonArray, array[i]);
        }
        return jsonArray;
    }
    
    public void zza(final Activity activity, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
        }
    }
    
    public void zza(final Activity activity, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnScrollChangedListener(viewTreeObserver$OnScrollChangedListener);
        }
    }
    
    @TargetApi(18)
    public void zza(final Context context, final Uri uri) {
        try {
            final Bundle bundle = new Bundle();
            if (zzdi.zzbhp.get() && zzs.zzaxq()) {
                bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder)null);
            }
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(new Intent("android.intent.action.VIEW", uri).putExtras(bundle));
            final String value = String.valueOf(uri.toString());
            zzb.zzdd(new StringBuilder(26 + String.valueOf(value).length()).append("Opening ").append(value).append(" in a new browser.").toString());
        }
        catch (ActivityNotFoundException ex) {
            zzb.zzb("No browser is found.", (Throwable)ex);
        }
    }
    
    public void zza(final Context context, final String s, final WebSettings webSettings) {
        webSettings.setUserAgentString(this.zzg(context, s));
    }
    
    public void zza(final Context context, @Nullable final String s, final String s2, final Bundle bundle, final boolean b) {
        if (b) {
            bundle.putString("device", zzu.zzfz().zzuj());
            bundle.putString("eids", TextUtils.join((CharSequence)",", (Iterable)zzdi.zzkr()));
        }
        zzm.zzjr().zza(context, s, s2, bundle, b, (com.google.android.gms.ads.internal.util.client.zza.zza)new com.google.android.gms.ads.internal.util.client.zza.zza() {
            @Override
            public void zzcy(final String s) {
                zzu.zzfz().zzc(context, s, s);
            }
        });
    }
    
    public void zza(final Context context, final String s, final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Future future = (Future)new zzlb(context, s, iterator.next()).zzqw();
        }
    }
    
    public void zza(final Context context, final String s, final boolean b, final HttpURLConnection httpURLConnection) {
        this.zza(context, s, b, httpURLConnection, false);
    }
    
    public void zza(final Context context, final String s, final boolean instanceFollowRedirects, final HttpURLConnection httpURLConnection, final boolean useCaches) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(instanceFollowRedirects);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", this.zzg(context, s));
        httpURLConnection.setUseCaches(useCaches);
    }
    
    public void zza(final Context context, final List<String> list) {
        if (context instanceof Activity && !TextUtils.isEmpty((CharSequence)zzarq.zzfc(context))) {
            if (list == null) {
                zzkn.v("Cannot ping urls: empty list.");
            }
            else if (!zzdw.zzo(context)) {
                zzkn.v("Cannot ping url because custom tabs is not supported");
            }
            else {
                final zzdw zzdw = new zzdw();
                zzdw.zza((zzdw.zza)new zzdw.zza() {
                    @Override
                    public void zzlh() {
                        for (final String s : list) {
                            final String value = String.valueOf(s);
                            String concat;
                            if (value.length() != 0) {
                                concat = "Pinging url: ".concat(value);
                            }
                            else {
                                concat = new String("Pinging url: ");
                            }
                            zzb.zzde(concat);
                            zzdw.mayLaunchUrl(Uri.parse(s), null, null);
                        }
                        zzdw.zzd((Activity)context);
                    }
                    
                    @Override
                    public void zzli() {
                    }
                });
                zzdw.zze((Activity)context);
            }
        }
    }
    
    public void zza(final List<String> list, final String s) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Future future = (Future)new zzlb(iterator.next(), s).zzqw();
        }
    }
    
    @TargetApi(24)
    public boolean zza(final Activity activity, final Configuration configuration) {
        final DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        final com.google.android.gms.ads.internal.util.client.zza zzjr = zzm.zzjr();
        final int zzb = zzjr.zzb((Context)activity, configuration.screenHeightDp);
        final int zzb2 = zzjr.zzb((Context)activity, configuration.screenWidthDp);
        final int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize;
        if (identifier > 0) {
            dimensionPixelSize = activity.getResources().getDimensionPixelSize(identifier);
        }
        else {
            dimensionPixelSize = 0;
        }
        final int n = (int)Math.round(0.5 + displayMetrics.densityDpi / 160.0) * zzdi.zzbhw.get();
        final boolean zzb3 = this.zzb(displayMetrics.heightPixels, dimensionPixelSize + zzb, n);
        boolean b = false;
        if (zzb3) {
            final boolean zzb4 = this.zzb(displayMetrics.widthPixels, zzb2, n);
            b = false;
            if (zzb4) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean zza(final PackageManager packageManager, final String s, final String s2) {
        return packageManager.checkPermission(s2, s) == 0;
    }
    
    public boolean zza(final View view, final Context context) {
        final Context applicationContext = context.getApplicationContext();
        PowerManager powerManager;
        if (applicationContext != null) {
            powerManager = (PowerManager)applicationContext.getSystemService("power");
        }
        else {
            powerManager = null;
        }
        final Object systemService = context.getSystemService("keyguard");
        KeyguardManager keyguardManager = null;
        if (systemService != null) {
            final boolean b = systemService instanceof KeyguardManager;
            keyguardManager = null;
            if (b) {
                keyguardManager = (KeyguardManager)systemService;
            }
        }
        return this.zza(view, powerManager, keyguardManager);
    }
    
    public boolean zza(final View view, final PowerManager powerManager, final KeyguardManager keyguardManager) {
        boolean b = true;
        final boolean b2 = (zzu.zzfz().zzuf() || !this.zza(keyguardManager)) && b;
        if (view.getVisibility() != 0 || !view.isShown() || !this.zza(powerManager) || !b2 || (zzdi.zzbdu.get() && view.getLocalVisibleRect(new Rect()) && !view.getGlobalVisibleRect(new Rect()))) {
            b = false;
        }
        return b;
    }
    
    public boolean zza(final ClassLoader classLoader, final Class<?> clazz, final String s) {
        try {
            return clazz.isAssignableFrom(Class.forName(s, false, classLoader));
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public boolean zzac(final Context context) {
        boolean b = false;
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        final ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            zzb.zzdf("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
        }
        else {
            boolean b2;
            if ((0x10 & resolveActivity.activityInfo.configChanges) == 0x0) {
                zzb.zzdf(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboard"));
                b2 = false;
            }
            else {
                b2 = true;
            }
            if ((0x20 & resolveActivity.activityInfo.configChanges) == 0x0) {
                zzb.zzdf(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboardHidden"));
                b2 = false;
            }
            if ((0x80 & resolveActivity.activityInfo.configChanges) == 0x0) {
                zzb.zzdf(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "orientation"));
                b2 = false;
            }
            if ((0x100 & resolveActivity.activityInfo.configChanges) == 0x0) {
                zzb.zzdf(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenLayout"));
                b2 = false;
            }
            if ((0x200 & resolveActivity.activityInfo.configChanges) == 0x0) {
                zzb.zzdf(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "uiMode"));
                b2 = false;
            }
            if ((0x400 & resolveActivity.activityInfo.configChanges) == 0x0) {
                zzb.zzdf(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenSize"));
                b2 = false;
            }
            if ((0x800 & resolveActivity.activityInfo.configChanges) == 0x0) {
                zzb.zzdf(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "smallestScreenSize"));
                b = false;
            }
            else {
                b = b2;
            }
        }
        return b;
    }
    
    public boolean zzad(final Context context) {
        boolean zzcrh = true;
        if (this.zzcrh) {
            zzcrh = false;
        }
        else {
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            context.getApplicationContext().registerReceiver((BroadcastReceiver)new zza(), intentFilter);
            this.zzcrh = zzcrh;
        }
        return zzcrh;
    }
    
    protected String zzae(final Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }
    
    public AlertDialog$Builder zzaf(final Context context) {
        return new AlertDialog$Builder(context);
    }
    
    public zzda zzag(final Context context) {
        return new zzda(context);
    }
    
    public String zzah(final Context context) {
        try {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            final List runningTasks = activityManager.getRunningTasks(1);
            if (runningTasks != null && !runningTasks.isEmpty()) {
                final ActivityManager$RunningTaskInfo activityManager$RunningTaskInfo = runningTasks.get(0);
                if (activityManager$RunningTaskInfo != null && activityManager$RunningTaskInfo.topActivity != null) {
                    return activityManager$RunningTaskInfo.topActivity.getClassName();
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public boolean zzai(final Context context) {
        try {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            final KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    final List runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses == null) {
                        return false;
                    }
                    for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                        if (Process.myPid() == activityManager$RunningAppProcessInfo.pid) {
                            if (activityManager$RunningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && this.zzi(context)) {
                                return true;
                            }
                            break;
                        }
                    }
                    return false;
                }
            }
        }
        catch (Throwable t) {
            return false;
        }
        return false;
    }
    
    public Bitmap zzaj(final Context context) {
        final boolean b = context instanceof Activity;
        Bitmap bitmap = null;
        if (b) {
            Bitmap bitmap2 = null;
            Label_0095: {
                try {
                    if (!zzdi.zzbfo.get()) {
                        bitmap2 = this.zzl(((Activity)context).getWindow().getDecorView());
                        break Label_0095;
                    }
                    final Window window = ((Activity)context).getWindow();
                    if (window != null) {
                        bitmap2 = this.zzm(window.getDecorView().getRootView());
                        break Label_0095;
                    }
                }
                catch (RuntimeException ex) {
                    zzb.zzb("Fail to capture screen shot", ex);
                }
                bitmap2 = null;
            }
            bitmap = bitmap2;
        }
        return bitmap;
    }
    
    public AudioManager zzak(final Context context) {
        return (AudioManager)context.getSystemService("audio");
    }
    
    public float zzal(final Context context) {
        final AudioManager zzak = this.zzak(context);
        float n = 0.0f;
        if (zzak != null) {
            final int streamMaxVolume = zzak.getStreamMaxVolume(3);
            final int streamVolume = zzak.getStreamVolume(3);
            n = 0.0f;
            if (streamMaxVolume != 0) {
                n = streamVolume / streamMaxVolume;
            }
        }
        return n;
    }
    
    public int zzam(final Context context) {
        final ApplicationInfo applicationInfo = context.getApplicationInfo();
        int targetSdkVersion;
        if (applicationInfo == null) {
            targetSdkVersion = 0;
        }
        else {
            targetSdkVersion = applicationInfo.targetSdkVersion;
        }
        return targetSdkVersion;
    }
    
    public JSONObject zzan(final Map<String, ?> map) throws JSONException {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            for (final String s : map.keySet()) {
                this.zza(jsonObject, s, map.get(s));
            }
        }
        catch (ClassCastException ex) {
            final String value = String.valueOf(ex.getMessage());
            String concat;
            if (value.length() != 0) {
                concat = "Could not convert map to JSON: ".concat(value);
            }
            else {
                concat = new String("Could not convert map to JSON: ");
            }
            throw new JSONException(concat);
        }
        return jsonObject;
    }
    
    public boolean zzan(final Context context) {
        try {
            context.getClassLoader().loadClass(ClientApi.class.getName());
            return false;
        }
        catch (ClassNotFoundException ex) {
            return true;
        }
    }
    
    public void zzb(final Activity activity, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(viewTreeObserver$OnScrollChangedListener);
        }
    }
    
    public void zzb(final Context context, final Intent intent) {
        try {
            context.startActivity(intent);
        }
        catch (Throwable t) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }
    
    public void zzb(final Context context, final String s, final String s2, final Bundle bundle, final boolean b) {
        if (zzdi.zzbdy.get()) {
            this.zza(context, s, s2, bundle, b);
        }
    }
    
    boolean zzb(final int n, final int n2, final int n3) {
        return Math.abs(n - n2) <= n3;
    }
    
    public zzfy zzc(Context applicationContext, final VersionInfoParcel versionInfoParcel) {
        synchronized (this.zzakd) {
            if (this.zzcjn == null) {
                if (applicationContext.getApplicationContext() != null) {
                    applicationContext = applicationContext.getApplicationContext();
                }
                this.zzcjn = new zzfy(applicationContext, versionInfoParcel, zzdi.zzbao.get());
            }
            return this.zzcjn;
        }
    }
    
    public String zzc(String replaceAll, final Map<String, String> map) {
        for (final String s : map.keySet()) {
            replaceAll = replaceAll.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", s), String.format("$1%s$2", Uri.encode((String)map.get(s))));
        }
        return replaceAll.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", "[^@]+"), String.format("$1%s$2", "")).replaceAll("@@", "@");
    }
    
    public void zzc(final Context context, final String s, final String s2) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s2);
        this.zza(context, s, list);
    }
    
    public String zzcv(final String s) {
        return Uri.parse(s).buildUpon().query((String)null).build().toString();
    }
    
    public int zzcw(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            final String value = String.valueOf(ex);
            zzb.zzdf(new StringBuilder(22 + String.valueOf(value).length()).append("Could not parse value:").append(value).toString());
            return 0;
        }
    }
    
    public boolean zzcx(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }
    
    public void zzd(final Context context, final String s, final String s2) {
        try {
            final FileOutputStream openFileOutput = context.openFileOutput(s, 0);
            openFileOutput.write(s2.getBytes("UTF-8"));
            openFileOutput.close();
        }
        catch (Exception ex) {
            zzb.zzb("Error writing to file in internal storage.", ex);
        }
    }
    
    public float zzfe() {
        final zzo zzfd = zzu.zzgt().zzfd();
        float zzfe;
        if (zzfd != null && zzfd.zzff()) {
            zzfe = zzfd.zzfe();
        }
        else {
            zzfe = 1.0f;
        }
        return zzfe;
    }
    
    public boolean zzfg() {
        final zzo zzfd = zzu.zzgt().zzfd();
        return zzfd != null && zzfd.zzfg();
    }
    
    public String zzg(final Context context, final String s) {
        String s2 = null;
        synchronized (this.zzakd) {
            if (this.zzbnw != null) {
                s2 = this.zzbnw;
                return s2;
            }
            if (s == null) {
                s2 = this.zzug();
                return s2;
            }
        }
        while (true) {
            try {
                this.zzbnw = zzu.zzgb().getDefaultUserAgent(context);
                Label_0188: {
                    if (!TextUtils.isEmpty((CharSequence)this.zzbnw)) {
                        break Label_0188;
                    }
                    Block_10: {
                        if (!zzm.zzjr().zzvf()) {
                            this.zzbnw = null;
                            zzkr.zzcrf.post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    synchronized (zzkr.this.zzakd) {
                                        zzkr.this.zzbnw = zzkr.this.zzae(context);
                                        zzkr.this.zzakd.notifyAll();
                                    }
                                }
                            });
                            while (this.zzbnw == null) {
                                try {
                                    this.zzakd.wait();
                                    continue;
                                }
                                catch (InterruptedException ex) {
                                    this.zzbnw = this.zzug();
                                    final String value = String.valueOf(this.zzbnw);
                                    String concat;
                                    if (value.length() != 0) {
                                        concat = "Interrupted, use default user agent: ".concat(value);
                                    }
                                    else {
                                        concat = new String("Interrupted, use default user agent: ");
                                    }
                                    zzb.zzdf(concat);
                                    continue;
                                }
                                break Block_10;
                            }
                            break Label_0188;
                        }
                    }
                    try {
                        this.zzbnw = this.zzae(context);
                        final String value2 = String.valueOf(this.zzbnw);
                        this.zzbnw = new StringBuilder(11 + String.valueOf(value2).length() + String.valueOf(s).length()).append(value2).append(" (Mobile; ").append(s).append(")").toString();
                        s2 = this.zzbnw;
                    }
                    // monitorexit(o)
                    catch (Exception ex2) {
                        this.zzbnw = this.zzug();
                    }
                }
            }
            catch (Exception ex3) {
                continue;
            }
            break;
        }
        return s2;
    }
    
    public Map<String, String> zzg(final Uri uri) {
        Map<String, String> map;
        if (uri == null) {
            map = null;
        }
        else {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            for (final String s : zzu.zzgb().zzh(uri)) {
                hashMap.put(s, uri.getQueryParameter(s));
            }
            map = hashMap;
        }
        return map;
    }
    
    public String zzh(final Context context, final String s) {
        try {
            return new String(com.google.android.gms.common.util.zzo.zza(context.openFileInput(s), true), "UTF-8");
        }
        catch (IOException ex) {
            zzb.zzb("Error reading from internal storage.", ex);
            return "";
        }
    }
    
    public int[] zzh(final Activity activity) {
        final Window window = activity.getWindow();
        if (window == null) {
            return this.zzuk();
        }
        final View viewById = window.findViewById(16908290);
        if (viewById == null) {
            return this.zzuk();
        }
        return new int[] { viewById.getWidth(), viewById.getHeight() };
        zzuk = this.zzuk();
        return zzuk;
    }
    
    public int[] zzi(final Activity activity) {
        final int[] zzh = this.zzh(activity);
        return new int[] { zzm.zzjr().zzc((Context)activity, zzh[0]), zzm.zzjr().zzc((Context)activity, zzh[1]) };
    }
    
    public int[] zzj(final Activity activity) {
        final Window window = activity.getWindow();
        if (window == null) {
            return this.zzuk();
        }
        final View viewById = window.findViewById(16908290);
        if (viewById == null) {
            return this.zzuk();
        }
        return new int[] { viewById.getTop(), viewById.getBottom() };
        zzuk = this.zzuk();
        return zzuk;
    }
    
    public Bitmap zzk(final View view) {
        view.setDrawingCacheEnabled(true);
        final Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }
    
    public int[] zzk(final Activity activity) {
        final int[] zzj = this.zzj(activity);
        return new int[] { zzm.zzjr().zzc((Context)activity, zzj[0]), zzm.zzjr().zzc((Context)activity, zzj[1]) };
    }
    
    public int zzn(@Nullable final View view) {
        int positionForView;
        if (view == null) {
            positionForView = -1;
        }
        else {
            ViewParent viewParent;
            for (viewParent = view.getParent(); viewParent != null && !(viewParent instanceof AdapterView); viewParent = viewParent.getParent()) {}
            if (viewParent == null) {
                positionForView = -1;
            }
            else {
                positionForView = ((AdapterView)viewParent).getPositionForView(view);
            }
        }
        return positionForView;
    }
    
    public boolean zzuf() {
        return this.zzcrg;
    }
    
    String zzug() {
        final StringBuffer sb = new StringBuffer(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build$VERSION.RELEASE != null) {
            sb.append(" ").append(Build$VERSION.RELEASE);
        }
        sb.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/").append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }
    
    public String zzuh() {
        return UUID.randomUUID().toString();
    }
    
    public String zzui() {
        final UUID randomUUID = UUID.randomUUID();
        final byte[] byteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        final byte[] byteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String s = new BigInteger(1, byteArray).toString();
        int n = 0;
    Label_0108_Outer:
        while (true) {
            if (n >= 2) {
                return s;
            }
            while (true) {
                try {
                    final MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(byteArray);
                    instance.update(byteArray2);
                    final byte[] array = new byte[8];
                    System.arraycopy(instance.digest(), 0, array, 0, 8);
                    s = new BigInteger(1, array).toString();
                    ++n;
                    continue Label_0108_Outer;
                }
                catch (NoSuchAlgorithmException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public String zzuj() {
        final String manufacturer = Build.MANUFACTURER;
        String s = Build.MODEL;
        if (!s.startsWith(manufacturer)) {
            s = new StringBuilder(1 + String.valueOf(manufacturer).length() + String.valueOf(s).length()).append(manufacturer).append(" ").append(s).toString();
        }
        return s;
    }
    
    protected int[] zzuk() {
        return new int[] { 0, 0 };
    }
    
    public Bundle zzul() {
        final Bundle bundle = new Bundle();
        try {
            if (zzdi.zzbbp.get()) {
                final Debug$MemoryInfo debug$MemoryInfo = new Debug$MemoryInfo();
                Debug.getMemoryInfo(debug$MemoryInfo);
                bundle.putParcelable("debug_memory_info", (Parcelable)debug$MemoryInfo);
            }
            if (zzdi.zzbbq.get()) {
                final Runtime runtime = Runtime.getRuntime();
                bundle.putLong("runtime_free_memory", runtime.freeMemory());
                bundle.putLong("runtime_max_memory", runtime.maxMemory());
                bundle.putLong("runtime_total_memory", runtime.totalMemory());
            }
            return bundle;
        }
        catch (Exception ex) {
            zzb.zzd("Unable to gather memory stats", ex);
            return bundle;
        }
    }
    
    private final class zza extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                zzkr.this.zzcrg = true;
            }
            else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                zzkr.this.zzcrg = false;
            }
        }
    }
}
