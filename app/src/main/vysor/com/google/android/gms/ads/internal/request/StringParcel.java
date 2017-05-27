// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public class StringParcel extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<StringParcel> CREATOR;
    final int mVersionCode;
    String zzbiw;
    
    static {
        CREATOR = (Parcelable$Creator)new zzo();
    }
    
    StringParcel(final int mVersionCode, final String zzbiw) {
        this.mVersionCode = mVersionCode;
        this.zzbiw = zzbiw;
    }
    
    public StringParcel(final String zzbiw) {
        this.mVersionCode = 1;
        this.zzbiw = zzbiw;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzo.zza(this, parcel, n);
    }
    
    public String zzsb() {
        return this.zzbiw;
    }
}
