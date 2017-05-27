// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class PlayLoggerContext extends AbstractSafeParcelable
{
    public static final zza CREATOR;
    public final boolean axA;
    public final int axB;
    public final int axu;
    public final int axv;
    public final String axw;
    public final String axx;
    public final boolean axy;
    public final String axz;
    public final String packageName;
    public final int versionCode;
    
    static {
        CREATOR = new zza();
    }
    
    public PlayLoggerContext(final int versionCode, final String packageName, final int axu, final int axv, final String axw, final String axx, final boolean axy, final String axz, final boolean axA, final int axB) {
        this.versionCode = versionCode;
        this.packageName = packageName;
        this.axu = axu;
        this.axv = axv;
        this.axw = axw;
        this.axx = axx;
        this.axy = axy;
        this.axz = axz;
        this.axA = axA;
        this.axB = axB;
    }
    
    public PlayLoggerContext(final String s, final int axu, final int axv, final String axz, final String axw, final String axx, final boolean axA, final int axB) {
        this.versionCode = 1;
        this.packageName = zzac.zzy(s);
        this.axu = axu;
        this.axv = axv;
        this.axz = axz;
        this.axw = axw;
        this.axx = axx;
        this.axy = !axA;
        this.axA = axA;
        this.axB = axB;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            if (o instanceof PlayLoggerContext) {
                final PlayLoggerContext playLoggerContext = (PlayLoggerContext)o;
                if (this.versionCode != playLoggerContext.versionCode || !this.packageName.equals(playLoggerContext.packageName) || this.axu != playLoggerContext.axu || this.axv != playLoggerContext.axv || !zzab.equal(this.axz, playLoggerContext.axz) || !zzab.equal(this.axw, playLoggerContext.axw) || !zzab.equal(this.axx, playLoggerContext.axx) || this.axy != playLoggerContext.axy || this.axA != playLoggerContext.axA || this.axB != playLoggerContext.axB) {
                    b = false;
                }
            }
            else {
                b = false;
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.versionCode, this.packageName, this.axu, this.axv, this.axz, this.axw, this.axx, this.axy, this.axA, this.axB);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("versionCode=").append(this.versionCode).append(',');
        sb.append("package=").append(this.packageName).append(',');
        sb.append("packageVersionCode=").append(this.axu).append(',');
        sb.append("logSource=").append(this.axv).append(',');
        sb.append("logSourceName=").append(this.axz).append(',');
        sb.append("uploadAccount=").append(this.axw).append(',');
        sb.append("loggingId=").append(this.axx).append(',');
        sb.append("logAndroidId=").append(this.axy).append(',');
        sb.append("isAnonymous=").append(this.axA).append(',');
        sb.append("qosTier=").append(this.axB);
        sb.append("]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
}
