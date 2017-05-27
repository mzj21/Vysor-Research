// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public final class zzrz implements zzry
{
    @Override
    public PendingResult<Status> zzg(final GoogleApiClient googleApiClient) {
        return googleApiClient.zzd((PendingResult<Status>)new zzsa.zza(googleApiClient) {
            protected void zza(final zzsb zzsb) throws RemoteException {
                zzsb.zzatx().zza(new zzrz.zza((zzqc.zzb<Status>)this));
            }
        });
    }
    
    private static class zza extends zzrw
    {
        private final zzqc.zzb<Status> Dj;
        
        public zza(final zzqc.zzb<Status> dj) {
            this.Dj = dj;
        }
        
        @Override
        public void zzgw(final int n) throws RemoteException {
            this.Dj.setResult(new Status(n));
        }
    }
}
