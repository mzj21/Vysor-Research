// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;

@zziy
public class zzlv
{
    public zzlt zza(final Context context, final AdSizeParcel adSizeParcel, final boolean b, final boolean b2, @Nullable final zzau zzau, final VersionInfoParcel versionInfoParcel) {
        return this.zza(context, adSizeParcel, b, b2, zzau, versionInfoParcel, null, null, null);
    }
    
    public zzlt zza(final Context context, final AdSizeParcel adSizeParcel, final boolean b, final boolean b2, @Nullable final zzau zzau, final VersionInfoParcel versionInfoParcel, final zzdq zzdq, final zzs zzs, final zzd zzd) {
        final zzlw zzlw = new zzlw(zzlx.zzb(context, adSizeParcel, b, b2, zzau, versionInfoParcel, zzdq, zzs, zzd));
        zzlw.setWebViewClient(zzu.zzgb().zzb(zzlw, b2));
        zzlw.setWebChromeClient(zzu.zzgb().zzn(zzlw));
        return zzlw;
    }
}
