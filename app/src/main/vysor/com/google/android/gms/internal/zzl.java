// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.Set;
import java.util.Queue;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class zzl
{
    private AtomicInteger zzax;
    private final Map<String, Queue<zzk<?>>> zzay;
    private final Set<zzk<?>> zzaz;
    private final PriorityBlockingQueue<zzk<?>> zzba;
    private final PriorityBlockingQueue<zzk<?>> zzbb;
    private zzg[] zzbc;
    private zzc zzbd;
    private List<zza> zzbe;
    private final zzb zzi;
    private final zzn zzj;
    private final zzf zzy;
    
    public zzl(final zzb zzb, final zzf zzf) {
        this(zzb, zzf, 4);
    }
    
    public zzl(final zzb zzb, final zzf zzf, final int n) {
        this(zzb, zzf, n, new zze(new Handler(Looper.getMainLooper())));
    }
    
    public zzl(final zzb zzi, final zzf zzy, final int n, final zzn zzj) {
        this.zzax = new AtomicInteger();
        this.zzay = new HashMap<String, Queue<zzk<?>>>();
        this.zzaz = new HashSet<zzk<?>>();
        this.zzba = new PriorityBlockingQueue<zzk<?>>();
        this.zzbb = new PriorityBlockingQueue<zzk<?>>();
        this.zzbe = new ArrayList<zza>();
        this.zzi = zzi;
        this.zzy = zzy;
        this.zzbc = new zzg[n];
        this.zzj = zzj;
    }
    
    public int getSequenceNumber() {
        return this.zzax.incrementAndGet();
    }
    
    public void start() {
        this.stop();
        (this.zzbd = new zzc(this.zzba, this.zzbb, this.zzi, this.zzj)).start();
        for (int i = 0; i < this.zzbc.length; ++i) {
            (this.zzbc[i] = new zzg(this.zzbb, this.zzy, this.zzi, this.zzj)).start();
        }
    }
    
    public void stop() {
        if (this.zzbd != null) {
            this.zzbd.quit();
        }
        for (int i = 0; i < this.zzbc.length; ++i) {
            if (this.zzbc[i] != null) {
                this.zzbc[i].quit();
            }
        }
    }
    
    public <T> zzk<T> zze(final zzk<T> zzk) {
        while (true) {
            zzk.zza(this);
            synchronized (this.zzaz) {
                this.zzaz.add(zzk);
                // monitorexit(this.zzaz)
                zzk.zza(this.getSequenceNumber());
                zzk.zzc("add-to-queue");
                if (!zzk.zzq()) {
                    this.zzbb.add(zzk);
                    return zzk;
                }
            }
            while (true) {
                while (true) {
                    final String zzg;
                    synchronized (this.zzay) {
                        zzg = zzk.zzg();
                        if (this.zzay.containsKey(zzg)) {
                            Queue<zzk<?>> queue = this.zzay.get(zzg);
                            if (queue == null) {
                                queue = new LinkedList<zzk<?>>();
                            }
                            queue.add(zzk);
                            this.zzay.put(zzg, queue);
                            if (zzs.DEBUG) {
                                zzs.zza("Request for cacheKey=%s is in flight, putting on hold.", zzg);
                            }
                            return zzk;
                        }
                    }
                    this.zzay.put(zzg, null);
                    this.zzba.add(zzk);
                    continue;
                }
            }
        }
    }
    
     <T> void zzf(final zzk<T> zzk) {
        synchronized (this.zzaz) {
            this.zzaz.remove(zzk);
            // monitorexit(this.zzaz)
            synchronized (this.zzbe) {
                final Iterator<zza<T>> iterator = this.zzbe.iterator();
                while (iterator.hasNext()) {
                    iterator.next().zzg(zzk);
                }
            }
        }
        // monitorexit(list)
        if (zzk.zzq()) {
            synchronized (this.zzay) {
                final String zzg = zzk.zzg();
                final Queue<zzk<?>> queue = this.zzay.remove(zzg);
                if (queue != null) {
                    if (zzs.DEBUG) {
                        zzs.zza("Releasing %d waiting requests for cacheKey=%s.", queue.size(), zzg);
                    }
                    this.zzba.addAll((Collection<?>)queue);
                }
            }
        }
    }
    
    public interface zza<T>
    {
        void zzg(final zzk<T> p0);
    }
}
