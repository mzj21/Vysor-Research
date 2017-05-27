// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import android.content.Context;
import android.app.Activity;
import android.os.IBinder;
import com.google.android.gms.dynamic.zzg;

@zziy
public final class zzho extends zzg<zzhq>
{
    public zzho() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }
    
    protected zzhq zzas(final IBinder binder) {
        return zzhq.zza.zzau(binder);
    }
    
    public zzhp zzf(final Activity activity) {
        try {
            return zzhp.zza.zzat(this.zzcu((Context)activity).zzo(zze.zzac(activity)));
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not create remote AdOverlay.", (Throwable)ex);
            return null;
        }
        catch (zza zza) {
            zzb.zzd("Could not create remote AdOverlay.", zza);
            return null;
        }
    }
}
