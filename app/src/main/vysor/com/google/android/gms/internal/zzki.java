// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.ComponentName;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Context;

@zziy
public class zzki
{
    private final Object zzakd;
    final String zzcpl;
    int zzcqc;
    long zzcqd;
    long zzcqe;
    int zzcqf;
    int zzcqg;
    int zzcqh;
    
    public zzki(final String zzcpl) {
        this.zzcqd = -1L;
        this.zzcqe = -1L;
        this.zzcqf = -1;
        this.zzcqc = -1;
        this.zzakd = new Object();
        this.zzcqg = 0;
        this.zzcqh = 0;
        this.zzcpl = zzcpl;
    }
    
    public static boolean zzab(final Context context) {
        boolean b = false;
        final int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            zzb.zzde("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
        }
        else {
            final ComponentName componentName = new ComponentName(context.getPackageName(), "com.google.android.gms.ads.AdActivity");
            try {
                final int theme = context.getPackageManager().getActivityInfo(componentName, 0).theme;
                b = false;
                if (identifier == theme) {
                    b = true;
                }
                else {
                    zzb.zzde("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
                    b = false;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                zzb.zzdf("Fail to fetch AdActivity theme");
                zzb.zzde("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            }
        }
        return b;
    }
    
    public void zzb(final AdRequestParcel adRequestParcel, final long n) {
        while (true) {
        Label_0118:
            while (true) {
                synchronized (this.zzakd) {
                    if (this.zzcqe == -1L) {
                        if (n - zzu.zzgd().zztt() > zzdi.zzbdd.get()) {
                            this.zzbd(-1);
                        }
                        else {
                            this.zzbd(zzu.zzgd().zztu());
                        }
                        this.zzcqe = n;
                        this.zzcqd = this.zzcqe;
                        if (adRequestParcel.extras != null && adRequestParcel.extras.getInt("gw", 2) == 1) {
                            break;
                        }
                        break Label_0118;
                    }
                }
                this.zzcqd = n;
                continue;
            }
            ++this.zzcqf;
            ++this.zzcqc;
            // monitorexit(o)
            break;
        }
    }
    
    public void zzbd(final int zzcqc) {
        this.zzcqc = zzcqc;
    }
    
    public Bundle zze(final Context context, final String s) {
        synchronized (this.zzakd) {
            final Bundle bundle = new Bundle();
            bundle.putString("session_id", this.zzcpl);
            bundle.putLong("basets", this.zzcqe);
            bundle.putLong("currts", this.zzcqd);
            bundle.putString("seq_num", s);
            bundle.putInt("preqs", this.zzcqf);
            bundle.putInt("preqs_in_session", this.zzcqc);
            bundle.putInt("pclick", this.zzcqg);
            bundle.putInt("pimp", this.zzcqh);
            bundle.putBoolean("support_transparent_background", zzab(context));
            return bundle;
        }
    }
    
    public void zzsz() {
        synchronized (this.zzakd) {
            ++this.zzcqh;
        }
    }
    
    public void zzta() {
        synchronized (this.zzakd) {
            ++this.zzcqg;
        }
    }
    
    public int zztu() {
        return this.zzcqc;
    }
    
    public long zzua() {
        return this.zzcqe;
    }
}
