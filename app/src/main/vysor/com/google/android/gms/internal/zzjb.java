// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.request.zzl;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONObject;
import java.util.List;
import android.text.TextUtils;
import org.json.JSONException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.UUID;
import com.google.android.gms.ads.internal.zzu;
import android.location.Location;
import java.util.concurrent.Callable;
import android.os.Bundle;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;
import com.google.android.gms.ads.internal.request.zzk;

@zziy
public final class zzjb extends zzk.zza
{
    private static final Object zzaok;
    private static zzjb zzcjk;
    private final Context mContext;
    private final zzja zzcjl;
    private final zzdb zzcjm;
    private final zzfy zzcjn;
    
    static {
        zzaok = new Object();
    }
    
    zzjb(final Context mContext, final zzdb zzcjm, final zzja zzcjl) {
        this.mContext = mContext;
        this.zzcjl = zzcjl;
        this.zzcjm = zzcjm;
        Context applicationContext;
        if (mContext.getApplicationContext() != null) {
            applicationContext = mContext.getApplicationContext();
        }
        else {
            applicationContext = mContext;
        }
        this.zzcjn = new zzfy(applicationContext, VersionInfoParcel.zzvg(), zzcjm.zzkp(), new zzkw<zzfv>() {
            public void zza(final zzfv zzfv) {
                zzfv.zza("/log", zzeu.zzbmj);
            }
        }, new zzfy.zzb<zzfv>());
    }
    
