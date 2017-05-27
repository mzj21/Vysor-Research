// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import java.util.Set;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.Status;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.zzc;

public final class zzqb extends zzqe<zzc>
{
    private int wv;
    private boolean ww;
    
    private void zza(final ConnectionResult connectionResult) {
        for (int i = 0; i < null.size(); ++i) {
            this.zza(null.keyAt(i), connectionResult);
        }
    }
    
    public void zza(final zzpz<?> zzpz, final ConnectionResult connectionResult) {
        synchronized (null) {
            null.put(zzpz, connectionResult);
            --this.wv;
            if (!connectionResult.isSuccess()) {
                this.ww = true;
            }
            if (this.wv == 0) {
                Status vy;
                if (this.ww) {
                    vy = new Status(13);
                }
                else {
                    vy = Status.vY;
                }
                zzc zzc;
                if (null.size() == 1) {
                    zzc = new com.google.android.gms.common.api.zzb(vy, null);
                }
                else {
                    zzc = new zzc(vy, null);
                }
                this.zzc(zzc);
            }
        }
    }
    
    public Set<zzpz<?>> zzaqm() {
        return null.keySet();
    }
    
    protected zzc zzy(final Status status) {
        synchronized (null) {
            this.zza(new ConnectionResult(8));
            zzc zzc;
            if (null.size() == 1) {
                zzc = new com.google.android.gms.common.api.zzb(status, null);
            }
            else {
                zzc = new zzc(status, null);
            }
            return zzc;
        }
    }
}
