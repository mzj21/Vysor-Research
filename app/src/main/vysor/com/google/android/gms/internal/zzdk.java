// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.net.Uri$Builder;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ArrayBlockingQueue;
import android.os.Environment;
import java.util.HashMap;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.BlockingQueue;
import android.content.Context;

@zziy
public class zzdk
{
    final Context mContext;
    final String zzati;
    String zzbhz;
    BlockingQueue<zzdq> zzbib;
    ExecutorService zzbic;
    LinkedHashMap<String, String> zzbid;
    Map<String, zzdn> zzbie;
    private AtomicBoolean zzbif;
    private File zzbig;
    
    public zzdk(final Context mContext, final String zzati, final String zzbhz, final Map<String, String> map) {
        this.zzbid = new LinkedHashMap<String, String>();
        this.zzbie = new HashMap<String, zzdn>();
        this.mContext = mContext;
        this.zzati = zzati;
        this.zzbhz = zzbhz;
        (this.zzbif = new AtomicBoolean(false)).set(zzdi.zzbcc.get());
        if (this.zzbif.get()) {
            final File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.zzbig = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            this.zzbid.put(entry.getKey(), entry.getValue());
        }
        this.zzbib = new ArrayBlockingQueue<zzdq>(30);
        (this.zzbic = Executors.newSingleThreadExecutor()).execute(new Runnable() {
            @Override
            public void run() {
                zzdk.this.zzkw();
            }
        });
        this.zzbie.put("action", zzdn.zzbij);
        this.zzbie.put("ad_format", zzdn.zzbij);
        this.zzbie.put("e", zzdn.zzbik);
    }
    
    private void zzc(@Nullable final File p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          110
        //     4: new             Ljava/io/FileOutputStream;
        //     7: dup            
        //     8: aload_1        
        //     9: iconst_1       
        //    10: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;Z)V
        //    13: astore_3       
        //    14: aload_3        
        //    15: aload_2        
        //    16: invokevirtual   java/lang/String.getBytes:()[B
        //    19: invokevirtual   java/io/FileOutputStream.write:([B)V
        //    22: aload_3        
        //    23: bipush          10
        //    25: invokevirtual   java/io/FileOutputStream.write:(I)V
        //    28: aload_3        
        //    29: ifnull          36
        //    32: aload_3        
        //    33: invokevirtual   java/io/FileOutputStream.close:()V
        //    36: return         
        //    37: astore          8
        //    39: ldc             "CsiReporter: Cannot close file: sdk_csi_data.txt."
        //    41: aload           8
        //    43: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    46: goto            36
        //    49: astore          4
        //    51: aconst_null    
        //    52: astore_3       
        //    53: ldc             "CsiReporter: Cannot write to file: sdk_csi_data.txt."
        //    55: aload           4
        //    57: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    60: aload_3        
        //    61: ifnull          36
        //    64: aload_3        
        //    65: invokevirtual   java/io/FileOutputStream.close:()V
        //    68: goto            36
        //    71: astore          7
        //    73: ldc             "CsiReporter: Cannot close file: sdk_csi_data.txt."
        //    75: aload           7
        //    77: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    80: goto            36
        //    83: astore          5
        //    85: aconst_null    
        //    86: astore_3       
        //    87: aload_3        
        //    88: ifnull          95
        //    91: aload_3        
        //    92: invokevirtual   java/io/FileOutputStream.close:()V
        //    95: aload           5
        //    97: athrow         
        //    98: astore          6
        //   100: ldc             "CsiReporter: Cannot close file: sdk_csi_data.txt."
        //   102: aload           6
        //   104: invokestatic    com/google/android/gms/internal/zzkn.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   107: goto            95
        //   110: ldc             "CsiReporter: File doesn't exists. Cannot write CSI data to file."
        //   112: invokestatic    com/google/android/gms/internal/zzkn.zzdf:(Ljava/lang/String;)V
        //   115: goto            36
        //   118: astore          5
        //   120: goto            87
        //   123: astore          4
        //   125: goto            53
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      14     49     53     Ljava/io/IOException;
        //  4      14     83     87     Any
        //  14     28     123    128    Ljava/io/IOException;
        //  14     28     118    123    Any
        //  32     36     37     49     Ljava/io/IOException;
        //  53     60     118    123    Any
        //  64     68     71     83     Ljava/io/IOException;
        //  91     95     98     110    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    private void zzc(final Map<String, String> map, final String s) {
        final String zza = this.zza(this.zzbhz, map, s);
        if (this.zzbif.get()) {
            this.zzc(this.zzbig, zza);
        }
        else {
            zzu.zzfz().zzc(this.mContext, this.zzati, zza);
        }
    }
    
    private void zzkw() {
        try {
            while (true) {
                final zzdq zzdq = this.zzbib.take();
                final String zzlc = zzdq.zzlc();
                if (!TextUtils.isEmpty((CharSequence)zzlc)) {
                    this.zzc(this.zza(this.zzbid, zzdq.zzm()), zzlc);
                }
            }
        }
        catch (InterruptedException ex) {
            zzb.zzd("CsiReporter:reporter interrupted", ex);
        }
    }
    
    String zza(final String s, final Map<String, String> map, @NonNull final String s2) {
        final Uri$Builder buildUpon = Uri.parse(s).buildUpon();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            buildUpon.appendQueryParameter((String)entry.getKey(), (String)entry.getValue());
        }
        final StringBuilder sb = new StringBuilder(buildUpon.build().toString());
        sb.append("&").append("it").append("=").append(s2);
        return sb.toString();
    }
    
    Map<String, String> zza(final Map<String, String> map, @Nullable final Map<String, String> map2) {
        final LinkedHashMap<Object, String> linkedHashMap = new LinkedHashMap<Object, String>(map);
        LinkedHashMap<String, String> linkedHashMap2;
        if (map2 == null) {
            linkedHashMap2 = (LinkedHashMap<String, String>)linkedHashMap;
        }
        else {
            for (final Map.Entry<String, String> entry : map2.entrySet()) {
                final String s = entry.getKey();
                linkedHashMap.put(s, this.zzat(s).zzg(linkedHashMap.get(s), entry.getValue()));
            }
            linkedHashMap2 = (LinkedHashMap<String, String>)linkedHashMap;
        }
        return linkedHashMap2;
    }
    
    public boolean zza(final zzdq zzdq) {
        return this.zzbib.offer(zzdq);
    }
    
    public zzdn zzat(final String s) {
        zzdn zzbii = this.zzbie.get(s);
        if (zzbii == null) {
            zzbii = zzdn.zzbii;
        }
        return zzbii;
    }
    
    public void zzc(@Nullable final List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzbid.put("e", TextUtils.join((CharSequence)",", (Iterable)list));
        }
    }
}
