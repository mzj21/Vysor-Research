// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build$VERSION;

public class WindowInsetsCompat
{
    private static final WindowInsetsCompatImpl IMPL;
    private final Object mInsets;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = (WindowInsetsCompatImpl)new WindowInsetsCompatApi21Impl();
        }
        else if (sdk_INT >= 20) {
            IMPL = (WindowInsetsCompatImpl)new WindowInsetsCompatApi20Impl();
        }
        else {
            IMPL = (WindowInsetsCompatImpl)new WindowInsetsCompatBaseImpl();
        }
    }
    
    public WindowInsetsCompat(final WindowInsetsCompat windowInsetsCompat) {
        Object sourceWindowInsets;
        if (windowInsetsCompat == null) {
            sourceWindowInsets = null;
        }
        else {
            sourceWindowInsets = WindowInsetsCompat.IMPL.getSourceWindowInsets(windowInsetsCompat.mInsets);
        }
        this.mInsets = sourceWindowInsets;
    }
    
    WindowInsetsCompat(final Object mInsets) {
        this.mInsets = mInsets;
    }
    
    static Object unwrap(final WindowInsetsCompat windowInsetsCompat) {
        Object mInsets;
        if (windowInsetsCompat == null) {
            mInsets = null;
        }
        else {
            mInsets = windowInsetsCompat.mInsets;
        }
        return mInsets;
    }
    
    static WindowInsetsCompat wrap(final Object o) {
        WindowInsetsCompat windowInsetsCompat;
        if (o == null) {
            windowInsetsCompat = null;
        }
        else {
            windowInsetsCompat = new WindowInsetsCompat(o);
        }
        return windowInsetsCompat;
    }
    
    public WindowInsetsCompat consumeStableInsets() {
        return WindowInsetsCompat.IMPL.consumeStableInsets(this.mInsets);
    }
    
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return WindowInsetsCompat.IMPL.consumeSystemWindowInsets(this.mInsets);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean equals = true;
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                equals = false;
            }
            else {
                final WindowInsetsCompat windowInsetsCompat = (WindowInsetsCompat)o;
                if (this.mInsets == null) {
                    if (windowInsetsCompat.mInsets != null) {
                        equals = false;
                    }
                }
                else {
                    equals = this.mInsets.equals(windowInsetsCompat.mInsets);
                }
            }
        }
        return equals;
    }
    
    public int getStableInsetBottom() {
        return WindowInsetsCompat.IMPL.getStableInsetBottom(this.mInsets);
    }
    
    public int getStableInsetLeft() {
        return WindowInsetsCompat.IMPL.getStableInsetLeft(this.mInsets);
    }
    
    public int getStableInsetRight() {
        return WindowInsetsCompat.IMPL.getStableInsetRight(this.mInsets);
    }
    
    public int getStableInsetTop() {
        return WindowInsetsCompat.IMPL.getStableInsetTop(this.mInsets);
    }
    
    public int getSystemWindowInsetBottom() {
        return WindowInsetsCompat.IMPL.getSystemWindowInsetBottom(this.mInsets);
    }
    
    public int getSystemWindowInsetLeft() {
        return WindowInsetsCompat.IMPL.getSystemWindowInsetLeft(this.mInsets);
    }
    
    public int getSystemWindowInsetRight() {
        return WindowInsetsCompat.IMPL.getSystemWindowInsetRight(this.mInsets);
    }
    
    public int getSystemWindowInsetTop() {
        return WindowInsetsCompat.IMPL.getSystemWindowInsetTop(this.mInsets);
    }
    
    public boolean hasInsets() {
        return WindowInsetsCompat.IMPL.hasInsets(this.mInsets);
    }
    
    public boolean hasStableInsets() {
        return WindowInsetsCompat.IMPL.hasStableInsets(this.mInsets);
    }
    
    public boolean hasSystemWindowInsets() {
        return WindowInsetsCompat.IMPL.hasSystemWindowInsets(this.mInsets);
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.mInsets == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mInsets.hashCode();
        }
        return hashCode;
    }
    
    public boolean isConsumed() {
        return WindowInsetsCompat.IMPL.isConsumed(this.mInsets);
    }
    
    public boolean isRound() {
        return WindowInsetsCompat.IMPL.isRound(this.mInsets);
    }
    
    public WindowInsetsCompat replaceSystemWindowInsets(final int n, final int n2, final int n3, final int n4) {
        return WindowInsetsCompat.IMPL.replaceSystemWindowInsets(this.mInsets, n, n2, n3, n4);
    }
    
    public WindowInsetsCompat replaceSystemWindowInsets(final Rect rect) {
        return WindowInsetsCompat.IMPL.replaceSystemWindowInsets(this.mInsets, rect);
    }
    
    private static class WindowInsetsCompatApi20Impl extends WindowInsetsCompatBaseImpl
    {
        @Override
        public WindowInsetsCompat consumeSystemWindowInsets(final Object o) {
            return new WindowInsetsCompat(WindowInsetsCompatApi20.consumeSystemWindowInsets(o));
        }
        
        @Override
        public Object getSourceWindowInsets(final Object o) {
            return WindowInsetsCompatApi20.getSourceWindowInsets(o);
        }
        
        @Override
        public int getSystemWindowInsetBottom(final Object o) {
            return WindowInsetsCompatApi20.getSystemWindowInsetBottom(o);
        }
        
        @Override
        public int getSystemWindowInsetLeft(final Object o) {
            return WindowInsetsCompatApi20.getSystemWindowInsetLeft(o);
        }
        
        @Override
        public int getSystemWindowInsetRight(final Object o) {
            return WindowInsetsCompatApi20.getSystemWindowInsetRight(o);
        }
        
        @Override
        public int getSystemWindowInsetTop(final Object o) {
            return WindowInsetsCompatApi20.getSystemWindowInsetTop(o);
        }
        
        @Override
        public boolean hasInsets(final Object o) {
            return WindowInsetsCompatApi20.hasInsets(o);
        }
        
        @Override
        public boolean hasSystemWindowInsets(final Object o) {
            return WindowInsetsCompatApi20.hasSystemWindowInsets(o);
        }
        
        @Override
        public boolean isRound(final Object o) {
            return WindowInsetsCompatApi20.isRound(o);
        }
        
        @Override
        public WindowInsetsCompat replaceSystemWindowInsets(final Object o, final int n, final int n2, final int n3, final int n4) {
            return new WindowInsetsCompat(WindowInsetsCompatApi20.replaceSystemWindowInsets(o, n, n2, n3, n4));
        }
    }
    
    private static class WindowInsetsCompatApi21Impl extends WindowInsetsCompatApi20Impl
    {
        @Override
        public WindowInsetsCompat consumeStableInsets(final Object o) {
            return new WindowInsetsCompat(WindowInsetsCompatApi21.consumeStableInsets(o));
        }
        
        @Override
        public int getStableInsetBottom(final Object o) {
            return WindowInsetsCompatApi21.getStableInsetBottom(o);
        }
        
        @Override
        public int getStableInsetLeft(final Object o) {
            return WindowInsetsCompatApi21.getStableInsetLeft(o);
        }
        
        @Override
        public int getStableInsetRight(final Object o) {
            return WindowInsetsCompatApi21.getStableInsetRight(o);
        }
        
        @Override
        public int getStableInsetTop(final Object o) {
            return WindowInsetsCompatApi21.getStableInsetTop(o);
        }
        
        @Override
        public boolean hasStableInsets(final Object o) {
            return WindowInsetsCompatApi21.hasStableInsets(o);
        }
        
        @Override
        public boolean isConsumed(final Object o) {
            return WindowInsetsCompatApi21.isConsumed(o);
        }
        
        @Override
        public WindowInsetsCompat replaceSystemWindowInsets(final Object o, final Rect rect) {
            return new WindowInsetsCompat(WindowInsetsCompatApi21.replaceSystemWindowInsets(o, rect));
        }
    }
    
    private static class WindowInsetsCompatBaseImpl implements WindowInsetsCompatImpl
    {
        @Override
        public WindowInsetsCompat consumeStableInsets(final Object o) {
            return null;
        }
        
        @Override
        public WindowInsetsCompat consumeSystemWindowInsets(final Object o) {
            return null;
        }
        
        @Override
        public Object getSourceWindowInsets(final Object o) {
            return null;
        }
        
        @Override
        public int getStableInsetBottom(final Object o) {
            return 0;
        }
        
        @Override
        public int getStableInsetLeft(final Object o) {
            return 0;
        }
        
        @Override
        public int getStableInsetRight(final Object o) {
            return 0;
        }
        
        @Override
        public int getStableInsetTop(final Object o) {
            return 0;
        }
        
        @Override
        public int getSystemWindowInsetBottom(final Object o) {
            return 0;
        }
        
        @Override
        public int getSystemWindowInsetLeft(final Object o) {
            return 0;
        }
        
        @Override
        public int getSystemWindowInsetRight(final Object o) {
            return 0;
        }
        
        @Override
        public int getSystemWindowInsetTop(final Object o) {
            return 0;
        }
        
        @Override
        public boolean hasInsets(final Object o) {
            return false;
        }
        
        @Override
        public boolean hasStableInsets(final Object o) {
            return false;
        }
        
        @Override
        public boolean hasSystemWindowInsets(final Object o) {
            return false;
        }
        
        @Override
        public boolean isConsumed(final Object o) {
            return false;
        }
        
        @Override
        public boolean isRound(final Object o) {
            return false;
        }
        
        @Override
        public WindowInsetsCompat replaceSystemWindowInsets(final Object o, final int n, final int n2, final int n3, final int n4) {
            return null;
        }
        
        @Override
        public WindowInsetsCompat replaceSystemWindowInsets(final Object o, final Rect rect) {
            return null;
        }
    }
    
    private interface WindowInsetsCompatImpl
    {
        WindowInsetsCompat consumeStableInsets(final Object p0);
        
        WindowInsetsCompat consumeSystemWindowInsets(final Object p0);
        
        Object getSourceWindowInsets(final Object p0);
        
        int getStableInsetBottom(final Object p0);
        
        int getStableInsetLeft(final Object p0);
        
        int getStableInsetRight(final Object p0);
        
        int getStableInsetTop(final Object p0);
        
        int getSystemWindowInsetBottom(final Object p0);
        
        int getSystemWindowInsetLeft(final Object p0);
        
        int getSystemWindowInsetRight(final Object p0);
        
        int getSystemWindowInsetTop(final Object p0);
        
        boolean hasInsets(final Object p0);
        
        boolean hasStableInsets(final Object p0);
        
        boolean hasSystemWindowInsets(final Object p0);
        
        boolean isConsumed(final Object p0);
        
        boolean isRound(final Object p0);
        
        WindowInsetsCompat replaceSystemWindowInsets(final Object p0, final int p1, final int p2, final int p3, final int p4);
        
        WindowInsetsCompat replaceSystemWindowInsets(final Object p0, final Rect p1);
    }
}
