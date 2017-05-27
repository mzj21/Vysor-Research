// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics.drawable;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.content.res.Resources$Theme;
import android.support.annotation.NonNull;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;

public final class DrawableCompat
{
    static final DrawableImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 23) {
            IMPL = (DrawableImpl)new MDrawableImpl();
        }
        else if (sdk_INT >= 21) {
            IMPL = (DrawableImpl)new LollipopDrawableImpl();
        }
        else if (sdk_INT >= 19) {
            IMPL = (DrawableImpl)new KitKatDrawableImpl();
        }
        else if (sdk_INT >= 17) {
            IMPL = (DrawableImpl)new JellybeanMr1DrawableImpl();
        }
        else if (sdk_INT >= 11) {
            IMPL = (DrawableImpl)new HoneycombDrawableImpl();
        }
        else {
            IMPL = (DrawableImpl)new BaseDrawableImpl();
        }
    }
    
    public static void applyTheme(@NonNull final Drawable drawable, @NonNull final Resources$Theme resources$Theme) {
        DrawableCompat.IMPL.applyTheme(drawable, resources$Theme);
    }
    
    public static boolean canApplyTheme(@NonNull final Drawable drawable) {
        return DrawableCompat.IMPL.canApplyTheme(drawable);
    }
    
    public static void clearColorFilter(@NonNull final Drawable drawable) {
        DrawableCompat.IMPL.clearColorFilter(drawable);
    }
    
    public static int getAlpha(@NonNull final Drawable drawable) {
        return DrawableCompat.IMPL.getAlpha(drawable);
    }
    
    public static ColorFilter getColorFilter(@NonNull final Drawable drawable) {
        return DrawableCompat.IMPL.getColorFilter(drawable);
    }
    
    public static int getLayoutDirection(@NonNull final Drawable drawable) {
        return DrawableCompat.IMPL.getLayoutDirection(drawable);
    }
    
    public static void inflate(@NonNull final Drawable drawable, @NonNull final Resources resources, @NonNull final XmlPullParser xmlPullParser, @NonNull final AttributeSet set, @Nullable final Resources$Theme resources$Theme) throws XmlPullParserException, IOException {
        DrawableCompat.IMPL.inflate(drawable, resources, xmlPullParser, set, resources$Theme);
    }
    
    public static boolean isAutoMirrored(@NonNull final Drawable drawable) {
        return DrawableCompat.IMPL.isAutoMirrored(drawable);
    }
    
    public static void jumpToCurrentState(@NonNull final Drawable drawable) {
        DrawableCompat.IMPL.jumpToCurrentState(drawable);
    }
    
    public static void setAutoMirrored(@NonNull final Drawable drawable, final boolean b) {
        DrawableCompat.IMPL.setAutoMirrored(drawable, b);
    }
    
    public static void setHotspot(@NonNull final Drawable drawable, final float n, final float n2) {
        DrawableCompat.IMPL.setHotspot(drawable, n, n2);
    }
    
    public static void setHotspotBounds(@NonNull final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        DrawableCompat.IMPL.setHotspotBounds(drawable, n, n2, n3, n4);
    }
    
    public static boolean setLayoutDirection(@NonNull final Drawable drawable, final int n) {
        return DrawableCompat.IMPL.setLayoutDirection(drawable, n);
    }
    
    public static void setTint(@NonNull final Drawable drawable, @ColorInt final int n) {
        DrawableCompat.IMPL.setTint(drawable, n);
    }
    
    public static void setTintList(@NonNull final Drawable drawable, @Nullable final ColorStateList list) {
        DrawableCompat.IMPL.setTintList(drawable, list);
    }
    
    public static void setTintMode(@NonNull final Drawable drawable, @Nullable final PorterDuff$Mode porterDuff$Mode) {
        DrawableCompat.IMPL.setTintMode(drawable, porterDuff$Mode);
    }
    
    public static <T extends Drawable> T unwrap(@NonNull Drawable wrappedDrawable) {
        if (wrappedDrawable instanceof DrawableWrapper) {
            wrappedDrawable = ((DrawableWrapper)wrappedDrawable).getWrappedDrawable();
        }
        return (T)wrappedDrawable;
    }
    
    public static Drawable wrap(@NonNull final Drawable drawable) {
        return DrawableCompat.IMPL.wrap(drawable);
    }
    
    static class BaseDrawableImpl implements DrawableImpl
    {
        @Override
        public void applyTheme(final Drawable drawable, final Resources$Theme resources$Theme) {
        }
        
        @Override
        public boolean canApplyTheme(final Drawable drawable) {
            return false;
        }
        
        @Override
        public void clearColorFilter(final Drawable drawable) {
            drawable.clearColorFilter();
        }
        
        @Override
        public int getAlpha(final Drawable drawable) {
            return 0;
        }
        
        @Override
        public ColorFilter getColorFilter(final Drawable drawable) {
            return null;
        }
        
        @Override
        public int getLayoutDirection(final Drawable drawable) {
            return 0;
        }
        
        @Override
        public void inflate(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) throws IOException, XmlPullParserException {
            DrawableCompatBase.inflate(drawable, resources, xmlPullParser, set, resources$Theme);
        }
        
        @Override
        public boolean isAutoMirrored(final Drawable drawable) {
            return false;
        }
        
        @Override
        public void jumpToCurrentState(final Drawable drawable) {
        }
        
        @Override
        public void setAutoMirrored(final Drawable drawable, final boolean b) {
        }
        
        @Override
        public void setHotspot(final Drawable drawable, final float n, final float n2) {
        }
        
        @Override
        public void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
        }
        
        @Override
        public boolean setLayoutDirection(final Drawable drawable, final int n) {
            return false;
        }
        
        @Override
        public void setTint(final Drawable drawable, final int n) {
            DrawableCompatBase.setTint(drawable, n);
        }
        
        @Override
        public void setTintList(final Drawable drawable, final ColorStateList list) {
            DrawableCompatBase.setTintList(drawable, list);
        }
        
        @Override
        public void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
            DrawableCompatBase.setTintMode(drawable, porterDuff$Mode);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatBase.wrapForTinting(drawable);
        }
    }
    
    interface DrawableImpl
    {
        void applyTheme(final Drawable p0, final Resources$Theme p1);
        
        boolean canApplyTheme(final Drawable p0);
        
        void clearColorFilter(final Drawable p0);
        
        int getAlpha(final Drawable p0);
        
        ColorFilter getColorFilter(final Drawable p0);
        
        int getLayoutDirection(final Drawable p0);
        
        void inflate(final Drawable p0, final Resources p1, final XmlPullParser p2, final AttributeSet p3, final Resources$Theme p4) throws IOException, XmlPullParserException;
        
        boolean isAutoMirrored(final Drawable p0);
        
        void jumpToCurrentState(final Drawable p0);
        
        void setAutoMirrored(final Drawable p0, final boolean p1);
        
        void setHotspot(final Drawable p0, final float p1, final float p2);
        
        void setHotspotBounds(final Drawable p0, final int p1, final int p2, final int p3, final int p4);
        
        boolean setLayoutDirection(final Drawable p0, final int p1);
        
        void setTint(final Drawable p0, final int p1);
        
        void setTintList(final Drawable p0, final ColorStateList p1);
        
        void setTintMode(final Drawable p0, final PorterDuff$Mode p1);
        
        Drawable wrap(final Drawable p0);
    }
    
    static class HoneycombDrawableImpl extends BaseDrawableImpl
    {
        @Override
        public void jumpToCurrentState(final Drawable drawable) {
            DrawableCompatHoneycomb.jumpToCurrentState(drawable);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatHoneycomb.wrapForTinting(drawable);
        }
    }
    
    static class JellybeanMr1DrawableImpl extends HoneycombDrawableImpl
    {
        @Override
        public int getLayoutDirection(final Drawable drawable) {
            int layoutDirection = DrawableCompatJellybeanMr1.getLayoutDirection(drawable);
            if (layoutDirection < 0) {
                layoutDirection = 0;
            }
            return layoutDirection;
        }
        
        @Override
        public boolean setLayoutDirection(final Drawable drawable, final int n) {
            return DrawableCompatJellybeanMr1.setLayoutDirection(drawable, n);
        }
    }
    
    static class KitKatDrawableImpl extends JellybeanMr1DrawableImpl
    {
        @Override
        public int getAlpha(final Drawable drawable) {
            return DrawableCompatKitKat.getAlpha(drawable);
        }
        
        @Override
        public boolean isAutoMirrored(final Drawable drawable) {
            return DrawableCompatKitKat.isAutoMirrored(drawable);
        }
        
        @Override
        public void setAutoMirrored(final Drawable drawable, final boolean b) {
            DrawableCompatKitKat.setAutoMirrored(drawable, b);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatKitKat.wrapForTinting(drawable);
        }
    }
    
    static class LollipopDrawableImpl extends KitKatDrawableImpl
    {
        @Override
        public void applyTheme(final Drawable drawable, final Resources$Theme resources$Theme) {
            DrawableCompatLollipop.applyTheme(drawable, resources$Theme);
        }
        
        @Override
        public boolean canApplyTheme(final Drawable drawable) {
            return DrawableCompatLollipop.canApplyTheme(drawable);
        }
        
        @Override
        public void clearColorFilter(final Drawable drawable) {
            DrawableCompatLollipop.clearColorFilter(drawable);
        }
        
        @Override
        public ColorFilter getColorFilter(final Drawable drawable) {
            return DrawableCompatLollipop.getColorFilter(drawable);
        }
        
        @Override
        public void inflate(final Drawable drawable, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) throws IOException, XmlPullParserException {
            DrawableCompatLollipop.inflate(drawable, resources, xmlPullParser, set, resources$Theme);
        }
        
        @Override
        public void setHotspot(final Drawable drawable, final float n, final float n2) {
            DrawableCompatLollipop.setHotspot(drawable, n, n2);
        }
        
        @Override
        public void setHotspotBounds(final Drawable drawable, final int n, final int n2, final int n3, final int n4) {
            DrawableCompatLollipop.setHotspotBounds(drawable, n, n2, n3, n4);
        }
        
        @Override
        public void setTint(final Drawable drawable, final int n) {
            DrawableCompatLollipop.setTint(drawable, n);
        }
        
        @Override
        public void setTintList(final Drawable drawable, final ColorStateList list) {
            DrawableCompatLollipop.setTintList(drawable, list);
        }
        
        @Override
        public void setTintMode(final Drawable drawable, final PorterDuff$Mode porterDuff$Mode) {
            DrawableCompatLollipop.setTintMode(drawable, porterDuff$Mode);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return DrawableCompatLollipop.wrapForTinting(drawable);
        }
    }
    
    static class MDrawableImpl extends LollipopDrawableImpl
    {
        @Override
        public void clearColorFilter(final Drawable drawable) {
            drawable.clearColorFilter();
        }
        
        @Override
        public int getLayoutDirection(final Drawable drawable) {
            return DrawableCompatApi23.getLayoutDirection(drawable);
        }
        
        @Override
        public boolean setLayoutDirection(final Drawable drawable, final int n) {
            return DrawableCompatApi23.setLayoutDirection(drawable, n);
        }
        
        @Override
        public Drawable wrap(final Drawable drawable) {
            return drawable;
        }
    }
}
