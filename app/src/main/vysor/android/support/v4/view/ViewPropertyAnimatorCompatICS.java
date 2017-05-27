// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;
import android.view.View;

class ViewPropertyAnimatorCompatICS
{
    public static void alpha(final View view, final float n) {
        view.animate().alpha(n);
    }
    
    public static void alphaBy(final View view, final float n) {
        view.animate().alphaBy(n);
    }
    
    public static void cancel(final View view) {
        view.animate().cancel();
    }
    
    public static long getDuration(final View view) {
        return view.animate().getDuration();
    }
    
    public static long getStartDelay(final View view) {
        return view.animate().getStartDelay();
    }
    
    public static void rotation(final View view, final float n) {
        view.animate().rotation(n);
    }
    
    public static void rotationBy(final View view, final float n) {
        view.animate().rotationBy(n);
    }
    
    public static void rotationX(final View view, final float n) {
        view.animate().rotationX(n);
    }
    
    public static void rotationXBy(final View view, final float n) {
        view.animate().rotationXBy(n);
    }
    
    public static void rotationY(final View view, final float n) {
        view.animate().rotationY(n);
    }
    
    public static void rotationYBy(final View view, final float n) {
        view.animate().rotationYBy(n);
    }
    
    public static void scaleX(final View view, final float n) {
        view.animate().scaleX(n);
    }
    
    public static void scaleXBy(final View view, final float n) {
        view.animate().scaleXBy(n);
    }
    
    public static void scaleY(final View view, final float n) {
        view.animate().scaleY(n);
    }
    
    public static void scaleYBy(final View view, final float n) {
        view.animate().scaleYBy(n);
    }
    
    public static void setDuration(final View view, final long duration) {
        view.animate().setDuration(duration);
    }
    
    public static void setInterpolator(final View view, final Interpolator interpolator) {
        view.animate().setInterpolator((TimeInterpolator)interpolator);
    }
    
    public static void setListener(final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
                public void onAnimationCancel(final Animator animator) {
                    viewPropertyAnimatorListener.onAnimationCancel(view);
                }
                
                public void onAnimationEnd(final Animator animator) {
                    viewPropertyAnimatorListener.onAnimationEnd(view);
                }
                
                public void onAnimationStart(final Animator animator) {
                    viewPropertyAnimatorListener.onAnimationStart(view);
                }
            });
        }
        else {
            view.animate().setListener((Animator$AnimatorListener)null);
        }
    }
    
    public static void setStartDelay(final View view, final long startDelay) {
        view.animate().setStartDelay(startDelay);
    }
    
    public static void start(final View view) {
        view.animate().start();
    }
    
    public static void translationX(final View view, final float n) {
        view.animate().translationX(n);
    }
    
    public static void translationXBy(final View view, final float n) {
        view.animate().translationXBy(n);
    }
    
    public static void translationY(final View view, final float n) {
        view.animate().translationY(n);
    }
    
    public static void translationYBy(final View view, final float n) {
        view.animate().translationYBy(n);
    }
    
    public static void x(final View view, final float n) {
        view.animate().x(n);
    }
    
    public static void xBy(final View view, final float n) {
        view.animate().xBy(n);
    }
    
    public static void y(final View view, final float n) {
        view.animate().y(n);
    }
    
    public static void yBy(final View view, final float n) {
        view.animate().yBy(n);
    }
}
