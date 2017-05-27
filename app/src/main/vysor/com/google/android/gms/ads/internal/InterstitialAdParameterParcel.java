// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.os.Parcel;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class InterstitialAdParameterParcel extends AbstractSafeParcelable
{
    public static final zzm CREATOR;
    public final int versionCode;
    public final boolean zzanx;
    public final boolean zzany;
    public final String zzanz;
    public final boolean zzaoa;
    public final float zzaob;
    public final int zzaoc;
    
    static {
        CREATOR = new zzm();
    }
    
    InterstitialAdParameterParcel(final int versionCode, final boolean zzanx, final boolean zzany, final String zzanz, final boolean zzaoa, final float zzaob, final int zzaoc) {
        this.versionCode = versionCode;
        this.zzanx = zzanx;
        this.zzany = zzany;
        this.zzanz = zzanz;
        this.zzaoa = zzaoa;
        this.zzaob = zzaob;
        this.zzaoc = zzaoc;
    }
    
    public InterstitialAdParameterParcel(final boolean b, final boolean b2, final boolean b3, final float n, final int n2) {
        this(3, b, b2, null, b3, n, n2);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzm.zza(this, parcel, n);
    }
}
