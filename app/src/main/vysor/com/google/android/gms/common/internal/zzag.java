// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.zze;
import android.view.View;
import android.content.Context;
import com.google.android.gms.dynamic.zzg;

public final class zzag extends zzg<zzy>
{
    private static final zzag Da;
    
    static {
        Da = new zzag();
    }
    
    private zzag() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }
    
    public static View zzb(final Context context, final int n, final int n2) throws zza {
        return zzag.Da.zzc(context, n, n2);
    }
    
    private View zzc(final Context context, final int n, final int n2) throws zza {
        try {
            return zze.zzae(this.zzcu(context).zza(zze.zzac(context), new SignInButtonConfig(n, n2, null)));
        }
        catch (Exception ex) {
            throw new zza(new StringBuilder(64).append("Could not get button with size ").append(n).append(" and color ").append(n2).toString(), ex);
        }
    }
    
    public zzy zzdz(final IBinder binder) {
        return zzy.zza.zzdy(binder);
    }
}
