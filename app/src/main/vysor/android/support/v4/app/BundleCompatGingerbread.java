// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import android.os.IBinder;
import android.os.Bundle;
import java.lang.reflect.Method;

class BundleCompatGingerbread
{
    private static final String TAG = "BundleCompatGingerbread";
    private static Method sGetIBinderMethod;
    private static boolean sGetIBinderMethodFetched;
    private static Method sPutIBinderMethod;
    private static boolean sPutIBinderMethodFetched;
    
    public static IBinder getBinder(final Bundle bundle, final String s) {
        Label_0036: {
            if (BundleCompatGingerbread.sGetIBinderMethodFetched) {
                break Label_0036;
            }
            while (true) {
                try {
                    (BundleCompatGingerbread.sGetIBinderMethod = Bundle.class.getMethod("getIBinder", String.class)).setAccessible(true);
                    BundleCompatGingerbread.sGetIBinderMethodFetched = true;
                    if (BundleCompatGingerbread.sGetIBinderMethod != null) {
                        final Method method = BundleCompatGingerbread.sGetIBinderMethod;
                        final Bundle bundle2 = bundle;
                        final int n = 1;
                        final Object[] array = new Object[n];
                        final int n2 = 0;
                        final String s2 = s;
                        array[n2] = s2;
                        final Object o = method.invoke(bundle2, array);
                        final IBinder binder = (IBinder)o;
                        return binder;
                    }
                    goto Label_0092;
                }
                catch (NoSuchMethodException ex) {
                    Log.i("BundleCompatGingerbread", "Failed to retrieve getIBinder method", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
        try {
            final Method method = BundleCompatGingerbread.sGetIBinderMethod;
            final Bundle bundle2 = bundle;
            final int n = 1;
            final Object[] array = new Object[n];
            final int n2 = 0;
            final String s2 = s;
            array[n2] = s2;
            final Object o = method.invoke(bundle2, array);
            final IBinder binder2;
            final IBinder binder = binder2 = (IBinder)o;
            return binder2;
        }
        catch (IllegalAccessException ex2) {}
        catch (IllegalArgumentException ex3) {
            goto Label_0079;
        }
        catch (InvocationTargetException ex3) {
            goto Label_0079;
        }
    }
    
    public static void putBinder(final Bundle p0, final String p1, final IBinder p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/app/BundleCompatGingerbread.sPutIBinderMethodFetched:Z
        //     3: ifne            41
        //     6: ldc             Landroid/os/Bundle;.class
        //     8: ldc             "putIBinder"
        //    10: iconst_2       
        //    11: anewarray       Ljava/lang/Class;
        //    14: dup            
        //    15: iconst_0       
        //    16: ldc             Ljava/lang/String;.class
        //    18: aastore        
        //    19: dup            
        //    20: iconst_1       
        //    21: ldc             Landroid/os/IBinder;.class
        //    23: aastore        
        //    24: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    27: putstatic       android/support/v4/app/BundleCompatGingerbread.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    30: getstatic       android/support/v4/app/BundleCompatGingerbread.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    33: iconst_1       
        //    34: invokevirtual   java/lang/reflect/Method.setAccessible:(Z)V
        //    37: iconst_1       
        //    38: putstatic       android/support/v4/app/BundleCompatGingerbread.sPutIBinderMethodFetched:Z
        //    41: getstatic       android/support/v4/app/BundleCompatGingerbread.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    44: ifnull          67
        //    47: getstatic       android/support/v4/app/BundleCompatGingerbread.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    50: aload_0        
        //    51: iconst_2       
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: iconst_0       
        //    57: aload_1        
        //    58: aastore        
        //    59: dup            
        //    60: iconst_1       
        //    61: aload_2        
        //    62: aastore        
        //    63: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    66: pop            
        //    67: return         
        //    68: astore          6
        //    70: ldc             "BundleCompatGingerbread"
        //    72: ldc             "Failed to retrieve putIBinder method"
        //    74: aload           6
        //    76: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    79: pop            
        //    80: goto            37
        //    83: astore_3       
        //    84: ldc             "BundleCompatGingerbread"
        //    86: ldc             "Failed to invoke putIBinder via reflection"
        //    88: aload_3        
        //    89: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    92: pop            
        //    93: aconst_null    
        //    94: putstatic       android/support/v4/app/BundleCompatGingerbread.sPutIBinderMethod:Ljava/lang/reflect/Method;
        //    97: goto            67
        //   100: astore_3       
        //   101: goto            84
        //   104: astore_3       
        //   105: goto            84
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  6      37     68     83     Ljava/lang/NoSuchMethodException;
        //  47     67     104    108    Ljava/lang/reflect/InvocationTargetException;
        //  47     67     83     84     Ljava/lang/IllegalAccessException;
        //  47     67     100    104    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
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
