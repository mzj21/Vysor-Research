// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzd;
import android.os.IBinder;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final zzf CREATOR;
    public final int orientation;
    public final String url;
    public final int versionCode;
    public final VersionInfoParcel zzaqv;
    public final AdLauncherIntentInfoParcel zzbye;
    public final zza zzbyf;
    public final zzg zzbyg;
    public final zzlt zzbyh;
    public final zzer zzbyi;
    public final String zzbyj;
    public final boolean zzbyk;
    public final String zzbyl;
    public final zzp zzbym;
    public final int zzbyn;
    public final zzex zzbyo;
    public final String zzbyp;
    public final InterstitialAdParameterParcel zzbyq;
    
    static {
        CREATOR = new zzf();
    }
    
    AdOverlayInfoParcel(final int versionCode, final AdLauncherIntentInfoParcel zzbye, final IBinder binder, final IBinder binder2, final IBinder binder3, final IBinder binder4, final String zzbyj, final boolean zzbyk, final String zzbyl, final IBinder binder5, final int orientation, final int zzbyn, final String url, final VersionInfoParcel zzaqv, final IBinder binder6, final String zzbyp, final InterstitialAdParameterParcel zzbyq) {
        this.versionCode = versionCode;
        this.zzbye = zzbye;
        this.zzbyf = zze.zzae(zzd.zza.zzfe(binder));
        this.zzbyg = zze.zzae(zzd.zza.zzfe(binder2));
        this.zzbyh = zze.zzae(zzd.zza.zzfe(binder3));
        this.zzbyi = zze.zzae(zzd.zza.zzfe(binder4));
        this.zzbyj = zzbyj;
        this.zzbyk = zzbyk;
        this.zzbyl = zzbyl;
        this.zzbym = zze.zzae(zzd.zza.zzfe(binder5));
        this.orientation = orientation;
        this.zzbyn = zzbyn;
        this.url = url;
        this.zzaqv = zzaqv;
        this.zzbyo = zze.zzae(zzd.zza.zzfe(binder6));
        this.zzbyp = zzbyp;
        this.zzbyq = zzbyq;
    }
    
    public AdOverlayInfoParcel(final zza zzbyf, final zzg zzbyg, final zzp zzbym, final zzlt zzbyh, final int orientation, final VersionInfoParcel zzaqv, final String zzbyp, final InterstitialAdParameterParcel zzbyq) {
        this.versionCode = 4;
        this.zzbye = null;
        this.zzbyf = zzbyf;
        this.zzbyg = zzbyg;
        this.zzbyh = zzbyh;
        this.zzbyi = null;
        this.zzbyj = null;
        this.zzbyk = false;
        this.zzbyl = null;
        this.zzbym = zzbym;
        this.orientation = orientation;
        this.zzbyn = 1;
        this.url = null;
        this.zzaqv = zzaqv;
        this.zzbyo = null;
        this.zzbyp = zzbyp;
        this.zzbyq = zzbyq;
    }
    
    public AdOverlayInfoParcel(final zza zzbyf, final zzg zzbyg, final zzp zzbym, final zzlt zzbyh, final boolean zzbyk, final int orientation, final VersionInfoParcel zzaqv) {
        this.versionCode = 4;
        this.zzbye = null;
        this.zzbyf = zzbyf;
        this.zzbyg = zzbyg;
        this.zzbyh = zzbyh;
        this.zzbyi = null;
        this.zzbyj = null;
        this.zzbyk = zzbyk;
        this.zzbyl = null;
        this.zzbym = zzbym;
        this.orientation = orientation;
        this.zzbyn = 2;
        this.url = null;
        this.zzaqv = zzaqv;
        this.zzbyo = null;
        this.zzbyp = null;
        this.zzbyq = null;
    }
    
    public AdOverlayInfoParcel(final zza zzbyf, final zzg zzbyg, final zzer zzbyi, final zzp zzbym, final zzlt zzbyh, final boolean zzbyk, final int orientation, final String url, final VersionInfoParcel zzaqv, final zzex zzbyo) {
        this.versionCode = 4;
        this.zzbye = null;
        this.zzbyf = zzbyf;
        this.zzbyg = zzbyg;
        this.zzbyh = zzbyh;
        this.zzbyi = zzbyi;
        this.zzbyj = null;
        this.zzbyk = zzbyk;
        this.zzbyl = null;
        this.zzbym = zzbym;
        this.orientation = orientation;
        this.zzbyn = 3;
        this.url = url;
        this.zzaqv = zzaqv;
        this.zzbyo = zzbyo;
        this.zzbyp = null;
        this.zzbyq = null;
    }
    
    public AdOverlayInfoParcel(final zza zzbyf, final zzg zzbyg, final zzer zzbyi, final zzp zzbym, final zzlt zzbyh, final boolean zzbyk, final int orientation, final String zzbyl, final String zzbyj, final VersionInfoParcel zzaqv, final zzex zzbyo) {
        this.versionCode = 4;
        this.zzbye = null;
        this.zzbyf = zzbyf;
        this.zzbyg = zzbyg;
        this.zzbyh = zzbyh;
        this.zzbyi = zzbyi;
        this.zzbyj = zzbyj;
        this.zzbyk = zzbyk;
        this.zzbyl = zzbyl;
        this.zzbym = zzbym;
        this.orientation = orientation;
        this.zzbyn = 3;
        this.url = null;
        this.zzaqv = zzaqv;
        this.zzbyo = zzbyo;
        this.zzbyp = null;
        this.zzbyq = null;
    }
    
    public AdOverlayInfoParcel(final AdLauncherIntentInfoParcel zzbye, final zza zzbyf, final zzg zzbyg, final zzp zzbym, final VersionInfoParcel zzaqv) {
        this.versionCode = 4;
        this.zzbye = zzbye;
        this.zzbyf = zzbyf;
        this.zzbyg = zzbyg;
        this.zzbyh = null;
        this.zzbyi = null;
        this.zzbyj = null;
        this.zzbyk = false;
        this.zzbyl = null;
        this.zzbym = zzbym;
        this.orientation = -1;
        this.zzbyn = 4;
        this.url = null;
        this.zzaqv = zzaqv;
        this.zzbyo = null;
        this.zzbyp = null;
        this.zzbyq = null;
    }
    
    public static void zza(final Intent intent, final AdOverlayInfoParcel adOverlayInfoParcel) {
        final Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", (Parcelable)adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }
    
    public static AdOverlayInfoParcel zzb(final Intent intent) {
        try {
            final Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel)bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
    
    IBinder zzpc() {
        return zze.zzac(this.zzbyf).asBinder();
    }
    
    IBinder zzpd() {
        return zze.zzac(this.zzbyg).asBinder();
    }
    
    IBinder zzpe() {
        return zze.zzac(this.zzbyh).asBinder();
    }
    
    IBinder zzpf() {
        return zze.zzac(this.zzbyi).asBinder();
    }
    
    IBinder zzpg() {
        return zze.zzac(this.zzbyo).asBinder();
    }
    
    IBinder zzph() {
        return zze.zzac(this.zzbym).asBinder();
    }
}
