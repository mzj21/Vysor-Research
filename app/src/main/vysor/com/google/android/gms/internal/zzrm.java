// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import com.google.android.gms.common.api.Status;

public class zzrm extends zzqe<Status>
{
    public zzrm(final Looper looper) {
        super(looper);
    }
    
    public zzrm(final GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
    
    protected Status zzb(final Status status) {
        return status;
    }
}
