// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gass.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class GassRequestParcel extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<GassRequestParcel> CREATOR;
    public final String aez;
    public final String packageName;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    GassRequestParcel(final int versionCode, final String packageName, final String aez) {
        this.versionCode = versionCode;
        this.packageName = packageName;
        this.aez = aez;
    }
    
    public GassRequestParcel(final String s, final String s2) {
        this(1, s, s2);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
