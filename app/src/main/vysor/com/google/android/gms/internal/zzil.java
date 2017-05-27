// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.ads.internal.zzq;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import android.content.Context;

@zziy
public class zzil
{
    public zzkt zza(final Context context, final com.google.android.gms.ads.internal.zza zza, final zzke.zza zza2, final zzau zzau, @Nullable final zzlt zzlt, final zzgq zzgq, final zza zza3, final zzdq zzdq) {
        final AdResponseParcel zzcop = zza2.zzcop;
        Object o;
        if (zzcop.zzchc) {
            o = new zzip(context, zza2, zzgq, zza3, zzdq, zzlt);
        }
        else if (zzcop.zzaxl || zza instanceof zzq) {
            if (zzcop.zzaxl && zza instanceof zzq) {
                o = new zziq(context, (zzq)zza, zza2, zzau, zza3, zzdq);
            }
            else {
                o = new zzin(zza2, zza3);
            }
        }
        else if (zzcop.zzchi) {
            o = new zzij(context, zza2, zzlt, zza3);
        }
        else if (zzdi.zzbco.get() && zzs.zzaxr() && !zzs.zzaxt() && zzlt != null && zzlt.zzdt().zzaxj) {
            o = new zzio(context, zza2, zzlt, zza3);
        }
        else {
            o = new zzim(context, zza2, zzlt, zza3);
        }
        final String value = String.valueOf(((zzip)o).getClass().getName());
        String concat;
        if (value.length() != 0) {
            concat = "AdRenderer: ".concat(value);
        }
        else {
            concat = new String("AdRenderer: ");
        }
        zzb.zzdd(concat);
        ((zzkt<Future>)o).zzqw();
        return (zzkt)o;
    }
    
    public zzkt zza(final Context context, final zzke.zza zza, final zzjr zzjr) {
        final zzjy zzjy = new zzjy(context, zza, zzjr);
        final String value = String.valueOf(zzjy.getClass().getName());
        String concat;
        if (value.length() != 0) {
            concat = "AdRenderer: ".concat(value);
        }
        else {
            concat = new String("AdRenderer: ");
        }
        zzb.zzdd(concat);
        zzjy.zzqw();
        return zzjy;
    }
    
    public interface zza
    {
        void zzb(final zzke p0);
    }
}
