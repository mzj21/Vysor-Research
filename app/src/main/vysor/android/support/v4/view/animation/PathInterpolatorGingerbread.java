// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.animation;

import android.graphics.PathMeasure;
import android.graphics.Path;
import android.view.animation.Interpolator;

class PathInterpolatorGingerbread implements Interpolator
{
    private static final float PRECISION = 0.002f;
    private final float[] mX;
    private final float[] mY;
    
    public PathInterpolatorGingerbread(final float n, final float n2) {
        this(createQuad(n, n2));
    }
    
    public PathInterpolatorGingerbread(final float n, final float n2, final float n3, final float n4) {
        this(createCubic(n, n2, n3, n4));
    }
    
    public PathInterpolatorGingerbread(final Path path) {
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        final float length = pathMeasure.getLength();
        final int n = 1 + (int)(length / 0.002f);
        this.mX = new float[n];
        this.mY = new float[n];
        final float[] array = new float[2];
        for (int i = 0; i < n; ++i) {
            pathMeasure.getPosTan(length * i / (n - 1), array, (float[])null);
            this.mX[i] = array[0];
            this.mY[i] = array[1];
        }
    }
    
    private static Path createCubic(final float n, final float n2, final float n3, final float n4) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(n, n2, n3, n4, 1.0f, 1.0f);
        return path;
    }
    
    private static Path createQuad(final float n, final float n2) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(n, n2, 1.0f, 1.0f);
        return path;
    }
    
    public float getInterpolation(final float n) {
        final float n2 = fcmpg(n, 0.0f);
        float n3 = 0.0f;
        if (n2 > 0) {
            if (n >= 1.0f) {
                n3 = 1.0f;
            }
            else {
                int n4 = 0;
                int n5 = -1 + this.mX.length;
                while (n5 - n4 > 1) {
                    final int n6 = (n4 + n5) / 2;
                    if (n < this.mX[n6]) {
                        n5 = n6;
                    }
                    else {
                        n4 = n6;
                    }
                }
                final float n7 = this.mX[n5] - this.mX[n4];
                if (n7 == 0.0f) {
                    n3 = this.mY[n4];
                }
                else {
                    final float n8 = (n - this.mX[n4]) / n7;
                    final float n9 = this.mY[n4];
                    n3 = n9 + n8 * (this.mY[n5] - n9);
                }
            }
        }
        return n3;
    }
}
