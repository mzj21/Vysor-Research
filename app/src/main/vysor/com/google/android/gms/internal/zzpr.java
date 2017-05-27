// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.common.api.Result;
import java.util.concurrent.Executors;
import android.os.Process;
import java.util.concurrent.ThreadFactory;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import android.util.Log;
import com.google.android.gms.common.util.zzh;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.util.zze;
import java.util.concurrent.ScheduledFuture;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.ScheduledExecutorService;
import com.google.android.gms.clearcut.zzc;

public class zzpr implements com.google.android.gms.clearcut.zzc
{
    private static final Object um;
    private static ScheduledExecutorService un;
    private static final zze uo;
    private static final long up;
    private GoogleApiClient kv;
    private final zza uq;
    private final Object ur;
    private long us;
    private final long ut;
    private ScheduledFuture<?> uu;
    private final Runnable uv;
    private final com.google.android.gms.common.util.zze zzapy;
    
    static {
        um = new Object();
        uo = new zze();
        up = TimeUnit.MILLISECONDS.convert(2L, TimeUnit.MINUTES);
    }
    
    public zzpr() {
        this(new zzh(), zzpr.up, (zza)new zzb());
    }
    
    public zzpr(final com.google.android.gms.common.util.zze zzapy, final long ut, final zza uq) {
        this.ur = new Object();
        this.us = 0L;
        this.uu = null;
        this.kv = null;
        this.uv = new Runnable() {
            @Override
            public void run() {
                synchronized (zzpr.this.ur) {
                    if (zzpr.zzb(zzpr.this) <= zzpr.this.zzapy.elapsedRealtime() && zzpr.this.kv != null) {
                        Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
                        zzpr.this.kv.disconnect();
                        zzpr.this.kv = null;
                    }
                }
            }
        };
        this.zzapy = zzapy;
        this.ut = ut;
        this.uq = uq;
    }
    
    private PendingResult<Status> zza(final GoogleApiClient googleApiClient, final zzc<Status> zzc) {
        this.zzaoz().execute(new Runnable() {
            @Override
            public void run() {
                googleApiClient.zzc(zzc);
            }
        });
        return (PendingResult<Status>)zzc;
    }
    
    private static void zza(final LogEventParcelable logEventParcelable) {
        if (logEventParcelable.uk != null && logEventParcelable.uj.brh.length == 0) {
            logEventParcelable.uj.brh = logEventParcelable.uk.zzaoy();
        }
        if (logEventParcelable.ul != null && logEventParcelable.uj.bro.length == 0) {
            logEventParcelable.uj.bro = logEventParcelable.ul.zzaoy();
        }
        logEventParcelable.ud = zzark.zzf(logEventParcelable.uj);
    }
    
    private ScheduledExecutorService zzaoz() {
        synchronized (zzpr.um) {
            if (zzpr.un == null) {
                zzpr.un = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                    @Override
                    public Thread newThread(final Runnable runnable) {
                        return new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Process.setThreadPriority(10);
                                runnable.run();
                            }
                        }, "ClearcutLoggerApiImpl");
                    }
                });
            }
            return zzpr.un;
        }
    }
    
    static /* synthetic */ long zzb(final zzpr zzpr) {
        return 0L;
    }
    
    private zzd zzb(final GoogleApiClient googleApiClient, final LogEventParcelable logEventParcelable) {
        zzpr.uo.increment();
        final zzd zzd = new zzd(logEventParcelable, googleApiClient);
        zzd.zza(new PendingResult.zza() {
            @Override
            public void zzv(final Status status) {
                zzpr.uo.decrement();
            }
        });
        return zzd;
    }
    
    @Override
    public PendingResult<Status> zza(final GoogleApiClient googleApiClient, final LogEventParcelable logEventParcelable) {
        return this.zza(googleApiClient, (zzc<Status>)this.zzb(googleApiClient, logEventParcelable));
    }
    
    public interface zza
    {
    }
    
    public static class zzb implements zza
    {
    }
    
    abstract static class zzc<R extends Result> extends zzqc.zza<R, zzps>
    {
        public zzc(final GoogleApiClient googleApiClient) {
            super(zzb.API, googleApiClient);
        }
    }
    
    static final class zzd extends zzc<Status>
    {
        private final LogEventParcelable uA;
        
        zzd(final LogEventParcelable ua, final GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.uA = ua;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof zzd && this.uA.equals(((zzd)o).uA);
        }
        
        @Override
        public String toString() {
            final String value = String.valueOf(this.uA);
            return new StringBuilder(12 + String.valueOf(value).length()).append("MethodImpl(").append(value).append(")").toString();
        }
        
        protected void zza(final zzps zzps) throws RemoteException {
            final zzpu.zza zza = new zzpu.zza() {
                public void zzw(final Status status) {
                    zzd.this.zzc((R)status);
                }
            };
            try {
                zza(this.uA);
                zzps.zza(zza, this.uA);
            }
            catch (RuntimeException ex) {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", (Throwable)ex);
                ((zzqc.zza)this).zzz(new Status(10, "MessageProducer"));
            }
        }
        
        protected Status zzb(final Status status) {
            return status;
        }
    }
    
    private static final class zze
    {
        private int mSize;
        
        private zze() {
            this.mSize = 0;
        }
        
        public void decrement() {
            synchronized (this) {
                if (this.mSize == 0) {
                    throw new RuntimeException("too many decrements");
                }
            }
            --this.mSize;
            if (this.mSize == 0) {
                this.notifyAll();
            }
        }
        // monitorexit(this)
        
        public void increment() {
            synchronized (this) {
                ++this.mSize;
            }
        }
    }
}
