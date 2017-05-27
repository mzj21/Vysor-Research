// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.dynamic.zzd;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.common.zzc;
import android.content.Context;
import com.google.android.gms.dynamic.zzg;

public final class zzcf extends zzg<zzch>
{
    private static final zzcf zzakm;
    
    static {
        zzakm = new zzcf();
    }
    
    private zzcf() {
        super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
    }
    
    public static zzcg zzb(final String s, final Context context, final boolean b) {
        if (zzc.zzapd().isGooglePlayServicesAvailable(context) == 0) {
            final zzcg zzc = zzcf.zzakm.zzc(s, context, b);
            if (zzc != null) {
                return zzc;
            }
        }
        return new zzce(s, context, b);
    }
    
    private zzcg zzc(final String s, final Context context, final boolean b) {
        final zzd zzac = zze.zzac(context);
        while (true) {
            if (b) {
                zzcg zzd = null;
                try {
                    IBinder binder = this.zzcu(context).zza(s, zzac);
                    zzd = zzcg.zza.zzd(binder);
                    return zzd;
                    binder = this.zzcu(context).zzb(s, zzac);
                    return zzcg.zza.zzd(binder);
                }
                catch (RemoteException ex) {}
                catch (zza zza) {
                    goto Label_0065;
                }
                return zzd;
            }
            continue;
        }
    }
    
    protected zzch zzb(final IBinder binder) {
        return zzch.zza.zze(binder);
    }
}
