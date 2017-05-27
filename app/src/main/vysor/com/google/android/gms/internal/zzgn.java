// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.ExecutionException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import android.content.Context;

@zziy
public class zzgn implements zzgf
{
    private final Context mContext;
    private final Object zzakd;
    private final zzgq zzals;
    private final boolean zzatk;
    private final boolean zzazd;
    private final zzgh zzbsv;
    private final AdRequestInfoParcel zzbtk;
    private final long zzbtl;
    private final long zzbtm;
    private final int zzbtn;
    private boolean zzbto;
    private final Map<zzlj<zzgl>, zzgk> zzbtp;
    private List<zzgl> zzbtq;
    
    public zzgn(final Context mContext, final AdRequestInfoParcel zzbtk, final zzgq zzals, final zzgh zzbsv, final boolean zzatk, final boolean zzazd, final long zzbtl, final long zzbtm, final int zzbtn) {
        this.zzakd = new Object();
        this.zzbto = false;
        this.zzbtp = new HashMap<zzlj<zzgl>, zzgk>();
        this.zzbtq = new ArrayList<zzgl>();
        this.mContext = mContext;
        this.zzbtk = zzbtk;
        this.zzals = zzals;
        this.zzbsv = zzbsv;
        this.zzatk = zzatk;
        this.zzazd = zzazd;
        this.zzbtl = zzbtl;
        this.zzbtm = zzbtm;
        this.zzbtn = zzbtn;
    }
    
