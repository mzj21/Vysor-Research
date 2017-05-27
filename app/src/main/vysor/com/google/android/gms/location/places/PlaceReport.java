// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzac;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<PlaceReport> CREATOR;
    private final String I;
    private final String aiY;
    private final String mTag;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    PlaceReport(final int mVersionCode, final String aiY, final String mTag, final String i) {
        this.mVersionCode = mVersionCode;
        this.aiY = aiY;
        this.mTag = mTag;
        this.I = i;
    }
    
    public static PlaceReport create(final String s, final String s2) {
        return zzj(s, s2, "unknown");
    }
    
    public static PlaceReport zzj(final String s, final String s2, final String s3) {
        zzac.zzy(s);
        zzac.zzhz(s2);
        zzac.zzhz(s3);
        zzac.zzb(zzla(s3), (Object)"Invalid source");
        return new PlaceReport(1, s, s2, s3);
    }
    
    private static boolean zzla(final String s) {
        boolean b = true;
        int n = -1;
        switch (s.hashCode()) {
            case -284840886: {
                if (s.equals("unknown")) {
                    n = 0;
                    break;
                }
                break;
            }
            case -1194968642: {
                if (s.equals("userReported")) {
                    n = (b ? 1 : 0);
                    break;
                }
                break;
            }
            case -1436706272: {
                if (s.equals("inferredGeofencing")) {
                    n = 2;
                    break;
                }
                break;
            }
            case 1287171955: {
                if (s.equals("inferredRadioSignals")) {
                    n = 3;
                    break;
                }
                break;
            }
            case -262743844: {
                if (s.equals("inferredReverseGeocoding")) {
                    n = 4;
                    break;
                }
                break;
            }
            case 1164924125: {
                if (s.equals("inferredSnappedToRoad")) {
                    n = 5;
                    break;
                }
                break;
            }
        }
        switch (n) {
            default: {
                b = false;
                return b;
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                return b;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof PlaceReport;
        boolean b2 = false;
        if (b) {
            final PlaceReport placeReport = (PlaceReport)o;
            final boolean equal = zzab.equal(this.aiY, placeReport.aiY);
            b2 = false;
            if (equal) {
                final boolean equal2 = zzab.equal(this.mTag, placeReport.mTag);
                b2 = false;
                if (equal2) {
                    final boolean equal3 = zzab.equal(this.I, placeReport.I);
                    b2 = false;
                    if (equal3) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    public String getPlaceId() {
        return this.aiY;
    }
    
    public String getSource() {
        return this.I;
    }
    
    public String getTag() {
        return this.mTag;
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.aiY, this.mTag, this.I);
    }
    
    @Override
    public String toString() {
        final zzab.zza zzx = zzab.zzx(this);
        zzx.zzg("placeId", this.aiY);
        zzx.zzg("tag", this.mTag);
        if (!"unknown".equals(this.I)) {
            zzx.zzg("source", this.I);
        }
        return zzx.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
}
