// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.os.Build$VERSION;

public final class AccessibilityEventCompat
{
    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;
    private static final AccessibilityEventVersionImpl IMPL;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_ASSIST_READING_CONTEXT = 16777216;
    public static final int TYPE_GESTURE_DETECTION_END = 524288;
    public static final int TYPE_GESTURE_DETECTION_START = 262144;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 2097152;
    public static final int TYPE_TOUCH_INTERACTION_START = 1048576;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 65536;
    public static final int TYPE_VIEW_CONTEXT_CLICKED = 8388608;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 131072;
    public static final int TYPE_WINDOWS_CHANGED = 4194304;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (AccessibilityEventVersionImpl)new AccessibilityEventKitKatImpl();
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (AccessibilityEventVersionImpl)new AccessibilityEventJellyBeanImpl();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (AccessibilityEventVersionImpl)new AccessibilityEventIcsImpl();
        }
        else {
            IMPL = (AccessibilityEventVersionImpl)new AccessibilityEventStubImpl();
        }
    }
    
    public static void appendRecord(final AccessibilityEvent accessibilityEvent, final AccessibilityRecordCompat accessibilityRecordCompat) {
        AccessibilityEventCompat.IMPL.appendRecord(accessibilityEvent, accessibilityRecordCompat.getImpl());
    }
    
    public static AccessibilityRecordCompat asRecord(final AccessibilityEvent accessibilityEvent) {
        return new AccessibilityRecordCompat(accessibilityEvent);
    }
    
    public static int getContentChangeTypes(final AccessibilityEvent accessibilityEvent) {
        return AccessibilityEventCompat.IMPL.getContentChangeTypes(accessibilityEvent);
    }
    
    public static AccessibilityRecordCompat getRecord(final AccessibilityEvent accessibilityEvent, final int n) {
        return new AccessibilityRecordCompat(AccessibilityEventCompat.IMPL.getRecord(accessibilityEvent, n));
    }
    
    public static int getRecordCount(final AccessibilityEvent accessibilityEvent) {
        return AccessibilityEventCompat.IMPL.getRecordCount(accessibilityEvent);
    }
    
    public static void setContentChangeTypes(final AccessibilityEvent accessibilityEvent, final int n) {
        AccessibilityEventCompat.IMPL.setContentChangeTypes(accessibilityEvent, n);
    }
    
    public int getAction(final AccessibilityEvent accessibilityEvent) {
        return AccessibilityEventCompat.IMPL.getAction(accessibilityEvent);
    }
    
    public int getMovementGranularity(final AccessibilityEvent accessibilityEvent) {
        return AccessibilityEventCompat.IMPL.getMovementGranularity(accessibilityEvent);
    }
    
    public void setAction(final AccessibilityEvent accessibilityEvent, final int n) {
        AccessibilityEventCompat.IMPL.setAction(accessibilityEvent, n);
    }
    
    public void setMovementGranularity(final AccessibilityEvent accessibilityEvent, final int n) {
        AccessibilityEventCompat.IMPL.setMovementGranularity(accessibilityEvent, n);
    }
    
    static class AccessibilityEventIcsImpl extends AccessibilityEventStubImpl
    {
        @Override
        public void appendRecord(final AccessibilityEvent accessibilityEvent, final Object o) {
            AccessibilityEventCompatIcs.appendRecord(accessibilityEvent, o);
        }
        
        @Override
        public Object getRecord(final AccessibilityEvent accessibilityEvent, final int n) {
            return AccessibilityEventCompatIcs.getRecord(accessibilityEvent, n);
        }
        
        @Override
        public int getRecordCount(final AccessibilityEvent accessibilityEvent) {
            return AccessibilityEventCompatIcs.getRecordCount(accessibilityEvent);
        }
    }
    
    static class AccessibilityEventJellyBeanImpl extends AccessibilityEventIcsImpl
    {
        @Override
        public int getAction(final AccessibilityEvent accessibilityEvent) {
            return AccessibilityEventCompatJellyBean.getAction(accessibilityEvent);
        }
        
        @Override
        public int getMovementGranularity(final AccessibilityEvent accessibilityEvent) {
            return AccessibilityEventCompatJellyBean.getMovementGranularity(accessibilityEvent);
        }
        
        @Override
        public void setAction(final AccessibilityEvent accessibilityEvent, final int n) {
            AccessibilityEventCompatJellyBean.setAction(accessibilityEvent, n);
        }
        
        @Override
        public void setMovementGranularity(final AccessibilityEvent accessibilityEvent, final int n) {
            AccessibilityEventCompatJellyBean.setMovementGranularity(accessibilityEvent, n);
        }
    }
    
    static class AccessibilityEventKitKatImpl extends AccessibilityEventJellyBeanImpl
    {
        @Override
        public int getContentChangeTypes(final AccessibilityEvent accessibilityEvent) {
            return AccessibilityEventCompatKitKat.getContentChangeTypes(accessibilityEvent);
        }
        
        @Override
        public void setContentChangeTypes(final AccessibilityEvent accessibilityEvent, final int n) {
            AccessibilityEventCompatKitKat.setContentChangeTypes(accessibilityEvent, n);
        }
    }
    
    static class AccessibilityEventStubImpl implements AccessibilityEventVersionImpl
    {
        @Override
        public void appendRecord(final AccessibilityEvent accessibilityEvent, final Object o) {
        }
        
        @Override
        public int getAction(final AccessibilityEvent accessibilityEvent) {
            return 0;
        }
        
        @Override
        public int getContentChangeTypes(final AccessibilityEvent accessibilityEvent) {
            return 0;
        }
        
        @Override
        public int getMovementGranularity(final AccessibilityEvent accessibilityEvent) {
            return 0;
        }
        
        @Override
        public Object getRecord(final AccessibilityEvent accessibilityEvent, final int n) {
            return null;
        }
        
        @Override
        public int getRecordCount(final AccessibilityEvent accessibilityEvent) {
            return 0;
        }
        
        @Override
        public void setAction(final AccessibilityEvent accessibilityEvent, final int n) {
        }
        
        @Override
        public void setContentChangeTypes(final AccessibilityEvent accessibilityEvent, final int n) {
        }
        
        @Override
        public void setMovementGranularity(final AccessibilityEvent accessibilityEvent, final int n) {
        }
    }
    
    interface AccessibilityEventVersionImpl
    {
        void appendRecord(final AccessibilityEvent p0, final Object p1);
        
        int getAction(final AccessibilityEvent p0);
        
        int getContentChangeTypes(final AccessibilityEvent p0);
        
        int getMovementGranularity(final AccessibilityEvent p0);
        
        Object getRecord(final AccessibilityEvent p0, final int p1);
        
        int getRecordCount(final AccessibilityEvent p0);
        
        void setAction(final AccessibilityEvent p0, final int p1);
        
        void setContentChangeTypes(final AccessibilityEvent p0, final int p1);
        
        void setMovementGranularity(final AccessibilityEvent p0, final int p1);
    }
}
