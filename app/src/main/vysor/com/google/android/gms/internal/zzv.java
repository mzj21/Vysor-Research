// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.FilterInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Collections;
import android.os.SystemClock;
import java.util.Iterator;
import java.io.OutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.io.File;
import java.util.Map;

public class zzv implements com.google.android.gms.internal.zzb
{
    private final Map<String, zza> zzbw;
    private long zzbx;
    private final File zzby;
    private final int zzbz;
    
    public zzv(final File file) {
        this(file, 5242880);
    }
    
    public zzv(final File zzby, final int zzbz) {
        this.zzbw = new LinkedHashMap<String, zza>(16, 0.75f, true);
        this.zzbx = 0L;
        this.zzby = zzby;
        this.zzbz = zzbz;
    }
    
    private void removeEntry(final String s) {
        final zza zza = this.zzbw.get(s);
        if (zza != null) {
            this.zzbx -= zza.zzca;
            this.zzbw.remove(s);
        }
    }
    
    private static int zza(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        if (read == -1) {
            throw new EOFException();
        }
        return read;
    }
    
    static void zza(final OutputStream outputStream, final int n) throws IOException {
        outputStream.write(0xFF & n >> 0);
        outputStream.write(0xFF & n >> 8);
        outputStream.write(0xFF & n >> 16);
        outputStream.write(0xFF & n >> 24);
    }
    
    static void zza(final OutputStream outputStream, final long n) throws IOException {
        outputStream.write((byte)(n >>> 0));
        outputStream.write((byte)(n >>> 8));
        outputStream.write((byte)(n >>> 16));
        outputStream.write((byte)(n >>> 24));
        outputStream.write((byte)(n >>> 32));
        outputStream.write((byte)(n >>> 40));
        outputStream.write((byte)(n >>> 48));
        outputStream.write((byte)(n >>> 56));
    }
    
    static void zza(final OutputStream outputStream, final String s) throws IOException {
        final byte[] bytes = s.getBytes("UTF-8");
        zza(outputStream, (long)bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }
    
    private void zza(final String s, final zza zza) {
        if (!this.zzbw.containsKey(s)) {
            this.zzbx += zza.zzca;
        }
        else {
            this.zzbx += zza.zzca - this.zzbw.get(s).zzca;
        }
        this.zzbw.put(s, zza);
    }
    
    static void zza(final Map<String, String> map, final OutputStream outputStream) throws IOException {
        if (map != null) {
            zza(outputStream, map.size());
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                zza(outputStream, entry.getKey());
                zza(outputStream, entry.getValue());
            }
        }
        else {
            zza(outputStream, 0);
        }
    }
    
    private static byte[] zza(final InputStream inputStream, final int n) throws IOException {
        final byte[] array = new byte[n];
        int i;
        int read;
        for (i = 0; i < n; i += read) {
            read = inputStream.read(array, i, n - i);
            if (read == -1) {
                break;
            }
        }
        if (i != n) {
            throw new IOException(new StringBuilder(50).append("Expected ").append(n).append(" bytes, read ").append(i).append(" bytes").toString());
        }
        return array;
    }
    
    static int zzb(final InputStream inputStream) throws IOException {
        return 0x0 | zza(inputStream) << 0 | zza(inputStream) << 8 | zza(inputStream) << 16 | zza(inputStream) << 24;
    }
    
    static long zzc(final InputStream inputStream) throws IOException {
        return 0x0L | (0xFFL & zza(inputStream)) << 0 | (0xFFL & zza(inputStream)) << 8 | (0xFFL & zza(inputStream)) << 16 | (0xFFL & zza(inputStream)) << 24 | (0xFFL & zza(inputStream)) << 32 | (0xFFL & zza(inputStream)) << 40 | (0xFFL & zza(inputStream)) << 48 | (0xFFL & zza(inputStream)) << 56;
    }
    
