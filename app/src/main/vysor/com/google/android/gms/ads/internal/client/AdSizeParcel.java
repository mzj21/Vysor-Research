// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.zza;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public class AdSizeParcel extends AbstractSafeParcelable
{
    public static final zzi CREATOR;
    public final int height;
    public final int heightPixels;
    public final int versionCode;
    public final int width;
    public final int widthPixels;
    public final String zzaxi;
    public final boolean zzaxj;
    public final AdSizeParcel[] zzaxk;
    public final boolean zzaxl;
    public final boolean zzaxm;
    public boolean zzaxn;
    
    static {
        CREATOR = new zzi();
    }
    
    public AdSizeParcel() {
        this(5, "interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }
    
    AdSizeParcel(final int versionCode, final String zzaxi, final int height, final int heightPixels, final boolean zzaxj, final int width, final int widthPixels, final AdSizeParcel[] zzaxk, final boolean zzaxl, final boolean zzaxm, final boolean zzaxn) {
        this.versionCode = versionCode;
        this.zzaxi = zzaxi;
        this.height = height;
        this.heightPixels = heightPixels;
        this.zzaxj = zzaxj;
        this.width = width;
        this.widthPixels = widthPixels;
        this.zzaxk = zzaxk;
        this.zzaxl = zzaxl;
        this.zzaxm = zzaxm;
        this.zzaxn = zzaxn;
    }
    
    public AdSizeParcel(final Context context, final AdSize adSize) {
        this(context, new AdSize[] { adSize });
    }
    
    public AdSizeParcel(final Context context, final AdSize[] array) {
        final AdSize adSize = array[0];
        this.versionCode = 5;
        this.zzaxj = false;
        this.zzaxm = adSize.isFluid();
        if (this.zzaxm) {
            this.width = AdSize.BANNER.getWidth();
            this.height = AdSize.BANNER.getHeight();
        }
        else {
            this.width = adSize.getWidth();
            this.height = adSize.getHeight();
        }
        boolean b;
        if (this.width == -1) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (this.height == -2) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int n3;
        if (b) {
            if (zzm.zzjr().zzat(context) && zzm.zzjr().zzau(context)) {
                this.widthPixels = zza(displayMetrics) - zzm.zzjr().zzav(context);
            }
            else {
                this.widthPixels = zza(displayMetrics);
            }
            final double n = this.widthPixels / displayMetrics.density;
            int n2 = (int)n;
            if (n - (int)n >= 0.01) {
                ++n2;
            }
            n3 = n2;
        }
        else {
            final int width = this.width;
            this.widthPixels = zzm.zzjr().zza(displayMetrics, this.width);
            n3 = width;
        }
        int n4;
        if (b2) {
            n4 = zzc(displayMetrics);
        }
        else {
            n4 = this.height;
        }
        this.heightPixels = zzm.zzjr().zza(displayMetrics, n4);
        if (b || b2) {
            this.zzaxi = new StringBuilder(26).append(n3).append("x").append(n4).append("_as").toString();
        }
        else if (this.zzaxm) {
            this.zzaxi = "320x50_mb";
        }
        else {
            this.zzaxi = adSize.toString();
        }
        if (array.length > 1) {
            this.zzaxk = new AdSizeParcel[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.zzaxk[i] = new AdSizeParcel(context, array[i]);
            }
        }
        else {
            this.zzaxk = null;
        }
        this.zzaxl = false;
        this.zzaxn = false;
    }
    
    public AdSizeParcel(final AdSizeParcel adSizeParcel, final AdSizeParcel[] array) {
        this(5, adSizeParcel.zzaxi, adSizeParcel.height, adSizeParcel.heightPixels, adSizeParcel.zzaxj, adSizeParcel.width, adSizeParcel.widthPixels, array, adSizeParcel.zzaxl, adSizeParcel.zzaxm, adSizeParcel.zzaxn);
    }
    
    public static int zza(final DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }
    
    public static int zzb(final DisplayMetrics displayMetrics) {
        return (int)(zzc(displayMetrics) * displayMetrics.density);
    }
    
    private static int zzc(final DisplayMetrics displayMetrics) {
        final int n = (int)(displayMetrics.heightPixels / displayMetrics.density);
        int n2;
        if (n <= 400) {
            n2 = 32;
        }
        else if (n <= 720) {
            n2 = 50;
        }
        else {
            n2 = 90;
        }
        return n2;
    }
    
    public static AdSizeParcel zzjc() {
        return new AdSizeParcel(5, "reward_mb", 0, 0, true, 0, 0, null, false, false, false);
    }
    
    public static AdSizeParcel zzk(final Context context) {
        return new AdSizeParcel(5, "320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
    
    public AdSize zzjd() {
        return zza.zza(this.width, this.height, this.zzaxi);
    }
    
    public void zzl(final boolean zzaxn) {
        this.zzaxn = zzaxn;
    }
}
