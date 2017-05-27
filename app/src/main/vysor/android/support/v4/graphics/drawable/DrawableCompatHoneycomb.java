// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompatHoneycomb
{
    public static void jumpToCurrentState(final Drawable drawable) {
        drawable.jumpToCurrentState();
    }
    
    public static Drawable wrapForTinting(Drawable drawable) {
        if (!(drawable instanceof TintAwareDrawable)) {
            drawable = new DrawableWrapperHoneycomb(drawable);
        }
        return drawable;
    }
}
