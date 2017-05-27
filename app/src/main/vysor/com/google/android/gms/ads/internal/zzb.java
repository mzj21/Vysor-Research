// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzlj;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzia;
import com.google.android.gms.ads.internal.purchase.zzf;
import android.content.Intent;
import com.google.android.gms.internal.zzhw;
import com.google.android.gms.ads.internal.purchase.GInAppPurchaseManagerInfoParcel;
import com.google.android.gms.ads.internal.client.zzm;
import java.util.ArrayList;
import com.google.android.gms.ads.internal.purchase.zzc;
import java.util.concurrent.Future;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzke;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.internal.zzkg;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import android.os.Handler;
import com.google.android.gms.internal.zzhu;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;
import com.google.android.gms.internal.zzgq;
import android.os.Messenger;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzgi;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.ads.internal.purchase.zzj;
import com.google.android.gms.ads.internal.overlay.zzg;

@zziy
public abstract class zzb extends zza implements zzg, zzj, zzs, zzex, zzgi
{
    private final Messenger mMessenger;
    protected final zzgq zzals;
    protected transient boolean zzalt;
    
    public zzb(final Context context, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final VersionInfoParcel versionInfoParcel, final zzd zzd) {
        this(new zzv(context, adSizeParcel, s, versionInfoParcel), zzgq, null, zzd);
    }
    
    protected zzb(final zzv zzv, final zzgq zzals, @Nullable final zzr zzr, final zzd zzd) {
        super(zzv, zzr, zzd);
        this.zzals = zzals;
        this.mMessenger = new Messenger((Handler)new zzhu(this.zzall.zzahn));
        this.zzalt = false;
    }
    
