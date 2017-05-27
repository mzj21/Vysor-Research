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
public final class zzid extends zzg<zzhz>
{
    public zzid() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }
    
    protected zzhz zzbc(final IBinder binder) {
        return zzhz.zza.zzaz(binder);
    }
    
    public zzhy zzg(final Activity activity) {
        try {
            return zzhy.zza.zzay(this.zzcu((Context)activity).zzp(zze.zzac(activity)));
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not create remote InAppPurchaseManager.", (Throwable)ex);
            return null;
        }
        catch (zza zza) {
            zzb.zzd("Could not create remote InAppPurchaseManager.", zza);
            return null;
        }
    }
}
