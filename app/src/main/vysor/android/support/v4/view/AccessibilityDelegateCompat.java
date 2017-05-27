// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.os.Bundle;
import android.view.ViewGroup;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.os.Build$VERSION;

public class AccessibilityDelegateCompat
{
    private static final Object DEFAULT_DELEGATE;
    private static final AccessibilityDelegateImpl IMPL;
    final Object mBridge;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (AccessibilityDelegateImpl)new AccessibilityDelegateJellyBeanImpl();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (AccessibilityDelegateImpl)new AccessibilityDelegateIcsImpl();
        }
        else {
            IMPL = (AccessibilityDelegateImpl)new AccessibilityDelegateStubImpl();
        }
        DEFAULT_DELEGATE = AccessibilityDelegateCompat.IMPL.newAccessiblityDelegateDefaultImpl();
    }
    
    public AccessibilityDelegateCompat() {
        this.mBridge = AccessibilityDelegateCompat.IMPL.newAccessiblityDelegateBridge(this);
    }
    
    public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        return AccessibilityDelegateCompat.IMPL.dispatchPopulateAccessibilityEvent(AccessibilityDelegateCompat.DEFAULT_DELEGATE, view, accessibilityEvent);
    }
    
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View view) {
        return AccessibilityDelegateCompat.IMPL.getAccessibilityNodeProvider(AccessibilityDelegateCompat.DEFAULT_DELEGATE, view);
    }
    
    Object getBridge() {
        return this.mBridge;
    }
    
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        AccessibilityDelegateCompat.IMPL.onInitializeAccessibilityEvent(AccessibilityDelegateCompat.DEFAULT_DELEGATE, view, accessibilityEvent);
    }
    
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityDelegateCompat.IMPL.onInitializeAccessibilityNodeInfo(AccessibilityDelegateCompat.DEFAULT_DELEGATE, view, accessibilityNodeInfoCompat);
    }
    
    public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        AccessibilityDelegateCompat.IMPL.onPopulateAccessibilityEvent(AccessibilityDelegateCompat.DEFAULT_DELEGATE, view, accessibilityEvent);
    }
    
    public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
        return AccessibilityDelegateCompat.IMPL.onRequestSendAccessibilityEvent(AccessibilityDelegateCompat.DEFAULT_DELEGATE, viewGroup, view, accessibilityEvent);
    }
    
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return AccessibilityDelegateCompat.IMPL.performAccessibilityAction(AccessibilityDelegateCompat.DEFAULT_DELEGATE, view, n, bundle);
    }
    
    public void sendAccessibilityEvent(final View view, final int n) {
        AccessibilityDelegateCompat.IMPL.sendAccessibilityEvent(AccessibilityDelegateCompat.DEFAULT_DELEGATE, view, n);
    }
    
    public void sendAccessibilityEventUnchecked(final View view, final AccessibilityEvent accessibilityEvent) {
        AccessibilityDelegateCompat.IMPL.sendAccessibilityEventUnchecked(AccessibilityDelegateCompat.DEFAULT_DELEGATE, view, accessibilityEvent);
    }
    
    static class AccessibilityDelegateIcsImpl extends AccessibilityDelegateStubImpl
    {
        @Override
        public boolean dispatchPopulateAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            return AccessibilityDelegateCompatIcs.dispatchPopulateAccessibilityEvent(o, view, accessibilityEvent);
        }
        
        @Override
        public Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return AccessibilityDelegateCompatIcs.newAccessibilityDelegateBridge((AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge)new AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge() {
                @Override
                public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                    return accessibilityDelegateCompat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
                }
                
                @Override
                public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                    accessibilityDelegateCompat.onInitializeAccessibilityEvent(view, accessibilityEvent);
                }
                
                @Override
                public void onInitializeAccessibilityNodeInfo(final View view, final Object o) {
                    accessibilityDelegateCompat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(o));
                }
                
                @Override
                public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                    accessibilityDelegateCompat.onPopulateAccessibilityEvent(view, accessibilityEvent);
                }
                
                @Override
                public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
                    return accessibilityDelegateCompat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
                }
                
                @Override
                public void sendAccessibilityEvent(final View view, final int n) {
                    accessibilityDelegateCompat.sendAccessibilityEvent(view, n);
                }
                
                @Override
                public void sendAccessibilityEventUnchecked(final View view, final AccessibilityEvent accessibilityEvent) {
                    accessibilityDelegateCompat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
                }
            });
        }
        
        @Override
        public Object newAccessiblityDelegateDefaultImpl() {
            return AccessibilityDelegateCompatIcs.newAccessibilityDelegateDefaultImpl();
        }
        
        @Override
        public void onInitializeAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.onInitializeAccessibilityEvent(o, view, accessibilityEvent);
        }
        
        @Override
        public void onInitializeAccessibilityNodeInfo(final Object o, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            AccessibilityDelegateCompatIcs.onInitializeAccessibilityNodeInfo(o, view, accessibilityNodeInfoCompat.getInfo());
        }
        
        @Override
        public void onPopulateAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.onPopulateAccessibilityEvent(o, view, accessibilityEvent);
        }
        
        @Override
        public boolean onRequestSendAccessibilityEvent(final Object o, final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
            return AccessibilityDelegateCompatIcs.onRequestSendAccessibilityEvent(o, viewGroup, view, accessibilityEvent);
        }
        
        @Override
        public void sendAccessibilityEvent(final Object o, final View view, final int n) {
            AccessibilityDelegateCompatIcs.sendAccessibilityEvent(o, view, n);
        }
        
        @Override
        public void sendAccessibilityEventUnchecked(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            AccessibilityDelegateCompatIcs.sendAccessibilityEventUnchecked(o, view, accessibilityEvent);
        }
    }
    
    interface AccessibilityDelegateImpl
    {
        boolean dispatchPopulateAccessibilityEvent(final Object p0, final View p1, final AccessibilityEvent p2);
        
        AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final Object p0, final View p1);
        
        Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat p0);
        
        Object newAccessiblityDelegateDefaultImpl();
        
        void onInitializeAccessibilityEvent(final Object p0, final View p1, final AccessibilityEvent p2);
        
        void onInitializeAccessibilityNodeInfo(final Object p0, final View p1, final AccessibilityNodeInfoCompat p2);
        
        void onPopulateAccessibilityEvent(final Object p0, final View p1, final AccessibilityEvent p2);
        
        boolean onRequestSendAccessibilityEvent(final Object p0, final ViewGroup p1, final View p2, final AccessibilityEvent p3);
        
        boolean performAccessibilityAction(final Object p0, final View p1, final int p2, final Bundle p3);
        
        void sendAccessibilityEvent(final Object p0, final View p1, final int p2);
        
        void sendAccessibilityEventUnchecked(final Object p0, final View p1, final AccessibilityEvent p2);
    }
    
    static class AccessibilityDelegateJellyBeanImpl extends AccessibilityDelegateIcsImpl
    {
        @Override
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final Object o, final View view) {
            final Object accessibilityNodeProvider = AccessibilityDelegateCompatJellyBean.getAccessibilityNodeProvider(o, view);
            AccessibilityNodeProviderCompat accessibilityNodeProviderCompat;
            if (accessibilityNodeProvider != null) {
                accessibilityNodeProviderCompat = new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
            }
            else {
                accessibilityNodeProviderCompat = null;
            }
            return accessibilityNodeProviderCompat;
        }
        
        @Override
        public Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return AccessibilityDelegateCompatJellyBean.newAccessibilityDelegateBridge((AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean)new AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean() {
                @Override
                public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                    return accessibilityDelegateCompat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
                }
                
                @Override
                public Object getAccessibilityNodeProvider(final View view) {
                    final AccessibilityNodeProviderCompat accessibilityNodeProvider = accessibilityDelegateCompat.getAccessibilityNodeProvider(view);
                    Object provider;
                    if (accessibilityNodeProvider != null) {
                        provider = accessibilityNodeProvider.getProvider();
                    }
                    else {
                        provider = null;
                    }
                    return provider;
                }
                
                @Override
                public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                    accessibilityDelegateCompat.onInitializeAccessibilityEvent(view, accessibilityEvent);
                }
                
                @Override
                public void onInitializeAccessibilityNodeInfo(final View view, final Object o) {
                    accessibilityDelegateCompat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(o));
                }
                
                @Override
                public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                    accessibilityDelegateCompat.onPopulateAccessibilityEvent(view, accessibilityEvent);
                }
                
                @Override
                public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
                    return accessibilityDelegateCompat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
                }
                
                @Override
                public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
                    return accessibilityDelegateCompat.performAccessibilityAction(view, n, bundle);
                }
                
                @Override
                public void sendAccessibilityEvent(final View view, final int n) {
                    accessibilityDelegateCompat.sendAccessibilityEvent(view, n);
                }
                
                @Override
                public void sendAccessibilityEventUnchecked(final View view, final AccessibilityEvent accessibilityEvent) {
                    accessibilityDelegateCompat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
                }
            });
        }
        
        @Override
        public boolean performAccessibilityAction(final Object o, final View view, final int n, final Bundle bundle) {
            return AccessibilityDelegateCompatJellyBean.performAccessibilityAction(o, view, n, bundle);
        }
    }
    
    static class AccessibilityDelegateStubImpl implements AccessibilityDelegateImpl
    {
        @Override
        public boolean dispatchPopulateAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
            return false;
        }
        
        @Override
        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final Object o, final View view) {
            return null;
        }
        
        @Override
        public Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat accessibilityDelegateCompat) {
            return null;
        }
        
        @Override
        public Object newAccessiblityDelegateDefaultImpl() {
            return null;
        }
        
        @Override
        public void onInitializeAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        }
        
        @Override
        public void onInitializeAccessibilityNodeInfo(final Object o, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        }
        
        @Override
        public void onPopulateAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        }
        
        @Override
        public boolean onRequestSendAccessibilityEvent(final Object o, final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
            return true;
        }
        
        @Override
        public boolean performAccessibilityAction(final Object o, final View view, final int n, final Bundle bundle) {
            return false;
        }
        
        @Override
        public void sendAccessibilityEvent(final Object o, final View view, final int n) {
        }
        
        @Override
        public void sendAccessibilityEventUnchecked(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        }
    }
}
