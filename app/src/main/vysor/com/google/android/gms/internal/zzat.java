// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.List;
import android.content.Context;

public class zzat extends zzas
{
    private static final String TAG;
    
    static {
        TAG = zzat.class.getSimpleName();
    }
    
    protected zzat(final Context context, final String s, final boolean b) {
        super(context, s, b);
    }
    
    public static zzat zza(final String s, final Context context, final boolean b) {
        zzas.zza(context, b);
        return new zzat(context, s, b);
    }
    
    @Override
    protected List<Callable<Void>> zzb(final zzbb zzbb, final zzae.zza zza) {
        List<Callable<Void>> zzb;
        if (zzbb.zzch() == null || !this.zzagr) {
            zzb = super.zzb(zzbb, zza);
        }
        else {
            final int zzau = zzbb.zzau();
            final ArrayList<Callable<Void>> list = new ArrayList<Callable<Void>>();
            list.addAll((Collection<?>)super.zzb(zzbb, zza));
            list.add(new zzbk(zzbb, zzax.zzbj(), zzax.zzbk(), zza, zzau, 24));
            zzb = list;
        }
        return zzb;
    }
}
