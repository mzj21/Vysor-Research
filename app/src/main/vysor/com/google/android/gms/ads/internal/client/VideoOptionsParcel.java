// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public class VideoOptionsParcel extends AbstractSafeParcelable
{
    public static final zzaq CREATOR;
    public final int versionCode;
    public final boolean zzbac;
    
    static {
        CREATOR = new zzaq();
    }
    
    public VideoOptionsParcel(final int versionCode, final boolean zzbac) {
        this.versionCode = versionCode;
        this.zzbac = zzbac;
    }
    
    public VideoOptionsParcel(final VideoOptions videoOptions) {
        this(1, videoOptions.getStartMuted());
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzaq.zza(this, parcel, n);
    }
}
