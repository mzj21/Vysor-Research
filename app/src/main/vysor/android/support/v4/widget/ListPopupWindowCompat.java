// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View$OnTouchListener;
import android.view.View;
import android.os.Build$VERSION;

public final class ListPopupWindowCompat
{
    static final ListPopupWindowImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (ListPopupWindowImpl)new KitKatListPopupWindowImpl();
        }
        else {
            IMPL = (ListPopupWindowImpl)new BaseListPopupWindowImpl();
        }
    }
    
    public static View$OnTouchListener createDragToOpenListener(final Object o, final View view) {
        return ListPopupWindowCompat.IMPL.createDragToOpenListener(o, view);
    }
    
    static class BaseListPopupWindowImpl implements ListPopupWindowImpl
    {
        @Override
        public View$OnTouchListener createDragToOpenListener(final Object o, final View view) {
            return null;
        }
    }
    
    static class KitKatListPopupWindowImpl extends BaseListPopupWindowImpl
    {
        @Override
        public View$OnTouchListener createDragToOpenListener(final Object o, final View view) {
            return ListPopupWindowCompatKitKat.createDragToOpenListener(o, view);
        }
    }
    
    interface ListPopupWindowImpl
    {
        View$OnTouchListener createDragToOpenListener(final Object p0, final View p1);
    }
}
