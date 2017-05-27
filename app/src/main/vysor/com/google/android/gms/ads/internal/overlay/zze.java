// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import com.google.android.gms.common.util.zzs;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzu;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zze
{
    public void zza(final Context context, final AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zza(context, adOverlayInfoParcel, true);
    }
    
    public void zza(final Context context, final AdOverlayInfoParcel adOverlayInfoParcel, final boolean b) {
        if (adOverlayInfoParcel.zzbyn == 4 && adOverlayInfoParcel.zzbyg == null) {
            if (adOverlayInfoParcel.zzbyf != null) {
                adOverlayInfoParcel.zzbyf.onAdClicked();
            }
            zzu.zzfw().zza(context, adOverlayInfoParcel.zzbye, adOverlayInfoParcel.zzbym);
        }
        else {
            final Intent intent = new Intent();
            intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
            intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzaqv.zzctu);
            intent.putExtra("shouldCallOnOverlayOpened", b);
            AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
            if (!zzs.zzaxt()) {
                intent.addFlags(524288);
            }
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            zzu.zzfz().zzb(context, intent);
        }
    }
}
