// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.TreeSet;
import android.support.annotation.Nullable;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.google.android.gms.ads.internal.client.AdRequestParcel;

@zziy
class zzfr
{
    private final Object[] mParams;
    
    zzfr(final AdRequestParcel adRequestParcel, final String s, final int n) {
        this.mParams = zza(adRequestParcel, s, n);
    }
    
    private static Object[] zza(final AdRequestParcel adRequestParcel, final String s, final int n) {
        final HashSet set = new HashSet((Collection<? extends E>)Arrays.asList(zzdi.zzbdk.get().split(",")));
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        if (set.contains("networkType")) {
            list.add((String)n);
        }
        if (set.contains("birthday")) {
            list.add((String)adRequestParcel.zzawd);
        }
        if (set.contains("extras")) {
            list.add(zzc(adRequestParcel.extras));
        }
        if (set.contains("gender")) {
            list.add((String)adRequestParcel.zzawe);
        }
        if (set.contains("keywords")) {
            if (adRequestParcel.zzawf != null) {
                list.add(adRequestParcel.zzawf.toString());
            }
            else {
                list.add(null);
            }
        }
        if (set.contains("isTestDevice")) {
            list.add((String)adRequestParcel.zzawg);
        }
        if (set.contains("tagForChildDirectedTreatment")) {
            list.add((String)adRequestParcel.zzawh);
        }
        if (set.contains("manualImpressionsEnabled")) {
            list.add((String)adRequestParcel.zzawi);
        }
        if (set.contains("publisherProvidedId")) {
            list.add(adRequestParcel.zzawj);
        }
        if (set.contains("location")) {
            if (adRequestParcel.zzawl != null) {
                list.add(adRequestParcel.zzawl.toString());
            }
            else {
                list.add(null);
            }
        }
        if (set.contains("contentUrl")) {
            list.add(adRequestParcel.zzawm);
        }
        if (set.contains("networkExtras")) {
            list.add(zzc(adRequestParcel.zzawn));
        }
        if (set.contains("customTargeting")) {
            list.add(zzc(adRequestParcel.zzawo));
        }
        if (set.contains("categoryExclusions")) {
            if (adRequestParcel.zzawp != null) {
                list.add(adRequestParcel.zzawp.toString());
            }
            else {
                list.add(null);
            }
        }
        if (set.contains("requestAgent")) {
            list.add(adRequestParcel.zzawq);
        }
        if (set.contains("requestPackage")) {
            list.add(adRequestParcel.zzawr);
        }
        return list.toArray();
    }
    
    private static String zzc(@Nullable final Bundle bundle) {
        String string;
        if (bundle == null) {
            string = null;
        }
        else {
            final StringBuilder sb = new StringBuilder();
            final Iterator<String> iterator = new TreeSet<String>(bundle.keySet()).iterator();
            while (iterator.hasNext()) {
                final Object value = bundle.get((String)iterator.next());
                String s;
                if (value == null) {
                    s = "null";
                }
                else if (value instanceof Bundle) {
                    s = zzc((Bundle)value);
                }
                else {
                    s = value.toString();
                }
                sb.append(s);
            }
            string = sb.toString();
        }
        return string;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof zzfr && Arrays.equals(this.mParams, ((zzfr)o).mParams);
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.mParams);
    }
    
    @Override
    public String toString() {
        final String value = String.valueOf(Arrays.toString(this.mParams));
        return new StringBuilder(24 + String.valueOf(value).length()).append("[InterstitialAdPoolKey ").append(value).append("]").toString();
    }
}
