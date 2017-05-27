// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import android.os.Process;
import android.app.KeyguardManager;
import android.app.ActivityManager;
import com.google.android.gms.ads.internal.zzu;
import android.os.PowerManager;
import android.content.Context;
import android.webkit.ValueCallback;
import com.google.android.gms.common.util.zzs;
import android.app.ActivityManager$RunningAppProcessInfo;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.Window;
import android.app.Activity;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.annotation.TargetApi;

@zziy
@TargetApi(14)
public class zzcu extends Thread
{
    private boolean mStarted;
    private final Object zzakd;
    private final int zzatu;
    private final int zzatw;
    private boolean zzauq;
    private final zzcs zzaur;
    private final zzix zzaus;
    private final int zzaut;
    private final int zzauu;
    private final int zzauv;
    private boolean zzbl;
    
    public zzcu(final zzcs zzaur, final zzix zzaus) {
        this.mStarted = false;
        this.zzauq = false;
        this.zzbl = false;
        this.zzaur = zzaur;
        this.zzaus = zzaus;
        this.zzakd = new Object();
        this.zzatu = zzdi.zzbce.get();
        this.zzauu = zzdi.zzbcf.get();
        this.zzatw = zzdi.zzbcg.get();
        this.zzauv = zzdi.zzbch.get();
        this.zzaut = zzdi.zzbci.get();
        this.setName("ContentFetchTask");
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/google/android/gms/internal/zzcu.zzin:()Z
        //     4: ifeq            108
        //     7: invokestatic    com/google/android/gms/ads/internal/zzu.zzgc:()Lcom/google/android/gms/internal/zzct;
        //    10: invokevirtual   com/google/android/gms/internal/zzct.getActivity:()Landroid/app/Activity;
        //    13: astore          6
        //    15: aload           6
        //    17: ifnonnull       79
        //    20: ldc             "ContentFetchThread: no activity. Sleeping."
        //    22: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //    25: aload_0        
        //    26: invokevirtual   com/google/android/gms/internal/zzcu.zzip:()V
        //    29: sipush          1000
        //    32: aload_0        
        //    33: getfield        com/google/android/gms/internal/zzcu.zzaut:I
        //    36: imul           
        //    37: i2l            
        //    38: invokestatic    java/lang/Thread.sleep:(J)V
        //    41: aload_0        
        //    42: getfield        com/google/android/gms/internal/zzcu.zzakd:Ljava/lang/Object;
        //    45: astore_2       
        //    46: aload_2        
        //    47: monitorenter   
        //    48: aload_0        
        //    49: getfield        com/google/android/gms/internal/zzcu.zzauq:Z
        //    52: istore          4
        //    54: iload           4
        //    56: ifeq            120
        //    59: ldc             "ContentFetchTask: waiting"
        //    61: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //    64: aload_0        
        //    65: getfield        com/google/android/gms/internal/zzcu.zzakd:Ljava/lang/Object;
        //    68: invokevirtual   java/lang/Object.wait:()V
        //    71: goto            48
        //    74: astore          5
        //    76: goto            48
        //    79: aload_0        
        //    80: aload           6
        //    82: invokevirtual   com/google/android/gms/internal/zzcu.zza:(Landroid/app/Activity;)V
        //    85: goto            29
        //    88: astore_1       
        //    89: ldc             "Error in ContentFetchTask"
        //    91: aload_1        
        //    92: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    95: aload_0        
        //    96: getfield        com/google/android/gms/internal/zzcu.zzaus:Lcom/google/android/gms/internal/zzix;
        //    99: aload_1        
        //   100: ldc             "ContentFetchTask.run"
        //   102: invokevirtual   com/google/android/gms/internal/zzix.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //   105: goto            41
        //   108: ldc             "ContentFetchTask: sleeping"
        //   110: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //   113: aload_0        
        //   114: invokevirtual   com/google/android/gms/internal/zzcu.zzip:()V
        //   117: goto            29
        //   120: aload_2        
        //   121: monitorexit    
        //   122: goto            0
        //   125: astore_3       
        //   126: aload_2        
        //   127: monitorexit    
        //   128: aload_3        
        //   129: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      41     88     108    Ljava/lang/Throwable;
        //  48     54     125    130    Any
        //  59     71     74     79     Ljava/lang/InterruptedException;
        //  59     71     125    130    Any
        //  79     85     88     108    Ljava/lang/Throwable;
        //  108    117    88     108    Ljava/lang/Throwable;
        //  120    128    125    130    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    public void wakeup() {
        synchronized (this.zzakd) {
            this.zzauq = false;
            this.zzakd.notifyAll();
            zzb.zzdd("ContentFetchThread: wakeup");
        }
    }
    
