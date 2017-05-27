// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

class AccessibilityEventCompatJellyBean
{
    public static int getAction(final AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getAction();
    }
    
    public static int getMovementGranularity(final AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getMovementGranularity();
    }
    
    public static void setAction(final AccessibilityEvent accessibilityEvent, final int action) {
        accessibilityEvent.setAction(action);
    }
    
    public static void setMovementGranularity(final AccessibilityEvent accessibilityEvent, final int movementGranularity) {
        accessibilityEvent.setMovementGranularity(movementGranularity);
    }
}
