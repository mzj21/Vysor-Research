// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class SignInRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<SignInRequest> CREATOR;
    final ResolveAccountRequest aAm;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzh();
    }
    
    SignInRequest(final int mVersionCode, final ResolveAccountRequest aAm) {
        this.mVersionCode = mVersionCode;
        this.aAm = aAm;
    }
    
    public SignInRequest(final ResolveAccountRequest resolveAccountRequest) {
        this(1, resolveAccountRequest);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
    
    public ResolveAccountRequest zzcdk() {
        return this.aAm;
    }
}
