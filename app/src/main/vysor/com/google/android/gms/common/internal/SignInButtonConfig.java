// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Scope;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class SignInButtonConfig extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<SignInButtonConfig> CREATOR;
    @Deprecated
    private final Scope[] AX;
    private final int CY;
    private final int CZ;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaf();
    }
    
    SignInButtonConfig(final int mVersionCode, final int cy, final int cz, final Scope[] ax) {
        this.mVersionCode = mVersionCode;
        this.CY = cy;
        this.CZ = cz;
        this.AX = ax;
    }
    
    public SignInButtonConfig(final int n, final int n2, final Scope[] array) {
        this(1, n, n2, null);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzaf.zza(this, parcel, n);
    }
    
    public int zzavh() {
        return this.CY;
    }
    
    public int zzavi() {
        return this.CZ;
    }
    
    @Deprecated
    public Scope[] zzavj() {
        return this.AX;
    }
}
