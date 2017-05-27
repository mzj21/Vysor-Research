// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.List;
import android.text.TextUtils;
import android.view.View;
import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class zzar extends zzas
{
    private static final String TAG;
    private AdvertisingIdClient.Info zzagq;
    
    static {
        TAG = zzar.class.getSimpleName();
    }
    
    protected zzar(final Context context) {
        super(context, "");
    }
    
    public static zzar zzd(final Context context) {
        zzas.zza(context, true);
        return new zzar(context);
    }
    
    @Override
    protected zzae.zza zza(final Context context, final View view) {
        return null;
    }
    
    public String zza(final String s, final String s2) {
        return zzam.zza(s, s2, true);
    }
    
    public void zza(final AdvertisingIdClient.Info zzagq) {
        this.zzagq = zzagq;
    }
    
    @Override
    protected void zza(final zzbb zzbb, final zzae.zza zza) {
        if (zzbb.zzcm()) {
            if (this.zzagq != null) {
                final String id = this.zzagq.getId();
                if (!TextUtils.isEmpty((CharSequence)id)) {
                    zza.zzes = zzbd.zzq(id);
                    zza.zzet = 5;
                    zza.zzeu = this.zzagq.isLimitAdTrackingEnabled();
                }
                this.zzagq = null;
            }
        }
        else {
            this.zza(this.zzb(zzbb, zza));
        }
    }
    
    @Override
    protected List<Callable<Void>> zzb(final zzbb zzbb, final zzae.zza zza) {
        final ArrayList<zzbk> list = new ArrayList<zzbk>();
        ArrayList<Callable<Void>> list2;
        if (zzbb.zzch() == null) {
            list2 = (ArrayList<Callable<Void>>)list;
        }
        else {
            list.add(new zzbk(zzbb, zzax.zzbj(), zzax.zzbk(), zza, zzbb.zzau(), 24));
            list2 = (ArrayList<Callable<Void>>)list;
        }
        return list2;
    }
}
