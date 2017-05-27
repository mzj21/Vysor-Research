// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatJellybeanMr1
{
    public static Object getLabelFor(final Object o) {
        return ((AccessibilityNodeInfo)o).getLabelFor();
    }
    
    public static Object getLabeledBy(final Object o) {
        return ((AccessibilityNodeInfo)o).getLabeledBy();
    }
    
    public static void setLabelFor(final Object o, final View labelFor) {
        ((AccessibilityNodeInfo)o).setLabelFor(labelFor);
    }
    
    public static void setLabelFor(final Object o, final View view, final int n) {
        ((AccessibilityNodeInfo)o).setLabelFor(view, n);
    }
    
    public static void setLabeledBy(final Object o, final View labeledBy) {
        ((AccessibilityNodeInfo)o).setLabeledBy(labeledBy);
    }
    
    public static void setLabeledBy(final Object o, final View view, final int n) {
        ((AccessibilityNodeInfo)o).setLabeledBy(view, n);
    }
}
