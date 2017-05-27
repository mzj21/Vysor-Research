// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import java.util.List;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatIcs
{
    public static void addAction(final Object o, final int n) {
        ((AccessibilityNodeInfo)o).addAction(n);
    }
    
    public static void addChild(final Object o, final View view) {
        ((AccessibilityNodeInfo)o).addChild(view);
    }
    
    public static List<Object> findAccessibilityNodeInfosByText(final Object o, final String s) {
        return (List<Object>)((AccessibilityNodeInfo)o).findAccessibilityNodeInfosByText(s);
    }
    
    public static int getActions(final Object o) {
        return ((AccessibilityNodeInfo)o).getActions();
    }
    
    public static void getBoundsInParent(final Object o, final Rect rect) {
        ((AccessibilityNodeInfo)o).getBoundsInParent(rect);
    }
    
    public static void getBoundsInScreen(final Object o, final Rect rect) {
        ((AccessibilityNodeInfo)o).getBoundsInScreen(rect);
    }
    
    public static Object getChild(final Object o, final int n) {
        return ((AccessibilityNodeInfo)o).getChild(n);
    }
    
    public static int getChildCount(final Object o) {
        return ((AccessibilityNodeInfo)o).getChildCount();
    }
    
    public static CharSequence getClassName(final Object o) {
        return ((AccessibilityNodeInfo)o).getClassName();
    }
    
    public static CharSequence getContentDescription(final Object o) {
        return ((AccessibilityNodeInfo)o).getContentDescription();
    }
    
    public static CharSequence getPackageName(final Object o) {
        return ((AccessibilityNodeInfo)o).getPackageName();
    }
    
    public static Object getParent(final Object o) {
        return ((AccessibilityNodeInfo)o).getParent();
    }
    
    public static CharSequence getText(final Object o) {
        return ((AccessibilityNodeInfo)o).getText();
    }
    
    public static int getWindowId(final Object o) {
        return ((AccessibilityNodeInfo)o).getWindowId();
    }
    
    public static boolean isCheckable(final Object o) {
        return ((AccessibilityNodeInfo)o).isCheckable();
    }
    
    public static boolean isChecked(final Object o) {
        return ((AccessibilityNodeInfo)o).isChecked();
    }
    
    public static boolean isClickable(final Object o) {
        return ((AccessibilityNodeInfo)o).isClickable();
    }
    
    public static boolean isEnabled(final Object o) {
        return ((AccessibilityNodeInfo)o).isEnabled();
    }
    
    public static boolean isFocusable(final Object o) {
        return ((AccessibilityNodeInfo)o).isFocusable();
    }
    
    public static boolean isFocused(final Object o) {
        return ((AccessibilityNodeInfo)o).isFocused();
    }
    
    public static boolean isLongClickable(final Object o) {
        return ((AccessibilityNodeInfo)o).isLongClickable();
    }
    
    public static boolean isPassword(final Object o) {
        return ((AccessibilityNodeInfo)o).isPassword();
    }
    
    public static boolean isScrollable(final Object o) {
        return ((AccessibilityNodeInfo)o).isScrollable();
    }
    
    public static boolean isSelected(final Object o) {
        return ((AccessibilityNodeInfo)o).isSelected();
    }
    
    public static Object obtain() {
        return AccessibilityNodeInfo.obtain();
    }
    
    public static Object obtain(final View view) {
        return AccessibilityNodeInfo.obtain(view);
    }
    
    public static Object obtain(final Object o) {
        return AccessibilityNodeInfo.obtain((AccessibilityNodeInfo)o);
    }
    
    public static boolean performAction(final Object o, final int n) {
        return ((AccessibilityNodeInfo)o).performAction(n);
    }
    
    public static void recycle(final Object o) {
        ((AccessibilityNodeInfo)o).recycle();
    }
    
    public static void setBoundsInParent(final Object o, final Rect boundsInParent) {
        ((AccessibilityNodeInfo)o).setBoundsInParent(boundsInParent);
    }
    
    public static void setBoundsInScreen(final Object o, final Rect boundsInScreen) {
        ((AccessibilityNodeInfo)o).setBoundsInScreen(boundsInScreen);
    }
    
    public static void setCheckable(final Object o, final boolean checkable) {
        ((AccessibilityNodeInfo)o).setCheckable(checkable);
    }
    
    public static void setChecked(final Object o, final boolean checked) {
        ((AccessibilityNodeInfo)o).setChecked(checked);
    }
    
    public static void setClassName(final Object o, final CharSequence className) {
        ((AccessibilityNodeInfo)o).setClassName(className);
    }
    
    public static void setClickable(final Object o, final boolean clickable) {
        ((AccessibilityNodeInfo)o).setClickable(clickable);
    }
    
    public static void setContentDescription(final Object o, final CharSequence contentDescription) {
        ((AccessibilityNodeInfo)o).setContentDescription(contentDescription);
    }
    
    public static void setEnabled(final Object o, final boolean enabled) {
        ((AccessibilityNodeInfo)o).setEnabled(enabled);
    }
    
    public static void setFocusable(final Object o, final boolean focusable) {
        ((AccessibilityNodeInfo)o).setFocusable(focusable);
    }
    
    public static void setFocused(final Object o, final boolean focused) {
        ((AccessibilityNodeInfo)o).setFocused(focused);
    }
    
    public static void setLongClickable(final Object o, final boolean longClickable) {
        ((AccessibilityNodeInfo)o).setLongClickable(longClickable);
    }
    
    public static void setPackageName(final Object o, final CharSequence packageName) {
        ((AccessibilityNodeInfo)o).setPackageName(packageName);
    }
    
    public static void setParent(final Object o, final View parent) {
        ((AccessibilityNodeInfo)o).setParent(parent);
    }
    
    public static void setPassword(final Object o, final boolean password) {
        ((AccessibilityNodeInfo)o).setPassword(password);
    }
    
    public static void setScrollable(final Object o, final boolean scrollable) {
        ((AccessibilityNodeInfo)o).setScrollable(scrollable);
    }
    
    public static void setSelected(final Object o, final boolean selected) {
        ((AccessibilityNodeInfo)o).setSelected(selected);
    }
    
    public static void setSource(final Object o, final View source) {
        ((AccessibilityNodeInfo)o).setSource(source);
    }
    
    public static void setText(final Object o, final CharSequence text) {
        ((AccessibilityNodeInfo)o).setText(text);
    }
}
