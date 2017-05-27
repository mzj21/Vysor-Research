// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build$VERSION;

public class AccessibilityWindowInfoCompat
{
    private static final AccessibilityWindowInfoImpl IMPL;
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private static final int UNDEFINED = -1;
    private Object mInfo;
    
    static {
        if (Build$VERSION.SDK_INT >= 24) {
            IMPL = (AccessibilityWindowInfoImpl)new AccessibilityWindowInfoApi24Impl();
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            IMPL = (AccessibilityWindowInfoImpl)new AccessibilityWindowInfoApi21Impl();
        }
        else {
            IMPL = (AccessibilityWindowInfoImpl)new AccessibilityWindowInfoStubImpl();
        }
    }
    
    private AccessibilityWindowInfoCompat(final Object mInfo) {
        this.mInfo = mInfo;
    }
    
    public static AccessibilityWindowInfoCompat obtain() {
        return wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.obtain());
    }
    
    public static AccessibilityWindowInfoCompat obtain(final AccessibilityWindowInfoCompat accessibilityWindowInfoCompat) {
        AccessibilityWindowInfoCompat wrapNonNullInstance;
        if (accessibilityWindowInfoCompat == null) {
            wrapNonNullInstance = null;
        }
        else {
            wrapNonNullInstance = wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.obtain(accessibilityWindowInfoCompat.mInfo));
        }
        return wrapNonNullInstance;
    }
    
    private static String typeToString(final int n) {
        String s = null;
        switch (n) {
            default: {
                s = "<UNKNOWN>";
                break;
            }
            case 1: {
                s = "TYPE_APPLICATION";
                break;
            }
            case 2: {
                s = "TYPE_INPUT_METHOD";
                break;
            }
            case 3: {
                s = "TYPE_SYSTEM";
                break;
            }
            case 4: {
                s = "TYPE_ACCESSIBILITY_OVERLAY";
                break;
            }
        }
        return s;
    }
    
    static AccessibilityWindowInfoCompat wrapNonNullInstance(final Object o) {
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat;
        if (o != null) {
            accessibilityWindowInfoCompat = new AccessibilityWindowInfoCompat(o);
        }
        else {
            accessibilityWindowInfoCompat = null;
        }
        return accessibilityWindowInfoCompat;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            if (o == null) {
                b = false;
            }
            else if (this.getClass() != o.getClass()) {
                b = false;
            }
            else {
                final AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat)o;
                if (this.mInfo == null) {
                    if (accessibilityWindowInfoCompat.mInfo != null) {
                        b = false;
                    }
                }
                else if (!this.mInfo.equals(accessibilityWindowInfoCompat.mInfo)) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public AccessibilityNodeInfoCompat getAnchor() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.getAnchor(this.mInfo));
    }
    
    public void getBoundsInScreen(final Rect rect) {
        AccessibilityWindowInfoCompat.IMPL.getBoundsInScreen(this.mInfo, rect);
    }
    
    public AccessibilityWindowInfoCompat getChild(final int n) {
        return wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.getChild(this.mInfo, n));
    }
    
    public int getChildCount() {
        return AccessibilityWindowInfoCompat.IMPL.getChildCount(this.mInfo);
    }
    
    public int getId() {
        return AccessibilityWindowInfoCompat.IMPL.getId(this.mInfo);
    }
    
    public int getLayer() {
        return AccessibilityWindowInfoCompat.IMPL.getLayer(this.mInfo);
    }
    
    public AccessibilityWindowInfoCompat getParent() {
        return wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.getParent(this.mInfo));
    }
    
    public AccessibilityNodeInfoCompat getRoot() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityWindowInfoCompat.IMPL.getRoot(this.mInfo));
    }
    
    public CharSequence getTitle() {
        return AccessibilityWindowInfoCompat.IMPL.getTitle(this.mInfo);
    }
    
    public int getType() {
        return AccessibilityWindowInfoCompat.IMPL.getType(this.mInfo);
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.mInfo == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mInfo.hashCode();
        }
        return hashCode;
    }
    
    public boolean isAccessibilityFocused() {
        return AccessibilityWindowInfoCompat.IMPL.isAccessibilityFocused(this.mInfo);
    }
    
    public boolean isActive() {
        return AccessibilityWindowInfoCompat.IMPL.isActive(this.mInfo);
    }
    
    public boolean isFocused() {
        return AccessibilityWindowInfoCompat.IMPL.isFocused(this.mInfo);
    }
    
    public void recycle() {
        AccessibilityWindowInfoCompat.IMPL.recycle(this.mInfo);
    }
    
    @Override
    public String toString() {
        boolean b = true;
        final StringBuilder sb = new StringBuilder();
        final Rect rect = new Rect();
        this.getBoundsInScreen(rect);
        sb.append("AccessibilityWindowInfo[");
        sb.append("id=").append(this.getId());
        sb.append(", type=").append(typeToString(this.getType()));
        sb.append(", layer=").append(this.getLayer());
        sb.append(", bounds=").append(rect);
        sb.append(", focused=").append(this.isFocused());
        sb.append(", active=").append(this.isActive());
        sb.append(", hasParent=").append(this.getParent() != null && b);
        final StringBuilder append = sb.append(", hasChildren=");
        if (this.getChildCount() <= 0) {
            b = false;
        }
        append.append(b);
        sb.append(']');
        return sb.toString();
    }
    
    private static class AccessibilityWindowInfoApi21Impl extends AccessibilityWindowInfoStubImpl
    {
        @Override
        public void getBoundsInScreen(final Object o, final Rect rect) {
            AccessibilityWindowInfoCompatApi21.getBoundsInScreen(o, rect);
        }
        
        @Override
        public Object getChild(final Object o, final int n) {
            return AccessibilityWindowInfoCompatApi21.getChild(o, n);
        }
        
        @Override
        public int getChildCount(final Object o) {
            return AccessibilityWindowInfoCompatApi21.getChildCount(o);
        }
        
        @Override
        public int getId(final Object o) {
            return AccessibilityWindowInfoCompatApi21.getId(o);
        }
        
        @Override
        public int getLayer(final Object o) {
            return AccessibilityWindowInfoCompatApi21.getLayer(o);
        }
        
        @Override
        public Object getParent(final Object o) {
            return AccessibilityWindowInfoCompatApi21.getParent(o);
        }
        
        @Override
        public Object getRoot(final Object o) {
            return AccessibilityWindowInfoCompatApi21.getRoot(o);
        }
        
        @Override
        public int getType(final Object o) {
            return AccessibilityWindowInfoCompatApi21.getType(o);
        }
        
        @Override
        public boolean isAccessibilityFocused(final Object o) {
            return AccessibilityWindowInfoCompatApi21.isAccessibilityFocused(o);
        }
        
        @Override
        public boolean isActive(final Object o) {
            return AccessibilityWindowInfoCompatApi21.isActive(o);
        }
        
        @Override
        public boolean isFocused(final Object o) {
            return AccessibilityWindowInfoCompatApi21.isFocused(o);
        }
        
        @Override
        public Object obtain() {
            return AccessibilityWindowInfoCompatApi21.obtain();
        }
        
        @Override
        public Object obtain(final Object o) {
            return AccessibilityWindowInfoCompatApi21.obtain(o);
        }
        
        @Override
        public void recycle(final Object o) {
            AccessibilityWindowInfoCompatApi21.recycle(o);
        }
    }
    
    private static class AccessibilityWindowInfoApi24Impl extends AccessibilityWindowInfoApi21Impl
    {
        @Override
        public Object getAnchor(final Object o) {
            return AccessibilityWindowInfoCompatApi24.getAnchor(o);
        }
        
        @Override
        public CharSequence getTitle(final Object o) {
            return AccessibilityWindowInfoCompatApi24.getTitle(o);
        }
    }
    
    private interface AccessibilityWindowInfoImpl
    {
        Object getAnchor(final Object p0);
        
        void getBoundsInScreen(final Object p0, final Rect p1);
        
        Object getChild(final Object p0, final int p1);
        
        int getChildCount(final Object p0);
        
        int getId(final Object p0);
        
        int getLayer(final Object p0);
        
        Object getParent(final Object p0);
        
        Object getRoot(final Object p0);
        
        CharSequence getTitle(final Object p0);
        
        int getType(final Object p0);
        
        boolean isAccessibilityFocused(final Object p0);
        
        boolean isActive(final Object p0);
        
        boolean isFocused(final Object p0);
        
        Object obtain();
        
        Object obtain(final Object p0);
        
        void recycle(final Object p0);
    }
    
    private static class AccessibilityWindowInfoStubImpl implements AccessibilityWindowInfoImpl
    {
        @Override
        public Object getAnchor(final Object o) {
            return null;
        }
        
        @Override
        public void getBoundsInScreen(final Object o, final Rect rect) {
        }
        
        @Override
        public Object getChild(final Object o, final int n) {
            return null;
        }
        
        @Override
        public int getChildCount(final Object o) {
            return 0;
        }
        
        @Override
        public int getId(final Object o) {
            return -1;
        }
        
        @Override
        public int getLayer(final Object o) {
            return -1;
        }
        
        @Override
        public Object getParent(final Object o) {
            return null;
        }
        
        @Override
        public Object getRoot(final Object o) {
            return null;
        }
        
        @Override
        public CharSequence getTitle(final Object o) {
            return null;
        }
        
        @Override
        public int getType(final Object o) {
            return -1;
        }
        
        @Override
        public boolean isAccessibilityFocused(final Object o) {
            return true;
        }
        
        @Override
        public boolean isActive(final Object o) {
            return true;
        }
        
        @Override
        public boolean isFocused(final Object o) {
            return true;
        }
        
        @Override
        public Object obtain() {
            return null;
        }
        
        @Override
        public Object obtain(final Object o) {
            return null;
        }
        
        @Override
        public void recycle(final Object o) {
        }
    }
}
