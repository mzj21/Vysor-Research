// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.os.Bundle;
import android.view.ViewParent;
import android.view.View;

class ViewCompatJB
{
    public static Object getAccessibilityNodeProvider(final View view) {
        return view.getAccessibilityNodeProvider();
    }
    
    public static boolean getFitsSystemWindows(final View view) {
        return view.getFitsSystemWindows();
    }
    
    public static int getImportantForAccessibility(final View view) {
        return view.getImportantForAccessibility();
    }
    
    public static int getMinimumHeight(final View view) {
        return view.getMinimumHeight();
    }
    
    public static int getMinimumWidth(final View view) {
        return view.getMinimumWidth();
    }
    
    public static ViewParent getParentForAccessibility(final View view) {
        return view.getParentForAccessibility();
    }
    
    public static boolean hasOverlappingRendering(final View view) {
        return view.hasOverlappingRendering();
    }
    
    public static boolean hasTransientState(final View view) {
        return view.hasTransientState();
    }
    
    public static boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return view.performAccessibilityAction(n, bundle);
    }
    
    public static void postInvalidateOnAnimation(final View view) {
        view.postInvalidateOnAnimation();
    }
    
    public static void postInvalidateOnAnimation(final View view, final int n, final int n2, final int n3, final int n4) {
        view.postInvalidate(n, n2, n3, n4);
    }
    
    public static void postOnAnimation(final View view, final Runnable runnable) {
        view.postOnAnimation(runnable);
    }
    
    public static void postOnAnimationDelayed(final View view, final Runnable runnable, final long n) {
        view.postOnAnimationDelayed(runnable, n);
    }
    
    public static void requestApplyInsets(final View view) {
        view.requestFitSystemWindows();
    }
    
    public static void setHasTransientState(final View view, final boolean hasTransientState) {
        view.setHasTransientState(hasTransientState);
    }
    
    public static void setImportantForAccessibility(final View view, final int importantForAccessibility) {
        view.setImportantForAccessibility(importantForAccessibility);
    }
}
