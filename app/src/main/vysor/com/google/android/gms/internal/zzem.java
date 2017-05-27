// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd;

@zziy
public class zzem extends zzeh.zza
{
    private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzblw;
    
    public zzem(final NativeAppInstallAd.OnAppInstallAdLoadedListener zzblw) {
        this.zzblw = zzblw;
    }
    
    public void zza(final zzeb zzeb) {
        this.zzblw.onAppInstallAdLoaded(this.zzb(zzeb));
    }
    
    zzec zzb(final zzeb zzeb) {
        return new zzec(zzeb);
    }
}
