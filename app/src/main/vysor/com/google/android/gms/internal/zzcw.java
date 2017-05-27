// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.OutputStream;
import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.ArrayList;

@zziy
public class zzcw
{
    private final int zzavg;
    private final int zzavh;
    private final int zzavi;
    private final zzcv zzavj;
    
    public zzcw(final int zzavh) {
        this.zzavj = new zzcy();
        this.zzavh = zzavh;
        this.zzavg = 6;
        this.zzavi = 0;
    }
    
    public String zza(final ArrayList<String> list) {
        final StringBuffer sb = new StringBuffer();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toLowerCase(Locale.US));
            sb.append('\n');
        }
        return this.zzad(sb.toString());
    }
    
    String zzad(final String s) {
        final String[] split = s.split("\n");
        String string;
        if (split.length == 0) {
            string = "";
        }
        else {
            final zza zzis = this.zzis();
            final PriorityQueue<zzcz.zza> priorityQueue = new PriorityQueue<zzcz.zza>(this.zzavh, (Comparator<? super zzcz.zza>)new Comparator<zzcz.zza>() {
                public int zza(final zzcz.zza zza, final zzcz.zza zza2) {
                    int n = zza.zzavp - zza2.zzavp;
                    if (n == 0) {
                        n = (int)(zza.value - zza2.value);
                    }
                    return n;
                }
            });
            for (int i = 0; i < split.length; ++i) {
                final String[] zzaf = zzcx.zzaf(split[i]);
                if (zzaf.length != 0) {
                    zzcz.zza(zzaf, this.zzavh, this.zzavg, priorityQueue);
                }
            }
            for (final zzcz.zza zza : priorityQueue) {
                try {
                    zzis.write(this.zzavj.zzac(zza.zzavo));
                    continue;
                }
                catch (IOException ex) {
                    zzb.zzb("Error while writing hash to byteStream", ex);
                }
                break;
            }
            string = zzis.toString();
        }
        return string;
    }
    
    zza zzis() {
        return new zza();
    }
    
    static class zza
    {
        ByteArrayOutputStream zzavl;
        Base64OutputStream zzavm;
        
        public zza() {
            this.zzavl = new ByteArrayOutputStream(4096);
            this.zzavm = new Base64OutputStream((OutputStream)this.zzavl, 10);
        }
        
        @Override
        public String toString() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/google/android/gms/internal/zzcw$zza.zzavm:Landroid/util/Base64OutputStream;
            //     4: invokevirtual   android/util/Base64OutputStream.close:()V
            //     7: aload_0        
            //     8: getfield        com/google/android/gms/internal/zzcw$zza.zzavl:Ljava/io/ByteArrayOutputStream;
            //    11: invokevirtual   java/io/ByteArrayOutputStream.close:()V
            //    14: aload_0        
            //    15: getfield        com/google/android/gms/internal/zzcw$zza.zzavl:Ljava/io/ByteArrayOutputStream;
            //    18: invokevirtual   java/io/ByteArrayOutputStream.toString:()Ljava/lang/String;
            //    21: astore          5
            //    23: aload           5
            //    25: astore          4
            //    27: aload_0        
            //    28: aconst_null    
            //    29: putfield        com/google/android/gms/internal/zzcw$zza.zzavl:Ljava/io/ByteArrayOutputStream;
            //    32: aload_0        
            //    33: aconst_null    
            //    34: putfield        com/google/android/gms/internal/zzcw$zza.zzavm:Landroid/util/Base64OutputStream;
            //    37: aload           4
            //    39: areturn        
            //    40: astore_1       
            //    41: ldc             "HashManager: Unable to convert to Base64."
            //    43: aload_1        
            //    44: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
            //    47: goto            7
            //    50: astore_3       
            //    51: ldc             "HashManager: Unable to convert to Base64."
            //    53: aload_3        
            //    54: invokestatic    com/google/android/gms/internal/zzkn.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
            //    57: ldc             ""
            //    59: astore          4
            //    61: aload_0        
            //    62: aconst_null    
            //    63: putfield        com/google/android/gms/internal/zzcw$zza.zzavl:Ljava/io/ByteArrayOutputStream;
            //    66: aload_0        
            //    67: aconst_null    
            //    68: putfield        com/google/android/gms/internal/zzcw$zza.zzavm:Landroid/util/Base64OutputStream;
            //    71: goto            37
            //    74: astore_2       
            //    75: aload_0        
            //    76: aconst_null    
            //    77: putfield        com/google/android/gms/internal/zzcw$zza.zzavl:Ljava/io/ByteArrayOutputStream;
            //    80: aload_0        
            //    81: aconst_null    
            //    82: putfield        com/google/android/gms/internal/zzcw$zza.zzavm:Landroid/util/Base64OutputStream;
            //    85: aload_2        
            //    86: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  0      7      40     50     Ljava/io/IOException;
            //  7      23     50     74     Ljava/io/IOException;
            //  7      23     74     87     Any
            //  51     61     74     87     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0007:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public void write(final byte[] array) throws IOException {
            this.zzavm.write(array);
        }
    }
}