    private void zzc(final int n) {
        if (this.zzbx + n >= this.zzbz) {
            if (zzs.DEBUG) {
                zzs.zza("Pruning old cache entries.", new Object[0]);
            }
            final long zzbx = this.zzbx;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final Iterator<Map.Entry<String, zza>> iterator = this.zzbw.entrySet().iterator();
            int n2 = 0;
            while (true) {
                while (iterator.hasNext()) {
                    final zza zza = iterator.next().getValue();
                    if (this.zzf(zza.zzcb).delete()) {
                        this.zzbx -= zza.zzca;
                    }
                    else {
                        zzs.zzb("Could not delete cache entry for key=%s, filename=%s", zza.zzcb, this.zze(zza.zzcb));
                    }
                    iterator.remove();
                    final int n3 = n2 + 1;
                    if (this.zzbx + n < 0.9f * this.zzbz) {
                        if (zzs.DEBUG) {
                            zzs.zza("pruned %d files, %d bytes, %d ms", n3, this.zzbx - zzbx, SystemClock.elapsedRealtime() - elapsedRealtime);
                        }
                        return;
                    }
                    else {
                        n2 = n3;
                    }
                }
                final int n3 = n2;
                continue;
            }
        }
    }
    
    static String zzd(final InputStream inputStream) throws IOException {
        return new String(zza(inputStream, (int)zzc(inputStream)), "UTF-8");
    }
    
    private String zze(final String s) {
        final int n = s.length() / 2;
        final String value = String.valueOf(String.valueOf(s.substring(0, n).hashCode()));
        final String value2 = String.valueOf(String.valueOf(s.substring(n).hashCode()));
        String concat;
        if (value2.length() != 0) {
            concat = value.concat(value2);
        }
        else {
            concat = new String(value);
        }
        return concat;
    }
    
    static Map<String, String> zze(final InputStream inputStream) throws IOException {
        final int zzb = zzb(inputStream);
        Map<String, String> emptyMap;
        if (zzb == 0) {
            emptyMap = Collections.emptyMap();
        }
        else {
            emptyMap = new HashMap<String, String>(zzb);
        }
        for (int i = 0; i < zzb; ++i) {
            emptyMap.put(zzd(inputStream).intern(), zzd(inputStream).intern());
        }
        return emptyMap;
    }
    