    private static AdResponseParcel zza(final Context context, final zzfy zzfy, final zzdb zzdb, final zzja zzja, final AdRequestInfoParcel adRequestInfoParcel) {
        zzb.zzdd("Starting ad request from service using: AFMA_getAd");
        zzdi.initialize(context);
        final zzlj<Bundle> zzqi = zzja.zzcji.zzqi();
        final zzdq zzdq = new zzdq(zzdi.zzbca.get(), "load_ad", adRequestInfoParcel.zzaqz.zzaxi);
        if (adRequestInfoParcel.versionCode > 10 && adRequestInfoParcel.zzcgm != -1L) {
            zzdq.zza(zzdq.zzc(adRequestInfoParcel.zzcgm), "cts");
        }
        final zzdo zzla = zzdq.zzla();
        Bundle zzcgb;
        if (adRequestInfoParcel.versionCode >= 4 && adRequestInfoParcel.zzcgb != null) {
            zzcgb = adRequestInfoParcel.zzcgb;
        }
        else {
            zzcgb = null;
        }
        Bundle bundle;
        zzlj<Object> zzlj;
        if (zzdi.zzbcj.get() && zzja.zzcja != null) {
            if (zzcgb == null && zzdi.zzbck.get()) {
                zzkn.v("contentInfo is not present, but we'll still launch the app index task");
                zzcgb = new Bundle();
            }
            if (zzcgb != null) {
                final zzlj<Object> zza = zzkq.zza((Callable<Object>)new Callable<Void>() {
                    public Void zzdb() throws Exception {
                        zzja.zzcja.zza(context, adRequestInfoParcel.zzcfv.packageName, zzcgb);
                        return null;
                    }
                });
                bundle = zzcgb;
                zzlj = zza;
            }
            else {
                bundle = zzcgb;
                zzlj = null;
            }
        }
        else {
            bundle = zzcgb;
            zzlj = null;
        }
        final zzlh<Location> zzlh = new zzlh<Location>(null);
        final Bundle extras = adRequestInfoParcel.zzcfu.extras;
        boolean b;
        if (extras != null && extras.getString("_ad") != null) {
            b = true;
        }
        else {
            b = false;
        }
        while (true) {
            Label_1085: {
                if (!adRequestInfoParcel.zzcgt || b) {
                    break Label_1085;
                }
                final zzlj<Location> zza2 = zzja.zzcjf.zza(adRequestInfoParcel.applicationInfo);
                final zzjh zzy = zzu.zzgi().zzy(context);
                AdResponseParcel adResponseParcel;
                if (zzy.zzcmd == -1) {
                    zzb.zzdd("Device is offline.");
                    adResponseParcel = new AdResponseParcel(2);
                }
                else {
                    String s;
                    if (adRequestInfoParcel.versionCode >= 7) {
                        s = adRequestInfoParcel.zzcgj;
                    }
                    else {
                        s = UUID.randomUUID().toString();
                    }
                    final zzjd zzjd = new zzjd(s, adRequestInfoParcel.applicationInfo.packageName);
                    if (adRequestInfoParcel.zzcfu.extras != null) {
                        final String string = adRequestInfoParcel.zzcfu.extras.getString("_ad");
                        if (string != null) {
                            adResponseParcel = zzjc.zza(context, adRequestInfoParcel, string);
                            return adResponseParcel;
                        }
                    }
                    final List<String> zza3 = zzja.zzcjd.zza(adRequestInfoParcel);
                    final String zzg = zzja.zzcjj.zzg(adRequestInfoParcel);
                    final zzjl.zza zzz = zzja.zzcjh.zzz(context);
                    while (true) {
                        if (zzlj != null) {
                            JSONObject zza4 = null;
                            try {
                                zzkn.v("Waiting for app index fetching task.");
                                zzlj.get(zzdi.zzbcl.get(), TimeUnit.MILLISECONDS);
                                zzkn.v("App index fetching task completed.");
                                final String zzcr = zzja.zzcjc.zzcr(adRequestInfoParcel.zzcfv.packageName);
                                zzd(zzqi);
                                zza4 = zzjc.zza(context, new zziz().zzf(adRequestInfoParcel).zza(zzy).zza(zzz).zzc(zzc(zza2)).zze(zzd(zzqi)).zzci(zzg).zzk(zza3).zzf(bundle).zzcj(zzcr).zzj(zzja.zzcjb.zzj(context)));
                                if (zza4 == null) {
                                    adResponseParcel = new AdResponseParcel(0);
                                    return adResponseParcel;
                                }
                                goto Label_0638;
                            }
                            catch (InterruptedException ex2) {}
                            catch (TimeoutException ex3) {
                                zzb.zzdd("Timed out waiting for app index fetching task");
                                continue;
                            }
                            catch (ExecutionException ex4) {
                                goto Label_0616;
                            }
                            try {
                                zza4.put("request_id", (Object)s);
                                Label_0833: {
                                    while (true) {
                                        try {
                                            zza4.put("prefetch_mode", (Object)"url");
                                            final String string2 = zza4.toString();
                                            zzdq.zza(zzla, "arc");
                                            zzkr.zzcrf.post((Runnable)new Runnable() {
                                                final /* synthetic */ zzdo zzcjs = zzdq.zzla();
                                                
                                                @Override
                                                public void run() {
                                                    final zzfy.zzc zzmy = zzfy.zzmy();
                                                    zzjd.zzb(zzmy);
                                                    zzdq.zza(this.zzcjs, "rwc");
                                                    zzmy.zza(new zzlm.zzc<zzfz>() {
                                                        final /* synthetic */ zzdo zzcju = zzdq.zzla();
                                                        
                                                        public void zzb(final zzfz zzfz) {
                                                            zzdq.zza(this.zzcju, "jsf");
                                                            zzdq.zzlb();
                                                            zzfz.zza("/invalidRequest", zzjd.zzckc);
                                                            zzfz.zza("/loadAdURL", zzjd.zzckd);
                                                            zzfz.zza("/loadAd", zzjd.zzcke);
                                                            try {
                                                                zzfz.zzj("AFMA_getAd", string2);
                                                            }
                                                            catch (Exception ex) {
                                                                com.google.android.gms.ads.internal.util.client.zzb.zzb("Error requesting an ad url", ex);
                                                            }
                                                        }
                                                    }, new zzlm.zza() {
                                                        @Override
                                                        public void run() {
                                                        }
                                                    });
                                                }
                                            });
                                            final zzjd zzjd2 = zzjd;
                                            final Future<zzjg> future = zzjd2.zzse();
                                            final long n = 10L;
                                            final TimeUnit timeUnit = TimeUnit.SECONDS;
                                            final zzjg zzjg = future.get(n, timeUnit);
                                            final zzjg zzjg2 = zzjg;
                                            final zzjg zzjg4;
                                            final zzjg zzjg3 = zzjg4 = zzjg2;
                                            if (zzjg4 == null) {
                                                final int n2 = 0;
                                                adResponseParcel = new AdResponseParcel(n2);
                                                return adResponseParcel;
                                            }
                                            break Label_0833;
                                        }
                                        catch (JSONException ex) {
                                            zzb.zzd("Failed putting prefetch parameters to ad request.", (Throwable)ex);
                                            continue;
                                        }
                                        break;
                                    }
                                    try {
                                        zzjg zzjg3;
                                        try {
                                            final zzjd zzjd2 = zzjd;
                                            final Future<zzjg> future = zzjd2.zzse();
                                            final long n = 10L;
                                            final TimeUnit timeUnit = TimeUnit.SECONDS;
                                            final zzjg zzjg = future.get(n, timeUnit);
                                            final zzjg zzjg2 = zzjg;
                                            final zzjg zzjg4;
                                            zzjg3 = (zzjg4 = zzjg2);
                                            if (zzjg4 == null) {
                                                final int n2 = 0;
                                                adResponseParcel = new AdResponseParcel(n2);
                                                return adResponseParcel;
                                            }
                                        }
                                        catch (Exception ex5) {
                                            adResponseParcel = new AdResponseParcel(0);
                                            return adResponseParcel;
                                        }
                                        if (zzjg3.getErrorCode() != -2) {
                                            adResponseParcel = new AdResponseParcel(zzjg3.getErrorCode());
                                            return adResponseParcel;
                                        }
                                        if (zzdq.zzld() != null) {
                                            zzdq.zza(zzdq.zzld(), "rur");
                                        }
                                        final boolean empty = TextUtils.isEmpty((CharSequence)zzjg3.zzsj());
                                        adResponseParcel = null;
                                        if (!empty) {
                                            adResponseParcel = zzjc.zza(context, adRequestInfoParcel, zzjg3.zzsj());
                                        }
                                        if (adResponseParcel == null && !TextUtils.isEmpty((CharSequence)zzjg3.getUrl())) {
                                            final String zzcr;
                                            adResponseParcel = zza(adRequestInfoParcel, context, adRequestInfoParcel.zzaqv.zzcs, zzjg3.getUrl(), zzcr, zzjg3, zzdq, zzja);
                                        }
                                        if (adResponseParcel == null) {
                                            adResponseParcel = new AdResponseParcel(0);
                                        }
                                        zzdq.zza(zzla, "tts");
                                        adResponseParcel.zzchp = zzdq.zzlc();
                                        return adResponseParcel;
                                    }
                                    finally {
                                        zzkr.zzcrf.post((Runnable)new Runnable() {
                                            @Override
                                            public void run() {
                                                zzja.zzcje.zza(context, zzjd, adRequestInfoParcel.zzaqv);
                                            }
                                        });
                                    }
                                }
                            }
                            catch (JSONException ex6) {}
                            break Label_1085;
                        }
                        continue;
                    }
                }
                return adResponseParcel;
            }
            final zzlj<Location> zza2 = zzlh;
            continue;
        }
    }
    
