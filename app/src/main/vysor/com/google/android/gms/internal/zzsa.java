// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

abstract class zzsa<R extends Result> extends zzqc.zza<R, zzsb>
{
    public zzsa(final GoogleApiClient googleApiClient) {
        super(zzrx.API, googleApiClient);
    }
    
    abstract static class zza extends zzsa<Status>
    {
        public zza(final GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }
        
        public Status zzb(final Status status) {
            return status;
        }
    }
}
