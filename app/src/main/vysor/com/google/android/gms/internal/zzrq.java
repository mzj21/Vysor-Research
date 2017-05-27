// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import android.util.Log;
import java.io.PrintWriter;
import android.os.RemoteException;
import android.os.IBinder$DeathRecipient;
import android.os.IBinder;
import com.google.android.gms.common.api.zzf;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.Set;
import java.util.Map;
import com.google.android.gms.common.api.Api;

public class zzrq
{
    private static final zzqe<?>[] zt;
    private final Api.zze vC;
    private final Map<Api.zzc<?>, Api.zze> xW;
    final Set<zzqe<?>> zu;
    private final zzb zv;
    private zzc zw;
    
    static {
        zt = new zzqe[0];
    }
    
    public zzrq(final Api.zze vc) {
        this.zu = Collections.synchronizedSet((Set<zzqe<?>>)Collections.newSetFromMap((Map<T, Boolean>)new WeakHashMap<Object, Boolean>()));
        this.zv = (zzb)new zzb() {
            @Override
            public void zzc(final zzqe<?> zzqe) {
                zzrq.this.zu.remove(zzqe);
                if (zzqe.zzaqf() != null && zzrq.zza(zzrq.this) != null) {
                    zzrq.zza(zzrq.this).remove(zzqe.zzaqf());
                }
                if (zzrq.this.zw != null && zzrq.this.zu.isEmpty()) {
                    zzrq.this.zw.zzask();
                }
            }
        };
        this.zw = null;
        this.xW = null;
        this.vC = vc;
    }
    
    public zzrq(final Map<Api.zzc<?>, Api.zze> xw) {
        this.zu = Collections.synchronizedSet((Set<zzqe<?>>)Collections.newSetFromMap((Map<T, Boolean>)new WeakHashMap<Object, Boolean>()));
        this.zv = (zzb)new zzb() {
            @Override
            public void zzc(final zzqe<?> zzqe) {
                zzrq.this.zu.remove(zzqe);
                if (zzqe.zzaqf() != null && zzrq.zza(zzrq.this) != null) {
                    zzrq.zza(zzrq.this).remove(zzqe.zzaqf());
                }
                if (zzrq.this.zw != null && zzrq.this.zu.isEmpty()) {
                    zzrq.this.zw.zzask();
                }
            }
        };
        this.zw = null;
        this.xW = xw;
        this.vC = null;
    }
    
    static /* synthetic */ zzf zza(final zzrq zzrq) {
        return null;
    }
    
    private static void zza(final zzqe<?> zzqe, final zzf zzf, final IBinder binder) {
        if (zzqe.isReady()) {
            zzqe.zza(new zza((zzqe)zzqe, zzf, binder));
        }
        else if (binder != null && binder.isBinderAlive()) {
            final zza zza = new zza((zzqe)zzqe, zzf, binder);
            zzqe.zza(zza);
            try {
                binder.linkToDeath((IBinder$DeathRecipient)zza, 0);
            }
            catch (RemoteException ex) {
                zzqe.cancel();
                zzf.remove(zzqe.zzaqf());
            }
        }
        else {
            zzqe.zza((zzb)null);
            zzqe.cancel();
            zzf.remove(zzqe.zzaqf());
        }
    }
    
    public void dump(final PrintWriter printWriter) {
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zu.size());
    }
    
    public void release() {
        for (final zzqe<?> zzqe : this.zu.toArray(zzrq.zt)) {
            zzqe.zza((zzb)null);
            if (zzqe.zzaqf() == null) {
                if (zzqe.zzaqq()) {
                    this.zu.remove(zzqe);
                }
            }
            else {
                zzqe.zzaqs();
                IBinder binder;
                if (this.vC != null) {
                    binder = this.vC.zzaps();
                }
                else if (this.xW != null) {
                    binder = this.xW.get(((zzqc.zza<?, ?>)zzqe).zzapp()).zzaps();
                }
                else {
                    Log.wtf("UnconsumedApiCalls", "Could not get service broker binder", (Throwable)new Exception());
                    binder = null;
                }
                zza(zzqe, null, binder);
                this.zu.remove(zzqe);
            }
        }
    }
    
    public void zza(final zzc zw) {
        if (this.zu.isEmpty()) {
            zw.zzask();
        }
        this.zw = zw;
    }
    
    public void zzasw() {
        final zzqe<?>[] array = this.zu.toArray(zzrq.zt);
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].zzaa(new Status(8, "The connection to Google Play services was lost"));
        }
    }
    
    public boolean zzasx() {
        final zzqe<?>[] array = this.zu.toArray(zzrq.zt);
        for (int length = array.length, i = 0; i < length; ++i) {
            if (!array[i].isReady()) {
                return true;
            }
        }
        return false;
    }
    
    void zzb(final zzqe<? extends Result> zzqe) {
        this.zu.add(zzqe);
        zzqe.zza(this.zv);
    }
    
    private static class zza implements IBinder$DeathRecipient, zzb
    {
        private final WeakReference<IBinder> zA;
        private final WeakReference<zzqe<?>> zy;
        private final WeakReference<zzf> zz;
        
        private zza(final zzqe<?> zzqe, final zzf zzf, final IBinder binder) {
            this.zz = new WeakReference<zzf>(zzf);
            this.zy = new WeakReference<zzqe<?>>(zzqe);
            this.zA = new WeakReference<IBinder>(binder);
        }
        
        private void zzasd() {
            final zzqe zzqe = this.zy.get();
            final zzf zzf = this.zz.get();
            if (zzf != null && zzqe != null) {
                zzf.remove(zzqe.zzaqf());
            }
            final IBinder binder = this.zA.get();
            if (binder != null) {
                binder.unlinkToDeath((IBinder$DeathRecipient)this, 0);
            }
        }
        
        public void binderDied() {
            this.zzasd();
        }
        
        public void zzc(final zzqe<?> zzqe) {
            this.zzasd();
        }
    }
    
    interface zzb
    {
        void zzc(final zzqe<?> p0);
    }
    
    interface zzc
    {
        void zzask();
    }
}
