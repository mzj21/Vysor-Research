// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.support.annotation.WorkerThread;
import android.os.IInterface;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.internal.zzqc;
import android.util.Pair;
import com.google.android.gms.internal.zzqu;
import com.google.android.gms.common.internal.zzac;
import android.support.annotation.NonNull;
import android.os.Looper;
import com.google.android.gms.internal.zzqt;
import com.google.android.gms.internal.zzpz;
import com.google.android.gms.internal.zzre;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import android.content.Context;

public abstract class zzd<O extends Api.ApiOptions>
{
    private final Context mContext;
    private final int mId;
    private final Api<O> tv;
    private final AtomicBoolean vA;
    private final AtomicInteger vB;
    private Api.zze vC;
    private final zzre vv;
    private final O vw;
    private final zzpz<O> vx;
    private final zzqt vy;
    private final GoogleApiClient vz;
    private final Looper zzajn;
    
    public zzd(@NonNull final Context context, final Api<O> api, final O o) {
        Looper looper;
        if (Looper.myLooper() != null) {
            looper = Looper.myLooper();
        }
        else {
            looper = Looper.getMainLooper();
        }
        this(context, (Api<Api.ApiOptions>)api, o, looper);
    }
    
    public zzd(@NonNull final Context context, final Api<O> tv, final O vw, final Looper zzajn) {
        this.vA = new AtomicBoolean(false);
        this.vB = new AtomicInteger(0);
        zzac.zzb(context, "Null context is not permitted.");
        zzac.zzb(tv, "Api must not be null.");
        zzac.zzb(zzajn, "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.tv = tv;
        this.vw = vw;
        this.zzajn = zzajn;
        this.vv = new zzre();
        this.vx = zzpz.zza(this.tv, this.vw);
        this.vz = new zzqu<Object>(this);
        final Pair<zzqt, Integer> zza = zzqt.zza(this.mContext, this);
        this.vy = (zzqt)zza.first;
        this.mId = (int)zza.second;
    }
    
    private <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zza(final int n, @NonNull final T t) {
        t.zzaqt();
        this.vy.zza((zzd<Api.ApiOptions>)this, n, (zzqc.zza<? extends Result, Api.zzb>)t);
        return t;
    }
    
    private <TResult, A extends Api.zzb> Task<TResult> zza(final int n, @NonNull final zzro<A, TResult> zzro) {
        final TaskCompletionSource<TResult> taskCompletionSource = new TaskCompletionSource<TResult>();
        this.vy.zza((zzd<Api.ApiOptions>)this, n, (zzro<Api.zzb, TResult>)zzro, taskCompletionSource);
        return taskCompletionSource.getTask();
    }
    
    public int getInstanceId() {
        return this.mId;
    }
    
    public Looper getLooper() {
        return this.zzajn;
    }
    
    public void release() {
        boolean b = true;
        if (!this.vA.getAndSet(b)) {
            this.vv.release();
            final zzqt vy = this.vy;
            final int mId = this.mId;
            if (this.vB.get() <= 0) {
                b = false;
            }
            vy.zzd(mId, b);
        }
    }
    
    @WorkerThread
    public Api.zze zza(final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (!this.zzapw()) {
            if (this.tv.zzapq()) {
                final Api.zzh<?, O> zzapo = this.tv.zzapo();
                this.vC = new zzai<Object>(this.mContext, looper, zzapo.zzapt(), connectionCallbacks, onConnectionFailedListener, zzh.zzcd(this.mContext), (Api.zzg<IInterface>)zzapo.zzr(this.vw));
            }
            else {
                this.vC = (Api.zze)this.tv.zzapn().zza(this.mContext, looper, zzh.zzcd(this.mContext), this.vw, connectionCallbacks, onConnectionFailedListener);
            }
        }
        return this.vC;
    }
    
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zza(@NonNull final T t) {
        return this.zza(0, t);
    }
    
    public <TResult, A extends Api.zzb> Task<TResult> zza(final zzro<A, TResult> zzro) {
        return this.zza(0, zzro);
    }
    
    public void zzapu() {
        this.vB.incrementAndGet();
    }
    
    public void zzapv() {
        if (this.vB.decrementAndGet() == 0 && this.vA.get()) {
            this.vy.zzd(this.mId, false);
        }
    }
    
    public boolean zzapw() {
        return this.vC != null;
    }
    
    public zzpz<O> zzapx() {
        return this.vx;
    }
    
    public GoogleApiClient zzapy() {
        return this.vz;
    }
    
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzb(@NonNull final T t) {
        return this.zza(1, t);
    }
    
    public <TResult, A extends Api.zzb> Task<TResult> zzb(final zzro<A, TResult> zzro) {
        return this.zza(1, zzro);
    }
}
