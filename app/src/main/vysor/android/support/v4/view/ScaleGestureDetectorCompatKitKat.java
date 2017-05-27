// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ScaleGestureDetector;

class ScaleGestureDetectorCompatKitKat
{
    public static boolean isQuickScaleEnabled(final Object o) {
        return ((ScaleGestureDetector)o).isQuickScaleEnabled();
    }
    
    public static void setQuickScaleEnabled(final Object o, final boolean quickScaleEnabled) {
        ((ScaleGestureDetector)o).setQuickScaleEnabled(quickScaleEnabled);
    }
}
