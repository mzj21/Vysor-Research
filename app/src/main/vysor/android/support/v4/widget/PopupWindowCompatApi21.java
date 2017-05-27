// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

class PopupWindowCompatApi21
{
    private static final String TAG = "PopupWindowCompatApi21";
    private static Field sOverlapAnchorField;
    
    static {
        try {
            (PopupWindowCompatApi21.sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor")).setAccessible(true);
        }
        catch (NoSuchFieldException ex) {
            Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", (Throwable)ex);
        }
    }
    
    static boolean getOverlapAnchor(final PopupWindow popupWindow) {
        if (PopupWindowCompatApi21.sOverlapAnchorField == null) {
            return false;
        }
        try {
            return (boolean)PopupWindowCompatApi21.sOverlapAnchorField.get(popupWindow);
        }
        catch (IllegalAccessException ex) {
            Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", (Throwable)ex);
        }
        return false;
    }
    
    static void setOverlapAnchor(final PopupWindow popupWindow, final boolean b) {
        if (PopupWindowCompatApi21.sOverlapAnchorField == null) {
            return;
        }
        try {
            PopupWindowCompatApi21.sOverlapAnchorField.set(popupWindow, b);
        }
        catch (IllegalAccessException ex) {
            Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", (Throwable)ex);
        }
    }
}
