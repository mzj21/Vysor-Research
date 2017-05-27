// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.phenotype;

import android.os.Parcel;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzab;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Map;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class Configuration extends AbstractSafeParcelable implements Comparable<Configuration>
{
    public static final Parcelable$Creator<Configuration> CREATOR;
    public final int axl;
    public final Flag[] axm;
    public final String[] axn;
    public final Map<String, Flag> axo;
    final int mVersionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    Configuration(final int mVersionCode, final int axl, final Flag[] axm, final String[] axn) {
        this.mVersionCode = mVersionCode;
        this.axl = axl;
        this.axm = axm;
        this.axo = new TreeMap<String, Flag>();
        for (final Flag flag : axm) {
            this.axo.put(flag.name, flag);
        }
        this.axn = axn;
        if (this.axn != null) {
            Arrays.sort(this.axn);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = false;
        if (o != null) {
            final boolean b2 = o instanceof Configuration;
            b = false;
            if (b2) {
                final Configuration configuration = (Configuration)o;
                final int mVersionCode = this.mVersionCode;
                final int mVersionCode2 = configuration.mVersionCode;
                b = false;
                if (mVersionCode == mVersionCode2) {
                    final int axl = this.axl;
                    final int axl2 = configuration.axl;
                    b = false;
                    if (axl == axl2) {
                        final boolean equal = zzab.equal(this.axo, configuration.axo);
                        b = false;
                        if (equal) {
                            final boolean equals = Arrays.equals(this.axn, configuration.axn);
                            b = false;
                            if (equals) {
                                b = true;
                            }
                        }
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.mVersionCode);
        sb.append(", ");
        sb.append(this.axl);
        sb.append(", ");
        sb.append("(");
        final Iterator<Flag> iterator = this.axo.values().iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        if (this.axn != null) {
            final String[] axn = this.axn;
            for (int length = axn.length, i = 0; i < length; ++i) {
                sb.append(axn[i]);
                sb.append(", ");
            }
        }
        else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public int zza(final Configuration configuration) {
        return this.axl - configuration.axl;
    }
}
