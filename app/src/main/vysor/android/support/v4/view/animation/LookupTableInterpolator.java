// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.animation;

import android.view.animation.Interpolator;

abstract class LookupTableInterpolator implements Interpolator
{
    private final float mStepSize;
    private final float[] mValues;
    
    public LookupTableInterpolator(final float[] mValues) {
        this.mValues = mValues;
        this.mStepSize = 1.0f / (-1 + this.mValues.length);
    }
    
    public float getInterpolation(final float n) {
        float n2 = 1.0f;
        if (n < n2) {
            if (n <= 0.0f) {
                n2 = 0.0f;
            }
            else {
                final int min = Math.min((int)(n * (-1 + this.mValues.length)), -2 + this.mValues.length);
                n2 = this.mValues[min] + (n - min * this.mStepSize) / this.mStepSize * (this.mValues[min + 1] - this.mValues[min]);
            }
        }
        return n2;
    }
}
