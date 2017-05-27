// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgq;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.dynamic.zzg;

@zziy
public class zzf extends zzg<zzc>
{
    public zzf() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }
    
    public zzb zzb(final Context context, final zzgq zzgq) {
        try {
            return zzb.zza.zzbh(this.zzcu(context).zza(zze.zzac(context), zzgq, 9683000));
        }
        catch (RemoteException ex) {}
        catch (zza zza) {
            goto Label_0037;
        }
    }
    
    protected zzc zzbk(final IBinder binder) {
        return zzc.zza.zzbi(binder);
    }
}
