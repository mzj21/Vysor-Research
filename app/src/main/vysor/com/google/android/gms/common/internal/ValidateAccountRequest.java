// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
public class ValidateAccountRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ValidateAccountRequest> CREATOR;
    final IBinder AW;
    private final Scope[] AX;
    private final int De;
    private final Bundle Df;
    private final String Dg;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzak();
    }
    
    ValidateAccountRequest(final int mVersionCode, final int de, final IBinder aw, final Scope[] ax, final Bundle df, final String dg) {
        this.mVersionCode = mVersionCode;
        this.De = de;
        this.AW = aw;
        this.AX = ax;
        this.Df = df;
        this.Dg = dg;
    }
    
    public String getCallingPackage() {
        return this.Dg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzak.zza(this, parcel, n);
    }
    
    public Scope[] zzavj() {
        return this.AX;
    }
    
    public int zzavl() {
        return this.De;
    }
    
    public Bundle zzavm() {
        return this.Df;
    }
}
