// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.widget.ListPopupWindow;
import android.view.View$OnTouchListener;
import android.view.View;

class ListPopupWindowCompatKitKat
{
    public static View$OnTouchListener createDragToOpenListener(final Object o, final View view) {
        return ((ListPopupWindow)o).createDragToOpenListener(view);
    }
}