    @Override
    public void initialize() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/internal/zzv.zzby:Ljava/io/File;
        //     6: invokevirtual   java/io/File.exists:()Z
        //     9: ifne            50
        //    12: aload_0        
        //    13: getfield        com/google/android/gms/internal/zzv.zzby:Ljava/io/File;
        //    16: invokevirtual   java/io/File.mkdirs:()Z
        //    19: ifne            47
        //    22: iconst_1       
        //    23: anewarray       Ljava/lang/Object;
        //    26: astore          17
        //    28: aload           17
        //    30: iconst_0       
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/internal/zzv.zzby:Ljava/io/File;
        //    35: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    38: aastore        
        //    39: ldc_w           "Unable to create cache dir %s"
        //    42: aload           17
        //    44: invokestatic    com/google/android/gms/internal/zzs.zzc:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    47: aload_0        
        //    48: monitorexit    
        //    49: return         
        //    50: aload_0        
        //    51: getfield        com/google/android/gms/internal/zzv.zzby:Ljava/io/File;
        //    54: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    57: astore_2       
        //    58: aload_2        
        //    59: ifnull          47
        //    62: aload_2        
        //    63: arraylength    
        //    64: istore_3       
        //    65: iconst_0       
        //    66: istore          4
        //    68: iload           4
        //    70: iload_3        
        //    71: if_icmpge       47
        //    74: aload_2        
        //    75: iload           4
        //    77: aaload         
        //    78: astore          5
        //    80: aconst_null    
        //    81: astore          6
        //    83: new             Ljava/io/BufferedInputStream;
        //    86: dup            
        //    87: new             Ljava/io/FileInputStream;
        //    90: dup            
        //    91: aload           5
        //    93: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    96: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    99: astore          7
        //   101: aload           7
        //   103: invokestatic    com/google/android/gms/internal/zzv$zza.zzf:(Ljava/io/InputStream;)Lcom/google/android/gms/internal/zzv$zza;
        //   106: astore          14
        //   108: aload           14
        //   110: aload           5
        //   112: invokevirtual   java/io/File.length:()J
        //   115: putfield        com/google/android/gms/internal/zzv$zza.zzca:J
        //   118: aload_0        
        //   119: aload           14
        //   121: getfield        com/google/android/gms/internal/zzv$zza.zzcb:Ljava/lang/String;
        //   124: aload           14
        //   126: invokespecial   com/google/android/gms/internal/zzv.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/zzv$zza;)V
        //   129: aload           7
        //   131: ifnull          139
        //   134: aload           7
        //   136: invokevirtual   java/io/BufferedInputStream.close:()V
        //   139: iinc            4, 1
        //   142: goto            68
        //   145: astore          16
        //   147: aconst_null    
        //   148: astore          7
        //   150: aload           5
        //   152: ifnull          161
        //   155: aload           5
        //   157: invokevirtual   java/io/File.delete:()Z
        //   160: pop            
        //   161: aload           7
        //   163: ifnull          139
        //   166: aload           7
        //   168: invokevirtual   java/io/BufferedInputStream.close:()V
        //   171: goto            139
        //   174: astore          9
        //   176: goto            139
        //   179: astore          11
        //   181: aload           6
        //   183: ifnull          191
        //   186: aload           6
        //   188: invokevirtual   java/io/BufferedInputStream.close:()V
        //   191: aload           11
        //   193: athrow         
        //   194: astore_1       
        //   195: aload_0        
        //   196: monitorexit    
        //   197: aload_1        
        //   198: athrow         
        //   199: astore          15
        //   201: goto            139
        //   204: astore          12
        //   206: goto            191
        //   209: astore          10
        //   211: aload           7
        //   213: astore          6
        //   215: aload           10
        //   217: astore          11
        //   219: goto            181
        //   222: astore          8
        //   224: goto            150
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      47     194    199    Any
        //  50     80     194    199    Any
        //  83     101    145    150    Ljava/io/IOException;
        //  83     101    179    181    Any
        //  101    129    222    227    Ljava/io/IOException;
        //  101    129    209    222    Any
        //  134    139    199    204    Ljava/io/IOException;
        //  134    139    194    199    Any
        //  155    161    209    222    Any
        //  166    171    174    179    Ljava/io/IOException;
        //  166    171    194    199    Any
        //  186    191    204    209    Ljava/io/IOException;
        //  186    191    194    199    Any
        //  191    194    194    199    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0139:
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
    
    public void remove(final String s) {
        synchronized (this) {
            final boolean delete = this.zzf(s).delete();
            this.removeEntry(s);
            if (!delete) {
                zzs.zzb("Could not delete cache entry for key=%s, filename=%s", s, this.zze(s));
            }
        }
    }
    
