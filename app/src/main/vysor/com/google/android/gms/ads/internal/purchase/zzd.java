// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.Build$VERSION;
import java.util.HashMap;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.google.android.gms.ads.internal.zzu;
import java.util.LinkedList;
import java.util.ArrayList;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzhw;

@zziy
public class zzd extends zzhw.zza
{
    private Context mContext;
    private String zzati;
    private String zzcbr;
    private ArrayList<String> zzcbs;
    
    public zzd(final String zzcbr, final ArrayList<String> zzcbs, final Context mContext, final String zzati) {
        this.zzcbr = zzcbr;
        this.zzcbs = zzcbs;
        this.zzati = zzati;
        this.mContext = mContext;
    }
    
    public String getProductId() {
        return this.zzcbr;
    }
    
    public void recordPlayBillingResolution(final int n) {
        if (n == 0) {
            this.zzqq();
        }
        final Map<String, String> zzqp = this.zzqp();
        zzqp.put("google_play_status", String.valueOf(n));
        zzqp.put("sku", this.zzcbr);
        zzqp.put("status", String.valueOf(this.zzak(n)));
        final LinkedList<String> list = new LinkedList<String>();
        final Iterator<String> iterator = this.zzcbs.iterator();
        while (iterator.hasNext()) {
            list.add(zzu.zzfz().zzc(iterator.next(), zzqp));
        }
        zzu.zzfz().zza(this.mContext, this.zzati, list);
    }
    
    public void recordResolution(final int n) {
        if (n == 1) {
            this.zzqq();
        }
        final Map<String, String> zzqp = this.zzqp();
        zzqp.put("status", String.valueOf(n));
        zzqp.put("sku", this.zzcbr);
        final LinkedList<String> list = new LinkedList<String>();
        final Iterator<String> iterator = this.zzcbs.iterator();
        while (iterator.hasNext()) {
            list.add(zzu.zzfz().zzc(iterator.next(), zzqp));
        }
        zzu.zzfz().zza(this.mContext, this.zzati, list);
    }
    
    protected int zzak(final int n) {
        int n2 = 1;
        if (n != 0) {
            if (n == n2) {
                n2 = 2;
            }
            else if (n == 4) {
                n2 = 3;
            }
            else {
                n2 = 0;
            }
        }
        return n2;
    }
    
    Map<String, String> zzqp() {
        final String packageName = this.mContext.getPackageName();
        String versionName = "";
        while (true) {
            try {
                versionName = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
                final long n = SystemClock.elapsedRealtime() - zzu.zzgd().zztl().zzua();
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("sessionid", zzu.zzgd().getSessionId());
                hashMap.put("appid", packageName);
                hashMap.put("osversion", String.valueOf(Build$VERSION.SDK_INT));
                hashMap.put("sdkversion", this.zzati);
                hashMap.put("appversion", versionName);
                hashMap.put("timestamp", String.valueOf(n));
                return hashMap;
            }
            catch (PackageManager$NameNotFoundException ex) {
                zzb.zzd("Error to retrieve app version", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    void zzqq() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", Context.class, String.class, String.class, Boolean.TYPE).invoke(null, this.mContext, this.zzcbr, "", true);
        }
        catch (ClassNotFoundException ex2) {
            zzb.zzdf("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        }
        catch (NoSuchMethodException ex3) {
            zzb.zzdf("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        }
        catch (Exception ex) {
            zzb.zzd("Fail to report a conversion.", ex);
        }
    }
}
