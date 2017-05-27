// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd;

@zziy
public class zzen extends zzei.zza
{
    private final NativeContentAd.OnContentAdLoadedListener zzblx;
    
    public zzen(final NativeContentAd.OnContentAdLoadedListener zzblx) {
        this.zzblx = zzblx;
    }
    
    public void zza(final zzed zzed) {
        this.zzblx.onContentAdLoaded(this.zzb(zzed));
    }
    
    zzee zzb(final zzed zzed) {
        return new zzee(zzed);
    }
}
