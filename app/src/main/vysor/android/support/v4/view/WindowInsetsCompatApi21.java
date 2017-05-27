// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.WindowInsets;

class WindowInsetsCompatApi21
{
    public static Object consumeStableInsets(final Object o) {
        return ((WindowInsets)o).consumeStableInsets();
    }
    
    public static int getStableInsetBottom(final Object o) {
        return ((WindowInsets)o).getStableInsetBottom();
    }
    
    public static int getStableInsetLeft(final Object o) {
        return ((WindowInsets)o).getStableInsetLeft();
    }
    
    public static int getStableInsetRight(final Object o) {
        return ((WindowInsets)o).getStableInsetRight();
    }
    
    public static int getStableInsetTop(final Object o) {
        return ((WindowInsets)o).getStableInsetTop();
    }
    
    public static boolean hasStableInsets(final Object o) {
        return ((WindowInsets)o).hasStableInsets();
    }
    
    public static boolean isConsumed(final Object o) {
        return ((WindowInsets)o).isConsumed();
    }
    
    public static Object replaceSystemWindowInsets(final Object o, final Rect rect) {
        return ((WindowInsets)o).replaceSystemWindowInsets(rect);
    }
}
