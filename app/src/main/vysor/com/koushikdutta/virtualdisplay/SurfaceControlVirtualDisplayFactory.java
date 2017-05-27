// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.virtualdisplay;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import android.os.RemoteException;
import android.view.IRotationWatcher;
import android.os.Handler;
import android.view.Surface;
import java.lang.reflect.Method;
import android.view.DisplayInfo;
import android.hardware.display.IDisplayManager;
import android.view.IWindowManager;
import android.os.IBinder;
import android.os.Build$VERSION;
import android.graphics.Point;
import android.graphics.Rect;

public class SurfaceControlVirtualDisplayFactory implements VirtualDisplayFactory
{
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
        Point point = null;
        Block_0: {
            break Block_0;
        Label_0261:
            while (true) {
                int n;
                do {
                    Label_0079: {
                        break Label_0079;
                        try {
                            final Method declaredMethod = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
                            point = new Point();
                            if (Build$VERSION.SDK_INT >= 18) {
                                final IWindowManager interface1 = IWindowManager.Stub.asInterface((IBinder)declaredMethod.invoke(null, "window"));
                                interface1.getBaseDisplaySize(0, point);
                                n = interface1.getRotation();
                                continue Label_0261;
                            }
                            if (Build$VERSION.SDK_INT == 17) {
                                final DisplayInfo displayInfo = IDisplayManager.Stub.asInterface((IBinder)declaredMethod.invoke(null, "display")).getDisplayInfo(0);
                                point.x = (int)DisplayInfo.class.getDeclaredField("logicalWidth").get(displayInfo);
                                point.y = (int)DisplayInfo.class.getDeclaredField("logicalHeight").get(displayInfo);
                                n = (int)DisplayInfo.class.getDeclaredField("rotation").get(displayInfo);
                                continue Label_0261;
                            }
                            final IWindowManager interface2 = IWindowManager.Stub.asInterface((IBinder)declaredMethod.invoke(null, "window"));
                            interface2.getRealDisplaySize(point);
                            n = interface2.getRotation();
                            continue Label_0261;
                            final int x = point.x;
                            point.x = point.y;
                            point.y = x;
                            break;
                        }
                        catch (Exception ex) {
                            throw new AssertionError((Object)ex);
                        }
                    }
                    continue Label_0261;
                } while (b && (n == 1 || n == 3));
                break;
            }
        }
        return point;
    }
    
    @Override
    public VirtualDisplay createVirtualDisplay(final String s, final int n, final int n2, final int n3, final int n4, final Surface surface, final Handler handler) {
        try {
            final Class<?> forName = Class.forName("android.view.SurfaceControl");
            final IBinder binder = (IBinder)forName.getDeclaredMethod("createDisplay", String.class, Boolean.TYPE).invoke(null, s, false);
            final Method declaredMethod = forName.getDeclaredMethod("setDisplaySurface", IBinder.class, Surface.class);
            final Method declaredMethod2 = forName.getDeclaredMethod("setDisplayProjection", IBinder.class, Integer.TYPE, Rect.class, Rect.class);
            final Method declaredMethod3 = forName.getDeclaredMethod("setDisplayLayerStack", IBinder.class, Integer.TYPE);
            final Method declaredMethod4 = forName.getDeclaredMethod("openTransaction", (Class<?>[])new Class[0]);
            final Method declaredMethod5 = forName.getDeclaredMethod("closeTransaction", (Class<?>[])new Class[0]);
            final Method declaredMethod6 = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
            this.displayRect = new Rect(0, 0, n, n2);
            final Rect rect = new Rect(0, 0, this.displaySize.x, this.displaySize.y);
            declaredMethod4.invoke(null, new Object[0]);
            declaredMethod.invoke(null, binder, surface);
            declaredMethod2.invoke(null, binder, 0, rect, this.displayRect);
            declaredMethod3.invoke(null, binder, 0);
            declaredMethod5.invoke(null, new Object[0]);
            return new VirtualDisplay() {
                final /* synthetic */ Method val$destroyDisplayMethod = forName.getDeclaredMethod("destroyDisplay", IBinder.class);
                IRotationWatcher watcher;
                IWindowManager wm = IWindowManager.Stub.asInterface((IBinder)declaredMethod6.invoke(null, "window"));
                
                {
                    try {
                        this.wm.watchRotation(new IRotationWatcher.Stub() {
                            public void onRotationChanged(final int n) throws RemoteException {
                                final Point displaySize = SurfaceControlVirtualDisplayFactory.this.displaySize;
                                SurfaceControlVirtualDisplayFactory.this.displaySize = SurfaceControlVirtualDisplayFactory.getCurrentDisplaySize();
                                if (!displaySize.equals((Object)SurfaceControlVirtualDisplayFactory.this.displaySize)) {
                                    try {
                                        final Rect rect = new Rect(0, 0, SurfaceControlVirtualDisplayFactory.this.displaySize.x, SurfaceControlVirtualDisplayFactory.this.displaySize.y);
                                        declaredMethod4.invoke(null, new Object[0]);
                                        declaredMethod2.invoke(null, binder, 0, rect, SurfaceControlVirtualDisplayFactory.this.displayRect);
                                        declaredMethod5.invoke(null, new Object[0]);
                                    }
                                    catch (Exception ex) {
                                        throw new AssertionError((Object)ex);
                                    }
                                }
                            }
                        });
                    }
                    catch (RemoteException ex) {
                        throw new AssertionError(ex);
                    }
                }
                
                @Override
                public void release() {
                    Log.i("VysorFactory", "VirtualDisplay released");
                    this.wm = null;
                    this.watcher = null;
                    try {
                        this.val$destroyDisplayMethod.invoke(null, binder);
                    }
                    catch (Exception ex) {
                        throw new AssertionError((Object)ex);
                    }
                }
            };
        }
        catch (Exception ex) {
            throw new AssertionError((Object)ex);
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
