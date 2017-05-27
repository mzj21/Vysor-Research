// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.internal.zzqe;
import com.google.android.gms.internal.zzrg;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzrm;
import android.os.Looper;

public final class PendingResults
{
    public static PendingResult<Status> canceledPendingResult() {
        final zzrm zzrm = new zzrm(Looper.getMainLooper());
        zzrm.cancel();
        return zzrm;
    }
    
    public static <R extends Result> PendingResult<R> canceledPendingResult(final R r) {
        zzac.zzb(r, "Result must not be null");
        zzac.zzb(r.getStatus().getStatusCode() == 16, (Object)"Status code must be CommonStatusCodes.CANCELED");
        final zza zza = new zza(r);
        zza.cancel();
        return (PendingResult<R>)zza;
    }
    
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(final R r) {
        zzac.zzb(r, "Result must not be null");
        final zzc<Result> zzc = new zzc<Result>((GoogleApiClient)null);
        zzc.zzc(r);
        return new zzrg<R>((PendingResult<R>)zzc);
    }
    
    public static PendingResult<Status> immediatePendingResult(final Status status) {
        zzac.zzb(status, "Result must not be null");
        final zzrm zzrm = new zzrm(Looper.getMainLooper());
        zzrm.zzc(status);
        return zzrm;
    }
    
    public static <R extends Result> PendingResult<R> zza(final R r, final GoogleApiClient googleApiClient) {
        zzac.zzb(r, "Result must not be null");
        zzac.zzb(!r.getStatus().isSuccess(), (Object)"Status code must not be SUCCESS");
        final zzb<R> zzb = new zzb<R>(googleApiClient, r);
        zzb.zzc(r);
        return zzb;
    }
    
    public static PendingResult<Status> zza(final Status status, final GoogleApiClient googleApiClient) {
        zzac.zzb(status, "Result must not be null");
        final zzrm zzrm = new zzrm(googleApiClient);
        zzrm.zzc(status);
        return zzrm;
    }
    
    public static <R extends Result> OptionalPendingResult<R> zzb(final R r, final GoogleApiClient googleApiClient) {
        zzac.zzb(r, "Result must not be null");
        final zzc<Result> zzc = new zzc<Result>(googleApiClient);
        zzc.zzc(r);
        return new zzrg<R>((PendingResult<R>)zzc);
    }
    
    private static final class zza<R extends Result> extends zzqe<R>
    {
        private final R vT;
        
        public zza(final R vt) {
            super(Looper.getMainLooper());
            this.vT = vt;
        }
        
        @Override
        protected R zzc(final Status status) {
            if (status.getStatusCode() != this.vT.getStatus().getStatusCode()) {
                throw new UnsupportedOperationException("Creating failed results is not supported");
            }
            return this.vT;
        }
    }
    
    private static final class zzb<R extends Result> extends zzqe<R>
    {
        private final R vU;
        
        public zzb(final GoogleApiClient googleApiClient, final R vu) {
            super(googleApiClient);
            this.vU = vu;
        }
        
        @Override
        protected R zzc(final Status status) {
            return this.vU;
        }
    }
    
    private static final class zzc<R extends Result> extends zzqe<R>
    {
        public zzc(final GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }
        
        @Override
        protected R zzc(final Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }
}
