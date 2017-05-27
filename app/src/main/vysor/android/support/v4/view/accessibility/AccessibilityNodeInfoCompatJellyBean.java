// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.View;

class AccessibilityNodeInfoCompatJellyBean
{
    public static void addChild(final Object o, final View view, final int n) {
        ((AccessibilityNodeInfo)o).addChild(view, n);
    }
    
    public static Object findFocus(final Object o, final int n) {
        return ((AccessibilityNodeInfo)o).findFocus(n);
    }
    
    public static Object focusSearch(final Object o, final int n) {
        return ((AccessibilityNodeInfo)o).focusSearch(n);
    }
    
    public static int getMovementGranularities(final Object o) {
        return ((AccessibilityNodeInfo)o).getMovementGranularities();
    }
    
    public static boolean isAccessibilityFocused(final Object o) {
        return ((AccessibilityNodeInfo)o).isAccessibilityFocused();
    }
    
    public static boolean isVisibleToUser(final Object o) {
        return ((AccessibilityNodeInfo)o).isVisibleToUser();
    }
    
    public static Object obtain(final View view, final int n) {
        return AccessibilityNodeInfo.obtain(view, n);
    }
    
    public static boolean performAction(final Object o, final int n, final Bundle bundle) {
        return ((AccessibilityNodeInfo)o).performAction(n, bundle);
    }
    
    public static void setAccesibilityFocused(final Object o, final boolean accessibilityFocused) {
        ((AccessibilityNodeInfo)o).setAccessibilityFocused(accessibilityFocused);
    }
    
    public static void setMovementGranularities(final Object o, final int movementGranularities) {
        ((AccessibilityNodeInfo)o).setMovementGranularities(movementGranularities);
    }
    
    public static void setParent(final Object o, final View view, final int n) {
        ((AccessibilityNodeInfo)o).setParent(view, n);
    }
    
    public static void setSource(final Object o, final View view, final int n) {
        ((AccessibilityNodeInfo)o).setSource(view, n);
    }
    
    public static void setVisibleToUser(final Object o, final boolean visibleToUser) {
        ((AccessibilityNodeInfo)o).setVisibleToUser(visibleToUser);
    }
}
