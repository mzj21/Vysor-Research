// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.util.concurrent.TimeoutException;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.TimeUnit;
import android.content.ComponentName;
import java.util.concurrent.LinkedBlockingQueue;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import android.content.ServiceConnection;

public class zza implements ServiceConnection
{
    boolean uH;
    private final BlockingQueue<IBinder> uI;
    
    public zza() {
        this.uH = false;
        this.uI = new LinkedBlockingQueue<IBinder>();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.uI.add(binder);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
    
    public IBinder zza(final long n, final TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        zzac.zzhr("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.uH) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.uH = true;
        final IBinder binder = this.uI.poll(n, timeUnit);
        if (binder == null) {
            throw new TimeoutException("Timed out waiting for the service connection");
        }
        return binder;
    }
    
    public IBinder zzapc() throws InterruptedException {
        zzac.zzhr("BlockingServiceConnection.getService() called on main thread");
        if (this.uH) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.uH = true;
        return this.uI.take();
    }
}
