// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import java.lang.ref.WeakReference;
import com.google.android.gms.internal.zziy;

@zziy
public final class zzg extends zzl.zza
{
    private final WeakReference<zzc.zza> zzcha;
    
    public zzg(final zzc.zza zza) {
        this.zzcha = new WeakReference<zzc.zza>(zza);
    }
    
    public void zzb(final AdResponseParcel adResponseParcel) {
        final zzc.zza zza = this.zzcha.get();
        if (zza != null) {
            zza.zzb(adResponseParcel);
        }
    }
}
