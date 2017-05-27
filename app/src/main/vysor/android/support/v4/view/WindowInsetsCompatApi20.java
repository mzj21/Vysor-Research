// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.WindowInsets;

class WindowInsetsCompatApi20
{
    public static Object consumeSystemWindowInsets(final Object o) {
        return ((WindowInsets)o).consumeSystemWindowInsets();
    }
    
    public static Object getSourceWindowInsets(final Object o) {
        return new WindowInsets((WindowInsets)o);
    }
    
    public static int getSystemWindowInsetBottom(final Object o) {
        return ((WindowInsets)o).getSystemWindowInsetBottom();
    }
    
    public static int getSystemWindowInsetLeft(final Object o) {
        return ((WindowInsets)o).getSystemWindowInsetLeft();
    }
    
    public static int getSystemWindowInsetRight(final Object o) {
        return ((WindowInsets)o).getSystemWindowInsetRight();
    }
    
    public static int getSystemWindowInsetTop(final Object o) {
        return ((WindowInsets)o).getSystemWindowInsetTop();
    }
    
    public static boolean hasInsets(final Object o) {
        return ((WindowInsets)o).hasInsets();
    }
    
    public static boolean hasSystemWindowInsets(final Object o) {
        return ((WindowInsets)o).hasSystemWindowInsets();
    }
    
    public static boolean isRound(final Object o) {
        return ((WindowInsets)o).isRound();
    }
    
    public static Object replaceSystemWindowInsets(final Object o, final int n, final int n2, final int n3, final int n4) {
        return ((WindowInsets)o).replaceSystemWindowInsets(n, n2, n3, n4);
    }
}
