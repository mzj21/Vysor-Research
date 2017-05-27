// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.View$OnTouchListener;
import android.os.Build$VERSION;

public final class PopupMenuCompat
{
    static final PopupMenuImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (PopupMenuImpl)new KitKatPopupMenuImpl();
        }
        else {
            IMPL = (PopupMenuImpl)new BasePopupMenuImpl();
        }
    }
    
    public static View$OnTouchListener getDragToOpenListener(final Object o) {
        return PopupMenuCompat.IMPL.getDragToOpenListener(o);
    }
    
    static class BasePopupMenuImpl implements PopupMenuImpl
    {
        @Override
        public View$OnTouchListener getDragToOpenListener(final Object o) {
            return null;
        }
    }
    
    static class KitKatPopupMenuImpl extends BasePopupMenuImpl
    {
        @Override
        public View$OnTouchListener getDragToOpenListener(final Object o) {
            return PopupMenuCompatKitKat.getDragToOpenListener(o);
        }
    }
    
    interface PopupMenuImpl
    {
        View$OnTouchListener getDragToOpenListener(final Object p0);
    }
}
