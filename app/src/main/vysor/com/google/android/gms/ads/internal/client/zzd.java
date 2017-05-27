// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgq;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.dynamic.zzg;

@zziy
public final class zzd extends zzg<zzt>
{
    public zzd() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }
    
    public zzs zza(final Context context, final String s, final zzgq zzgq) {
        try {
            return zzs.zza.zzo(this.zzcu(context).zza(zze.zzac(context), s, zzgq, 9683000));
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not create remote builder for AdLoader.", (Throwable)ex);
        }
        catch (zza zza) {
            zzb.zzd("Could not create remote builder for AdLoader.", zza);
            goto Label_0046;
        }
    }
    
    protected zzt zzj(final IBinder binder) {
        return zzt.zza.zzp(binder);
    }
}
