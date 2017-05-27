// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable$Creator;

public final class ConnectionEvent extends StatsEvent
{
    public static final Parcelable$Creator<ConnectionEvent> CREATOR;
    private final long DM;
    private int DN;
    private final String DO;
    private final String DP;
    private final String DQ;
    private final String DR;
    private final String DS;
    private final String DT;
    private final long DU;
    private final long DV;
    private long DW;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    ConnectionEvent(final int mVersionCode, final long dm, final int dn, final String do1, final String dp, final String dq, final String dr, final String ds, final String dt, final long du, final long dv) {
        this.mVersionCode = mVersionCode;
        this.DM = dm;
        this.DN = dn;
        this.DO = do1;
        this.DP = dp;
        this.DQ = dq;
        this.DR = dr;
        this.DW = -1L;
        this.DS = ds;
        this.DT = dt;
        this.DU = du;
        this.DV = dv;
    }
    
    public ConnectionEvent(final long n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final long n3, final long n4) {
        this(1, n, n2, s, s2, s3, s4, s5, s6, n3, n4);
    }
    
    @Override
    public int getEventType() {
        return this.DN;
    }
    
    @Override
    public long getTimeMillis() {
        return this.DM;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzawk() {
        return this.DO;
    }
    
    public String zzawl() {
        return this.DP;
    }
    
    public String zzawm() {
        return this.DQ;
    }
    
    public String zzawn() {
        return this.DR;
    }
    
    public String zzawo() {
        return this.DS;
    }
    
    public String zzawp() {
        return this.DT;
    }
    
    @Override
    public long zzawq() {
        return this.DW;
    }
    
    public long zzawr() {
        return this.DV;
    }
    
    public long zzaws() {
        return this.DU;
    }
    
    @Override
    public String zzawt() {
        final String value = String.valueOf("\t");
        final String value2 = String.valueOf(this.zzawk());
        final String value3 = String.valueOf(this.zzawl());
        final String value4 = String.valueOf("\t");
        final String value5 = String.valueOf(this.zzawm());
        final String value6 = String.valueOf(this.zzawn());
        final String value7 = String.valueOf("\t");
        String ds;
        if (this.DS == null) {
            ds = "";
        }
        else {
            ds = this.DS;
        }
        final String value8 = String.valueOf("\t");
        return new StringBuilder(22 + String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(value3).length() + String.valueOf(value4).length() + String.valueOf(value5).length() + String.valueOf(value6).length() + String.valueOf(value7).length() + String.valueOf(ds).length() + String.valueOf(value8).length()).append(value).append(value2).append("/").append(value3).append(value4).append(value5).append("/").append(value6).append(value7).append(ds).append(value8).append(this.zzawr()).toString();
    }
}
