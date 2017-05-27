// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.DeadObjectException;
import android.os.Binder;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzdb;
import com.google.android.gms.internal.zzdi;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkt;

@zziy
public abstract class zzd implements zzc.zza, zzkt<Void>
{
    private final Object zzakd;
    private final zzlm<AdRequestInfoParcel> zzcfm;
    private final zzc.zza zzcfn;
    
    public zzd(final zzlm<AdRequestInfoParcel> zzcfm, final zzc.zza zzcfn) {
        this.zzakd = new Object();
        this.zzcfm = zzcfm;
        this.zzcfn = zzcfn;
    }
    
    @Override
    public void cancel() {
        this.zzrv();
    }
    
    boolean zza(final zzk zzk, final AdRequestInfoParcel adRequestInfoParcel) {
        try {
            zzk.zza(adRequestInfoParcel, new zzg(this));
            return true;
        }
        catch (RemoteException ex) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service.", (Throwable)ex);
            zzu.zzgd().zza((Throwable)ex, "AdRequestClientTask.getAdResponseFromService");
        }
        catch (NullPointerException ex2) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", ex2);
            zzu.zzgd().zza(ex2, "AdRequestClientTask.getAdResponseFromService");
            goto Label_0040;
        }
        catch (SecurityException ex3) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", ex3);
            zzu.zzgd().zza(ex3, "AdRequestClientTask.getAdResponseFromService");
            goto Label_0040;
        }
        catch (Throwable t) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", t);
            zzu.zzgd().zza(t, "AdRequestClientTask.getAdResponseFromService");
            goto Label_0040;
        }
    }
    
    @Override
    public void zzb(final AdResponseParcel adResponseParcel) {
        synchronized (this.zzakd) {
            this.zzcfn.zzb(adResponseParcel);
            this.zzrv();
        }
    }
    
    public Void zzqt() {
        final zzk zzrw = this.zzrw();
        if (zzrw == null) {
            this.zzcfn.zzb(new AdResponseParcel(0));
            this.zzrv();
        }
        else {
            this.zzcfm.zza((zzlm.zzc<AdRequestInfoParcel>)new zzlm.zzc<AdRequestInfoParcel>() {
                public void zzc(final AdRequestInfoParcel adRequestInfoParcel) {
                    if (!zzd.this.zza(zzrw, adRequestInfoParcel)) {
                        zzd.this.zzrv();
                    }
                }
            }, (zzlm.zza)new zzlm.zza() {
                @Override
                public void run() {
                    zzd.this.zzrv();
                }
            });
        }
        return null;
    }
    
    @Override
    public /* synthetic */ Object zzqw() {
        return this.zzqt();
    }
    
    public abstract void zzrv();
    
    public abstract zzk zzrw();
    
    @zziy
    public static final class zza extends zzd
    {
        private final Context mContext;
        
        public zza(final Context mContext, final zzlm<AdRequestInfoParcel> zzlm, final zzc.zza zza) {
            super(zzlm, zza);
            this.mContext = mContext;
        }
        
        @Override
        public void zzrv() {
        }
        
        @Override
        public zzk zzrw() {
            return zzjb.zza(this.mContext, new zzdb(zzdi.zzbao.get()), zzja.zzsc());
        }
    }
    
    @zziy
    public static class zzb extends zzd implements com.google.android.gms.common.internal.zze.zzb, zze.zzc
    {
        private Context mContext;
        private final Object zzakd;
        private VersionInfoParcel zzanh;
        private zzlm<AdRequestInfoParcel> zzcfm;
        private final zzc.zza zzcfn;
        protected zze zzcfq;
        private boolean zzcfr;
        
        public zzb(final Context mContext, final VersionInfoParcel zzanh, final zzlm<AdRequestInfoParcel> zzcfm, final zzc.zza zzcfn) {
            super(zzcfm, zzcfn);
            this.zzakd = new Object();
            this.mContext = mContext;
            this.zzanh = zzanh;
            this.zzcfm = zzcfm;
            this.zzcfn = zzcfn;
            Looper looper;
            if (zzdi.zzbbu.get()) {
                this.zzcfr = true;
                looper = zzu.zzgp().zzuy();
            }
            else {
                looper = mContext.getMainLooper();
            }
            this.zzcfq = new zze(mContext, looper, this, this, this.zzanh.zzctt);
            this.connect();
        }
        
        protected void connect() {
            this.zzcfq.zzatu();
        }
        
        @Override
        public void onConnected(final Bundle bundle) {
            final Void void1 = (Void)this.zzqw();
        }
        
        @Override
        public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Cannot connect to remote service, fallback to local instance.");
            this.zzrx().zzqw();
            final Bundle bundle = new Bundle();
            bundle.putString("action", "gms_connection_failed_fallback_to_local");
            zzu.zzfz().zzb(this.mContext, this.zzanh.zzcs, "gmob-apps", bundle, true);
        }
        
        @Override
        public void onConnectionSuspended(final int n) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Disconnected from remote ad request service.");
        }
        
        @Override
        public void zzrv() {
            synchronized (this.zzakd) {
                if (this.zzcfq.isConnected() || this.zzcfq.isConnecting()) {
                    this.zzcfq.disconnect();
                }
                Binder.flushPendingCommands();
                if (this.zzcfr) {
                    zzu.zzgp().zzuz();
                    this.zzcfr = false;
                }
            }
        }
        
        @Override
        public com.google.android.gms.ads.internal.request.zzk zzrw() {
            final Object zzakd = this.zzakd;
            // monitorenter(zzakd)
            while (true) {
                try {
                    try {
                        this.zzcfq.zzry();
                        goto Label_0042;
                    }
                    // monitorexit(zzakd)
                    finally {
                    }
                    // monitorexit(zzakd)
                }
                catch (DeadObjectException ex) {
                    continue;
                }
                catch (IllegalStateException ex2) {
                    continue;
                }
                break;
            }
        }
        
        zzkt zzrx() {
            return new zza(this.mContext, this.zzcfm, this.zzcfn);
        }
    }
}
