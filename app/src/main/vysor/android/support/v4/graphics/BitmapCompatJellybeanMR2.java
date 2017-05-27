// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics;

import android.graphics.Bitmap;

class BitmapCompatJellybeanMR2
{
    public static boolean hasMipMap(final Bitmap bitmap) {
        return bitmap.hasMipMap();
    }
    
    public static void setHasMipMap(final Bitmap bitmap, final boolean hasMipMap) {
        bitmap.setHasMipMap(hasMipMap);
    }
}
