// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import com.google.android.gms.internal.zzlj;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzke;
import java.util.concurrent.Future;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.internal.zzau;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zza
{
    public zzkm zza(final Context context, final AdRequestInfoParcel.zza zza, final zzau zzau, final zza zza2) {
        zzkm zzkm;
        if (zza.zzcfu.extras.getBundle("sdk_less_server_data") != null) {
            zzkm = new zzn(context, zza, zza2);
        }
        else {
            zzkm = new zzb(context, zza, zzau, zza2);
        }
        final Future future = (Future)zzkm.zzqw();
        return zzkm;
    }
    
    public interface zza
    {
        void zza(final zzke.zza p0);
    }
}
