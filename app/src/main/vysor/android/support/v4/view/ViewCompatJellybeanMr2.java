// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.View;

class ViewCompatJellybeanMr2
{
    public static Rect getClipBounds(final View view) {
        return view.getClipBounds();
    }
    
    public static boolean isInLayout(final View view) {
        return view.isInLayout();
    }
    
    public static void setClipBounds(final View view, final Rect clipBounds) {
        view.setClipBounds(clipBounds);
    }
}
