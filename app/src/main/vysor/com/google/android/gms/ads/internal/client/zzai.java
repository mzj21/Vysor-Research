// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.dynamic.zzg;

@zziy
public class zzai extends zzg<zzaa>
{
    public zzai() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }
    
    public zzz zzm(final Context context) {
        try {
            return zzz.zza.zzu(this.zzcu(context).zza(zze.zzac(context), 9683000));
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not get remote MobileAdsSettingManager.", (Throwable)ex);
            return null;
        }
        catch (zza zza) {
            zzb.zzd("Could not get remote MobileAdsSettingManager.", zza);
            return null;
        }
    }
    
    protected zzaa zzy(final IBinder binder) {
        return zzaa.zza.zzv(binder);
    }
}
