// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;

public class zzqm implements zzqq
{
    private final zzqr xk;
    private boolean xl;
    
    public zzqm(final zzqr xk) {
        this.xl = false;
        this.xk = xk;
    }
    
    private <A extends Api.zzb> void zzf(final zzqc.zza<? extends Result, A> zza) throws DeadObjectException {
        this.xk.wV.yc.zzb(zza);
        Object o = this.xk.wV.zzb(zza.zzapp());
        if (!((Api.zze)o).isConnected() && this.xk.yl.containsKey(zza.zzapp())) {
            zza.zzz(new Status(17));
        }
        else {
            if (o instanceof zzai) {
                o = ((zzai)o).zzavk();
            }
            zza.zzb((zzai)o);
        }
    }
    
    @Override
    public void begin() {
    }
    
    @Override
    public void connect() {
        if (this.xl) {
            this.xl = false;
            this.xk.zza((zzqr.zza)new zzqr.zza(this) {
                public void zzari() {
                    zzqm.this.xk.yp.zzn(null);
                }
            });
        }
    }
    
    @Override
    public boolean disconnect() {
        boolean xl = true;
        if (this.xl) {
            xl = false;
        }
        else if (this.xk.wV.zzaru()) {
            this.xl = xl;
            final Iterator<zzrp> iterator = this.xk.wV.yb.iterator();
            while (iterator.hasNext()) {
                iterator.next().zzasu();
            }
            xl = false;
        }
        else {
            this.xk.zzi(null);
        }
        return xl;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.xk.zzi(null);
        this.xk.yp.zzc(n, this.xl);
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final Api<?> api, final int n) {
    }
    
    void zzarh() {
        if (this.xl) {
            this.xl = false;
            this.xk.wV.yc.release();
            this.disconnect();
        }
    }
    
    @Override
    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(final T t) {
        return this.zzd(t);
    }
    
    @Override
    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(final T t) {
        try {
            this.zzf((zzqc.zza<? extends Result, Api.zzb>)t);
            return t;
        }
        catch (DeadObjectException ex) {
            this.xk.zza((zzqr.zza)new zzqr.zza(this) {
                public void zzari() {
                    zzqm.this.onConnectionSuspended(1);
                }
            });
            return t;
        }
    }
}
