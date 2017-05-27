// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;
import java.util.List;
import java.io.FileNotFoundException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.clearcut.zzb;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.gass.internal.zza;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.security.SecureRandom;
import java.util.concurrent.Executors;
import android.util.Log;
import android.support.annotation.NonNull;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.util.HashMap;
import android.util.Pair;
import java.util.Map;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.Future;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import dalvik.system.DexClassLoader;
import java.util.concurrent.ExecutorService;
import android.content.Context;
import com.google.android.gms.common.zzc;

public class zzbb
{
    private static final String TAG;
    protected static final Object zzaia;
    private static zzc zzaic;
    private volatile boolean zzagr;
    protected Context zzahn;
    protected Context zzaho;
    private ExecutorService zzahp;
    private DexClassLoader zzahq;
    private zzaw zzahr;
    private byte[] zzahs;
    private volatile AdvertisingIdClient zzaht;
    private Future zzahu;
    private volatile zzae.zza zzahv;
    private Future zzahw;
    private zzao zzahx;
    private GoogleApiClient zzahy;
    protected boolean zzahz;
    protected boolean zzaib;
    protected boolean zzaid;
    private Map<Pair<String, String>, zzbu> zzaie;
    
    static {
        TAG = zzbb.class.getSimpleName();
        zzaia = new Object();
        zzbb.zzaic = null;
    }
    
    private zzbb(final Context zzahn) {
        this.zzaht = null;
        this.zzagr = false;
        this.zzahu = null;
        this.zzahv = null;
        this.zzahw = null;
        this.zzahy = null;
        this.zzahz = false;
        this.zzaib = false;
        this.zzaid = false;
        this.zzahn = zzahn;
        this.zzaho = zzahn.getApplicationContext();
        this.zzaie = new HashMap<Pair<String, String>, zzbu>();
    }
    
    public static zzbb zza(final Context context, final String s, final String s2, final boolean b) {
        while (true) {
            zzbb zzbb = new zzbb(context);
            try {
                if (zzbb.zzc(s, s2, b)) {
                    return zzbb;
                }
            }
            catch (zzay zzay) {}
            zzbb = null;
            return zzbb;
        }
    }
    
    @NonNull
    private File zza(final String s, final File file, final String s2) throws zzaw.zza, IOException {
        final File file2 = new File(String.format("%s/%s.jar", file, s2));
        if (!file2.exists()) {
            final byte[] zzc = this.zzahr.zzc(this.zzahs, s);
            file2.createNewFile();
            final FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(zzc, 0, zzc.length);
            fileOutputStream.close();
        }
        return file2;
    }
    
    private void zza(final File file) {
        if (!file.exists()) {
            Log.d(zzbb.TAG, String.format("File %s not found. No need for deletion", file.getAbsolutePath()));
        }
        else {
            file.delete();
        }
    }
    
