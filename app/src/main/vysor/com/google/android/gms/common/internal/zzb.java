// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.zze;
import com.google.android.gms.common.api.zza;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;

public class zzb
{
    @NonNull
    public static zza zzae(@NonNull final Status status) {
        zza zza;
        if (status.hasResolution()) {
            zza = new zze(status);
        }
        else {
            zza = new zza(status);
        }
        return zza;
    }
    
    @NonNull
    public static zza zzl(@NonNull final ConnectionResult connectionResult) {
        return zzae(new Status(connectionResult.getErrorCode(), connectionResult.getErrorMessage(), connectionResult.getResolution()));
    }
}
