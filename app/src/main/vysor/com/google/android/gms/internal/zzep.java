// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zziy
public class zzep extends zzek.zza
{
    private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzblz;
    
    public zzep(final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzblz) {
        this.zzblz = zzblz;
    }
    
    public void zza(final zzef zzef) {
        this.zzblz.onCustomTemplateAdLoaded(new zzeg(zzef));
    }
}
