// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class SignInResponse extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<SignInResponse> CREATOR;
    private final ResolveAccountResponse aAn;
    final int mVersionCode;
    private final ConnectionResult vm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    public SignInResponse(final int n) {
        this(new ConnectionResult(n, null), null);
    }
    
    SignInResponse(final int mVersionCode, final ConnectionResult vm, final ResolveAccountResponse aAn) {
        this.mVersionCode = mVersionCode;
        this.vm = vm;
        this.aAn = aAn;
    }
    
    public SignInResponse(final ConnectionResult connectionResult, final ResolveAccountResponse resolveAccountResponse) {
        this(1, connectionResult, resolveAccountResponse);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
    
    public ConnectionResult zzave() {
        return this.vm;
    }
    
    public ResolveAccountResponse zzcdl() {
        return this.aAn;
    }
}
