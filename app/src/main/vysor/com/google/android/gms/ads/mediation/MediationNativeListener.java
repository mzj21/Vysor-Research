// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

public interface MediationNativeListener
{
    void onAdClicked(final MediationNativeAdapter p0);
    
    void onAdClosed(final MediationNativeAdapter p0);
    
    void onAdFailedToLoad(final MediationNativeAdapter p0, final int p1);
    
    void onAdImpression(final MediationNativeAdapter p0);
    
    void onAdLeftApplication(final MediationNativeAdapter p0);
    
    void onAdLoaded(final MediationNativeAdapter p0, final NativeAdMapper p1);
    
    void onAdOpened(final MediationNativeAdapter p0);
}
