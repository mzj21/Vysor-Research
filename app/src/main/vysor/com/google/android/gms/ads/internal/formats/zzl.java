// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.ads.internal.client.zzm;
import android.graphics.Rect;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzd;
import android.graphics.Point;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.zzu;
import java.util.HashMap;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Map;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.google.android.gms.internal.zziy;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;
import com.google.android.gms.internal.zzdz;

@zziy
public class zzl extends zzdz.zza implements View$OnClickListener, View$OnTouchListener, ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    private final Object zzakd;
    @Nullable
    private FrameLayout zzaks;
    @Nullable
    private zzi zzbkb;
    private final FrameLayout zzblg;
    private Map<String, WeakReference<View>> zzblh;
    @Nullable
    private zzb zzbli;
    boolean zzblj;
    int zzblk;
    int zzbll;
    
    public zzl(final FrameLayout zzblg, final FrameLayout zzaks) {
        this.zzakd = new Object();
        this.zzblh = new HashMap<String, WeakReference<View>>();
        this.zzblj = false;
        this.zzblg = zzblg;
        this.zzaks = zzaks;
        zzu.zzgx().zza((View)this.zzblg, (ViewTreeObserver$OnGlobalLayoutListener)this);
        zzu.zzgx().zza((View)this.zzblg, (ViewTreeObserver$OnScrollChangedListener)this);
        this.zzblg.setOnTouchListener((View$OnTouchListener)this);
        this.zzblg.setOnClickListener((View$OnClickListener)this);
    }
    
    private void zzd(final zzj zzj) {
        if (!this.zzblh.containsKey("2011")) {
            zzj.zzlz();
        }
        else {
            final View view = this.zzblh.get("2011").get();
            if (!(view instanceof FrameLayout)) {
                zzj.zzlz();
            }
            else {
                zzj.zza(view, new zzg() {
                    @Override
                    public void zzc(final MotionEvent motionEvent) {
                        zzl.this.onTouch(null, motionEvent);
                    }
                    
                    @Override
                    public void zzlu() {
                        zzl.this.onClick(view);
                    }
                });
            }
        }
    }
    
    public void destroy() {
        synchronized (this.zzakd) {
            if (this.zzaks != null) {
                this.zzaks.removeAllViews();
            }
            this.zzaks = null;
            this.zzblh = null;
            this.zzbli = null;
            this.zzbkb = null;
        }
    }
    
    int getMeasuredHeight() {
        return this.zzblg.getMeasuredHeight();
    }
    
    int getMeasuredWidth() {
        return this.zzblg.getMeasuredWidth();
    }
    
    public void onClick(final View p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/formats/zzl.zzakd:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbkb:Lcom/google/android/gms/ads/internal/formats/zzi;
        //    11: ifnonnull       19
        //    14: aload_2        
        //    15: monitorexit    
        //    16: goto            475
        //    19: new             Lorg/json/JSONObject;
        //    22: dup            
        //    23: invokespecial   org/json/JSONObject.<init>:()V
        //    26: astore          4
        //    28: aload_0        
        //    29: getfield        com/google/android/gms/ads/internal/formats/zzl.zzblh:Ljava/util/Map;
        //    32: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //    37: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    42: astore          5
        //    44: aload           5
        //    46: invokeinterface java/util/Iterator.hasNext:()Z
        //    51: ifeq            256
        //    54: aload           5
        //    56: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    61: checkcast       Ljava/util/Map$Entry;
        //    64: astore          14
        //    66: aload           14
        //    68: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //    73: checkcast       Ljava/lang/ref/WeakReference;
        //    76: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //    79: checkcast       Landroid/view/View;
        //    82: astore          15
        //    84: aload           15
        //    86: ifnull          44
        //    89: aload_0        
        //    90: aload           15
        //    92: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzi:(Landroid/view/View;)Landroid/graphics/Point;
        //    95: astore          16
        //    97: new             Lorg/json/JSONObject;
        //   100: dup            
        //   101: invokespecial   org/json/JSONObject.<init>:()V
        //   104: astore          17
        //   106: aload           17
        //   108: ldc             "mWidth"
        //   110: aload_0        
        //   111: aload           15
        //   113: invokevirtual   android/view/View.getWidth:()I
        //   116: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzz:(I)I
        //   119: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   122: pop            
        //   123: aload           17
        //   125: ldc             "mHeight"
        //   127: aload_0        
        //   128: aload           15
        //   130: invokevirtual   android/view/View.getHeight:()I
        //   133: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzz:(I)I
        //   136: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   139: pop            
        //   140: aload           17
        //   142: ldc             "x"
        //   144: aload_0        
        //   145: aload           16
        //   147: getfield        android/graphics/Point.x:I
        //   150: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzz:(I)I
        //   153: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   156: pop            
        //   157: aload           17
        //   159: ldc             "y"
        //   161: aload_0        
        //   162: aload           16
        //   164: getfield        android/graphics/Point.y:I
        //   167: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzz:(I)I
        //   170: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   173: pop            
        //   174: aload           4
        //   176: aload           14
        //   178: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   183: checkcast       Ljava/lang/String;
        //   186: aload           17
        //   188: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   191: pop            
        //   192: goto            44
        //   195: astore          18
        //   197: aload           14
        //   199: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   204: checkcast       Ljava/lang/String;
        //   207: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   210: astore          19
        //   212: aload           19
        //   214: invokevirtual   java/lang/String.length:()I
        //   217: ifeq            242
        //   220: ldc             "Unable to get view rectangle for view "
        //   222: aload           19
        //   224: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   227: astore          20
        //   229: aload           20
        //   231: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   234: goto            44
        //   237: astore_3       
        //   238: aload_2        
        //   239: monitorexit    
        //   240: aload_3        
        //   241: athrow         
        //   242: new             Ljava/lang/String;
        //   245: dup            
        //   246: ldc             "Unable to get view rectangle for view "
        //   248: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   251: astore          20
        //   253: goto            229
        //   256: new             Lorg/json/JSONObject;
        //   259: dup            
        //   260: invokespecial   org/json/JSONObject.<init>:()V
        //   263: astore          6
        //   265: aload           6
        //   267: ldc             "x"
        //   269: aload_0        
        //   270: aload_0        
        //   271: getfield        com/google/android/gms/ads/internal/formats/zzl.zzblk:I
        //   274: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzz:(I)I
        //   277: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   280: pop            
        //   281: aload           6
        //   283: ldc             "y"
        //   285: aload_0        
        //   286: aload_0        
        //   287: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbll:I
        //   290: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzz:(I)I
        //   293: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   296: pop            
        //   297: new             Lorg/json/JSONObject;
        //   300: dup            
        //   301: invokespecial   org/json/JSONObject.<init>:()V
        //   304: astore          8
        //   306: aload           8
        //   308: ldc             "mWidth"
        //   310: aload_0        
        //   311: aload_0        
        //   312: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.getMeasuredWidth:()I
        //   315: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzz:(I)I
        //   318: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   321: pop            
        //   322: aload           8
        //   324: ldc             "mHeight"
        //   326: aload_0        
        //   327: aload_0        
        //   328: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.getMeasuredHeight:()I
        //   331: invokevirtual   com/google/android/gms/ads/internal/formats/zzl.zzz:(I)I
        //   334: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   337: pop            
        //   338: aload_0        
        //   339: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbli:Lcom/google/android/gms/ads/internal/formats/zzb;
        //   342: ifnull          452
        //   345: aload_0        
        //   346: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbli:Lcom/google/android/gms/ads/internal/formats/zzb;
        //   349: invokevirtual   com/google/android/gms/ads/internal/formats/zzb.zzlm:()Landroid/view/ViewGroup;
        //   352: aload_1        
        //   353: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   356: ifeq            452
        //   359: aload_0        
        //   360: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbkb:Lcom/google/android/gms/ads/internal/formats/zzi;
        //   363: instanceof      Lcom/google/android/gms/ads/internal/formats/zzh;
        //   366: ifeq            431
        //   369: aload_0        
        //   370: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbkb:Lcom/google/android/gms/ads/internal/formats/zzi;
        //   373: checkcast       Lcom/google/android/gms/ads/internal/formats/zzh;
        //   376: invokevirtual   com/google/android/gms/ads/internal/formats/zzh.zzlw:()Lcom/google/android/gms/ads/internal/formats/zzi;
        //   379: ifnull          431
        //   382: aload_0        
        //   383: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbkb:Lcom/google/android/gms/ads/internal/formats/zzi;
        //   386: checkcast       Lcom/google/android/gms/ads/internal/formats/zzh;
        //   389: invokevirtual   com/google/android/gms/ads/internal/formats/zzh.zzlw:()Lcom/google/android/gms/ads/internal/formats/zzi;
        //   392: aload_1        
        //   393: ldc             "1007"
        //   395: aload           4
        //   397: aload           6
        //   399: aload           8
        //   401: invokeinterface com/google/android/gms/ads/internal/formats/zzi.zza:(Landroid/view/View;Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONObject;Lorg/json/JSONObject;)V
        //   406: aload_2        
        //   407: monitorexit    
        //   408: goto            475
        //   411: astore          7
        //   413: ldc             "Unable to get click location"
        //   415: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   418: goto            297
        //   421: astore          9
        //   423: ldc             "Unable to get native ad view bounding box"
        //   425: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   428: goto            338
        //   431: aload_0        
        //   432: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbkb:Lcom/google/android/gms/ads/internal/formats/zzi;
        //   435: aload_1        
        //   436: ldc             "1007"
        //   438: aload           4
        //   440: aload           6
        //   442: aload           8
        //   444: invokeinterface com/google/android/gms/ads/internal/formats/zzi.zza:(Landroid/view/View;Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONObject;Lorg/json/JSONObject;)V
        //   449: goto            406
        //   452: aload_0        
        //   453: getfield        com/google/android/gms/ads/internal/formats/zzl.zzbkb:Lcom/google/android/gms/ads/internal/formats/zzi;
        //   456: aload_1        
        //   457: aload_0        
        //   458: getfield        com/google/android/gms/ads/internal/formats/zzl.zzblh:Ljava/util/Map;
        //   461: aload           4
        //   463: aload           6
        //   465: aload           8
        //   467: invokeinterface com/google/android/gms/ads/internal/formats/zzi.zza:(Landroid/view/View;Ljava/util/Map;Lorg/json/JSONObject;Lorg/json/JSONObject;Lorg/json/JSONObject;)V
        //   472: goto            406
        //   475: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  7      106    237    242    Any
        //  106    192    195    237    Lorg/json/JSONException;
        //  106    192    237    242    Any
        //  197    240    237    242    Any
        //  242    265    237    242    Any
        //  265    297    411    421    Lorg/json/JSONException;
        //  265    297    237    242    Any
        //  297    306    237    242    Any
        //  306    338    421    431    Lorg/json/JSONException;
        //  306    338    237    242    Any
        //  338    472    237    242    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0338:
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
    
    public void onGlobalLayout() {
        synchronized (this.zzakd) {
            if (this.zzblj) {
                final int measuredWidth = this.getMeasuredWidth();
                final int measuredHeight = this.getMeasuredHeight();
                if (measuredWidth != 0 && measuredHeight != 0 && this.zzaks != null) {
                    this.zzaks.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(measuredWidth, measuredHeight));
                    this.zzblj = false;
                }
            }
            if (this.zzbkb != null) {
                this.zzbkb.zzg((View)this.zzblg);
            }
        }
    }
    
    public void onScrollChanged() {
        synchronized (this.zzakd) {
            if (this.zzbkb != null) {
                this.zzbkb.zzg((View)this.zzblg);
            }
        }
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        synchronized (this.zzakd) {
            if (this.zzbkb != null) {
                final Point zze = this.zze(motionEvent);
                this.zzblk = zze.x;
                this.zzbll = zze.y;
                final MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setLocation((float)zze.x, (float)zze.y);
                this.zzbkb.zzd(obtain);
                obtain.recycle();
            }
        }
        return false;
    }
    
    public zzd zzas(final String s) {
        synchronized (this.zzakd) {
            final WeakReference<View> weakReference = this.zzblh.get(s);
            View view;
            if (weakReference == null) {
                view = null;
            }
            else {
                view = weakReference.get();
            }
            return zze.zzac(view);
        }
    }
    
    @Nullable
    zzb zzc(final zzj zzj) {
        return zzj.zza((View$OnClickListener)this);
    }
    
    public void zzc(final String s, final zzd zzd) {
        final View view = zze.zzae(zzd);
        final Object zzakd = this.zzakd;
        // monitorenter(zzakd)
        Label_0036: {
            if (view != null) {
                break Label_0036;
            }
            try {
                this.zzblh.remove(s);
                return;
                this.zzblh.put(s, new WeakReference<View>(view));
                view.setOnTouchListener((View$OnTouchListener)this);
                view.setClickable(true);
                view.setOnClickListener((View$OnClickListener)this);
            }
            finally {
            }
            // monitorexit(zzakd)
        }
    }
    
    Point zze(final MotionEvent motionEvent) {
        final int[] array = new int[2];
        this.zzblg.getLocationOnScreen(array);
        return new Point((int)(motionEvent.getRawX() - array[0]), (int)(motionEvent.getRawY() - array[1]));
    }
    
    public void zze(final zzd zzd) {
        while (true) {
            while (true) {
                final zzj zzbkb;
                synchronized (this.zzakd) {
                    this.zzh(null);
                    final zzj zzae = zze.zzae(zzd);
                    if (!(zzae instanceof zzj)) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzdf("Not an instance of native engine. This is most likely a transient error");
                        break;
                    }
                    if (this.zzaks != null) {
                        this.zzaks.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(0, 0));
                        this.zzblg.requestLayout();
                    }
                    this.zzblj = true;
                    zzbkb = zzae;
                    if (this.zzbkb != null && zzdi.zzbfz.get()) {
                        this.zzbkb.zzb((View)this.zzblg, this.zzblh);
                    }
                    if (this.zzbkb instanceof zzh && ((zzh)this.zzbkb).zzlv()) {
                        ((zzh)this.zzbkb).zzc(zzbkb);
                        if (zzdi.zzbfz.get()) {
                            this.zzaks.setClickable(false);
                        }
                        this.zzaks.removeAllViews();
                        this.zzbli = this.zzc(zzbkb);
                        if (this.zzbli != null) {
                            this.zzblh.put("1007", new WeakReference<View>((View)this.zzbli.zzlm()));
                            this.zzaks.addView((View)this.zzbli);
                        }
                        zzbkb.zza((View)this.zzblg, this.zzblh, (View$OnTouchListener)this, (View$OnClickListener)this);
                        zzkr.zzcrf.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                final zzlt zzlx = zzbkb.zzlx();
                                if (zzlx != null && zzl.this.zzaks != null) {
                                    zzl.this.zzaks.addView(zzlx.getView());
                                }
                                if (!(zzbkb instanceof zzh)) {
                                    zzl.this.zzd(zzbkb);
                                }
                            }
                        });
                        this.zzh((View)this.zzblg);
                        break;
                    }
                }
                this.zzbkb = zzbkb;
                if (zzbkb instanceof zzh) {
                    ((zzh)zzbkb).zzc(null);
                    continue;
                }
                continue;
            }
        }
    }
    
    void zzh(@Nullable final View view) {
        if (this.zzbkb != null) {
            zzi zzi;
            if (this.zzbkb instanceof zzh) {
                zzi = ((zzh)this.zzbkb).zzlw();
            }
            else {
                zzi = this.zzbkb;
            }
            if (zzi != null) {
                zzi.zzh(view);
            }
        }
    }
    
    Point zzi(final View view) {
        Point point3;
        if (this.zzbli != null && this.zzbli.zzlm().equals(view)) {
            final Point point = new Point();
            this.zzblg.getGlobalVisibleRect(new Rect(), point);
            final Point point2 = new Point();
            view.getGlobalVisibleRect(new Rect(), point2);
            point3 = new Point(point2.x - point.x, point2.y - point.y);
        }
        else {
            point3 = new Point();
            view.getGlobalVisibleRect(new Rect(), point3);
        }
        return point3;
    }
    
    int zzz(final int n) {
        return zzm.zzjr().zzc(this.zzbkb.getContext(), n);
    }
}
