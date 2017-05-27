// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads.mediation;

import com.google.ads.AdRequest;

@Deprecated
public interface MediationBannerListener
{
    void onClick(final MediationBannerAdapter<?, ?> p0);
    
    void onDismissScreen(final MediationBannerAdapter<?, ?> p0);
    
    void onFailedToReceiveAd(final MediationBannerAdapter<?, ?> p0, final AdRequest.ErrorCode p1);
    
    void onLeaveApplication(final MediationBannerAdapter<?, ?> p0);
    
    void onPresentScreen(final MediationBannerAdapter<?, ?> p0);
    
    void onReceivedAd(final MediationBannerAdapter<?, ?> p0);
}
