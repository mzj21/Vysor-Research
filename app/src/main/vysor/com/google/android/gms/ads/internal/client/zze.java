// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzgq;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.dynamic.zzg;

@zziy
public class zze extends zzg<zzv>
{
    public zze() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }
    
    public zzu zza(final Context context, final AdSizeParcel adSizeParcel, final String s, final zzgq zzgq, final int n) {
        try {
            return zzu.zza.zzq(this.zzcu(context).zza(com.google.android.gms.dynamic.zze.zzac(context), adSizeParcel, s, zzgq, 9683000, n));
        }
        catch (RemoteException ex) {}
        catch (zza zza) {
            goto Label_0043;
        }
    }
    
    protected zzv zzk(final IBinder binder) {
        return zzv.zza.zzr(binder);
    }
}
