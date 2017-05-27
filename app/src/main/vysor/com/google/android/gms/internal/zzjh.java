// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Build;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.annotation.TargetApi;
import android.net.NetworkInfo;
import android.os.Build$VERSION;
import com.google.android.gms.ads.internal.zzu;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.content.pm.PackageInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.ResolveInfo;
import android.util.DisplayMetrics;
import android.content.res.Resources;
import android.content.pm.PackageManager;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.ads.internal.client.zzm;
import java.util.Locale;
import android.content.Context;

@zziy
public final class zzjh
{
    public final int zzcge;
    public final int zzcgf;
    public final float zzcgg;
    public final int zzclr;
    public final boolean zzcls;
    public final boolean zzclt;
    public final String zzclu;
    public final String zzclv;
    public final boolean zzclw;
    public final boolean zzclx;
    public final boolean zzcly;
    public final boolean zzclz;
    public final String zzcma;
    public final String zzcmb;
    public final int zzcmc;
    public final int zzcmd;
    public final int zzcme;
    public final int zzcmf;
    public final int zzcmg;
    public final int zzcmh;
    public final double zzcmi;
    public final boolean zzcmj;
    public final boolean zzcmk;
    public final int zzcml;
    public final String zzcmm;
    public final boolean zzcmn;
    
    zzjh(final int zzclr, final boolean zzcls, final boolean zzclt, final String zzclu, final String zzclv, final boolean zzclw, final boolean zzclx, final boolean zzcly, final boolean zzclz, final String zzcma, final String zzcmb, final int zzcmc, final int zzcmd, final int zzcme, final int zzcmf, final int zzcmg, final int zzcmh, final float zzcgg, final int zzcge, final int zzcgf, final double zzcmi, final boolean zzcmj, final boolean zzcmk, final int zzcml, final String zzcmm, final boolean zzcmn) {
        this.zzclr = zzclr;
        this.zzcls = zzcls;
        this.zzclt = zzclt;
        this.zzclu = zzclu;
        this.zzclv = zzclv;
        this.zzclw = zzclw;
        this.zzclx = zzclx;
        this.zzcly = zzcly;
        this.zzclz = zzclz;
        this.zzcma = zzcma;
        this.zzcmb = zzcmb;
        this.zzcmc = zzcmc;
        this.zzcmd = zzcmd;
        this.zzcme = zzcme;
        this.zzcmf = zzcmf;
        this.zzcmg = zzcmg;
        this.zzcmh = zzcmh;
        this.zzcgg = zzcgg;
        this.zzcge = zzcge;
        this.zzcgf = zzcgf;
        this.zzcmi = zzcmi;
        this.zzcmj = zzcmj;
        this.zzcmk = zzcmk;
        this.zzcml = zzcml;
        this.zzcmm = zzcmm;
        this.zzcmn = zzcmn;
    }
    
    public static final class zza
    {
        private int zzcge;
        private int zzcgf;
        private float zzcgg;
        private int zzclr;
        private boolean zzcls;
        private boolean zzclt;
        private String zzclu;
        private String zzclv;
        private boolean zzclw;
        private boolean zzclx;
        private boolean zzcly;
        private boolean zzclz;
        private String zzcma;
        private String zzcmb;
        private int zzcmc;
        private int zzcmd;
        private int zzcme;
        private int zzcmf;
        private int zzcmg;
        private int zzcmh;
        private double zzcmi;
        private boolean zzcmj;
        private boolean zzcmk;
        private int zzcml;
        private String zzcmm;
        private boolean zzcmn;
        
        public zza(final Context context) {
            boolean zzclt = true;
            final PackageManager packageManager = context.getPackageManager();
            this.zzv(context);
            this.zza(context, packageManager);
            this.zzw(context);
            final Locale default1 = Locale.getDefault();
            this.zzcls = (zza(packageManager, "geo:0,0?q=donuts") != null && zzclt);
            if (zza(packageManager, "http://www.google.com") == null) {
                zzclt = false;
            }
            this.zzclt = zzclt;
            this.zzclv = default1.getCountry();
            this.zzclw = zzm.zzjr().zzve();
            this.zzclx = zzi.zzcm(context);
            this.zzcma = default1.getLanguage();
            this.zzcmb = zza(packageManager);
            final Resources resources = context.getResources();
            if (resources != null) {
                final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                if (displayMetrics != null) {
                    this.zzcgg = displayMetrics.density;
                    this.zzcge = displayMetrics.widthPixels;
                    this.zzcgf = displayMetrics.heightPixels;
                }
            }
        }
        
