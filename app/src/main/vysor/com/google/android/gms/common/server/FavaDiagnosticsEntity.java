// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final zza CREATOR;
    public final String Dl;
    public final int Dm;
    final int mVersionCode;
    
    static {
        CREATOR = new zza();
    }
    
    public FavaDiagnosticsEntity(final int mVersionCode, final String dl, final int dm) {
        this.mVersionCode = mVersionCode;
        this.Dl = dl;
        this.Dm = dm;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
}
