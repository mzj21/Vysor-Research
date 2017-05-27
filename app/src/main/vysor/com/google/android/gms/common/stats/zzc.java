// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzrs;

public final class zzc
{
    public static zzrs<Integer> Ef;
    public static zzrs<Integer> Eg;
    
    static {
        zzc.Ef = zzrs.zza("gms:common:stats:max_num_of_events", Integer.valueOf(100));
        zzc.Eg = zzrs.zza("gms:common:stats:max_chunk_size", Integer.valueOf(100));
    }
    
    public static final class zza
    {
        public static zzrs<Integer> Eh;
        public static zzrs<String> Ei;
        public static zzrs<String> Ej;
        public static zzrs<String> Ek;
        public static zzrs<String> El;
        public static zzrs<Long> Em;
        
        static {
            zza.Eh = zzrs.zza("gms:common:stats:connections:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
            zza.Ei = zzrs.zzab("gms:common:stats:connections:ignored_calling_processes", "");
            zza.Ej = zzrs.zzab("gms:common:stats:connections:ignored_calling_services", "");
            zza.Ek = zzrs.zzab("gms:common:stats:connections:ignored_target_processes", "");
            zza.El = zzrs.zzab("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
            zza.Em = zzrs.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(600000L));
        }
    }
    
    public static final class zzb
    {
        public static zzrs<Integer> Eh;
        public static zzrs<Long> Em;
        
        static {
            zzb.Eh = zzrs.zza("gms:common:stats:wakeLocks:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
            zzb.Em = zzrs.zza("gms:common:stats:wakelocks:time_out_duration", Long.valueOf(600000L));
        }
    }
}
