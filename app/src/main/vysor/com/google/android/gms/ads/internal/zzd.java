// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.safebrowsing.zza;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.internal.zzes;
import com.google.android.gms.ads.internal.overlay.zzm;
import com.google.android.gms.ads.internal.overlay.zzj;
import com.google.android.gms.internal.zzfl;
import com.google.android.gms.internal.zziy;

@zziy
public class zzd
{
    public final zzfl zzamc;
    public final zzj zzamd;
    public final zzm zzame;
    public final com.google.android.gms.ads.internal.safebrowsing.zzd zzamf;
    
    public zzd(final zzfl zzamc, final zzj zzamd, final zzm zzame, final com.google.android.gms.ads.internal.safebrowsing.zzd zzamf) {
        this.zzamc = zzamc;
        this.zzamd = zzamd;
        this.zzame = zzame;
        this.zzamf = zzamf;
    }
    
    public static zzd zzeq() {
        return new zzd(new zzes(), new zzn(), new zzt(), new zza());
    }
}
