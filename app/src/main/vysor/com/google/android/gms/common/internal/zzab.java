// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public final class zzab
{
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static int hashCode(final Object... array) {
        return Arrays.hashCode(array);
    }
    
    public static zza zzx(final Object o) {
        return new zza(o);
    }
    
    public static final class zza
    {
        private final List<String> CU;
        private final Object zzctc;
        
        private zza(final Object o) {
            this.zzctc = zzac.zzy(o);
            this.CU = new ArrayList<String>();
        }
        
        @Override
        public String toString() {
            final StringBuilder append = new StringBuilder(100).append(this.zzctc.getClass().getSimpleName()).append('{');
            for (int size = this.CU.size(), i = 0; i < size; ++i) {
                append.append(this.CU.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
        
        public zza zzg(final String s, final Object o) {
            final List<String> cu = this.CU;
            final String s2 = zzac.zzy(s);
            final String value = String.valueOf(String.valueOf(o));
            cu.add(new StringBuilder(1 + String.valueOf(s2).length() + String.valueOf(value).length()).append(s2).append("=").append(value).toString());
            return this;
        }
    }
}
