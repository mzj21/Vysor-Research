// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseException;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zza;
import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api;
import android.util.SparseArray;

public abstract class zzpy
{
    public final int lN;
    public final int wf;
    
    public zzpy(final int wf, final int ln) {
        this.wf = wf;
        this.lN = ln;
    }
    
    public boolean cancel() {
        return true;
    }
    
    public void zza(final SparseArray<zzrq> sparseArray) {
    }
    
    public abstract void zzb(final Api.zzb p0) throws DeadObjectException;
    
    public abstract void zzx(@NonNull final Status p0);
    
    private abstract static class zza extends zzpy
    {
        protected final SparseArray<Map<zzrd.zzb<?>, zzri>> wg;
        protected final TaskCompletionSource<Void> wh;
        
        public zza(final int n, final int n2, final TaskCompletionSource<Void> wh, final SparseArray<Map<zzrd.zzb<?>, zzri>> wg) {
            super(n, n2);
            this.wg = wg;
            this.wh = wh;
        }
        
        private void zza(final RemoteException ex) {
            this.zzx(new Status(8, ex.getLocalizedMessage(), null));
        }
        
        @Override
        public boolean cancel() {
            this.wh.setException(new com.google.android.gms.common.api.zza(Status.wc));
            return true;
        }
        
        @Override
        public void zza(final SparseArray<zzrq> sparseArray) {
        }
        
        protected abstract void zza(final Api.zzb p0) throws RemoteException;
        
        @Override
        public final void zzb(final Api.zzb zzb) throws DeadObjectException {
            try {
                this.zza(zzb);
            }
            catch (DeadObjectException ex) {
                this.zza((RemoteException)ex);
                throw ex;
            }
            catch (RemoteException ex2) {
                this.zza(ex2);
            }
        }
        
        @Override
        public void zzx(@NonNull final Status status) {
            this.wh.setException(new com.google.android.gms.common.api.zza(status));
        }
    }
    
    public static class zzb<A extends zzqc.zza<? extends Result, Api.zzb>> extends zzpy
    {
        protected final A wi;
        
        public zzb(final int n, final int n2, final A wi) {
            super(n, n2);
            this.wi = wi;
        }
        
        @Override
        public boolean cancel() {
            return this.wi.zzaqq();
        }
        
        @Override
        public void zza(final SparseArray<zzrq> sparseArray) {
            final zzrq zzrq = (zzrq)sparseArray.get(this.wf);
            if (zzrq != null) {
                zzrq.zzb(this.wi);
            }
        }
        
        @Override
        public void zzb(final Api.zzb zzb) throws DeadObjectException {
            ((zzqc.zza<R, Api.zzb>)this.wi).zzb(zzb);
        }
        
        @Override
        public void zzx(@NonNull final Status status) {
            ((zzqc.zza)this.wi).zzz(status);
        }
    }
    
    public static final class zzc extends zza
    {
        public final zzrh<Api.zzb> wj;
        public final zzrr<Api.zzb> wk;
        
        public zzc(final int n, final zzri zzri, final TaskCompletionSource<Void> taskCompletionSource, final SparseArray<Map<zzrd.zzb<?>, zzri>> sparseArray) {
            super(n, 3, taskCompletionSource, sparseArray);
            this.wj = zzri.wj;
            this.wk = zzri.wk;
        }
        
        public void zza(final Api.zzb zzb) throws DeadObjectException {
            this.wj.zza(zzb, this.wh);
            Map<zzrd.zzb, zzri> map = (Map<zzrd.zzb, zzri>)this.wg.get(this.wf);
            if (map == null) {
                map = new ArrayMap<zzrd.zzb, zzri>(1);
                this.wg.put(this.wf, (Object)map);
            }
            final String value = String.valueOf(this.wj.zzasr());
            Log.d("reg", new StringBuilder(12 + String.valueOf(value).length()).append("registered: ").append(value).toString());
            if (this.wj.zzasr() != null) {
                map.put((zzrd.zzb)this.wj.zzasr(), new zzri(this.wj, this.wk));
            }
        }
    }
    
    public static final class zzd<TResult> extends zzpy
    {
        private static final Status wm;
        private final TaskCompletionSource<TResult> wh;
        private final zzro<Api.zzb, TResult> wl;
        
        static {
            wm = new Status(8, "Connection to Google Play services was lost while executing the API call.");
        }
        
        public zzd(final int n, final int n2, final zzro<Api.zzb, TResult> wl, final TaskCompletionSource<TResult> wh) {
            super(n, n2);
            this.wh = wh;
            this.wl = wl;
        }
        
        @Override
        public void zzb(final Api.zzb zzb) throws DeadObjectException {
            try {
                this.wl.zzb(zzb, this.wh);
            }
            catch (DeadObjectException ex) {
                this.zzx(zzd.wm);
                throw ex;
            }
            catch (RemoteException ex2) {
                this.zzx(zzd.wm);
            }
        }
        
        @Override
        public void zzx(@NonNull final Status status) {
            if (status.getStatusCode() == 8) {
                this.wh.setException(new FirebaseException(status.getStatusMessage()));
            }
            else {
                this.wh.setException(new FirebaseApiNotAvailableException(status.getStatusMessage()));
            }
        }
    }
    
    public static final class zze extends zza
    {
        public final zzrr<Api.zzb> wn;
        
        public zze(final int n, final zzrr<Api.zzb> wn, final TaskCompletionSource<Void> taskCompletionSource, final SparseArray<Map<zzrd.zzb<?>, zzri>> sparseArray) {
            super(n, 4, taskCompletionSource, sparseArray);
            this.wn = wn;
        }
        
        public void zza(final Api.zzb zzb) throws DeadObjectException {
            final Map map = (Map)this.wg.get(this.wf);
            if (map != null && this.wn.zzasr() != null) {
                map.remove(this.wn.zzasr());
                this.wn.zzc(zzb, this.wh);
            }
            else {
                Log.wtf("UnregisterListenerTask", "Received call to unregister a listener without a matching registration call.", (Throwable)new Exception());
                this.wh.setException(new com.google.android.gms.common.api.zza(Status.wa));
            }
        }
    }
}
