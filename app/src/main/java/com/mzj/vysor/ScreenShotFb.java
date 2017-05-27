package com.mzj.vysor;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Build;
import android.view.IWindowManager;

import com.koushikdutta.virtualdisplay.SurfaceControlVirtualDisplayFactory;

import com.mzj.vysor.util.ReflectionUtils;
import com.mzj.vysor.util.SystemServiceUtil;

public class ScreenShotFb {
    public static Bitmap screenshot() throws Exception {
        final Point currentDisplaySize = SurfaceControlVirtualDisplayFactory.getCurrentDisplaySize(false);
        String s;
        if (Build.VERSION.SDK_INT <= 17) {
            s = "android.view.Surface";
        } else {
            s = "android.view.SurfaceControl";
        }
        Class[] classes = {Integer.TYPE, Integer.TYPE};
        Object[] objects = {currentDisplaySize.x, currentDisplaySize.y};
        Bitmap bitmap = (Bitmap) ReflectionUtils.invokeMethod(s, "screenshot", classes, objects);
        final IWindowManager windowManager = SystemServiceUtil.getWindowManager();
        final int rotation = windowManager.getRotation();
        if (rotation != 0) {
            final Matrix matrix = new Matrix();
            if (rotation == 1) {
                matrix.postRotate(-90.0f);
            } else if (rotation == 2) {
                matrix.postRotate(-180.0f);
            } else if (rotation == 3) {
                matrix.postRotate(-270.0f);
            }
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, currentDisplaySize.x, currentDisplaySize.y, matrix, false);
        }
        return bitmap;
    }
}