// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class AuthAccountRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<AuthAccountRequest> CREATOR;
    final IBinder AW;
    final Scope[] AX;
    Integer AY;
    Integer AZ;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    AuthAccountRequest(final int mVersionCode, final IBinder aw, final Scope[] ax, final Integer ay, final Integer az) {
        this.mVersionCode = mVersionCode;
        this.AW = aw;
        this.AX = ax;
        this.AY = ay;
        this.AZ = az;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
}
