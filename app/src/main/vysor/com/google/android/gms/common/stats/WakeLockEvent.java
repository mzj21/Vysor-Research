// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.text.TextUtils;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;

public final class WakeLockEvent extends StatsEvent
{
    public static final Parcelable$Creator<WakeLockEvent> CREATOR;
    private final long DM;
    private int DN;
    private final long DU;
    private long DW;
    private final String EA;
    private final int EB;
    private final List<String> EC;
    private final String ED;
    private int EE;
    private final String EF;
    private final float EG;
    private final String Ey;
    private final String Ez;
    private final long mTimeout;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    WakeLockEvent(final int mVersionCode, final long dm, final int dn, final String ey, final int eb, final List<String> ec, final String ed, final long du, final int ee, final String ez, final String ef, final float eg, final long mTimeout, final String ea) {
        this.mVersionCode = mVersionCode;
        this.DM = dm;
        this.DN = dn;
        this.Ey = ey;
        this.Ez = ez;
        this.EA = ea;
        this.EB = eb;
        this.DW = -1L;
        this.EC = ec;
        this.ED = ed;
        this.DU = du;
        this.EE = ee;
        this.EF = ef;
        this.EG = eg;
        this.mTimeout = mTimeout;
    }
    
    public WakeLockEvent(final long n, final int n2, final String s, final int n3, final List<String> list, final String s2, final long n4, final int n5, final String s3, final String s4, final float n6, final long n7, final String s5) {
        this(2, n, n2, s, n3, list, s2, n4, n5, s3, s4, n6, n7, s5);
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
        zzg.zza(this, parcel, n);
    }
    
    public String zzawp() {
        return this.ED;
    }
    
    @Override
    public long zzawq() {
        return this.DW;
    }
    
    public long zzaws() {
        return this.DU;
    }
    
    @Override
    public String zzawt() {
        final String value = String.valueOf("\t");
        final String value2 = String.valueOf(this.zzaww());
        final String value3 = String.valueOf("\t");
        final int zzawz = this.zzawz();
        final String value4 = String.valueOf("\t");
        String join;
        if (this.zzaxa() == null) {
            join = "";
        }
        else {
            join = TextUtils.join((CharSequence)",", (Iterable)this.zzaxa());
        }
        final String value5 = String.valueOf("\t");
        final int zzaxb = this.zzaxb();
        final String value6 = String.valueOf("\t");
        String zzawx;
        if (this.zzawx() == null) {
            zzawx = "";
        }
        else {
            zzawx = this.zzawx();
        }
        final String value7 = String.valueOf("\t");
        String zzaxc;
        if (this.zzaxc() == null) {
            zzaxc = "";
        }
        else {
            zzaxc = this.zzaxc();
        }
        final String value8 = String.valueOf("\t");
        final float zzaxd = this.zzaxd();
        final String value9 = String.valueOf("\t");
        String zzawy;
        if (this.zzawy() == null) {
            zzawy = "";
        }
        else {
            zzawy = this.zzawy();
        }
        return new StringBuilder(37 + String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(value3).length() + String.valueOf(value4).length() + String.valueOf(join).length() + String.valueOf(value5).length() + String.valueOf(value6).length() + String.valueOf(zzawx).length() + String.valueOf(value7).length() + String.valueOf(zzaxc).length() + String.valueOf(value8).length() + String.valueOf(value9).length() + String.valueOf(zzawy).length()).append(value).append(value2).append(value3).append(zzawz).append(value4).append(join).append(value5).append(zzaxb).append(value6).append(zzawx).append(value7).append(zzaxc).append(value8).append(zzaxd).append(value9).append(zzawy).toString();
    }
    
    public String zzaww() {
        return this.Ey;
    }
    
    public String zzawx() {
        return this.Ez;
    }
    
    public String zzawy() {
        return this.EA;
    }
    
    public int zzawz() {
        return this.EB;
    }
    
    public List<String> zzaxa() {
        return this.EC;
    }
    
    public int zzaxb() {
        return this.EE;
    }
    
    public String zzaxc() {
        return this.EF;
    }
    
    public float zzaxd() {
        return this.EG;
    }
    
    public long zzaxe() {
        return this.mTimeout;
    }
}
