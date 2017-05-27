// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zze;
import android.view.View;
import com.google.android.gms.dynamic.zzd;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzh;

@zziy
public final class zzdr extends zzdt.zza
{
    private final zzh zzbiu;
    @Nullable
    private final String zzbiv;
    private final String zzbiw;
    
    public zzdr(final zzh zzbiu, @Nullable final String zzbiv, final String zzbiw) {
        this.zzbiu = zzbiu;
        this.zzbiv = zzbiv;
        this.zzbiw = zzbiw;
    }
    
    public String getContent() {
        return this.zzbiw;
    }
    
    public void recordClick() {
        this.zzbiu.zzen();
    }
    
    public void recordImpression() {
        this.zzbiu.zzeo();
    }
    
    public void zzi(@Nullable final zzd zzd) {
        if (zzd != null) {
            this.zzbiu.zzc(zze.zzae(zzd));
        }
    }
    
    public String zzle() {
        return this.zzbiv;
    }
}
