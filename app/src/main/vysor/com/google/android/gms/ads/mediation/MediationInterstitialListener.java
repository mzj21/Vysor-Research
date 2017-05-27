// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

public interface MediationInterstitialListener
{
    void onAdClicked(final MediationInterstitialAdapter p0);
    
    void onAdClosed(final MediationInterstitialAdapter p0);
    
    void onAdFailedToLoad(final MediationInterstitialAdapter p0, final int p1);
    
    void onAdLeftApplication(final MediationInterstitialAdapter p0);
    
    void onAdLoaded(final MediationInterstitialAdapter p0);
    
    void onAdOpened(final MediationInterstitialAdapter p0);
}
