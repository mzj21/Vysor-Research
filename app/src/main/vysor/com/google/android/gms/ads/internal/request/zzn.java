// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import java.util.Map;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zzke;
import java.util.concurrent.Future;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.ads.internal.zzu;
import org.json.JSONObject;
import com.google.android.gms.internal.zzfv;
import com.google.android.gms.internal.zzkw;
import com.google.android.gms.internal.zzdi;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzfa;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzfy;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkm;

@zziy
public class zzn extends zzkm
{
    private static final Object zzaok;
    private static zzfy zzcdq;
    static final long zzcik;
    static boolean zzcil;
    private static zzew zzcim;
    private static zzfa zzcin;
    private static zzev zzcio;
    private final Context mContext;
    private final Object zzccn;
    private final com.google.android.gms.ads.internal.request.zza.zza zzcfh;
    private final AdRequestInfoParcel.zza zzcfi;
    private zzfy.zzc zzcip;
    
    static {
        zzcik = TimeUnit.SECONDS.toMillis(10L);
        zzaok = new Object();
        zzn.zzcil = false;
        zzn.zzcdq = null;
        zzn.zzcim = null;
        zzn.zzcin = null;
        zzn.zzcio = null;
    }
    
    public zzn(final Context mContext, final AdRequestInfoParcel.zza zzcfi, final com.google.android.gms.ads.internal.request.zza.zza zzcfh) {
        super(true);
        this.zzccn = new Object();
        this.zzcfh = zzcfh;
        this.mContext = mContext;
        this.zzcfi = zzcfi;
        synchronized (zzn.zzaok) {
            if (!zzn.zzcil) {
                zzn.zzcin = new zzfa();
                zzn.zzcim = new zzew(mContext.getApplicationContext(), zzcfi.zzaqv);
                zzn.zzcio = new zzc();
                zzn.zzcdq = new zzfy(this.mContext.getApplicationContext(), this.zzcfi.zzaqv, zzdi.zzbao.get(), new zzb(), new zza());
                zzn.zzcil = true;
            }
        }
    }
    
