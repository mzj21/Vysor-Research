// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.support.annotation.NonNull;
import android.content.res.Resources;

class ConfigurationHelperHoneycombMr2
{
    static int getScreenHeightDp(@NonNull final Resources resources) {
        return resources.getConfiguration().screenHeightDp;
    }
    
    static int getScreenWidthDp(@NonNull final Resources resources) {
        return resources.getConfiguration().screenWidthDp;
    }
    
    static int getSmallestScreenWidthDp(@NonNull final Resources resources) {
        return resources.getConfiguration().smallestScreenWidthDp;
    }
}
