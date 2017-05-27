// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.zzp;
import android.content.Intent;
import com.google.android.gms.common.util.zzi;
import android.app.PendingIntent;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.internal.zzsi;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import android.content.Context;

public class zzc
{
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zzc uN;
    
    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        uN = new zzc();
    }
    
    public static zzc zzapd() {
        return zzc.uN;
    }
    
    private String zzt(@Nullable final Context context, @Nullable final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        sb.append("-");
        if (!TextUtils.isEmpty((CharSequence)s)) {
            sb.append(s);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        Label_0094: {
            if (context == null) {
                break Label_0094;
            }
            try {
                sb.append(zzsi.zzcr(context).getPackageInfo(context.getPackageName(), 0).versionCode);
                return sb.toString();
            }
            catch (PackageManager$NameNotFoundException ex) {
                return sb.toString();
            }
        }
    }
    
    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final int n, final int n2) {
        return this.zza(context, n, n2, null);
    }
    
    public String getErrorString(final int n) {
        return zze.getErrorString(n);
    }
    
    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(final Context context) {
        return zze.getOpenSourceSoftwareLicenseInfo(context);
    }
    
    public int isGooglePlayServicesAvailable(final Context context) {
        int googlePlayServicesAvailable = zze.isGooglePlayServicesAvailable(context);
        if (zze.zzd(context, googlePlayServicesAvailable)) {
            googlePlayServicesAvailable = 18;
        }
        return googlePlayServicesAvailable;
    }
    
    public boolean isUserResolvableError(final int n) {
        return zze.isUserRecoverableError(n);
    }
    
    @Nullable
    public PendingIntent zza(final Context context, int n, final int n2, @Nullable final String s) {
        if (zzi.zzcl(context) && n == 2) {
            n = 42;
        }
        final Intent zza = this.zza(context, n, s);
        PendingIntent activity;
        if (zza == null) {
            activity = null;
        }
        else {
            activity = PendingIntent.getActivity(context, n2, zza, 268435456);
        }
        return activity;
    }
    
    @Nullable
    public Intent zza(final Context context, final int n, @Nullable final String s) {
        Intent intent = null;
        switch (n) {
            default: {
                intent = null;
                break;
            }
            case 1:
            case 2: {
                intent = zzp.zzad("com.google.android.gms", this.zzt(context, s));
                break;
            }
            case 42: {
                intent = zzp.zzaux();
                break;
            }
            case 3: {
                intent = zzp.zzhw("com.google.android.gms");
                break;
            }
        }
        return intent;
    }
    
    public int zzbo(final Context context) {
        return zze.zzbo(context);
    }
    
    public void zzbp(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zze.zzbc(context);
    }
    
    public void zzbq(final Context context) {
        zze.zzbq(context);
    }
    
    public boolean zzd(final Context context, final int n) {
        return zze.zzd(context, n);
    }
    
    @Deprecated
    @Nullable
    public Intent zzfl(final int n) {
        return this.zza(null, n, null);
    }
    
    public boolean zzs(final Context context, final String s) {
        return zze.zzs(context, s);
    }
}
