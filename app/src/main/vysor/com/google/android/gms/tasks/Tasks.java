// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tasks;

import java.util.concurrent.CountDownLatch;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import com.google.android.gms.common.internal.zzac;
import android.support.annotation.NonNull;

public final class Tasks
{
    public static <TResult> TResult await(@NonNull final Task<TResult> task) throws ExecutionException, InterruptedException {
        zzac.zzavb();
        zzac.zzb(task, "Task must not be null");
        Object o;
        if (task.isComplete()) {
            o = zzb((Task<Object>)task);
        }
        else {
            final zza zza = new zza();
            zza(task, (zzb)zza);
            zza.await();
            o = zzb((Task<Object>)task);
        }
        return (TResult)o;
    }
    
    public static <TResult> TResult await(@NonNull final Task<TResult> task, final long n, @NonNull final TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        zzac.zzavb();
        zzac.zzb(task, "Task must not be null");
        zzac.zzb(timeUnit, "TimeUnit must not be null");
        TResult tResult;
        if (task.isComplete()) {
            tResult = zzb(task);
        }
        else {
            final zza zza = new zza();
            zza(task, (zzb)zza);
            if (!zza.await(n, timeUnit)) {
                throw new TimeoutException("Timed out waiting for Task");
            }
            tResult = zzb(task);
        }
        return tResult;
    }
    
    public static <TResult> Task<TResult> call(@NonNull final Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }
    
    public static <TResult> Task<TResult> call(@NonNull final Executor executor, @NonNull final Callable<TResult> callable) {
        zzac.zzb(executor, "Executor must not be null");
        zzac.zzb(callable, "Callback must not be null");
        final zzh<TResult> zzh = new zzh<TResult>();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    zzh.setResult(callable.call());
                }
                catch (Exception exception) {
                    zzh.setException(exception);
                }
            }
        });
        return zzh;
    }
    
    public static <TResult> Task<TResult> forException(@NonNull final Exception exception) {
        final zzh<TResult> zzh = new zzh<TResult>();
        zzh.setException(exception);
        return zzh;
    }
    
    public static <TResult> Task<TResult> forResult(final TResult result) {
        final zzh<TResult> zzh = new zzh<TResult>();
        zzh.setResult(result);
        return zzh;
    }
    
    public static Task<Void> whenAll(final Collection<? extends Task<?>> collection) {
        Task<Void> forResult;
        if (collection.isEmpty()) {
            forResult = forResult((Void)null);
        }
        else {
            final Iterator<? extends Task<?>> iterator = collection.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    throw new NullPointerException("null tasks are not accepted");
                }
            }
            final zzh<Void> zzh = new zzh<Void>();
            final zzc zzc = new zzc(collection.size(), zzh);
            final Iterator<? extends Task<?>> iterator2 = collection.iterator();
            while (iterator2.hasNext()) {
                zza((Task<?>)iterator2.next(), (zzb)zzc);
            }
            forResult = zzh;
        }
        return forResult;
    }
    
    public static Task<Void> whenAll(final Task<?>... array) {
        Task<Void> task;
        if (array.length == 0) {
            task = forResult((Void)null);
        }
        else {
            task = whenAll(Arrays.asList(array));
        }
        return task;
    }
    
    private static void zza(final Task<?> task, final zzb zzb) {
        task.addOnSuccessListener(TaskExecutors.aJI, zzb);
        task.addOnFailureListener(TaskExecutors.aJI, zzb);
    }
    
    private static <TResult> TResult zzb(final Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }
    
    private static final class zza implements zzb
    {
        private final CountDownLatch zzamx;
        
        private zza() {
            this.zzamx = new CountDownLatch(1);
        }
        
        public void await() throws InterruptedException {
            this.zzamx.await();
        }
        
        public boolean await(final long n, final TimeUnit timeUnit) throws InterruptedException {
            return this.zzamx.await(n, timeUnit);
        }
        
        @Override
        public void onFailure(@NonNull final Exception ex) {
            this.zzamx.countDown();
        }
        
        @Override
        public void onSuccess(final Object o) {
            this.zzamx.countDown();
        }
    }
    
    interface zzb extends OnFailureListener, OnSuccessListener<Object>
    {
    }
    
    private static final class zzc implements zzb
    {
        private final zzh<Void> aJH;
        private Exception aJM;
        private final int aJO;
        private int aJP;
        private int aJQ;
        private final Object zzakd;
        
        public zzc(final int ajo, final zzh<Void> ajh) {
            this.zzakd = new Object();
            this.aJO = ajo;
            this.aJH = ajh;
        }
        
        private void zzclk() {
            if (this.aJP + this.aJQ == this.aJO) {
                if (this.aJM == null) {
                    this.aJH.setResult(null);
                }
                else {
                    this.aJH.setException(new ExecutionException(new StringBuilder(54).append(this.aJQ).append(" out of ").append(this.aJO).append(" underlying tasks failed").toString(), this.aJM));
                }
            }
        }
        
        @Override
        public void onFailure(@NonNull final Exception ajm) {
            synchronized (this.zzakd) {
                ++this.aJQ;
                this.aJM = ajm;
                this.zzclk();
            }
        }
        
        @Override
        public void onSuccess(final Object o) {
            synchronized (this.zzakd) {
                ++this.aJP;
                this.zzclk();
            }
        }
    }
}
