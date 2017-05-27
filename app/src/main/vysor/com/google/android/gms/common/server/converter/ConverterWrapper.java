// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class ConverterWrapper extends AbstractSafeParcelable
{
    public static final zza CREATOR;
    private final StringToIntConverter Dn;
    private final int mVersionCode;
    
    static {
        CREATOR = new zza();
    }
    
    ConverterWrapper(final int mVersionCode, final StringToIntConverter dn) {
        this.mVersionCode = mVersionCode;
        this.Dn = dn;
    }
    
    private ConverterWrapper(final StringToIntConverter dn) {
        this.mVersionCode = 1;
        this.Dn = dn;
    }
    
    public static ConverterWrapper zza(final FastJsonResponse.zza<?, ?> zza) {
        if (zza instanceof StringToIntConverter) {
            return new ConverterWrapper((StringToIntConverter)zza);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zza creator = ConverterWrapper.CREATOR;
        zza.zza(this, parcel, n);
    }
    
    StringToIntConverter zzavn() {
        return this.Dn;
    }
    
    public FastJsonResponse.zza<?, ?> zzavo() {
        if (this.Dn != null) {
            return this.Dn;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