    public static AdResponseParcel zza(final AdRequestInfoParcel p0, final Context p1, final String p2, final String p3, final String p4, final zzjg p5, final zzdq p6, final zzja p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           6
        //     2: ifnull          782
        //     5: aload           6
        //     7: invokevirtual   com/google/android/gms/internal/zzdq.zzla:()Lcom/google/android/gms/internal/zzdo;
        //    10: astore          8
        //    12: new             Lcom/google/android/gms/internal/zzje;
        //    15: dup            
        //    16: aload_0        
        //    17: invokespecial   com/google/android/gms/internal/zzje.<init>:(Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)V
        //    20: astore          9
        //    22: aload_3        
        //    23: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    26: astore          14
        //    28: aload           14
        //    30: invokevirtual   java/lang/String.length:()I
        //    33: ifeq            379
        //    36: ldc_w           "AdRequestServiceImpl: Sending request: "
        //    39: aload           14
        //    41: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    44: astore          15
        //    46: aload           15
        //    48: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //    51: new             Ljava/net/URL;
        //    54: dup            
        //    55: aload_3        
        //    56: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    59: astore          16
        //    61: invokestatic    com/google/android/gms/ads/internal/zzu.zzgf:()Lcom/google/android/gms/common/util/zze;
        //    64: invokeinterface com/google/android/gms/common/util/zze.elapsedRealtime:()J
        //    69: lstore          17
        //    71: iconst_0       
        //    72: istore          19
        //    74: aload           16
        //    76: astore          20
        //    78: aload           7
        //    80: ifnull          93
        //    83: aload           7
        //    85: getfield        com/google/android/gms/internal/zzja.zzcjg:Lcom/google/android/gms/internal/zzjk;
        //    88: invokeinterface com/google/android/gms/internal/zzjk.zzsl:()V
        //    93: aload           20
        //    95: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    98: checkcast       Ljava/net/HttpURLConnection;
        //   101: astore          21
        //   103: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   106: aload_1        
        //   107: aload_2        
        //   108: iconst_0       
        //   109: aload           21
        //   111: invokevirtual   com/google/android/gms/internal/zzkr.zza:(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
        //   114: aload           4
        //   116: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   119: ifne            140
        //   122: aload           5
        //   124: invokevirtual   com/google/android/gms/internal/zzjg.zzsi:()Z
        //   127: ifeq            140
        //   130: aload           21
        //   132: ldc_w           "x-afma-drt-cookie"
        //   135: aload           4
        //   137: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   140: aload_0        
        //   141: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcgu:Ljava/lang/String;
        //   144: astore          23
        //   146: aload           23
        //   148: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   151: ifne            170
        //   154: ldc_w           "Sending webview cookie in ad request header."
        //   157: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //   160: aload           21
        //   162: ldc_w           "Cookie"
        //   165: aload           23
        //   167: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   170: aload           5
        //   172: ifnull          236
        //   175: aload           5
        //   177: invokevirtual   com/google/android/gms/internal/zzjg.zzsh:()Ljava/lang/String;
        //   180: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   183: ifne            236
        //   186: aload           21
        //   188: iconst_1       
        //   189: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   192: aload           5
        //   194: invokevirtual   com/google/android/gms/internal/zzjg.zzsh:()Ljava/lang/String;
        //   197: invokevirtual   java/lang/String.getBytes:()[B
        //   200: astore          38
        //   202: aload           21
        //   204: aload           38
        //   206: arraylength    
        //   207: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   210: new             Ljava/io/BufferedOutputStream;
        //   213: dup            
        //   214: aload           21
        //   216: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   219: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   222: astore          39
        //   224: aload           39
        //   226: aload           38
        //   228: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //   231: aload           39
        //   233: invokestatic    com/google/android/gms/common/util/zzo.zzb:(Ljava/io/Closeable;)V
        //   236: aload           21
        //   238: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   241: istore          24
        //   243: aload           21
        //   245: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   248: astore          25
        //   250: iload           24
        //   252: sipush          200
        //   255: if_icmplt       493
        //   258: iload           24
        //   260: sipush          300
        //   263: if_icmpge       493
        //   266: aload           20
        //   268: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   271: astore          32
        //   273: new             Ljava/io/InputStreamReader;
        //   276: dup            
        //   277: aload           21
        //   279: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   282: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //   285: astore          33
        //   287: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   290: aload           33
        //   292: invokevirtual   com/google/android/gms/internal/zzkr.zza:(Ljava/io/InputStreamReader;)Ljava/lang/String;
        //   295: astore          35
        //   297: aload           33
        //   299: invokestatic    com/google/android/gms/common/util/zzo.zzb:(Ljava/io/Closeable;)V
        //   302: aload           32
        //   304: aload           25
        //   306: aload           35
        //   308: iload           24
        //   310: invokestatic    com/google/android/gms/internal/zzjb.zza:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   313: aload           9
        //   315: aload           32
        //   317: aload           25
        //   319: aload           35
        //   321: invokevirtual   com/google/android/gms/internal/zzje.zzb:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V
        //   324: aload           6
        //   326: ifnull          347
        //   329: aload           6
        //   331: aload           8
        //   333: iconst_1       
        //   334: anewarray       Ljava/lang/String;
        //   337: dup            
        //   338: iconst_0       
        //   339: ldc_w           "ufe"
        //   342: aastore        
        //   343: invokevirtual   com/google/android/gms/internal/zzdq.zza:(Lcom/google/android/gms/internal/zzdo;[Ljava/lang/String;)Z
        //   346: pop            
        //   347: aload           9
        //   349: lload           17
        //   351: invokevirtual   com/google/android/gms/internal/zzje.zzj:(J)Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
        //   354: astore          36
        //   356: aload           21
        //   358: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   361: aload           7
        //   363: ifnull          775
        //   366: aload           7
        //   368: getfield        com/google/android/gms/internal/zzja.zzcjg:Lcom/google/android/gms/internal/zzjk;
        //   371: invokeinterface com/google/android/gms/internal/zzjk.zzsm:()V
        //   376: goto            775
        //   379: new             Ljava/lang/String;
        //   382: dup            
        //   383: ldc_w           "AdRequestServiceImpl: Sending request: "
        //   386: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   389: astore          15
        //   391: goto            46
        //   394: astore          10
        //   396: aload           10
        //   398: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   401: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   404: astore          11
        //   406: aload           11
        //   408: invokevirtual   java/lang/String.length:()I
        //   411: ifeq            750
        //   414: ldc_w           "Error while connecting to ad server: "
        //   417: aload           11
        //   419: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   422: astore          12
        //   424: aload           12
        //   426: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   429: new             Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
        //   432: dup            
        //   433: iconst_2       
        //   434: invokespecial   com/google/android/gms/ads/internal/request/AdResponseParcel.<init>:(I)V
        //   437: astore          13
        //   439: goto            779
        //   442: astore          40
        //   444: aconst_null    
        //   445: astore          39
        //   447: aload           39
        //   449: invokestatic    com/google/android/gms/common/util/zzo.zzb:(Ljava/io/Closeable;)V
        //   452: aload           40
        //   454: athrow         
        //   455: astore          22
        //   457: aload           21
        //   459: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   462: aload           7
        //   464: ifnull          477
        //   467: aload           7
        //   469: getfield        com/google/android/gms/internal/zzja.zzcjg:Lcom/google/android/gms/internal/zzjk;
        //   472: invokeinterface com/google/android/gms/internal/zzjk.zzsm:()V
        //   477: aload           22
        //   479: athrow         
        //   480: astore          34
        //   482: aconst_null    
        //   483: astore          33
        //   485: aload           33
        //   487: invokestatic    com/google/android/gms/common/util/zzo.zzb:(Ljava/io/Closeable;)V
        //   490: aload           34
        //   492: athrow         
        //   493: aload           20
        //   495: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //   498: aload           25
        //   500: aconst_null    
        //   501: iload           24
        //   503: invokestatic    com/google/android/gms/internal/zzjb.zza:(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
        //   506: iload           24
        //   508: sipush          300
        //   511: if_icmplt       649
        //   514: iload           24
        //   516: sipush          400
        //   519: if_icmpge       649
        //   522: aload           21
        //   524: ldc_w           "Location"
        //   527: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   530: astore          27
        //   532: aload           27
        //   534: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   537: ifeq            583
        //   540: ldc_w           "No location header to follow redirect."
        //   543: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   546: new             Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
        //   549: dup            
        //   550: iconst_0       
        //   551: invokespecial   com/google/android/gms/ads/internal/request/AdResponseParcel.<init>:(I)V
        //   554: astore          31
        //   556: aload           21
        //   558: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   561: aload           7
        //   563: ifnull          576
        //   566: aload           7
        //   568: getfield        com/google/android/gms/internal/zzja.zzcjg:Lcom/google/android/gms/internal/zzjk;
        //   571: invokeinterface com/google/android/gms/internal/zzjk.zzsm:()V
        //   576: aload           31
        //   578: astore          13
        //   580: goto            779
        //   583: new             Ljava/net/URL;
        //   586: dup            
        //   587: aload           27
        //   589: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   592: astore          28
        //   594: iload           19
        //   596: iconst_1       
        //   597: iadd           
        //   598: istore          29
        //   600: iload           29
        //   602: iconst_5       
        //   603: if_icmple       712
        //   606: ldc_w           "Too many redirects."
        //   609: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   612: new             Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
        //   615: dup            
        //   616: iconst_0       
        //   617: invokespecial   com/google/android/gms/ads/internal/request/AdResponseParcel.<init>:(I)V
        //   620: astore          30
        //   622: aload           21
        //   624: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   627: aload           7
        //   629: ifnull          642
        //   632: aload           7
        //   634: getfield        com/google/android/gms/internal/zzja.zzcjg:Lcom/google/android/gms/internal/zzjk;
        //   637: invokeinterface com/google/android/gms/internal/zzjk.zzsm:()V
        //   642: aload           30
        //   644: astore          13
        //   646: goto            779
        //   649: new             Ljava/lang/StringBuilder;
        //   652: dup            
        //   653: bipush          46
        //   655: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   658: ldc_w           "Received error HTTP response code: "
        //   661: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   664: iload           24
        //   666: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   669: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   672: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   675: new             Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
        //   678: dup            
        //   679: iconst_0       
        //   680: invokespecial   com/google/android/gms/ads/internal/request/AdResponseParcel.<init>:(I)V
        //   683: astore          26
        //   685: aload           21
        //   687: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   690: aload           7
        //   692: ifnull          705
        //   695: aload           7
        //   697: getfield        com/google/android/gms/internal/zzja.zzcjg:Lcom/google/android/gms/internal/zzjk;
        //   700: invokeinterface com/google/android/gms/internal/zzjk.zzsm:()V
        //   705: aload           26
        //   707: astore          13
        //   709: goto            779
        //   712: aload           9
        //   714: aload           25
        //   716: invokevirtual   com/google/android/gms/internal/zzje.zzj:(Ljava/util/Map;)V
        //   719: aload           21
        //   721: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   724: aload           7
        //   726: ifnull          739
        //   729: aload           7
        //   731: getfield        com/google/android/gms/internal/zzja.zzcjg:Lcom/google/android/gms/internal/zzjk;
        //   734: invokeinterface com/google/android/gms/internal/zzjk.zzsm:()V
        //   739: iload           29
        //   741: istore          19
        //   743: aload           28
        //   745: astore          20
        //   747: goto            78
        //   750: new             Ljava/lang/String;
        //   753: dup            
        //   754: ldc_w           "Error while connecting to ad server: "
        //   757: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   760: astore          12
        //   762: goto            424
        //   765: astore          34
        //   767: goto            485
        //   770: astore          40
        //   772: goto            447
        //   775: aload           36
        //   777: astore          13
        //   779: aload           13
        //   781: areturn        
        //   782: aconst_null    
        //   783: astore          8
        //   785: goto            12
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     103    394    442    Ljava/io/IOException;
        //  103    210    455    480    Any
        //  210    224    442    447    Any
        //  224    231    770    775    Any
        //  231    273    455    480    Any
        //  273    287    480    485    Any
        //  287    297    765    770    Any
        //  297    356    455    480    Any
        //  356    391    394    442    Ljava/io/IOException;
        //  447    455    455    480    Any
        //  457    480    394    442    Ljava/io/IOException;
        //  485    556    455    480    Any
        //  556    576    394    442    Ljava/io/IOException;
        //  583    622    455    480    Any
        //  622    642    394    442    Ljava/io/IOException;
        //  649    685    455    480    Any
        //  685    705    394    442    Ljava/io/IOException;
        //  712    719    455    480    Any
        //  719    739    394    442    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 332, Size: 332
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:635)
        //     at java.util.ArrayList.get(ArrayList.java:411)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
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
    
