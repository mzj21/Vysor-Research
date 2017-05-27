// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Collection;
import android.os.Parcel;
import com.google.android.gms.common.zzc;
import android.accounts.Account;
import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class GetServiceRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<GetServiceRequest> CREATOR;
    final int Ci;
    int Cj;
    String Ck;
    IBinder Cl;
    Scope[] Cm;
    Bundle Cn;
    Account Co;
    long Cp;
    final int version;
    
    static {
        CREATOR = (Parcelable$Creator)new zzk();
    }
    
    public GetServiceRequest(final int ci) {
        this.version = 3;
        this.Cj = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.Ci = ci;
    }
    
    GetServiceRequest(final int version, final int ci, final int cj, final String ck, final IBinder cl, final Scope[] cm, final Bundle cn, final Account co, final long cp) {
        this.version = version;
        this.Ci = ci;
        this.Cj = cj;
        this.Ck = ck;
        if (version < 2) {
            this.Co = this.zzdq(cl);
        }
        else {
            this.Cl = cl;
            this.Co = co;
        }
        this.Cm = cm;
        this.Cn = cn;
        this.Cp = cp;
    }
    
    private Account zzdq(final IBinder binder) {
        Account zza = null;
        if (binder != null) {
            zza = com.google.android.gms.common.internal.zza.zza(zzr.zza.zzdr(binder));
        }
        return zza;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzk.zza(this, parcel, n);
    }
    
    public GetServiceRequest zzb(final zzr zzr) {
        if (zzr != null) {
            this.Cl = zzr.asBinder();
        }
        return this;
    }
    
    public GetServiceRequest zzd(final Account co) {
        this.Co = co;
        return this;
    }
    
    public GetServiceRequest zzf(final Collection<Scope> collection) {
        this.Cm = collection.toArray(new Scope[collection.size()]);
        return this;
    }
    
    public GetServiceRequest zzht(final String ck) {
        this.Ck = ck;
        return this;
    }
    
    public GetServiceRequest zzo(final Bundle cn) {
        this.Cn = cn;
        return this;
    }
}
