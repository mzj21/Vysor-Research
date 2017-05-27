// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import java.util.Collections;
import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.List;
import android.view.accessibility.AccessibilityManager;
import android.os.Build$VERSION;

public final class AccessibilityManagerCompat
{
    private static final AccessibilityManagerVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (AccessibilityManagerVersionImpl)new AccessibilityManagerKitKatImpl();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (AccessibilityManagerVersionImpl)new AccessibilityManagerIcsImpl();
        }
        else {
            IMPL = (AccessibilityManagerVersionImpl)new AccessibilityManagerStubImpl();
        }
    }
    
    public static boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListener accessibilityStateChangeListener) {
        return AccessibilityManagerCompat.IMPL.addAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListener);
    }
    
    public static boolean addTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        return AccessibilityManagerCompat.IMPL.addTouchExplorationStateChangeListener(accessibilityManager, touchExplorationStateChangeListener);
    }
    
    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
        return AccessibilityManagerCompat.IMPL.getEnabledAccessibilityServiceList(accessibilityManager, n);
    }
    
    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompat.IMPL.getInstalledAccessibilityServiceList(accessibilityManager);
    }
    
    public static boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
        return AccessibilityManagerCompat.IMPL.isTouchExplorationEnabled(accessibilityManager);
    }
    
    public static boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListener accessibilityStateChangeListener) {
        return AccessibilityManagerCompat.IMPL.removeAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListener);
    }
    
    public static boolean removeTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
        return AccessibilityManagerCompat.IMPL.removeTouchExplorationStateChangeListener(accessibilityManager, touchExplorationStateChangeListener);
    }
    
    static class AccessibilityManagerIcsImpl extends AccessibilityManagerStubImpl
    {
        @Override
        public boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListener accessibilityStateChangeListener) {
            return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(accessibilityManager, this.newAccessibilityStateChangeListener(accessibilityStateChangeListener));
        }
        
        @Override
        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
            return AccessibilityManagerCompatIcs.getEnabledAccessibilityServiceList(accessibilityManager, n);
        }
        
        @Override
        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
            return AccessibilityManagerCompatIcs.getInstalledAccessibilityServiceList(accessibilityManager);
        }
        
        @Override
        public boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
            return AccessibilityManagerCompatIcs.isTouchExplorationEnabled(accessibilityManager);
        }
        
        @Override
        public AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerWrapper newAccessibilityStateChangeListener(final AccessibilityStateChangeListener accessibilityStateChangeListener) {
            return new AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerWrapper(accessibilityStateChangeListener, new AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge() {
                @Override
                public void onAccessibilityStateChanged(final boolean b) {
                    accessibilityStateChangeListener.onAccessibilityStateChanged(b);
                }
            });
        }
        
        @Override
        public boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListener accessibilityStateChangeListener) {
            return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(accessibilityManager, this.newAccessibilityStateChangeListener(accessibilityStateChangeListener));
        }
    }
    
    static class AccessibilityManagerKitKatImpl extends AccessibilityManagerIcsImpl
    {
        @Override
        public boolean addTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
            return AccessibilityManagerCompatKitKat.addTouchExplorationStateChangeListener(accessibilityManager, this.newTouchExplorationStateChangeListener(touchExplorationStateChangeListener));
        }
        
        @Override
        public AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerWrapper newTouchExplorationStateChangeListener(final TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
            return new AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerWrapper(touchExplorationStateChangeListener, new AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerBridge() {
                @Override
                public void onTouchExplorationStateChanged(final boolean b) {
                    touchExplorationStateChangeListener.onTouchExplorationStateChanged(b);
                }
            });
        }
        
        @Override
        public boolean removeTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
            return AccessibilityManagerCompatKitKat.removeTouchExplorationStateChangeListener(accessibilityManager, this.newTouchExplorationStateChangeListener(touchExplorationStateChangeListener));
        }
    }
    
    static class AccessibilityManagerStubImpl implements AccessibilityManagerVersionImpl
    {
        @Override
        public boolean addAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListener accessibilityStateChangeListener) {
            return false;
        }
        
        @Override
        public boolean addTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
            return false;
        }
        
        @Override
        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager accessibilityManager, final int n) {
            return Collections.emptyList();
        }
        
        @Override
        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager accessibilityManager) {
            return Collections.emptyList();
        }
        
        @Override
        public boolean isTouchExplorationEnabled(final AccessibilityManager accessibilityManager) {
            return false;
        }
        
        @Override
        public AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerWrapper newAccessibilityStateChangeListener(final AccessibilityStateChangeListener accessibilityStateChangeListener) {
            return null;
        }
        
        @Override
        public AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerWrapper newTouchExplorationStateChangeListener(final TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
            return null;
        }
        
        @Override
        public boolean removeAccessibilityStateChangeListener(final AccessibilityManager accessibilityManager, final AccessibilityStateChangeListener accessibilityStateChangeListener) {
            return false;
        }
        
        @Override
        public boolean removeTouchExplorationStateChangeListener(final AccessibilityManager accessibilityManager, final TouchExplorationStateChangeListener touchExplorationStateChangeListener) {
            return false;
        }
    }
    
    interface AccessibilityManagerVersionImpl
    {
        boolean addAccessibilityStateChangeListener(final AccessibilityManager p0, final AccessibilityStateChangeListener p1);
        
        boolean addTouchExplorationStateChangeListener(final AccessibilityManager p0, final TouchExplorationStateChangeListener p1);
        
        List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(final AccessibilityManager p0, final int p1);
        
        List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(final AccessibilityManager p0);
        
        boolean isTouchExplorationEnabled(final AccessibilityManager p0);
        
        AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerWrapper newAccessibilityStateChangeListener(final AccessibilityStateChangeListener p0);
        
        AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerWrapper newTouchExplorationStateChangeListener(final TouchExplorationStateChangeListener p0);
        
        boolean removeAccessibilityStateChangeListener(final AccessibilityManager p0, final AccessibilityStateChangeListener p1);
        
        boolean removeTouchExplorationStateChangeListener(final AccessibilityManager p0, final TouchExplorationStateChangeListener p1);
    }
    
    public interface AccessibilityStateChangeListener
    {
        void onAccessibilityStateChanged(final boolean p0);
    }
    
    @Deprecated
    public abstract static class AccessibilityStateChangeListenerCompat implements AccessibilityStateChangeListener
    {
    }
    
    public interface TouchExplorationStateChangeListener
    {
        void onTouchExplorationStateChanged(final boolean p0);
    }
}