        public zza(final Context context, final zzjh zzjh) {
            final PackageManager packageManager = context.getPackageManager();
            this.zzv(context);
            this.zza(context, packageManager);
            this.zzw(context);
            this.zzx(context);
            this.zzcls = zzjh.zzcls;
            this.zzclt = zzjh.zzclt;
            this.zzclv = zzjh.zzclv;
            this.zzclw = zzjh.zzclw;
            this.zzclx = zzjh.zzclx;
            this.zzcma = zzjh.zzcma;
            this.zzcmb = zzjh.zzcmb;
            this.zzcgg = zzjh.zzcgg;
            this.zzcge = zzjh.zzcge;
            this.zzcgf = zzjh.zzcgf;
        }
        
        private static ResolveInfo zza(final PackageManager packageManager, final String s) {
            return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)), 65536);
        }
        
        private static String zza(final PackageManager packageManager) {
            final ResolveInfo zza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
            String string = null;
            if (zza != null) {
                final ActivityInfo activityInfo = zza.activityInfo;
                string = null;
                if (activityInfo != null) {
                    try {
                        final PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
                        string = null;
                        if (packageInfo != null) {
                            final int versionCode = packageInfo.versionCode;
                            final String value = String.valueOf(activityInfo.packageName);
                            string = new StringBuilder(12 + String.valueOf(value).length()).append(versionCode).append(".").append(value).toString();
                        }
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        string = null;
                    }
                }
            }
            return string;
        }
        
        @TargetApi(16)
        private void zza(final Context context, final PackageManager packageManager) {
            final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
            this.zzclu = telephonyManager.getNetworkOperator();
            this.zzcme = telephonyManager.getNetworkType();
            this.zzcmf = telephonyManager.getPhoneType();
            this.zzcmd = -2;
            this.zzcmk = false;
            this.zzcml = -1;
            if (zzu.zzfz().zza(packageManager, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    this.zzcmd = activeNetworkInfo.getType();
                    this.zzcml = activeNetworkInfo.getDetailedState().ordinal();
                }
                else {
                    this.zzcmd = -1;
                }
                if (Build$VERSION.SDK_INT >= 16) {
                    this.zzcmk = connectivityManager.isActiveNetworkMetered();
                }
            }
        }
        
        private void zzv(final Context context) {
            final AudioManager zzak = zzu.zzfz().zzak(context);
            Label_0074: {
                if (zzak == null) {
                    break Label_0074;
                }
                try {
                    this.zzclr = zzak.getMode();
                    this.zzcly = zzak.isMusicActive();
                    this.zzclz = zzak.isSpeakerphoneOn();
                    this.zzcmc = zzak.getStreamVolume(3);
                    this.zzcmg = zzak.getRingerMode();
                    this.zzcmh = zzak.getStreamVolume(2);
                    return;
                }
                catch (Throwable t) {
                    zzu.zzgd().zza(t, "DeviceInfo.gatherAudioInfo");
                }
            }
            this.zzclr = -2;
            this.zzcly = false;
            this.zzclz = false;
            this.zzcmc = 0;
            this.zzcmg = 0;
            this.zzcmh = 0;
        }
        
        private void zzw(final Context context) {
            final Intent registerReceiver = context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                final int intExtra = registerReceiver.getIntExtra("status", -1);
                this.zzcmi = registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1);
                boolean zzcmj = false;
                Label_0077: {
                    if (intExtra != 2) {
                        zzcmj = false;
                        if (intExtra != 5) {
                            break Label_0077;
                        }
                    }
                    zzcmj = true;
                }
                this.zzcmj = zzcmj;
            }
            else {
                this.zzcmi = -1.0;
                this.zzcmj = false;
            }
        }
        
        private void zzx(final Context context) {
            this.zzcmm = Build.FINGERPRINT;
            this.zzcmn = zzdw.zzo(context);
        }
        
        public zzjh zzsk() {
            return new zzjh(this.zzclr, this.zzcls, this.zzclt, this.zzclu, this.zzclv, this.zzclw, this.zzclx, this.zzcly, this.zzclz, this.zzcma, this.zzcmb, this.zzcmc, this.zzcmd, this.zzcme, this.zzcmf, this.zzcmg, this.zzcmh, this.zzcgg, this.zzcge, this.zzcgf, this.zzcmi, this.zzcmj, this.zzcmk, this.zzcml, this.zzcmm, this.zzcmn);
        }
    }
}