    @Override
    public com.google.android.gms.internal.zzb.zza zza(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/internal/zzv.zzbw:Ljava/util/Map;
        //     6: aload_1        
        //     7: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: checkcast       Lcom/google/android/gms/internal/zzv$zza;
        //    15: astore_3       
        //    16: aload_3        
        //    17: ifnonnull       28
        //    20: aconst_null    
        //    21: astore          9
        //    23: aload_0        
        //    24: monitorexit    
        //    25: aload           9
        //    27: areturn        
        //    28: aload_0        
        //    29: aload_1        
        //    30: invokevirtual   com/google/android/gms/internal/zzv.zzf:(Ljava/lang/String;)Ljava/io/File;
        //    33: astore          4
        //    35: new             Lcom/google/android/gms/internal/zzv$zzb;
        //    38: dup            
        //    39: new             Ljava/io/FileInputStream;
        //    42: dup            
        //    43: aload           4
        //    45: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    48: aconst_null    
        //    49: invokespecial   com/google/android/gms/internal/zzv$zzb.<init>:(Ljava/io/InputStream;Lcom/google/android/gms/internal/zzv$1;)V
        //    52: astore          5
        //    54: aload           5
        //    56: invokestatic    com/google/android/gms/internal/zzv$zza.zzf:(Ljava/io/InputStream;)Lcom/google/android/gms/internal/zzv$zza;
        //    59: pop            
        //    60: aload_3        
        //    61: aload           5
        //    63: aload           4
        //    65: invokevirtual   java/io/File.length:()J
        //    68: aload           5
        //    70: invokestatic    com/google/android/gms/internal/zzv$zzb.zza:(Lcom/google/android/gms/internal/zzv$zzb;)I
        //    73: i2l            
        //    74: lsub           
        //    75: l2i            
        //    76: invokestatic    com/google/android/gms/internal/zzv.zza:(Ljava/io/InputStream;I)[B
        //    79: invokevirtual   com/google/android/gms/internal/zzv$zza.zzb:([B)Lcom/google/android/gms/internal/zzb$zza;
        //    82: astore          13
        //    84: aload           13
        //    86: astore          9
        //    88: aload           5
        //    90: ifnull          23
        //    93: aload           5
        //    95: invokevirtual   com/google/android/gms/internal/zzv$zzb.close:()V
        //    98: goto            23
        //   101: astore          14
        //   103: aconst_null    
        //   104: astore          9
        //   106: goto            23
        //   109: astore          6
        //   111: aconst_null    
        //   112: astore          5
        //   114: iconst_2       
        //   115: anewarray       Ljava/lang/Object;
        //   118: astore          10
        //   120: aload           10
        //   122: iconst_0       
        //   123: aload           4
        //   125: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   128: aastore        
        //   129: aload           10
        //   131: iconst_1       
        //   132: aload           6
        //   134: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   137: aastore        
        //   138: ldc_w           "%s: %s"
        //   141: aload           10
        //   143: invokestatic    com/google/android/gms/internal/zzs.zzb:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   146: aload_0        
        //   147: aload_1        
        //   148: invokevirtual   com/google/android/gms/internal/zzv.remove:(Ljava/lang/String;)V
        //   151: aload           5
        //   153: ifnull          161
        //   156: aload           5
        //   158: invokevirtual   com/google/android/gms/internal/zzv$zzb.close:()V
        //   161: aconst_null    
        //   162: astore          9
        //   164: goto            23
        //   167: astore          11
        //   169: aconst_null    
        //   170: astore          9
        //   172: goto            23
        //   175: astore          7
        //   177: aconst_null    
        //   178: astore          5
        //   180: aload           5
        //   182: ifnull          190
        //   185: aload           5
        //   187: invokevirtual   com/google/android/gms/internal/zzv$zzb.close:()V
        //   190: aload           7
        //   192: athrow         
        //   193: astore_2       
        //   194: aload_0        
        //   195: monitorexit    
        //   196: aload_2        
        //   197: athrow         
        //   198: astore          8
        //   200: aconst_null    
        //   201: astore          9
        //   203: goto            23
        //   206: astore          7
        //   208: goto            180
        //   211: astore          6
        //   213: goto            114
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      16     193    198    Any
        //  28     35     193    198    Any
        //  35     54     109    114    Ljava/io/IOException;
        //  35     54     175    180    Any
        //  54     84     211    216    Ljava/io/IOException;
        //  54     84     206    211    Any
        //  93     98     101    109    Ljava/io/IOException;
        //  93     98     193    198    Any
        //  114    151    206    211    Any
        //  156    161    167    175    Ljava/io/IOException;
        //  156    161    193    198    Any
        //  185    190    198    206    Ljava/io/IOException;
        //  185    190    193    198    Any
        //  190    193    193    198    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0114:
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
    public void zza(final String s, final com.google.android.gms.internal.zzb.zza zza) {
        synchronized (this) {
            this.zzc(zza.data.length);
            final File zzf = this.zzf(s);
            FileOutputStream fileOutputStream = null;
            zza zza2 = null;
            Label_0122: {
                try {
                    fileOutputStream = new FileOutputStream(zzf);
                    zza2 = new zza(s, zza);
                    if (!zza2.zza(fileOutputStream)) {
                        fileOutputStream.close();
                        zzs.zzb("Failed to write header for %s", zzf.getAbsolutePath());
                        throw new IOException();
                    }
                    break Label_0122;
                }
                catch (IOException ex) {
                    if (!zzf.delete()) {
                        zzs.zzb("Could not clean up file %s", zzf.getAbsolutePath());
                    }
                }
                return;
            }
            fileOutputStream.write(zza.data);
            fileOutputStream.close();
            this.zza(s, zza2);
        }
    }
    
    public File zzf(final String s) {
        return new File(this.zzby, this.zze(s));
    }
    
    static class zza
    {
        public String zza;
        public long zzb;
        public long zzc;
        public long zzca;
        public String zzcb;
        public long zzd;
        public long zze;
        public Map<String, String> zzf;
        
        private zza() {
        }
        
        public zza(final String zzcb, final com.google.android.gms.internal.zzb.zza zza) {
            this.zzcb = zzcb;
            this.zzca = zza.data.length;
            this.zza = zza.zza;
            this.zzb = zza.zzb;
            this.zzc = zza.zzc;
            this.zzd = zza.zzd;
            this.zze = zza.zze;
            this.zzf = zza.zzf;
        }
        
        public static zza zzf(final InputStream inputStream) throws IOException {
            final zza zza = new zza();
            if (zzv.zzb(inputStream) != 538247942) {
                throw new IOException();
            }
            zza.zzcb = zzv.zzd(inputStream);
            zza.zza = zzv.zzd(inputStream);
            if (zza.zza.equals("")) {
                zza.zza = null;
            }
            zza.zzb = zzv.zzc(inputStream);
            zza.zzc = zzv.zzc(inputStream);
            zza.zzd = zzv.zzc(inputStream);
            zza.zze = zzv.zzc(inputStream);
            zza.zzf = zzv.zze(inputStream);
            return zza;
        }
        
        public boolean zza(final OutputStream outputStream) {
            int n = 1;
            try {
                zzv.zza(outputStream, 538247942);
                zzv.zza(outputStream, this.zzcb);
                String zza;
                if (this.zza == null) {
                    zza = "";
                }
                else {
                    zza = this.zza;
                }
                zzv.zza(outputStream, zza);
                zzv.zza(outputStream, this.zzb);
                zzv.zza(outputStream, this.zzc);
                zzv.zza(outputStream, this.zzd);
                zzv.zza(outputStream, this.zze);
                zzv.zza(this.zzf, outputStream);
                outputStream.flush();
            }
            catch (IOException ex) {
                final Object[] array = new Object[n];
                array[0] = ex.toString();
                zzs.zzb("%s", array);
                n = 0;
            }
            return n != 0;
        }
        
        public com.google.android.gms.internal.zzb.zza zzb(final byte[] data) {
            final com.google.android.gms.internal.zzb.zza zza = new com.google.android.gms.internal.zzb.zza();
            zza.data = data;
            zza.zza = this.zza;
            zza.zzb = this.zzb;
            zza.zzc = this.zzc;
            zza.zzd = this.zzd;
            zza.zze = this.zze;
            zza.zzf = this.zzf;
            return zza;
        }
    }
    
    private static class zzb extends FilterInputStream
    {
        private int zzcc;
        
        private zzb(final InputStream inputStream) {
            super(inputStream);
            this.zzcc = 0;
        }
        
        @Override
        public int read() throws IOException {
            final int read = super.read();
            if (read != -1) {
                ++this.zzcc;
            }
            return read;
        }
        
        @Override
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            final int read = super.read(array, n, n2);
            if (read != -1) {
                this.zzcc += read;
            }
            return read;
        }
    }
}
