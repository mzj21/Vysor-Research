// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class CheckServerAuthResult extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<CheckServerAuthResult> CREATOR;
    final boolean aAh;
    final List<Scope> aAi;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    CheckServerAuthResult(final int mVersionCode, final boolean aAh, final List<Scope> aAi) {
        this.mVersionCode = mVersionCode;
        this.aAh = aAh;
        this.aAi = aAi;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
