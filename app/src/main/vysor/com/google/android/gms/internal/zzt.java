// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.impl.cookie.DateUtils;
import java.util.Date;
import java.io.Serializable;
import org.apache.http.StatusLine;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;
import org.apache.http.Header;

public class zzt implements zzf
{
    protected static final boolean DEBUG;
    private static int zzbn;
    private static int zzbo;
    protected final zzy zzbp;
    protected final zzu zzbq;
    
    static {
        DEBUG = zzs.DEBUG;
        zzt.zzbn = 3000;
        zzt.zzbo = 4096;
    }
    
    public zzt(final zzy zzy) {
        this(zzy, new zzu(zzt.zzbo));
    }
    
    public zzt(final zzy zzbp, final zzu zzbq) {
        this.zzbp = zzbp;
        this.zzbq = zzbq;
    }
    
    protected static Map<String, String> zza(final Header[] array) {
        final TreeMap<String, String> treeMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < array.length; ++i) {
            treeMap.put(array[i].getName(), array[i].getValue());
        }
        return treeMap;
    }
    
    private void zza(final long n, final zzk<?> zzk, final byte[] array, final StatusLine statusLine) {
        if (zzt.DEBUG || n > zzt.zzbn) {
            final Object[] array2 = { zzk, n, null, null, null };
            Serializable value;
            if (array != null) {
                value = array.length;
            }
            else {
                value = "null";
            }
            array2[2] = value;
            array2[3] = statusLine.getStatusCode();
            array2[4] = zzk.zzt().zzd();
            zzs.zzb("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", array2);
        }
    }
    
    private static void zza(final String s, final zzk<?> zzk, final zzr zzr) throws zzr {
        final zzo zzt = zzk.zzt();
        final int zzs = zzk.zzs();
        try {
            zzt.zza(zzr);
            zzk.zzc(String.format("%s-retry [timeout=%s]", s, zzs));
        }
        catch (zzr zzr2) {
            zzk.zzc(String.format("%s-timeout-giveup [timeout=%s]", s, zzs));
            throw zzr2;
        }
    }
    
    private void zza(final Map<String, String> map, final zzb.zza zza) {
        if (zza != null) {
            if (zza.zza != null) {
                map.put("If-None-Match", zza.zza);
            }
            if (zza.zzc > 0L) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(zza.zzc)));
            }
        }
    }
    
    private byte[] zza(final HttpEntity p0) throws IOException, zzp {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/google/android/gms/internal/zzaa;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/google/android/gms/internal/zzt.zzbq:Lcom/google/android/gms/internal/zzu;
        //     8: aload_1        
        //     9: invokeinterface org/apache/http/HttpEntity.getContentLength:()J
        //    14: l2i            
        //    15: invokespecial   com/google/android/gms/internal/zzaa.<init>:(Lcom/google/android/gms/internal/zzu;I)V
        //    18: astore_2       
        //    19: aconst_null    
        //    20: astore_3       
        //    21: aload_1        
        //    22: invokeinterface org/apache/http/HttpEntity.getContent:()Ljava/io/InputStream;
        //    27: astore          6
        //    29: aconst_null    
        //    30: astore_3       
        //    31: aload           6
        //    33: ifnonnull       67
        //    36: new             Lcom/google/android/gms/internal/zzp;
        //    39: dup            
        //    40: invokespecial   com/google/android/gms/internal/zzp.<init>:()V
        //    43: athrow         
        //    44: astore          4
        //    46: aload_1        
        //    47: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //    52: aload_0        
        //    53: getfield        com/google/android/gms/internal/zzt.zzbq:Lcom/google/android/gms/internal/zzu;
        //    56: aload_3        
        //    57: invokevirtual   com/google/android/gms/internal/zzu.zza:([B)V
        //    60: aload_2        
        //    61: invokevirtual   com/google/android/gms/internal/zzaa.close:()V
        //    64: aload           4
        //    66: athrow         
        //    67: aload_0        
        //    68: getfield        com/google/android/gms/internal/zzt.zzbq:Lcom/google/android/gms/internal/zzu;
        //    71: sipush          1024
        //    74: invokevirtual   com/google/android/gms/internal/zzu.zzb:(I)[B
        //    77: astore_3       
        //    78: aload           6
        //    80: aload_3        
        //    81: invokevirtual   java/io/InputStream.read:([B)I
        //    84: istore          7
        //    86: iload           7
        //    88: iconst_m1      
        //    89: if_icmpeq       103
        //    92: aload_2        
        //    93: aload_3        
        //    94: iconst_0       
        //    95: iload           7
        //    97: invokevirtual   com/google/android/gms/internal/zzaa.write:([BII)V
        //   100: goto            78
        //   103: aload_2        
        //   104: invokevirtual   com/google/android/gms/internal/zzaa.toByteArray:()[B
        //   107: astore          8
        //   109: aload_1        
        //   110: invokeinterface org/apache/http/HttpEntity.consumeContent:()V
        //   115: aload_0        
        //   116: getfield        com/google/android/gms/internal/zzt.zzbq:Lcom/google/android/gms/internal/zzu;
        //   119: aload_3        
        //   120: invokevirtual   com/google/android/gms/internal/zzu.zza:([B)V
        //   123: aload_2        
        //   124: invokevirtual   com/google/android/gms/internal/zzaa.close:()V
        //   127: aload           8
        //   129: areturn        
        //   130: astore          9
        //   132: ldc             "Error occured when calling consumingContent"
        //   134: iconst_0       
        //   135: anewarray       Ljava/lang/Object;
        //   138: invokestatic    com/google/android/gms/internal/zzs.zza:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   141: goto            115
        //   144: astore          5
        //   146: ldc             "Error occured when calling consumingContent"
        //   148: iconst_0       
        //   149: anewarray       Ljava/lang/Object;
        //   152: invokestatic    com/google/android/gms/internal/zzs.zza:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   155: goto            52
        //    Exceptions:
        //  throws java.io.IOException
        //  throws com.google.android.gms.internal.zzp
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  21     44     44     67     Any
        //  46     52     144    158    Ljava/io/IOException;
        //  67     109    44     67     Any
        //  109    115    130    144    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
    public zzi zza(final zzk<?> p0) throws zzr {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //     3: lstore_2       
        //     4: aconst_null    
        //     5: astore          4
        //     7: invokestatic    java/util/Collections.emptyMap:()Ljava/util/Map;
        //    10: astore          5
        //    12: new             Ljava/util/HashMap;
        //    15: dup            
        //    16: invokespecial   java/util/HashMap.<init>:()V
        //    19: astore          6
        //    21: aload_0        
        //    22: aload           6
        //    24: aload_1        
        //    25: invokevirtual   com/google/android/gms/internal/zzk.zzh:()Lcom/google/android/gms/internal/zzb$zza;
        //    28: invokespecial   com/google/android/gms/internal/zzt.zza:(Ljava/util/Map;Lcom/google/android/gms/internal/zzb$zza;)V
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/internal/zzt.zzbp:Lcom/google/android/gms/internal/zzy;
        //    35: aload_1        
        //    36: aload           6
        //    38: invokeinterface com/google/android/gms/internal/zzy.zza:(Lcom/google/android/gms/internal/zzk;Ljava/util/Map;)Lorg/apache/http/HttpResponse;
        //    43: astore          17
        //    45: aload           17
        //    47: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //    52: astore          18
        //    54: aload           18
        //    56: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //    61: istore          19
        //    63: aload           17
        //    65: invokeinterface org/apache/http/HttpResponse.getAllHeaders:()[Lorg/apache/http/Header;
        //    70: invokestatic    com/google/android/gms/internal/zzt.zza:([Lorg/apache/http/Header;)Ljava/util/Map;
        //    73: astore          5
        //    75: iload           19
        //    77: sipush          304
        //    80: if_icmpne       161
        //    83: aload_1        
        //    84: invokevirtual   com/google/android/gms/internal/zzk.zzh:()Lcom/google/android/gms/internal/zzb$zza;
        //    87: astore          25
        //    89: aload           25
        //    91: ifnonnull       118
        //    94: new             Lcom/google/android/gms/internal/zzi;
        //    97: dup            
        //    98: sipush          304
        //   101: aconst_null    
        //   102: aload           5
        //   104: iconst_1       
        //   105: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   108: lload_2        
        //   109: lsub           
        //   110: invokespecial   com/google/android/gms/internal/zzi.<init>:(I[BLjava/util/Map;ZJ)V
        //   113: astore          23
        //   115: goto            526
        //   118: aload           25
        //   120: getfield        com/google/android/gms/internal/zzb$zza.zzf:Ljava/util/Map;
        //   123: aload           5
        //   125: invokeinterface java/util/Map.putAll:(Ljava/util/Map;)V
        //   130: new             Lcom/google/android/gms/internal/zzi;
        //   133: dup            
        //   134: sipush          304
        //   137: aload           25
        //   139: getfield        com/google/android/gms/internal/zzb$zza.data:[B
        //   142: aload           25
        //   144: getfield        com/google/android/gms/internal/zzb$zza.zzf:Ljava/util/Map;
        //   147: iconst_1       
        //   148: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   151: lload_2        
        //   152: lsub           
        //   153: invokespecial   com/google/android/gms/internal/zzi.<init>:(I[BLjava/util/Map;ZJ)V
        //   156: astore          23
        //   158: goto            526
        //   161: aload           17
        //   163: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   168: ifnull          245
        //   171: aload_0        
        //   172: aload           17
        //   174: invokeinterface org/apache/http/HttpResponse.getEntity:()Lorg/apache/http/HttpEntity;
        //   179: invokespecial   com/google/android/gms/internal/zzt.zza:(Lorg/apache/http/HttpEntity;)[B
        //   182: astore          24
        //   184: aload           24
        //   186: astore          20
        //   188: aload_0        
        //   189: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   192: lload_2        
        //   193: lsub           
        //   194: aload_1        
        //   195: aload           20
        //   197: aload           18
        //   199: invokespecial   com/google/android/gms/internal/zzt.zza:(JLcom/google/android/gms/internal/zzk;[BLorg/apache/http/StatusLine;)V
        //   202: iload           19
        //   204: sipush          200
        //   207: if_icmplt       218
        //   210: iload           19
        //   212: sipush          299
        //   215: if_icmple       253
        //   218: new             Ljava/io/IOException;
        //   221: dup            
        //   222: invokespecial   java/io/IOException.<init>:()V
        //   225: athrow         
        //   226: astore          16
        //   228: ldc_w           "socket"
        //   231: aload_1        
        //   232: new             Lcom/google/android/gms/internal/zzq;
        //   235: dup            
        //   236: invokespecial   com/google/android/gms/internal/zzq.<init>:()V
        //   239: invokestatic    com/google/android/gms/internal/zzt.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
        //   242: goto            4
        //   245: iconst_0       
        //   246: newarray        B
        //   248: astore          20
        //   250: goto            188
        //   253: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   256: lload_2        
        //   257: lsub           
        //   258: lstore          21
        //   260: new             Lcom/google/android/gms/internal/zzi;
        //   263: dup            
        //   264: iload           19
        //   266: aload           20
        //   268: aload           5
        //   270: iconst_0       
        //   271: lload           21
        //   273: invokespecial   com/google/android/gms/internal/zzi.<init>:(I[BLjava/util/Map;ZJ)V
        //   276: astore          23
        //   278: goto            526
        //   281: astore          15
        //   283: ldc_w           "connection"
        //   286: aload_1        
        //   287: new             Lcom/google/android/gms/internal/zzq;
        //   290: dup            
        //   291: invokespecial   com/google/android/gms/internal/zzq.<init>:()V
        //   294: invokestatic    com/google/android/gms/internal/zzt.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
        //   297: goto            4
        //   300: astore          12
        //   302: aload_1        
        //   303: invokevirtual   com/google/android/gms/internal/zzk.getUrl:()Ljava/lang/String;
        //   306: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   309: astore          13
        //   311: aload           13
        //   313: invokevirtual   java/lang/String.length:()I
        //   316: ifeq            341
        //   319: ldc_w           "Bad URL "
        //   322: aload           13
        //   324: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   327: astore          14
        //   329: new             Ljava/lang/RuntimeException;
        //   332: dup            
        //   333: aload           14
        //   335: aload           12
        //   337: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   340: athrow         
        //   341: new             Ljava/lang/String;
        //   344: dup            
        //   345: ldc_w           "Bad URL "
        //   348: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   351: astore          14
        //   353: goto            329
        //   356: astore          7
        //   358: aconst_null    
        //   359: astore          8
        //   361: aload           4
        //   363: ifnull          472
        //   366: aload           4
        //   368: invokeinterface org/apache/http/HttpResponse.getStatusLine:()Lorg/apache/http/StatusLine;
        //   373: invokeinterface org/apache/http/StatusLine.getStatusCode:()I
        //   378: istore          9
        //   380: iconst_2       
        //   381: anewarray       Ljava/lang/Object;
        //   384: astore          10
        //   386: aload           10
        //   388: iconst_0       
        //   389: iload           9
        //   391: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   394: aastore        
        //   395: aload           10
        //   397: iconst_1       
        //   398: aload_1        
        //   399: invokevirtual   com/google/android/gms/internal/zzk.getUrl:()Ljava/lang/String;
        //   402: aastore        
        //   403: ldc_w           "Unexpected response code %d for %s"
        //   406: aload           10
        //   408: invokestatic    com/google/android/gms/internal/zzs.zzc:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   411: aload           8
        //   413: ifnull          492
        //   416: new             Lcom/google/android/gms/internal/zzi;
        //   419: dup            
        //   420: iload           9
        //   422: aload           8
        //   424: aload           5
        //   426: iconst_0       
        //   427: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   430: lload_2        
        //   431: lsub           
        //   432: invokespecial   com/google/android/gms/internal/zzi.<init>:(I[BLjava/util/Map;ZJ)V
        //   435: astore          11
        //   437: iload           9
        //   439: sipush          401
        //   442: if_icmpeq       453
        //   445: iload           9
        //   447: sipush          403
        //   450: if_icmpne       482
        //   453: ldc_w           "auth"
        //   456: aload_1        
        //   457: new             Lcom/google/android/gms/internal/zza;
        //   460: dup            
        //   461: aload           11
        //   463: invokespecial   com/google/android/gms/internal/zza.<init>:(Lcom/google/android/gms/internal/zzi;)V
        //   466: invokestatic    com/google/android/gms/internal/zzt.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
        //   469: goto            4
        //   472: new             Lcom/google/android/gms/internal/zzj;
        //   475: dup            
        //   476: aload           7
        //   478: invokespecial   com/google/android/gms/internal/zzj.<init>:(Ljava/lang/Throwable;)V
        //   481: athrow         
        //   482: new             Lcom/google/android/gms/internal/zzp;
        //   485: dup            
        //   486: aload           11
        //   488: invokespecial   com/google/android/gms/internal/zzp.<init>:(Lcom/google/android/gms/internal/zzi;)V
        //   491: athrow         
        //   492: new             Lcom/google/android/gms/internal/zzh;
        //   495: dup            
        //   496: aconst_null    
        //   497: invokespecial   com/google/android/gms/internal/zzh.<init>:(Lcom/google/android/gms/internal/zzi;)V
        //   500: athrow         
        //   501: astore          7
        //   503: aload           17
        //   505: astore          4
        //   507: aconst_null    
        //   508: astore          8
        //   510: goto            361
        //   513: astore          7
        //   515: aload           20
        //   517: astore          8
        //   519: aload           17
        //   521: astore          4
        //   523: goto            361
        //   526: aload           23
        //   528: areturn        
        //    Exceptions:
        //  throws com.google.android.gms.internal.zzr
        //    Signature:
        //  (Lcom/google/android/gms/internal/zzk<*>;)Lcom/google/android/gms/internal/zzi;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                          
        //  -----  -----  -----  -----  ----------------------------------------------
        //  12     45     226    245    Ljava/net/SocketTimeoutException;
        //  12     45     281    300    Lorg/apache/http/conn/ConnectTimeoutException;
        //  12     45     300    356    Ljava/net/MalformedURLException;
        //  12     45     356    361    Ljava/io/IOException;
        //  45     184    226    245    Ljava/net/SocketTimeoutException;
        //  45     184    281    300    Lorg/apache/http/conn/ConnectTimeoutException;
        //  45     184    300    356    Ljava/net/MalformedURLException;
        //  45     184    501    513    Ljava/io/IOException;
        //  188    226    226    245    Ljava/net/SocketTimeoutException;
        //  188    226    281    300    Lorg/apache/http/conn/ConnectTimeoutException;
        //  188    226    300    356    Ljava/net/MalformedURLException;
        //  188    226    513    526    Ljava/io/IOException;
        //  245    250    226    245    Ljava/net/SocketTimeoutException;
        //  245    250    281    300    Lorg/apache/http/conn/ConnectTimeoutException;
        //  245    250    300    356    Ljava/net/MalformedURLException;
        //  245    250    501    513    Ljava/io/IOException;
        //  253    278    226    245    Ljava/net/SocketTimeoutException;
        //  253    278    281    300    Lorg/apache/http/conn/ConnectTimeoutException;
        //  253    278    300    356    Ljava/net/MalformedURLException;
        //  253    278    513    526    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 236, Size: 236
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
}
