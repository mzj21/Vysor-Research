// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class VersionInfoParcel extends AbstractSafeParcelable
{
    public static final zzd CREATOR;
    public final int versionCode;
    public String zzcs;
    public int zzcts;
    public int zzctt;
    public boolean zzctu;
    
    static {
        CREATOR = new zzd();
    }
    
    public VersionInfoParcel(final int n, final int n2, final boolean b) {
        this(n, n2, b, false);
    }
    
    public VersionInfoParcel(final int n, final int n2, final boolean b, final boolean b2) {
        final String value = String.valueOf("afma-sdk-a-v");
        String s;
        if (b) {
            s = "0";
        }
        else if (b2) {
            s = "2";
        }
        else {
            s = "1";
        }
        this(1, new StringBuilder(24 + String.valueOf(value).length() + String.valueOf(s).length()).append(value).append(n).append(".").append(n2).append(".").append(s).toString(), n, n2, b);
    }
    
    VersionInfoParcel(final int versionCode, final String zzcs, final int zzcts, final int zzctt, final boolean zzctu) {
        this.versionCode = versionCode;
        this.zzcs = zzcs;
        this.zzcts = zzcts;
        this.zzctt = zzctt;
        this.zzctu = zzctu;
    }
    
    public static VersionInfoParcel zzvg() {
        return new VersionInfoParcel(9683208, 9683208, true);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
}