    private JSONObject zza(final AdRequestInfoParcel p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        com/google/android/gms/ads/internal/request/AdRequestInfoParcel.zzcfu:Lcom/google/android/gms/ads/internal/client/AdRequestParcel;
        //     4: getfield        com/google/android/gms/ads/internal/client/AdRequestParcel.extras:Landroid/os/Bundle;
        //     7: ldc             "sdk_less_server_data"
        //     9: invokevirtual   android/os/Bundle.getBundle:(Ljava/lang/String;)Landroid/os/Bundle;
        //    12: astore_3       
        //    13: aconst_null    
        //    14: astore          4
        //    16: aload_3        
        //    17: ifnonnull       23
        //    20: aload           4
        //    22: areturn        
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/ads/internal/request/zzn.mContext:Landroid/content/Context;
        //    27: new             Lcom/google/android/gms/internal/zziz;
        //    30: dup            
        //    31: invokespecial   com/google/android/gms/internal/zziz.<init>:()V
        //    34: aload_1        
        //    35: invokevirtual   com/google/android/gms/internal/zziz.zzf:(Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)Lcom/google/android/gms/internal/zziz;
        //    38: invokestatic    com/google/android/gms/ads/internal/zzu.zzgi:()Lcom/google/android/gms/internal/zzji;
        //    41: aload_0        
        //    42: getfield        com/google/android/gms/ads/internal/request/zzn.mContext:Landroid/content/Context;
        //    45: invokevirtual   com/google/android/gms/internal/zzji.zzy:(Landroid/content/Context;)Lcom/google/android/gms/internal/zzjh;
        //    48: invokevirtual   com/google/android/gms/internal/zziz.zza:(Lcom/google/android/gms/internal/zzjh;)Lcom/google/android/gms/internal/zziz;
        //    51: invokestatic    com/google/android/gms/internal/zzjc.zza:(Landroid/content/Context;Lcom/google/android/gms/internal/zziz;)Lorg/json/JSONObject;
        //    54: astore          5
        //    56: aconst_null    
        //    57: astore          4
        //    59: aload           5
        //    61: ifnull          20
        //    64: aload_0        
        //    65: getfield        com/google/android/gms/ads/internal/request/zzn.mContext:Landroid/content/Context;
        //    68: invokestatic    com/google/android/gms/ads/identifier/AdvertisingIdClient.getAdvertisingIdInfo:(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
        //    71: astore          17
        //    73: aload           17
        //    75: astore          7
        //    77: new             Ljava/util/HashMap;
        //    80: dup            
        //    81: invokespecial   java/util/HashMap.<init>:()V
        //    84: astore          8
        //    86: aload           8
        //    88: ldc             "request_id"
        //    90: aload_2        
        //    91: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    94: pop            
        //    95: aload           8
        //    97: ldc             "request_param"
        //    99: aload           5
        //   101: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   104: pop            
        //   105: aload           8
        //   107: ldc             "data"
        //   109: aload_3        
        //   110: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   113: pop            
        //   114: aload           7
        //   116: ifnull          156
        //   119: aload           8
        //   121: ldc             "adid"
        //   123: aload           7
        //   125: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$Info.getId:()Ljava/lang/String;
        //   128: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   131: pop            
        //   132: aload           7
        //   134: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$Info.isLimitAdTrackingEnabled:()Z
        //   137: ifeq            188
        //   140: iconst_1       
        //   141: istore          15
        //   143: aload           8
        //   145: ldc             "lat"
        //   147: iload           15
        //   149: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   152: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   155: pop            
        //   156: invokestatic    com/google/android/gms/ads/internal/zzu.zzfz:()Lcom/google/android/gms/internal/zzkr;
        //   159: aload           8
        //   161: invokevirtual   com/google/android/gms/internal/zzkr.zzan:(Ljava/util/Map;)Lorg/json/JSONObject;
        //   164: astore          13
        //   166: aload           13
        //   168: astore          4
        //   170: goto            20
        //   173: astore          6
        //   175: ldc             "Cannot get advertising id info"
        //   177: aload           6
        //   179: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   182: aconst_null    
        //   183: astore          7
        //   185: goto            77
        //   188: iconst_0       
        //   189: istore          15
        //   191: goto            143
        //   194: astore          12
        //   196: aconst_null    
        //   197: astore          4
        //   199: goto            20
        //   202: astore          6
        //   204: goto            175
        //   207: astore          6
        //   209: goto            175
        //   212: astore          6
        //   214: goto            175
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                   
        //  -----  -----  -----  -----  -----------------------------------------------------------------------
        //  64     73     212    217    Ljava/io/IOException;
        //  64     73     202    207    Ljava/lang/IllegalStateException;
        //  64     73     207    212    Lcom/google/android/gms/common/GooglePlayServicesNotAvailableException;
        //  64     73     173    175    Lcom/google/android/gms/common/GooglePlayServicesRepairableException;
        //  156    166    194    202    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0156:
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
    
    protected static void zzb(final zzfv zzfv) {
        zzfv.zza("/loadAd", zzn.zzcin);
        zzfv.zza("/fetchHttpRequest", zzn.zzcim);
        zzfv.zza("/invalidRequest", zzn.zzcio);
    }
    
    protected static void zzc(final zzfv zzfv) {
        zzfv.zzb("/loadAd", zzn.zzcin);
        zzfv.zzb("/fetchHttpRequest", zzn.zzcim);
        zzfv.zzb("/invalidRequest", zzn.zzcio);
    }
    
    private AdResponseParcel zze(final AdRequestInfoParcel adRequestInfoParcel) {
        final String zzuh = zzu.zzfz().zzuh();
        final JSONObject zza = this.zza(adRequestInfoParcel, zzuh);
        AdResponseParcel adResponseParcel = null;
        if (zza == null) {
            adResponseParcel = new AdResponseParcel(0);
        }
        else {
            final long elapsedRealtime = zzu.zzgf().elapsedRealtime();
            final Future<JSONObject> zzaz = zzn.zzcin.zzaz(zzuh);
            com.google.android.gms.ads.internal.util.client.zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    zzn.this.zzcip = zzn.zzcdq.zzmy();
                    zzn.this.zzcip.zza(new zzlm.zzc<zzfz>() {
                        public void zzb(final zzfz zzfz) {
                            try {
                                zzfz.zza("AFMA_getAdapterLessMediationAd", zza);
                            }
                            catch (Exception ex) {
                                zzb.zzb("Error requesting an ad url", ex);
                                zzn.zzcin.zzba(zzuh);
                            }
                        }
                    }, new zzlm.zza() {
                        @Override
                        public void run() {
                            zzn.zzcin.zzba(zzuh);
                        }
                    });
                }
            });
            final long n = zzn.zzcik - (zzu.zzgf().elapsedRealtime() - elapsedRealtime);
            try {
                if (zzaz.get(n, TimeUnit.MILLISECONDS) != null) {
                    goto Label_0164;
                }
                adResponseParcel = new AdResponseParcel(-1);
            }
            catch (InterruptedException ex) {}
            catch (TimeoutException ex2) {
                adResponseParcel = new AdResponseParcel(2);
            }
            catch (ExecutionException ex3) {
                adResponseParcel = new AdResponseParcel(0);
            }
            catch (CancellationException ex4) {
                goto Label_0121;
            }
        }
        return adResponseParcel;
    }
    
    @Override
    public void onStop() {
        synchronized (this.zzccn) {
            com.google.android.gms.ads.internal.util.client.zza.zzctj.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (zzn.this.zzcip != null) {
                        zzn.this.zzcip.release();
                        zzn.this.zzcip = null;
                    }
                }
            });
        }
    }
    
    @Override
    public void zzfc() {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("SdkLessAdLoaderBackgroundTask started.");
        final AdRequestInfoParcel adRequestInfoParcel = new AdRequestInfoParcel(this.zzcfi, null, -1L);
        final AdResponseParcel zze = this.zze(adRequestInfoParcel);
        com.google.android.gms.ads.internal.util.client.zza.zzctj.post((Runnable)new Runnable() {
            final /* synthetic */ zzke.zza zzalx = new zzke.zza(adRequestInfoParcel, zze, null, null, zze.errorCode, zzu.zzgf().elapsedRealtime(), zze.zzchg, null);
            
            @Override
            public void run() {
                zzn.this.zzcfh.zza(this.zzalx);
                if (zzn.this.zzcip != null) {
                    zzn.this.zzcip.release();
                    zzn.this.zzcip = null;
                }
            }
        });
    }
    
    public static class zza implements zzkw<zzfv>
    {
        public void zza(final zzfv zzfv) {
            zzn.zzc(zzfv);
        }
    }
    
    public static class zzb implements zzkw<zzfv>
    {
        public void zza(final zzfv zzfv) {
            zzn.zzb(zzfv);
        }
    }
    
    public static class zzc implements zzev
    {
        @Override
        public void zza(final zzlt zzlt, final Map<String, String> map) {
            final String s = map.get("request_id");
            final String value = String.valueOf(map.get("errors"));
            String concat;
            if (value.length() != 0) {
                concat = "Invalid request: ".concat(value);
            }
            else {
                concat = new String("Invalid request: ");
            }
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(concat);
            zzn.zzcin.zzba(s);
        }
    }
}
