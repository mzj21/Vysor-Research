// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build$VERSION;

public final class ViewGroupCompat
{
    static final ViewGroupCompatImpl IMPL;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = (ViewGroupCompatImpl)new ViewGroupCompatLollipopImpl();
        }
        else if (sdk_INT >= 18) {
            IMPL = (ViewGroupCompatImpl)new ViewGroupCompatJellybeanMR2Impl();
        }
        else if (sdk_INT >= 14) {
            IMPL = (ViewGroupCompatImpl)new ViewGroupCompatIcsImpl();
        }
        else if (sdk_INT >= 11) {
            IMPL = (ViewGroupCompatImpl)new ViewGroupCompatHCImpl();
        }
        else {
            IMPL = (ViewGroupCompatImpl)new ViewGroupCompatStubImpl();
        }
    }
    
    public static int getLayoutMode(final ViewGroup viewGroup) {
        return ViewGroupCompat.IMPL.getLayoutMode(viewGroup);
    }
    
    public static int getNestedScrollAxes(final ViewGroup viewGroup) {
        return ViewGroupCompat.IMPL.getNestedScrollAxes(viewGroup);
    }
    
    public static boolean isTransitionGroup(final ViewGroup viewGroup) {
        return ViewGroupCompat.IMPL.isTransitionGroup(viewGroup);
    }
    
    public static boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
        return ViewGroupCompat.IMPL.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
    
    public static void setLayoutMode(final ViewGroup viewGroup, final int n) {
        ViewGroupCompat.IMPL.setLayoutMode(viewGroup, n);
    }
    
    public static void setMotionEventSplittingEnabled(final ViewGroup viewGroup, final boolean b) {
        ViewGroupCompat.IMPL.setMotionEventSplittingEnabled(viewGroup, b);
    }
    
    public static void setTransitionGroup(final ViewGroup viewGroup, final boolean b) {
        ViewGroupCompat.IMPL.setTransitionGroup(viewGroup, b);
    }
    
    static class ViewGroupCompatHCImpl extends ViewGroupCompatStubImpl
    {
        @Override
        public void setMotionEventSplittingEnabled(final ViewGroup viewGroup, final boolean b) {
            ViewGroupCompatHC.setMotionEventSplittingEnabled(viewGroup, b);
        }
    }
    
    static class ViewGroupCompatIcsImpl extends ViewGroupCompatHCImpl
    {
        @Override
        public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
            return ViewGroupCompatIcs.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }
    
    interface ViewGroupCompatImpl
    {
        int getLayoutMode(final ViewGroup p0);
        
        int getNestedScrollAxes(final ViewGroup p0);
        
        boolean isTransitionGroup(final ViewGroup p0);
        
        boolean onRequestSendAccessibilityEvent(final ViewGroup p0, final View p1, final AccessibilityEvent p2);
        
        void setLayoutMode(final ViewGroup p0, final int p1);
        
        void setMotionEventSplittingEnabled(final ViewGroup p0, final boolean p1);
        
        void setTransitionGroup(final ViewGroup p0, final boolean p1);
    }
    
    static class ViewGroupCompatJellybeanMR2Impl extends ViewGroupCompatIcsImpl
    {
        @Override
        public int getLayoutMode(final ViewGroup viewGroup) {
            return ViewGroupCompatJellybeanMR2.getLayoutMode(viewGroup);
        }
        
        @Override
        public void setLayoutMode(final ViewGroup viewGroup, final int n) {
            ViewGroupCompatJellybeanMR2.setLayoutMode(viewGroup, n);
        }
    }
    
    static class ViewGroupCompatLollipopImpl extends ViewGroupCompatJellybeanMR2Impl
    {
        @Override
        public int getNestedScrollAxes(final ViewGroup viewGroup) {
            return ViewGroupCompatLollipop.getNestedScrollAxes(viewGroup);
        }
        
        @Override
        public boolean isTransitionGroup(final ViewGroup viewGroup) {
            return ViewGroupCompatLollipop.isTransitionGroup(viewGroup);
        }
        
        @Override
        public void setTransitionGroup(final ViewGroup viewGroup, final boolean b) {
            ViewGroupCompatLollipop.setTransitionGroup(viewGroup, b);
        }
    }
    
    static class ViewGroupCompatStubImpl implements ViewGroupCompatImpl
    {
        @Override
        public int getLayoutMode(final ViewGroup viewGroup) {
            return 0;
        }
        
        @Override
        public int getNestedScrollAxes(final ViewGroup viewGroup) {
            int nestedScrollAxes;
            if (viewGroup instanceof NestedScrollingParent) {
                nestedScrollAxes = ((NestedScrollingParent)viewGroup).getNestedScrollAxes();
            }
            else {
                nestedScrollAxes = 0;
            }
            return nestedScrollAxes;
        }
        
        @Override
        public boolean isTransitionGroup(final ViewGroup viewGroup) {
            return false;
        }
        
        @Override
        public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
            return true;
        }
        
        @Override
        public void setLayoutMode(final ViewGroup viewGroup, final int n) {
        }
        
        @Override
        public void setMotionEventSplittingEnabled(final ViewGroup viewGroup, final boolean b) {
        }
        
        @Override
        public void setTransitionGroup(final ViewGroup viewGroup, final boolean b) {
        }
    }
}
