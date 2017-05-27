// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;
import android.content.res.Resources$NotFoundException;
import android.os.Build$VERSION;
import android.support.annotation.Nullable;
import android.content.res.Resources$Theme;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.content.res.Resources;

public final class ResourcesCompat
{
    @ColorInt
    public static int getColor(@NonNull final Resources resources, @ColorRes final int n, @Nullable final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        int n2;
        if (Build$VERSION.SDK_INT >= 23) {
            n2 = ResourcesCompatApi23.getColor(resources, n, resources$Theme);
        }
        else {
            n2 = resources.getColor(n);
        }
        return n2;
    }
    
    @Nullable
    public static ColorStateList getColorStateList(@NonNull final Resources resources, @ColorRes final int n, @Nullable final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        ColorStateList list;
        if (Build$VERSION.SDK_INT >= 23) {
            list = ResourcesCompatApi23.getColorStateList(resources, n, resources$Theme);
        }
        else {
            list = resources.getColorStateList(n);
        }
        return list;
    }
    
    @Nullable
    public static Drawable getDrawable(@NonNull final Resources resources, @DrawableRes final int n, @Nullable final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        Drawable drawable;
        if (Build$VERSION.SDK_INT >= 21) {
            drawable = ResourcesCompatApi21.getDrawable(resources, n, resources$Theme);
        }
        else {
            drawable = resources.getDrawable(n);
        }
        return drawable;
    }
    
    @Nullable
    public static Drawable getDrawableForDensity(@NonNull final Resources resources, @DrawableRes final int n, final int n2, @Nullable final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        Drawable drawable;
        if (Build$VERSION.SDK_INT >= 21) {
            drawable = ResourcesCompatApi21.getDrawableForDensity(resources, n, n2, resources$Theme);
        }
        else if (Build$VERSION.SDK_INT >= 15) {
            drawable = ResourcesCompatIcsMr1.getDrawableForDensity(resources, n, n2);
        }
        else {
            drawable = resources.getDrawable(n);
        }
        return drawable;
    }
}
