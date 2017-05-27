// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatApi22
{
    public static Object getTraversalAfter(final Object o) {
        return ((AccessibilityNodeInfo)o).getTraversalAfter();
    }
    
    public static Object getTraversalBefore(final Object o) {
        return ((AccessibilityNodeInfo)o).getTraversalBefore();
    }
    
    public static void setTraversalAfter(final Object o, final View traversalAfter) {
        ((AccessibilityNodeInfo)o).setTraversalAfter(traversalAfter);
    }
    
    public static void setTraversalAfter(final Object o, final View view, final int n) {
        ((AccessibilityNodeInfo)o).setTraversalAfter(view, n);
    }
    
    public static void setTraversalBefore(final Object o, final View traversalBefore) {
        ((AccessibilityNodeInfo)o).setTraversalBefore(traversalBefore);
    }
    
    public static void setTraversalBefore(final Object o, final View view, final int n) {
        ((AccessibilityNodeInfo)o).setTraversalBefore(view, n);
    }
}
