// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.util.DisplayMetrics;
import android.support.annotation.NonNull;
import android.content.res.Resources;

class ConfigurationHelperGingerbread
{
    static int getDensityDpi(@NonNull final Resources resources) {
        return resources.getDisplayMetrics().densityDpi;
    }
    
    static int getScreenHeightDp(@NonNull final Resources resources) {
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return (int)(displayMetrics.heightPixels / displayMetrics.density);
    }
    
    static int getScreenWidthDp(@NonNull final Resources resources) {
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return (int)(displayMetrics.widthPixels / displayMetrics.density);
    }
    
    static int getSmallestScreenWidthDp(@NonNull final Resources resources) {
        return Math.min(getScreenWidthDp(resources), getScreenHeightDp(resources));
    }
}
