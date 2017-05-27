// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

class AccessibilityNodeInfoCompatJellybeanMr2
{
    public static List<Object> findAccessibilityNodeInfosByViewId(final Object o, final String s) {
        return (List<Object>)((AccessibilityNodeInfo)o).findAccessibilityNodeInfosByViewId(s);
    }
    
    public static int getTextSelectionEnd(final Object o) {
        return ((AccessibilityNodeInfo)o).getTextSelectionEnd();
    }
    
    public static int getTextSelectionStart(final Object o) {
        return ((AccessibilityNodeInfo)o).getTextSelectionStart();
    }
    
    public static String getViewIdResourceName(final Object o) {
        return ((AccessibilityNodeInfo)o).getViewIdResourceName();
    }
    
    public static boolean isEditable(final Object o) {
        return ((AccessibilityNodeInfo)o).isEditable();
    }
    
    public static boolean refresh(final Object o) {
        return ((AccessibilityNodeInfo)o).refresh();
    }
    
    public static void setEditable(final Object o, final boolean editable) {
        ((AccessibilityNodeInfo)o).setEditable(editable);
    }
    
    public static void setTextSelection(final Object o, final int n, final int n2) {
        ((AccessibilityNodeInfo)o).setTextSelection(n, n2);
    }
    
    public static void setViewIdResourceName(final Object o, final String viewIdResourceName) {
        ((AccessibilityNodeInfo)o).setViewIdResourceName(viewIdResourceName);
    }
}
