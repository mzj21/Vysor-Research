// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import com.google.ads.AdRequest;

@Deprecated
public interface MediationInterstitialListener
{
    void onDismissScreen(final MediationInterstitialAdapter<?, ?> p0);
    
    void onFailedToReceiveAd(final MediationInterstitialAdapter<?, ?> p0, final AdRequest.ErrorCode p1);
    
    void onLeaveApplication(final MediationInterstitialAdapter<?, ?> p0);
    
    void onPresentScreen(final MediationInterstitialAdapter<?, ?> p0);
    
    void onReceivedAd(final MediationInterstitialAdapter<?, ?> p0);
}
