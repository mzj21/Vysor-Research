package com.mzj.vysor.util;

import android.hardware.display.IDisplayManager;
import android.hardware.input.InputManager;
import android.os.IBinder;
import android.os.IPowerManager;
import android.view.DisplayInfo;
import android.view.IWindowManager;

import java.lang.reflect.Method;

public class SystemServiceUtil {

    public static IWindowManager getWindowManager() throws Exception {
        final Method declaredMethod = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
        return IWindowManager.Stub.asInterface((IBinder) declaredMethod.invoke(null, "window"));
    }

    public static IPowerManager getPowerManager() throws Exception {
        final Method declaredMethod = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
        return IPowerManager.Stub.asInterface((IBinder) declaredMethod.invoke(null, "power"));
    }

    public static DisplayInfo getDisplayInfo() throws Exception {
        final Method declaredMethod = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
        return IDisplayManager.Stub.asInterface((IBinder) declaredMethod.invoke(null, "display")).getDisplayInfo(0);
    }

    public static InputManager getInputManager() {
        return (InputManager) ReflectionUtils.invokeMethod(InputManager.class, "getInstance", null , null);
    }
}
