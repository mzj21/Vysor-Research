// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import android.view.ViewParent;
import android.graphics.Matrix;
import android.animation.ValueAnimator;
import android.view.View;

class ViewCompatHC
{
    public static int combineMeasuredStates(final int n, final int n2) {
        return View.combineMeasuredStates(n, n2);
    }
    
    public static float getAlpha(final View view) {
        return view.getAlpha();
    }
    
    static long getFrameTime() {
        return ValueAnimator.getFrameDelay();
    }
    
    public static int getLayerType(final View view) {
        return view.getLayerType();
    }
    
    public static Matrix getMatrix(final View view) {
        return view.getMatrix();
    }
    
    public static int getMeasuredHeightAndState(final View view) {
        return view.getMeasuredHeightAndState();
    }
    
    public static int getMeasuredState(final View view) {
        return view.getMeasuredState();
    }
    
    public static int getMeasuredWidthAndState(final View view) {
        return view.getMeasuredWidthAndState();
    }
    
    public static float getPivotX(final View view) {
        return view.getPivotX();
    }
    
    public static float getPivotY(final View view) {
        return view.getPivotY();
    }
    
    public static float getRotation(final View view) {
        return view.getRotation();
    }
    
    public static float getRotationX(final View view) {
        return view.getRotationX();
    }
    
    public static float getRotationY(final View view) {
        return view.getRotationY();
    }
    
    public static float getScaleX(final View view) {
        return view.getScaleX();
    }
    
    public static float getScaleY(final View view) {
        return view.getScaleY();
    }
    
    public static float getTranslationX(final View view) {
        return view.getTranslationX();
    }
    
    public static float getTranslationY(final View view) {
        return view.getTranslationY();
    }
    
    public static float getX(final View view) {
        return view.getX();
    }
    
    public static float getY(final View view) {
        return view.getY();
    }
    
    public static void jumpDrawablesToCurrentState(final View view) {
        view.jumpDrawablesToCurrentState();
    }
    
    static void offsetLeftAndRight(final View view, final int n) {
        view.offsetLeftAndRight(n);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            final ViewParent parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View)parent);
            }
        }
    }
    
    static void offsetTopAndBottom(final View view, final int n) {
        view.offsetTopAndBottom(n);
        if (view.getVisibility() == 0) {
            tickleInvalidationFlag(view);
            final ViewParent parent = view.getParent();
            if (parent instanceof View) {
                tickleInvalidationFlag((View)parent);
            }
        }
    }
    
    public static int resolveSizeAndState(final int n, final int n2, final int n3) {
        return View.resolveSizeAndState(n, n2, n3);
    }
    
    public static void setActivated(final View view, final boolean activated) {
        view.setActivated(activated);
    }
    
    public static void setAlpha(final View view, final float alpha) {
        view.setAlpha(alpha);
    }
    
    public static void setLayerType(final View view, final int n, final Paint paint) {
        view.setLayerType(n, paint);
    }
    
    public static void setPivotX(final View view, final float pivotX) {
        view.setPivotX(pivotX);
    }
    
    public static void setPivotY(final View view, final float pivotY) {
        view.setPivotY(pivotY);
    }
    
    public static void setRotation(final View view, final float rotation) {
        view.setRotation(rotation);
    }
    
    public static void setRotationX(final View view, final float rotationX) {
        view.setRotationX(rotationX);
    }
    
    public static void setRotationY(final View view, final float rotationY) {
        view.setRotationY(rotationY);
    }
    
    public static void setSaveFromParentEnabled(final View view, final boolean saveFromParentEnabled) {
        view.setSaveFromParentEnabled(saveFromParentEnabled);
    }
    
    public static void setScaleX(final View view, final float scaleX) {
        view.setScaleX(scaleX);
    }
    
    public static void setScaleY(final View view, final float scaleY) {
        view.setScaleY(scaleY);
    }
    
    public static void setTranslationX(final View view, final float translationX) {
        view.setTranslationX(translationX);
    }
    
    public static void setTranslationY(final View view, final float translationY) {
        view.setTranslationY(translationY);
    }
    
    public static void setX(final View view, final float x) {
        view.setX(x);
    }
    
    public static void setY(final View view, final float y) {
        view.setY(y);
    }
    
    private static void tickleInvalidationFlag(final View view) {
        final float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }
}
