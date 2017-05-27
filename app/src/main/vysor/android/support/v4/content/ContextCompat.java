// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.content.res.ColorStateList;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.os.Build$VERSION;
import android.util.Log;
import android.support.v4.os.BuildCompat;
import android.os.Process;
import android.support.annotation.NonNull;
import android.content.Context;
import java.io.File;
import android.util.TypedValue;

public class ContextCompat
{
    private static final String DIR_ANDROID = "Android";
    private static final String DIR_OBB = "obb";
    private static final String TAG = "ContextCompat";
    private static final Object sLock;
    private static TypedValue sTempValue;
    
    static {
        sLock = new Object();
    }
    
    private static File buildPath(final File file, final String... array) {
        final int length = array.length;
        int i = 0;
        File file2 = file;
        while (i < length) {
            final String s = array[i];
            File file3;
            if (file2 == null) {
                file3 = new File(s);
            }
            else if (s != null) {
                file3 = new File(file2, s);
            }
            else {
                file3 = file2;
            }
            ++i;
            file2 = file3;
        }
        return file2;
    }
    
    public static int checkSelfPermission(@NonNull final Context context, @NonNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException("permission is null");
        }
        return context.checkPermission(s, Process.myPid(), Process.myUid());
    }
    
    @Deprecated
    public static Context createDeviceEncryptedStorageContext(final Context context) {
        return createDeviceProtectedStorageContext(context);
    }
    
    public static Context createDeviceProtectedStorageContext(final Context context) {
        Context deviceProtectedStorageContext;
        if (BuildCompat.isAtLeastN()) {
            deviceProtectedStorageContext = ContextCompatApi24.createDeviceProtectedStorageContext(context);
        }
        else {
            deviceProtectedStorageContext = null;
        }
        return deviceProtectedStorageContext;
    }
    
    private static File createFilesDir(File file) {
        synchronized (ContextCompat.class) {
            if (!file.exists() && !file.mkdirs() && !file.exists()) {
                Log.w("ContextCompat", "Unable to create files subdir " + file.getPath());
                file = null;
            }
            return file;
        }
    }
    
    public static File getCodeCacheDir(final Context context) {
        File file;
        if (Build$VERSION.SDK_INT >= 21) {
            file = ContextCompatApi21.getCodeCacheDir(context);
        }
        else {
            file = createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
        }
        return file;
    }
    
    @ColorInt
    public static final int getColor(final Context context, @ColorRes final int n) {
        int n2;
        if (Build$VERSION.SDK_INT >= 23) {
            n2 = ContextCompatApi23.getColor(context, n);
        }
        else {
            n2 = context.getResources().getColor(n);
        }
        return n2;
    }
    
    public static final ColorStateList getColorStateList(final Context context, @ColorRes final int n) {
        ColorStateList list;
        if (Build$VERSION.SDK_INT >= 23) {
            list = ContextCompatApi23.getColorStateList(context, n);
        }
        else {
            list = context.getResources().getColorStateList(n);
        }
        return list;
    }
    
    public static File getDataDir(final Context context) {
        File dataDir;
        if (BuildCompat.isAtLeastN()) {
            dataDir = ContextCompatApi24.getDataDir(context);
        }
        else {
            final String dataDir2 = context.getApplicationInfo().dataDir;
            if (dataDir2 != null) {
                dataDir = new File(dataDir2);
            }
            else {
                dataDir = null;
            }
        }
        return dataDir;
    }
    
    public static final Drawable getDrawable(final Context context, @DrawableRes final int n) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        Drawable drawable;
        if (sdk_INT >= 21) {
            drawable = ContextCompatApi21.getDrawable(context, n);
        }
        else if (sdk_INT >= 16) {
            drawable = context.getResources().getDrawable(n);
        }
        else {
            synchronized (ContextCompat.sLock) {
                if (ContextCompat.sTempValue == null) {
                    ContextCompat.sTempValue = new TypedValue();
                }
                context.getResources().getValue(n, ContextCompat.sTempValue, true);
                final int resourceId = ContextCompat.sTempValue.resourceId;
                // monitorexit(ContextCompat.sLock)
                drawable = context.getResources().getDrawable(resourceId);
            }
        }
        return drawable;
    }
    
    public static File[] getExternalCacheDirs(final Context context) {
        File[] externalCacheDirs;
        if (Build$VERSION.SDK_INT >= 19) {
            externalCacheDirs = ContextCompatKitKat.getExternalCacheDirs(context);
        }
        else {
            externalCacheDirs = new File[] { context.getExternalCacheDir() };
        }
        return externalCacheDirs;
    }
    
    public static File[] getExternalFilesDirs(final Context context, final String s) {
        File[] externalFilesDirs;
        if (Build$VERSION.SDK_INT >= 19) {
            externalFilesDirs = ContextCompatKitKat.getExternalFilesDirs(context, s);
        }
        else {
            externalFilesDirs = new File[] { context.getExternalFilesDir(s) };
        }
        return externalFilesDirs;
    }
    
    public static final File getNoBackupFilesDir(final Context context) {
        File file;
        if (Build$VERSION.SDK_INT >= 21) {
            file = ContextCompatApi21.getNoBackupFilesDir(context);
        }
        else {
            file = createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
        }
        return file;
    }
    
    public static File[] getObbDirs(final Context context) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        File[] obbDirs;
        if (sdk_INT >= 19) {
            obbDirs = ContextCompatKitKat.getObbDirs(context);
        }
        else {
            File file;
            if (sdk_INT >= 11) {
                file = ContextCompatHoneycomb.getObbDir(context);
            }
            else {
                file = buildPath(Environment.getExternalStorageDirectory(), "Android", "obb", context.getPackageName());
            }
            obbDirs = new File[] { file };
        }
        return obbDirs;
    }
    
    @Deprecated
    public static boolean isDeviceEncryptedStorage(final Context context) {
        return isDeviceProtectedStorage(context);
    }
    
    public static boolean isDeviceProtectedStorage(final Context context) {
        return BuildCompat.isAtLeastN() && ContextCompatApi24.isDeviceProtectedStorage(context);
    }
    
    public static boolean startActivities(final Context context, final Intent[] array) {
        return startActivities(context, array, null);
    }
    
    public static boolean startActivities(final Context context, final Intent[] array, final Bundle bundle) {
        boolean b = true;
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 16) {
            ContextCompatJellybean.startActivities(context, array, bundle);
        }
        else if (sdk_INT >= 11) {
            ContextCompatHoneycomb.startActivities(context, array);
        }
        else {
            b = false;
        }
        return b;
    }
}
