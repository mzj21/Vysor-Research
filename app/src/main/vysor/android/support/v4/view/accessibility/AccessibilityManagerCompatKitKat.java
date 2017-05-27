// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager$TouchExplorationStateChangeListener;
import android.view.accessibility.AccessibilityManager;

class AccessibilityManagerCompatKitKat
{
    public static boolean addTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final Object o) {
        return accessibilityManager.addTouchExplorationStateChangeListener((AccessibilityManager$TouchExplorationStateChangeListener)o);
    }
    
    public static Object newTouchExplorationStateChangeListener(final TouchExplorationStateChangeListenerBridge touchExplorationStateChangeListenerBridge) {
        return new AccessibilityManager$TouchExplorationStateChangeListener() {
            public void onTouchExplorationStateChanged(final boolean b) {
                touchExplorationStateChangeListenerBridge.onTouchExplorationStateChanged(b);
            }
        };
    }
    
    public static boolean removeTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final Object o) {
        return accessibilityManager.removeTouchExplorationStateChangeListener((AccessibilityManager$TouchExplorationStateChangeListener)o);
    }
    
    interface TouchExplorationStateChangeListenerBridge
    {
        void onTouchExplorationStateChanged(final boolean p0);
    }
    
    public static class TouchExplorationStateChangeListenerWrapper implements AccessibilityManager$TouchExplorationStateChangeListener
    {
        final Object mListener;
        final TouchExplorationStateChangeListenerBridge mListenerBridge;
        
        public TouchExplorationStateChangeListenerWrapper(final Object mListener, final TouchExplorationStateChangeListenerBridge mListenerBridge) {
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
                    final TouchExplorationStateChangeListenerWrapper touchExplorationStateChangeListenerWrapper = (TouchExplorationStateChangeListenerWrapper)o;
                    if (this.mListener == null) {
                        if (touchExplorationStateChangeListenerWrapper.mListener != null) {
                            equals = false;
                        }
                    }
                    else {
                        equals = this.mListener.equals(touchExplorationStateChangeListenerWrapper.mListener);
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
        
        public void onTouchExplorationStateChanged(final boolean b) {
            this.mListenerBridge.onTouchExplorationStateChanged(b);
        }
    }
}
