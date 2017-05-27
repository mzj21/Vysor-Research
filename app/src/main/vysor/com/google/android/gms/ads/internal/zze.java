// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import java.util.Iterator;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import android.content.Context;
import com.google.android.gms.internal.zziy;

@zziy
public class zze
{
    private final Context mContext;
    private final AutoClickProtectionConfigurationParcel zzamg;
    private boolean zzamh;
    
    public zze(final Context context) {
        this(context, false);
    }
    
    public zze(final Context mContext, @Nullable final zzke.zza zza) {
        this.mContext = mContext;
        if (zza != null && zza.zzcop.zzchv != null) {
            this.zzamg = zza.zzcop.zzchv;
        }
        else {
            this.zzamg = new AutoClickProtectionConfigurationParcel();
        }
    }
    
    public zze(final Context mContext, final boolean b) {
        this.mContext = mContext;
        this.zzamg = new AutoClickProtectionConfigurationParcel(b);
    }
    
    public void recordClick() {
        this.zzamh = true;
    }
    
    public boolean zzer() {
        return !this.zzamg.zzchz || this.zzamh;
    }
    
    public void zzv(@Nullable String s) {
        if (s == null) {
            s = "";
        }
        zzb.zzde("Action was blocked because no touch was detected.");
        if (this.zzamg.zzchz && this.zzamg.zzcia != null) {
            for (final String s2 : this.zzamg.zzcia) {
                if (!TextUtils.isEmpty((CharSequence)s2)) {
                    zzu.zzfz().zzc(this.mContext, "", s2.replace("{NAVIGATION_URL}", Uri.encode(s)));
                }
            }
        }
    }
}
