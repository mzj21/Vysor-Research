// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.animation;

import android.graphics.Path;
import android.view.animation.PathInterpolator;
import android.view.animation.Interpolator;

class PathInterpolatorCompatApi21
{
    public static Interpolator create(final float n, final float n2) {
        return (Interpolator)new PathInterpolator(n, n2);
    }
    
    public static Interpolator create(final float n, final float n2, final float n3, final float n4) {
        return (Interpolator)new PathInterpolator(n, n2, n3, n4);
    }
    
    public static Interpolator create(final Path path) {
        return (Interpolator)new PathInterpolator(path);
    }
}
