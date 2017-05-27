// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzlt;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zzn extends zzj
{
    @Nullable
    @Override
    public zzi zza(final Context context, final zzlt zzlt, final int n, final boolean b, final zzdq zzdq) {
        zzi zzi;
        if (!this.zzq(context)) {
            zzi = null;
        }
        else {
            zzi = new zzc(context, b, this.zzh(zzlt), new zzx(context, zzlt.zzvu(), zzlt.getRequestId(), zzdq, zzlt.zzvz()));
        }
        return zzi;
    }
}
