// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;

@zziy
public class zzdm
{
    @Nullable
    public static zzdo zza(@Nullable final zzdq zzdq, final long n) {
        zzdo zzc;
        if (zzdq == null) {
            zzc = null;
        }
        else {
            zzc = zzdq.zzc(n);
        }
        return zzc;
    }
    
    public static boolean zza(@Nullable final zzdq zzdq, @Nullable final zzdo zzdo, final long n, final String... array) {
        return zzdq != null && zzdo != null && zzdq.zza(zzdo, n, array);
    }
    
    public static boolean zza(@Nullable final zzdq zzdq, @Nullable final zzdo zzdo, final String... array) {
        return zzdq != null && zzdo != null && zzdq.zza(zzdo, array);
    }
    
    @Nullable
    public static zzdo zzb(@Nullable final zzdq zzdq) {
        zzdo zzla;
        if (zzdq == null) {
            zzla = null;
        }
        else {
            zzla = zzdq.zzla();
        }
        return zzla;
    }
}
