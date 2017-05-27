// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.Context;
import android.widget.EdgeEffect;
import android.graphics.Canvas;

class EdgeEffectCompatIcs
{
    public static boolean draw(final Object o, final Canvas canvas) {
        return ((EdgeEffect)o).draw(canvas);
    }
    
    public static void finish(final Object o) {
        ((EdgeEffect)o).finish();
    }
    
    public static boolean isFinished(final Object o) {
        return ((EdgeEffect)o).isFinished();
    }
    
    public static Object newEdgeEffect(final Context context) {
        return new EdgeEffect(context);
    }
    
    public static boolean onAbsorb(final Object o, final int n) {
        ((EdgeEffect)o).onAbsorb(n);
        return true;
    }
    
    public static boolean onPull(final Object o, final float n) {
        ((EdgeEffect)o).onPull(n);
        return true;
    }
    
    public static boolean onRelease(final Object o) {
        final EdgeEffect edgeEffect = (EdgeEffect)o;
        edgeEffect.onRelease();
        return edgeEffect.isFinished();
    }
    
    public static void setSize(final Object o, final int n, final int n2) {
        ((EdgeEffect)o).setSize(n, n2);
    }
}
