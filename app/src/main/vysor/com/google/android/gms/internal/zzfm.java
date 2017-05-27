// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Collections;
import java.util.HashSet;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Set;

@zziy
public class zzfm extends zzfj
{
    private static final Set<String> zzbog;
    private static final DecimalFormat zzboh;
    private File zzboi;
    private boolean zzboj;
    
    static {
        zzbog = Collections.synchronizedSet(new HashSet<String>());
        zzboh = new DecimalFormat("#,###");
    }
    
    public zzfm(final zzlt zzlt) {
        super(zzlt);
        final File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzb.zzdf("Context.getCacheDir() returned null");
        }
        else {
            this.zzboi = new File(cacheDir, "admobVideoStreams");
            if (!this.zzboi.isDirectory() && !this.zzboi.mkdirs()) {
                final String value = String.valueOf(this.zzboi.getAbsolutePath());
                String concat;
                if (value.length() != 0) {
                    concat = "Could not create preload cache directory at ".concat(value);
                }
                else {
                    concat = new String("Could not create preload cache directory at ");
                }
                zzb.zzdf(concat);
                this.zzboi = null;
            }
            else if (!this.zzboi.setReadable(true, false) || !this.zzboi.setExecutable(true, false)) {
                final String value2 = String.valueOf(this.zzboi.getAbsolutePath());
                String concat2;
                if (value2.length() != 0) {
                    concat2 = "Could not set cache file permissions at ".concat(value2);
                }
                else {
                    concat2 = new String("Could not set cache file permissions at ");
                }
                zzb.zzdf(concat2);
                this.zzboi = null;
            }
        }
    }
    
    private File zzb(final File file) {
        return new File(this.zzboi, String.valueOf(file.getName()).concat(".done"));
    }
    
    private static void zzc(final File file) {
        if (file.isFile()) {
            file.setLastModified(System.currentTimeMillis());
        }
        else {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {}
        }
    }
    
    @Override
    public void abort() {
        this.zzboj = true;
    }
    
    @Override
    public boolean zzbc(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzfm.zzboi:Ljava/io/File;
        //     4: ifnonnull       22
        //     7: aload_0        
        //     8: aload_1        
        //     9: aconst_null    
        //    10: ldc             "noCacheDir"
        //    12: aconst_null    
        //    13: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    16: iconst_0       
        //    17: istore          17
        //    19: iload           17
        //    21: ireturn        
        //    22: aload_0        
        //    23: invokevirtual   com/google/android/gms/internal/zzfm.zzmj:()I
        //    26: getstatic       com/google/android/gms/internal/zzdi.zzbbe:Lcom/google/android/gms/internal/zzde;
        //    29: invokevirtual   com/google/android/gms/internal/zzde.get:()Ljava/lang/Object;
        //    32: checkcast       Ljava/lang/Integer;
        //    35: invokevirtual   java/lang/Integer.intValue:()I
        //    38: if_icmple       68
        //    41: aload_0        
        //    42: invokevirtual   com/google/android/gms/internal/zzfm.zzmk:()Z
        //    45: ifne            22
        //    48: ldc             "Unable to expire stream cache"
        //    50: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //    53: aload_0        
        //    54: aload_1        
        //    55: aconst_null    
        //    56: ldc             "expireFailed"
        //    58: aconst_null    
        //    59: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    62: iconst_0       
        //    63: istore          17
        //    65: goto            19
        //    68: aload_0        
        //    69: aload_1        
        //    70: invokevirtual   com/google/android/gms/internal/zzfm.zzbd:(Ljava/lang/String;)Ljava/lang/String;
        //    73: astore_2       
        //    74: new             Ljava/io/File;
        //    77: dup            
        //    78: aload_0        
        //    79: getfield        com/google/android/gms/internal/zzfm.zzboi:Ljava/io/File;
        //    82: aload_2        
        //    83: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    86: astore_3       
        //    87: aload_0        
        //    88: aload_3        
        //    89: invokespecial   com/google/android/gms/internal/zzfm.zzb:(Ljava/io/File;)Ljava/io/File;
        //    92: astore          4
        //    94: aload_3        
        //    95: invokevirtual   java/io/File.isFile:()Z
        //    98: ifeq            175
        //   101: aload           4
        //   103: invokevirtual   java/io/File.isFile:()Z
        //   106: ifeq            175
        //   109: aload_3        
        //   110: invokevirtual   java/io/File.length:()J
        //   113: l2i            
        //   114: istore          59
        //   116: aload_1        
        //   117: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   120: astore          60
        //   122: aload           60
        //   124: invokevirtual   java/lang/String.length:()I
        //   127: ifeq            161
        //   130: ldc             "Stream cache hit at "
        //   132: aload           60
        //   134: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   137: astore          61
        //   139: aload           61
        //   141: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //   144: aload_0        
        //   145: aload_1        
        //   146: aload_3        
        //   147: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   150: iload           59
        //   152: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;I)V
        //   155: iconst_1       
        //   156: istore          17
        //   158: goto            19
        //   161: new             Ljava/lang/String;
        //   164: dup            
        //   165: ldc             "Stream cache hit at "
        //   167: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   170: astore          61
        //   172: goto            139
        //   175: aload_0        
        //   176: getfield        com/google/android/gms/internal/zzfm.zzboi:Ljava/io/File;
        //   179: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   182: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   185: astore          5
        //   187: aload_1        
        //   188: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   191: astore          6
        //   193: aload           6
        //   195: invokevirtual   java/lang/String.length:()I
        //   198: ifeq            288
        //   201: aload           5
        //   203: aload           6
        //   205: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   208: astore          7
        //   210: getstatic       com/google/android/gms/internal/zzfm.zzbog:Ljava/util/Set;
        //   213: astore          8
        //   215: aload           8
        //   217: monitorenter   
        //   218: getstatic       com/google/android/gms/internal/zzfm.zzbog:Ljava/util/Set;
        //   221: aload           7
        //   223: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   228: ifeq            316
        //   231: aload_1        
        //   232: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   235: astore          57
        //   237: aload           57
        //   239: invokevirtual   java/lang/String.length:()I
        //   242: ifeq            302
        //   245: ldc             "Stream cache already in progress at "
        //   247: aload           57
        //   249: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   252: astore          58
        //   254: aload           58
        //   256: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   259: aload_0        
        //   260: aload_1        
        //   261: aload_3        
        //   262: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   265: ldc             "inProgress"
        //   267: aconst_null    
        //   268: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   271: aload           8
        //   273: monitorexit    
        //   274: iconst_0       
        //   275: istore          17
        //   277: goto            19
        //   280: astore          9
        //   282: aload           8
        //   284: monitorexit    
        //   285: aload           9
        //   287: athrow         
        //   288: new             Ljava/lang/String;
        //   291: dup            
        //   292: aload           5
        //   294: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   297: astore          7
        //   299: goto            210
        //   302: new             Ljava/lang/String;
        //   305: dup            
        //   306: ldc             "Stream cache already in progress at "
        //   308: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   311: astore          58
        //   313: goto            254
        //   316: getstatic       com/google/android/gms/internal/zzfm.zzbog:Ljava/util/Set;
        //   319: aload           7
        //   321: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   326: pop            
        //   327: aload           8
        //   329: monitorexit    
        //   330: aconst_null    
        //   331: astore          11
        //   333: invokestatic    com/google/android/gms/ads/internal/zzu.zzgm:()Lcom/google/android/gms/internal/zzll;
        //   336: aload_1        
        //   337: getstatic       com/google/android/gms/internal/zzdi.zzbbk:Lcom/google/android/gms/internal/zzde;
        //   340: invokevirtual   com/google/android/gms/internal/zzde.get:()Ljava/lang/Object;
        //   343: checkcast       Ljava/lang/Integer;
        //   346: invokevirtual   java/lang/Integer.intValue:()I
        //   349: invokevirtual   com/google/android/gms/internal/zzll.zzb:(Ljava/lang/String;I)Ljava/net/HttpURLConnection;
        //   352: astore          21
        //   354: aload           21
        //   356: instanceof      Ljava/net/HttpURLConnection;
        //   359: ifeq            634
        //   362: aload           21
        //   364: checkcast       Ljava/net/HttpURLConnection;
        //   367: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   370: istore          54
        //   372: iload           54
        //   374: sipush          400
        //   377: if_icmplt       634
        //   380: ldc             "badUrl"
        //   382: astore          13
        //   384: iload           54
        //   386: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   389: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   392: astore          55
        //   394: aload           55
        //   396: invokevirtual   java/lang/String.length:()I
        //   399: ifeq            609
        //   402: ldc             "HTTP request failed. Code: "
        //   404: aload           55
        //   406: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   409: astore          56
        //   411: aload           56
        //   413: astore          14
        //   415: new             Ljava/io/IOException;
        //   418: dup            
        //   419: new             Ljava/lang/StringBuilder;
        //   422: dup            
        //   423: bipush          32
        //   425: aload_1        
        //   426: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   429: invokevirtual   java/lang/String.length:()I
        //   432: iadd           
        //   433: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   436: ldc             "HTTP status code "
        //   438: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   441: iload           54
        //   443: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   446: ldc             " at "
        //   448: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   451: aload_1        
        //   452: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   455: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   458: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   461: athrow         
        //   462: astore          12
        //   464: aload           12
        //   466: instanceof      Ljava/lang/RuntimeException;
        //   469: ifeq            482
        //   472: invokestatic    com/google/android/gms/ads/internal/zzu.zzgd:()Lcom/google/android/gms/internal/zzkh;
        //   475: aload           12
        //   477: ldc             "VideoStreamFullFileCache.preload"
        //   479: invokevirtual   com/google/android/gms/internal/zzkh.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //   482: aload           11
        //   484: invokevirtual   java/io/FileOutputStream.close:()V
        //   487: aload_0        
        //   488: getfield        com/google/android/gms/internal/zzfm.zzboj:Z
        //   491: ifeq            1431
        //   494: new             Ljava/lang/StringBuilder;
        //   497: dup            
        //   498: bipush          26
        //   500: aload_1        
        //   501: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   504: invokevirtual   java/lang/String.length:()I
        //   507: iadd           
        //   508: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   511: ldc_w           "Preload aborted for URL \""
        //   514: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   517: aload_1        
        //   518: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   521: ldc_w           "\""
        //   524: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   527: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   530: invokestatic    com/google/android/gms/internal/zzkn.zzde:(Ljava/lang/String;)V
        //   533: aload_3        
        //   534: invokevirtual   java/io/File.exists:()Z
        //   537: ifeq            579
        //   540: aload_3        
        //   541: invokevirtual   java/io/File.delete:()Z
        //   544: ifne            579
        //   547: aload_3        
        //   548: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   551: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   554: astore          18
        //   556: aload           18
        //   558: invokevirtual   java/lang/String.length:()I
        //   561: ifeq            1475
        //   564: ldc_w           "Could not delete partial cache file at "
        //   567: aload           18
        //   569: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   572: astore          19
        //   574: aload           19
        //   576: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   579: aload_0        
        //   580: aload_1        
        //   581: aload_3        
        //   582: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   585: aload           13
        //   587: aload           14
        //   589: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   592: getstatic       com/google/android/gms/internal/zzfm.zzbog:Ljava/util/Set;
        //   595: aload           7
        //   597: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //   602: pop            
        //   603: iconst_0       
        //   604: istore          17
        //   606: goto            19
        //   609: new             Ljava/lang/String;
        //   612: dup            
        //   613: ldc             "HTTP request failed. Code: "
        //   615: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   618: astore          14
        //   620: goto            415
        //   623: astore          12
        //   625: aconst_null    
        //   626: astore          14
        //   628: aconst_null    
        //   629: astore          11
        //   631: goto            464
        //   634: aload           21
        //   636: invokevirtual   java/net/URLConnection.getContentLength:()I
        //   639: istore          22
        //   641: iload           22
        //   643: ifge            720
        //   646: aload_1        
        //   647: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   650: astore          51
        //   652: aload           51
        //   654: invokevirtual   java/lang/String.length:()I
        //   657: ifeq            705
        //   660: ldc_w           "Stream cache aborted, missing content-length header at "
        //   663: aload           51
        //   665: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   668: astore          52
        //   670: aload           52
        //   672: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   675: aload_0        
        //   676: aload_1        
        //   677: aload_3        
        //   678: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   681: ldc_w           "contentLengthMissing"
        //   684: aconst_null    
        //   685: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   688: getstatic       com/google/android/gms/internal/zzfm.zzbog:Ljava/util/Set;
        //   691: aload           7
        //   693: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //   698: pop            
        //   699: iconst_0       
        //   700: istore          17
        //   702: goto            19
        //   705: new             Ljava/lang/String;
        //   708: dup            
        //   709: ldc_w           "Stream cache aborted, missing content-length header at "
        //   712: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   715: astore          52
        //   717: goto            670
        //   720: getstatic       com/google/android/gms/internal/zzfm.zzboh:Ljava/text/DecimalFormat;
        //   723: iload           22
        //   725: i2l            
        //   726: invokevirtual   java/text/DecimalFormat.format:(J)Ljava/lang/String;
        //   729: astore          23
        //   731: getstatic       com/google/android/gms/internal/zzdi.zzbbf:Lcom/google/android/gms/internal/zzde;
        //   734: invokevirtual   com/google/android/gms/internal/zzde.get:()Ljava/lang/Object;
        //   737: checkcast       Ljava/lang/Integer;
        //   740: invokevirtual   java/lang/Integer.intValue:()I
        //   743: istore          24
        //   745: iload           22
        //   747: iload           24
        //   749: if_icmple       876
        //   752: new             Ljava/lang/StringBuilder;
        //   755: dup            
        //   756: bipush          33
        //   758: aload           23
        //   760: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   763: invokevirtual   java/lang/String.length:()I
        //   766: iadd           
        //   767: aload_1        
        //   768: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   771: invokevirtual   java/lang/String.length:()I
        //   774: iadd           
        //   775: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   778: ldc_w           "Content length "
        //   781: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   784: aload           23
        //   786: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   789: ldc_w           " exceeds limit at "
        //   792: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   795: aload_1        
        //   796: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   799: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   802: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   805: aload           23
        //   807: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   810: astore          48
        //   812: aload           48
        //   814: invokevirtual   java/lang/String.length:()I
        //   817: ifeq            861
        //   820: ldc_w           "File too big for full file cache. Size: "
        //   823: aload           48
        //   825: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   828: astore          49
        //   830: aload_0        
        //   831: aload_1        
        //   832: aload_3        
        //   833: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   836: ldc_w           "sizeExceeded"
        //   839: aload           49
        //   841: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   844: getstatic       com/google/android/gms/internal/zzfm.zzbog:Ljava/util/Set;
        //   847: aload           7
        //   849: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //   854: pop            
        //   855: iconst_0       
        //   856: istore          17
        //   858: goto            19
        //   861: new             Ljava/lang/String;
        //   864: dup            
        //   865: ldc_w           "File too big for full file cache. Size: "
        //   868: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   871: astore          49
        //   873: goto            830
        //   876: new             Ljava/lang/StringBuilder;
        //   879: dup            
        //   880: bipush          20
        //   882: aload           23
        //   884: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   887: invokevirtual   java/lang/String.length:()I
        //   890: iadd           
        //   891: aload_1        
        //   892: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   895: invokevirtual   java/lang/String.length:()I
        //   898: iadd           
        //   899: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   902: ldc_w           "Caching "
        //   905: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   908: aload           23
        //   910: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   913: ldc_w           " bytes from "
        //   916: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   919: aload_1        
        //   920: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   923: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   926: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //   929: aload           21
        //   931: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   934: invokestatic    java/nio/channels/Channels.newChannel:(Ljava/io/InputStream;)Ljava/nio/channels/ReadableByteChannel;
        //   937: astore          25
        //   939: new             Ljava/io/FileOutputStream;
        //   942: dup            
        //   943: aload_3        
        //   944: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   947: astore          26
        //   949: aload           26
        //   951: invokevirtual   java/io/FileOutputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //   954: astore          27
        //   956: ldc_w           1048576
        //   959: invokestatic    java/nio/ByteBuffer.allocate:(I)Ljava/nio/ByteBuffer;
        //   962: astore          28
        //   964: invokestatic    com/google/android/gms/ads/internal/zzu.zzgf:()Lcom/google/android/gms/common/util/zze;
        //   967: astore          29
        //   969: iconst_0       
        //   970: istore          30
        //   972: aload           29
        //   974: invokeinterface com/google/android/gms/common/util/zze.currentTimeMillis:()J
        //   979: lstore          31
        //   981: getstatic       com/google/android/gms/internal/zzdi.zzbbj:Lcom/google/android/gms/internal/zzde;
        //   984: invokevirtual   com/google/android/gms/internal/zzde.get:()Ljava/lang/Object;
        //   987: checkcast       Ljava/lang/Long;
        //   990: invokevirtual   java/lang/Long.longValue:()J
        //   993: lstore          33
        //   995: new             Lcom/google/android/gms/internal/zzlc;
        //   998: dup            
        //   999: lload           33
        //  1001: invokespecial   com/google/android/gms/internal/zzlc.<init>:(J)V
        //  1004: astore          35
        //  1006: getstatic       com/google/android/gms/internal/zzdi.zzbbi:Lcom/google/android/gms/internal/zzde;
        //  1009: invokevirtual   com/google/android/gms/internal/zzde.get:()Ljava/lang/Object;
        //  1012: checkcast       Ljava/lang/Long;
        //  1015: invokevirtual   java/lang/Long.longValue:()J
        //  1018: lstore          36
        //  1020: aload           25
        //  1022: aload           28
        //  1024: invokeinterface java/nio/channels/ReadableByteChannel.read:(Ljava/nio/ByteBuffer;)I
        //  1029: istore          38
        //  1031: iload           38
        //  1033: iflt            1315
        //  1036: iload           30
        //  1038: iload           38
        //  1040: iadd           
        //  1041: istore          30
        //  1043: iload           30
        //  1045: iload           24
        //  1047: if_icmple       1134
        //  1050: ldc_w           "sizeExceeded"
        //  1053: astore          13
        //  1055: iload           30
        //  1057: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  1060: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1063: astore          46
        //  1065: aload           46
        //  1067: invokevirtual   java/lang/String.length:()I
        //  1070: ifeq            1107
        //  1073: ldc_w           "File too big for full file cache. Size: "
        //  1076: aload           46
        //  1078: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1081: astore          47
        //  1083: aload           47
        //  1085: astore          14
        //  1087: new             Ljava/io/IOException;
        //  1090: dup            
        //  1091: ldc_w           "stream cache file size limit exceeded"
        //  1094: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //  1097: athrow         
        //  1098: astore          12
        //  1100: aload           26
        //  1102: astore          11
        //  1104: goto            464
        //  1107: new             Ljava/lang/String;
        //  1110: dup            
        //  1111: ldc_w           "File too big for full file cache. Size: "
        //  1114: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1117: astore          14
        //  1119: goto            1087
        //  1122: astore          12
        //  1124: aload           26
        //  1126: astore          11
        //  1128: aconst_null    
        //  1129: astore          14
        //  1131: goto            464
        //  1134: aload           28
        //  1136: invokevirtual   java/nio/ByteBuffer.flip:()Ljava/nio/Buffer;
        //  1139: pop            
        //  1140: aload           27
        //  1142: aload           28
        //  1144: invokevirtual   java/nio/channels/FileChannel.write:(Ljava/nio/ByteBuffer;)I
        //  1147: ifgt            1140
        //  1150: aload           28
        //  1152: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //  1155: pop            
        //  1156: aload           29
        //  1158: invokeinterface com/google/android/gms/common/util/zze.currentTimeMillis:()J
        //  1163: lload           31
        //  1165: lsub           
        //  1166: ldc2_w          1000
        //  1169: lload           36
        //  1171: lmul           
        //  1172: lcmp           
        //  1173: ifle            1255
        //  1176: ldc_w           "downloadTimeout"
        //  1179: astore          13
        //  1181: lload           36
        //  1183: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //  1186: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1189: astore          44
        //  1191: new             Ljava/lang/StringBuilder;
        //  1194: dup            
        //  1195: bipush          29
        //  1197: aload           44
        //  1199: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1202: invokevirtual   java/lang/String.length:()I
        //  1205: iadd           
        //  1206: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  1209: ldc_w           "Timeout exceeded. Limit: "
        //  1212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1215: aload           44
        //  1217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1220: ldc_w           " sec"
        //  1223: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1226: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1229: astore          45
        //  1231: aload           45
        //  1233: astore          14
        //  1235: new             Ljava/io/IOException;
        //  1238: dup            
        //  1239: ldc_w           "stream cache time limit exceeded"
        //  1242: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //  1245: athrow         
        //  1246: astore          12
        //  1248: aload           26
        //  1250: astore          11
        //  1252: goto            464
        //  1255: aload_0        
        //  1256: getfield        com/google/android/gms/internal/zzfm.zzboj:Z
        //  1259: ifeq            1290
        //  1262: ldc_w           "externalAbort"
        //  1265: astore          13
        //  1267: new             Ljava/io/IOException;
        //  1270: dup            
        //  1271: ldc_w           "abort requested"
        //  1274: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //  1277: athrow         
        //  1278: astore          12
        //  1280: aload           26
        //  1282: astore          11
        //  1284: aconst_null    
        //  1285: astore          14
        //  1287: goto            464
        //  1290: aload           35
        //  1292: invokevirtual   com/google/android/gms/internal/zzlc.tryAcquire:()Z
        //  1295: ifeq            1020
        //  1298: aload_0        
        //  1299: aload_1        
        //  1300: aload_3        
        //  1301: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //  1304: iload           30
        //  1306: iload           22
        //  1308: iconst_0       
        //  1309: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;IIZ)V
        //  1312: goto            1020
        //  1315: aload           26
        //  1317: invokevirtual   java/io/FileOutputStream.close:()V
        //  1320: iconst_3       
        //  1321: invokestatic    com/google/android/gms/internal/zzkn.zzbf:(I)Z
        //  1324: ifeq            1391
        //  1327: getstatic       com/google/android/gms/internal/zzfm.zzboh:Ljava/text/DecimalFormat;
        //  1330: iload           30
        //  1332: i2l            
        //  1333: invokevirtual   java/text/DecimalFormat.format:(J)Ljava/lang/String;
        //  1336: astore          41
        //  1338: new             Ljava/lang/StringBuilder;
        //  1341: dup            
        //  1342: bipush          22
        //  1344: aload           41
        //  1346: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1349: invokevirtual   java/lang/String.length:()I
        //  1352: iadd           
        //  1353: aload_1        
        //  1354: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1357: invokevirtual   java/lang/String.length:()I
        //  1360: iadd           
        //  1361: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  1364: ldc_w           "Preloaded "
        //  1367: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1370: aload           41
        //  1372: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1375: ldc_w           " bytes from "
        //  1378: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1381: aload_1        
        //  1382: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1385: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1388: invokestatic    com/google/android/gms/internal/zzkn.zzdd:(Ljava/lang/String;)V
        //  1391: aload_3        
        //  1392: iconst_1       
        //  1393: iconst_0       
        //  1394: invokevirtual   java/io/File.setReadable:(ZZ)Z
        //  1397: pop            
        //  1398: aload           4
        //  1400: invokestatic    com/google/android/gms/internal/zzfm.zzc:(Ljava/io/File;)V
        //  1403: aload_0        
        //  1404: aload_1        
        //  1405: aload_3        
        //  1406: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //  1409: iload           30
        //  1411: invokevirtual   com/google/android/gms/internal/zzfm.zza:(Ljava/lang/String;Ljava/lang/String;I)V
        //  1414: getstatic       com/google/android/gms/internal/zzfm.zzbog:Ljava/util/Set;
        //  1417: aload           7
        //  1419: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //  1424: pop            
        //  1425: iconst_1       
        //  1426: istore          17
        //  1428: goto            19
        //  1431: new             Ljava/lang/StringBuilder;
        //  1434: dup            
        //  1435: bipush          25
        //  1437: aload_1        
        //  1438: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1441: invokevirtual   java/lang/String.length:()I
        //  1444: iadd           
        //  1445: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  1448: ldc_w           "Preload failed for URL \""
        //  1451: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1454: aload_1        
        //  1455: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1458: ldc_w           "\""
        //  1461: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1464: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1467: aload           12
        //  1469: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1472: goto            533
        //  1475: new             Ljava/lang/String;
        //  1478: dup            
        //  1479: ldc_w           "Could not delete partial cache file at "
        //  1482: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1485: astore          19
        //  1487: goto            574
        //  1490: astore          20
        //  1492: goto            487
        //  1495: astore          15
        //  1497: goto            487
        //  1500: astore          12
        //  1502: aconst_null    
        //  1503: astore          14
        //  1505: aconst_null    
        //  1506: astore          11
        //  1508: goto            464
        //  1511: astore          12
        //  1513: aconst_null    
        //  1514: astore          11
        //  1516: goto            464
        //  1519: astore          12
        //  1521: ldc_w           "error"
        //  1524: astore          13
        //  1526: aload           26
        //  1528: astore          11
        //  1530: aconst_null    
        //  1531: astore          14
        //  1533: goto            464
        //  1536: astore          12
        //  1538: ldc_w           "error"
        //  1541: astore          13
        //  1543: aconst_null    
        //  1544: astore          14
        //  1546: aconst_null    
        //  1547: astore          11
        //  1549: goto            464
        //  1552: astore          12
        //  1554: ldc_w           "error"
        //  1557: astore          13
        //  1559: aconst_null    
        //  1560: astore          14
        //  1562: aconst_null    
        //  1563: astore          11
        //  1565: goto            464
        //  1568: astore          12
        //  1570: ldc_w           "error"
        //  1573: astore          13
        //  1575: aload           26
        //  1577: astore          11
        //  1579: aconst_null    
        //  1580: astore          14
        //  1582: goto            464
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  218    285    280    288    Any
        //  302    330    280    288    Any
        //  333    384    1536   1552   Ljava/io/IOException;
        //  333    384    1552   1568   Ljava/lang/RuntimeException;
        //  384    411    623    634    Ljava/io/IOException;
        //  384    411    1500   1511   Ljava/lang/RuntimeException;
        //  415    462    462    464    Ljava/io/IOException;
        //  415    462    1511   1519   Ljava/lang/RuntimeException;
        //  482    487    1490   1495   Ljava/io/IOException;
        //  482    487    1495   1500   Ljava/lang/NullPointerException;
        //  609    620    623    634    Ljava/io/IOException;
        //  609    620    1500   1511   Ljava/lang/RuntimeException;
        //  634    949    1536   1552   Ljava/io/IOException;
        //  634    949    1552   1568   Ljava/lang/RuntimeException;
        //  949    1055   1568   1585   Ljava/io/IOException;
        //  949    1055   1519   1536   Ljava/lang/RuntimeException;
        //  1055   1083   1122   1134   Ljava/io/IOException;
        //  1055   1083   1278   1290   Ljava/lang/RuntimeException;
        //  1087   1098   1098   1107   Ljava/io/IOException;
        //  1087   1098   1246   1255   Ljava/lang/RuntimeException;
        //  1107   1119   1122   1134   Ljava/io/IOException;
        //  1107   1119   1278   1290   Ljava/lang/RuntimeException;
        //  1134   1181   1568   1585   Ljava/io/IOException;
        //  1134   1181   1519   1536   Ljava/lang/RuntimeException;
        //  1181   1231   1122   1134   Ljava/io/IOException;
        //  1181   1231   1278   1290   Ljava/lang/RuntimeException;
        //  1235   1246   1098   1107   Ljava/io/IOException;
        //  1235   1246   1246   1255   Ljava/lang/RuntimeException;
        //  1255   1267   1568   1585   Ljava/io/IOException;
        //  1255   1267   1519   1536   Ljava/lang/RuntimeException;
        //  1267   1278   1122   1134   Ljava/io/IOException;
        //  1267   1278   1278   1290   Ljava/lang/RuntimeException;
        //  1290   1425   1568   1585   Ljava/io/IOException;
        //  1290   1425   1519   1536   Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 698, Size: 698
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:635)
        //     at java.util.ArrayList.get(ArrayList.java:411)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
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
    
    public int zzmj() {
        final File zzboi = this.zzboi;
        int n = 0;
        if (zzboi != null) {
            final File[] listFiles = this.zzboi.listFiles();
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                if (!listFiles[i].getName().endsWith(".done")) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public boolean zzmk() {
        final File zzboi = this.zzboi;
        boolean b = false;
        if (zzboi != null) {
            File file = null;
            long n = Long.MAX_VALUE;
            final File[] listFiles = this.zzboi.listFiles();
            final int length = listFiles.length;
            int i = 0;
        Label_0083_Outer:
            while (i < length) {
                final File file2 = listFiles[i];
                while (true) {
                    Label_0143: {
                        if (file2.getName().endsWith(".done")) {
                            break Label_0143;
                        }
                        final long lastModified = file2.lastModified();
                        if (lastModified >= n) {
                            break Label_0143;
                        }
                        final File file3 = file2;
                        ++i;
                        file = file3;
                        n = lastModified;
                        continue Label_0083_Outer;
                    }
                    final long lastModified = n;
                    final File file3 = file;
                    continue;
                }
            }
            boolean delete;
            if (file != null) {
                delete = file.delete();
                final File zzb = this.zzb(file);
                if (zzb.isFile()) {
                    delete &= zzb.delete();
                }
            }
            else {
                delete = false;
            }
            b = delete;
        }
        return b;
    }
}