    private void zza(final zzlj<zzgl> zzlj) {
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                for (final zzlj zzlj : zzgn.this.zzbtp.keySet()) {
                    if (zzlj != zzlj) {
                        ((zzgk)zzgn.this.zzbtp.get(zzlj)).cancel();
                    }
                }
            }
        });
    }
    
    private zzgl zze(final List<zzlj<zzgl>> list) {
        final Object zzakd = this.zzakd;
        // monitorenter(zzakd)
        zzgl zzgl;
        try {
            if (!this.zzbto) {
                // monitorexit(zzakd)
                for (final zzlj<zzgl> zzlj : list) {
                    try {
                        zzgl = zzlj.get();
                        this.zzbtq.add(zzgl);
                        if (zzgl != null && zzgl.zzbtd == 0) {
                            this.zza(zzlj);
                            return zzgl;
                        }
                        continue;
                    }
                    catch (InterruptedException ex) {}
                    catch (ExecutionException ex2) {}
                }
                goto Label_0124;
            }
            zzgl = new zzgl(-1);
        }
        finally {}
        return zzgl;
    }
    
    private zzgl zzf(final List<zzlj<zzgl>> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzgn.zzakd:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/zzgn.zzbto:Z
        //    11: ifeq            29
        //    14: new             Lcom/google/android/gms/internal/zzgl;
        //    17: dup            
        //    18: iconst_m1      
        //    19: invokespecial   com/google/android/gms/internal/zzgl.<init>:(I)V
        //    22: astore          4
        //    24: aload_2        
        //    25: monitorexit    
        //    26: goto            400
        //    29: aload_2        
        //    30: monitorexit    
        //    31: iconst_m1      
        //    32: istore          5
        //    34: aconst_null    
        //    35: astore          6
        //    37: aconst_null    
        //    38: astore          4
        //    40: aload_0        
        //    41: getfield        com/google/android/gms/internal/zzgn.zzbsv:Lcom/google/android/gms/internal/zzgh;
        //    44: getfield        com/google/android/gms/internal/zzgh.zzbsn:J
        //    47: ldc2_w          -1
        //    50: lcmp           
        //    51: ifeq            262
        //    54: aload_0        
        //    55: getfield        com/google/android/gms/internal/zzgn.zzbsv:Lcom/google/android/gms/internal/zzgh;
        //    58: getfield        com/google/android/gms/internal/zzgh.zzbsn:J
        //    61: lstore          7
        //    63: aload_1        
        //    64: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    69: astore          9
        //    71: lload           7
        //    73: lstore          10
        //    75: aload           9
        //    77: invokeinterface java/util/Iterator.hasNext:()Z
        //    82: ifeq            346
        //    85: aload           9
        //    87: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    92: checkcast       Lcom/google/android/gms/internal/zzlj;
        //    95: astore          12
        //    97: invokestatic    com/google/android/gms/ads/internal/zzu.zzgf:()Lcom/google/android/gms/common/util/zze;
        //   100: invokeinterface com/google/android/gms/common/util/zze.currentTimeMillis:()J
        //   105: lstore          13
        //   107: lload           10
        //   109: lconst_0       
        //   110: lcmp           
        //   111: ifne            270
        //   114: aload           12
        //   116: invokeinterface com/google/android/gms/internal/zzlj.isDone:()Z
        //   121: ifeq            270
        //   124: aload           12
        //   126: invokeinterface com/google/android/gms/internal/zzlj.get:()Ljava/lang/Object;
        //   131: checkcast       Lcom/google/android/gms/internal/zzgl;
        //   134: astore          21
        //   136: aload_0        
        //   137: getfield        com/google/android/gms/internal/zzgn.zzbtq:Ljava/util/List;
        //   140: aload           21
        //   142: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   147: pop            
        //   148: aload           21
        //   150: ifnull          385
        //   153: aload           21
        //   155: getfield        com/google/android/gms/internal/zzgl.zzbtd:I
        //   158: ifne            385
        //   161: aload           21
        //   163: getfield        com/google/android/gms/internal/zzgl.zzbti:Lcom/google/android/gms/internal/zzgt;
        //   166: astore          29
        //   168: aload           29
        //   170: ifnull          385
        //   173: aload           29
        //   175: invokeinterface com/google/android/gms/internal/zzgt.zznk:()I
        //   180: iload           5
        //   182: if_icmple       385
        //   185: aload           29
        //   187: invokeinterface com/google/android/gms/internal/zzgt.zznk:()I
        //   192: istore          30
        //   194: iload           30
        //   196: istore          25
        //   198: aload           21
        //   200: astore          31
        //   202: aload           12
        //   204: astore          24
        //   206: aload           31
        //   208: astore          23
        //   210: lload           10
        //   212: invokestatic    com/google/android/gms/ads/internal/zzu.zzgf:()Lcom/google/android/gms/common/util/zze;
        //   215: invokeinterface com/google/android/gms/common/util/zze.currentTimeMillis:()J
        //   220: lload           13
        //   222: lsub           
        //   223: lsub           
        //   224: lconst_0       
        //   225: invokestatic    java/lang/Math.max:(JJ)J
        //   228: lstore          26
        //   230: aload           24
        //   232: astore          6
        //   234: aload           23
        //   236: astore          28
        //   238: lload           26
        //   240: lstore          19
        //   242: iload           25
        //   244: istore          5
        //   246: aload           28
        //   248: astore          4
        //   250: lload           19
        //   252: lstore          10
        //   254: goto            75
        //   257: astore_3       
        //   258: aload_2        
        //   259: monitorexit    
        //   260: aload_3        
        //   261: athrow         
        //   262: ldc2_w          10000
        //   265: lstore          7
        //   267: goto            63
        //   270: aload           12
        //   272: lload           10
        //   274: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //   277: invokeinterface com/google/android/gms/internal/zzlj.get:(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
        //   282: checkcast       Lcom/google/android/gms/internal/zzgl;
        //   285: astore          21
        //   287: goto            136
        //   290: astore          15
        //   292: ldc             "Exception while processing an adapter; continuing with other adapters"
        //   294: aload           15
        //   296: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   299: lload           10
        //   301: invokestatic    com/google/android/gms/ads/internal/zzu.zzgf:()Lcom/google/android/gms/common/util/zze;
        //   304: invokeinterface com/google/android/gms/common/util/zze.currentTimeMillis:()J
        //   309: lload           13
        //   311: lsub           
        //   312: lsub           
        //   313: lconst_0       
        //   314: invokestatic    java/lang/Math.max:(JJ)J
        //   317: lstore          19
        //   319: goto            250
        //   322: astore          16
        //   324: lload           10
        //   326: invokestatic    com/google/android/gms/ads/internal/zzu.zzgf:()Lcom/google/android/gms/common/util/zze;
        //   329: invokeinterface com/google/android/gms/common/util/zze.currentTimeMillis:()J
        //   334: lload           13
        //   336: lsub           
        //   337: lsub           
        //   338: lconst_0       
        //   339: invokestatic    java/lang/Math.max:(JJ)J
        //   342: pop2           
        //   343: aload           16
        //   345: athrow         
        //   346: aload_0        
        //   347: aload           6
        //   349: invokespecial   com/google/android/gms/internal/zzgn.zza:(Lcom/google/android/gms/internal/zzlj;)V
        //   352: aload           4
        //   354: ifnonnull       400
        //   357: new             Lcom/google/android/gms/internal/zzgl;
        //   360: dup            
        //   361: iconst_1       
        //   362: invokespecial   com/google/android/gms/internal/zzgl.<init>:(I)V
        //   365: astore          4
        //   367: goto            400
        //   370: astore          15
        //   372: goto            292
        //   375: astore          15
        //   377: goto            292
        //   380: astore          15
        //   382: goto            292
        //   385: aload           4
        //   387: astore          23
        //   389: aload           6
        //   391: astore          24
        //   393: iload           5
        //   395: istore          25
        //   397: goto            210
        //   400: aload           4
        //   402: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/google/android/gms/internal/zzlj<Lcom/google/android/gms/internal/zzgl;>;>;)Lcom/google/android/gms/internal/zzgl;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  7      31     257    262    Any
        //  114    194    370    375    Ljava/lang/InterruptedException;
        //  114    194    375    380    Ljava/util/concurrent/ExecutionException;
        //  114    194    290    292    Landroid/os/RemoteException;
        //  114    194    380    385    Ljava/util/concurrent/TimeoutException;
        //  114    194    322    346    Any
        //  258    260    257    262    Any
        //  270    287    370    375    Ljava/lang/InterruptedException;
        //  270    287    375    380    Ljava/util/concurrent/ExecutionException;
        //  270    287    290    292    Landroid/os/RemoteException;
        //  270    287    380    385    Ljava/util/concurrent/TimeoutException;
        //  270    287    322    346    Any
        //  292    299    322    346    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0136:
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
    
    @Override
    public void cancel() {
        synchronized (this.zzakd) {
            this.zzbto = true;
            final Iterator<zzgk> iterator = this.zzbtp.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().cancel();
            }
        }
    }
    // monitorexit(o)
    
    @Override
    public zzgl zzd(final List<zzgg> list) {
        zzb.zzdd("Starting mediation.");
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        final ArrayList<zzlj<zzgl>> list2 = new ArrayList<zzlj<zzgl>>();
        for (final zzgg zzgg : list) {
            final String value = String.valueOf(zzgg.zzbrm);
            String concat;
            if (value.length() != 0) {
                concat = "Trying mediation network: ".concat(value);
            }
            else {
                concat = new String("Trying mediation network: ");
            }
            zzb.zzde(concat);
            final Iterator<String> iterator2 = zzgg.zzbrn.iterator();
            while (iterator2.hasNext()) {
                final zzgk zzgk = new zzgk(this.mContext, iterator2.next(), this.zzals, this.zzbsv, zzgg, this.zzbtk.zzcfu, this.zzbtk.zzaqz, this.zzbtk.zzaqv, this.zzatk, this.zzazd, this.zzbtk.zzarn, this.zzbtk.zzarr);
                final zzlj<zzgl> zza = zzkq.zza(cachedThreadPool, (Callable<zzgl>)new Callable<zzgl>() {
                    public zzgl zznl() throws Exception {
                        zzgl zza;
                        synchronized (zzgn.this.zzakd) {
                            if (zzgn.this.zzbto) {
                                // monitorexit(zzgn.zza(this.zzbts))
                                zza = null;
                            }
                            else {
                                // monitorexit(zzgn.zza(this.zzbts))
                                zza = zzgk.zza(zzgn.this.zzbtl, zzgn.this.zzbtm);
                            }
                        }
                        return zza;
                    }
                });
                this.zzbtp.put(zza, zzgk);
                list2.add(zza);
            }
        }
        zzgl zzgl = null;
        switch (this.zzbtn) {
            default: {
                zzgl = this.zze(list2);
                break;
            }
            case 2: {
                zzgl = this.zzf(list2);
                break;
            }
        }
        return zzgl;
    }
    
    @Override
    public List<zzgl> zzne() {
        return this.zzbtq;
    }
}
