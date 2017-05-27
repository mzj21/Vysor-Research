// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.view.View;

class ViewPropertyAnimatorCompatKK
{
    public static void setUpdateListener(final View view, final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        Object updateListener = null;
        if (viewPropertyAnimatorUpdateListener != null) {
            updateListener = new ValueAnimator$AnimatorUpdateListener() {
                public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                    viewPropertyAnimatorUpdateListener.onAnimationUpdate(view);
                }
            };
        }
        view.animate().setUpdateListener((ValueAnimator$AnimatorUpdateListener)updateListener);
    }
}
