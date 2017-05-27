// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zziy
public final class zzdv extends zzdu.zza
{
    private final OnCustomRenderedAdLoadedListener zzayz;
    
    public zzdv(final OnCustomRenderedAdLoadedListener zzayz) {
        this.zzayz = zzayz;
    }
    
    public void zza(final zzdt zzdt) {
        this.zzayz.onCustomRenderedAdLoaded(new zzds(zzdt));
    }
}
