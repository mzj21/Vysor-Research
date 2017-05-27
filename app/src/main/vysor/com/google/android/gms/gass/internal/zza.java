// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gass.internal;

import java.util.concurrent.TimeUnit;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import android.os.HandlerThread;
import java.util.concurrent.LinkedBlockingQueue;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.internal.zzae;
import android.content.Context;

public class zza
{
    public static zzae.zza zzi(final Context context, final String s, final String s2) {
        return new zza(context, s, s2).zzcp();
    }
    
    static class zza implements zzb, zzc
    {
        protected com.google.android.gms.gass.internal.zzb aev;
        private final String aew;
        private final LinkedBlockingQueue<zzae.zza> aex;
        private final HandlerThread aey;
        private final String packageName;
        
        public zza(final Context context, final String packageName, final String aew) {
            this.packageName = packageName;
            this.aew = aew;
            (this.aey = new HandlerThread("GassClient")).start();
            this.aev = new com.google.android.gms.gass.internal.zzb(context, this.aey.getLooper(), this, this);
            this.aex = new LinkedBlockingQueue<zzae.zza>();
            this.connect();
        }
        
        protected void connect() {
            this.aev.zzatu();
        }
        
        @Override
        public void onConnected(final Bundle bundle) {
            final zze zzbnt = this.zzbnt();
            if (zzbnt == null) {
                return;
            }
            try {
                this.aex.put(zzbnt.zza(new GassRequestParcel(this.packageName, this.aew)).zzbnw());
            }
            catch (Throwable t) {
                this.zzrv();
                this.aey.quit();
            }
            finally {
                this.zzrv();
                this.aey.quit();
            }
        }
        
        @Override
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            try {
                this.aex.put(new zzae.zza());
            }
            catch (InterruptedException ex) {}
        }
        
        @Override
        public void onConnectionSuspended(final int n) {
            try {
                this.aex.put(new zzae.zza());
            }
            catch (InterruptedException ex) {}
        }
        
        protected zze zzbnt() {
            try {
                return this.aev.zzbnu();
            }
            catch (IllegalStateException ex) {}
            catch (DeadObjectException ex2) {
                goto Label_0015;
            }
        }
        
        public zzae.zza zzcp() {
            return this.zzth(2000);
        }
        
        public void zzrv() {
            if (this.aev != null && (this.aev.isConnected() || this.aev.isConnecting())) {
                this.aev.disconnect();
            }
        }
        
        public zzae.zza zzth(final int n) {
            while (true) {
                try {
                    zzae.zza zza = this.aex.poll(n, TimeUnit.MILLISECONDS);
                    if (zza == null) {
                        zza = new zzae.zza();
                    }
                    return zza;
                }
                catch (InterruptedException ex) {
                    final zzae.zza zza = null;
                    continue;
                }
                break;
            }
        }
    }
}
