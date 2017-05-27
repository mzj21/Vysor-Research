// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import com.google.android.gms.common.ConnectionResult;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class ResolveAccountResponse extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountResponse> CREATOR;
    IBinder AW;
    private boolean CX;
    final int mVersionCode;
    private ConnectionResult vm;
    private boolean xz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzae();
    }
    
    ResolveAccountResponse(final int mVersionCode, final IBinder aw, final ConnectionResult vm, final boolean xz, final boolean cx) {
        this.mVersionCode = mVersionCode;
        this.AW = aw;
        this.vm = vm;
        this.xz = xz;
        this.CX = cx;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            if (!(o instanceof ResolveAccountResponse)) {
                b = false;
            }
            else {
                final ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse)o;
                if (!this.vm.equals(resolveAccountResponse.vm) || !this.zzavd().equals(resolveAccountResponse.zzavd())) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzae.zza(this, parcel, n);
    }
    
    public zzr zzavd() {
        return zzr.zza.zzdr(this.AW);
    }
    
    public ConnectionResult zzave() {
        return this.vm;
    }
    
    public boolean zzavf() {
        return this.xz;
    }
    
    public boolean zzavg() {
        return this.CX;
    }
}
