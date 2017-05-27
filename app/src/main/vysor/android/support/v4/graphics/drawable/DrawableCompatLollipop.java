// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.drawable.DrawableContainer$DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.content.res.Resources$Theme;
import android.graphics.drawable.Drawable;

class DrawableCompatLollipop
{
    public static void applyTheme(final Drawable drawable, final Resources$Theme resources$Theme) {
        drawable.applyTheme(resources$Theme);
    }
    
    public static boolean canApplyTheme(final Drawable drawable) {
        return drawable.canApplyTheme();
    }
    
    public static void clearColorFilter(final Drawable drawable) {
        drawable.clearColorFilter();
        if (drawable instanceof InsetDrawable) {
            clearColorFilter(((InsetDrawable)drawable).getDrawable());
        }
        else if (drawable instanceof DrawableWrapper) {
            clearColorFilter(((DrawableWrapper)drawable).getWrappedDrawable());
        }
        else if (drawable instanceof DrawableContainer) {
            final DrawableContainer$DrawableContainerState drawableContainer$DrawableContainerState = (DrawableContainer$DrawableContainerState)((DrawableContainer)drawable).getConstantState();
            if (drawableContainer$DrawableContainerState != null) {
                for (int i = 0; i < drawableContainer$DrawableContainerState.getChildCount(); ++i) {
                    final Drawable child = drawableContainer$DrawableContainerState.getChild(i);
                    if (child != null) {
                        clearColorFilter(child);
                    }
                }
            }
        }
    }
    
    public static ColorFilter getColorFilter(final Drawable drawable) {
        return drawable.getColorFilter();
    }
    
    public static void inflate(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) throws IOException, XmlPullParserException {
        drawable.inflate(resources, xmlPullParser, set, resources$Theme);
    }
    
    public static void setHotspot(final Drawable drawable, final float n, final float n2) {
        drawable.setHotspot(n, n2);
    }
    
    public static void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        drawable.setHotspotBounds(n, n2, n3, n4);
    }
    
    public static void setTint(final Drawable drawable, final int tint) {
        drawable.setTint(tint);
    }
    
    public static void setTintList(final Drawable drawable, final ColorStateList tintList) {
        drawable.setTintList(tintList);
    }
    
    public static void setTintMode(final Drawable drawable, final PorterDuff$Mode tintMode) {
        drawable.setTintMode(tintMode);
    }
    
    public static Drawable wrapForTinting(Drawable drawable) {
        if (!(drawable instanceof TintAwareDrawable)) {
            drawable = new DrawableWrapperLollipop(drawable);
        }
        return drawable;
    }
}
