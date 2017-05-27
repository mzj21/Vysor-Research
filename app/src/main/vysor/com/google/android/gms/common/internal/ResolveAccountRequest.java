// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import android.os.Parcel;
import android.accounts.Account;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class ResolveAccountRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountRequest> CREATOR;
    private final int CV;
    private final GoogleSignInAccount CW;
    private final Account ec;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzad();
    }
    
    ResolveAccountRequest(final int mVersionCode, final Account ec, final int cv, final GoogleSignInAccount cw) {
        this.mVersionCode = mVersionCode;
        this.ec = ec;
        this.CV = cv;
        this.CW = cw;
    }
    
    public ResolveAccountRequest(final Account account, final int n, final GoogleSignInAccount googleSignInAccount) {
        this(2, account, n, googleSignInAccount);
    }
    
    public Account getAccount() {
        return this.ec;
    }
    
    public int getSessionId() {
        return this.CV;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzad.zza(this, parcel, n);
    }
    
    @Nullable
    public GoogleSignInAccount zzavc() {
        return this.CW;
    }
}
