// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.phenotype;

import java.util.Comparator;
import android.os.Parcel;
import java.util.Arrays;
import com.google.android.gms.common.internal.zzab;
import java.nio.charset.Charset;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class Flag extends AbstractSafeParcelable implements Comparable<Flag>
{
    public static final Parcelable$Creator<Flag> CREATOR;
    private static final Charset UTF_8;
    public static final zza axt;
    final String Dr;
    final double afA;
    final boolean afy;
    final long axp;
    final byte[] axq;
    public final int axr;
    public final int axs;
    final int mVersionCode;
    public final String name;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
        UTF_8 = Charset.forName("UTF-8");
        axt = new zza();
    }
    
    Flag(final int mVersionCode, final String name, final long axp, final boolean afy, final double afA, final String dr, final byte[] axq, final int axr, final int axs) {
        this.mVersionCode = mVersionCode;
        this.name = name;
        this.axp = axp;
        this.afy = afy;
        this.afA = afA;
        this.Dr = dr;
        this.axq = axq;
        this.axr = axr;
        this.axs = axs;
    }
    
    private static int compare(final byte b, final byte b2) {
        return b - b2;
    }
    
    private static int compare(final int n, final int n2) {
        int n3;
        if (n < n2) {
            n3 = -1;
        }
        else if (n == n2) {
            n3 = 0;
        }
        else {
            n3 = 1;
        }
        return n3;
    }
    
    private static int compare(final long n, final long n2) {
        int n3;
        if (n < n2) {
            n3 = -1;
        }
        else if (n == n2) {
            n3 = 0;
        }
        else {
            n3 = 1;
        }
        return n3;
    }
    
    private static int compare(final String s, final String s2) {
        int compareTo;
        if (s == s2) {
            compareTo = 0;
        }
        else if (s == null) {
            compareTo = -1;
        }
        else if (s2 == null) {
            compareTo = 1;
        }
        else {
            compareTo = s.compareTo(s2);
        }
        return compareTo;
    }
    
    private static int compare(final boolean b, final boolean b2) {
        int n;
        if (b == b2) {
            n = 0;
        }
        else if (b) {
            n = 1;
        }
        else {
            n = -1;
        }
        return n;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (o != null && o instanceof Flag) {
            final Flag flag = (Flag)o;
            if (this.mVersionCode != flag.mVersionCode || !zzab.equal(this.name, flag.name) || this.axr != flag.axr || this.axs != flag.axs) {
                b = false;
            }
            else {
                switch (this.axr) {
                    default: {
                        throw new AssertionError((Object)new StringBuilder(31).append("Invalid enum value: ").append(this.axr).toString());
                    }
                    case 1: {
                        if (this.axp != flag.axp) {
                            b = false;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.afy != flag.afy) {
                            b = false;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.afA != flag.afA) {
                            b = false;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        b = zzab.equal(this.Dr, flag.Dr);
                        break;
                    }
                    case 5: {
                        b = Arrays.equals(this.axq, flag.axq);
                        break;
                    }
                }
            }
        }
        else {
            b = false;
        }
        return b;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flag(");
        sb.append(this.mVersionCode);
        sb.append(", ");
        sb.append(this.name);
        sb.append(", ");
        switch (this.axr) {
            default: {
                throw new AssertionError((Object)new StringBuilder(31).append("Invalid enum value: ").append(this.axr).toString());
            }
            case 1: {
                sb.append(this.axp);
                break;
            }
            case 2: {
                sb.append(this.afy);
                break;
            }
            case 3: {
                sb.append(this.afA);
                break;
            }
            case 4: {
                sb.append("'");
                sb.append(this.Dr);
                sb.append("'");
                break;
            }
            case 5: {
                if (this.axq == null) {
                    sb.append("null");
                    break;
                }
                sb.append("'");
                sb.append(new String(this.axq, Flag.UTF_8));
                sb.append("'");
                break;
            }
        }
        sb.append(", ");
        sb.append(this.axr);
        sb.append(", ");
        sb.append(this.axs);
        sb.append(")");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public int zza(final Flag flag) {
        final int compareTo = this.name.compareTo(flag.name);
        int n = 0;
        Label_0019: {
            if (compareTo != 0) {
                n = compareTo;
            }
            else {
                final int compare = compare(this.axr, flag.axr);
                if (compare != 0) {
                    n = compare;
                }
                else {
                    switch (this.axr) {
                        default: {
                            throw new AssertionError((Object)new StringBuilder(31).append("Invalid enum value: ").append(this.axr).toString());
                        }
                        case 1: {
                            n = compare(this.axp, flag.axp);
                            break;
                        }
                        case 2: {
                            n = compare(this.afy, flag.afy);
                            break;
                        }
                        case 3: {
                            n = Double.compare(this.afA, flag.afA);
                            break;
                        }
                        case 4: {
                            n = compare(this.Dr, flag.Dr);
                            break;
                        }
                        case 5: {
                            final byte[] axq = this.axq;
                            final byte[] axq2 = flag.axq;
                            n = 0;
                            if (axq == axq2) {
                                break;
                            }
                            if (this.axq == null) {
                                n = -1;
                                break;
                            }
                            final byte[] axq3 = flag.axq;
                            int i = 0;
                            if (axq3 == null) {
                                n = 1;
                                break;
                            }
                            while (i < Math.min(this.axq.length, flag.axq.length)) {
                                final int compare2 = compare(this.axq[i], flag.axq[i]);
                                if (compare2 != 0) {
                                    n = compare2;
                                    break Label_0019;
                                }
                                ++i;
                            }
                            n = compare(this.axq.length, flag.axq.length);
                            break;
                        }
                    }
                }
            }
        }
        return n;
    }
    
    public static class zza implements Comparator<Flag>
    {
        public int zza(final Flag flag, final Flag flag2) {
            int compareTo;
            if (flag.axs == flag2.axs) {
                compareTo = flag.name.compareTo(flag2.name);
            }
            else {
                compareTo = flag.axs - flag2.axs;
            }
            return compareTo;
        }
    }
}
