// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources$Theme;
import android.content.res.Resources;

class ResourcesCompatApi23
{
    public static int getColor(final Resources resources, final int n, final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        return resources.getColor(n, resources$Theme);
    }
    
    public static ColorStateList getColorStateList(final Resources resources, final int n, final Resources$Theme resources$Theme) throws Resources$NotFoundException {
        return resources.getColorStateList(n, resources$Theme);
    }
}
