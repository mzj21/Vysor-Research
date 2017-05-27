// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Message;
import android.os.Handler;
import android.support.annotation.WorkerThread;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.Releasable;
import android.os.Looper;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.GoogleApiClient;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.api.Result;

public class zzrp<R extends Result> extends TransformedResult<R> implements ResultCallback<R>
{
    private final Object wG;
    private final WeakReference<GoogleApiClient> wI;
    private ResultTransform<? super R, ? extends Result> zk;
    private zzrp<? extends Result> zl;
    private volatile ResultCallbacks<? super R> zm;
    private PendingResult<R> zn;
    private Status zo;
    private final zza zp;
    private boolean zq;
    
    public zzrp(final WeakReference<GoogleApiClient> wi) {
        this.zk = null;
        this.zl = null;
        this.zm = null;
        this.zn = null;
        this.wG = new Object();
        this.zo = null;
        this.zq = false;
        zzac.zzb(wi, "GoogleApiClient reference must not be null");
        this.wI = wi;
        final GoogleApiClient googleApiClient = this.wI.get();
        Looper looper;
        if (googleApiClient != null) {
            looper = googleApiClient.getLooper();
        }
        else {
            looper = Looper.getMainLooper();
        }
        this.zp = new zza(looper);
    }
    
    private void zzac(final Status zo) {
        synchronized (this.wG) {
            this.zzad(this.zo = zo);
        }
    }
    
    private void zzad(final Status status) {
        synchronized (this.wG) {
            if (this.zk != null) {
                final Status onFailure = this.zk.onFailure(status);
                zzac.zzb(onFailure, "onFailure must not return null");
                this.zl.zzac(onFailure);
            }
            else if (this.zzasv()) {
                this.zm.onFailure(status);
            }
        }
    }
    
    private void zzast() {
        if (this.zk != null || this.zm != null) {
            final GoogleApiClient googleApiClient = this.wI.get();
            if (!this.zq && this.zk != null && googleApiClient != null) {
                googleApiClient.zza(this);
                this.zq = true;
            }
            if (this.zo != null) {
                this.zzad(this.zo);
            }
            else if (this.zn != null) {
                this.zn.setResultCallback(this);
            }
        }
    }
    
    private boolean zzasv() {
        final GoogleApiClient googleApiClient = this.wI.get();
        return this.zm != null && googleApiClient != null;
    }
    
    private void zze(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            final String value = String.valueOf(result);
            Log.w("TransformedResultImpl", new StringBuilder(18 + String.valueOf(value).length()).append("Unable to release ").append(value).toString(), (Throwable)ex);
        }
    }
    
    @Override
    public void andFinally(@NonNull final ResultCallbacks<? super R> zm) {
    Label_0033_Outer:
        while (true) {
            boolean b = true;
            while (true) {
            Label_0064:
                while (true) {
                    synchronized (this.wG) {
                        if (this.zm == null) {
                            final boolean b2 = b;
                            zzac.zza(b2, (Object)"Cannot call andFinally() twice.");
                            if (this.zk == null) {
                                zzac.zza(b, (Object)"Cannot call then() and andFinally() on the same TransformedResult.");
                                this.zm = zm;
                                this.zzast();
                                return;
                            }
                            break Label_0064;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0033_Outer;
                }
                b = false;
                continue;
            }
        }
    }
    
    @Override
    public void onResult(final R r) {
        while (true) {
            synchronized (this.wG) {
                if (r.getStatus().isSuccess()) {
                    if (this.zk != null) {
                        zzrj.zzarz().submit(new Runnable() {
                            @WorkerThread
                            @Override
                            public void run() {
                                try {
                                    zzqe.wF.set(true);
                                    zzrp.this.zp.sendMessage(zzrp.this.zp.obtainMessage(0, (Object)zzrp.this.zk.onSuccess(r)));
                                }
                                catch (RuntimeException ex) {
                                    zzrp.this.zp.sendMessage(zzrp.this.zp.obtainMessage(1, (Object)ex));
                                    zzqe.wF.set(false);
                                    zzrp.this.zze(r);
                                    final GoogleApiClient googleApiClient = (GoogleApiClient)zzrp.this.wI.get();
                                    if (googleApiClient != null) {
                                        googleApiClient.zzb(zzrp.this);
                                    }
                                }
                                finally {
                                    zzqe.wF.set(false);
                                    zzrp.this.zze(r);
                                    final GoogleApiClient googleApiClient2 = (GoogleApiClient)zzrp.this.wI.get();
                                    if (googleApiClient2 != null) {
                                        googleApiClient2.zzb(zzrp.this);
                                    }
                                }
                            }
                        });
                    }
                    else if (this.zzasv()) {
                        this.zm.onSuccess((Object)r);
                    }
                    return;
                }
            }
            this.zzac(r.getStatus());
            this.zze(r);
        }
    }
    
    @NonNull
    @Override
    public <S extends Result> TransformedResult<S> then(@NonNull final ResultTransform<? super R, ? extends S> zk) {
    Label_0033_Outer:
        while (true) {
            boolean b = true;
            while (true) {
            Label_0085:
                while (true) {
                    synchronized (this.wG) {
                        if (this.zk == null) {
                            final boolean b2 = b;
                            zzac.zza(b2, (Object)"Cannot call then() twice.");
                            if (this.zm == null) {
                                zzac.zza(b, (Object)"Cannot call then() and andFinally() on the same TransformedResult.");
                                this.zk = zk;
                                final zzrp<Result> zl = new zzrp<Result>(this.wI);
                                this.zl = zl;
                                this.zzast();
                                return (TransformedResult<S>)zl;
                            }
                            break Label_0085;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0033_Outer;
                }
                b = false;
                continue;
            }
        }
    }
    
    public void zza(final PendingResult<?> zn) {
        synchronized (this.wG) {
            this.zn = (PendingResult<R>)zn;
            this.zzast();
        }
    }
    
    void zzasu() {
        this.zm = null;
    }
    
    private final class zza extends Handler
    {
        public zza(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.e("TransformedResultImpl", new StringBuilder(70).append("TransformationResultHandler received unknown message type: ").append(message.what).toString());
                    break;
                }
                case 0: {
                    final PendingResult pendingResult = (PendingResult)message.obj;
                    final Object zzf = zzrp.this.wG;
                    // monitorenter(zzf)
                    while (true) {
                        Label_0124: {
                            if (pendingResult != null) {
                                break Label_0124;
                            }
                            try {
                                zzrp.this.zl.zzac(new Status(13, "Transform returned null"));
                                break;
                            }
                            finally {
                            }
                            // monitorexit(zzf)
                        }
                        if (pendingResult instanceof zzrk) {
                            zzrp.this.zl.zzac(((zzrk<?>)pendingResult).getStatus());
                            continue;
                        }
                        zzrp.this.zl.zza(pendingResult);
                        continue;
                    }
                }
                case 1: {
                    final RuntimeException ex = (RuntimeException)message.obj;
                    final String value = String.valueOf(ex.getMessage());
                    String concat;
                    if (value.length() != 0) {
                        concat = "Runtime exception on the transformation worker thread: ".concat(value);
                    }
                    else {
                        concat = new String("Runtime exception on the transformation worker thread: ");
                    }
                    Log.e("TransformedResultImpl", concat);
                    throw ex;
                }
            }
        }
    }
}
