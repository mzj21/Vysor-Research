// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityWindowInfo;
import android.graphics.Rect;

class AccessibilityWindowInfoCompatApi21
{
    public static void getBoundsInScreen(final Object o, final Rect rect) {
        ((AccessibilityWindowInfo)o).getBoundsInScreen(rect);
    }
    
    public static Object getChild(final Object o, final int n) {
        return ((AccessibilityWindowInfo)o).getChild(n);
    }
    
    public static int getChildCount(final Object o) {
        return ((AccessibilityWindowInfo)o).getChildCount();
    }
    
    public static int getId(final Object o) {
        return ((AccessibilityWindowInfo)o).getId();
    }
    
    public static int getLayer(final Object o) {
        return ((AccessibilityWindowInfo)o).getLayer();
    }
    
    public static Object getParent(final Object o) {
        return ((AccessibilityWindowInfo)o).getParent();
    }
    
    public static Object getRoot(final Object o) {
        return ((AccessibilityWindowInfo)o).getRoot();
    }
    
    public static int getType(final Object o) {
        return ((AccessibilityWindowInfo)o).getType();
    }
    
    public static boolean isAccessibilityFocused(final Object o) {
        return ((AccessibilityWindowInfo)o).isAccessibilityFocused();
    }
    
    public static boolean isActive(final Object o) {
        return ((AccessibilityWindowInfo)o).isActive();
    }
    
    public static boolean isFocused(final Object o) {
        return ((AccessibilityWindowInfo)o).isFocused();
    }
    
    public static Object obtain() {
        return AccessibilityWindowInfo.obtain();
    }
    
    public static Object obtain(final Object o) {
        return AccessibilityWindowInfo.obtain((AccessibilityWindowInfo)o);
    }
    
    public static void recycle(final Object o) {
        ((AccessibilityWindowInfo)o).recycle();
    }
}
