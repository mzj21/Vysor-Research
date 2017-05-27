// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.reward;

public interface RewardedVideoAdListener
{
    void onRewarded(final RewardItem p0);
    
    void onRewardedVideoAdClosed();
    
    void onRewardedVideoAdFailedToLoad(final int p0);
    
    void onRewardedVideoAdLeftApplication();
    
    void onRewardedVideoAdLoaded();
    
    void onRewardedVideoAdOpened();
    
    void onRewardedVideoStarted();
}
