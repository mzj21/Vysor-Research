// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.util;

import java.util.Set;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.api.Scope;

public final class zzv
{
    public static String[] zza(final Scope[] array) {
        zzac.zzb(array, "scopes can't be null.");
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i].zzaqg();
        }
        return array2;
    }
    
    public static String[] zzd(final Set<Scope> set) {
        zzac.zzb(set, "scopes can't be null.");
        return zza(set.toArray(new Scope[set.size()]));
    }
}
