// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;

public class zzqc
{
    public abstract static class zza<R extends Result, A extends Api.zzb> extends zzqe<R> implements zzqc.zzb<R>
    {
        private final Api<?> tv;
        private final Api.zzc<A> wx;
        
        protected zza(final Api.zzc<A> zzc, final GoogleApiClient googleApiClient) {
            super(zzac.zzb(googleApiClient, "GoogleApiClient must not be null"));
            this.wx = (Api.zzc<A>)zzac.zzy((Api.zzc)zzc);
            this.tv = null;
        }
        
        protected zza(final Api<?> tv, final GoogleApiClient googleApiClient) {
            super(zzac.zzb(googleApiClient, "GoogleApiClient must not be null"));
            this.wx = (Api.zzc<A>)tv.zzapp();
            this.tv = tv;
        }
        
        private void zza(final RemoteException ex) {
            this.zzz(new Status(8, ex.getLocalizedMessage(), null));
        }
        
        protected abstract void zza(final A p0) throws RemoteException;
        
        public final Api.zzc<A> zzapp() {
            return this.wx;
        }
        
        public final Api<?> zzaqn() {
            return this.tv;
        }
        
        public final void zzb(final A a) throws DeadObjectException {
            try {
                this.zza(a);
            }
            catch (DeadObjectException ex) {
                this.zza((RemoteException)ex);
                throw ex;
            }
            catch (RemoteException ex2) {
                this.zza(ex2);
            }
        }
        
        protected void zzb(final R r) {
        }
        
        @Override
        public final void zzz(final Status status) {
            zzac.zzb(!status.isSuccess(), (Object)"Failed result must not be success");
            final Result zzc = this.zzc(status);
            this.zzc((R)zzc);
            this.zzb((R)zzc);
        }
    }
    
    public interface zzb<R>
    {
        void setResult(final R p0);
        
        void zzz(final Status p0);
    }
}
