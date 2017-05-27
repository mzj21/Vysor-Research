// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import android.os.Build$VERSION;
import java.util.LinkedHashMap;
import java.util.Map;
import android.content.Context;

@zziy
public class zzdj
{
    private Context mContext;
    private String zzati;
    private boolean zzbhy;
    private String zzbhz;
    private Map<String, String> zzbia;
    
    public zzdj(final Context mContext, final String zzati) {
        this.mContext = null;
        this.zzati = null;
        this.mContext = mContext;
        this.zzati = zzati;
        this.zzbhy = zzdi.zzbca.get();
        this.zzbhz = zzdi.zzbcb.get();
        (this.zzbia = new LinkedHashMap<String, String>()).put("s", "gmob_sdk");
        this.zzbia.put("v", "3");
        this.zzbia.put("os", Build$VERSION.RELEASE);
        this.zzbia.put("sdk", Build$VERSION.SDK);
        this.zzbia.put("device", zzu.zzfz().zzuj());
        final Map<String, String> zzbia = this.zzbia;
        String s;
        if (mContext.getApplicationContext() != null) {
            s = mContext.getApplicationContext().getPackageName();
        }
        else {
            s = mContext.getPackageName();
        }
        zzbia.put("app", s);
        final Map<String, String> zzbia2 = this.zzbia;
        String s2;
        if (zzu.zzfz().zzan(mContext)) {
            s2 = "1";
        }
        else {
            s2 = "0";
        }
        zzbia2.put("is_lite_sdk", s2);
        final zzjh zzy = zzu.zzgi().zzy(this.mContext);
        this.zzbia.put("network_coarse", Integer.toString(zzy.zzcmd));
        this.zzbia.put("network_fine", Integer.toString(zzy.zzcme));
    }
    
    Context getContext() {
        return this.mContext;
    }
    
    String zzhy() {
        return this.zzati;
    }
    
    boolean zzkt() {
        return this.zzbhy;
    }
    
    String zzku() {
        return this.zzbhz;
    }
    
    Map<String, String> zzkv() {
        return this.zzbia;
    }
}