    zza zza(@Nullable final View view, final zzcr zzcr) {
        int i = 0;
        zza zza;
        if (view == null) {
            zza = new zza(0, 0);
        }
        else {
            final boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
            if (view instanceof TextView && !(view instanceof EditText)) {
                final CharSequence text = ((TextView)view).getText();
                if (!TextUtils.isEmpty(text)) {
                    zzcr.zze(text.toString(), globalVisibleRect);
                    zza = new zza(1, 0);
                }
                else {
                    zza = new zza(0, 0);
                }
            }
            else if (view instanceof WebView && !(view instanceof zzlt)) {
                zzcr.zzii();
                if (this.zza((WebView)view, zzcr, globalVisibleRect)) {
                    zza = new zza(0, 1);
                }
                else {
                    zza = new zza(0, 0);
                }
            }
            else if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                int n = 0;
                int n2 = 0;
                while (i < viewGroup.getChildCount()) {
                    final zza zza2 = this.zza(viewGroup.getChildAt(i), zzcr);
                    n2 += zza2.zzavd;
                    n += zza2.zzave;
                    ++i;
                }
                zza = new zza(n2, n);
            }
            else {
                zza = new zza(0, 0);
            }
        }
        return zza;
    }
    
    void zza(@Nullable final Activity activity) {
        if (activity != null) {
            while (true) {
                try {
                    final Window window = activity.getWindow();
                    View viewById = null;
                    if (window != null) {
                        final View decorView = activity.getWindow().getDecorView();
                        viewById = null;
                        if (decorView != null) {
                            viewById = activity.getWindow().getDecorView().findViewById(16908290);
                        }
                    }
                    if (viewById != null) {
                        this.zze(viewById);
                    }
                }
                catch (Throwable t) {
                    zzb.zzdd("Failed getting root view of activity. Content not extracted.");
                    final View viewById = null;
                    continue;
                }
                break;
            }
        }
    }
    
    void zza(final zzcr zzcr, final WebView webView, final String s, final boolean b) {
        zzcr.zzih();
        try {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                final String optString = new JSONObject(s).optString("text");
                if (!TextUtils.isEmpty((CharSequence)webView.getTitle())) {
                    final String value = String.valueOf(webView.getTitle());
                    zzcr.zzd(new StringBuilder(1 + String.valueOf(value).length() + String.valueOf(optString).length()).append(value).append("\n").append(optString).toString(), b);
                }
                else {
                    zzcr.zzd(optString, b);
                }
            }
            if (zzcr.zzid()) {
                this.zzaur.zzb(zzcr);
            }
        }
        catch (JSONException ex) {
            zzb.zzdd("Json string may be malformed.");
        }
        catch (Throwable t) {
            zzb.zza("Failed to get webview content.", t);
            this.zzaus.zza(t, "ContentFetchTask.processWebViewContent");
        }
    }
    
    boolean zza(final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo) {
        return activityManager$RunningAppProcessInfo.importance == 100;
    }
    
    @TargetApi(19)
    boolean zza(final WebView webView, final zzcr zzcr, final boolean b) {
        boolean b2;
        if (!zzs.zzaxr()) {
            b2 = false;
        }
        else {
            zzcr.zzii();
            webView.post((Runnable)new Runnable() {
                ValueCallback<String> zzauy = new ValueCallback<String>() {
                    public void zzab(final String s) {
                        zzcu.this.zza(zzcr, webView, s, b);
                    }
                };
                
                @Override
                public void run() {
                    if (!webView.getSettings().getJavaScriptEnabled()) {
                        return;
                    }
                    try {
                        webView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", (ValueCallback)this.zzauy);
                    }
                    catch (Throwable t) {
                        this.zzauy.onReceiveValue((Object)"");
                    }
                }
            });
            b2 = true;
        }
        return b2;
    }
    
    boolean zze(@Nullable final View view) {
        boolean b;
        if (view == null) {
            b = false;
        }
        else {
            view.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    zzcu.this.zzf(view);
                }
            });
            b = true;
        }
        return b;
    }
    
    void zzf(final View view) {
        try {
            final zzcr zzcr = new zzcr(this.zzatu, this.zzauu, this.zzatw, this.zzauv);
            final zza zza = this.zza(view, zzcr);
            zzcr.zzij();
            if (zza.zzavd != 0 || zza.zzave != 0) {
                if ((zza.zzave != 0 || zzcr.zzik() != 0) && (zza.zzave != 0 || !this.zzaur.zza(zzcr))) {
                    this.zzaur.zzc(zzcr);
                }
            }
        }
        catch (Exception ex) {
            zzb.zzb("Exception in fetchContentOnUIThread", ex);
            this.zzaus.zza(ex, "ContentFetchTask.fetchContent");
        }
    }
    
    boolean zzi(final Context context) {
        final PowerManager powerManager = (PowerManager)context.getSystemService("power");
        return powerManager != null && powerManager.isScreenOn();
    }
    
    public void zzim() {
        synchronized (this.zzakd) {
            if (this.mStarted) {
                zzb.zzdd("Content hash thread already started, quiting...");
            }
            else {
                this.mStarted = true;
                // monitorexit(this.zzakd)
                this.start();
            }
        }
    }
    
    boolean zzin() {
        boolean b;
        try {
            final Context context = zzu.zzgc().getContext();
            if (context == null) {
                b = false;
            }
            else {
                final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
                final KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
                if (activityManager == null || keyguardManager == null) {
                    return false;
                }
                final List runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses == null) {
                    b = false;
                }
                else {
                    for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                        if (Process.myPid() == activityManager$RunningAppProcessInfo.pid) {
                            if (this.zza(activityManager$RunningAppProcessInfo) && !keyguardManager.inKeyguardRestrictedInputMode() && this.zzi(context)) {
                                b = true;
                                return b;
                            }
                            break;
                        }
                    }
                    b = false;
                }
            }
        }
        catch (Throwable t) {
            b = false;
        }
        return b;
        b = false;
        return b;
    }
    
    public zzcr zzio() {
        return this.zzaur.zzil();
    }
    
    public void zzip() {
        synchronized (this.zzakd) {
            this.zzauq = true;
            zzb.zzdd(new StringBuilder(42).append("ContentFetchThread: paused, mPause = ").append(this.zzauq).toString());
        }
    }
    
    public boolean zziq() {
        return this.zzauq;
    }
    
    @zziy
    class zza
    {
        final int zzavd;
        final int zzave;
        
        zza(final int zzavd, final int zzave) {
            this.zzavd = zzavd;
            this.zzave = zzave;
        }
    }
}
