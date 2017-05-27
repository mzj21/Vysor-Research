// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.view.LayoutInflater$Factory;
import android.view.LayoutInflater$Factory2;
import android.view.LayoutInflater;
import java.lang.reflect.Field;

class LayoutInflaterCompatHC
{
    private static final String TAG = "LayoutInflaterCompatHC";
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;
    
    static void forceSetFactory2(final LayoutInflater p0, final LayoutInflater$Factory2 p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/view/LayoutInflaterCompatHC.sCheckedField:Z
        //     3: ifne            27
        //     6: ldc             Landroid/view/LayoutInflater;.class
        //     8: ldc             "mFactory2"
        //    10: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    13: putstatic       android/support/v4/view/LayoutInflaterCompatHC.sLayoutInflaterFactory2Field:Ljava/lang/reflect/Field;
        //    16: getstatic       android/support/v4/view/LayoutInflaterCompatHC.sLayoutInflaterFactory2Field:Ljava/lang/reflect/Field;
        //    19: iconst_1       
        //    20: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    23: iconst_1       
        //    24: putstatic       android/support/v4/view/LayoutInflaterCompatHC.sCheckedField:Z
        //    27: getstatic       android/support/v4/view/LayoutInflaterCompatHC.sLayoutInflaterFactory2Field:Ljava/lang/reflect/Field;
        //    30: ifnull          41
        //    33: getstatic       android/support/v4/view/LayoutInflaterCompatHC.sLayoutInflaterFactory2Field:Ljava/lang/reflect/Field;
        //    36: aload_0        
        //    37: aload_1        
        //    38: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    41: return         
        //    42: astore          4
        //    44: ldc             "LayoutInflaterCompatHC"
        //    46: new             Ljava/lang/StringBuilder;
        //    49: dup            
        //    50: invokespecial   java/lang/StringBuilder.<init>:()V
        //    53: ldc             "forceSetFactory2 Could not find field 'mFactory2' on class "
        //    55: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    58: ldc             Landroid/view/LayoutInflater;.class
        //    60: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    63: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    66: ldc             "; inflation may have unexpected results."
        //    68: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    71: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    74: aload           4
        //    76: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    79: pop            
        //    80: goto            23
        //    83: astore_2       
        //    84: ldc             "LayoutInflaterCompatHC"
        //    86: new             Ljava/lang/StringBuilder;
        //    89: dup            
        //    90: invokespecial   java/lang/StringBuilder.<init>:()V
        //    93: ldc             "forceSetFactory2 could not set the Factory2 on LayoutInflater "
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    98: aload_0        
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   102: ldc             "; inflation may have unexpected results."
        //   104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   107: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   110: aload_2        
        //   111: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   114: pop            
        //   115: goto            41
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      23     42     83     Ljava/lang/NoSuchFieldException;
        //  33     41     83     118    Ljava/lang/IllegalAccessException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    
    static void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
        Object factory2;
        if (layoutInflaterFactory != null) {
            factory2 = new FactoryWrapperHC(layoutInflaterFactory);
        }
        else {
            factory2 = null;
        }
        layoutInflater.setFactory2((LayoutInflater$Factory2)factory2);
        final LayoutInflater$Factory factory3 = layoutInflater.getFactory();
        if (factory3 instanceof LayoutInflater$Factory2) {
            forceSetFactory2(layoutInflater, (LayoutInflater$Factory2)factory3);
        }
        else {
            forceSetFactory2(layoutInflater, (LayoutInflater$Factory2)factory2);
        }
    }
    
    static class FactoryWrapperHC extends FactoryWrapper implements LayoutInflater$Factory2
    {
        FactoryWrapperHC(final LayoutInflaterFactory layoutInflaterFactory) {
            super(layoutInflaterFactory);
        }
        
        public View onCreateView(final View view, final String s, final Context context, final AttributeSet set) {
            return this.mDelegateFactory.onCreateView(view, s, context, set);
        }
    }
}
