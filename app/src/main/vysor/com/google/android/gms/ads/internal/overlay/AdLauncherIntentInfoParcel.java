// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.os.Parcel;
import android.content.Intent;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class AdLauncherIntentInfoParcel extends AbstractSafeParcelable
{
    public static final zzb CREATOR;
    public final Intent intent;
    public final String mimeType;
    public final String packageName;
    public final String url;
    public final int versionCode;
    public final String zzbwe;
    public final String zzbwf;
    public final String zzbwg;
    public final String zzbwh;
    
    static {
        CREATOR = new zzb();
    }
    
    public AdLauncherIntentInfoParcel(final int versionCode, final String zzbwe, final String url, final String mimeType, final String packageName, final String zzbwf, final String zzbwg, final String zzbwh, final Intent intent) {
        this.versionCode = versionCode;
        this.zzbwe = zzbwe;
        this.url = url;
        this.mimeType = mimeType;
        this.packageName = packageName;
        this.zzbwf = zzbwf;
        this.zzbwg = zzbwg;
        this.zzbwh = zzbwh;
        this.intent = intent;
    }
    
    public AdLauncherIntentInfoParcel(final Intent intent) {
        this(2, null, null, null, null, null, null, null, intent);
    }
    
    public AdLauncherIntentInfoParcel(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        this(2, s, s2, s3, s4, s5, s6, s7, null);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
