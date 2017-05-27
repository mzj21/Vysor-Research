// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.View;
import android.view.ViewParent;
import android.os.Build$VERSION;

public final class ViewParentCompat
{
    static final ViewParentCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = (ViewParentCompatImpl)new ViewParentCompatLollipopImpl();
        }
        else if (sdk_INT >= 19) {
            IMPL = (ViewParentCompatImpl)new ViewParentCompatKitKatImpl();
        }
        else if (sdk_INT >= 14) {
            IMPL = (ViewParentCompatImpl)new ViewParentCompatICSImpl();
        }
        else {
            IMPL = (ViewParentCompatImpl)new ViewParentCompatStubImpl();
        }
    }
    
    public static void notifySubtreeAccessibilityStateChanged(final ViewParent viewParent, final View view, final View view2, final int n) {
        ViewParentCompat.IMPL.notifySubtreeAccessibilityStateChanged(viewParent, view, view2, n);
    }
    
    public static boolean onNestedFling(final ViewParent viewParent, final View view, final float n, final float n2, final boolean b) {
        return ViewParentCompat.IMPL.onNestedFling(viewParent, view, n, n2, b);
    }
    
    public static boolean onNestedPreFling(final ViewParent viewParent, final View view, final float n, final float n2) {
        return ViewParentCompat.IMPL.onNestedPreFling(viewParent, view, n, n2);
    }
    
    public static void onNestedPreScroll(final ViewParent viewParent, final View view, final int n, final int n2, final int[] array) {
        ViewParentCompat.IMPL.onNestedPreScroll(viewParent, view, n, n2, array);
    }
    
    public static void onNestedScroll(final ViewParent viewParent, final View view, final int n, final int n2, final int n3, final int n4) {
        ViewParentCompat.IMPL.onNestedScroll(viewParent, view, n, n2, n3, n4);
    }
    
    public static void onNestedScrollAccepted(final ViewParent viewParent, final View view, final View view2, final int n) {
        ViewParentCompat.IMPL.onNestedScrollAccepted(viewParent, view, view2, n);
    }
    
    public static boolean onStartNestedScroll(final ViewParent viewParent, final View view, final View view2, final int n) {
        return ViewParentCompat.IMPL.onStartNestedScroll(viewParent, view, view2, n);
    }
    
    public static void onStopNestedScroll(final ViewParent viewParent, final View view) {
        ViewParentCompat.IMPL.onStopNestedScroll(viewParent, view);
    }
    
    public static boolean requestSendAccessibilityEvent(final ViewParent viewParent, final View view, final AccessibilityEvent accessibilityEvent) {
        return ViewParentCompat.IMPL.requestSendAccessibilityEvent(viewParent, view, accessibilityEvent);
    }
    
    static class ViewParentCompatICSImpl extends ViewParentCompatStubImpl
    {
        @Override
        public boolean requestSendAccessibilityEvent(final ViewParent viewParent, final View view, final AccessibilityEvent accessibilityEvent) {
            return ViewParentCompatICS.requestSendAccessibilityEvent(viewParent, view, accessibilityEvent);
        }
    }
    
    interface ViewParentCompatImpl
    {
        void notifySubtreeAccessibilityStateChanged(final ViewParent p0, final View p1, final View p2, final int p3);
        
        boolean onNestedFling(final ViewParent p0, final View p1, final float p2, final float p3, final boolean p4);
        
        boolean onNestedPreFling(final ViewParent p0, final View p1, final float p2, final float p3);
        
        void onNestedPreScroll(final ViewParent p0, final View p1, final int p2, final int p3, final int[] p4);
        
        void onNestedScroll(final ViewParent p0, final View p1, final int p2, final int p3, final int p4, final int p5);
        
        void onNestedScrollAccepted(final ViewParent p0, final View p1, final View p2, final int p3);
        
        boolean onStartNestedScroll(final ViewParent p0, final View p1, final View p2, final int p3);
        
        void onStopNestedScroll(final ViewParent p0, final View p1);
        
        boolean requestSendAccessibilityEvent(final ViewParent p0, final View p1, final AccessibilityEvent p2);
    }
    
    static class ViewParentCompatKitKatImpl extends ViewParentCompatICSImpl
    {
        @Override
        public void notifySubtreeAccessibilityStateChanged(final ViewParent viewParent, final View view, final View view2, final int n) {
            ViewParentCompatKitKat.notifySubtreeAccessibilityStateChanged(viewParent, view, view2, n);
        }
    }
    
    static class ViewParentCompatLollipopImpl extends ViewParentCompatKitKatImpl
    {
        @Override
        public boolean onNestedFling(final ViewParent viewParent, final View view, final float n, final float n2, final boolean b) {
            return ViewParentCompatLollipop.onNestedFling(viewParent, view, n, n2, b);
        }
        
        @Override
        public boolean onNestedPreFling(final ViewParent viewParent, final View view, final float n, final float n2) {
            return ViewParentCompatLollipop.onNestedPreFling(viewParent, view, n, n2);
        }
        
        @Override
        public void onNestedPreScroll(final ViewParent viewParent, final View view, final int n, final int n2, final int[] array) {
            ViewParentCompatLollipop.onNestedPreScroll(viewParent, view, n, n2, array);
        }
        
        @Override
        public void onNestedScroll(final ViewParent viewParent, final View view, final int n, final int n2, final int n3, final int n4) {
            ViewParentCompatLollipop.onNestedScroll(viewParent, view, n, n2, n3, n4);
        }
        
        @Override
        public void onNestedScrollAccepted(final ViewParent viewParent, final View view, final View view2, final int n) {
            ViewParentCompatLollipop.onNestedScrollAccepted(viewParent, view, view2, n);
        }
        
        @Override
        public boolean onStartNestedScroll(final ViewParent viewParent, final View view, final View view2, final int n) {
            return ViewParentCompatLollipop.onStartNestedScroll(viewParent, view, view2, n);
        }
        
        @Override
        public void onStopNestedScroll(final ViewParent viewParent, final View view) {
            ViewParentCompatLollipop.onStopNestedScroll(viewParent, view);
        }
    }
    
    static class ViewParentCompatStubImpl implements ViewParentCompatImpl
    {
        @Override
        public void notifySubtreeAccessibilityStateChanged(final ViewParent viewParent, final View view, final View view2, final int n) {
        }
        
        @Override
        public boolean onNestedFling(final ViewParent viewParent, final View view, final float n, final float n2, final boolean b) {
            return viewParent instanceof NestedScrollingParent && ((NestedScrollingParent)viewParent).onNestedFling(view, n, n2, b);
        }
        
        @Override
        public boolean onNestedPreFling(final ViewParent viewParent, final View view, final float n, final float n2) {
            return viewParent instanceof NestedScrollingParent && ((NestedScrollingParent)viewParent).onNestedPreFling(view, n, n2);
        }
        
        @Override
        public void onNestedPreScroll(final ViewParent viewParent, final View view, final int n, final int n2, final int[] array) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent)viewParent).onNestedPreScroll(view, n, n2, array);
            }
        }
        
        @Override
        public void onNestedScroll(final ViewParent viewParent, final View view, final int n, final int n2, final int n3, final int n4) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent)viewParent).onNestedScroll(view, n, n2, n3, n4);
            }
        }
        
        @Override
        public void onNestedScrollAccepted(final ViewParent viewParent, final View view, final View view2, final int n) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent)viewParent).onNestedScrollAccepted(view, view2, n);
            }
        }
        
        @Override
        public boolean onStartNestedScroll(final ViewParent viewParent, final View view, final View view2, final int n) {
            return viewParent instanceof NestedScrollingParent && ((NestedScrollingParent)viewParent).onStartNestedScroll(view, view2, n);
        }
        
        @Override
        public void onStopNestedScroll(final ViewParent viewParent, final View view) {
            if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent)viewParent).onStopNestedScroll(view);
            }
        }
        
        @Override
        public boolean requestSendAccessibilityEvent(final ViewParent viewParent, final View view, final AccessibilityEvent accessibilityEvent) {
            boolean b;
            if (view == null) {
                b = false;
            }
            else {
                ((AccessibilityManager)view.getContext().getSystemService("accessibility")).sendAccessibilityEvent(accessibilityEvent);
                b = true;
            }
            return b;
        }
    }
}
