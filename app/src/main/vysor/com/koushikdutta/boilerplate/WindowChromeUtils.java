// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate;

import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.app.Activity;
import android.animation.ValueAnimator;
import android.content.Context;
import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.view.Window;

public final class WindowChromeUtils
{
    public static int getStatusBarColor(final Window window) {
        int statusBarColorLollipop;
        if (Build$VERSION.SDK_INT < 21) {
            statusBarColorLollipop = 0;
        }
        else {
            statusBarColorLollipop = getStatusBarColorLollipop(window);
        }
        return statusBarColorLollipop;
    }
    
    @TargetApi(21)
    private static int getStatusBarColorLollipop(final Window window) {
        return window.getStatusBarColor();
    }
    
    public static ValueAnimator navigationBarFadeToColor(final Context context, final ValueAnimator valueAnimator, final int n) {
        ValueAnimator navigationBarFadeToColorLollipop;
        if (Build$VERSION.SDK_INT < 21) {
            navigationBarFadeToColorLollipop = null;
        }
        else {
            navigationBarFadeToColorLollipop = navigationBarFadeToColorLollipop(context, valueAnimator, n);
        }
        return navigationBarFadeToColorLollipop;
    }
    
    @TargetApi(21)
    private static ValueAnimator navigationBarFadeToColorLollipop(final Context context, final ValueAnimator valueAnimator, final int n) {
        Label_0032: {
            if (valueAnimator == null) {
                break Label_0032;
            }
            valueAnimator.end();
            if ((int)valueAnimator.getAnimatedValue() != n) {
                valueAnimator.cancel();
                break Label_0032;
            }
            return valueAnimator;
        }
        final Window window = ((Activity)context).getWindow();
        final ValueAnimator ofArgb = ValueAnimator.ofArgb(new int[] { window.getNavigationBarColor(), n });
        ofArgb.setDuration((long)context.getResources().getInteger(17694722));
        ofArgb.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                window.setNavigationBarColor((int)valueAnimator.getAnimatedValue());
            }
        });
        ofArgb.start();
        return ofArgb;
    }
    
    public static void setStatusBarColor(final Window window, final int n) {
        if (Build$VERSION.SDK_INT >= 21) {
            setStatusBarColorLollipop(window, n);
        }
    }
    
    @TargetApi(21)
    private static void setStatusBarColorLollipop(final Window window, final int statusBarColor) {
        window.setStatusBarColor(statusBarColor);
    }
    
    public static ValueAnimator statusBarFadeToColor(final Context context, final ValueAnimator valueAnimator, final int n) {
        ValueAnimator statusBarFadeToColorLollipop;
        if (Build$VERSION.SDK_INT < 21) {
            statusBarFadeToColorLollipop = null;
        }
        else {
            statusBarFadeToColorLollipop = statusBarFadeToColorLollipop(context, valueAnimator, n);
        }
        return statusBarFadeToColorLollipop;
    }
    
    @TargetApi(21)
    private static ValueAnimator statusBarFadeToColorLollipop(final Context context, final ValueAnimator valueAnimator, final int n) {
        Label_0030: {
            if (valueAnimator == null) {
                break Label_0030;
            }
            valueAnimator.end();
            if ((int)valueAnimator.getAnimatedValue() != n) {
                valueAnimator.cancel();
                break Label_0030;
            }
            return valueAnimator;
        }
        if (!(context instanceof Activity)) {
            return null;
        }
        final Window window = ((Activity)context).getWindow();
        final ValueAnimator ofArgb = ValueAnimator.ofArgb(new int[] { window.getStatusBarColor(), n });
        ofArgb.setDuration((long)context.getResources().getInteger(17694722));
        ofArgb.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                window.setStatusBarColor((int)valueAnimator.getAnimatedValue());
            }
        });
        ofArgb.start();
        return ofArgb;
    }
}
