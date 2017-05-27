// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import android.os.Process;
import android.os.Binder;

public class zzt
{
    private static String EY;
    
    static {
        zzt.EY = null;
    }
    
    public static String zzaxx() {
        return zzhl(Binder.getCallingPid());
    }
    
    public static String zzaxy() {
        if (zzt.EY == null) {
            zzt.EY = zzhl(Process.myPid());
        }
        return zzt.EY;
    }
    
    private static String zzhl(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/BufferedReader;
        //     3: dup            
        //     4: new             Ljava/io/FileReader;
        //     7: dup            
        //     8: new             Ljava/lang/StringBuilder;
        //    11: dup            
        //    12: bipush          25
        //    14: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //    17: ldc             "/proc/"
        //    19: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    22: iload_0        
        //    23: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    26: ldc             "/cmdline"
        //    28: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    31: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    34: invokespecial   java/io/FileReader.<init>:(Ljava/lang/String;)V
        //    37: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    40: astore_1       
        //    41: aload_1        
        //    42: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    45: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    48: astore          10
        //    50: aload           10
        //    52: astore          7
        //    54: aload_1        
        //    55: ifnull          62
        //    58: aload_1        
        //    59: invokevirtual   java/io/BufferedReader.close:()V
        //    62: aload           7
        //    64: areturn        
        //    65: astore          11
        //    67: ldc             "ProcessUtils"
        //    69: aload           11
        //    71: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //    74: aload           11
        //    76: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    79: pop            
        //    80: goto            62
        //    83: astore_2       
        //    84: aconst_null    
        //    85: astore_1       
        //    86: ldc             "ProcessUtils"
        //    88: aload_2        
        //    89: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //    92: aload_2        
        //    93: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    96: pop            
        //    97: aconst_null    
        //    98: astore          7
        //   100: aload_1        
        //   101: ifnull          62
        //   104: aload_1        
        //   105: invokevirtual   java/io/BufferedReader.close:()V
        //   108: aconst_null    
        //   109: astore          7
        //   111: goto            62
        //   114: astore          8
        //   116: ldc             "ProcessUtils"
        //   118: aload           8
        //   120: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   123: aload           8
        //   125: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   128: pop            
        //   129: aconst_null    
        //   130: astore          7
        //   132: goto            62
        //   135: astore          13
        //   137: aconst_null    
        //   138: astore_1       
        //   139: aload           13
        //   141: astore_3       
        //   142: aload_1        
        //   143: ifnull          150
        //   146: aload_1        
        //   147: invokevirtual   java/io/BufferedReader.close:()V
        //   150: aload_3        
        //   151: athrow         
        //   152: astore          4
        //   154: ldc             "ProcessUtils"
        //   156: aload           4
        //   158: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   161: aload           4
        //   163: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   166: pop            
        //   167: goto            150
        //   170: astore_3       
        //   171: goto            142
        //   174: astore_2       
        //   175: goto            86
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      41     83     86     Ljava/io/IOException;
        //  0      41     135    142    Any
        //  41     50     174    178    Ljava/io/IOException;
        //  41     50     170    174    Any
        //  58     62     65     83     Ljava/lang/Exception;
        //  86     97     170    174    Any
        //  104    108    114    135    Ljava/lang/Exception;
        //  146    150    152    170    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
