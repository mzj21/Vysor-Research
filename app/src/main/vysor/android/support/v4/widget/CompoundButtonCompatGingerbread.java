// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

class CompoundButtonCompatGingerbread
{
    private static final String TAG = "CompoundButtonCompatGingerbread";
    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;
    
    static Drawable getButtonDrawable(final CompoundButton p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/widget/CompoundButtonCompatGingerbread.sButtonDrawableFieldFetched:Z
        //     3: ifne            27
        //     6: ldc             Landroid/widget/CompoundButton;.class
        //     8: ldc             "mButtonDrawable"
        //    10: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    13: putstatic       android/support/v4/widget/CompoundButtonCompatGingerbread.sButtonDrawableField:Ljava/lang/reflect/Field;
        //    16: getstatic       android/support/v4/widget/CompoundButtonCompatGingerbread.sButtonDrawableField:Ljava/lang/reflect/Field;
        //    19: iconst_1       
        //    20: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    23: iconst_1       
        //    24: putstatic       android/support/v4/widget/CompoundButtonCompatGingerbread.sButtonDrawableFieldFetched:Z
        //    27: getstatic       android/support/v4/widget/CompoundButtonCompatGingerbread.sButtonDrawableField:Ljava/lang/reflect/Field;
        //    30: ifnull          75
        //    33: getstatic       android/support/v4/widget/CompoundButtonCompatGingerbread.sButtonDrawableField:Ljava/lang/reflect/Field;
        //    36: aload_0        
        //    37: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    40: checkcast       Landroid/graphics/drawable/Drawable;
        //    43: astore_1       
        //    44: aload_1        
        //    45: areturn        
        //    46: astore          4
        //    48: ldc             "CompoundButtonCompatGingerbread"
        //    50: ldc             "Failed to retrieve mButtonDrawable field"
        //    52: aload           4
        //    54: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    57: pop            
        //    58: goto            23
        //    61: astore_2       
        //    62: ldc             "CompoundButtonCompatGingerbread"
        //    64: ldc             "Failed to get button drawable via reflection"
        //    66: aload_2        
        //    67: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    70: pop            
        //    71: aconst_null    
        //    72: putstatic       android/support/v4/widget/CompoundButtonCompatGingerbread.sButtonDrawableField:Ljava/lang/reflect/Field;
        //    75: aconst_null    
        //    76: astore_1       
        //    77: goto            44
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      23     46     61     Ljava/lang/NoSuchFieldException;
        //  33     44     61     75     Ljava/lang/IllegalAccessException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0044:
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
    
    static ColorStateList getButtonTintList(final CompoundButton compoundButton) {
        ColorStateList supportButtonTintList;
        if (compoundButton instanceof TintableCompoundButton) {
            supportButtonTintList = ((TintableCompoundButton)compoundButton).getSupportButtonTintList();
        }
        else {
            supportButtonTintList = null;
        }
        return supportButtonTintList;
    }
    
    static PorterDuff$Mode getButtonTintMode(final CompoundButton compoundButton) {
        PorterDuff$Mode supportButtonTintMode;
        if (compoundButton instanceof TintableCompoundButton) {
            supportButtonTintMode = ((TintableCompoundButton)compoundButton).getSupportButtonTintMode();
        }
        else {
            supportButtonTintMode = null;
        }
        return supportButtonTintMode;
    }
    
    static void setButtonTintList(final CompoundButton compoundButton, final ColorStateList supportButtonTintList) {
        if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton)compoundButton).setSupportButtonTintList(supportButtonTintList);
        }
    }
    
    static void setButtonTintMode(final CompoundButton compoundButton, final PorterDuff$Mode supportButtonTintMode) {
        if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton)compoundButton).setSupportButtonTintMode(supportButtonTintMode);
        }
    }
}
