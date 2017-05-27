// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.content.res.Resources$NotFoundException;
import android.graphics.drawable.Drawable;
import android.content.res.Resources$Theme;
import android.content.res.Resources;

class ResourcesCompatApi21
{
    public static Drawable getDrawable(final Resources resources, final int n, final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        return resources.getDrawable(n, resources$Theme);
    }
    
    public static Drawable getDrawableForDensity(final Resources resources, final int n, final int n2, final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        return resources.getDrawableForDensity(n, n2, resources$Theme);
    }
}
