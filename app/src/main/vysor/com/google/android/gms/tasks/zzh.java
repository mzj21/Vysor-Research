// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import android.support.annotation.MainThread;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.internal.zzrb;
import java.lang.ref.WeakReference;
import java.util.List;
import com.google.android.gms.internal.zzra;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.internal.zzac;

final class zzh<TResult> extends Task<TResult>
{
    private final zzg<TResult> aJJ;
    private boolean aJK;
    private TResult aJL;
    private Exception aJM;
    private final Object zzakd;
    
    zzh() {
        this.zzakd = new Object();
        this.aJJ = new zzg<TResult>();
    }
    
    private void zzclh() {
        zzac.zza(this.aJK, (Object)"Task is not yet complete");
    }
    
    private void zzcli() {
        zzac.zza(!this.aJK, (Object)"Task is already complete");
    }
    
    private void zzclj() {
        synchronized (this.zzakd) {
            if (this.aJK) {
                // monitorexit(this.zzakd)
                this.aJJ.zza(this);
            }
        }
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnCompleteListener(@NonNull final Activity activity, @NonNull final OnCompleteListener<TResult> onCompleteListener) {
        final zzc<TResult> zzc = new zzc<TResult>(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.aJJ.zza(zzc);
        zza.zzv(activity).zzb(zzc);
        this.zzclj();
        return this;
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnCompleteListener(@NonNull final OnCompleteListener<TResult> onCompleteListener) {
        return this.addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnCompleteListener(@NonNull final Executor executor, @NonNull final OnCompleteListener<TResult> onCompleteListener) {
        this.aJJ.zza(new zzc<TResult>(executor, onCompleteListener));
        this.zzclj();
        return this;
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnFailureListener(@NonNull final Activity activity, @NonNull final OnFailureListener onFailureListener) {
        final zzd<TResult> zzd = new zzd<TResult>(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.aJJ.zza(zzd);
        zza.zzv(activity).zzb(zzd);
        this.zzclj();
        return this;
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnFailureListener(@NonNull final OnFailureListener onFailureListener) {
        return this.addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnFailureListener(@NonNull final Executor executor, @NonNull final OnFailureListener onFailureListener) {
        this.aJJ.zza(new zzd<TResult>(executor, onFailureListener));
        this.zzclj();
        return this;
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnSuccessListener(@NonNull final Activity activity, @NonNull final OnSuccessListener<? super TResult> onSuccessListener) {
        final zze<TResult> zze = new zze<TResult>(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.aJJ.zza(zze);
        zza.zzv(activity).zzb(zze);
        this.zzclj();
        return this;
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnSuccessListener(@NonNull final OnSuccessListener<? super TResult> onSuccessListener) {
        return this.addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }
    
    @NonNull
    @Override
    public Task<TResult> addOnSuccessListener(@NonNull final Executor executor, @NonNull final OnSuccessListener<? super TResult> onSuccessListener) {
        this.aJJ.zza(new zze<TResult>(executor, onSuccessListener));
        this.zzclj();
        return this;
    }
    
    @NonNull
    @Override
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull final Continuation<TResult, TContinuationResult> continuation) {
        return this.continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }
    
    @NonNull
    @Override
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull final Executor executor, @NonNull final Continuation<TResult, TContinuationResult> continuation) {
        final zzh<Object> zzh = new zzh<Object>();
        this.aJJ.zza(new com.google.android.gms.tasks.zza<TResult, Object>(executor, (Continuation<Object, Object>)continuation, zzh));
        this.zzclj();
        return (Task<TContinuationResult>)zzh;
    }
    
    @NonNull
    @Override
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull final Continuation<TResult, Task<TContinuationResult>> continuation) {
        return this.continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }
    
    @NonNull
    @Override
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull final Executor executor, @NonNull final Continuation<TResult, Task<TContinuationResult>> continuation) {
        final zzh<Object> zzh = new zzh<Object>();
        this.aJJ.zza(new zzb<TResult, Object>(executor, (Continuation<Object, Task<Object>>)continuation, zzh));
        this.zzclj();
        return (Task<TContinuationResult>)zzh;
    }
    
    @Nullable
    @Override
    public Exception getException() {
        synchronized (this.zzakd) {
            return this.aJM;
        }
    }
    
    @Override
    public TResult getResult() {
        synchronized (this.zzakd) {
            this.zzclh();
            if (this.aJM != null) {
                throw new RuntimeExecutionException(this.aJM);
            }
        }
        // monitorexit(o)
        return this.aJL;
    }
    
    @Override
    public <X extends Throwable> TResult getResult(@NonNull final Class<X> clazz) throws X, Throwable {
        synchronized (this.zzakd) {
            this.zzclh();
            if (clazz.isInstance(this.aJM)) {
                throw clazz.cast(this.aJM);
            }
        }
        if (this.aJM != null) {
            throw new RuntimeExecutionException(this.aJM);
        }
        // monitorexit(o)
        return this.aJL;
    }
    
    @Override
    public boolean isComplete() {
        synchronized (this.zzakd) {
            return this.aJK;
        }
    }
    
    @Override
    public boolean isSuccessful() {
        while (true) {
            synchronized (this.zzakd) {
                if (this.aJK && this.aJM == null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public void setException(@NonNull final Exception ajm) {
        zzac.zzb(ajm, "Exception must not be null");
        synchronized (this.zzakd) {
            this.zzcli();
            this.aJK = true;
            this.aJM = ajm;
            // monitorexit(this.zzakd)
            this.aJJ.zza(this);
        }
    }
    
    public void setResult(final TResult ajl) {
        synchronized (this.zzakd) {
            this.zzcli();
            this.aJK = true;
            this.aJL = ajl;
            // monitorexit(this.zzakd)
            this.aJJ.zza(this);
        }
    }
    
    private static class zza extends zzra
    {
        private final List<WeakReference<zzf<?>>> mListeners;
        
        private zza(final zzrb zzrb) {
            super(zzrb);
            this.mListeners = new ArrayList<WeakReference<zzf<?>>>();
            this.yY.zza("TaskOnStopCallback", this);
        }
        
        public static zza zzv(final Activity activity) {
            final zzrb zzs = zzra.zzs(activity);
            zza zza = zzs.zza("TaskOnStopCallback", zza.class);
            if (zza == null) {
                zza = new zza(zzs);
            }
            return zza;
        }
        
        @MainThread
        @Override
        public void onStop() {
            synchronized (this.mListeners) {
                final Iterator<WeakReference<zzf<?>>> iterator = this.mListeners.iterator();
                while (iterator.hasNext()) {
                    final zzf zzf = iterator.next().get();
                    if (zzf != null) {
                        zzf.cancel();
                    }
                }
            }
            this.mListeners.clear();
        }
        // monitorexit(list)
        
        public <T> void zzb(final zzf<T> zzf) {
            synchronized (this.mListeners) {
                this.mListeners.add(new WeakReference<zzf<?>>(zzf));
            }
        }
    }
}
