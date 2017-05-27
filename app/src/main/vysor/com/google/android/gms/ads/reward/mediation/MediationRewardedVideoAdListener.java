// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.reward.mediation;

import com.google.android.gms.ads.reward.RewardItem;

public interface MediationRewardedVideoAdListener
{
    void onAdClicked(final MediationRewardedVideoAdAdapter p0);
    
    void onAdClosed(final MediationRewardedVideoAdAdapter p0);
    
    void onAdFailedToLoad(final MediationRewardedVideoAdAdapter p0, final int p1);
    
    void onAdLeftApplication(final MediationRewardedVideoAdAdapter p0);
    
    void onAdLoaded(final MediationRewardedVideoAdAdapter p0);
    
    void onAdOpened(final MediationRewardedVideoAdAdapter p0);
    
    void onInitializationFailed(final MediationRewardedVideoAdAdapter p0, final int p1);
    
    void onInitializationSucceeded(final MediationRewardedVideoAdAdapter p0);
    
    void onRewarded(final MediationRewardedVideoAdAdapter p0, final RewardItem p1);
    
    void onVideoStarted(final MediationRewardedVideoAdAdapter p0);
}
