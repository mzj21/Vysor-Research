// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;

@zziy
public class zzij extends zzih
{
    private zzii zzccz;
    
    zzij(final Context context, final zzke.zza zza, final zzlt zzlt, final zzil.zza zza2) {
        super(context, zza, zzlt, zza2);
    }
    
    @Override
    protected void zzqu() {
        final AdSizeParcel zzdt = this.zzbkr.zzdt();
        int n;
        int n2;
        if (zzdt.zzaxj) {
            final DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            n = displayMetrics.widthPixels;
            n2 = displayMetrics.heightPixels;
        }
        else {
            n = zzdt.widthPixels;
            n2 = zzdt.heightPixels;
        }
        this.zzccz = new zzii(this, this.zzbkr, n, n2);
        this.zzbkr.zzvr().zza((zzlu.zza)this);
        this.zzccz.zza(this.zzccl);
    }
    
    @Override
    protected int zzqv() {
        int n;
        if (this.zzccz.zzqz()) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Ad-Network indicated no fill with passback URL.");
            n = 3;
        }
        else if (!this.zzccz.zzra()) {
            n = 2;
        }
        else {
            n = -2;
        }
        return n;
    }
}
