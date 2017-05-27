// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.animation.Interpolator;
import android.view.View;

class ViewPropertyAnimatorCompatJellybeanMr2
{
    public static Interpolator getInterpolator(final View view) {
        return (Interpolator)view.animate().getInterpolator();
    }
}