    public static zzjb zza(Context applicationContext, final zzdb zzdb, final zzja zzja) {
        synchronized (zzjb.zzaok) {
            if (zzjb.zzcjk == null) {
                if (applicationContext.getApplicationContext() != null) {
                    applicationContext = applicationContext.getApplicationContext();
                }
                zzjb.zzcjk = new zzjb(applicationContext, zzdb, zzja);
            }
            return zzjb.zzcjk;
        }
    }
    
    private static void zza(final String s, final Map<String, List<String>> map, final String s2, final int n) {
        if (zzb.zzbf(2)) {
            zzkn.v(new StringBuilder(39 + String.valueOf(s).length()).append("Http Response: {\n  URL:\n    ").append(s).append("\n  Headers:").toString());
            if (map != null) {
                for (final String s3 : map.keySet()) {
                    zzkn.v(new StringBuilder(5 + String.valueOf(s3).length()).append("    ").append(s3).append(":").toString());
                    final Iterator<String> iterator2 = map.get(s3).iterator();
                    while (iterator2.hasNext()) {
                        final String value = String.valueOf(iterator2.next());
                        String concat;
                        if (value.length() != 0) {
                            concat = "      ".concat(value);
                        }
                        else {
                            concat = new String("      ");
                        }
                        zzkn.v(concat);
                    }
                }
            }
            zzkn.v("  Body:");
            if (s2 != null) {
                for (int i = 0; i < Math.min(s2.length(), 100000); i += 1000) {
                    zzkn.v(s2.substring(i, Math.min(s2.length(), i + 1000)));
                }
            }
            else {
                zzkn.v("    null");
            }
            zzkn.v(new StringBuilder(34).append("  Response Code:\n    ").append(n).append("\n}").toString());
        }
    }
    
