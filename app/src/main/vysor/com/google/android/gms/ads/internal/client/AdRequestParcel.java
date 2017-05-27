// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzab;
import android.location.Location;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class AdRequestParcel extends AbstractSafeParcelable
{
    public static final zzg CREATOR;
    public final Bundle extras;
    public final int versionCode;
    public final long zzawd;
    public final int zzawe;
    public final List<String> zzawf;
    public final boolean zzawg;
    public final int zzawh;
    public final boolean zzawi;
    public final String zzawj;
    public final SearchAdRequestParcel zzawk;
    public final Location zzawl;
    public final String zzawm;
    public final Bundle zzawn;
    public final Bundle zzawo;
    public final List<String> zzawp;
    public final String zzawq;
    public final String zzawr;
    public final boolean zzaws;
    
    static {
        CREATOR = new zzg();
    }
    
    public AdRequestParcel(final int versionCode, final long zzawd, Bundle extras, final int zzawe, final List<String> zzawf, final boolean zzawg, final int zzawh, final boolean zzawi, final String zzawj, final SearchAdRequestParcel zzawk, final Location zzawl, final String zzawm, Bundle zzawn, final Bundle zzawo, final List<String> zzawp, final String zzawq, final String zzawr, final boolean zzaws) {
        this.versionCode = versionCode;
        this.zzawd = zzawd;
        if (extras == null) {
            extras = new Bundle();
        }
        this.extras = extras;
        this.zzawe = zzawe;
        this.zzawf = zzawf;
        this.zzawg = zzawg;
        this.zzawh = zzawh;
        this.zzawi = zzawi;
        this.zzawj = zzawj;
        this.zzawk = zzawk;
        this.zzawl = zzawl;
        this.zzawm = zzawm;
        if (zzawn == null) {
            zzawn = new Bundle();
        }
        this.zzawn = zzawn;
        this.zzawo = zzawo;
        this.zzawp = zzawp;
        this.zzawq = zzawq;
        this.zzawr = zzawr;
        this.zzaws = zzaws;
    }
    
    public static void zzj(final AdRequestParcel adRequestParcel) {
        adRequestParcel.zzawn.putBundle("com.google.ads.mediation.admob.AdMobAdapter", adRequestParcel.extras);
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof AdRequestParcel;
        boolean b2 = false;
        if (b) {
            final AdRequestParcel adRequestParcel = (AdRequestParcel)o;
            final int versionCode = this.versionCode;
            final int versionCode2 = adRequestParcel.versionCode;
            b2 = false;
            if (versionCode == versionCode2) {
                final long n = lcmp(this.zzawd, adRequestParcel.zzawd);
                b2 = false;
                if (n == 0) {
                    final boolean equal = zzab.equal(this.extras, adRequestParcel.extras);
                    b2 = false;
                    if (equal) {
                        final int zzawe = this.zzawe;
                        final int zzawe2 = adRequestParcel.zzawe;
                        b2 = false;
                        if (zzawe == zzawe2) {
                            final boolean equal2 = zzab.equal(this.zzawf, adRequestParcel.zzawf);
                            b2 = false;
                            if (equal2) {
                                final boolean zzawg = this.zzawg;
                                final boolean zzawg2 = adRequestParcel.zzawg;
                                b2 = false;
                                if (zzawg == zzawg2) {
                                    final int zzawh = this.zzawh;
                                    final int zzawh2 = adRequestParcel.zzawh;
                                    b2 = false;
                                    if (zzawh == zzawh2) {
                                        final boolean zzawi = this.zzawi;
                                        final boolean zzawi2 = adRequestParcel.zzawi;
                                        b2 = false;
                                        if (zzawi == zzawi2) {
                                            final boolean equal3 = zzab.equal(this.zzawj, adRequestParcel.zzawj);
                                            b2 = false;
                                            if (equal3) {
                                                final boolean equal4 = zzab.equal(this.zzawk, adRequestParcel.zzawk);
                                                b2 = false;
                                                if (equal4) {
                                                    final boolean equal5 = zzab.equal(this.zzawl, adRequestParcel.zzawl);
                                                    b2 = false;
                                                    if (equal5) {
                                                        final boolean equal6 = zzab.equal(this.zzawm, adRequestParcel.zzawm);
                                                        b2 = false;
                                                        if (equal6) {
                                                            final boolean equal7 = zzab.equal(this.zzawn, adRequestParcel.zzawn);
                                                            b2 = false;
                                                            if (equal7) {
                                                                final boolean equal8 = zzab.equal(this.zzawo, adRequestParcel.zzawo);
                                                                b2 = false;
                                                                if (equal8) {
                                                                    final boolean equal9 = zzab.equal(this.zzawp, adRequestParcel.zzawp);
                                                                    b2 = false;
                                                                    if (equal9) {
                                                                        final boolean equal10 = zzab.equal(this.zzawq, adRequestParcel.zzawq);
                                                                        b2 = false;
                                                                        if (equal10) {
                                                                            final boolean equal11 = zzab.equal(this.zzawr, adRequestParcel.zzawr);
                                                                            b2 = false;
                                                                            if (equal11) {
                                                                                final boolean zzaws = this.zzaws;
                                                                                final boolean zzaws2 = adRequestParcel.zzaws;
                                                                                b2 = false;
                                                                                if (zzaws == zzaws2) {
                                                                                    b2 = true;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return zzab.hashCode(this.versionCode, this.zzawd, this.extras, this.zzawe, this.zzawf, this.zzawg, this.zzawh, this.zzawi, this.zzawj, this.zzawk, this.zzawl, this.zzawm, this.zzawn, this.zzawo, this.zzawp, this.zzawq, this.zzawr, this.zzaws);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
}
