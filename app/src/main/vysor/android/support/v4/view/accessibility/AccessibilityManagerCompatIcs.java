// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.List;
import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;
import android.view.accessibility.AccessibilityManager;

class AccessibilityManagerCompatIcs
{
    public static boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerWrapper accessibilityStateChangeListenerWrapper) {
        return accessibilityManager.addAccessibilityStateChangeListener((AccessibilityManager$AccessibilityStateChangeListener)accessibilityStateChangeListenerWrapper);
    }
    
    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
        return (List<AccessibilityServiceInfo>)accessibilityManager.getEnabledAccessibilityServiceList(n);
    }
    
    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
        return (List<AccessibilityServiceInfo>)accessibilityManager.getInstalledAccessibilityServiceList();
    }
    
    public static boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
        return accessibilityManager.isTouchExplorationEnabled();
    }
    
    public static boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListenerWrapper accessibilityStateChangeListenerWrapper) {
        return accessibilityManager.removeAccessibilityStateChangeListener((AccessibilityManager$AccessibilityStateChangeListener)accessibilityStateChangeListenerWrapper);
    }
    
    interface AccessibilityStateChangeListenerBridge
    {
        void onAccessibilityStateChanged(final boolean p0);
    }
    
    public static class AccessibilityStateChangeListenerWrapper implements AccessibilityManager$AccessibilityStateChangeListener
    {
        Object mListener;
        AccessibilityStateChangeListenerBridge mListenerBridge;
        
        public AccessibilityStateChangeListenerWrapper(final Object mListener, final AccessibilityStateChangeListenerBridge mListenerBridge) {
            this.mListener = mListener;
            this.mListenerBridge = mListenerBridge;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean equals = true;
            if (this != o) {
                if (o == null || this.getClass() != o.getClass()) {
                    equals = false;
                }
                else {
                    final AccessibilityStateChangeListenerWrapper accessibilityStateChangeListenerWrapper = (AccessibilityStateChangeListenerWrapper)o;
                    if (this.mListener == null) {
                        if (accessibilityStateChangeListenerWrapper.mListener != null) {
                            equals = false;
                        }
                    }
                    else {
                        equals = this.mListener.equals(accessibilityStateChangeListenerWrapper.mListener);
                    }
                }
            }
            return equals;
        }
        
        @Override
        public int hashCode() {
            int hashCode;
            if (this.mListener == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.mListener.hashCode();
            }
            return hashCode;
        }
        
        public void onAccessibilityStateChanged(final boolean b) {
            this.mListenerBridge.onAccessibilityStateChanged(b);
        }
    }
}
