// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.graphics.drawable.Drawable;
import java.io.File;
import android.content.Context;

class ContextCompatApi21
{
    public static File getCodeCacheDir(final Context context) {
        return context.getCodeCacheDir();
    }
    
    public static Drawable getDrawable(final Context context, final int n) {
        return context.getDrawable(n);
    }
    
    public static File getNoBackupFilesDir(final Context context) {
        return context.getNoBackupFilesDir();
    }
}
