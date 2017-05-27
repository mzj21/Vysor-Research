// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.zziy;

@zziy
public final class zzc extends zzq.zza
{
    private final AdListener zzawc;
    
    public zzc(final AdListener zzawc) {
        this.zzawc = zzawc;
    }
    
    public void onAdClosed() {
        this.zzawc.onAdClosed();
    }
    
    public void onAdFailedToLoad(final int n) {
        this.zzawc.onAdFailedToLoad(n);
    }
    
    public void onAdLeftApplication() {
        this.zzawc.onAdLeftApplication();
    }
    
    public void onAdLoaded() {
        this.zzawc.onAdLoaded();
    }
    
    public void onAdOpened() {
        this.zzawc.onAdOpened();
    }
}