    private static Location zzc(final zzlj<Location> zzlj) {
        try {
            return zzlj.get(zzdi.zzbgm.get(), TimeUnit.MILLISECONDS);
        }
        catch (Exception ex) {
            zzb.zzd("Exception caught while getting location", ex);
            return null;
        }
    }
    
    private static Bundle zzd(final zzlj<Bundle> zzlj) {
        final Bundle bundle = new Bundle();
        try {
            return zzlj.get(zzdi.zzbhd.get(), TimeUnit.MILLISECONDS);
        }
        catch (Exception ex) {
            zzb.zzd("Exception caught while getting parental controls.", ex);
            return bundle;
        }
    }
    
    public void zza(final AdRequestInfoParcel adRequestInfoParcel, final zzl zzl) {
        zzu.zzgd().zzb(this.mContext, adRequestInfoParcel.zzaqv);
        zzkq.zza(new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/google/android/gms/internal/zzjb$5.zzcjw:Lcom/google/android/gms/internal/zzjb;
                //     4: aload_0        
                //     5: getfield        com/google/android/gms/internal/zzjb$5.zzcjp:Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;
                //     8: invokevirtual   com/google/android/gms/internal/zzjb.zzd:(Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
                //    11: astore          4
                //    13: aload           4
                //    15: astore_2       
                //    16: aload_2        
                //    17: ifnonnull       29
                //    20: new             Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
                //    23: dup            
                //    24: iconst_0       
                //    25: invokespecial   com/google/android/gms/ads/internal/request/AdResponseParcel.<init>:(I)V
                //    28: astore_2       
                //    29: aload_0        
                //    30: getfield        com/google/android/gms/internal/zzjb$5.zzcjx:Lcom/google/android/gms/ads/internal/request/zzl;
                //    33: aload_2        
                //    34: invokeinterface com/google/android/gms/ads/internal/request/zzl.zzb:(Lcom/google/android/gms/ads/internal/request/AdResponseParcel;)V
                //    39: return         
                //    40: astore_1       
                //    41: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
                //    44: aload_1        
                //    45: ldc             "AdRequestServiceImpl.loadAdAsync"
                //    47: invokevirtual   com/google/android/gms/internal/zzkh.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
                //    50: ldc             "Could not fetch ad response due to an Exception."
                //    52: aload_1        
                //    53: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //    56: aconst_null    
                //    57: astore_2       
                //    58: goto            16
                //    61: astore_3       
                //    62: ldc             "Fail to forward ad response."
                //    64: aload_3        
                //    65: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
                //    68: goto            39
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                        
                //  -----  -----  -----  -----  ----------------------------
                //  0      13     40     61     Ljava/lang/Exception;
                //  29     39     61     71     Landroid/os/RemoteException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    public AdResponseParcel zzd(final AdRequestInfoParcel adRequestInfoParcel) {
        return zza(this.mContext, this.zzcjn, this.zzcjm, this.zzcjl, adRequestInfoParcel);
    }
}
