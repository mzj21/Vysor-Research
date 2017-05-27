// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.formats.zzi;
import android.support.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.HashMap;
import java.util.concurrent.Future;
import java.util.ArrayList;
import android.content.Context;

@zziy
public class zzjy extends zzkm implements zzjx
{
    private final Context mContext;
    private final Object zzakd;
    private final zzke.zza zzcck;
    private final long zzcne;
    private final ArrayList<Future> zzcnp;
    private final ArrayList<String> zzcnq;
    private final HashMap<String, zzjs> zzcnr;
    private final List<zzjt> zzcns;
    private final HashSet<String> zzcnt;
    private final zzjr zzcnu;
    
    public zzjy(final Context context, final zzke.zza zza, final zzjr zzjr) {
        this(context, zza, zzjr, zzdi.zzbda.get());
    }
    
    zzjy(final Context mContext, final zzke.zza zzcck, final zzjr zzcnu, final long zzcne) {
        this.zzcnp = new ArrayList<Future>();
        this.zzcnq = new ArrayList<String>();
        this.zzcnr = new HashMap<String, zzjs>();
        this.zzcns = new ArrayList<zzjt>();
        this.zzcnt = new HashSet<String>();
        this.zzakd = new Object();
        this.mContext = mContext;
        this.zzcck = zzcck;
        this.zzcnu = zzcnu;
        this.zzcne = zzcne;
    }
    
    private zzke zza(final int n, @Nullable final String s, @Nullable final zzgg zzgg) {
        return new zzke(this.zzcck.zzcix.zzcfu, null, this.zzcck.zzcop.zzbsd, n, this.zzcck.zzcop.zzbse, this.zzcck.zzcop.zzche, this.zzcck.zzcop.orientation, this.zzcck.zzcop.zzbsj, this.zzcck.zzcix.zzcfx, this.zzcck.zzcop.zzchc, zzgg, null, s, this.zzcck.zzcof, null, this.zzcck.zzcop.zzchd, this.zzcck.zzaqz, this.zzcck.zzcop.zzchb, this.zzcck.zzcoj, this.zzcck.zzcop.zzchg, this.zzcck.zzcop.zzchh, this.zzcck.zzcod, null, this.zzcck.zzcop.zzchr, this.zzcck.zzcop.zzchs, this.zzcck.zzcop.zzcht, this.zzcck.zzcop.zzchu, this.zzcck.zzcop.zzchv, this.zzsu(), this.zzcck.zzcop.zzbsg, this.zzcck.zzcop.zzchy);
    }
    
    private zzke zza(final String s, final zzgg zzgg) {
        return this.zza(-2, s, zzgg);
    }
    
    private static String zza(final zzjt zzjt) {
        final String zzbro = zzjt.zzbro;
        return new StringBuilder(33 + String.valueOf(zzbro).length()).append(zzbro).append(".").append(zzao(zzjt.errorCode)).append(".").append(zzjt.zzbtj).toString();
    }
    
    private void zza(final String s, final String s2, final zzgg zzgg) {
        synchronized (this.zzakd) {
            final zzjz zzcl = this.zzcnu.zzcl(s);
            if (zzcl == null || zzcl.zzsw() == null || zzcl.zzsv() == null) {
                this.zzcns.add(new zzjt.zza().zzco(zzgg.zzbro).zzcn(s).zzl(0L).zzaz(7).zzss());
            }
            else {
                final zzjs zza = this.zza(s, s2, zzgg, zzcl);
                this.zzcnp.add((Future)zza.zzqw());
                this.zzcnq.add(s);
                this.zzcnr.put(s, zza);
            }
        }
    }
    
    private static int zzao(final int n) {
        int n2 = 0;
        switch (n) {
            default: {
                n2 = 6;
                break;
            }
            case 6: {
                n2 = 0;
                break;
            }
            case 3: {
                n2 = 1;
                break;
            }
            case 4: {
                n2 = 2;
                break;
            }
            case 7: {
                n2 = 3;
                break;
            }
            case 5: {
                n2 = 4;
                break;
            }
        }
        return n2;
    }
    
    private zzke zzst() {
        return this.zza(3, null, null);
    }
    
    private String zzsu() {
        final StringBuilder sb = new StringBuilder("");
        String s;
        if (this.zzcns == null) {
            s = sb.toString();
        }
        else {
            for (final zzjt zzjt : this.zzcns) {
                if (zzjt != null && !TextUtils.isEmpty((CharSequence)zzjt.zzbro)) {
                    sb.append(String.valueOf(zza(zzjt)).concat("_"));
                }
            }
            s = sb.substring(0, Math.max(0, -1 + sb.length()));
        }
        return s;
    }
    
