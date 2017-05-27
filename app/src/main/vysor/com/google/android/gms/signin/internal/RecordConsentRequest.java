// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.accounts.Account;
import com.google.android.gms.common.api.Scope;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class RecordConsentRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<RecordConsentRequest> CREATOR;
    private final Scope[] aAj;
    private final Account ec;
    private final String hk;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    RecordConsentRequest(final int mVersionCode, final Account ec, final Scope[] aAj, final String hk) {
        this.mVersionCode = mVersionCode;
        this.ec = ec;
        this.aAj = aAj;
        this.hk = hk;
    }
    
    public Account getAccount() {
        return this.ec;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
    
    public String zzahn() {
        return this.hk;
    }
    
    public Scope[] zzcdi() {
        return this.aAj;
    }
}
