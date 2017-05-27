// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.client;

import android.os.Parcel;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class RewardedVideoAdRequestParcel extends AbstractSafeParcelable
{
    public static final zzh CREATOR;
    public final int versionCode;
    public final String zzaqt;
    public final AdRequestParcel zzcfu;
    
    static {
        CREATOR = new zzh();
    }
    
    public RewardedVideoAdRequestParcel(final int versionCode, final AdRequestParcel zzcfu, final String zzaqt) {
        this.versionCode = versionCode;
        this.zzcfu = zzcfu;
        this.zzaqt = zzaqt;
    }
    
    public RewardedVideoAdRequestParcel(final AdRequestParcel adRequestParcel, final String s) {
        this(1, adRequestParcel, s);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
}