    @Override
    public void onStop() {
    }
    
    protected zzjs zza(final String s, final String s2, final zzgg zzgg, final zzjz zzjz) {
        return new zzjs(this.mContext, s, s2, zzgg, this.zzcck, zzjz, this, this.zzcne);
    }
    
    @Override
    public void zza(final String s, final int n) {
    }
    
    @Override
    public void zzcm(final String s) {
        synchronized (this.zzakd) {
            this.zzcnt.add(s);
        }
    }
    
    @Override
    public void zzfc() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzjy.zzcck:Lcom/google/android/gms/internal/zzke$zza;
        //     4: getfield        com/google/android/gms/internal/zzke$zza.zzcof:Lcom/google/android/gms/internal/zzgh;
        //     7: getfield        com/google/android/gms/internal/zzgh.zzbsb:Ljava/util/List;
        //    10: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    15: astore_1       
        //    16: aload_1        
        //    17: invokeinterface java/util/Iterator.hasNext:()Z
        //    22: ifeq            146
        //    25: aload_1        
        //    26: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    31: checkcast       Lcom/google/android/gms/internal/zzgg;
        //    34: astore          35
        //    36: aload           35
        //    38: getfield        com/google/android/gms/internal/zzgg.zzbrt:Ljava/lang/String;
        //    41: astore          36
        //    43: aload           35
        //    45: getfield        com/google/android/gms/internal/zzgg.zzbrn:Ljava/util/List;
        //    48: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    53: astore          37
        //    55: aload           37
        //    57: invokeinterface java/util/Iterator.hasNext:()Z
        //    62: ifeq            16
        //    65: aload           37
        //    67: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    72: checkcast       Ljava/lang/String;
        //    75: astore          38
        //    77: ldc_w           "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter"
        //    80: aload           38
        //    82: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    85: ifne            99
        //    88: ldc_w           "com.google.ads.mediation.customevent.CustomEventAdapter"
        //    91: aload           38
        //    93: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    96: ifeq            120
        //    99: new             Lorg/json/JSONObject;
        //   102: dup            
        //   103: aload           36
        //   105: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   108: ldc_w           "class_name"
        //   111: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   114: astore          40
        //   116: aload           40
        //   118: astore          38
        //   120: aload_0        
        //   121: aload           38
        //   123: aload           36
        //   125: aload           35
        //   127: invokespecial   com/google/android/gms/internal/zzjy.zza:(Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/internal/zzgg;)V
        //   130: goto            55
        //   133: astore          39
        //   135: ldc_w           "Unable to determine custom event class name, skipping..."
        //   138: aload           39
        //   140: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   143: goto            55
        //   146: iconst_0       
        //   147: istore_2       
        //   148: iload_2        
        //   149: aload_0        
        //   150: getfield        com/google/android/gms/internal/zzjy.zzcnp:Ljava/util/ArrayList;
        //   153: invokevirtual   java/util/ArrayList.size:()I
        //   156: if_icmpge       426
        //   159: aload_0        
        //   160: getfield        com/google/android/gms/internal/zzjy.zzcnp:Ljava/util/ArrayList;
        //   163: iload_2        
        //   164: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   167: checkcast       Ljava/util/concurrent/Future;
        //   170: invokeinterface java/util/concurrent/Future.get:()Ljava/lang/Object;
        //   175: pop            
        //   176: aload_0        
        //   177: getfield        com/google/android/gms/internal/zzjy.zzakd:Ljava/lang/Object;
        //   180: astore          24
        //   182: aload           24
        //   184: monitorenter   
        //   185: aload_0        
        //   186: getfield        com/google/android/gms/internal/zzjy.zzcnq:Ljava/util/ArrayList;
        //   189: iload_2        
        //   190: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   193: checkcast       Ljava/lang/String;
        //   196: astore          26
        //   198: aload           26
        //   200: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   203: ifne            240
        //   206: aload_0        
        //   207: getfield        com/google/android/gms/internal/zzjy.zzcnr:Ljava/util/HashMap;
        //   210: aload           26
        //   212: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   215: checkcast       Lcom/google/android/gms/internal/zzjs;
        //   218: astore          33
        //   220: aload           33
        //   222: ifnull          240
        //   225: aload_0        
        //   226: getfield        com/google/android/gms/internal/zzjy.zzcns:Ljava/util/List;
        //   229: aload           33
        //   231: invokevirtual   com/google/android/gms/internal/zzjs.zzsp:()Lcom/google/android/gms/internal/zzjt;
        //   234: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   239: pop            
        //   240: aload           24
        //   242: monitorexit    
        //   243: aload_0        
        //   244: getfield        com/google/android/gms/internal/zzjy.zzakd:Ljava/lang/Object;
        //   247: astore          27
        //   249: aload           27
        //   251: monitorenter   
        //   252: aload_0        
        //   253: getfield        com/google/android/gms/internal/zzjy.zzcnt:Ljava/util/HashSet;
        //   256: aload_0        
        //   257: getfield        com/google/android/gms/internal/zzjy.zzcnq:Ljava/util/ArrayList;
        //   260: iload_2        
        //   261: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   264: invokevirtual   java/util/HashSet.contains:(Ljava/lang/Object;)Z
        //   267: ifeq            632
        //   270: aload_0        
        //   271: getfield        com/google/android/gms/internal/zzjy.zzcnq:Ljava/util/ArrayList;
        //   274: iload_2        
        //   275: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   278: checkcast       Ljava/lang/String;
        //   281: astore          29
        //   283: aload_0        
        //   284: getfield        com/google/android/gms/internal/zzjy.zzcnr:Ljava/util/HashMap;
        //   287: aload           29
        //   289: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   292: ifnull          626
        //   295: aload_0        
        //   296: getfield        com/google/android/gms/internal/zzjy.zzcnr:Ljava/util/HashMap;
        //   299: aload           29
        //   301: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   304: checkcast       Lcom/google/android/gms/internal/zzjs;
        //   307: invokevirtual   com/google/android/gms/internal/zzjs.zzsq:()Lcom/google/android/gms/internal/zzgg;
        //   310: astore          30
        //   312: aload_0        
        //   313: aload           29
        //   315: aload           30
        //   317: invokespecial   com/google/android/gms/internal/zzjy.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzgg;)Lcom/google/android/gms/internal/zzke;
        //   320: astore          31
        //   322: getstatic       com/google/android/gms/ads/internal/util/client/zza.zzctj:Landroid/os/Handler;
        //   325: new             Lcom/google/android/gms/internal/zzjy$1;
        //   328: dup            
        //   329: aload_0        
        //   330: aload           31
        //   332: invokespecial   com/google/android/gms/internal/zzjy$1.<init>:(Lcom/google/android/gms/internal/zzjy;Lcom/google/android/gms/internal/zzke;)V
        //   335: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   338: pop            
        //   339: aload           27
        //   341: monitorexit    
        //   342: return         
        //   343: astore          25
        //   345: aload           24
        //   347: monitorexit    
        //   348: aload           25
        //   350: athrow         
        //   351: astore          17
        //   353: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   356: invokevirtual   java/lang/Thread.interrupt:()V
        //   359: aload_0        
        //   360: getfield        com/google/android/gms/internal/zzjy.zzakd:Ljava/lang/Object;
        //   363: astore          18
        //   365: aload           18
        //   367: monitorenter   
        //   368: aload_0        
        //   369: getfield        com/google/android/gms/internal/zzjy.zzcnq:Ljava/util/ArrayList;
        //   372: iload_2        
        //   373: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   376: checkcast       Ljava/lang/String;
        //   379: astore          20
        //   381: aload           20
        //   383: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   386: ifne            423
        //   389: aload_0        
        //   390: getfield        com/google/android/gms/internal/zzjy.zzcnr:Ljava/util/HashMap;
        //   393: aload           20
        //   395: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   398: checkcast       Lcom/google/android/gms/internal/zzjs;
        //   401: astore          21
        //   403: aload           21
        //   405: ifnull          423
        //   408: aload_0        
        //   409: getfield        com/google/android/gms/internal/zzjy.zzcns:Ljava/util/List;
        //   412: aload           21
        //   414: invokevirtual   com/google/android/gms/internal/zzjs.zzsp:()Lcom/google/android/gms/internal/zzjt;
        //   417: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   422: pop            
        //   423: aload           18
        //   425: monitorexit    
        //   426: aload_0        
        //   427: invokespecial   com/google/android/gms/internal/zzjy.zzst:()Lcom/google/android/gms/internal/zzke;
        //   430: astore_3       
        //   431: getstatic       com/google/android/gms/ads/internal/util/client/zza.zzctj:Landroid/os/Handler;
        //   434: new             Lcom/google/android/gms/internal/zzjy$2;
        //   437: dup            
        //   438: aload_0        
        //   439: aload_3        
        //   440: invokespecial   com/google/android/gms/internal/zzjy$2.<init>:(Lcom/google/android/gms/internal/zzjy;Lcom/google/android/gms/internal/zzke;)V
        //   443: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   446: pop            
        //   447: goto            342
        //   450: astore          19
        //   452: aload           18
        //   454: monitorexit    
        //   455: aload           19
        //   457: athrow         
        //   458: astore          11
        //   460: ldc_w           "Unable to resolve rewarded adapter."
        //   463: aload           11
        //   465: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   468: aload_0        
        //   469: getfield        com/google/android/gms/internal/zzjy.zzakd:Ljava/lang/Object;
        //   472: astore          12
        //   474: aload           12
        //   476: monitorenter   
        //   477: aload_0        
        //   478: getfield        com/google/android/gms/internal/zzjy.zzcnq:Ljava/util/ArrayList;
        //   481: iload_2        
        //   482: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   485: checkcast       Ljava/lang/String;
        //   488: astore          14
        //   490: aload           14
        //   492: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   495: ifne            532
        //   498: aload_0        
        //   499: getfield        com/google/android/gms/internal/zzjy.zzcnr:Ljava/util/HashMap;
        //   502: aload           14
        //   504: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   507: checkcast       Lcom/google/android/gms/internal/zzjs;
        //   510: astore          15
        //   512: aload           15
        //   514: ifnull          532
        //   517: aload_0        
        //   518: getfield        com/google/android/gms/internal/zzjy.zzcns:Ljava/util/List;
        //   521: aload           15
        //   523: invokevirtual   com/google/android/gms/internal/zzjs.zzsp:()Lcom/google/android/gms/internal/zzjt;
        //   526: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   531: pop            
        //   532: aload           12
        //   534: monitorexit    
        //   535: goto            646
        //   538: astore          13
        //   540: aload           12
        //   542: monitorexit    
        //   543: aload           13
        //   545: athrow         
        //   546: astore          5
        //   548: aload_0        
        //   549: getfield        com/google/android/gms/internal/zzjy.zzakd:Ljava/lang/Object;
        //   552: astore          6
        //   554: aload           6
        //   556: monitorenter   
        //   557: aload_0        
        //   558: getfield        com/google/android/gms/internal/zzjy.zzcnq:Ljava/util/ArrayList;
        //   561: iload_2        
        //   562: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   565: checkcast       Ljava/lang/String;
        //   568: astore          8
        //   570: aload           8
        //   572: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   575: ifne            612
        //   578: aload_0        
        //   579: getfield        com/google/android/gms/internal/zzjy.zzcnr:Ljava/util/HashMap;
        //   582: aload           8
        //   584: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   587: checkcast       Lcom/google/android/gms/internal/zzjs;
        //   590: astore          9
        //   592: aload           9
        //   594: ifnull          612
        //   597: aload_0        
        //   598: getfield        com/google/android/gms/internal/zzjy.zzcns:Ljava/util/List;
        //   601: aload           9
        //   603: invokevirtual   com/google/android/gms/internal/zzjs.zzsp:()Lcom/google/android/gms/internal/zzjt;
        //   606: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   611: pop            
        //   612: aload           6
        //   614: monitorexit    
        //   615: aload           5
        //   617: athrow         
        //   618: astore          7
        //   620: aload           6
        //   622: monitorexit    
        //   623: aload           7
        //   625: athrow         
        //   626: aconst_null    
        //   627: astore          30
        //   629: goto            312
        //   632: aload           27
        //   634: monitorexit    
        //   635: goto            646
        //   638: astore          28
        //   640: aload           27
        //   642: monitorexit    
        //   643: aload           28
        //   645: athrow         
        //   646: iinc            2, 1
        //   649: goto            148
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  99     116    133    146    Lorg/json/JSONException;
        //  159    176    351    458    Ljava/lang/InterruptedException;
        //  159    176    458    546    Ljava/lang/Exception;
        //  159    176    546    626    Any
        //  185    243    343    351    Any
        //  252    342    638    646    Any
        //  345    348    343    351    Any
        //  353    359    546    626    Any
        //  368    426    450    458    Any
        //  452    455    450    458    Any
        //  460    468    546    626    Any
        //  477    543    538    546    Any
        //  557    615    618    626    Any
        //  620    623    618    626    Any
        //  632    643    638    646    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:635)
        //     at java.util.ArrayList.get(ArrayList.java:411)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
}
