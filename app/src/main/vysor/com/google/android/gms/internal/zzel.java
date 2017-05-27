// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import android.widget.FrameLayout;
import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.dynamic.zzg;

@zziy
public class zzel extends zzg<zzea>
{
    public zzel() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }
    
    protected zzea zzal(final IBinder binder) {
        return zzea.zza.zzad(binder);
    }
    
    public zzdz zzb(final Context context, final FrameLayout frameLayout, final FrameLayout frameLayout2) {
        try {
            return zzdz.zza.zzac(this.zzcu(context).zza(zze.zzac(context), zze.zzac(frameLayout), zze.zzac(frameLayout2), 9683000));
        }
        catch (RemoteException ex) {}
        catch (zza zza) {
            goto Label_0053;
        }
    }
}
