// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzd;
import android.os.IBinder;
import android.content.Context;
import com.google.android.gms.internal.zzhw;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class GInAppPurchaseManagerInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final zza CREATOR;
    public final int versionCode;
    public final zzk zzars;
    public final zzhw zzcbf;
    public final Context zzcbg;
    public final zzj zzcbh;
    
    static {
        CREATOR = new zza();
    }
    
    GInAppPurchaseManagerInfoParcel(final int versionCode, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4) {
        this.versionCode = versionCode;
        this.zzars = zze.zzae(zzd.zza.zzfe(binder));
        this.zzcbf = zze.zzae(zzd.zza.zzfe(binder2));
        this.zzcbg = zze.zzae(zzd.zza.zzfe(binder3));
        this.zzcbh = zze.zzae(zzd.zza.zzfe(binder4));
    }
    
    public GInAppPurchaseManagerInfoParcel(final Context zzcbg, final zzk zzars, final zzhw zzcbf, final zzj zzcbh) {
        this.versionCode = 2;
        this.zzcbg = zzcbg;
        this.zzars = zzars;
        this.zzcbf = zzcbf;
        this.zzcbh = zzcbh;
    }
    
    public static void zza(final Intent intent, final GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", (Parcelable)gInAppPurchaseManagerInfoParcel);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", bundle);
    }
    
    public static GInAppPurchaseManagerInfoParcel zzc(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
            bundleExtra.setClassLoader(GInAppPurchaseManagerInfoParcel.class.getClassLoader());
            return (GInAppPurchaseManagerInfoParcel)bundleExtra.getParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    IBinder zzqk() {
        return zze.zzac(this.zzcbh).asBinder();
    }
    
    IBinder zzql() {
        return zze.zzac(this.zzars).asBinder();
    }
    
    IBinder zzqm() {
        return zze.zzac(this.zzcbf).asBinder();
    }
    
    IBinder zzqn() {
        return zze.zzac(this.zzcbg).asBinder();
    }
}
