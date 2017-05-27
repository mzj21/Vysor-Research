// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Service;

class ServiceCompatApi24
{
    public static void stopForeground(final Service service, final int n) {
        service.stopForeground(n);
    }
}
