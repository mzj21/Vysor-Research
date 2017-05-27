// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.gass.internal;

import com.google.android.gms.internal.zzark;
import android.os.Parcel;
import com.google.android.gms.internal.zzarj;
import com.google.android.gms.internal.zzae;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class GassResponseParcel extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<GassResponseParcel> CREATOR;
    private zzae.zza aeA;
    private byte[] aeB;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    GassResponseParcel(final int versionCode, final byte[] aeB) {
        this.versionCode = versionCode;
        this.aeA = null;
        this.aeB = aeB;
        this.zzayw();
    }
    
    private void zzayu() {
        Label_0023: {
            if (this.zzayv()) {
                break Label_0023;
            }
            try {
                this.aeA = zzae.zza.zzc(this.aeB);
                this.aeB = null;
                this.zzayw();
            }
            catch (zzarj zzarj) {
                throw new IllegalStateException(zzarj);
            }
        }
    }
    
    private boolean zzayv() {
        return this.aeA != null;
    }
    
    private void zzayw() {
        if ((this.aeA == null && this.aeB != null) || (this.aeA != null && this.aeB == null)) {
            return;
        }
        if (this.aeA != null && this.aeB != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        }
        if (this.aeA == null && this.aeB == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        }
        throw new IllegalStateException("Impossible");
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
    
    public byte[] zzbnv() {
        byte[] array;
        if (this.aeB != null) {
            array = this.aeB;
        }
        else {
            array = zzark.zzf(this.aeA);
        }
        return array;
    }
    
    public zzae.zza zzbnw() {
        this.zzayu();
        return this.aeA;
    }
}
