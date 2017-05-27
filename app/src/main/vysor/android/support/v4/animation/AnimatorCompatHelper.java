// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.animation;

import android.view.View;
import android.os.Build$VERSION;

public final class AnimatorCompatHelper
{
    private static final AnimatorProvider IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 12) {
            IMPL = new HoneycombMr1AnimatorCompatProvider();
        }
        else {
            IMPL = new GingerbreadAnimatorCompatProvider();
        }
    }
    
    public static void clearInterpolator(final View view) {
        AnimatorCompatHelper.IMPL.clearInterpolator(view);
    }
    
    public static ValueAnimatorCompat emptyValueAnimator() {
        return AnimatorCompatHelper.IMPL.emptyValueAnimator();
    }
}
