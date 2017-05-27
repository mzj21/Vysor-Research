// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import android.content.res.Resources$Theme;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class DrawableCompatBase
{
    public static void inflate(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) throws IOException, XmlPullParserException {
        drawable.inflate(resources, xmlPullParser, set);
    }
    
    public static void setTint(final Drawable drawable, final int tint) {
        if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable).setTint(tint);
        }
    }
    
    public static void setTintList(final Drawable drawable, final ColorStateList tintList) {
        if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable).setTintList(tintList);
        }
    }
    
    public static void setTintMode(final Drawable drawable, final PorterDuff$Mode tintMode) {
        if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable).setTintMode(tintMode);
        }
    }
    
    public static Drawable wrapForTinting(Drawable drawable) {
        if (!(drawable instanceof TintAwareDrawable)) {
            drawable = new DrawableWrapperGingerbread(drawable);
        }
        return drawable;
    }
}
