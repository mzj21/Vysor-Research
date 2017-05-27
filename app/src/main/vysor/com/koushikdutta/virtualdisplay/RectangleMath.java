// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.virtualdisplay;

import android.graphics.Rect;

public class RectangleMath
{
    public static Rect centerInside(final int n, final int n2, final int n3, final int n4) {
        final float min = Math.min(n3 / n, n4 / n2);
        Rect rect;
        if (min == 0.0f) {
            rect = null;
        }
        else {
            final float n5 = min * n;
            final float n6 = min * n2;
            final float n7 = (n3 - n5) / 2.0f;
            final float n8 = (n4 - n6) / 2.0f;
            rect = new Rect((int)n7, (int)n8, (int)(n3 - n7), (int)(n4 - n8));
        }
        return rect;
    }
}
