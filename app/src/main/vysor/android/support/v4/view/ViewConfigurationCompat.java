// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewConfiguration;
import android.os.Build$VERSION;

public final class ViewConfigurationCompat
{
    static final ViewConfigurationVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (ViewConfigurationVersionImpl)new IcsViewConfigurationVersionImpl();
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            IMPL = (ViewConfigurationVersionImpl)new HoneycombViewConfigurationVersionImpl();
        }
        else {
            IMPL = (ViewConfigurationVersionImpl)new BaseViewConfigurationVersionImpl();
        }
    }
    
    @Deprecated
    public static int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledPagingTouchSlop();
    }
    
    public static boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
        return ViewConfigurationCompat.IMPL.hasPermanentMenuKey(viewConfiguration);
    }
    
    static class BaseViewConfigurationVersionImpl implements ViewConfigurationVersionImpl
    {
        @Override
        public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
            return true;
        }
    }
    
    static class HoneycombViewConfigurationVersionImpl extends BaseViewConfigurationVersionImpl
    {
        @Override
        public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
            return false;
        }
    }
    
    static class IcsViewConfigurationVersionImpl extends HoneycombViewConfigurationVersionImpl
    {
        @Override
        public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
            return ViewConfigurationCompatICS.hasPermanentMenuKey(viewConfiguration);
        }
    }
    
    interface ViewConfigurationVersionImpl
    {
        boolean hasPermanentMenuKey(final ViewConfiguration p0);
    }
}
