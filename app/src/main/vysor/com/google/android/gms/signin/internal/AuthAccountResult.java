// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import android.content.Intent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class AuthAccountResult extends AbstractSafeParcelable implements Result
{
    public static final Parcelable$Creator<AuthAccountResult> CREATOR;
    private int aAf;
    private Intent aAg;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public AuthAccountResult() {
        this(0, null);
    }
    
    AuthAccountResult(final int mVersionCode, final int aAf, final Intent aAg) {
        this.mVersionCode = mVersionCode;
        this.aAf = aAf;
        this.aAg = aAg;
    }
    
    public AuthAccountResult(final int n, final Intent intent) {
        this(2, n, intent);
    }
    
    @Override
    public Status getStatus() {
        Status status;
        if (this.aAf == 0) {
            status = Status.vY;
        }
        else {
            status = Status.wc;
        }
        return status;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public int zzcdg() {
        return this.aAf;
    }
    
    public Intent zzcdh() {
        return this.aAg;
    }
}
