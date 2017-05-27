// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import java.io.PrintStream;
import java.lang.reflect.Method;
import android.util.Log;
import android.graphics.Bitmap;

public class ScrapeMain
{
    public static void main(final String[] array) throws Exception {
        try {
            System.out.println("started");
            final Method declaredMethod = Class.forName("android.view.SurfaceControl").getDeclaredMethod("screenshot", Integer.TYPE, Integer.TYPE);
            int n = 0;
            while (true) {
                final Bitmap bitmap = (Bitmap)declaredMethod.invoke(null, 360, 640);
                final PrintStream out = System.out;
                final int n2 = n + 1;
                out.println(n);
                n = n2;
            }
        }
        catch (Exception ex) {
            Log.e("Scraper", "error", (Throwable)ex);
        }
    }
}