    private AdRequestInfoParcel.zza zza(final AdRequestParcel p0, final Bundle p1, final zzkg p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //     4: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //     7: invokevirtual   android/content/Context.getApplicationInfo:()Landroid/content/pm/ApplicationInfo;
        //    10: astore          4
        //    12: aload_0        
        //    13: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    16: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //    19: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    22: aload           4
        //    24: getfield        android/content/pm/ApplicationInfo.packageName:Ljava/lang/String;
        //    27: iconst_0       
        //    28: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    31: astore          54
        //    33: aload           54
        //    35: astore          6
        //    37: aload_0        
        //    38: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    41: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //    44: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    47: invokevirtual   android/content/res/Resources.getDisplayMetrics:()Landroid/util/DisplayMetrics;
        //    50: astore          7
        //    52: aload_0        
        //    53: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    56: getfield        com/google/android/gms/ads/internal/zzv.zzaqw:Lcom/google/android/gms/ads/internal/zzv$zza;
        //    59: astore          8
        //    61: aconst_null    
        //    62: astore          9
        //    64: aload           8
        //    66: ifnull          284
        //    69: aload_0        
        //    70: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    73: getfield        com/google/android/gms/ads/internal/zzv.zzaqw:Lcom/google/android/gms/ads/internal/zzv$zza;
        //    76: invokevirtual   com/google/android/gms/ads/internal/zzv$zza.getParent:()Landroid/view/ViewParent;
        //    79: astore          42
        //    81: aconst_null    
        //    82: astore          9
        //    84: aload           42
        //    86: ifnull          284
        //    89: iconst_2       
        //    90: newarray        I
        //    92: astore          43
        //    94: aload_0        
        //    95: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //    98: getfield        com/google/android/gms/ads/internal/zzv.zzaqw:Lcom/google/android/gms/ads/internal/zzv$zza;
        //   101: aload           43
        //   103: invokevirtual   com/google/android/gms/ads/internal/zzv$zza.getLocationOnScreen:([I)V
        //   106: aload           43
        //   108: iconst_0       
        //   109: iaload         
        //   110: istore          44
        //   112: aload           43
        //   114: iconst_1       
        //   115: iaload         
        //   116: istore          45
        //   118: aload_0        
        //   119: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   122: getfield        com/google/android/gms/ads/internal/zzv.zzaqw:Lcom/google/android/gms/ads/internal/zzv$zza;
        //   125: invokevirtual   com/google/android/gms/ads/internal/zzv$zza.getWidth:()I
        //   128: istore          46
        //   130: aload_0        
        //   131: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   134: getfield        com/google/android/gms/ads/internal/zzv.zzaqw:Lcom/google/android/gms/ads/internal/zzv$zza;
        //   137: invokevirtual   com/google/android/gms/ads/internal/zzv$zza.getHeight:()I
        //   140: istore          47
        //   142: aload_0        
        //   143: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   146: getfield        com/google/android/gms/ads/internal/zzv.zzaqw:Lcom/google/android/gms/ads/internal/zzv$zza;
        //   149: invokevirtual   com/google/android/gms/ads/internal/zzv$zza.isShown:()Z
        //   152: istore          48
        //   154: iconst_0       
        //   155: istore          49
        //   157: iload           48
        //   159: ifeq            229
        //   162: iload           44
        //   164: iload           46
        //   166: iadd           
        //   167: istore          50
        //   169: iconst_0       
        //   170: istore          49
        //   172: iload           50
        //   174: ifle            229
        //   177: iload           45
        //   179: iload           47
        //   181: iadd           
        //   182: istore          51
        //   184: iconst_0       
        //   185: istore          49
        //   187: iload           51
        //   189: ifle            229
        //   192: aload           7
        //   194: getfield        android/util/DisplayMetrics.widthPixels:I
        //   197: istore          52
        //   199: iconst_0       
        //   200: istore          49
        //   202: iload           44
        //   204: iload           52
        //   206: if_icmpgt       229
        //   209: aload           7
        //   211: getfield        android/util/DisplayMetrics.heightPixels:I
        //   214: istore          53
        //   216: iconst_0       
        //   217: istore          49
        //   219: iload           45
        //   221: iload           53
        //   223: if_icmpgt       229
        //   226: iconst_1       
        //   227: istore          49
        //   229: new             Landroid/os/Bundle;
        //   232: dup            
        //   233: iconst_5       
        //   234: invokespecial   android/os/Bundle.<init>:(I)V
        //   237: astore          9
        //   239: aload           9
        //   241: ldc             "x"
        //   243: iload           44
        //   245: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   248: aload           9
        //   250: ldc             "y"
        //   252: iload           45
        //   254: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   257: aload           9
        //   259: ldc             "mWidth"
        //   261: iload           46
        //   263: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   266: aload           9
        //   268: ldc             "mHeight"
        //   270: iload           47
        //   272: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   275: aload           9
        //   277: ldc             "visible"
        //   279: iload           49
        //   281: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   284: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //   287: invokevirtual   com/google/android/gms/internal/zzkh.zztk:()Ljava/lang/String;
        //   290: astore          10
        //   292: aload_0        
        //   293: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   296: new             Lcom/google/android/gms/internal/zzkf;
        //   299: dup            
        //   300: aload           10
        //   302: aload_0        
        //   303: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   306: getfield        com/google/android/gms/ads/internal/zzv.zzaqt:Ljava/lang/String;
        //   309: invokespecial   com/google/android/gms/internal/zzkf.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   312: putfield        com/google/android/gms/ads/internal/zzv.zzarc:Lcom/google/android/gms/internal/zzkf;
        //   315: aload_0        
        //   316: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   319: getfield        com/google/android/gms/ads/internal/zzv.zzarc:Lcom/google/android/gms/internal/zzkf;
        //   322: aload_1        
        //   323: invokevirtual   com/google/android/gms/internal/zzkf.zzt:(Lcom/google/android/gms/ads/internal/client/AdRequestParcel;)V
        //   326: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   329: aload_0        
        //   330: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   333: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //   336: aload_0        
        //   337: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   340: getfield        com/google/android/gms/ads/internal/zzv.zzaqw:Lcom/google/android/gms/ads/internal/zzv$zza;
        //   343: aload_0        
        //   344: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   347: getfield        com/google/android/gms/ads/internal/zzv.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   350: invokevirtual   com/google/android/gms/internal/zzkr.zza:(Landroid/content/Context;Landroid/view/View;Lcom/google/android/gms/ads/internal/client/AdSizeParcel;)Ljava/lang/String;
        //   353: astore          11
        //   355: lconst_0       
        //   356: lstore          12
        //   358: aload_0        
        //   359: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   362: getfield        com/google/android/gms/ads/internal/zzv.zzarg:Lcom/google/android/gms/ads/internal/client/zzy;
        //   365: ifnull          386
        //   368: aload_0        
        //   369: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   372: getfield        com/google/android/gms/ads/internal/zzv.zzarg:Lcom/google/android/gms/ads/internal/client/zzy;
        //   375: invokeinterface com/google/android/gms/ads/internal/client/zzy.getValue:()J
        //   380: lstore          40
        //   382: lload           40
        //   384: lstore          12
        //   386: invokestatic    java/util/UUID.randomUUID:()Ljava/util/UUID;
        //   389: invokevirtual   java/util/UUID.toString:()Ljava/lang/String;
        //   392: astore          14
        //   394: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //   397: aload_0        
        //   398: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   401: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //   404: aload_0        
        //   405: aload           10
        //   407: invokevirtual   com/google/android/gms/internal/zzkh.zza:(Landroid/content/Context;Lcom/google/android/gms/internal/zzkj;Ljava/lang/String;)Landroid/os/Bundle;
        //   410: astore          15
        //   412: new             Ljava/util/ArrayList;
        //   415: dup            
        //   416: invokespecial   java/util/ArrayList.<init>:()V
        //   419: astore          16
        //   421: iconst_0       
        //   422: istore          17
        //   424: iload           17
        //   426: aload_0        
        //   427: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   430: getfield        com/google/android/gms/ads/internal/zzv.zzarm:Landroid/support/v4/util/SimpleArrayMap;
        //   433: invokevirtual   android/support/v4/util/SimpleArrayMap.size:()I
        //   436: if_icmpge       486
        //   439: aload           16
        //   441: aload_0        
        //   442: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   445: getfield        com/google/android/gms/ads/internal/zzv.zzarm:Landroid/support/v4/util/SimpleArrayMap;
        //   448: iload           17
        //   450: invokevirtual   android/support/v4/util/SimpleArrayMap.keyAt:(I)Ljava/lang/Object;
        //   453: checkcast       Ljava/lang/String;
        //   456: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   461: pop            
        //   462: iinc            17, 1
        //   465: goto            424
        //   468: astore          5
        //   470: aconst_null    
        //   471: astore          6
        //   473: goto            37
        //   476: astore          39
        //   478: ldc             "Cannot get correlation id, default to 0."
        //   480: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   483: goto            386
        //   486: aload_0        
        //   487: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   490: getfield        com/google/android/gms/ads/internal/zzv.zzarh:Lcom/google/android/gms/internal/zzhx;
        //   493: ifnull          873
        //   496: iconst_1       
        //   497: istore          18
        //   499: aload_0        
        //   500: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   503: getfield        com/google/android/gms/ads/internal/zzv.zzari:Lcom/google/android/gms/internal/zzib;
        //   506: ifnull          879
        //   509: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //   512: invokevirtual   com/google/android/gms/internal/zzkh.zzty:()Z
        //   515: ifeq            879
        //   518: iconst_1       
        //   519: istore          19
        //   521: aload_0        
        //   522: getfield        com/google/android/gms/ads/internal/zzb.zzalo:Lcom/google/android/gms/ads/internal/zzd;
        //   525: getfield        com/google/android/gms/ads/internal/zzd.zzame:Lcom/google/android/gms/ads/internal/overlay/zzm;
        //   528: aload_0        
        //   529: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   532: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //   535: invokeinterface com/google/android/gms/ads/internal/overlay/zzm.zzr:(Landroid/content/Context;)Z
        //   540: istore          20
        //   542: ldc_w           ""
        //   545: astore          21
        //   547: getstatic       com/google/android/gms/internal/zzdi.zzbhh:Lcom/google/android/gms/internal/zzde;
        //   550: invokevirtual   com/google/android/gms/internal/zzde.get:()Ljava/lang/Object;
        //   553: checkcast       Ljava/lang/Boolean;
        //   556: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   559: ifeq            598
        //   562: ldc_w           "Getting webview cookie from CookieManager."
        //   565: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //   568: invokestatic    com/google/android/gms/ads/internal/zzu.zzgb:()Lcom/google/android/gms/internal/zzks;
        //   571: aload_0        
        //   572: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   575: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //   578: invokevirtual   com/google/android/gms/internal/zzks.zzao:(Landroid/content/Context;)Landroid/webkit/CookieManager;
        //   581: astore          37
        //   583: aload           37
        //   585: ifnull          598
        //   588: aload           37
        //   590: ldc_w           "googleads.g.doubleclick.net"
        //   593: invokevirtual   android/webkit/CookieManager.getCookie:(Ljava/lang/String;)Ljava/lang/String;
        //   596: astore          21
        //   598: aconst_null    
        //   599: astore          22
        //   601: aload_3        
        //   602: ifnull          611
        //   605: aload_3        
        //   606: invokevirtual   com/google/android/gms/internal/zzkg.zzth:()Ljava/lang/String;
        //   609: astore          22
        //   611: aload_0        
        //   612: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   615: getfield        com/google/android/gms/ads/internal/zzv.zzaqz:Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
        //   618: astore          23
        //   620: aload_0        
        //   621: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   624: getfield        com/google/android/gms/ads/internal/zzv.zzaqt:Ljava/lang/String;
        //   627: astore          24
        //   629: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //   632: invokevirtual   com/google/android/gms/internal/zzkh.getSessionId:()Ljava/lang/String;
        //   635: astore          25
        //   637: aload_0        
        //   638: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   641: getfield        com/google/android/gms/ads/internal/zzv.zzaqv:Lcom/google/android/gms/ads/internal/util/client/VersionInfoParcel;
        //   644: astore          26
        //   646: aload_0        
        //   647: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   650: getfield        com/google/android/gms/ads/internal/zzv.zzarr:Ljava/util/List;
        //   653: astore          27
        //   655: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //   658: invokevirtual   com/google/android/gms/internal/zzkh.zzto:()Z
        //   661: istore          28
        //   663: aload_0        
        //   664: getfield        com/google/android/gms/ads/internal/zzb.mMessenger:Landroid/os/Messenger;
        //   667: astore          29
        //   669: aload           7
        //   671: getfield        android/util/DisplayMetrics.widthPixels:I
        //   674: istore          30
        //   676: aload           7
        //   678: getfield        android/util/DisplayMetrics.heightPixels:I
        //   681: istore          31
        //   683: aload           7
        //   685: getfield        android/util/DisplayMetrics.density:F
        //   688: fstore          32
        //   690: invokestatic    com/google/android/gms/internal/zzdi.zzkr:()Ljava/util/List;
        //   693: astore          33
        //   695: aload_0        
        //   696: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   699: getfield        com/google/android/gms/ads/internal/zzv.zzaqs:Ljava/lang/String;
        //   702: astore          34
        //   704: aload_0        
        //   705: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   708: getfield        com/google/android/gms/ads/internal/zzv.zzarn:Lcom/google/android/gms/ads/internal/formats/NativeAdOptionsParcel;
        //   711: astore          35
        //   713: new             Lcom/google/android/gms/ads/internal/request/CapabilityParcel;
        //   716: dup            
        //   717: iload           18
        //   719: iload           19
        //   721: iload           20
        //   723: invokespecial   com/google/android/gms/ads/internal/request/CapabilityParcel.<init>:(ZZZ)V
        //   726: astore          36
        //   728: new             Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel$zza;
        //   731: dup            
        //   732: aload           9
        //   734: aload_1        
        //   735: aload           23
        //   737: aload           24
        //   739: aload           4
        //   741: aload           6
        //   743: aload           10
        //   745: aload           25
        //   747: aload           26
        //   749: aload           15
        //   751: aload           27
        //   753: aload           16
        //   755: aload_2        
        //   756: iload           28
        //   758: aload           29
        //   760: iload           30
        //   762: iload           31
        //   764: fload           32
        //   766: aload           11
        //   768: lload           12
        //   770: aload           14
        //   772: aload           33
        //   774: aload           34
        //   776: aload           35
        //   778: aload           36
        //   780: aload_0        
        //   781: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   784: invokevirtual   com/google/android/gms/ads/internal/zzv.zzhg:()Ljava/lang/String;
        //   787: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   790: invokevirtual   com/google/android/gms/internal/zzkr.zzfe:()F
        //   793: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   796: invokevirtual   com/google/android/gms/internal/zzkr.zzfg:()Z
        //   799: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   802: aload_0        
        //   803: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   806: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //   809: invokevirtual   com/google/android/gms/internal/zzkr.zzam:(Landroid/content/Context;)I
        //   812: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   815: aload_0        
        //   816: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   819: getfield        com/google/android/gms/ads/internal/zzv.zzaqw:Lcom/google/android/gms/ads/internal/zzv$zza;
        //   822: invokevirtual   com/google/android/gms/internal/zzkr.zzn:(Landroid/view/View;)I
        //   825: aload_0        
        //   826: getfield        com/google/android/gms/ads/internal/zzb.zzall:Lcom/google/android/gms/ads/internal/zzv;
        //   829: getfield        com/google/android/gms/ads/internal/zzv.zzahn:Landroid/content/Context;
        //   832: instanceof      Landroid/app/Activity;
        //   835: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //   838: invokevirtual   com/google/android/gms/internal/zzkh.zzts:()Z
        //   841: aload           21
        //   843: aload           22
        //   845: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //   848: invokevirtual   com/google/android/gms/internal/zzkh.zztv:()Z
        //   851: invokestatic    com/google/android/gms/ads/internal/zzu.zzgw:()Lcom/google/android/gms/internal/zzfi;
        //   854: invokevirtual   com/google/android/gms/internal/zzfi.zzmi:()I
        //   857: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   860: invokevirtual   com/google/android/gms/internal/zzkr.zzul:()Landroid/os/Bundle;
        //   863: invokestatic    com/google/android/gms/ads/internal/zzu.zzgh:()Lcom/google/android/gms/internal/zzkv;
        //   866: invokevirtual   com/google/android/gms/internal/zzkv.zzut:()Ljava/lang/String;
        //   869: invokespecial   com/google/android/gms/ads/internal/request/AdRequestInfoParcel$zza.<init>:(Landroid/os/Bundle;Lcom/google/android/gms/ads/internal/client/AdRequestParcel;Lcom/google/android/gms/ads/internal/client/AdSizeParcel;Ljava/lang/String;Landroid/content/pm/ApplicationInfo;Landroid/content/pm/PackageInfo;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/ads/internal/util/client/VersionInfoParcel;Landroid/os/Bundle;Ljava/util/List;Ljava/util/List;Landroid/os/Bundle;ZLandroid/os/Messenger;IIFLjava/lang/String;JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/google/android/gms/ads/internal/formats/NativeAdOptionsParcel;Lcom/google/android/gms/ads/internal/request/CapabilityParcel;Ljava/lang/String;FZIIZZLjava/lang/String;Ljava/lang/String;ZILandroid/os/Bundle;Ljava/lang/String;)V
        //   872: areturn        
        //   873: iconst_0       
        //   874: istore          18
        //   876: goto            499
        //   879: iconst_0       
        //   880: istore          19
        //   882: goto            521
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  12     33     468    476    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  368    382    476    486    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0386:
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
    
    public String getMediationAdapterClassName() {
        String zzbtg;
        if (this.zzall.zzara == null) {
            zzbtg = null;
        }
        else {
            zzbtg = this.zzall.zzara.zzbtg;
        }
        return zzbtg;
    }
    
    @Override
    public void onAdClicked() {
        if (this.zzall.zzara == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Ad state was null when trying to ping click URLs.");
        }
        else {
            if (this.zzall.zzara.zzcof != null && this.zzall.zzara.zzcof.zzbsd != null) {
                com.google.android.gms.ads.internal.zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara, this.zzall.zzaqt, false, this.zzall.zzara.zzcof.zzbsd);
            }
            if (this.zzall.zzara.zzbte != null && this.zzall.zzara.zzbte.zzbrq != null) {
                com.google.android.gms.ads.internal.zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara, this.zzall.zzaqt, false, this.zzall.zzara.zzbte.zzbrq);
            }
            super.onAdClicked();
        }
    }
    
    @Override
    public void onPause() {
        this.zzaln.zzl(this.zzall.zzara);
    }
    
    @Override
    public void onResume() {
        this.zzaln.zzm(this.zzall.zzara);
    }
    
    @Override
    public void pause() {
        zzac.zzhq("pause must be called on the main UI thread.");
        if (this.zzall.zzara != null && this.zzall.zzara.zzbyh != null && this.zzall.zzhc()) {
            com.google.android.gms.ads.internal.zzu.zzgb().zzl(this.zzall.zzara.zzbyh);
        }
        while (true) {
            if (this.zzall.zzara == null || this.zzall.zzara.zzbtf == null) {
                break Label_0094;
            }
            try {
                this.zzall.zzara.zzbtf.pause();
                this.zzaln.zzl(this.zzall.zzara);
                this.zzalk.pause();
            }
            catch (RemoteException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not pause mediation adapter.");
                continue;
            }
            break;
        }
    }
    
    public void recordImpression() {
        this.zza(this.zzall.zzara, false);
    }
    
    @Override
    public void resume() {
        zzac.zzhq("resume must be called on the main UI thread.");
        final zzke zzara = this.zzall.zzara;
        zzlt zzbyh = null;
        if (zzara != null) {
            final zzlt zzbyh2 = this.zzall.zzara.zzbyh;
            zzbyh = null;
            if (zzbyh2 != null) {
                zzbyh = this.zzall.zzara.zzbyh;
            }
        }
        if (zzbyh != null && this.zzall.zzhc()) {
            com.google.android.gms.ads.internal.zzu.zzgb().zzm(this.zzall.zzara.zzbyh);
        }
        while (true) {
            if (this.zzall.zzara == null || this.zzall.zzara.zzbtf == null) {
                break Label_0119;
            }
            try {
                this.zzall.zzara.zzbtf.resume();
                if (zzbyh == null || !zzbyh.zzvx()) {
                    this.zzalk.resume();
                }
                this.zzaln.zzm(this.zzall.zzara);
            }
            catch (RemoteException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not resume mediation adapter.");
                continue;
            }
            break;
        }
    }
    
    public void showInterstitial() {
        throw new IllegalStateException("showInterstitial is not supported for current ad type");
    }
    
    @Override
    public void zza(final zzhx zzarh) {
        zzac.zzhq("setInAppPurchaseListener must be called on the main UI thread.");
        this.zzall.zzarh = zzarh;
    }
    
    @Override
    public void zza(final zzib zzari, @Nullable final String s) {
        zzac.zzhq("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.zzall.zzars = new zzk(s);
        this.zzall.zzari = zzari;
        if (!com.google.android.gms.ads.internal.zzu.zzgd().zztn() && zzari != null) {
            final Future future = (Future)new zzc(this.zzall.zzahn, this.zzall.zzari, this.zzall.zzars).zzqw();
        }
    }
    
    protected void zza(@Nullable final zzke zzke, final boolean b) {
        if (zzke == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Ad state was null when trying to ping impression URLs.");
        }
        else {
            super.zzc(zzke);
            if (zzke.zzcof != null && zzke.zzcof.zzbse != null) {
                com.google.android.gms.ads.internal.zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, zzke, this.zzall.zzaqt, b, zzke.zzcof.zzbse);
            }
            if (zzke.zzbte != null && zzke.zzbte.zzbrr != null) {
                com.google.android.gms.ads.internal.zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, zzke, this.zzall.zzaqt, b, zzke.zzbte.zzbrr);
            }
        }
    }
    
    @Override
    public void zza(final String s, final ArrayList<String> list) {
        final com.google.android.gms.ads.internal.purchase.zzd zzd = new com.google.android.gms.ads.internal.purchase.zzd(s, list, this.zzall.zzahn, this.zzall.zzaqv.zzcs);
        if (this.zzall.zzarh == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("InAppPurchaseListener is not set. Try to launch default purchase flow.");
            if (!zzm.zzjr().zzas(this.zzall.zzahn)) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Google Play Service unavailable, cannot launch default purchase flow.");
            }
            else if (this.zzall.zzari == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("PlayStorePurchaseListener is not set.");
            }
            else if (this.zzall.zzars == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("PlayStorePurchaseVerifier is not initialized.");
            }
            else if (this.zzall.zzarw) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("An in-app purchase request is already in progress, abort");
            }
            else {
                this.zzall.zzarw = true;
                Label_0177: {
                    try {
                        if (this.zzall.zzari.isValidPurchase(s)) {
                            break Label_0177;
                        }
                        this.zzall.zzarw = false;
                    }
                    catch (RemoteException ex) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not start In-App purchase.");
                        this.zzall.zzarw = false;
                    }
                    return;
                }
                com.google.android.gms.ads.internal.zzu.zzgn().zza(this.zzall.zzahn, this.zzall.zzaqv.zzctu, new GInAppPurchaseManagerInfoParcel(this.zzall.zzahn, this.zzall.zzars, zzd, this));
            }
        }
        else {
            try {
                this.zzall.zzarh.zza(zzd);
            }
            catch (RemoteException ex2) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not start In-App purchase.");
            }
        }
    }
    
    @Override
    public void zza(final String s, final boolean b, final int n, final Intent intent, final zzf zzf) {
        while (true) {
            try {
                if (this.zzall.zzari != null) {
                    this.zzall.zzari.zza(new com.google.android.gms.ads.internal.purchase.zzg(this.zzall.zzahn, s, b, n, intent, zzf));
                }
                zzkr.zzcrf.postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        final int zzd = com.google.android.gms.ads.internal.zzu.zzgn().zzd(intent);
                        com.google.android.gms.ads.internal.zzu.zzgn();
                        if (zzd == 0 && zzb.this.zzall.zzara != null && zzb.this.zzall.zzara.zzbyh != null && zzb.this.zzall.zzara.zzbyh.zzvp() != null) {
                            zzb.this.zzall.zzara.zzbyh.zzvp().close();
                        }
                        zzb.this.zzall.zzarw = false;
                    }
                }, 500L);
            }
            catch (RemoteException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Fail to invoke PlayStorePurchaseListener.");
                continue;
            }
            break;
        }
    }
    
    public boolean zza(final AdRequestParcel adRequestParcel, final zzdq zzdq) {
        final boolean zzec = this.zzec();
        boolean b = false;
        if (zzec) {
            final Bundle zza = this.zza(com.google.android.gms.ads.internal.zzu.zzgd().zzaa(this.zzall.zzahn));
            this.zzalk.cancel();
            this.zzall.zzarv = 0;
            zzkg zztw;
            if (zzdi.zzbgq.get()) {
                zztw = com.google.android.gms.ads.internal.zzu.zzgd().zztw();
                final com.google.android.gms.ads.internal.zzg zzgv = com.google.android.gms.ads.internal.zzu.zzgv();
                final Context zzahn = this.zzall.zzahn;
                final VersionInfoParcel zzaqv = this.zzall.zzaqv;
                String zzti = null;
                if (zztw != null) {
                    zzti = zztw.zzti();
                }
                zzgv.zza(zzahn, zzaqv, false, zztw, zzti, this.zzall.zzaqt);
            }
            else {
                zztw = null;
            }
            final AdRequestInfoParcel.zza zza2 = this.zza(adRequestParcel, zza, zztw);
            zzdq.zzh("seq_num", zza2.zzcfx);
            zzdq.zzh("request_id", zza2.zzcgj);
            zzdq.zzh("session_id", zza2.zzcfy);
            if (zza2.zzcfv != null) {
                zzdq.zzh("app_version", String.valueOf(zza2.zzcfv.versionCode));
            }
            this.zzall.zzaqx = com.google.android.gms.ads.internal.zzu.zzfv().zza(this.zzall.zzahn, zza2, this.zzall.zzaqu, (com.google.android.gms.ads.internal.request.zza.zza)this);
            b = true;
        }
        return b;
    }
    
    protected boolean zza(final AdRequestParcel adRequestParcel, final zzke zzke, final boolean b) {
        if (!b && this.zzall.zzhc()) {
            if (zzke.zzbsj > 0L) {
                this.zzalk.zza(adRequestParcel, zzke.zzbsj);
            }
            else if (zzke.zzcof != null && zzke.zzcof.zzbsj > 0L) {
                this.zzalk.zza(adRequestParcel, zzke.zzcof.zzbsj);
            }
            else if (!zzke.zzchc && zzke.errorCode == 2) {
                this.zzalk.zzh(adRequestParcel);
            }
        }
        return this.zzalk.zzfl();
    }
    
    @Override
    boolean zza(final zzke zzke) {
        boolean boolean1 = false;
        AdRequestParcel adRequestParcel;
        if (this.zzalm != null) {
            adRequestParcel = this.zzalm;
            this.zzalm = null;
        }
        else {
            adRequestParcel = zzke.zzcfu;
            final Bundle extras = adRequestParcel.extras;
            boolean1 = false;
            if (extras != null) {
                boolean1 = adRequestParcel.extras.getBoolean("_noRefresh", false);
            }
        }
        return this.zza(adRequestParcel, zzke, boolean1);
    }
    
    @Override
    protected boolean zza(@Nullable final zzke zzke, final zzke zzke2) {
        if (zzke != null && zzke.zzbth != null) {
            zzke.zzbth.zza((zzgi)null);
        }
        if (zzke2.zzbth != null) {
            zzke2.zzbth.zza(this);
        }
        int zzbsp;
        int zzbsq;
        if (zzke2.zzcof != null) {
            zzbsp = zzke2.zzcof.zzbsp;
            zzbsq = zzke2.zzcof.zzbsq;
        }
        else {
            zzbsq = 0;
            zzbsp = 0;
        }
        this.zzall.zzart.zzh(zzbsp, zzbsq);
        return true;
    }
    
    @Override
    public void zzb(final zzke zzke) {
        super.zzb(zzke);
        if (zzke.zzbte != null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Pinging network fill URLs.");
            com.google.android.gms.ads.internal.zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, zzke, this.zzall.zzaqt, false, zzke.zzbte.zzbrs);
            if (zzke.zzcof != null && zzke.zzcof.zzbsg != null && zzke.zzcof.zzbsg.size() > 0) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdd("Pinging urls remotely");
                com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn, zzke.zzcof.zzbsg);
            }
        }
        if (zzke.errorCode == 3 && zzke.zzcof != null && zzke.zzcof.zzbsf != null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Pinging no fill URLs.");
            com.google.android.gms.ads.internal.zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, zzke, this.zzall.zzaqt, false, zzke.zzcof.zzbsf);
        }
    }
    
    @Override
    protected boolean zzc(final AdRequestParcel adRequestParcel) {
        return super.zzc(adRequestParcel) && !this.zzalt;
    }
    
    protected boolean zzec() {
        boolean b = true;
        if (!com.google.android.gms.ads.internal.zzu.zzfz().zza(this.zzall.zzahn.getPackageManager(), this.zzall.zzahn.getPackageName(), "android.permission.INTERNET") || !com.google.android.gms.ads.internal.zzu.zzfz().zzac(this.zzall.zzahn)) {
            b = false;
        }
        return b;
    }
    
    @Override
    public void zzed() {
        this.zzaln.zzj(this.zzall.zzara);
        this.zzalt = false;
        this.zzdx();
        this.zzall.zzarc.zztb();
    }
    
    @Override
    public void zzee() {
        this.zzalt = true;
        this.zzdz();
    }
    
    @Override
    public void zzef() {
        this.onAdClicked();
    }
    
    @Override
    public void zzeg() {
        this.zzed();
    }
    
    @Override
    public void zzeh() {
        this.zzdu();
    }
    
    @Override
    public void zzei() {
        this.zzee();
    }
    
    @Override
    public void zzej() {
        if (this.zzall.zzara != null) {
            final String zzbtg = this.zzall.zzara.zzbtg;
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(new StringBuilder(74 + String.valueOf(zzbtg).length()).append("Mediation adapter ").append(zzbtg).append(" refreshed, but mediation adapters should never refresh.").toString());
        }
        this.zza(this.zzall.zzara, true);
        this.zzea();
    }
    
    @Override
    public void zzek() {
        this.recordImpression();
    }
    
    @Override
    public void zzel() {
        com.google.android.gms.ads.internal.zzu.zzfz().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                zzb.this.zzalk.pause();
            }
        });
    }
    
    @Override
    public void zzem() {
        com.google.android.gms.ads.internal.zzu.zzfz().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                zzb.this.zzalk.resume();
            }
        });
    }
}
