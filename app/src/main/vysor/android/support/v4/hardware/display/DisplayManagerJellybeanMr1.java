// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;

final class DisplayManagerJellybeanMr1
{
    public static Display getDisplay(final Object o, final int n) {
        return ((DisplayManager)o).getDisplay(n);
    }
    
    public static Object getDisplayManager(final Context context) {
        return context.getSystemService("display");
    }
    
    public static Display[] getDisplays(final Object o) {
        return ((DisplayManager)o).getDisplays();
    }
    
    public static Display[] getDisplays(final Object o, final String s) {
        return ((DisplayManager)o).getDisplays(s);
    }
}
