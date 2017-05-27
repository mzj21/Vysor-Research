// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzac;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<Scope> CREATOR;
    final int mVersionCode;
    private final String vX;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    Scope(final int mVersionCode, final String vx) {
        zzac.zzh(vx, "scopeUri must not be null or empty");
        this.mVersionCode = mVersionCode;
        this.vX = vx;
    }
    
    public Scope(final String s) {
        this(1, s);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof Scope && this.vX.equals(((Scope)o).vX));
    }
    
    @Override
    public int hashCode() {
        return this.vX.hashCode();
    }
    
    @Override
    public String toString() {
        return this.vX;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
    
    public String zzaqg() {
        return this.vX;
    }
}
