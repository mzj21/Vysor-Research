// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import java.lang.reflect.Method;

class DrawableCompatJellybeanMr1
{
    private static final String TAG = "DrawableCompatJellybeanMr1";
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;
    
    public static int getLayoutDirection(final Drawable p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sGetLayoutDirectionMethodFetched:Z
        //     3: ifne            31
        //     6: ldc             Landroid/graphics/drawable/Drawable;.class
        //     8: ldc             "getLayoutDirection"
        //    10: iconst_0       
        //    11: anewarray       Ljava/lang/Class;
        //    14: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    17: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    20: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    23: iconst_1       
        //    24: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //    27: iconst_1       
        //    28: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sGetLayoutDirectionMethodFetched:Z
        //    31: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    34: ifnull          90
        //    37: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    40: aload_0        
        //    41: iconst_0       
        //    42: anewarray       Ljava/lang/Object;
        //    45: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    48: checkcast       Ljava/lang/Integer;
        //    51: invokevirtual   java/lang/Integer.intValue:()I
        //    54: istore          4
        //    56: iload           4
        //    58: istore_1       
        //    59: iload_1        
        //    60: ireturn        
        //    61: astore          5
        //    63: ldc             "DrawableCompatJellybeanMr1"
        //    65: ldc             "Failed to retrieve getLayoutDirection() method"
        //    67: aload           5
        //    69: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    72: pop            
        //    73: goto            27
        //    76: astore_2       
        //    77: ldc             "DrawableCompatJellybeanMr1"
        //    79: ldc             "Failed to invoke getLayoutDirection() via reflection"
        //    81: aload_2        
        //    82: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    85: pop            
        //    86: aconst_null    
        //    87: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sGetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    90: iconst_m1      
        //    91: istore_1       
        //    92: goto            59
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  6      27     61     76     Ljava/lang/NoSuchMethodException;
        //  37     56     76     90     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    public static boolean setLayoutDirection(final Drawable p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: istore_2       
        //     2: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethodFetched:Z
        //     5: ifne            44
        //     8: iconst_1       
        //     9: anewarray       Ljava/lang/Class;
        //    12: astore          10
        //    14: aload           10
        //    16: iconst_0       
        //    17: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //    20: aastore        
        //    21: ldc             Landroid/graphics/drawable/Drawable;.class
        //    23: ldc             "setLayoutDirection"
        //    25: aload           10
        //    27: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    30: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    33: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    36: iconst_1       
        //    37: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //    40: iload_2        
        //    41: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethodFetched:Z
        //    44: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    47: ifnull          109
        //    50: getstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //    53: astore          5
        //    55: iconst_1       
        //    56: anewarray       Ljava/lang/Object;
        //    59: astore          6
        //    61: aload           6
        //    63: iconst_0       
        //    64: iload_1        
        //    65: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    68: aastore        
        //    69: aload           5
        //    71: aload_0        
        //    72: aload           6
        //    74: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    77: pop            
        //    78: iload_2        
        //    79: ireturn        
        //    80: astore          8
        //    82: ldc             "DrawableCompatJellybeanMr1"
        //    84: ldc             "Failed to retrieve setLayoutDirection(int) method"
        //    86: aload           8
        //    88: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    91: pop            
        //    92: goto            40
        //    95: astore_3       
        //    96: ldc             "DrawableCompatJellybeanMr1"
        //    98: ldc             "Failed to invoke setLayoutDirection(int) via reflection"
        //   100: aload_3        
        //   101: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   104: pop            
        //   105: aconst_null    
        //   106: putstatic       android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.sSetLayoutDirectionMethod:Ljava/lang/reflect/Method;
        //   109: iconst_0       
        //   110: istore_2       
        //   111: goto            78
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  8      40     80     95     Ljava/lang/NoSuchMethodException;
        //  50     78     95     109    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0078:
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
