// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.Gravity;
import android.graphics.Rect;

class GravityCompatJellybeanMr1
{
    public static void apply(final int n, final int n2, final int n3, final Rect rect, final int n4, final int n5, final Rect rect2, final int n6) {
        Gravity.apply(n, n2, n3, rect, n4, n5, rect2, n6);
    }
    
    public static void apply(final int n, final int n2, final int n3, final Rect rect, final Rect rect2, final int n4) {
        Gravity.apply(n, n2, n3, rect, rect2, n4);
    }
    
    public static void applyDisplay(final int n, final Rect rect, final Rect rect2, final int n2) {
        Gravity.applyDisplay(n, rect, rect2, n2);
    }
    
    public static int getAbsoluteGravity(final int n, final int n2) {
        return Gravity.getAbsoluteGravity(n, n2);
    }
}