    private void zza(final File p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: new             Ljava/io/File;
        //     5: dup            
        //     6: ldc             "%s/%s.tmp"
        //     8: iconst_2       
        //     9: anewarray       Ljava/lang/Object;
        //    12: dup            
        //    13: iconst_0       
        //    14: aload_1        
        //    15: aastore        
        //    16: dup            
        //    17: iconst_1       
        //    18: aload_2        
        //    19: aastore        
        //    20: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    23: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    26: astore          4
        //    28: aload           4
        //    30: invokevirtual   java/io/File.exists:()Z
        //    33: ifeq            37
        //    36: return         
        //    37: new             Ljava/io/File;
        //    40: dup            
        //    41: ldc             "%s/%s.dex"
        //    43: iconst_2       
        //    44: anewarray       Ljava/lang/Object;
        //    47: dup            
        //    48: iconst_0       
        //    49: aload_1        
        //    50: aastore        
        //    51: dup            
        //    52: iconst_1       
        //    53: aload_2        
        //    54: aastore        
        //    55: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    58: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    61: astore          5
        //    63: aload           5
        //    65: invokevirtual   java/io/File.exists:()Z
        //    68: ifeq            36
        //    71: aload           5
        //    73: invokevirtual   java/io/File.length:()J
        //    76: lstore          6
        //    78: lload           6
        //    80: lconst_0       
        //    81: lcmp           
        //    82: ifle            36
        //    85: lload           6
        //    87: l2i            
        //    88: newarray        B
        //    90: astore          8
        //    92: new             Ljava/io/FileInputStream;
        //    95: dup            
        //    96: aload           5
        //    98: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   101: astore          9
        //   103: aload           9
        //   105: aload           8
        //   107: invokevirtual   java/io/FileInputStream.read:([B)I
        //   110: istore          19
        //   112: iload           19
        //   114: ifgt            136
        //   117: aload           9
        //   119: ifnull          127
        //   122: aload           9
        //   124: invokevirtual   java/io/FileInputStream.close:()V
        //   127: aload_0        
        //   128: aload           5
        //   130: invokespecial   com/google/android/gms/internal/zzbb.zza:(Ljava/io/File;)V
        //   133: goto            36
        //   136: new             Lcom/google/android/gms/internal/zzae$zzd;
        //   139: dup            
        //   140: invokespecial   com/google/android/gms/internal/zzae$zzd.<init>:()V
        //   143: astore          20
        //   145: aload           20
        //   147: getstatic       android/os/Build$VERSION.SDK:Ljava/lang/String;
        //   150: invokevirtual   java/lang/String.getBytes:()[B
        //   153: putfield        com/google/android/gms/internal/zzae$zzd.zzft:[B
        //   156: aload           20
        //   158: aload_2        
        //   159: invokevirtual   java/lang/String.getBytes:()[B
        //   162: putfield        com/google/android/gms/internal/zzae$zzd.zzfs:[B
        //   165: aload_0        
        //   166: getfield        com/google/android/gms/internal/zzbb.zzahr:Lcom/google/android/gms/internal/zzaw;
        //   169: aload_0        
        //   170: getfield        com/google/android/gms/internal/zzbb.zzahs:[B
        //   173: aload           8
        //   175: invokevirtual   com/google/android/gms/internal/zzaw.zzd:([B[B)Ljava/lang/String;
        //   178: invokevirtual   java/lang/String.getBytes:()[B
        //   181: astore          21
        //   183: aload           20
        //   185: aload           21
        //   187: putfield        com/google/android/gms/internal/zzae$zzd.data:[B
        //   190: aload           20
        //   192: aload           21
        //   194: invokestatic    com/google/android/gms/internal/zzam.zzg:([B)[B
        //   197: putfield        com/google/android/gms/internal/zzae$zzd.zzfr:[B
        //   200: aload           4
        //   202: invokevirtual   java/io/File.createNewFile:()Z
        //   205: pop            
        //   206: new             Ljava/io/FileOutputStream;
        //   209: dup            
        //   210: aload           4
        //   212: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   215: astore          11
        //   217: aload           20
        //   219: invokestatic    com/google/android/gms/internal/zzark.zzf:(Lcom/google/android/gms/internal/zzark;)[B
        //   222: astore          27
        //   224: aload           11
        //   226: aload           27
        //   228: iconst_0       
        //   229: aload           27
        //   231: arraylength    
        //   232: invokevirtual   java/io/FileOutputStream.write:([BII)V
        //   235: aload           11
        //   237: invokevirtual   java/io/FileOutputStream.close:()V
        //   240: aload           9
        //   242: ifnull          250
        //   245: aload           9
        //   247: invokevirtual   java/io/FileInputStream.close:()V
        //   250: aload           11
        //   252: ifnull          260
        //   255: aload           11
        //   257: invokevirtual   java/io/FileOutputStream.close:()V
        //   260: aload_0        
        //   261: aload           5
        //   263: invokespecial   com/google/android/gms/internal/zzbb.zza:(Ljava/io/File;)V
        //   266: goto            36
        //   269: astore          33
        //   271: aconst_null    
        //   272: astore          11
        //   274: aload_3        
        //   275: ifnull          282
        //   278: aload_3        
        //   279: invokevirtual   java/io/FileInputStream.close:()V
        //   282: aload           11
        //   284: ifnull          292
        //   287: aload           11
        //   289: invokevirtual   java/io/FileOutputStream.close:()V
        //   292: aload_0        
        //   293: aload           5
        //   295: invokespecial   com/google/android/gms/internal/zzbb.zza:(Ljava/io/File;)V
        //   298: goto            36
        //   301: astore          16
        //   303: aconst_null    
        //   304: astore          9
        //   306: aload           9
        //   308: ifnull          316
        //   311: aload           9
        //   313: invokevirtual   java/io/FileInputStream.close:()V
        //   316: aload_3        
        //   317: ifnull          324
        //   320: aload_3        
        //   321: invokevirtual   java/io/FileOutputStream.close:()V
        //   324: aload_0        
        //   325: aload           5
        //   327: invokespecial   com/google/android/gms/internal/zzbb.zza:(Ljava/io/File;)V
        //   330: aload           16
        //   332: athrow         
        //   333: astore          30
        //   335: goto            127
        //   338: astore          29
        //   340: goto            250
        //   343: astore          28
        //   345: goto            260
        //   348: astore          13
        //   350: goto            282
        //   353: astore          12
        //   355: goto            292
        //   358: astore          18
        //   360: goto            316
        //   363: astore          17
        //   365: goto            324
        //   368: astore          16
        //   370: aconst_null    
        //   371: astore_3       
        //   372: goto            306
        //   375: astore          26
        //   377: aload           11
        //   379: astore_3       
        //   380: aload           26
        //   382: astore          16
        //   384: goto            306
        //   387: astore          15
        //   389: aload           9
        //   391: astore_3       
        //   392: aconst_null    
        //   393: astore          11
        //   395: goto            274
        //   398: astore          25
        //   400: aload           9
        //   402: astore_3       
        //   403: goto            274
        //   406: astore          32
        //   408: aconst_null    
        //   409: astore          11
        //   411: aconst_null    
        //   412: astore_3       
        //   413: goto            274
        //   416: astore          14
        //   418: aload           9
        //   420: astore_3       
        //   421: aconst_null    
        //   422: astore          11
        //   424: goto            274
        //   427: astore          24
        //   429: aload           9
        //   431: astore_3       
        //   432: goto            274
        //   435: astore          31
        //   437: aconst_null    
        //   438: astore          11
        //   440: aconst_null    
        //   441: astore_3       
        //   442: goto            274
        //   445: astore          10
        //   447: aload           9
        //   449: astore_3       
        //   450: aconst_null    
        //   451: astore          11
        //   453: goto            274
        //   456: astore          23
        //   458: aload           9
        //   460: astore_3       
        //   461: goto            274
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  92     103    406    416    Ljava/io/IOException;
        //  92     103    269    274    Ljava/security/NoSuchAlgorithmException;
        //  92     103    435    445    Lcom/google/android/gms/internal/zzaw$zza;
        //  92     103    301    306    Any
        //  103    112    416    427    Ljava/io/IOException;
        //  103    112    387    398    Ljava/security/NoSuchAlgorithmException;
        //  103    112    445    456    Lcom/google/android/gms/internal/zzaw$zza;
        //  103    112    368    375    Any
        //  122    127    333    338    Ljava/io/IOException;
        //  136    217    416    427    Ljava/io/IOException;
        //  136    217    387    398    Ljava/security/NoSuchAlgorithmException;
        //  136    217    445    456    Lcom/google/android/gms/internal/zzaw$zza;
        //  136    217    368    375    Any
        //  217    240    427    435    Ljava/io/IOException;
        //  217    240    398    406    Ljava/security/NoSuchAlgorithmException;
        //  217    240    456    464    Lcom/google/android/gms/internal/zzaw$zza;
        //  217    240    375    387    Any
        //  245    250    338    343    Ljava/io/IOException;
        //  255    260    343    348    Ljava/io/IOException;
        //  278    282    348    353    Ljava/io/IOException;
        //  287    292    353    358    Ljava/io/IOException;
        //  311    316    358    363    Ljava/io/IOException;
        //  320    324    363    368    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 226, Size: 226
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
    
    private boolean zzb(final File p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: iconst_1       
        //     3: istore          4
        //     5: iconst_2       
        //     6: anewarray       Ljava/lang/Object;
        //     9: astore          5
        //    11: aload           5
        //    13: iconst_0       
        //    14: aload_1        
        //    15: aastore        
        //    16: aload           5
        //    18: iload           4
        //    20: aload_2        
        //    21: aastore        
        //    22: new             Ljava/io/File;
        //    25: dup            
        //    26: ldc             "%s/%s.tmp"
        //    28: aload           5
        //    30: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    33: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    36: astore          6
        //    38: aload           6
        //    40: invokevirtual   java/io/File.exists:()Z
        //    43: ifne            52
        //    46: iconst_0       
        //    47: istore          4
        //    49: iload           4
        //    51: ireturn        
        //    52: iconst_2       
        //    53: anewarray       Ljava/lang/Object;
        //    56: astore          7
        //    58: aload           7
        //    60: iconst_0       
        //    61: aload_1        
        //    62: aastore        
        //    63: aload           7
        //    65: iload           4
        //    67: aload_2        
        //    68: aastore        
        //    69: new             Ljava/io/File;
        //    72: dup            
        //    73: ldc             "%s/%s.dex"
        //    75: aload           7
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    83: astore          8
        //    85: aload           8
        //    87: invokevirtual   java/io/File.exists:()Z
        //    90: ifeq            99
        //    93: iconst_0       
        //    94: istore          4
        //    96: goto            49
        //    99: aload           6
        //   101: invokevirtual   java/io/File.length:()J
        //   104: lstore          19
        //   106: lload           19
        //   108: lconst_0       
        //   109: lcmp           
        //   110: ifgt            125
        //   113: aload_0        
        //   114: aload           6
        //   116: invokespecial   com/google/android/gms/internal/zzbb.zza:(Ljava/io/File;)V
        //   119: iconst_0       
        //   120: istore          4
        //   122: goto            49
        //   125: lload           19
        //   127: l2i            
        //   128: newarray        B
        //   130: astore          21
        //   132: new             Ljava/io/FileInputStream;
        //   135: dup            
        //   136: aload           6
        //   138: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   141: astore          15
        //   143: aload           15
        //   145: aload           21
        //   147: invokevirtual   java/io/FileInputStream.read:([B)I
        //   150: ifgt            184
        //   153: getstatic       com/google/android/gms/internal/zzbb.TAG:Ljava/lang/String;
        //   156: ldc             "Cannot read the cache data."
        //   158: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   161: pop            
        //   162: aload_0        
        //   163: aload           6
        //   165: invokespecial   com/google/android/gms/internal/zzbb.zza:(Ljava/io/File;)V
        //   168: aload           15
        //   170: ifnull          178
        //   173: aload           15
        //   175: invokevirtual   java/io/FileInputStream.close:()V
        //   178: iconst_0       
        //   179: istore          4
        //   181: goto            49
        //   184: aload           21
        //   186: invokestatic    com/google/android/gms/internal/zzae$zzd.zzd:([B)Lcom/google/android/gms/internal/zzae$zzd;
        //   189: astore          25
        //   191: aload_2        
        //   192: new             Ljava/lang/String;
        //   195: dup            
        //   196: aload           25
        //   198: getfield        com/google/android/gms/internal/zzae$zzd.zzfs:[B
        //   201: invokespecial   java/lang/String.<init>:([B)V
        //   204: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   207: ifeq            246
        //   210: aload           25
        //   212: getfield        com/google/android/gms/internal/zzae$zzd.zzfr:[B
        //   215: aload           25
        //   217: getfield        com/google/android/gms/internal/zzae$zzd.data:[B
        //   220: invokestatic    com/google/android/gms/internal/zzam.zzg:([B)[B
        //   223: invokestatic    java/util/Arrays.equals:([B[B)Z
        //   226: ifeq            246
        //   229: aload           25
        //   231: getfield        com/google/android/gms/internal/zzae$zzd.zzft:[B
        //   234: getstatic       android/os/Build$VERSION.SDK:Ljava/lang/String;
        //   237: invokevirtual   java/lang/String.getBytes:()[B
        //   240: invokestatic    java/util/Arrays.equals:([B[B)Z
        //   243: ifne            268
        //   246: aload_0        
        //   247: aload           6
        //   249: invokespecial   com/google/android/gms/internal/zzbb.zza:(Ljava/io/File;)V
        //   252: aload           15
        //   254: ifnull          262
        //   257: aload           15
        //   259: invokevirtual   java/io/FileInputStream.close:()V
        //   262: iconst_0       
        //   263: istore          4
        //   265: goto            49
        //   268: aload_0        
        //   269: getfield        com/google/android/gms/internal/zzbb.zzahr:Lcom/google/android/gms/internal/zzaw;
        //   272: aload_0        
        //   273: getfield        com/google/android/gms/internal/zzbb.zzahs:[B
        //   276: new             Ljava/lang/String;
        //   279: dup            
        //   280: aload           25
        //   282: getfield        com/google/android/gms/internal/zzae$zzd.data:[B
        //   285: invokespecial   java/lang/String.<init>:([B)V
        //   288: invokevirtual   com/google/android/gms/internal/zzaw.zzc:([BLjava/lang/String;)[B
        //   291: astore          27
        //   293: aload           8
        //   295: invokevirtual   java/io/File.createNewFile:()Z
        //   298: pop            
        //   299: new             Ljava/io/FileOutputStream;
        //   302: dup            
        //   303: aload           8
        //   305: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   308: astore          29
        //   310: aload           29
        //   312: aload           27
        //   314: iconst_0       
        //   315: aload           27
        //   317: arraylength    
        //   318: invokevirtual   java/io/FileOutputStream.write:([BII)V
        //   321: aload           15
        //   323: ifnull          331
        //   326: aload           15
        //   328: invokevirtual   java/io/FileInputStream.close:()V
        //   331: aload           29
        //   333: ifnull          49
        //   336: aload           29
        //   338: invokevirtual   java/io/FileOutputStream.close:()V
        //   341: goto            49
        //   344: astore          33
        //   346: goto            49
        //   349: astore          18
        //   351: aconst_null    
        //   352: astore          10
        //   354: aload_3        
        //   355: ifnull          362
        //   358: aload_3        
        //   359: invokevirtual   java/io/FileInputStream.close:()V
        //   362: aload           10
        //   364: ifnull          372
        //   367: aload           10
        //   369: invokevirtual   java/io/FileOutputStream.close:()V
        //   372: iconst_0       
        //   373: istore          4
        //   375: goto            49
        //   378: astore          14
        //   380: aconst_null    
        //   381: astore          15
        //   383: aload           15
        //   385: ifnull          393
        //   388: aload           15
        //   390: invokevirtual   java/io/FileInputStream.close:()V
        //   393: aload_3        
        //   394: ifnull          401
        //   397: aload_3        
        //   398: invokevirtual   java/io/FileOutputStream.close:()V
        //   401: aload           14
        //   403: athrow         
        //   404: astore          36
        //   406: goto            178
        //   409: astore          26
        //   411: goto            262
        //   414: astore          34
        //   416: goto            331
        //   419: astore          12
        //   421: goto            362
        //   424: astore          11
        //   426: goto            372
        //   429: astore          17
        //   431: goto            393
        //   434: astore          16
        //   436: goto            401
        //   439: astore          14
        //   441: aconst_null    
        //   442: astore_3       
        //   443: goto            383
        //   446: astore          14
        //   448: aload           29
        //   450: astore_3       
        //   451: goto            383
        //   454: astore          24
        //   456: aload           15
        //   458: astore_3       
        //   459: aconst_null    
        //   460: astore          10
        //   462: goto            354
        //   465: astore          32
        //   467: aload           29
        //   469: astore          10
        //   471: aload           15
        //   473: astore_3       
        //   474: goto            354
        //   477: astore          13
        //   479: aconst_null    
        //   480: astore          10
        //   482: aconst_null    
        //   483: astore_3       
        //   484: goto            354
        //   487: astore          23
        //   489: aload           15
        //   491: astore_3       
        //   492: aconst_null    
        //   493: astore          10
        //   495: goto            354
        //   498: astore          31
        //   500: aload           29
        //   502: astore          10
        //   504: aload           15
        //   506: astore_3       
        //   507: goto            354
        //   510: astore          9
        //   512: aconst_null    
        //   513: astore          10
        //   515: aconst_null    
        //   516: astore_3       
        //   517: goto            354
        //   520: astore          22
        //   522: aload           15
        //   524: astore_3       
        //   525: aconst_null    
        //   526: astore          10
        //   528: goto            354
        //   531: astore          30
        //   533: aload           29
        //   535: astore          10
        //   537: aload           15
        //   539: astore_3       
        //   540: goto            354
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                      
        //  -----  -----  -----  -----  ------------------------------------------
        //  99     143    477    487    Ljava/io/IOException;
        //  99     143    349    354    Ljava/security/NoSuchAlgorithmException;
        //  99     143    510    520    Lcom/google/android/gms/internal/zzaw$zza;
        //  99     143    378    383    Any
        //  143    168    487    498    Ljava/io/IOException;
        //  143    168    454    465    Ljava/security/NoSuchAlgorithmException;
        //  143    168    520    531    Lcom/google/android/gms/internal/zzaw$zza;
        //  143    168    439    446    Any
        //  173    178    404    409    Ljava/io/IOException;
        //  184    252    487    498    Ljava/io/IOException;
        //  184    252    454    465    Ljava/security/NoSuchAlgorithmException;
        //  184    252    520    531    Lcom/google/android/gms/internal/zzaw$zza;
        //  184    252    439    446    Any
        //  257    262    409    414    Ljava/io/IOException;
        //  268    310    487    498    Ljava/io/IOException;
        //  268    310    454    465    Ljava/security/NoSuchAlgorithmException;
        //  268    310    520    531    Lcom/google/android/gms/internal/zzaw$zza;
        //  268    310    439    446    Any
        //  310    321    498    510    Ljava/io/IOException;
        //  310    321    465    477    Ljava/security/NoSuchAlgorithmException;
        //  310    321    531    543    Lcom/google/android/gms/internal/zzaw$zza;
        //  310    321    446    454    Any
        //  326    331    414    419    Ljava/io/IOException;
        //  336    341    344    349    Ljava/io/IOException;
        //  358    362    419    424    Ljava/io/IOException;
        //  367    372    424    429    Ljava/io/IOException;
        //  388    393    429    434    Ljava/io/IOException;
        //  397    401    434    439    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 260, Size: 260
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
    
    private void zzc(final boolean zzagr) {
        if (this.zzagr = zzagr) {
            this.zzahu = this.zzahp.submit(new Runnable() {
                @Override
                public void run() {
                    zzbb.this.zzcr();
                }
            });
        }
    }
    
    private boolean zzc(final String s, final String s2, final boolean b) throws zzay {
        this.zzahp = Executors.newCachedThreadPool();
        this.zzc(b);
        this.zzcu();
        this.zzcs();
        this.zzahr = new zzaw(null);
        try {
            this.zzahs = this.zzahr.zzn(s);
            final boolean zzo = this.zzo(s2);
            this.zzahx = new zzao(this);
            return zzo;
        }
        catch (zzaw.zza zza) {
            throw new zzay(zza);
        }
    }
    
    private void zzcr() {
        try {
            if (this.zzaht == null && this.zzaho != null) {
                final AdvertisingIdClient zzaht = new AdvertisingIdClient(this.zzaho);
                zzaht.start();
                this.zzaht = zzaht;
            }
        }
        catch (GooglePlayServicesRepairableException ex) {}
        catch (GooglePlayServicesNotAvailableException ex2) {
            goto Label_0040;
        }
        catch (IOException ex3) {
            goto Label_0040;
        }
    }
    
    private void zzct() {
        if (!this.zzaib) {
            return;
        }
        try {
            this.zzahv = zza.zzi(this.zzahn, this.zzahn.getPackageName(), Integer.toString(this.zzahn.getPackageManager().getPackageInfo(this.zzahn.getPackageName(), 0).versionCode));
        }
        catch (PackageManager$NameNotFoundException ex) {}
    }
    
    private void zzcu() {
        boolean zzaib = true;
        zzbb.zzaic = zzc.zzapd();
        this.zzahz = (zzbb.zzaic.zzbo(this.zzahn) > 0 && zzaib);
        if (zzbb.zzaic.isGooglePlayServicesAvailable(this.zzahn) != 0) {
            zzaib = false;
        }
        this.zzaib = zzaib;
        if (this.zzahn.getApplicationContext() != null) {
            this.zzahy = new GoogleApiClient.Builder(this.zzahn).addApi(zzb.API).build();
        }
        zzdi.initialize(this.zzahn);
    }
    
    private boolean zzo(final String s) throws zzay {
        try {
            File file = this.zzahn.getCacheDir();
            if (file != null) {
                goto Label_0052;
            }
            file = this.zzahn.getDir("dex", 0);
            if (file == null) {
                throw new zzay();
            }
            goto Label_0052;
        }
        catch (FileNotFoundException ex) {
            throw new zzay(ex);
        }
        catch (IOException ex2) {
            throw new zzay(ex2);
        }
        catch (zzaw.zza zza) {
            throw new zzay(zza);
        }
        catch (NullPointerException ex3) {
            throw new zzay(ex3);
        }
        try {
            final File file2;
            final File file3;
            this.zzahq = new DexClassLoader(file2.getAbsolutePath(), file3.getAbsolutePath(), (String)null, this.zzahn.getClassLoader());
            return true;
        }
        finally {}
    }
    
    private void zzp(final String s) {
        this.zza(new File(s));
    }
    
    public Context getApplicationContext() {
        return this.zzaho;
    }
    
    public Context getContext() {
        return this.zzahn;
    }
    
    public boolean zza(final String s, final String s2, final List<Class> list) {
        boolean b;
        if (!this.zzaie.containsKey(new Pair((Object)s, (Object)s2))) {
            this.zzaie.put((Pair<String, String>)new Pair((Object)s, (Object)s2), new zzbu(this, s, s2, list));
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public int zzau() {
        int zzau = Integer.MIN_VALUE;
        final zzao zzco = this.zzco();
        if (zzco != null) {
            zzau = zzco.zzau();
        }
        return zzau;
    }
    
    public Method zzc(final String s, final String s2) {
        final zzbu zzbu = this.zzaie.get(new Pair((Object)s, (Object)s2));
        Method zzdd;
        if (zzbu == null) {
            zzdd = null;
        }
        else {
            zzdd = zzbu.zzdd();
        }
        return zzdd;
    }
    
    public ExecutorService zzch() {
        return this.zzahp;
    }
    
    public DexClassLoader zzci() {
        return this.zzahq;
    }
    
    public zzaw zzcj() {
        return this.zzahr;
    }
    
    public byte[] zzck() {
        return this.zzahs;
    }
    
    public GoogleApiClient zzcl() {
        return this.zzahy;
    }
    
    public boolean zzcm() {
        return this.zzahz;
    }
    
    public boolean zzcn() {
        return this.zzaid;
    }
    
    public zzao zzco() {
        return this.zzahx;
    }
    
    public zzae.zza zzcp() {
        return this.zzahv;
    }
    
    public Future zzcq() {
        return this.zzahw;
    }
    
    void zzcs() {
        if (zzdi.zzbfh.get()) {
            this.zzahw = this.zzahp.submit(new Runnable() {
                @Override
                public void run() {
                    zzbb.this.zzct();
                }
            });
        }
    }
    
    public AdvertisingIdClient zzcv() {
        final boolean zzagr = this.zzagr;
        AdvertisingIdClient advertisingIdClient = null;
        if (zzagr) {
            if (this.zzaht != null) {
                advertisingIdClient = this.zzaht;
            }
            else {
                while (true) {
                    if (this.zzahu == null) {
                        break Label_0056;
                    }
                    try {
                        this.zzahu.get(2000L, TimeUnit.MILLISECONDS);
                        this.zzahu = null;
                        advertisingIdClient = this.zzaht;
                    }
                    catch (TimeoutException ex) {
                        this.zzahu.cancel(true);
                        continue;
                    }
                    catch (ExecutionException ex2) {
                        continue;
                    }
                    catch (InterruptedException ex3) {
                        continue;
                    }
                    break;
                }
            }
        }
        return advertisingIdClient;
    }
    
    public void zzcw() {
        while (true) {
            while (true) {
                synchronized (zzbb.zzaia) {
                    if (this.zzaid) {
                        break;
                    }
                    if (this.zzaib && this.zzahy != null) {
                        this.zzahy.connect();
                        this.zzaid = true;
                        break;
                    }
                }
                this.zzaid = false;
                continue;
            }
        }
    }
    
    public void zzcx() {
        synchronized (zzbb.zzaia) {
            if (this.zzaid && this.zzahy != null) {
                this.zzahy.disconnect();
                this.zzaid = false;
            }
        }
    }
}
