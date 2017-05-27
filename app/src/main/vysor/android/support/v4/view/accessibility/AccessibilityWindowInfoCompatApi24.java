// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityWindowInfo;

class AccessibilityWindowInfoCompatApi24
{
    public static Object getAnchor(final Object o) {
        return ((AccessibilityWindowInfo)o).getAnchor();
    }
    
    public static CharSequence getTitle(final Object o) {
        return ((AccessibilityWindowInfo)o).getTitle();
    }
}
