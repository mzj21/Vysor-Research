// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepName;
import android.os.Parcelable;

@KeepName
public final class BinderWrapper implements Parcelable
{
    public static final Parcelable$Creator<BinderWrapper> CREATOR;
    private IBinder Bz;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<BinderWrapper>() {
            public BinderWrapper zzcj(final Parcel parcel) {
                return new BinderWrapper(parcel, null);
            }
            
            public BinderWrapper[] zzgm(final int n) {
                return new BinderWrapper[n];
            }
        };
    }
    
    public BinderWrapper() {
        this.Bz = null;
    }
    
    public BinderWrapper(final IBinder bz) {
        this.Bz = null;
        this.Bz = bz;
    }
    
    private BinderWrapper(final Parcel parcel) {
        this.Bz = null;
        this.Bz = parcel.readStrongBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStrongBinder(this.Bz);
    }
}
