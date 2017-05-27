// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction;

class AccessibilityNodeInfoCompatApi23
{
    public static Object getActionContextClick() {
        return AccessibilityNodeInfo$AccessibilityAction.ACTION_CONTEXT_CLICK;
    }
    
    public static Object getActionScrollDown() {
        return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_DOWN;
    }
    
    public static Object getActionScrollLeft() {
        return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_LEFT;
    }
    
    public static Object getActionScrollRight() {
        return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_RIGHT;
    }
    
    public static Object getActionScrollToPosition() {
        return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_TO_POSITION;
    }
    
    public static Object getActionScrollUp() {
        return AccessibilityNodeInfo$AccessibilityAction.ACTION_SCROLL_UP;
    }
    
    public static Object getActionShowOnScreen() {
        return AccessibilityNodeInfo$AccessibilityAction.ACTION_SHOW_ON_SCREEN;
    }
    
    public static boolean isContextClickable(final Object o) {
        return ((AccessibilityNodeInfo)o).isContextClickable();
    }
    
    public static void setContextClickable(final Object o, final boolean contextClickable) {
        ((AccessibilityNodeInfo)o).setContextClickable(contextClickable);
    }
}
