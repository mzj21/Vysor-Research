// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Iterator;
import java.util.Collection;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import android.os.Handler$Callback;

public final class zzm implements Handler$Callback
{
    private final zza Cs;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> Ct;
    final ArrayList<GoogleApiClient.ConnectionCallbacks> Cu;
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> Cv;
    private volatile boolean Cw;
    private final AtomicInteger Cx;
    private boolean Cy;
    private final Handler mHandler;
    private final Object zzakd;
    
    public zzm(final Looper looper, final zza cs) {
        this.Ct = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.Cu = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
        this.Cv = new ArrayList<GoogleApiClient.OnConnectionFailedListener>();
        this.Cw = false;
        this.Cx = new AtomicInteger(0);
        this.Cy = false;
        this.zzakd = new Object();
        this.Cs = cs;
        this.mHandler = new Handler(looper, (Handler$Callback)this);
    }
    
    public boolean handleMessage(final Message message) {
        if (message.what == 1) {
            final GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)message.obj;
            synchronized (this.zzakd) {
                if (this.Cw && this.Cs.isConnected() && this.Ct.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.Cs.zzaoe());
                }
                // monitorexit(this.zzakd)
                return true;
            }
        }
        Log.wtf("GmsClientEvents", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), (Throwable)new Exception());
        return false;
    }
    
    public boolean isConnectionCallbacksRegistered(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzac.zzy(connectionCallbacks);
        synchronized (this.zzakd) {
            return this.Ct.contains(connectionCallbacks);
        }
    }
    
    public boolean isConnectionFailedListenerRegistered(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzy(onConnectionFailedListener);
        synchronized (this.zzakd) {
            return this.Cv.contains(onConnectionFailedListener);
        }
    }
    
    public void registerConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzac.zzy(connectionCallbacks);
        synchronized (this.zzakd) {
            if (this.Ct.contains(connectionCallbacks)) {
                final String value = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(62 + String.valueOf(value).length()).append("registerConnectionCallbacks(): listener ").append(value).append(" is already registered").toString());
            }
            else {
                this.Ct.add(connectionCallbacks);
            }
            // monitorexit(this.zzakd)
            if (this.Cs.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)connectionCallbacks));
            }
        }
    }
    
    public void registerConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzy(onConnectionFailedListener);
        synchronized (this.zzakd) {
            if (this.Cv.contains(onConnectionFailedListener)) {
                final String value = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(67 + String.valueOf(value).length()).append("registerConnectionFailedListener(): listener ").append(value).append(" is already registered").toString());
            }
            else {
                this.Cv.add(onConnectionFailedListener);
            }
        }
    }
    
    public void unregisterConnectionCallbacks(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzac.zzy(connectionCallbacks);
        synchronized (this.zzakd) {
            if (!this.Ct.remove(connectionCallbacks)) {
                final String value = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(52 + String.valueOf(value).length()).append("unregisterConnectionCallbacks(): listener ").append(value).append(" not found").toString());
            }
            else if (this.Cy) {
                this.Cu.add(connectionCallbacks);
            }
        }
    }
    
    public void unregisterConnectionFailedListener(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzy(onConnectionFailedListener);
        synchronized (this.zzakd) {
            if (!this.Cv.remove(onConnectionFailedListener)) {
                final String value = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(57 + String.valueOf(value).length()).append("unregisterConnectionFailedListener(): listener ").append(value).append(" not found").toString());
            }
        }
    }
    
    public void zzaut() {
        this.Cw = false;
        this.Cx.incrementAndGet();
    }
    
    public void zzauu() {
        this.Cw = true;
    }
    
    public void zzgo(final int n) {
        final Looper myLooper = Looper.myLooper();
        final Looper looper = this.mHandler.getLooper();
        boolean b = false;
        if (myLooper == looper) {
            b = true;
        }
        zzac.zza(b, (Object)"onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzakd) {
            this.Cy = true;
            final ArrayList<GoogleApiClient.ConnectionCallbacks> list = new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.Ct);
            final int value = this.Cx.get();
            for (final GoogleApiClient.ConnectionCallbacks connectionCallbacks : list) {
                if (!this.Cw || this.Cx.get() != value) {
                    break;
                }
                if (!this.Ct.contains(connectionCallbacks)) {
                    continue;
                }
                connectionCallbacks.onConnectionSuspended(n);
            }
            this.Cu.clear();
            this.Cy = false;
        }
    }
    
    public void zzn(final ConnectionResult connectionResult) {
        zzac.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object)"onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzakd) {
            final ArrayList<GoogleApiClient.OnConnectionFailedListener> list = new ArrayList<GoogleApiClient.OnConnectionFailedListener>(this.Cv);
            final int value = this.Cx.get();
            for (final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener : list) {
                if (!this.Cw || this.Cx.get() != value) {
                    return;
                }
                if (!this.Cv.contains(onConnectionFailedListener)) {
                    continue;
                }
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }
    // monitorexit(o)
    
    public void zzp(final Bundle bundle) {
        boolean b = true;
        while (true) {
            Label_0203: {
                if (Looper.myLooper() != this.mHandler.getLooper()) {
                    break Label_0203;
                }
                final boolean b2 = b;
                boolean b3;
                ArrayList<GoogleApiClient.ConnectionCallbacks> list;
                int value;
                Label_0042_Outer:Label_0070_Outer:
                while (true) {
                    zzac.zza(b2, (Object)"onConnectionSuccess must only be called on the Handler thread");
                    while (true) {
                    Label_0214:
                        while (true) {
                            Label_0208: {
                                synchronized (this.zzakd) {
                                    if (this.Cy) {
                                        break Label_0208;
                                    }
                                    b3 = b;
                                    zzac.zzbr(b3);
                                    this.mHandler.removeMessages(1);
                                    this.Cy = true;
                                    if (this.Cu.size() == 0) {
                                        zzac.zzbr(b);
                                        list = new ArrayList<GoogleApiClient.ConnectionCallbacks>(this.Ct);
                                        value = this.Cx.get();
                                        for (final GoogleApiClient.ConnectionCallbacks connectionCallbacks : list) {
                                            if (!this.Cw || !this.Cs.isConnected() || this.Cx.get() != value) {
                                                break;
                                            }
                                            if (this.Cu.contains(connectionCallbacks)) {
                                                continue Label_0042_Outer;
                                            }
                                            connectionCallbacks.onConnected(bundle);
                                        }
                                        this.Cu.clear();
                                        this.Cy = false;
                                        return;
                                    }
                                    break Label_0214;
                                }
                                break;
                            }
                            b3 = false;
                            continue Label_0070_Outer;
                        }
                        b = false;
                        continue;
                    }
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    public interface zza
    {
        boolean isConnected();
        
        Bundle zzaoe();
    }
}
