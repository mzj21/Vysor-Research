// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.security.MessageDigest;

@zziy
public abstract class zzcv
{
    @Nullable
    private static MessageDigest zzavf;
    protected Object zzakd;
    
    static {
        zzcv.zzavf = null;
    }
    
    public zzcv() {
        this.zzakd = new Object();
    }
    
    abstract byte[] zzac(final String p0);
    
    @Nullable
    protected MessageDigest zzir() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/internal/zzcv.zzakd:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: getstatic       com/google/android/gms/internal/zzcv.zzavf:Ljava/security/MessageDigest;
        //    10: ifnull          23
        //    13: getstatic       com/google/android/gms/internal/zzcv.zzavf:Ljava/security/MessageDigest;
        //    16: astore          4
        //    18: aload_1        
        //    19: monitorexit    
        //    20: aload           4
        //    22: areturn        
        //    23: iconst_0       
        //    24: istore_3       
        //    25: iload_3        
        //    26: iconst_2       
        //    27: if_icmpge       44
        //    30: ldc             "MD5"
        //    32: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //    35: putstatic       com/google/android/gms/internal/zzcv.zzavf:Ljava/security/MessageDigest;
        //    38: iinc            3, 1
        //    41: goto            25
        //    44: getstatic       com/google/android/gms/internal/zzcv.zzavf:Ljava/security/MessageDigest;
        //    47: astore          4
        //    49: aload_1        
        //    50: monitorexit    
        //    51: goto            20
        //    54: astore_2       
        //    55: aload_1        
        //    56: monitorexit    
        //    57: aload_2        
        //    58: athrow         
        //    59: astore          5
        //    61: goto            38
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  7      20     54     59     Any
        //  30     38     59     64     Ljava/security/NoSuchAlgorithmException;
        //  30     38     54     59     Any
        //  44     57     54     59     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
}
