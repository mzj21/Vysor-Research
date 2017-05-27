// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public class CapabilityParcel extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<CapabilityParcel> CREATOR;
    public final int versionCode;
    public final boolean zzcib;
    public final boolean zzcic;
    public final boolean zzcid;
    
    static {
        CREATOR = (Parcelable$Creator)new zzj();
    }
    
    CapabilityParcel(final int versionCode, final boolean zzcib, final boolean zzcic, final boolean zzcid) {
        this.versionCode = versionCode;
        this.zzcib = zzcib;
        this.zzcic = zzcic;
        this.zzcid = zzcid;
    }
    
    public CapabilityParcel(final boolean b, final boolean b2, final boolean b3) {
        this(2, b, b2, b3);
    }
    
    public Bundle toBundle() {
        final Bundle bundle = new Bundle();
        bundle.putBoolean("iap_supported", this.zzcib);
        bundle.putBoolean("default_iap_supported", this.zzcic);
        bundle.putBoolean("app_streaming_supported", this.zzcid);
        return bundle;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzj.zza(this, parcel, n);
    }
}
