// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import android.os.Message;
import android.os.Handler;
import com.google.android.gms.common.api.Result;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.concurrent.TimeUnit;
import android.app.PendingIntent;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Condition;
import com.google.android.gms.common.zzc;
import java.util.concurrent.locks.Lock;
import java.util.Map;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.api.Api;
import android.content.Context;

public class zzqr implements zzqg, zzqy
{
    private final Context mContext;
    final Api.zza<? extends zzwz, zzxa> vQ;
    final zzqp wV;
    final zzh xB;
    final Map<Api<?>, Integer> xC;
    final Map<Api.zzc<?>, Api.zze> xW;
    private final Lock xf;
    private final zzc xn;
    private final Condition yj;
    private final zzb yk;
    final Map<Api.zzc<?>, ConnectionResult> yl;
    private volatile zzqq ym;
    private ConnectionResult yn;
    int yo;
    final zzqy.zza yp;
    
    public zzqr(final Context mContext, final zzqp wv, final Lock xf, final Looper looper, final zzc xn, final Map<Api.zzc<?>, Api.zze> xw, final zzh xb, final Map<Api<?>, Integer> xc, final Api.zza<? extends zzwz, zzxa> vq, final ArrayList<zzqf> list, final zzqy.zza yp) {
        this.yl = new HashMap<Api.zzc<?>, ConnectionResult>();
        this.yn = null;
        this.mContext = mContext;
        this.xf = xf;
        this.xn = xn;
        this.xW = xw;
        this.xB = xb;
        this.xC = xc;
        this.vQ = vq;
        this.wV = wv;
        this.yp = yp;
        final Iterator<zzqf> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this);
        }
        this.yk = new zzb(looper);
        this.yj = xf.newCondition();
        this.ym = new zzqo(this);
    }
    
    @Override
    public ConnectionResult blockingConnect() {
        this.connect();
        while (this.isConnecting()) {
            ConnectionResult connectionResult;
            try {
                this.yj.await();
                continue;
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                connectionResult = new ConnectionResult(15, null);
            }
            return connectionResult;
        }
        if (this.isConnected()) {
            return ConnectionResult.uJ;
        }
        if (this.yn != null) {
            return this.yn;
        }
        return new ConnectionResult(13, null);
    }
    
    @Override
    public ConnectionResult blockingConnect(final long n, final TimeUnit timeUnit) {
        this.connect();
        long n2 = timeUnit.toNanos(n);
    Label_0044_Outer:
        while (this.isConnecting()) {
            while (true) {
                if (n2 <= 0L) {
                    try {
                        this.disconnect();
                        return new ConnectionResult(14, null);
                        n2 = this.yj.awaitNanos(n2);
                        continue Label_0044_Outer;
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return new ConnectionResult(15, null);
                    }
                    break;
                }
                continue;
            }
        }
        if (this.isConnected()) {
            return ConnectionResult.uJ;
        }
        if (this.yn != null) {
            return this.yn;
        }
        return new ConnectionResult(13, null);
    }
    
    @Override
    public void connect() {
        this.ym.connect();
    }
    
    @Override
    public void disconnect() {
        if (this.ym.disconnect()) {
            this.yl.clear();
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        final String concat = String.valueOf(s).concat("  ");
        printWriter.append(s).append("mState=").println(this.ym);
        for (final Api<?> api : this.xC.keySet()) {
            printWriter.append(s).append(api.getName()).println(":");
            this.xW.get(api.zzapp()).dump(concat, fileDescriptor, printWriter, array);
        }
    }
    
    @Nullable
    @Override
    public ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        final Api.zzc<?> zzapp = (Api.zzc<?>)api.zzapp();
        if (!this.xW.containsKey(zzapp)) {
            return null;
        }
        ConnectionResult uj;
        if (this.xW.get(zzapp).isConnected()) {
            uj = ConnectionResult.uJ;
        }
        else {
            if (!this.yl.containsKey(zzapp)) {
                return null;
            }
            uj = this.yl.get(zzapp);
        }
        return uj;
        uj = null;
        return uj;
    }
    
    @Override
    public boolean isConnected() {
        return this.ym instanceof zzqm;
    }
    
    @Override
    public boolean isConnecting() {
        return this.ym instanceof zzqn;
    }
    
    @Override
    public void onConnected(@Nullable final Bundle bundle) {
        this.xf.lock();
        try {
            this.ym.onConnected(bundle);
        }
        finally {
            this.xf.unlock();
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.xf.lock();
        try {
            this.ym.onConnectionSuspended(n);
        }
        finally {
            this.xf.unlock();
        }
    }
    
    @Override
    public void zza(@NonNull final ConnectionResult connectionResult, @NonNull final Api<?> api, final int n) {
        this.xf.lock();
        try {
            this.ym.zza(connectionResult, api, n);
        }
        finally {
            this.xf.unlock();
        }
    }
    
    void zza(final zza zza) {
        this.yk.sendMessage(this.yk.obtainMessage(1, (Object)zza));
    }
    
    void zza(final RuntimeException ex) {
        this.yk.sendMessage(this.yk.obtainMessage(2, (Object)ex));
    }
    
    @Override
    public boolean zza(final zzrl zzrl) {
        return false;
    }
    
    @Override
    public void zzaqb() {
    }
    
    @Override
    public void zzaqy() {
        if (this.isConnected()) {
            ((zzqm)this.ym).zzarh();
        }
    }
    
    void zzarw() {
        this.xf.lock();
        try {
            (this.ym = new zzqn(this, this.xB, this.xC, this.xn, this.vQ, this.xf, this.mContext)).begin();
            this.yj.signalAll();
        }
        finally {
            this.xf.unlock();
        }
    }
    
    void zzarx() {
        this.xf.lock();
        try {
            this.wV.zzart();
            (this.ym = new zzqm(this)).begin();
            this.yj.signalAll();
        }
        finally {
            this.xf.unlock();
        }
    }
    
    void zzary() {
        final Iterator<Api.zze> iterator = this.xW.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().disconnect();
        }
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull final T t) {
        t.zzaqt();
        return this.ym.zzc(t);
    }
    
    @Override
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull final T t) {
        t.zzaqt();
        return this.ym.zzd(t);
    }
    
    void zzi(final ConnectionResult yn) {
        this.xf.lock();
        try {
            this.yn = yn;
            (this.ym = new zzqo(this)).begin();
            this.yj.signalAll();
        }
        finally {
            this.xf.unlock();
        }
    }
    
    abstract static class zza
    {
        private final zzqq yq;
        
        protected zza(final zzqq yq) {
            this.yq = yq;
        }
        
        protected abstract void zzari();
        
        public final void zzc(final zzqr zzqr) {
            zzqr.xf.lock();
            try {
                if (zzqr.ym == this.yq) {
                    this.zzari();
                    zzqr.xf.unlock();
                }
            }
            finally {
                zzqr.xf.unlock();
            }
        }
    }
    
    final class zzb extends Handler
    {
        zzb(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.w("GACStateManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                    break;
                }
                case 1: {
                    ((zza)message.obj).zzc(zzqr.this);
                    break;
                }
                case 2: {
                    throw (RuntimeException)message.obj;
                }
            }
        }
    }
}
