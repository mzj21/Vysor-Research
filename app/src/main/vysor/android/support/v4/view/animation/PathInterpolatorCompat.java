// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.animation;

import android.graphics.Path;
import android.os.Build$VERSION;
import android.view.animation.Interpolator;

public final class PathInterpolatorCompat
{
    public static Interpolator create(final float n, final float n2) {
        Interpolator interpolator;
        if (Build$VERSION.SDK_INT >= 21) {
            interpolator = PathInterpolatorCompatApi21.create(n, n2);
        }
        else {
            interpolator = PathInterpolatorCompatBase.create(n, n2);
        }
        return interpolator;
    }
    
    public static Interpolator create(final float n, final float n2, final float n3, final float n4) {
        Interpolator interpolator;
        if (Build$VERSION.SDK_INT >= 21) {
            interpolator = PathInterpolatorCompatApi21.create(n, n2, n3, n4);
        }
        else {
            interpolator = PathInterpolatorCompatBase.create(n, n2, n3, n4);
        }
        return interpolator;
    }
    
    public static Interpolator create(final Path path) {
        Interpolator interpolator;
        if (Build$VERSION.SDK_INT >= 21) {
            interpolator = PathInterpolatorCompatApi21.create(path);
        }
        else {
            interpolator = PathInterpolatorCompatBase.create(path);
        }
        return interpolator;
    }
}
