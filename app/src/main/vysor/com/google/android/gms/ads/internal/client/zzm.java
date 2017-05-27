// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.internal.zziy;

@zziy
public class zzm
{
    private static final Object zzaok;
    private static zzm zzayd;
    private final zza zzaye;
    private final zzl zzayf;
    
    static {
        zzaok = new Object();
        zza(new zzm());
    }
    
    protected zzm() {
        this.zzaye = new zza();
        this.zzayf = new zzl(new zze(), new zzd(), new zzai(), new zzel(), new zzf(), new zzid(), new zzho());
    }
    
    protected static void zza(final zzm zzayd) {
        synchronized (zzm.zzaok) {
            zzm.zzayd = zzayd;
        }
    }
    
    private static zzm zzjq() {
        synchronized (zzm.zzaok) {
            return zzm.zzayd;
        }
    }
    
    public static zza zzjr() {
        return zzjq().zzaye;
    }
    
    public static zzl zzjs() {
        return zzjq().zzayf;
    }
}
