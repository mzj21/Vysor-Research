// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.app.Service;
import android.support.v4.os.BuildCompat;

public final class ServiceCompat
{
    static final ServiceCompatImpl IMPL;
    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;
    
    static {
        if (BuildCompat.isAtLeastN()) {
            IMPL = (ServiceCompatImpl)new Api24ServiceCompatImpl();
        }
        else {
            IMPL = (ServiceCompatImpl)new BaseServiceCompatImpl();
        }
    }
    
    public static void stopForeground(final Service service, final int n) {
        ServiceCompat.IMPL.stopForeground(service, n);
    }
    
    static class Api24ServiceCompatImpl implements ServiceCompatImpl
    {
        @Override
        public void stopForeground(final Service service, final int n) {
            ServiceCompatApi24.stopForeground(service, n);
        }
    }
    
    static class BaseServiceCompatImpl implements ServiceCompatImpl
    {
        @Override
        public void stopForeground(final Service service, final int n) {
            service.stopForeground((n & 0x1) != 0x0);
        }
    }
    
    interface ServiceCompatImpl
    {
        void stopForeground(final Service p0, final int p1);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface StopForegroundFlags {
    }
}
