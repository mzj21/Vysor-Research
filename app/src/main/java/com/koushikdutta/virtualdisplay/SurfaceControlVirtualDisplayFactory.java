package com.koushikdutta.virtualdisplay;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.IRotationWatcher;
import android.view.IWindowManager;
import android.view.Surface;

import com.mzj.vysor.ClientConfig;
import com.mzj.vysor.util.SystemServiceUtil;

import java.lang.reflect.Method;

public class SurfaceControlVirtualDisplayFactory implements VirtualDisplayFactory {
    private static final String LOGTAG = "VysorFactory";
    Rect displayRect;
    Point displaySize;

    public SurfaceControlVirtualDisplayFactory() {
        this.displaySize = getCurrentDisplaySize();
    }

    public static Point getCurrentDisplaySize() {
        return getCurrentDisplaySize(true);
    }

    public static Point getCurrentDisplaySize(final boolean b) {
        Point point = new Point();
        try {
            IWindowManager manager = SystemServiceUtil.getWindowManager();
            manager.getBaseDisplaySize(0, point);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return point;
    }

    public static Point getEncodeSize() {
        final Point currentDisplaySize = SurfaceControlVirtualDisplayFactory.getCurrentDisplaySize();
        if (ClientConfig.resolution != 0.0) {
            currentDisplaySize.x *= (int) ClientConfig.resolution;
            currentDisplaySize.y *= (int) ClientConfig.resolution;
        } else {
            if (currentDisplaySize.x >= 1280 || currentDisplaySize.y >= 1280) {
                currentDisplaySize.x /= 2;
                currentDisplaySize.y /= 2;
            }
            while (currentDisplaySize.x > 1280 || currentDisplaySize.y > 1280) {
                currentDisplaySize.x /= 2;
                currentDisplaySize.y /= 2;
            }
        }
        return currentDisplaySize;
    }

    @Override
    public VirtualDisplay createVirtualDisplay(final String s, final int width, final int height, final int n3, final int n4, final Surface surface, final Handler handler) {
        try {
            final Class<?> forName = Class.forName("android.view.SurfaceControl");
            final IBinder binder = (IBinder) forName.getDeclaredMethod("createDisplay", String.class, Boolean.TYPE).invoke(null, s, false);
            final Method declaredMethod = forName.getDeclaredMethod("setDisplaySurface", IBinder.class, Surface.class);
            final Method declaredMethod2 = forName.getDeclaredMethod("setDisplayProjection", IBinder.class, Integer.TYPE, Rect.class, Rect.class);
            final Method declaredMethod3 = forName.getDeclaredMethod("setDisplayLayerStack", IBinder.class, Integer.TYPE);
            final Method declaredMethod4 = forName.getDeclaredMethod("openTransaction", (Class<?>[]) new Class[0]);
            final Method declaredMethod5 = forName.getDeclaredMethod("closeTransaction", (Class<?>[]) new Class[0]);
            final Method declaredMethod6 = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
            this.displayRect = new Rect(0, 0, width, height);
            final Rect rect = new Rect(0, 0, this.displaySize.x, this.displaySize.y);
            declaredMethod4.invoke(null);
            declaredMethod.invoke(null, binder, surface);
            declaredMethod2.invoke(null, binder, 0, rect, this.displayRect);
            declaredMethod3.invoke(null, binder, 0);
            declaredMethod5.invoke(null);
            return new VirtualDisplay() {
                final Method destroyDisplayMethod = forName.getDeclaredMethod("destroyDisplay", IBinder.class);
                IRotationWatcher watcher;
                IWindowManager wm = IWindowManager.Stub.asInterface((IBinder) declaredMethod6.invoke(null, "window"));

                {
                    try {
                        this.wm.watchRotation(new IRotationWatcher.Stub() {
                            public void onRotationChanged(final int n) throws RemoteException {
                                final Point displaySize = SurfaceControlVirtualDisplayFactory.this.displaySize;
                                SurfaceControlVirtualDisplayFactory.this.displaySize =
                                        SurfaceControlVirtualDisplayFactory.getCurrentDisplaySize();
                                if (!displaySize.equals(SurfaceControlVirtualDisplayFactory.this.displaySize)) {
                                    try {
                                        final Rect rect = new Rect(0, 0, SurfaceControlVirtualDisplayFactory.this.displaySize.x,
                                                SurfaceControlVirtualDisplayFactory.this.displaySize.y);
                                        declaredMethod4.invoke(null);
                                        declaredMethod2.invoke(null, binder, 0, rect, SurfaceControlVirtualDisplayFactory.this.displayRect);
                                        declaredMethod5.invoke(null);
                                    } catch (Exception ex) {
                                        throw new AssertionError(ex);
                                    }
                                }
                            }
                        });
                    } catch (RemoteException ex) {
                        throw new AssertionError(ex);
                    }
                }

                @Override
                public void release() {
                    Log.i("VysorFactory", "VirtualDisplay released");
                    this.wm = null;
                    this.watcher = null;
                    try {
                        this.destroyDisplayMethod.invoke(null, binder);
                    } catch (Exception ex) {
                        throw new AssertionError(ex);
                    }
                }
            };
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }
    }

    public Rect getDisplayRect() {
        return this.displayRect;
    }

    public Point getDisplaySize() {
        return new Point(this.displaySize);
    }

    @Override
    public void release() {
        Log.i("VysorFactory", "factory released");
    }
}
